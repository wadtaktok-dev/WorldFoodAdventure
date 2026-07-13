package com.mahmodhota.worldfoodadventure.game

import androidx.compose.ui.geometry.Offset

object CountryRepository {
    
    val europePack = listOf(
        CountryData(
            id = "germany",
            displayName = "Germany",
            nativeName = "Deutschland",
            flagEmoji = "🇩🇪",
            isoCode = "DE",
            region = "Europe",
            subregion = "Western Europe",
            continent = "Europe",
            language = "German",
            population = "83 Million",
            capital = "Berlin",
            welcomeMessage = "Willkommen in Deutschland!",
            cultureDesc = "Sausty bread and fairytale castles.",
            fact = "Germany has over 1,500 types of beer and 300 types of bread.",
            festival = "Oktoberfest",
            weatherOptions = listOf(WeatherType.SUNNY, WeatherType.RAIN, WeatherType.CLOUDY),
            status = ContentStatus.FULL,
            mapLocation = Offset(0.5f, 0.3f),
            mission = MissionConfig(requiredFoods = 8, maxBombsAllowed = 5),
            foods = listOf(
                FoodData("germany_brezel", "🥨", "Pretzel", "Baked knot bread"),
                FoodData("germany_wurst", "🌭", "Sausage", "Grilled bratwurst"),
                FoodData("germany_cake", "🍰", "Black Forest Cake", "Cherry & Cream"),
                FoodData("germany_strudel", "🥧", "Apple Strudel", "Warm pastry", isSpecial = true)
            ),
            landmarks = listOf("Brandenburg Gate", "Neuschwanstein Castle"),
            bossId = "Pretzel King"
        ),
        CountryData(
            id = "italy",
            displayName = "Italy",
            nativeName = "Italia",
            flagEmoji = "🇮🇹",
            isoCode = "IT",
            region = "Europe",
            subregion = "Southern Europe",
            continent = "Europe",
            language = "Italian",
            population = "59 Million",
            capital = "Rome",
            welcomeMessage = "Benvenuti in Italia!",
            cultureDesc = "Beloved pasta and pizza.",
            fact = "Italy has the most UNESCO World Heritage Sites in the world.",
            festival = "Carnevale",
            weatherOptions = listOf(WeatherType.SUNNY, WeatherType.SUNSET),
            status = ContentStatus.FULL,
            mapLocation = Offset(0.52f, 0.45f),
            mission = MissionConfig(requiredFoods = 10),
            foods = listOf(
                FoodData("italy_pizza", "🍕", "Pizza", "Neapolitan style"),
                FoodData("italy_pasta", "🍝", "Pasta", "Hand-rolled"),
                FoodData("italy_gelato", "🍦", "Gelato", "Creamy dessert"),
                FoodData("italy_lobster", "🦞", "Lobster", "Fresh seafood", isSpecial = true)
            ),
            landmarks = listOf("Colosseum", "Leaning Tower"),
            bossId = "Pizza Monster"
        ),
        CountryData(
            id = "france",
            displayName = "France",
            nativeName = "France",
            flagEmoji = "🇫🇷",
            isoCode = "FR",
            region = "Europe",
            subregion = "Western Europe",
            continent = "Europe",
            language = "French",
            population = "68 Million",
            capital = "Paris",
            welcomeMessage = "Bienvenue en France!",
            cultureDesc = "Art, history and fine pastries.",
            fact = "France is the most visited country in the world.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.45f, 0.35f),
            mission = MissionConfig(requiredFoods = 12),
            foods = listOf(
                FoodData("france_croissant", "🥐", "Croissant", "Buttery flaky pastry"),
                FoodData("france_baguette", "🥖", "Baguette", "Classic French bread"),
                FoodData("france_cheese", "🧀", "Cheese", "Fine aged brie"),
                FoodData("france_macaron", "🍪", "Macaron", "Colorful almond cookie", isSpecial = true)
            ),
            landmarks = listOf("Eiffel Tower", "Louvre Museum"),
            bossId = "Cheese Phantom"
        ),
        CountryData(
            id = "spain",
            displayName = "Spain",
            nativeName = "España",
            flagEmoji = "🇪🇸",
            isoCode = "ES",
            region = "Europe",
            subregion = "Southern Europe",
            continent = "Europe",
            language = "Spanish",
            population = "47 Million",
            capital = "Madrid",
            welcomeMessage = "¡Bienvenidos a España!",
            cultureDesc = "Vibrant culture and tapas.",
            fact = "Spain is the world's largest producer of olive oil.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.42f, 0.48f),
            mission = MissionConfig(requiredFoods = 10),
            foods = listOf(
                FoodData("spain_paella", "🥘", "Paella", "Traditional rice dish"),
                FoodData("spain_shrimp", "🍤", "Shrimp", "Fresh garlic shrimp"),
                FoodData("spain_wine", "🍷", "Wine", "Fine Spanish red"),
                FoodData("spain_churros", "🥨", "Churros", "Fried dough", isSpecial = true)
            ),
            landmarks = listOf("Sagrada Família", "Park Güell"),
            bossId = "Bull General"
        ),
        CountryData(
            id = "greece",
            displayName = "Greece",
            nativeName = "Elláda",
            flagEmoji = "🇬🇷",
            isoCode = "GR",
            region = "Europe",
            subregion = "Southern Europe",
            continent = "Europe",
            language = "Greek",
            population = "10 Million",
            capital = "Athens",
            welcomeMessage = "Kalosirthate stin Ellada!",
            cultureDesc = "Ancient history and blue seas.",
            fact = "Greece has more than 2,000 islands, but only about 170 are inhabited.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.58f, 0.48f),
            mission = MissionConfig(requiredFoods = 15),
            foods = listOf(
                FoodData("greece_gyro", "🥙", "Gyro", "Grilled meat in pita"),
                FoodData("greece_salad", "🥗", "Salad", "Fresh Greek salad"),
                FoodData("greece_feta", "🧀", "Feta", "Traditional sheep cheese"),
                FoodData("greece_baklava", "🍰", "Baklava", "Sweet honey pastry", isSpecial = true)
            ),
            landmarks = listOf("Acropolis", "Parthenon"),
            bossId = "Medusa Chef"
        )
    )

