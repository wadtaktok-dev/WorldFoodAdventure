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
        val updateList = mutableListOf<GameItem>()
        
        for (i in items) {
            val ny = i.y + (600f * (1f + currentScore / 150f) * dt)
            if (abs(i.x - px) < 60 && abs(ny - (sH - 200)) < 60) {
                handleCollision(i, sOn)
            } else if (ny <= sH + 100) {
                updateList.add(i.copy(y = ny))
            } else if (i.isObstacle) {
                mBombs++
            }
        }

        if (Random.nextInt(100) < 5) {
            val r = Random.nextInt(100)
            val countryFoods = currentCountry.foods
            updateList.add(when {
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
            })
        }
        items.clear()
        items.addAll(updateList)
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
        val nextAttacks = mutableListOf<BossAttack>()
        for (a in bossAttacks) {
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
            } else if (ny < sH + 100) {
                nextAttacks.add(a.copy(y = ny))
            }
        }
        bossAttacks.clear()
        bossAttacks.addAll(nextAttacks)

        // Player attacks boss (auto-fire or simplified catch mechanic for v0.21)
        // For simplicity: Catching "Attack Items" spawns automatically
        if (Random.nextInt(100) < 7) {
            items.add(GameItem(Random.nextFloat() * sW, -100f, "⚡", false))
        }
        
        val updateItems = mutableListOf<GameItem>()
        for (i in items) {
            val ny = i.y + 600f * dt
            if (abs(i.x - px) < 60 && abs(ny - (sH - 200)) < 60) {
                if (i.emoji == "⚡") {
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
            } else if (ny < sH + 100) {
                updateItems.add(i.copy(y = ny))
            }
        }
        items.clear()
        items.addAll(updateItems)
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
