package com.mahmodhota.worldfoodadventure.game

import android.graphics.Paint
import kotlin.random.Random
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.*

@Composable
fun WorldFoodGame() {
    val context = LocalContext.current
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

    var previousCountryId by remember { mutableStateOf("germany") }

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
                engine.update(dt, sOn, aiTarget)
                
                if (engine.playMode == PlayMode.LIVE_SHOWCASE) {
                    try { showcaseManager.update(dt, engine.pX, engine.sW, engine.sH, engine.items) } catch (e: Exception) { android.util.Log.e("WorldFood", "showcaseManager.update failed", e) }
                }

                try { npcManager.update(dt, engine.sW, engine.countryId) } catch (e: Exception) { android.util.Log.e("WorldFood", "npcManager.update failed", e) }
                try { environmentManager.update(dt, engine.sW) } catch (e: Exception) { android.util.Log.e("WorldFood", "environmentManager.update failed", e) }
                try { livingWorldManager.update(dt, engine.sW, engine.countryId) } catch (e: Exception) { android.util.Log.e("WorldFood", "livingWorldManager.update failed", e) }
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
                try { environmentManager.update(dt, sw) } catch (e: Exception) { android.util.Log.e("WorldFood", "environmentManager.update failed", e) }
                try { livingWorldManager.update(dt, sw, currentId) } catch (e: Exception) { android.util.Log.e("WorldFood", "livingWorldManager.update failed", e) }
                try { npcManager.update(dt, sw, if (engine.state == GameState.PAUSED || engine.state == GameState.TV_TRAVEL) currentId else null) } catch (e: Exception) { android.util.Log.e("WorldFood", "npcManager.update failed", e) }
            }
            delay(16)
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
                    previousCountryId = engine.countryId
                    engine.countryId = next.id
                    engine.state = GameState.WORLD_TRAVEL
                }
                GameState.GAME_OVER -> {
                    engine.setupLevel(engine.countryId, 5, weatherManager)
                }
                else -> {}
            }
        } else {
            saveManager.isWriteEnabled = true
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize()) {
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
                        IdleVisuals(engine.weather, engine.countryId)
                    }
                    
                    NpcLayer(npcManager)

                    // --- PREMIUM 2.0 LIGHTING OVERLAY (Drawn over background but behind UI) ---
                    Box(modifier = Modifier.fillMaxSize().background(lightingColor))
                }
            }
            
            // --- STATIC UI LAYER ---
            Box(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                // --- NEW DISCOVERY POPUP ---
                val discovered = foodAlbumManager.lastDiscovered
                if (discovered != null) {
                    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.TopCenter) {
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
                    GameState.SPLASH -> Column(modifier = Modifier.fillMaxSize().background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { Text("🌍", fontSize = 80.sp); Text("WorldFood", fontSize = 36.sp, fontWeight = FontWeight.Black); Text("Adventure", fontSize = 24.sp, color = Color(0xFFE67E22)) }
                    
                    GameState.TUTORIAL -> Column(modifier = Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { Text("How to Play", fontSize = 32.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(24.dp)); Text("Move left/right to catch food!\nAvoid bombs to survive.", textAlign = TextAlign.Center); Spacer(modifier = Modifier.height(48.dp)); GameButton("START", Color(0xFF2ECC71), { saveManager.setBoolean("seen", true); engine.state = GameState.MENU }) }
                    
                    GameState.MENU -> Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 40.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("WorldFood", fontSize = 48.sp, fontWeight = FontWeight.Black, color = Color.White); Text("Adventure", fontSize = 24.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
                        
                        Text(worldProgressManager.getExplorerTitle().uppercase(), color = Color(0xFF2ECC71), fontWeight = FontWeight.Black, fontSize = 12.sp)

                        Spacer(modifier = Modifier.height(16.dp))
                        XpBar(expManager)

                        Spacer(modifier = Modifier.weight(1f))
                        Text("AUTOMATIC MODE: ${if (showcaseManager.isRunning) "ON" else "OFF"}", color = if (showcaseManager.isRunning) Color(0xFF2ECC71) else Color.White.copy(0.6f), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("🪙 ${rewardManager.coins}", fontSize = 22.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (showcaseManager.isRunning) {
                                GameButton("STOP AUTO", Color(0xFFE74C3C), { 
                                    showcaseManager.stop()
                                    engine.playMode = PlayMode.NORMAL
                                    engine.state = GameState.MENU
                                }, modifier = Modifier.weight(1f))
                            } else {
                                GameButton("START AUTO", Color(0xFF9B59B6), { 
                                    engine.playMode = PlayMode.LIVE_SHOWCASE
                                    showcaseManager.start()
                                    engine.countryId = "germany"
                                    engine.setupLevel(engine.countryId, 5, weatherManager)
                                }, modifier = Modifier.weight(1f))
                            }
                            GameButton("PLAY", Color(0xFF2ECC71), { 
                                engine.playMode = PlayMode.NORMAL
                                showcaseManager.stop()
                                engine.setupLevel(engine.countryId, rewardManager.maxLives, weatherManager) 
                            }, modifier = Modifier.weight(1f))
                            IconButton(onClick = { engine.state = GameState.PROFILE }, modifier = Modifier.size(54.dp).background(Color.White.copy(0.3f), RoundedCornerShape(12.dp))) { Text(playerManager.avatar, fontSize = 24.sp) }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            GameButton("📺 TV MODE", Color(0xFF8E44AD), { 
                                tvTravelManager.start()
                                engine.state = GameState.TV_TRAVEL
                            }, modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            GameButton("PASSPORT", Color(0xFF2980B9), { engine.state = GameState.PASSPORT }, modifier = Modifier.weight(1f))
                            GameButton("MAP", Color(0xFFE67E22), { engine.state = GameState.WORLD_MAP }, modifier = Modifier.weight(1f))
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            GameButton("ALBUM", Color(0xFF27AE60), { engine.state = GameState.FOOD_ALBUM }, modifier = Modifier.weight(1f))
                            GameButton("SHOP", Color(0xFFF1C40F), { engine.state = GameState.SHOP }, modifier = Modifier.weight(1f), textColor = Color.Black)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            GameButton("STATS", Color(0xFF3498DB), { engine.state = GameState.STATISTICS }, modifier = Modifier.weight(1f))
                            GameButton("SETTINGS", Color(0xFF95A5A6), { engine.state = GameState.SETTINGS }, modifier = Modifier.weight(1f))
                        }
                    }
                    
                    GameState.WORLD_MAP -> WorldMapScreen(searchManager, worldProgressManager, homelandManager, onBack = { engine.state = GameState.MENU }) { c ->
                        previousCountryId = engine.countryId
                        engine.countryId = c.id
                        engine.state = GameState.WORLD_TRAVEL
                        soundManager.play(soundManager.click, sOn)
                    }

                    GameState.PASSPORT -> PassportScreen(worldProgressManager, foodAlbumManager, statsManager, onBack = { engine.state = GameState.MENU })
                    
                    GameState.ACHIEVEMENTS -> Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("TROPHIES", fontSize = 32.sp, fontWeight = FontWeight.Black); Spacer(modifier = Modifier.height(20.dp))
                        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) { 
                            items(items = allAchievements) { a -> 
                                Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) { 
                                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) { 
                                        Text(if (achievementManager.unlockedAchMap[a.id]!=null) a.icon else "❓", fontSize = 32.sp)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column { 
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(a.title, fontWeight = FontWeight.Bold)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text("[${a.tier}]", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Black)
                                            }
                                            Text(a.desc, fontSize = 12.sp, color = Color.Gray) 
                                        } 
                                    } 
                                } 
                            } 
                        }
                        GameButton("BACK", Color.Gray, { engine.state = GameState.MENU })
                    }
                    
                    GameState.DAILY_CHALLENGE -> Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("DAILY CHALLENGE", fontSize = 28.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(16.dp))
                        Card(modifier = Modifier.fillMaxWidth()) { Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) { Text(dailyChallengeManager.currentDaily.desc, fontSize = 20.sp); Text("${dailyChallengeManager.dailyProg} / ${dailyChallengeManager.currentDaily.target}", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color(0xFFE67E22)); if (!dailyChallengeManager.dailyClaimed && dailyChallengeManager.dailyProg >= dailyChallengeManager.currentDaily.target) GameButton("CLAIM 25 🪙", Color(0xFFF1C40F), { dailyChallengeManager.claimReward { rewardManager.addCoins(it) }; soundManager.play(soundManager.collect, sOn); if (expManager.addXp(80)) engine.state = GameState.LEVEL_UP }, textColor = Color.Black) } }
                        Spacer(modifier = Modifier.height(24.dp)); GameButton("BACK", Color.Gray, { engine.state = GameState.MENU })
                    }
                    
                    GameState.SHOP -> Column(Modifier.fillMaxSize().padding(24.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                        Text("SHOP", fontSize = 32.sp, fontWeight = FontWeight.Bold); Text("🪙 ${rewardManager.coins}", color = Color(0xFFF1C40F)); Spacer(Modifier.height(24.dp))
                        allSkins.forEach { s ->
                            val has = shopManager.purchasedSkins.contains(s.id); val isSel = shopManager.selectedSkinId == s.id
                            Button({ if (has) { shopManager.selectSkin(s.id) } else { shopManager.buySkin(s, rewardManager.coins) { rewardManager.coins = it; saveManager.setInt("coins", it) } } }, Modifier.fillMaxWidth(0.8f).padding(4.dp), colors = ButtonDefaults.buttonColors(containerColor = if (isSel) Color(0xFF2ECC71) else Color.White, contentColor = Color.Black)) { Text("${s.name} ${if (isSel) "(SELECTED)" else if (has) "(OWNED)" else "🪙" + s.price}") }
                        }
                        Spacer(modifier = Modifier.height(32.dp)); GameButton("BACK", Color.Gray, { engine.state = GameState.MENU })
                    }
                    
                    GameState.SETTINGS -> Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("SETTINGS", fontSize = 32.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) { Text("SFX"); Switch(sOn, { sOn = !sOn; saveManager.setBoolean("s_on", sOn) }) }
                        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) { Text("Music"); Switch(mOn, { mOn = !mOn; saveManager.setBoolean("m_on", mOn) }) }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Ambient Volume", fontWeight = FontWeight.Bold)
                        Slider(value = ambientSoundManager.volume, onValueChange = { ambientSoundManager.setAmbVolume(it) }, modifier = Modifier.fillMaxWidth(0.8f))

                        Spacer(modifier = Modifier.height(16.dp)); Text("Music Style", fontWeight = FontWeight.Bold)
                        LazyColumn(modifier = Modifier.height(180.dp).fillMaxWidth(0.8f)) { items(items = MusicStyle.entries) { s -> Row(modifier = Modifier.fillMaxWidth().clickable { mSt = s; saveManager.setString("m_st", s.name) }.padding(8.dp), verticalAlignment = Alignment.CenterVertically) { RadioButton(mSt == s, null); Text(s.displayName, modifier = Modifier.padding(start = 8.dp)) } } }
                        Spacer(modifier = Modifier.height(16.dp)); OutlinedButton({ 
                            saveManager.clear()
                            engine.reset()
                            rewardManager.reset()
                            shopManager.reset()
                            worldMapManager.reset()
                            foodAlbumManager.reset()
                            npcManager.reset()
                            ambientSoundManager.stop()
                            achievementManager.reset()
                            playerManager.reset()
                            statsManager.reset()
                            expManager.reset()
                            museumManager.reset()
                            galleryManager.reset()
                            dailyLoginManager.reset()
                            engine.state = GameState.SPLASH 
                        }, Modifier.fillMaxWidth(0.8f)) { Text("Reset Progress", color = Color.Red) }
                        Spacer(modifier = Modifier.weight(1f)); GameButton("BACK", Color.Gray, { engine.state = GameState.MENU })
                    }
                    
                    GameState.HIGH_SCORES -> Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) { Text("HIGH SCORE", fontSize = 32.sp); Text("${engine.highScore}", fontSize = 80.sp, fontWeight = FontWeight.Black); Spacer(Modifier.height(40.dp)); GameButton("BACK", Color.Gray, { engine.state = GameState.MENU }) }
                    
                    GameState.FOOD_ALBUM -> FoodAlbumScreen(foodAlbumManager, onBack = { engine.state = GameState.MENU })

                    GameState.LEVEL_UP -> LevelUpScreen(expManager) { engine.state = GameState.MENU }
                    
                    GameState.PROFILE -> ProfileScreen(playerManager, expManager, rewardManager, worldProgressManager, foodAlbumManager, achievementManager, statsManager) { engine.state = GameState.MENU }
                    
                    GameState.STATISTICS -> StatisticsScreen(statsManager) { engine.state = GameState.MENU }

                    GameState.GALLERY -> GalleryMenuScreen { engine.state = it }
                    
                    GameState.DAILY_LOGIN -> DailyLoginScreen(dailyLoginManager, rewardManager, shopManager, soundManager, sOn) { 
                        engine.state = if (saveManager.getBoolean("seen", false)) GameState.MENU else GameState.TUTORIAL 
                    }
                    
                    GameState.SEASONAL_EVENTS -> SeasonalEventsScreen(seasonManager, eventManager) { engine.state = GameState.MENU }

                    GameState.CHALLENGE_ARENA -> ChallengeArenaScreen(onBack = { engine.state = GameState.MENU }) { 
                        engine.setupBossBattle(it.id, rewardManager.maxLives)
                    }

                    GameState.BOSS_INTRO -> BossIntroScreen(engine.countryId) { engine.state = GameState.BOSS_BATTLE }
                    
                    GameState.BOSS_BATTLE -> BossBattleScreen(engine, onHome = { engine.state = GameState.MENU }) { 
                        engine.state = GameState.PAUSED 
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
                                engine.activeReward = rewardManager.generateReward(engine.currentCountry, shopManager.purchasedSkins, engine.isChallengeMode)
                                engine.activeReward?.let { rewardManager.claimReward(it, shopManager) }
                                worldProgressManager.completeMission(engine.countryId) // Unlock next country
                                statsManager.trackBoss()
                                statsManager.trackChest()
                                if (expManager.addXp(30)) engine.state = GameState.LEVEL_UP
                                else engine.state = GameState.TREASURE_CHEST
                            }, textColor = Color(0xFFE67E22))
                        }
                    }

                    GameState.WORLD_TRAVEL -> {
                        val from = CountryRepository.getCountry(previousCountryId) ?: CountryRepository.getFirstCountry()
                        val to = CountryRepository.getCountry(engine.countryId) ?: CountryRepository.getFirstCountry()
                        WorldTravelScreen(from, to, worldProgressManager.visitedStatus[to.id] ?: false) {
                            engine.setupLevel(to.id, rewardManager.maxLives, weatherManager)
                        }
                    }

                    GameState.COUNTDOWN -> Box(Modifier.fillMaxSize(), Alignment.Center) { Text(if (engine.countdown > 0) "${engine.countdown}" else "GO!", fontSize = 120.sp, fontWeight = FontWeight.Black) }
                    
                    GameState.PLAYING, GameState.PAUSED -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            PlayScreen(engine.currentScore, engine.lives, engine.pX, engine.countryId, shopManager.selectedSkinId, engine.items, "Lives: ${"❤️".repeat(engine.lives)}", engine.state == GameState.PAUSED, { 
                                showcaseManager.onManualInput()
                                engine.tX = (engine.tX + it).coerceIn(0.1f, 0.9f) 
                            }, { engine.state = if (engine.state == GameState.PLAYING) GameState.PAUSED else GameState.PLAYING }, { engine.state = GameState.MENU }, { w, h -> engine.sW = w; engine.sH = h })
                        }
                    }
                    
                    GameState.LEVEL_COMPLETE -> Box(modifier = Modifier.fillMaxSize().background(Color(0xFF2ECC71).copy(0.9f)), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("MISSION COMPLETE", fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Black)
                            Spacer(modifier = Modifier.height(48.dp))
                            GameButton("CLAIM REWARD", Color.White, {
                                // v0.43: Show Fact Card
                                currentDiscoveryCountry = CountryRepository.getCountry(engine.countryId)
                                showFactCard = true

                                engine.activeReward = rewardManager.generateReward(engine.currentCountry, shopManager.purchasedSkins, false)
                                engine.activeReward?.let { rewardManager.claimReward(it, shopManager) }
                                worldProgressManager.completeMission(engine.countryId) // Unlock next country
                                statsManager.trackChest()
                                if (expManager.addXp(30)) engine.state = GameState.LEVEL_UP
                                else engine.state = GameState.TREASURE_CHEST
                            }, textColor = Color.Black)
                        }
                    }
                    
                    GameState.TREASURE_CHEST -> TreasureChestScreen(engine.activeReward, soundManager, sOn, engine.isChallengeMode, onDone = { engine.state = GameState.MENU })
                    
                    GameState.GAME_OVER -> Column(Modifier.fillMaxSize().background(Color(0xFFE74C3C)), Arrangement.Center, Alignment.CenterHorizontally) { Text("GAME OVER", fontSize = 48.sp, color = Color.White, fontWeight = FontWeight.Black); Text("Score: ${engine.currentScore}", color = Color.White); Spacer(Modifier.height(48.dp)); GameButton("TRY AGAIN", Color.White, { engine.state = GameState.WORLD_MAP }, textColor = Color(0xFFE74C3C)) }

                    GameState.FEEDBACK -> FeedbackScreen(feedbackManager, onBack = { engine.state = GameState.MENU }, onHistory = { engine.state = GameState.FEEDBACK_HISTORY })
                    
                    GameState.FEEDBACK_HISTORY -> FeedbackHistoryScreen(feedbackManager, onBack = { engine.state = GameState.FEEDBACK })
                    
                    GameState.TV_TRAVEL -> TvTravelScreen(tvTravelManager, musicManager, ambientSoundManager, environmentManager, livingWorldManager, npcManager, weatherManager, onExit = { engine.state = GameState.MENU })

                    else -> {}
                }

                if (engine.playMode == PlayMode.LIVE_SHOWCASE && showcaseManager.isRunning) {
                    ShowcaseOverlay(showcaseManager, engine, mOn, weatherManager, { mOn = it; saveManager.setBoolean("m_on", it) })
                }

                // --- v0.43 DISCOVERY OVERLAYS ---
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

