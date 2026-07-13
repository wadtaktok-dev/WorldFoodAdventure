package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.*
import kotlin.math.abs
import kotlin.random.Random

class GameEngine(
    val soundManager: SoundManager,
    val saveManager: SaveManager,
    val achievementManager: AchievementManager,
    val dailyChallengeManager: DailyChallengeManager,
    val rewardManager: RewardManager,
    val foodAlbumManager: FoodAlbumManager,
    val statsManager: StatisticsManager,
    val expManager: ExperienceManager,
    val museumManager: MuseumManager,
    val galleryManager: GalleryManager,
    val eventManager: EventManager,
    val worldProgressManager: WorldProgressManager
) {
    var previousCountryId by mutableStateOf("germany")
    var state by mutableStateOf(GameState.SPLASH)
    var countryId by mutableStateOf("germany")
    val currentCountry: CountryData
        get() = CountryRepository.getCountry(countryId) ?: CountryRepository.getFirstCountry()
    
    var weather by mutableStateOf(WeatherType.SUNNY)
    var activeReward by mutableStateOf<Reward?>(null)
    
    var bossHP by mutableIntStateOf(0)
    var bossMaxHP by mutableIntStateOf(0)
    var bossX by mutableFloatStateOf(0.5f)
    var bossDir by mutableIntStateOf(1)
    var bossTime by mutableFloatStateOf(0f)
    val bossAttacks = mutableStateListOf<BossAttack>()

    var currentScore by mutableIntStateOf(0)
    var highScore by mutableIntStateOf(saveManager.getInt("high", 0))
    var lives by mutableIntStateOf(3)

    var mFoods by mutableIntStateOf(0)
    var mBombs by mutableIntStateOf(0)
    var mSpecial by mutableIntStateOf(0)
    var mCoins by mutableIntStateOf(0)

    var pX by mutableFloatStateOf(0.5f)
    var tX by mutableFloatStateOf(0.5f)
    var countdown by mutableIntStateOf(3)
    val items = mutableStateListOf<GameItem>()
    var sW by mutableFloatStateOf(0f)
    var sH by mutableFloatStateOf(0f)
    var isChallengeMode by mutableStateOf(false)
    var playMode by mutableStateOf(PlayMode.NORMAL)

    fun setupLevel(selectedCountryId: String, maxLives: Int, weatherManager: WeatherManager) {
        countryId = selectedCountryId
        val country = currentCountry
        if (playMode == PlayMode.NORMAL) {
            isChallengeMode = false
        }
        weather = weatherManager.pickWeatherForCountry(country)
        currentScore = 0
        lives = maxLives
        mFoods = 0
        mBombs = 0
        mSpecial = 0
        mCoins = 0
        pX = 0.5f
        tX = 0.5f
        items.clear()
        bossAttacks.clear()
        state = GameState.COUNTDOWN
    }

    fun reset() {
        state = GameState.SPLASH
        isChallengeMode = false
        currentScore = 0
        highScore = 0
        lives = 3
        mFoods = 0
        mBombs = 0
        mSpecial = 0
        mCoins = 0
        pX = 0.5f
        tX = 0.5f
        items.clear()
        bossAttacks.clear()
        countryId = "germany"
    }

    fun update(dt: Float, sOn: Boolean, showcaseTargetX: Float?) {
        if (sW <= 0) return

        if (showcaseTargetX != null && state == GameState.PLAYING) {
            tX = showcaseTargetX
        }

        if (abs(pX - tX) > 0.001f) {
            val move = 2.4f * dt
            pX = if (abs(pX - tX) < move) tX else if (pX < tX) pX + move else pX - move
        }

        if (state == GameState.BOSS_BATTLE) {
            updateBoss(dt, sOn)
            return
        }

        val px = pX * sW
        
        for (i in items.indices.reversed()) {
            val iItem = items[i]
            val ny = iItem.y + (600f * (1f + currentScore / 150f) * dt)
            
            if (abs(iItem.x - px) < 60 && abs(ny - (sH - 200)) < 60) {
                handleCollision(iItem, sOn)
                items.removeAt(i)
            } else if (ny <= sH + 100) {
                items[i] = iItem.copy(y = ny)
            } else {
                if (iItem.isObstacle) mBombs++
                items.removeAt(i)
            }
        }

        if (Random.nextInt(100) < 5) {
            val countryFoods = currentCountry.foods
            val r = Random.nextInt(100)
            val newItem = when {
                r < 75 -> {
                    if (countryFoods.isNotEmpty()) {
                        val food = countryFoods.random()
                        GameItem(Random.nextFloat() * sW, -100f, food.emoji, false, foodId = food.id)
                    } else {
                        GameItem(Random.nextFloat() * sW, -100f, "🍎", false)
                    }
                }
                r < 92 -> GameItem(Random.nextFloat() * sW, -100f, "💣", true)
                else -> GameItem(Random.nextFloat() * sW, -100f, "🪙", false)
            }
            items.add(newItem)
        }
    }

    private fun updateBoss(dt: Float, sOn: Boolean) {
        bossTime += dt
        // Boss Movement
        bossX += 0.5f * dt * bossDir
        if (bossX > 0.85f) { bossX = 0.85f; bossDir = -1 }
        if (bossX < 0.15f) { bossX = 0.15f; bossDir = 1 }

        // Boss Attack Spawn
        if (Random.nextInt(100) < 3) {
            bossAttacks.add(BossAttack(bossX * sW, 250f, if (Random.nextBoolean()) "💥" else "🔥"))
        }

        // Projectiles movement & collision
        val px = pX * sW
        for (i in bossAttacks.indices.reversed()) {
            val a = bossAttacks[i]
            val ny = a.y + 700f * dt
            if (abs(a.x - px) < 70 && abs(ny - (sH - 200)) < 70) {
                lives--
                statsManager.trackBomb()
                soundManager.play(soundManager.hit, sOn)
                if (lives <= 0) {
                    statsManager.trackDeath()
                    soundManager.play(soundManager.lose, sOn)
                    state = GameState.GAME_OVER
                }
                bossAttacks.removeAt(i)
            } else if (ny < sH + 100) {
                bossAttacks[i] = a.copy(y = ny)
            } else {
                bossAttacks.removeAt(i)
            }
        }

        // Player attacks boss (auto-fire or simplified catch mechanic for v0.21)
        // For simplicity: Catching "Attack Items" spawns automatically
        if (Random.nextInt(100) < 7) {
            items.add(GameItem(Random.nextFloat() * sW, -100f, "⚡", false))
        }
        
        for (i in items.indices.reversed()) {
            val iItem = items[i]
            val ny = iItem.y + 600f * dt
            if (abs(iItem.x - px) < 60 && abs(ny - (sH - 200)) < 60) {
                if (iItem.emoji == "⚡") {
                    bossHP--
                    if (bossHP <= 0) {
                        statsManager.trackBoss()
                        museumManager.trackBossDefeat(currentCountry.id, bossTime)
                        galleryManager.unlockMemory("first_boss")
                        if (expManager.addXp(200)) state = GameState.LEVEL_UP 
                        else state = GameState.BOSS_DEFEATED
                        soundManager.play(soundManager.win, sOn)
                        achievementManager.checkProgress(this, rewardManager, foodAlbumManager, worldProgressManager, sOn)
                    } else {
                        soundManager.play(soundManager.collect, sOn)
                    }
                }
                items.removeAt(i)
            } else if (ny < sH + 100) {
                items[i] = iItem.copy(y = ny)
            } else {
                items.removeAt(i)
            }
        }
    }

    private fun handleCollision(i: GameItem, sOn: Boolean) {
        if (i.isObstacle) {
            lives--
            statsManager.trackBomb()
            if (lives <= 0) {
                statsManager.trackDeath()
                soundManager.play(soundManager.lose, sOn)
                state = GameState.GAME_OVER
            } else {
                soundManager.play(soundManager.hit, sOn)
            }
        } else {
            when (i.emoji) {
                "🪙" -> {
                    val multiplier = 1 // Placeholder for now
                    currentScore += 5 * multiplier
                    if (playMode == PlayMode.NORMAL) {
                        rewardManager.addCoins(1 * multiplier)
                        mCoins += multiplier
                        statsManager.trackCoin()
                        expManager.addXp(2 * multiplier)
                        if (rewardManager.coins >= 100) achievementManager.unlockAch("coin_collector", sOn)
                    }
                    soundManager.play(soundManager.collect, sOn)
                }
                "❤️" -> {
                    lives = (lives + 1).coerceAtMost(rewardManager.maxLives)
                    soundManager.play(soundManager.collect, sOn)
                }
                else -> {
                    val multiplier = if (eventManager.isCountryEventActive(countryId)) 2 else 1
                    currentScore += 10 * multiplier
                    mFoods++
                    if (playMode == PlayMode.NORMAL) {
                        statsManager.trackFood()
                        i.foodId?.let { museumManager.trackFoodDiscovery(it) }
                        if (expManager.addXp(5 * multiplier)) {
                            state = GameState.LEVEL_UP
                        }
                        if (expManager.currentLevel >= 10) galleryManager.unlockMemory("level_10")
                        if (dailyChallengeManager.currentDaily.id == "f") dailyChallengeManager.incrementProgress()
                        achievementManager.unlockAch("first_food", sOn)
                    }
                    
                    if (i.emoji == "🍣") mSpecial++
                    i.foodId?.let { fId ->
                        if (foodAlbumManager.discoverFood(fId)) {
                            val countryFoods = allFoods.filter { it.countryId == countryId }
                            val discoveredInCountry = countryFoods.count { foodAlbumManager.discoveredFoods.contains(it.id) }
                            if (discoveredInCountry >= countryFoods.size && playMode == PlayMode.NORMAL) {
                                galleryManager.unlockMemory("all_foods")
                            }

                            if (foodAlbumManager.discoveredFoods.size >= allFoods.size && playMode == PlayMode.NORMAL) {
                                achievementManager.unlockAch("master_chef", sOn)
                            }
                        }
                    }
                    soundManager.play(soundManager.collect, sOn)
                }
            }

            checkWinCondition(sOn)
            
            rewardManager.addPoints(10)
            if (currentScore > highScore) {
                highScore = currentScore
                saveManager.setInt("high", highScore)
            }
        }
    }

    fun setupBossBattle(selectedCountryId: String, maxLives: Int) {
        countryId = selectedCountryId
        isChallengeMode = true
        lives = maxLives
        currentScore = 0
        val boss = allBossesById[countryId]
        if (boss != null) {
            bossHP = boss.health
            bossMaxHP = boss.health
            bossAttacks.clear()
            bossTime = 0f
            state = GameState.BOSS_INTRO
        }
    }

    fun stopShowcase(showcaseManager: LiveShowcaseManager) {
        try {
            showcaseManager.stop()
            playMode = PlayMode.NORMAL
            items.clear()
            state = GameState.MENU
            countryId = "germany"
            saveManager.isWriteEnabled = true
        } catch (e: Exception) {
            state = GameState.MENU
        }
    }

    fun stopTvMode(tvTravelManager: TvTravelManager) {
        tvTravelManager.stop()
        state = GameState.MENU
        saveManager.isWriteEnabled = true
    }

    fun claimRewardSafely(shopManager: ShopManager, isBoss: Boolean = false) {
        val reward = rewardManager.generateReward(currentCountry, shopManager.purchasedSkins, isChallengeMode)
        activeReward = reward
        rewardManager.claimReward(reward, shopManager)
        worldProgressManager.completeMission(countryId)
        if (isBoss) statsManager.trackBoss()
        statsManager.trackChest()
        if (expManager.addXp(30)) state = GameState.LEVEL_UP
        else state = GameState.TREASURE_CHEST
    }

    fun claimDailyRewardSafely() {
        dailyChallengeManager.claimReward { rewardManager.addCoins(it) }
        soundManager.play(soundManager.collect, true)
        if (expManager.addXp(80)) state = GameState.LEVEL_UP
    }

    fun performFullReset(
        shopManager: ShopManager,
        worldMapManager: WorldMapManager,
        npcManager: NpcManager,
        ambientSoundManager: AmbientSoundManager,
        playerManager: PlayerManager,
        dailyLoginManager: DailyLoginManager
    ) {
        saveManager.clear()
        reset()
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
        state = GameState.SPLASH
    }

    fun returnToMenu() {
        state = GameState.MENU
    }

    private fun checkWinCondition(sOn: Boolean) {
        val mission = currentCountry.mission
        val won = mFoods >= mission.requiredFoods &&
                  mSpecial >= mission.requiredSpecial &&
                  mCoins >= mission.requiredCoins &&
                  mBombs >= (if (mission.maxBombsAllowed < 999) mission.maxBombsAllowed else 0)

        if (won) {
            if (playMode == PlayMode.NORMAL) {
                worldProgressManager.completeMission(countryId)
                statsManager.trackCountry()
                galleryManager.unlockMemory("first_country")
                if (lives == rewardManager.maxLives) statsManager.trackPerfect()
                
                // Add rewards for mission complete
                rewardManager.addCoins(50)
                if (expManager.addXp(50)) state = GameState.LEVEL_UP
                else state = GameState.LEVEL_COMPLETE
                
                achievementManager.checkProgress(this, rewardManager, foodAlbumManager, worldProgressManager, sOn)
            } else {
                // Showcase just goes to Level Complete
                state = GameState.LEVEL_COMPLETE
            }
        }
    }
}
