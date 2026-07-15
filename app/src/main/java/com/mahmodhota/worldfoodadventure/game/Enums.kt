package com.mahmodhota.worldfoodadventure.game

enum class GameState { SPLASH, TUTORIAL, MENU, WORLD_MAP, ACHIEVEMENTS, HIGH_SCORES, SHOP, SETTINGS, DAILY_CHALLENGE, COUNTDOWN, PLAYING, PAUSED, LEVEL_COMPLETE, TREASURE_CHEST, GAME_OVER, FOOD_ALBUM, BOSS_INTRO, BOSS_BATTLE, BOSS_DEFEATED, WORLD_TRAVEL, LEVEL_UP, PROFILE, STATISTICS, GALLERY, CHALLENGE_ARENA, DAILY_LOGIN, SEASONAL_EVENTS, FEEDBACK, FEEDBACK_HISTORY, PASSPORT, TV_TRAVEL, COUNTRY_INFO }

enum class PlayMode { NORMAL, LIVE_SHOWCASE }

enum class PresentationMode { NONE, TV_TRAVEL }

enum class Season { SPRING, SUMMER, AUTUMN, WINTER, CHRISTMAS, HALLOWEEN, WORLD_FOOD_DAY, NONE }

enum class WeatherType { SUNNY, MORNING, SUNSET, NIGHT, RAIN, SNOW, SAKURA, STARS, CLOUDY }

enum class MusicStyle(val displayName: String, val fileName: String, val genre: String, val duration: String) {
    EDM("EDM", "music_edm", "Electronic", "2:45"),
    HIP_HOP("Hip Hop", "music_hiphop", "Urban", "3:12"),
    GUITAR("Guitar", "music_guitar", "Acoustic", "2:55"),
    CLASSIC("Classic", "music_classic", "Orchestral", "4:10"),
    REGGAE("Reggae", "music_reggae", "Caribbean", "3:30"),
    INTERNATIONAL("International", "music_international", "World", "3:00"),
    ITALY("Italy", "music_italy", "Italian Folk", "2:50"),
    GERMANY("Germany", "music_germany", "Bavarian", "2:45"),
    JAPAN("Japan", "music_japan", "Zen/Traditional", "3:15"),
    FRANCE("France", "music_france", "Chanson", "3:05"),
    SPAIN("Spain", "music_spain", "Flamenco", "2:55"),
    GREECE("Greece", "music_greece", "Sirtaki", "3:10"),
    MEXICO("Mexico", "music_mexico", "Mariachi", "3:00"),
    INDIA("India", "music_india", "Bollywood", "3:20"),
    CHINA("China", "music_china", "Traditional Chinese", "3:30"),
    THAILAND("Thailand", "music_thailand", "Thai Folk", "3:15"),
    TURKEY("Turkey", "music_turkey", "Turkish Folk", "3:05"),
    BRAZIL("Brazil", "music_brazil", "Samba", "3:40"),
    USA("USA", "music_usa", "Country/Pop", "3:10"),
    SUDAN("Sudan", "music_sudan", "Sudanese Folk", "3:25"),
    UK("United Kingdom", "music_uk", "British Rock/Pop", "3:15"),
    NETHERLANDS("Netherlands", "music_netherlands", "Dutch Folk/EDM", "3:00"),
    PORTUGAL("Portugal", "music_portugal", "Fado", "3:20"),
    AUSTRIA("Austria", "music_austria", "Classical/Waltz", "4:10"),
    SWITZERLAND("Switzerland", "music_switzerland", "Yodel/Alp Folk", "3:05"),
    EGYPT("Egypt", "music_egypt", "Egyptian Folk", "3:20"),
    MOROCCO("Morocco", "music_morocco", "Gnawa", "3:15"),
    KENYA("Kenya", "music_kenya", "Benga", "3:00"),
    ETHIOPIA("Ethiopia", "music_ethiopia", "Ethio-jazz", "3:30")
}