@Composable
fun ShowcaseOverlay(
    showcaseManager: LiveShowcaseManager,
    engine: GameEngine,
    musicEnabled: Boolean,
    weatherManager: WeatherManager,
    onMusicChanged: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Black.copy(0.7f)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "LIVE SHOWCASE",
                            color = Color(0xFFF1C40F),
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp
                        )
                        Text(
                            engine.currentCountry.displayName.uppercase(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(onClick = {
                            val playable = CountryRepository.getPlayableCountries()
                            val index = playable.indexOfFirst { it.id == engine.countryId }
                            val prev = if (index <= 0) playable.last() else playable[index - 1]
                            engine.countryId = prev.id
                            engine.state = GameState.WORLD_TRAVEL
                        }) {
                            Text("⏮️", fontSize = 20.sp)
                        }

                        IconButton(onClick = {
                            if (showcaseManager.isPaused) showcaseManager.resume()
                            else showcaseManager.pause()
                        }) {
                            Text(if (showcaseManager.isPaused) "▶️" else "⏸️", fontSize = 20.sp)
                        }

                        IconButton(onClick = {
                            val playable = CountryRepository.getPlayableCountries()
                            val index = playable.indexOfFirst { it.id == engine.countryId }
                            val next = if (index == -1 || index == playable.size - 1) playable.first() else playable[index + 1]
                            engine.countryId = next.id
                            engine.state = GameState.WORLD_TRAVEL
                        }) {
                            Text("⏭️", fontSize = 20.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GameButton(
                        txt = if (musicEnabled) "MUSIC: ON" else "MUSIC: OFF",
                        col = if (musicEnabled) Color(0xFF2ECC71) else Color.Gray,
                        onClick = { onMusicChanged(!musicEnabled) },
                        modifier = Modifier.weight(1f),
                        textColor = Color.White
                    )

                    GameButton(
                        txt = "STOP AUTO",
                        col = Color(0xFFE74C3C),
                        onClick = {
                            try {
                                showcaseManager.stop()
                                engine.playMode = PlayMode.NORMAL
                                engine.items.clear()
                                engine.state = GameState.MENU
                                engine.countryId = "germany"
                                engine.saveManager.isWriteEnabled = true
                            } catch (e: Exception) {
                                engine.state = GameState.MENU
                            }
                        },
                        modifier = Modifier.weight(1f),
                        textColor = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CountryIntroCard(country: CountryData, onDismiss: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(4000)
        onDismiss()
    }
    Box(Modifier.fillMaxSize().background(Color.Black.copy(0.7f)).clickable { onDismiss() }, Alignment.Center) {
        Card(modifier = Modifier.fillMaxWidth(0.85f), colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(24.dp), elevation = CardDefaults.cardElevation(12.dp)) {
            Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(country.flagEmoji, fontSize = 70.sp)
                Text(country.displayName.uppercase(), fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.Black)
                Text(country.continent.uppercase(), fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                
                Spacer(Modifier.height(16.dp))
                DiscoveryInfoRow("Capital", country.capital)
                DiscoveryInfoRow("Language", country.language)
                DiscoveryInfoRow("Population", country.population)
                
                Spacer(Modifier.height(20.dp))
                Text("FAMOUS FOODS", fontSize = 14.sp, fontWeight = FontWeight.Black, color = Color(0xFFE67E22))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    country.foods.take(3).forEach { Text(it.emoji, fontSize = 32.sp) }
                }

                Spacer(Modifier.height(24.dp))
                Text(country.welcomeMessage, fontSize = 18.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, textAlign = TextAlign.Center, color = Color(0xFF2E7D32))
            }
        }
    }
}

@Composable
fun DiscoveryInfoRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontWeight = FontWeight.Bold, color = Color.DarkGray, fontSize = 14.sp)
        Text(value, color = Color.Black, fontSize = 14.sp)
    }
}

