package com.mahmodhota.worldfoodadventure.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.mahmodhota.worldfoodadventure.game.rendering.*
import com.mahmodhota.worldfoodadventure.game.ui.*
import kotlinx.coroutines.delay
import kotlin.math.*

@Composable
fun WorldFoodGame() {
    val context = LocalContext.current
    val density = LocalDensity.current
    val insets = WindowInsets.safeDrawing.asPaddingValues()
    val bottomInsetPx = with(density) { insets.calculateBottomPadding().toPx() }
    val topInsetPx = with(density) { insets.calculateTopPadding().toPx() }
    val bottomSafeZonePx = remember(density) { with(density) { BOTTOM_AD_SAFE_ZONE_DP.toPx() } }
    
    // Shared constants for positioning
    val cartGapPx = remember(density) { with(density) { CART_BOTTOM_GAP_DP.toPx() } }
    val cartHeightPx = remember(density) { with(density) { PLAYER_CART_SIZE_DP.toPx() } }

    val soundManager = remember { SoundManager(context) }
    val musicManager = remember { MusicManager(context) }
    val saveManager = remember { SaveManager(context) }
    
    val rewardManager = remember { RewardManager(saveManager) }
    val shopManager = remember { ShopManager(saveManager) }
    val worldMapManager = remember { WorldMapManager(saveManager) }
    val dailyChallengeManager = remember { DailyChallengeManager(saveManager) }
    val foodAlbumManager = remember { FoodAlbumManager(saveManager) }
    val npcManager = remember { NpcManager() }
    val weatherManager = remember { WeatherManager() }
    val environmentManager = remember { EnvironmentManager() }
    val livingWorldManager = remember { LivingWorldManager() }
    val cameraManager = remember { CameraManager() }
    val ambientSoundManager = remember { AmbientSoundManager(context, saveManager) }
    
    val playerManager = remember { PlayerManager(saveManager) }
    val statsManager = remember { StatisticsManager(saveManager) }
    val expManager = remember { ExperienceManager(saveManager) }
    val museumManager = remember { MuseumManager(saveManager) }
    val galleryManager = remember { GalleryManager(saveManager) }
    val achievementManager = remember { AchievementManager(saveManager, soundManager) }
    
    val worldProgressManager = remember { WorldProgressManager(saveManager) }
    val searchManager = remember { CountrySearchManager(CountryRepository) }
    val homelandManager = remember { PlayerHomelandManager(saveManager) }
    
    val feedbackManager = remember { FeedbackManager(saveManager) }
    
    val dailyLoginManager = remember { DailyLoginManager(saveManager) }
    val seasonManager = remember { SeasonManager() }
    val eventManager = remember { EventManager() }
    val showcaseManager = remember { LiveShowcaseManager() }
    val tvTravelManager = remember { TvTravelManager(saveManager) }

    // --- ENCYCLOPEDIA & FAVORITES ---
    var selectedInfoCountry by remember { mutableStateOf<CountryData?>(null) }
    var favoriteCountries by remember { mutableStateOf(saveManager.getStringSet("favorites", emptySet())) }

    fun toggleFavorite(id: String) {
        val newSet = if (favoriteCountries.contains(id)) favoriteCountries - id else favoriteCountries + id
        favoriteCountries = newSet
        saveManager.setStringSet("favorites", newSet)
    }

    // --- SETTINGS STATE ---
    var sOn by remember { mutableStateOf(saveManager.getBoolean("s_on", true)) }
    var mOn by remember { mutableStateOf(saveManager.getBoolean("m_on", true)) }
    var mSt by remember { 
        mutableStateOf(
            try { MusicStyle.valueOf(saveManager.getString("m_st", MusicStyle.GERMANY.name) ?: MusicStyle.GERMANY.name) }
            catch (e: Exception) { MusicStyle.GERMANY }
        )
    }

    val engine = remember { 
        GameEngine(soundManager, saveManager, achievementManager, dailyChallengeManager, rewardManager, foodAlbumManager, statsManager, expManager, museumManager, galleryManager, eventManager, worldProgressManager)
    }

    // --- PREMIUM 2.0 LIGHTING & MUSIC ---
    var targetMusicVolume by remember { mutableFloatStateOf(1.0f) }
    var currentMusicVolume by remember { mutableFloatStateOf(0.0f) }
    
    LaunchedEffect(currentMusicVolume) {
        musicManager.setVolume(currentMusicVolume)
    }
    
    LaunchedEffect(mOn, engine.state) {
        targetMusicVolume = if (mOn && engine.state != GameState.PAUSED) 1.0f else 0.0f
    }
    
    LaunchedEffect(Unit) {
        while(true) {
            val step = 0.02f
            if (abs(currentMusicVolume - targetMusicVolume) > 0.01f) {
                currentMusicVolume = if (currentMusicVolume < targetMusicVolume) 
                    (currentMusicVolume + step).coerceAtMost(targetMusicVolume)
                else 
                    (currentMusicVolume - step).coerceIn(targetMusicVolume, 1.0f)
            }
            delay(50)
        }
    }

    // --- PREMIUM 2.0 LIGHTING ---
    val lightingColor = remember(engine.weather, engine.state) {
        when(engine.weather) {
            WeatherType.MORNING -> Color(0xFFBBDEFB).copy(0.12f) // Soft Blue Morning
            WeatherType.SUNSET -> Color(0xFFFFCC80).copy(0.18f) // Warm Sunset
            WeatherType.NIGHT, WeatherType.STARS -> Color(0xFF1A237E).copy(0.28f) // Deep Night
            WeatherType.CLOUDY, WeatherType.RAIN -> Color(0xFF90A4AE).copy(0.20f) // Overcast
            else -> Color.Transparent
        }
    }

    // --- SEASONAL STATE ---
    var currentSeason by remember { mutableStateOf(seasonManager.currentSeason) }
    
    // Safety timeout for Showcase
    var lastStateChangeTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
    
    LaunchedEffect(engine.state) {
        lastStateChangeTime = System.currentTimeMillis()
    }
    
    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            if (engine.playMode == PlayMode.LIVE_SHOWCASE && showcaseManager.isRunning) {
                if (System.currentTimeMillis() - lastStateChangeTime > 60000) { // 60 seconds stuck
                    showcaseManager.isRunning = false
                    engine.playMode = PlayMode.NORMAL
                    engine.state = GameState.MENU
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        currentSeason = seasonManager.currentSeason
    }


    // --- WORLD DISCOVERY STATE (v0.43) ---
    var showIntroCard by remember { mutableStateOf(false) }
    var showFactCard by remember { mutableStateOf(false) }
    var currentDiscoveryCountry by remember { mutableStateOf<CountryData?>(null) }


    LaunchedEffect(Unit) {
        var last = System.currentTimeMillis()
        while(true) {
            delay(5000)
            val now = System.currentTimeMillis()
            statsManager.addPlayTime((now - last) / 1000)
            last = now
        }
    }

    DisposableEffect(Unit) { onDispose { soundManager.release(); musicManager.release(); ambientSoundManager.release() } }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    musicManager.pause()
                    ambientSoundManager.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    if (engine.state != GameState.PAUSED && engine.state != GameState.SPLASH && engine.state != GameState.TUTORIAL && engine.state != GameState.WORLD_TRAVEL && engine.state != GameState.TV_TRAVEL) {
                        musicManager.play(mSt, mOn)
                        ambientSoundManager.updateAmbient(engine.countryId, mOn)
                    }
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(Unit) { 
        delay(2000)
        dailyLoginManager.checkLogin()
        if (!dailyLoginManager.rewardClaimedToday) engine.state = GameState.DAILY_LOGIN
        else engine.state = if (saveManager.getBoolean("seen", false)) GameState.MENU else GameState.TUTORIAL 
    }
    
    LaunchedEffect(engine.state, mOn, mSt) { 
        if (engine.state == GameState.PAUSED) {
            musicManager.pause()
            ambientSoundManager.stop()
        } else if (engine.state != GameState.SPLASH && engine.state != GameState.TUTORIAL && engine.state != GameState.WORLD_TRAVEL && engine.state != GameState.TV_TRAVEL) {
            musicManager.play(mSt, mOn)
            ambientSoundManager.updateAmbient(engine.countryId, mOn)
        } 
    }

    LaunchedEffect(engine.state) {
        if (engine.state == GameState.COUNTDOWN) { 
            // Show Intro Card on first visit (v0.43)
            if (!(worldProgressManager.visitedStatus[engine.countryId] ?: false)) {
                currentDiscoveryCountry = CountryRepository.getCountry(engine.countryId)
                showIntroCard = true
                worldProgressManager.markVisited(engine.countryId)
            }

            engine.countdown = 3
            while (engine.countdown > 0) { delay(1000); engine.countdown-- }
            engine.state = GameState.PLAYING 
        }
        if (engine.state == GameState.PLAYING) {
            var last = withFrameNanos { it }
            while (engine.state == GameState.PLAYING) {
                val curr = withFrameNanos { it }
                val dt = ((curr - last) / 1_000_000_000f).coerceAtMost(0.05f)
                last = curr
                
                val aiTarget = if (engine.playMode == PlayMode.LIVE_SHOWCASE) showcaseManager.aiTargetX else null
                engine.update(dt, sOn, aiTarget, bottomSafeZonePx, cartGapPx, cartHeightPx)
                
                if (engine.playMode == PlayMode.LIVE_SHOWCASE) {
                    try { showcaseManager.update(dt, engine.pX, engine.sW, engine.sH, engine.items) } catch (e: Exception) { android.util.Log.e("WorldFood", "showcaseManager.update failed", e) }
                }

                try { npcManager.update(dt, engine.sW, engine.sH, engine.countryId, bottomSafeZonePx + bottomInsetPx) } catch (e: Exception) { android.util.Log.e("WorldFood", "npcManager.update failed", e) }
                try { environmentManager.update(dt, engine.sW, engine.sH) } catch (e: Exception) { android.util.Log.e("WorldFood", "environmentManager.update failed", e) }
                try { livingWorldManager.update(dt, engine.sW, engine.sH, engine.countryId, bottomSafeZonePx + bottomInsetPx) } catch (e: Exception) { android.util.Log.e("WorldFood", "livingWorldManager.update failed", e) }
                try { cameraManager.update(dt) } catch (e: Exception) { android.util.Log.e("WorldFood", "cameraManager.update failed", e) }
            }
        }
    }

    // Global visual update loop (even when not playing)
    LaunchedEffect(Unit) {
        var last = withFrameNanos { it }
        while (true) {
            val curr = withFrameNanos { it }
            val dt = ((curr - last) / 1_000_000_000f).coerceAtMost(0.05f)
            last = curr
            val sw = if (engine.sW > 0) engine.sW else 1080f
            
            if (engine.state != GameState.PLAYING) {
                val currentId = if (engine.state == GameState.TV_TRAVEL) tvTravelManager.getCurrentCountry().id else engine.countryId
                try { cameraManager.update(dt) } catch (e: Exception) { android.util.Log.e("WorldFood", "cameraManager.update failed", e) }
                try { environmentManager.update(dt, sw, engine.sH) } catch (e: Exception) { android.util.Log.e("WorldFood", "environmentManager.update failed", e) }
                try { livingWorldManager.update(dt, sw, engine.sH, currentId, bottomSafeZonePx + bottomInsetPx) } catch (e: Exception) { android.util.Log.e("WorldFood", "livingWorldManager.update failed", e) }
                try { npcManager.update(dt, sw, engine.sH, if (engine.state == GameState.PAUSED || engine.state == GameState.TV_TRAVEL) currentId else null, bottomSafeZonePx + bottomInsetPx) } catch (e: Exception) { android.util.Log.e("WorldFood", "npcManager.update failed", e) }
            }
        }
    }

    LaunchedEffect(engine.state, engine.playMode) {
        if (engine.playMode == PlayMode.LIVE_SHOWCASE && showcaseManager.isRunning) {
            saveManager.isWriteEnabled = false // Double protection
            delay(3000)
            if (!showcaseManager.isRunning) return@LaunchedEffect // Check again after delay

            when (engine.state) {
                GameState.MENU -> {
                    engine.countryId = "germany"
                    engine.setupLevel(engine.countryId, 5, weatherManager)
                }
                GameState.LEVEL_COMPLETE -> {
                    engine.state = GameState.TREASURE_CHEST
                }
                GameState.BOSS_DEFEATED -> {
                    engine.state = GameState.TREASURE_CHEST
                }
                GameState.TREASURE_CHEST -> {
                    val playable = CountryRepository.getPlayableCountries()
                    val index = playable.indexOfFirst { it.id == engine.countryId }
                    val next = if (index == -1 || index == playable.size - 1) playable.first() else playable[index + 1]
                    engine.previousCountryId = engine.countryId
                    engine.countryId = next.id
                    engine.state = GameState.WORLD_TRAVEL
                }
                GameState.GAME_OVER -> {
                    engine.setupLevel(engine.countryId, 5, weatherManager)
                }
                else -> {}
            }
        } else if (engine.state == GameState.TV_TRAVEL) {
            saveManager.isWriteEnabled = false
        } else {
            saveManager.isWriteEnabled = true
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val swPx = with(density) { maxWidth.toPx() }
            val shPx = with(density) { maxHeight.toPx() }
            
            LaunchedEffect(swPx, shPx, bottomInsetPx) {
                engine.sW = swPx
                engine.sH = shPx
                engine.bottomInsetPx = bottomInsetPx
            }

            val currentBgId = when (engine.state) { 
                GameState.COUNTDOWN, GameState.PLAYING, GameState.PAUSED, 
                GameState.LEVEL_COMPLETE, GameState.TREASURE_CHEST, GameState.GAME_OVER,
                GameState.BOSS_INTRO, GameState.BOSS_BATTLE, GameState.BOSS_DEFEATED,
                GameState.TV_TRAVEL -> if (engine.state == GameState.TV_TRAVEL) tvTravelManager.getCurrentCountry().id else engine.countryId
                else -> null 
            }

            // --- BACKGROUND ANIMATION LAYERS ---
            if (engine.state != GameState.TV_TRAVEL) {
                Box(modifier = Modifier.fillMaxSize().graphicsLayer {
                    translationX = 0f 
                    translationY = 0f
                    scaleX = 1f
                    scaleY = 1f
                }) {
                    BackgroundStyle(currentBgId, engine.state)
                    
                    // Living World Layers
                    EnvironmentLayer(environmentManager)
                    WildlifeLayer(livingWorldManager)

                    // --- WEATHER OVERLAY ---
                    if (currentBgId != null) {
                        val currentCountry = CountryRepository.getCountry(currentBgId) ?: CountryRepository.getFirstCountry()
                        WeatherOverlay(engine.weather, currentCountry, currentSeason, festivalActive = false)
                    }
                    
                    if (engine.state == GameState.MENU || engine.state == GameState.PAUSED) {
                        IdleVisuals(engine.weather, engine.countryId, bottomSafeZonePx + bottomInsetPx)
                    }
                    
                    NpcLayer(npcManager)

                    // --- PREMIUM 2.0 LIGHTING OVERLAY (Drawn over background but behind UI) ---
                    Box(modifier = Modifier.fillMaxSize().background(lightingColor))
                }
            }
            
            // --- STATIC UI LAYER ---
            Box(modifier = Modifier.fillMaxSize()) {
                // --- NEW DISCOVERY POPUP ---
                val discovered = foodAlbumManager.lastDiscovered
                if (discovered != null) {
                    Box(modifier = Modifier.fillMaxSize().padding(16.dp).safeDrawingPadding(), contentAlignment = Alignment.TopCenter) {
                        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF27AE60)), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(8.dp)) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(discovered.emoji, fontSize = 28.sp)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text("NEW FOOD DISCOVERED: ${discovered.name}!", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                        LaunchedEffect(discovered) { delay(2500); foodAlbumManager.clearLastDiscovered() }
                    }
                }

                when (engine.state) {
                    GameState.SPLASH -> Column(modifier = Modifier.fillMaxSize().background(Color.White).safeDrawingPadding(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { Text("🌍", fontSize = 80.sp); Text("WorldFood", fontSize = 36.sp, fontWeight = FontWeight.Black); Text("Adventure", fontSize = 24.sp, color = Color(0xFFE67E22)) }
                    
                    GameState.TUTORIAL -> Column(modifier = Modifier.fillMaxSize().padding(32.dp).safeDrawingPadding(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { Text("How to Play", fontSize = 32.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(24.dp)); Text("Move left/right to catch food!\nAvoid bombs to survive.", textAlign = TextAlign.Center); Spacer(modifier = Modifier.height(48.dp)); GameButton("START", Color(0xFF2ECC71), { saveManager.setBoolean("seen", true); engine.state = GameState.MENU }) }
                    
                    GameState.MENU -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { MainMenuScreen(engine, rewardManager, expManager, worldProgressManager, showcaseManager, tvTravelManager, playerManager, weatherManager) }
                    
                    GameState.WORLD_MAP -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { WorldMapScreen(
                        progress = worldProgressManager,
                        onBack = { engine.returnToMenu() },
                        onPlay = { c ->
                            engine.previousCountryId = engine.countryId
                            engine.countryId = c.id
                            engine.state = GameState.WORLD_TRAVEL
                            soundManager.play(soundManager.click, sOn)
                        },
                        onShowInfo = { c ->
                            selectedInfoCountry = c
                            engine.state = GameState.COUNTRY_INFO
                        },
                        isFavorite = { id -> favoriteCountries.contains(id) },
                        onToggleFavorite = { id -> toggleFavorite(id) }
                    ) }

                    GameState.PASSPORT -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { PassportScreen(worldProgressManager, foodAlbumManager, statsManager, onBack = { engine.returnToMenu() }) }
                    
                    GameState.ACHIEVEMENTS -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { AchievementScreen(achievementManager, onBack = { engine.returnToMenu() }) }
                    
                    GameState.DAILY_CHALLENGE -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { DailyChallengeScreen(dailyChallengeManager, engine, onBack = { engine.returnToMenu() }) }
                    
                    GameState.SHOP -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { ShopScreen(rewardManager, shopManager, saveManager, onBack = { engine.returnToMenu() }) }
                    
                    GameState.SETTINGS -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { SettingsScreen(
                        sOn = sOn,
                        onSOnChanged = { sOn = it; saveManager.setBoolean("s_on", it) },
                        mOn = mOn,
                        onMOnChanged = { mOn = it; saveManager.setBoolean("m_on", it) },
                        mSt = mSt,
                        onMStChanged = { mSt = it; saveManager.setString("m_st", it.name) },
                        ambientSoundManager = ambientSoundManager,
                        onResetProgress = {
                            engine.performFullReset(shopManager, worldMapManager, npcManager, ambientSoundManager, playerManager, dailyLoginManager)
                        },
                        onBack = { engine.returnToMenu() }
                    ) }
                    
                    GameState.HIGH_SCORES -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { HighScoresScreen(engine.highScore, onBack = { engine.returnToMenu() }) }
                    
                    GameState.FOOD_ALBUM -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { FoodAlbumScreen(foodAlbumManager, onBack = { engine.returnToMenu() }) }

                    GameState.LEVEL_UP -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { LevelUpScreen(expManager) { engine.returnToMenu() } }
                    
                    GameState.PROFILE -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { ProfileScreen(playerManager, expManager, worldProgressManager, foodAlbumManager) { engine.returnToMenu() } }
                    
                    GameState.STATISTICS -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { StatisticsScreen(statsManager) { engine.returnToMenu() } }

                    GameState.COUNTRY_INFO -> {
                        selectedInfoCountry?.let { country ->
                            Box(Modifier.fillMaxSize().safeDrawingPadding()) {
                                CountryInfoScreen(
                                    country = country,
                                    isFavorite = favoriteCountries.contains(country.id),
                                    onToggleFavorite = { toggleFavorite(country.id) },
                                    onBack = { engine.state = GameState.WORLD_MAP },
                                    onPlay = {
                                        engine.previousCountryId = engine.countryId
                                        engine.countryId = country.id
                                        engine.state = GameState.WORLD_TRAVEL
                                        soundManager.play(soundManager.click, sOn)
                                    }
                                )
                            }
                        }
                    }

                    GameState.GALLERY -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { GalleryMenuScreen { engine.state = it } }
                    
                    GameState.DAILY_LOGIN -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { DailyLoginScreen(dailyLoginManager, rewardManager, shopManager, soundManager, sOn) { 
                        engine.state = if (saveManager.getBoolean("seen", false)) GameState.MENU else GameState.TUTORIAL 
                    } }
                    
                    GameState.SEASONAL_EVENTS -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { SeasonalEventsScreen(seasonManager, eventManager) { engine.returnToMenu() } }

                    GameState.CHALLENGE_ARENA -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { ChallengeArenaScreen(onBack = { engine.returnToMenu() }) { 
                        engine.setupBossBattle(it.id, rewardManager.maxLives)
                    } }

                    GameState.BOSS_INTRO -> Box(Modifier.fillMaxSize().safeDrawingPadding()) { BossIntroScreen(engine.countryId) { engine.state = GameState.BOSS_BATTLE } }
                    
                    GameState.BOSS_BATTLE -> Box(Modifier.fillMaxSize()) {
                        BossBattleScreen(engine, onHome = { engine.returnToMenu() }) { 
                            engine.state = GameState.PAUSED 
                        }
                    }

                    GameState.BOSS_DEFEATED -> Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFFF1C40F), Color(0xFFE67E22)))), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                            Text("👑 BOSS DEFEATED! 👑", fontSize = 42.sp, fontWeight = FontWeight.Black, color = Color.White, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(48.dp))
                            Surface(modifier = Modifier.size(140.dp), shape = RoundedCornerShape(70.dp), color = Color.White.copy(0.2f)) {
                                Box(contentAlignment = Alignment.Center) { Text("🏆", fontSize = 80.sp) }
                            }
                            Spacer(modifier = Modifier.height(60.dp))
                            GameButton("COLLECT LEGENDARY LOOT", Color.White, {
                                engine.claimRewardSafely(shopManager, isBoss = true)
                            }, textColor = Color(0xFFE67E22))
                        }
                    }

                    GameState.WORLD_TRAVEL -> {
                        val from = CountryRepository.getCountry(engine.previousCountryId) ?: CountryRepository.getFirstCountry()
                        val to = CountryRepository.getCountry(engine.countryId) ?: CountryRepository.getFirstCountry()
                        WorldTravelScreen(from, to, worldProgressManager.visitedStatus[to.id] ?: false) {
                            engine.setupLevel(to.id, rewardManager.maxLives, weatherManager)
                        }
                    }

                    GameState.COUNTDOWN -> Box(Modifier.fillMaxSize(), Alignment.Center) { Text(if (engine.countdown > 0) "${engine.countdown}" else "GO!", fontSize = 120.sp, fontWeight = FontWeight.Black) }
                    
                    GameState.PLAYING, GameState.PAUSED -> {
                        PlayScreen(
                            s = engine.currentScore, 
                            l = engine.lives, 
                            pX = engine.pX, 
                            cId = engine.countryId, 
                            skId = shopManager.selectedSkinId, 
                            items = engine.items, 
                            sub = "Lives: ${"❤️".repeat(engine.lives)}", 
                            pa = engine.state == GameState.PAUSED, 
                            bottomSafeZonePx = bottomSafeZonePx, 
                            bottomInsetPx = bottomInsetPx, 
                            onM = {
                                showcaseManager.onManualInput()
                                engine.tX = (engine.tX + it).coerceIn(0.1f, 0.9f) 
                            }, 
                            onP = { engine.state = if (engine.state == GameState.PLAYING) GameState.PAUSED else GameState.PLAYING }, 
                            onR = { engine.returnToMenu() }
                        )
                    }
                    
                    GameState.LEVEL_COMPLETE -> Box(modifier = Modifier.fillMaxSize().background(Color(0xFF2ECC71).copy(0.9f)), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("MISSION COMPLETE", fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Black)
                            Spacer(modifier = Modifier.height(48.dp))
                            GameButton("CLAIM REWARD", Color.White, {
                                currentDiscoveryCountry = CountryRepository.getCountry(engine.countryId)
                                showFactCard = true
                                engine.claimRewardSafely(shopManager)
                            }, textColor = Color.Black)
                        }
                    }
                    
                    GameState.TREASURE_CHEST -> TreasureChestScreen(engine.activeReward, soundManager, sOn, engine.isChallengeMode, onDone = { engine.returnToMenu() })
                    
                    GameState.GAME_OVER -> Column(Modifier.fillMaxSize().background(Color(0xFFE74C3C)), Arrangement.Center, Alignment.CenterHorizontally) { Text("GAME OVER", fontSize = 48.sp, color = Color.White, fontWeight = FontWeight.Black); Text("Score: ${engine.currentScore}", color = Color.White); Spacer(Modifier.height(48.dp)); GameButton("TRY AGAIN", Color.White, { engine.state = GameState.WORLD_MAP }, textColor = Color(0xFFE74C3C)) }

                    GameState.FEEDBACK -> FeedbackScreen(onBack = { engine.returnToMenu() })
                    
                    GameState.FEEDBACK_HISTORY -> FeedbackHistoryScreen(onBack = { engine.state = GameState.FEEDBACK })
                    
                    GameState.TV_TRAVEL -> TvTravelScreen(tvTravelManager, musicManager, ambientSoundManager, environmentManager, livingWorldManager, npcManager, weatherManager, onExit = { engine.stopTvMode(tvTravelManager) })

                }

                if (engine.playMode == PlayMode.LIVE_SHOWCASE && showcaseManager.isRunning) {
                    ShowcaseOverlay(showcaseManager, engine, mOn, weatherManager, { mOn = it; saveManager.setBoolean("m_on", it) })
                }

                if (showIntroCard && currentDiscoveryCountry != null) {
                    CountryIntroCard(currentDiscoveryCountry!!) { showIntroCard = false }
                }
                if (showFactCard && currentDiscoveryCountry != null) {
                    DidYouKnowCard(currentDiscoveryCountry!!) { showFactCard = false }
                }
            }
        }
    }
}