    val africaPack = listOf(
        CountryData(
            id = "sudan",
            displayName = "Sudan",
            nativeName = "As-Sudan",
            flagEmoji = "🇸🇩",
            isoCode = "SD",
            region = "Africa",
            subregion = "Northern Africa",
            continent = "Africa",
            language = "Arabic, English",
            population = "45 Million",
            capital = "Khartoum",
            welcomeMessage = "Marhaban bikum fi al-Sudan!",
            cultureDesc = "Nile river hospitality.",
            fact = "Sudan has more pyramids than Egypt, with over 200 in total.",
            festival = "Sudanese Cultural Festival",
            weatherOptions = listOf(WeatherType.SUNNY, WeatherType.SUNSET),
            status = ContentStatus.FULL,
            mapLocation = Offset(0.6f, 0.65f),
            mission = MissionConfig(requiredFoods = 12, requiredCoins = 5, maxBombsAllowed = 6),
            foods = listOf(
                FoodData("sudan_bamia", "🥘", "Bamia", "Okra stew"),
                FoodData("sudan_kisra", "🍞", "Kisra", "Fermented bread"),
                FoodData("sudan_ful", "🥜", "Ful Medames", "Fava beans"),
                FoodData("sudan_shaiya", "🍖", "Shaiya", "Grilled lamb", isSpecial = true)
            ),
            landmarks = listOf("Pyramids of Meroë"),
            bossId = "Desert Guardian"
        )
    )