@Composable
fun DidYouKnowCard(country: CountryData, onDismiss: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.Black.copy(0.7f)).clickable { onDismiss() }, Alignment.Center) {
        Card(modifier = Modifier.fillMaxWidth(0.8f), colors = CardDefaults.cardColors(containerColor = Color(0xFFF1C40F)), shape = RoundedCornerShape(24.dp)) {
            Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("💡 DID YOU KNOW?", fontWeight = FontWeight.Black, fontSize = 22.sp, color = Color.Black)
                Spacer(Modifier.height(16.dp))
                Text(country.fact, fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.Black, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(24.dp))
                GameButton("AWESOME!", Color.Black, onDismiss)
            }
        }
    }
}

@Composable
fun PassportScreen(progress: WorldProgressManager, album: FoodAlbumManager, stats: StatisticsManager, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF2C3E50)).padding(24.dp).safeDrawingPadding()) {
        Text("CHEF PASSPORT", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Text(progress.getExplorerTitle(), color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        
        LazyColumn(Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(CountryRepository.getPlayableCountries()) { country ->
                val isComp = progress.isCompleted(country.id)
                val cStats = progress.getCountryCompletionStats(country, album, stats)
                
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.1f))) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(80.dp).background(if (isComp) Color(0xFF27AE60) else Color.Gray, RoundedCornerShape(40.dp)), contentAlignment = Alignment.Center) {
                            if (isComp) Text("STAMPED", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Black)
                            else Box(Modifier.alpha(0.3f)) { Text(country.flagEmoji, fontSize = 40.sp) }
                        }
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Text(country.displayName, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp)
                            Text("Completion: ${cStats.missionPercent}% | Foods: ${cStats.foodPercent}%", fontSize = 12.sp, color = Color.LightGray)
                            Row {
                                repeat(3) { i -> Text(if (i < cStats.stars) "⭐" else "☆", color = Color(0xFFF1C40F)) }
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun PlayScreen(s: Int, l: Int, pX: Float, cId: String, skId: String, items: List<GameItem>, sub: String, pa: Boolean, onM: (Float) -> Unit, onP: () -> Unit, onR: () -> Unit, onI: (Float, Float) -> Unit) {
    val ctx = LocalContext.current
    val itemPaint = remember { Paint().apply { textSize = 80f; textAlign = Paint.Align.CENTER } }
    val skin = allSkins.find { it.id == skId } ?: allSkins[0]
    val dId = remember(skin.drawable) { getDrawableId(skin.drawable, ctx) }
    val cBM = remember(dId) { 
        try { if (dId != 0) ImageBitmap.imageResource(ctx.resources, dId) else null }
        catch (e: Exception) { android.util.Log.e("WorldFood", "Failed to load skin bitmap", e); null }
    }
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val w = maxWidth.value; val h = maxHeight.value
        LaunchedEffect(w, h) { onI(w, h) }
        Column {
            Row(Modifier.fillMaxWidth().background(Color.White.copy(0.7f)).padding(16.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Column { Text("Score: $s", fontWeight = FontWeight.Bold); Text(sub, fontSize = 12.sp) }
                Row { IconButton(onR) { Text("🏠", fontSize = 24.sp) }; IconButton(onP) { Text(if (pa) "▶️" else "⏸️", fontSize = 24.sp) } }
            }
            Box(Modifier.weight(1f).fillMaxWidth()) {
                Canvas(Modifier.fillMaxSize()) {
                    if (cBM != null) drawImage(cBM, dstOffset = IntOffset((pX * size.width - 75).toInt(), (size.height - 230).toInt()), dstSize = IntSize(150, 150))
                    else drawCircle(Color.Gray, 60f, Offset(pX * size.width, size.height - 150f))
                    items.forEach { i -> drawContext.canvas.nativeCanvas.drawText(i.emoji, i.x, i.y, itemPaint) }
                }
                if (pa) Box(Modifier.fillMaxSize().background(Color.Black.copy(0.4f)), Alignment.Center) { Text("PAUSED", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Black) }
            }
            Row(Modifier.fillMaxWidth().padding(bottom = 40.dp, top = 20.dp), Arrangement.SpaceEvenly) {
                Button({ onM(-0.15f) }, Modifier.size(120.dp, 70.dp), colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71)), shape = RoundedCornerShape(16.dp)) { Text("LEFT") }
                Button({ onM(0.15f) }, Modifier.size(120.dp, 70.dp), colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71)), shape = RoundedCornerShape(16.dp)) { Text("RIGHT") }
            }
        }
    }
}

@Composable
fun BackgroundStyle(cId: String?, state: GameState) {
    val ctx = LocalContext.current
    val id = remember(cId, state) { 
        if (state == GameState.MENU) getDrawableId("background_menu_cinematic", ctx)
        else cId?.let { getDrawableId("background_${it.lowercase()}", ctx) } ?: 0 
    }
    val bm = remember(id) { 
        try { if (id != 0) ImageBitmap.imageResource(ctx.resources, id) else null }
        catch (e: Exception) { android.util.Log.e("WorldFood", "Failed to load background bitmap", e); null }
    }
    Canvas(Modifier.fillMaxSize()) {
        if (bm != null) drawImage(bm, dstSize = IntSize(size.width.toInt(), size.height.toInt())) 
        else drawRect(Brush.verticalGradient(listOf(Color(0xFF1A237E), Color(0xFF4DB6AC))))
    }
}

@Composable
fun EnvironmentLayer(manager: EnvironmentManager) {
    val paint = remember { Paint().apply { textAlign = Paint.Align.CENTER } }
    Canvas(Modifier.fillMaxSize()) {
        try {
            for (i in manager.skyObjects.indices) {
                val obj = manager.skyObjects[i]
                if (!obj.x.isFinite() || !obj.y.isFinite()) continue
                paint.textSize = 80f * obj.scale
                paint.alpha = (obj.alpha * 255).toInt().coerceIn(0, 255)
                drawContext.canvas.nativeCanvas.drawText(obj.emoji, obj.x, obj.y, paint)
            }
        } catch (e: Exception) { android.util.Log.e("WorldFood", "EnvironmentLayer draw failed", e) }
    }
}