    val asiaPack = listOf(
        CountryData(
            id = "japan",
            displayName = "Japan",
            nativeName = "Nippon",
            flagEmoji = "🇯🇵",
            isoCode = "JP",
            region = "Asia",
            subregion = "Eastern Asia",
            continent = "Asia",
            language = "Japanese",
            population = "125 Million",
            capital = "Tokyo",
            welcomeMessage = "Nippon e yokoso!",
            cultureDesc = "Tradition meets technology.",
            fact = "Japan has more than 5 million vending machines across the country.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.92f, 0.35f),
            mission = MissionConfig(requiredFoods = 5, requiredSpecial = 5, requiredCoins = 3),
            foods = listOf(
                FoodData("japan_sushi", "🍣", "Sushi", "Vinegared rice"),
                FoodData("japan_ramen", "🍜", "Ramen", "Noodle soup"),
                FoodData("japan_onigiri", "🍙", "Onigiri", "Rice triangle"),
                FoodData("japan_bento", "🍱", "Bento", "Traditional lunch box", isSpecial = true)
            ),
            landmarks = listOf("Mount Fuji", "Kinkaku-ji"),
            bossId = "Sushi Dragon"
        ),
        CountryData(
            id = "india",
            displayName = "India",
            nativeName = "Bharat",
            flagEmoji = "🇮🇳",
            isoCode = "IN",
            region = "Asia",
            subregion = "Southern Asia",
            continent = "Asia",
            language = "Hindi, English",
            population = "1.4 Billion",
            capital = "New Delhi",
            welcomeMessage = "Bharat mein aapka swagat hai!",
            cultureDesc = "Spice and colors.",
            fact = "India is the world's largest producer of milk and spices.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.75f, 0.55f),
            mission = MissionConfig(requiredFoods = 12),
            foods = listOf(
                FoodData("india_curry", "🍛", "Curry", "Spiced vegetable curry"),
                FoodData("india_biryani", "🥘", "Biryani", "Fragrant basmati rice"),
                FoodData("india_naan", "🥯", "Naan", "Oven-baked flatbread"),
                FoodData("india_samosa", "🍘", "Samosa", "Fried savory pastry", isSpecial = true)
            ),
            landmarks = listOf("Taj Mahal", "Hawa Mahal"),
            bossId = "Spice Naga"
        ),
        CountryData(
            id = "china",
            displayName = "China",
            nativeName = "Zhōngguó",
            flagEmoji = "🇨🇳",
            isoCode = "CN",
            region = "Asia",
            subregion = "Eastern Asia",
            continent = "Asia",
            language = "Mandarin",
            population = "1.4 Billion",
            capital = "Beijing",
            welcomeMessage = "Huanying lai dao Zhongguo!",
            cultureDesc = "Rich heritage.",
            fact = "The Great Wall of China is over 21,000 kilometers long.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.85f, 0.4f),
            mission = MissionConfig(requiredFoods = 18),
            foods = listOf(
                FoodData("china_dumpling", "🥟", "Dumpling", "Steamed pork dumpling"),
                FoodData("china_takeout", "🥡", "Takeout", "Stir-fried noodles"),
                FoodData("china_chopsticks", "🥢", "Chopsticks", "Traditional utensils"),
                FoodData("china_mooncake", "🥮", "Mooncake", "Traditional festival cake", isSpecial = true)
            ),
            landmarks = listOf("Great Wall", "Forbidden City"),
            bossId = "Great Wall Dragon"
        ),
        CountryData(
            id = "thailand",
            displayName = "Thailand",
            nativeName = "Prathet Thai",
            flagEmoji = "🇹🇭",
            isoCode = "TH",
            region = "Asia",
            subregion = "South-Eastern Asia",
            continent = "Asia",
            language = "Thai",
            population = "71 Million",
            capital = "Bangkok",
            welcomeMessage = "Yindee ton rap su Thailand!",
            cultureDesc = "Street food paradise.",
            fact = "Thailand is the only Southeast Asian country that was never colonized by Europeans.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.82f, 0.65f),
            mission = MissionConfig(requiredFoods = 14),
            foods = listOf(
                FoodData("thailand_soup", "🍲", "Soup", "Spicy Tom Yum"),
                FoodData("thailand_padthai", "🍛", "Pad Thai", "Stir-fried rice noodles"),
                FoodData("thailand_coconut", "🥥", "Coconut", "Fresh tropical coconut"),
                FoodData("thailand_mango", "🥭", "Mango", "Mango sticky rice", isSpecial = true)
            ),
            landmarks = listOf("Wat Arun", "Grand Palace"),
            bossId = "Elephant King"
        )
    )