@Composable
fun WildlifeLayer(manager: LivingWorldManager) {
    val animalPaint = remember { Paint().apply { textAlign = Paint.Align.CENTER } }
    Canvas(Modifier.fillMaxSize()) {
        val h = size.height
        try {
            for (i in manager.animals.indices) {
                val animal = manager.animals[i]
                if (!animal.x.isFinite() || !animal.y.isFinite()) continue
                animalPaint.textSize = 90f * animal.scale
                withTransform({ if (animal.dir > 0) scale(-1f, 1f, Offset(animal.x, h * animal.y)) }) {
                    drawContext.canvas.nativeCanvas.drawText(animal.emoji, animal.x, h * animal.y, animalPaint)
                }
            }
            for (i in manager.particles.indices) {
                val p = manager.particles[i]
                if (!p.x.isFinite() || !p.y.isFinite()) continue
                val color = when(p.colorType) { 1 -> Color(0xFFFFC1E3); 2 -> Color(0xFFFFF59D); 3 -> Color.White.copy(0.3f); 4 -> Color(0xFF81C4FA); 5 -> Color(0xFFFFEB3B); 6 -> Color(0xFFA5D6A7); 7 -> Color(0xFFE1F5FE); else -> Color(0xFFFFF9C4) }
                if (p.colorType == 2 || p.colorType == 4 || p.colorType == 7) drawCircle(color.copy(alpha = (p.alpha * 0.4f).coerceIn(0f, 1f)), p.size * 4f, Offset(p.x, p.y))
                drawCircle(color.copy(alpha = p.alpha.coerceIn(0f, 1f)), p.size, Offset(p.x, p.y))
            }
        } catch (e: Exception) { android.util.Log.e("WorldFood", "WildlifeLayer draw failed", e) }
    }
}

@Composable
fun NpcLayer(manager: NpcManager) {
    val paint = remember { Paint().apply { textSize = 80f; textAlign = Paint.Align.CENTER } }
    Canvas(Modifier.fillMaxSize()) {
        try {
            for (i in manager.activeNpcs.indices) {
                val npc = manager.activeNpcs[i]
                if (!npc.x.isFinite() || !npc.y.isFinite()) continue
                drawContext.canvas.nativeCanvas.drawText(npc.emoji, npc.x, size.height * npc.y, paint)
            }
        } catch (e: Exception) { android.util.Log.e("WorldFood", "NpcLayer draw failed", e) }
    }
}

@Composable
fun WeatherOverlay(weather: WeatherType, country: CountryData, season: Season = Season.NONE, festivalActive: Boolean = false) {
    val inf = rememberInfiniteTransition(label = "weather")
    val pOff by inf.animateFloat(0f, 1200f, infiniteRepeatable(tween(8000, easing = LinearEasing)), label = "p")
    val tTime by inf.animateFloat(0f, 100f, infiniteRepeatable(tween(200000, easing = LinearEasing)), label = "t")
    val paint = remember { Paint().apply { textSize = 30f; alpha = 100; textAlign = Paint.Align.CENTER } }

    Canvas(Modifier.fillMaxSize()) {
        val w = size.width; val h = size.height
        when (weather) {
            WeatherType.MORNING -> { for (i in 0..8) { val rx = (i * (w / 8f) + pOff * 0.05f) % w; drawLine(Color(0xFFE3F2FD).copy(0.08f), Offset(rx, 0f), Offset(rx + 200f, h), strokeWidth = 100f) } }
            WeatherType.RAIN -> { for (i in 0..100) { val x = (i * 73f + pOff * 0.3f) % w; val y = (i * 109f + pOff * 3.0f) % h; drawLine(Color(0xFF81D4FA).copy(0.45f), Offset(x, y), Offset(x - 3f, y + 40f), strokeWidth = 2.5f) } }
            WeatherType.SNOW -> { for (i in 0..40) { val x = (i * 97f + pOff * 0.1f + sin(tTime + i) * 50f) % w; val y = (i * 153f + pOff * 0.6f) % h; drawCircle(Color.White.copy(0.8f), if(i % 3 == 0) 6f else 4f, Offset(x, y)) } }
            else -> {}
        }
    }
}

@Composable
fun IdleVisuals(weather: WeatherType = WeatherType.SUNNY, countryId: String? = null) {
    val inf = rememberInfiniteTransition(label = "idle")
    val time by inf.animateFloat(0f, 100f, infiniteRepeatable(tween(200000, easing = LinearEasing)), label = "t")
    val paint = remember { Paint().apply { textAlign = Paint.Align.CENTER } }

    Canvas(Modifier.fillMaxSize()) {
        val w = size.width; val h = size.height
        val waterY = h - 220f
        if (countryId == "italy" || countryId == "japan" || countryId == "sudan" || countryId == "brazil") {
            drawRect(Brush.verticalGradient(listOf(Color(0xFF4FC3F7).copy(0.2f), Color(0xFF01579B).copy(0.4f))), topLeft = Offset(0f, waterY), size = androidx.compose.ui.geometry.Size(w, 220f))
            for (i in 0..6) { val wy = waterY + i * 35f; val off = sin(time * 3.5f + i) * 12f; drawLine(Color.White.copy(0.18f), Offset(0f, wy + off), Offset(w, wy + off), strokeWidth = 2.5f) }
        }
        val landY = h - 200f
        paint.textSize = 140f; paint.alpha = 210
        when (countryId) {
            "italy" -> { drawContext.canvas.nativeCanvas.drawText("🏟️", w * 0.12f, landY, paint); drawContext.canvas.nativeCanvas.drawText("🛶", w * 0.78f, waterY + 45f, paint.apply { textSize = 130f }) }
            "germany" -> { drawContext.canvas.nativeCanvas.drawText("🏰", w * 0.25f, landY - 60f, paint.apply { textSize = 170f }); drawContext.canvas.nativeCanvas.drawText("🏘️", w * 0.81f, landY, paint.apply { textSize = 120f }) }
            "japan" -> { drawContext.canvas.nativeCanvas.drawText("🏯", w * 0.20f, landY - 50f, paint.apply { textSize = 160f }); drawContext.canvas.nativeCanvas.drawText("⛩️", w * 0.82f, landY + 10f, paint.apply { textSize = 140f }) }
            "sudan" -> { drawContext.canvas.nativeCanvas.drawText("🔺", w * 0.30f, landY, paint.apply { textSize = 160f }); drawContext.canvas.nativeCanvas.drawText("🛖", w * 0.80f, landY, paint.apply { textSize = 130f }) }
        }
    }
}