    val middleEastPack = listOf(
        CountryData(
            id = "turkey",
            displayName = "Turkey",
            nativeName = "Türkiye",
            flagEmoji = "🇹🇷",
            isoCode = "TR",
            region = "Middle East",
            subregion = "Western Asia",
            continent = "Eurasia",
            language = "Turkish",
            population = "85 Million",
            capital = "Ankara",
            welcomeMessage = "Türkiye'ye hos geldiniz!",
            cultureDesc = "Grand bazaar and ancient history.",
            fact = "Istanbul is the only city in the world that spans two continents: Europe and Asia.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.65f, 0.45f),
            mission = MissionConfig(requiredFoods = 16),
            foods = listOf(
                FoodData("turkey_kebab", "🥙", "Kebab", "Grilled meat skewer"),
                FoodData("turkey_kofte", "🥘", "Kofte", "Turkish meatballs"),
                FoodData("turkey_tea", "🍵", "Tea", "Strong black tea"),
                FoodData("turkey_delight", "🍬", "Delight", "Sweet Turkish delight", isSpecial = true)
            ),
            landmarks = listOf("Hagia Sophia", "Blue Mosque"),
            bossId = "Bazaar Genie"
        )
    )

    val americasPack = listOf(
        CountryData(
            id = "mexico",
            displayName = "Mexico",
            nativeName = "México",
            flagEmoji = "🇲🇽",
            isoCode = "MX",
            region = "North America",
            subregion = "Central America",
            continent = "North America",
            language = "Spanish",
            population = "128 Million",
            capital = "Mexico City",
            welcomeMessage = "¡Bienvenidos à México!",
            cultureDesc = "Dia de los Muertos and tacos.",
            fact = "Mexico introduced chocolate, corn, and chilies to the world.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.18f, 0.55f),
            mission = MissionConfig(requiredFoods = 20),
            foods = listOf(
                FoodData("mexico_taco", "🌮", "Taco", "Corn tortilla"),
                FoodData("mexico_burrito", "🌯", "Burrito", "Large wrap"),
                FoodData("mexico_corn", "🌽", "Corn", "Grilled street corn"),
                FoodData("mexico_tamales", "🫫", "Tamales", "Steamed corn dough", isSpecial = true)
            ),
            landmarks = listOf("Chichen Itza", "Teotihuacan"),
            bossId = "Piñata Titan"
        )
    )

    val comingSoonPack = listOf(
        CountryData(
            id = "brazil", displayName = "Brazil", nativeName = "Brasil", flagEmoji = "🇧🇷", isoCode = "BR",
            region = "South America", subregion = "South America", continent = "South America",
            language = "Portuguese", population = "214 Million", capital = "Brasilia", 
            welcomeMessage = "", cultureDesc = "", status = ContentStatus.COMING_SOON,
            foods = emptyList(), mission = MissionConfig()
        ),
        CountryData(
            id = "usa", displayName = "USA", nativeName = "USA", flagEmoji = "🇺🇸", isoCode = "US",
            region = "North America", subregion = "North America", continent = "North America",
            language = "English", population = "331 Million", capital = "Washington D.C.",
            welcomeMessage = "", cultureDesc = "", status = ContentStatus.COMING_SOON,
            foods = emptyList(), mission = MissionConfig()
        )
    )

    val allCountries: List<CountryData> = europePack + africaPack + asiaPack + middleEastPack + americasPack + comingSoonPack

    fun getCountry(id: String): CountryData? = allCountries.find { it.id == id }
    fun getFirstCountry(): CountryData = allCountries.first { it.id == "germany" }
    
    fun getPlayableCountries(): List<CountryData> = allCountries.filter { it.status != ContentStatus.COMING_SOON }

    fun getNextCountry(currentId: String): CountryData? {
        val playable = getPlayableCountries()
        val index = playable.indexOfFirst { it.id == currentId }
        if (index == -1 || index == playable.size - 1) return null
        return playable[index + 1]
    }
}