@Composable
fun XpBar(manager: ExperienceManager) {
    val progress by animateFloatAsState(targetValue = manager.getXpProgress(), label = "xpAnim")
    Column(modifier = Modifier.fillMaxWidth(0.85f), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(manager.lastUnlockedTitle, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Lvl ${manager.currentLevel}", color = Color(0xFFF1C40F), fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)).background(Color.White.copy(0.2f))) {
            Box(modifier = Modifier.fillMaxWidth(progress).fillMaxHeight().background(Brush.horizontalGradient(listOf(Color(0xFFF1C40F), Color(0xFFE67E22)))))
        }
    }
}

@Composable
fun LevelUpScreen(manager: ExperienceManager, onContinue: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Brush.radialGradient(listOf(Color(0xFF1E3C72), Color(0xFF2A5298)))), Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("✨ RANK UP! ✨", color = Color(0xFFF1C40F), fontSize = 54.sp, fontWeight = FontWeight.Black)
            Text(manager.lastUnlockedTitle.uppercase(), color = Color(0xFF2ECC71), fontSize = 42.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(60.dp))
            GameButton("CONTINUE JOURNEY", Color(0xFF2ECC71), onContinue)
        }
    }
}

@Composable
fun ProfileScreen(player: PlayerManager, exp: ExperienceManager, reward: RewardManager, world: WorldProgressManager, album: FoodAlbumManager, ach: AchievementManager, stats: StatisticsManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF121212)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("CHEF PROFILE", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f)), shape = RoundedCornerShape(24.dp)) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(modifier = Modifier.size(100.dp), shape = RoundedCornerShape(50.dp), color = Color.White.copy(0.1f)) {
                    Box(contentAlignment = Alignment.Center) { Text(player.avatar, fontSize = 60.sp) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(player.name, fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                XpBar(exp)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item { ProfileStatRow("Current Rank", exp.lastUnlockedTitle, Color(0xFFF1C40F)) }
            item { ProfileStatRow("Countries Mastered", "${world.completionStatus.count { it.value }} / ${CountryRepository.getPlayableCountries().size}", Color(0xFF2ECC71)) }
            item { ProfileStatRow("Discoveries", "${album.discoveredFoods.size} / ${allFoods.size}", Color(0xFF3498DB)) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        GameButton("BACK TO KITCHEN", Color.Gray, onBack)
    }
}

@Composable
fun ProfileStatRow(label: String, value: String, valueColor: Color) {
    Row(modifier = Modifier.fillMaxWidth().background(Color.White.copy(0.05f), RoundedCornerShape(12.dp)).padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color.Gray, fontWeight = FontWeight.Medium); Text(value, fontWeight = FontWeight.Bold, color = valueColor, fontSize = 18.sp)
    }
}

@Composable
fun StatisticsScreen(manager: StatisticsManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0F0F0F)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("LIFETIME STATS", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { StatItem("Foods Harvested", manager.foodsCollected.toString(), Color(0xFF2ECC71)) }
            item { StatItem("Coins Collected", manager.coinsCollected.toString(), Color(0xFFF1C40F)) }
            item { StatItem("Bosses Slain", manager.bossesDefeated.toString(), Color(0xFFE74C3C)) }
        }
        Spacer(modifier = Modifier.height(24.dp))
        GameButton("CLOSE RECORDS", Color.Gray, onBack)
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f)), shape = RoundedCornerShape(16.dp)) {
        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(label, color = Color.LightGray, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(value, color = color, fontWeight = FontWeight.Black, fontSize = 20.sp)
        }
    }
}

@Composable
fun GalleryMenuScreen(onNavigate: (GameState) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF1A1A2E)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("WORLD GALLERY", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK TO MENU", Color.Gray, { onNavigate(GameState.MENU) })
    }
}

@Composable
fun WorldMapScreen(search: CountrySearchManager, progress: WorldProgressManager, homeland: PlayerHomelandManager, onBack: () -> Unit, onSelect: (CountryData) -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF0D1B2A)).padding(16.dp)) {
        Text("SELECT DESTINATION", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
        LazyColumn(Modifier.weight(1f).fillMaxWidth()) {
            items(CountryRepository.allCountries) { c ->
                val isUnlocked = progress.isUnlocked(c) || progress.isCompleted(c.id)
                val isComingSoon = c.status == ContentStatus.COMING_SOON
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .alpha(if (isUnlocked && !isComingSoon) 1f else 0.5f)
                        .clickable(enabled = isUnlocked && !isComingSoon) { onSelect(c) },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.1f))
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(if (isUnlocked && !isComingSoon) c.flagEmoji else "🔒", fontSize = 28.sp)
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(c.displayName, fontWeight = FontWeight.Bold, color = if (isUnlocked && !isComingSoon) Color.White else Color.Gray)
                            if (isComingSoon) {
                                Text("Coming Soon", fontSize = 12.sp, color = Color(0xFFF1C40F))
                            } else if (!isUnlocked) {
                                Text("Complete the previous country", fontSize = 12.sp, color = Color.LightGray)
                            } else if (progress.isCompleted(c.id)) {
                                Text("Mastered ⭐", fontSize = 12.sp, color = Color(0xFF2ECC71))
                            }
                        }
                    }
                }
            }
        }
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun GameButton(txt: String, col: Color, onClick: () -> Unit, modifier: Modifier = Modifier, textColor: Color = Color.White) {
    Button(onClick = onClick, modifier = modifier.height(58.dp).widthIn(min = 200.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = col), elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)) { 
        Text(txt, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = textColor) 
    }
}

@Composable
fun FoodAlbumScreen(manager: FoodAlbumManager, onBack: () -> Unit) {
    val sdf = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    
    Column(Modifier.fillMaxSize().background(Color(0xFF1E1E1E)).padding(16.dp).safeDrawingPadding()) {
        Text("FOOD ALBUM", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
        Text("${manager.discoveredFoods.size} / ${allFoods.size} DISCOVERED", color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val countries = CountryRepository.allCountries.filter { it.status != ContentStatus.COMING_SOON }
            items(countries) { country ->
                Column(Modifier.fillMaxWidth()) {
                    Text(country.displayName.uppercase(), color = Color.Gray, fontWeight = FontWeight.Black, fontSize = 12.sp)
                    Spacer(Modifier.height(8.dp))
                    
                    val countryFoods = allFoods.filter { it.countryId == country.id }
                    countryFoods.forEach { food ->
                        val isDiscovered = manager.discoveredFoods.contains(food.id)
                        val date = manager.discoveryDates[food.id]
                        
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = if (isDiscovered) Color.White.copy(0.1f) else Color.White.copy(0.02f))
                        ) {
                            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(if (isDiscovered) food.emoji else "🔒", fontSize = 32.sp)
                                Spacer(Modifier.width(16.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        text = if (isDiscovered) food.name else "???",
                                        fontWeight = FontWeight.Bold,
                                        color = if (isDiscovered) Color.White else Color.Gray
                                    )
                                    if (isDiscovered) {
                                        Text(food.desc, fontSize = 12.sp, color = Color.LightGray)
                                        Text(
                                            "Discovered: ${date?.let { sdf.format(Date(it)) } ?: "Unknown"}",
                                            fontSize = 10.sp,
                                            color = Color(0xFF2ECC71)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(color = Color.White.copy(0.1f))
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun DailyLoginScreen(manager: DailyLoginManager, rewards: RewardManager, shop: ShopManager, sounds: SoundManager, sOn: Boolean, onDone: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("DAILY REWARDS", fontSize = 36.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("CONTINUE", Color.White, onDone, textColor = Color.Black)
    }
}

@Composable
fun SeasonalEventsScreen(season: SeasonManager, events: EventManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("SEASONAL EVENTS", fontSize = 32.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun BossIntroScreen(countryId: String, onStart: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("⚠️ BOSS ALERT ⚠️", color = Color.Red, fontSize = 48.sp, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(60.dp))
            GameButton("ENGAGE BATTLE!", Color.Red, onStart)
        }
    }
}

@Composable
fun BossBattleScreen(engine: GameEngine, onHome: () -> Unit, onPause: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color.Black.copy(0.4f))) {
        Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.SpaceBetween) {
            IconButton(onHome) { Text("🏠", fontSize = 24.sp) }
            IconButton(onPause) { Text("⏸️", fontSize = 24.sp) }
        }
        Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text("BOSS BATTLE", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun WorldTravelScreen(from: CountryData, to: CountryData, visited: Boolean, onDone: () -> Unit) {
    LaunchedEffect(Unit) { delay(2500); onDone() }
    Box(Modifier.fillMaxSize().background(Color(0xFF0D1B2A)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(to.flagEmoji, fontSize = 80.sp)
            Spacer(Modifier.height(16.dp))
            Text("TRAVELING TO ${to.displayName.uppercase()}...", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TreasureChestScreen(reward: Reward?, sounds: SoundManager, on: Boolean, isChallenge: Boolean = false, onDone: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.Black.copy(0.85f)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("TREASURE FOUND!", fontSize = 36.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Black)
            Spacer(Modifier.height(80.dp))
            Text("🎁", fontSize = 100.sp)
            Spacer(Modifier.height(80.dp))
            GameButton("COLLECT", Color(0xFF2ECC71), onDone) 
        }
    }
}

@Composable
fun FeedbackScreen(manager: FeedbackManager, onBack: () -> Unit, onHistory: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF121212)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("FEEDBACK", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun FeedbackHistoryScreen(manager: FeedbackManager, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF121212)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("HISTORY", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun ChallengeArenaScreen(onBack: () -> Unit, onStart: (CountryData) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF2C3E50)).padding(24.dp)) {
        Text("CHALLENGE ARENA", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
        LazyColumn(Modifier.weight(1f)) {
            items(CountryRepository.getPlayableCountries()) { country ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onStart(country) }) {
                    Row(Modifier.padding(16.dp)) { Text(country.flagEmoji); Spacer(Modifier.width(16.dp)); Text(country.displayName) }
                }
            }
        }
        GameButton("BACK", Color.Gray, onBack)
    }
}
