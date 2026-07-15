package com.mahmodhota.worldfoodadventure.game

import androidx.compose.ui.geometry.Offset

object CountryRepository {

    private val germanyEncyclopedia = EncyclopediaData(
        area = "357,022 km²",
        currency = "Euro (€)",
        climate = "Temperate seasonal",
        nationalAnimal = "Federal Eagle",
        nationalFlower = "Cornflower",
        traditionalMusic = "Bavarian Folk / Classical",
        shortHistory = "Unified in 1871, a major world economy with a rich cultural and scientific history.",
        unescoSites = listOf("Cologne Cathedral", "Museum Island", "Wadden Sea"),
        travelTips = listOf("Use the efficient train system", "Carry some cash", "Learn basic German phrases")
    )

    private val italyEncyclopedia = EncyclopediaData(
        area = "301,340 km²",
        currency = "Euro (€)",
        climate = "Mediterranean",
        nationalAnimal = "Italian Wolf",
        nationalFlower = "Lily",
        traditionalMusic = "Opera / Italian Folk",
        shortHistory = "Cradle of the Roman Empire and the Renaissance.",
        unescoSites = listOf("Venice", "Historic Centre of Rome", "Pompeii"),
        travelTips = listOf("Validate your train tickets", "Avoid tourist menus", "Enjoy a slow aperitivo")
    )

    private val franceEncyclopedia = EncyclopediaData(
        area = "551,695 km²",
        currency = "Euro (€)",
        climate = "Temperate / Oceanic",
        nationalAnimal = "Gallic Rooster",
        nationalFlower = "Iris",
        traditionalMusic = "Chanson / Classical",
        shortHistory = "A global center of art, science, and philosophy, known for the French Revolution.",
        unescoSites = listOf("Mont-Saint-Michel", "Palace of Versailles", "Banks of the Seine"),
        travelTips = listOf("Say Bonjour when entering shops", "Take your time at meals", "Explore beyond Paris")
    )

    private val spainEncyclopedia = EncyclopediaData(
        area = "505,990 km²",
        currency = "Euro (€)",
        climate = "Mediterranean",
        nationalAnimal = "Bull",
        nationalFlower = "Carnation",
        traditionalMusic = "Flamenco",
        shortHistory = "A history of exploration and a diverse cultural heritage from various influences.",
        unescoSites = listOf("Alhambra", "Sagrada Família", "Historic Centre of Córdoba"),
        travelTips = listOf("Siesta time is real", "Try different tapas", "Learn about regional differences")
    )

    private val greeceEncyclopedia = EncyclopediaData(
        area = "131,957 km²",
        currency = "Euro (€)",
        climate = "Mediterranean",
        nationalAnimal = "Dolphin",
        nationalFlower = "Acanthus",
        traditionalMusic = "Rebetiko / Syrtaki",
        shortHistory = "The birthplace of democracy and Western philosophy.",
        unescoSites = listOf("Acropolis", "Meteora", "Delphi"),
        travelTips = listOf("Wear comfortable shoes for ruins", "Try local island cheeses", "Don't rush")
    )

    private val sudanEncyclopedia = EncyclopediaData(
        area = "1,886,068 km²",
        currency = "Sudanese Pound (SDG)",
        climate = "Tropical / Desert",
        nationalAnimal = "Secretary Bird",
        nationalFlower = "Hibiscus",
        traditionalMusic = "Sudanese Folk / Tambour",
        shortHistory = "Home to ancient civilizations like the Kingdom of Kush, rich in archeological treasures.",
        unescoSites = listOf("Pyramids of Meroë", "Jebel Barkal"),
        travelTips = listOf("Visit the pyramids at sunrise", "Enjoy Nile-side tea", "Respect local customs")
    )

    private val japanEncyclopedia = EncyclopediaData(
        area = "377,975 km²",
        currency = "Yen (¥)",
        climate = "Temperate",
        nationalAnimal = "Green Pheasant",
        nationalFlower = "Cherry Blossom (Sakura)",
        traditionalMusic = "Gagaku / J-Pop",
        shortHistory = "An island nation with a unique blend of ancient traditions and modern technology.",
        unescoSites = listOf("Mt. Fuji", "Kinkaku-ji", "Hiroshima Peace Memorial"),
        travelTips = listOf("Get a JR Pass", "Respect silence in trains", "Try local convenience store snacks")
    )

    private val indiaEncyclopedia = EncyclopediaData(
        area = "3,287,263 km²",
        currency = "Indian Rupee (₹)",
        climate = "Tropical",
        nationalAnimal = "Bengal Tiger",
        nationalFlower = "Lotus",
        traditionalMusic = "Carnatic / Hindustani / Bollywood",
        shortHistory = "One of the world's oldest civilizations and the world's largest democracy.",
        unescoSites = listOf("Taj Mahal", "Hampi", "Ajanta Caves"),
        travelTips = listOf("Dress modestly", "Use bottled water", "Embrace the vibrant chaos")
    )

    private val chinaEncyclopedia = EncyclopediaData(
        area = "9,596,961 km²",
        currency = "Yuan (¥)",
        climate = "Diverse",
        nationalAnimal = "Giant Panda",
        nationalFlower = "Peony",
        traditionalMusic = "Traditional Chinese / Opera",
        shortHistory = "An ancient civilization with a long history of imperial dynasties.",
        unescoSites = listOf("Great Wall", "Forbidden City", "Terracotta Army"),
        travelTips = listOf("Use mobile payment apps", "Bring a translation app", "Explore regional cuisines")
    )

    private val thailandEncyclopedia = EncyclopediaData(
        area = "513,120 km²",
        currency = "Baht (฿)",
        climate = "Tropical",
        nationalAnimal = "Elephant",
        nationalFlower = "Ratchaphruek",
        traditionalMusic = "Luk Thung / Mor Lam",
        shortHistory = "Known as the Land of Smiles, it was never colonized by Europeans.",
        unescoSites = listOf("Ayutthaya", "Sukhothai", "Khao Yai"),
        travelTips = listOf("Visit temples early", "Try street food", "Respect the Royal Family")
    )

    private val turkeyEncyclopedia = EncyclopediaData(
        area = "783,356 km²",
        currency = "Turkish Lira (₺)",
        climate = "Mediterranean / Continental",
        nationalAnimal = "Gray Wolf",
        nationalFlower = "Tulip",
        traditionalMusic = "Turkish Folk / Classical Ottoman",
        shortHistory = "A bridge between Europe and Asia, home to many empires.",
        unescoSites = listOf("Hagia Sophia", "Cappadocia", "Ephesus"),
        travelTips = listOf("Drink lots of tea", "Visit a hammam", "Wander through local bazaars")
    )

    private val mexicoEncyclopedia = EncyclopediaData(
        area = "1,964,375 km²",
        currency = "Mexican Peso ($)",
        climate = "Tropical / Desert",
        nationalAnimal = "Golden Eagle",
        nationalFlower = "Dahlia",
        traditionalMusic = "Mariachi",
        shortHistory = "Home to civilizations like the Aztecs and Mayans, rich in colonial and indigenous heritage.",
        unescoSites = listOf("Chichen Itza", "Teotihuacan", "Historic Centre of Mexico City"),
        travelTips = listOf("Try the diverse salsas", "Visit local markets", "Learn about the Day of the Dead")
    )

    private val ukEncyclopedia = EncyclopediaData(
        area = "242,495 km²",
        currency = "British Pound (£)",
        climate = "Temperate Maritime",
        nationalAnimal = "Lion",
        nationalFlower = "Tudor Rose",
        traditionalMusic = "British Rock / Pop",
        shortHistory = "A union of England, Scotland, Wales, and Northern Ireland with a deep global legacy.",
        unescoSites = listOf("Stonehenge", "Tower of London", "Giant's Causeway", "Blenheim Palace"),
        travelTips = listOf("Bring an umbrella", "Drive on the left", "Try the Sunday Roast")
    )

    private val netherlandsEncyclopedia = EncyclopediaData(
        area = "41,850 km²",
        currency = "Euro (€)",
        climate = "Temperate Maritime",
        nationalAnimal = "Lion",
        nationalFlower = "Tulip",
        traditionalMusic = "Dutch Folk / EDM",
        shortHistory = "A historic maritime nation famous for windmills, canals, and innovative land reclamation.",
        unescoSites = listOf("Kinderdijk Windmills", "Amsterdam Canals", "Wadden Sea", "Beemster Polder"),
        travelTips = listOf("Rent a bicycle", "Buy a Museumkaart", "Try the raw herring")
    )

    private val portugalEncyclopedia = EncyclopediaData(
        area = "92,212 km²",
        currency = "Euro (€)",
        climate = "Mediterranean",
        nationalAnimal = "Iberian Wolf",
        nationalFlower = "Lavender",
        traditionalMusic = "Fado",
        shortHistory = "One of Europe's oldest nations, pioneered global exploration in the 15th century.",
        unescoSites = listOf("Jerónimos Monastery", "Sintra", "Historic Porto", "University of Coimbra"),
        travelTips = listOf("Try Pastel de Nata", "Wear comfy shoes for hills", "Visit the Algarve beaches")
    )

    private val austriaEncyclopedia = EncyclopediaData(
        area = "83,879 km²",
        currency = "Euro (€)",
        climate = "Temperate / Alpine",
        nationalAnimal = "Eagle",
        nationalFlower = "Edelweiss",
        traditionalMusic = "Classical / Viennese Waltz",
        shortHistory = "The center of the Habsburg Empire, renowned for its classical music and Alpine landscapes.",
        unescoSites = listOf("Schönbrunn Palace", "Hallstatt", "Historic Vienna", "Salzburg Centre"),
        travelTips = listOf("Visit a coffee house", "Take scenic Alpine trains", "Try the Schnitzel")
    )

    private val switzerlandEncyclopedia = EncyclopediaData(
        area = "41,285 km²",
        currency = "Swiss Franc (CHF)",
        climate = "Temperate / Alpine",
        nationalAnimal = "Alpine Ibex",
        nationalFlower = "Edelweiss",
        traditionalMusic = "Yodel / Alp Folk",
        shortHistory = "A neutral Alpine nation known for its lakes, mountains, watches, and direct democracy.",
        unescoSites = listOf("Old City of Bern", "Jungfrau-Aletsch", "Lavaux Vineyards", "Abbey of Saint Gall"),
        travelTips = listOf("Get a Swiss Travel Pass", "Tap water is excellent", "Be precisely on time")
    )

    private val egyptEncyclopedia = EncyclopediaData(
        area = "1,002,450 km²",
        currency = "Egyptian Pound (EGP)",
        climate = "Arid / Desert",
        nationalAnimal = "Steppe Eagle",
        nationalFlower = "Lotus",
        traditionalMusic = "Egyptian Arabic Folk",
        shortHistory = "Home to one of the world's oldest civilizations, known for its pharaohs, pyramids, and the life-giving Nile.",
        unescoSites = listOf("Pyramids of Giza", "Ancient Thebes", "Abu Simbel", "Islamic Cairo"),
        travelTips = listOf("Visit pyramids at sunrise", "Learn to haggle in souks", "Drink bottled water")
    )

    private val moroccoEncyclopedia = EncyclopediaData(
        area = "446,550 km²",
        currency = "Moroccan Dirham (MAD)",
        climate = "Mediterranean / Desert",
        nationalAnimal = "Barbary Lion",
        nationalFlower = "Rose",
        traditionalMusic = "Gnawa / Andalusian",
        shortHistory = "A historic kingdom with a rich blend of Arab, Berber, and African influences, famous for its colorful medinas.",
        unescoSites = listOf("Medina of Marrakesh", "Fez", "Essaouira", "Ait Benhaddou"),
        travelTips = listOf("Enjoy the mint tea", "Get lost in the souks", "Respect local customs")
    )

    private val kenyaEncyclopedia = EncyclopediaData(
        area = "580,367 km²",
        currency = "Kenyan Shilling (KES)",
        climate = "Tropical",
        nationalAnimal = "Lion",
        nationalFlower = "Orchid",
        traditionalMusic = "Benga",
        shortHistory = "A cradle of humanity with significant archeological finds, known today for its incredible wildlife and diverse cultures.",
        unescoSites = listOf("Mount Kenya", "Lamu Old Town", "Lake System in Rift Valley", "Fort Jesus"),
        travelTips = listOf("Go on a safari", "Try street food in Nairobi", "Learn basic Swahili")
    )

    private val ethiopiaEncyclopedia = EncyclopediaData(
        area = "1,104,300 km²",
        currency = "Ethiopian Birr (ETB)",
        climate = "Tropical Monsoon / Highland",
        nationalAnimal = "Abyssinian Lion",
        nationalFlower = "Calla Lily",
        traditionalMusic = "Ethio-jazz",
        shortHistory = "One of the world's oldest nations and the only African country never colonized, with a unique script and calendar.",
        unescoSites = listOf("Lalibela", "Simien National Park", "Aksum", "Fasil Ghebbi"),
        travelTips = listOf("Join a coffee ceremony", "Eat with your hands", "Visit the highlands")
    )

    private val canadaEncyclopedia = EncyclopediaData(
        area = "9,984,670 km²",
        currency = "Canadian Dollar ($)",
        climate = "Temperate / Subarctic",
        nationalAnimal = "Beaver",
        nationalFlower = "Bunchberry",
        traditionalMusic = "Folk / First Nations",
        shortHistory = "A vast nation spanning from the Atlantic to the Pacific, known for its natural beauty and multiculturalism.",
        unescoSites = listOf("L'Anse aux Meadows", "Nahanni National Park", "Gros Morne", "Old Quebec"),
        travelTips = listOf("Bring warm clothes", "Tipping is expected", "Enjoy the national parks")
    )

    private val peruEncyclopedia = EncyclopediaData(
        area = "1,285,216 km²",
        currency = "Sol (S/.)",
        climate = "Tropical / Arid",
        nationalAnimal = "Vicuña",
        nationalFlower = "Cantua",
        traditionalMusic = "Andean / Criollo",
        shortHistory = "Center of the ancient Inca Empire, with a diverse geography from the Amazon to the Andes.",
        unescoSites = listOf("Machu Picchu", "Cusco", "Chan Chan", "Nazca Lines"),
        travelTips = listOf("Altitude sickness is real", "Try the street food", "Respect sacred sites")
    )

    private val chileEncyclopedia = EncyclopediaData(
        area = "756,102 km²",
        currency = "Chilean Peso ($)",
        climate = "Diverse / Mediterranean",
        nationalAnimal = "Huemul",
        nationalFlower = "Copihue",
        traditionalMusic = "Cueca",
        shortHistory = "A long, narrow country with a varied climate, famous for its wines, mountains, and coastal beauty.",
        unescoSites = listOf("Rapa Nui (Easter Island)", "Chiloé Churches", "Valparaíso", "Sewell"),
        travelTips = listOf("Explore Patagonia", "Try local wines", "Be prepared for earthquakes")
    )

    private val argentinaEncyclopedia = EncyclopediaData(
        area = "2,780,400 km²",
        currency = "Argentine Peso ($)",
        climate = "Temperate / Arid",
        nationalAnimal = "Rufous Hornero",
        nationalFlower = "Ceibo",
        traditionalMusic = "Tango / Folklore",
        shortHistory = "The second largest country in South America, known for its vast plains, tango, and rich European-Latin heritage.",
        unescoSites = listOf("Los Glaciares", "Iguazu National Park", "Cueva de las Manos", "Quebrada de Humahuaca"),
        travelTips = listOf("Learn basic Tango", "Eat dinner late", "Visit the glaciers")
    )

    private val australiaEncyclopedia = EncyclopediaData(
        area = "7,692,024 km²",
        currency = "Australian Dollar ($)",
        climate = "Arid / Tropical",
        nationalAnimal = "Kangaroo / Emu",
        nationalFlower = "Golden Wattle",
        traditionalMusic = "Didgeridoo / Folk",
        shortHistory = "An island continent with a unique ecosystem and the world's oldest living culture.",
        unescoSites = listOf("Great Barrier Reef", "Kakadu", "Uluru-Kata Tjuta", "Sydney Opera House"),
        travelTips = listOf("Swim between flags", "Protect against UV", "Respect Indigenous land")
    )

    private val newZealandEncyclopedia = EncyclopediaData(
        area = "268,021 km²",
        currency = "New Zealand Dollar ($)",
        climate = "Temperate Maritime",
        nationalAnimal = "Kiwi Bird",
        nationalFlower = "Silver Fern",
        traditionalMusic = "Māori Waiata / Haka",
        shortHistory = "A remote island nation known for its stunning volcanic landscapes and strong Māori heritage.",
        unescoSites = listOf("Te Wahipounamu", "Tongariro National Park", "Subantarctic Islands"),
        travelTips = listOf("Expect four seasons", "Book Great Walks early", "Drive on the left")
    )

    private val southKoreaEncyclopedia = EncyclopediaData(
        area = "100,210 km²",
        currency = "South Korean Won (₩)",
        climate = "Temperate / Continental",
        nationalAnimal = "Siberian Tiger",
        nationalFlower = "Hibiscus syriacus",
        traditionalMusic = "Pansori / Gugak",
        shortHistory = "A nation with a rich dynastic history, now a global leader in technology and culture.",
        unescoSites = listOf("Seokguram Grotto", "Gyeongju Historic Areas", "Hwaseong Fortress", "Changdeokgung Palace"),
        travelTips = listOf("Use a T-money card", "Bowing is a sign of respect", "Try street food in Myeongdong")
    )

    private val vietnamEncyclopedia = EncyclopediaData(
        area = "331,212 km²",
        currency = "Vietnamese Dong (₫)",
        climate = "Tropical / Monsoon",
        nationalAnimal = "Water Buffalo",
        nationalFlower = "Lotus",
        traditionalMusic = "Nhã nhạc / Quan họ",
        shortHistory = "A nation with a long history of resilience, famous for its stunning landscapes and unique flavors.",
        unescoSites = listOf("Ha Long Bay", "Hội An Ancient Town", "Huế Monuments", "Phong Nha-Kẻ Bàng"),
        travelTips = listOf("Be careful crossing roads", "Drink bottled water", "Learn to say 'Xin chào'")
    )

    private val indonesiaEncyclopedia = EncyclopediaData(
        area = "1,904,569 km²",
        currency = "Indonesian Rupiah (Rp)",
        climate = "Tropical",
        nationalAnimal = "Komodo Dragon",
        nationalFlower = "Jasmine",
        traditionalMusic = "Gamelan",
        shortHistory = "The world's largest archipelago nation, home to a diverse array of cultures and natural wonders.",
        unescoSites = listOf("Borobudur Temple", "Prambanan Temple", "Komodo National Park", "Sangiran Early Man Site"),
        travelTips = listOf("Use Grab or Gojek", "Respect prayer times", "Try local coffee")
    )

    private val malaysiaEncyclopedia = EncyclopediaData(
        area = "330,803 km²",
        currency = "Malaysian Ringgit (RM)",
        climate = "Equatorial / Tropical",
        nationalAnimal = "Malayan Tiger",
        nationalFlower = "Hibiscus rosa-sinensis",
        traditionalMusic = "Joget / Gamelan",
        shortHistory = "A multicultural nation with a strategic position in maritime trade, blending Malay, Chinese, and Indian influences.",
        unescoSites = listOf("Melaka and George Town", "Kinabalu Park", "Gunung Mulu National Park", "Lenggong Valley"),
        travelTips = listOf("Try Mamak stalls", "Use the MRT in KL", "Respect cultural diversity")
    )

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
            bossId = "Pretzel King",
            encyclopedia = germanyEncyclopedia
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
            bossId = "Pizza Monster",
            encyclopedia = italyEncyclopedia
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
            bossId = "Cheese Phantom",
            encyclopedia = franceEncyclopedia
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
            bossId = "Bull General",
            encyclopedia = spainEncyclopedia
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
            bossId = "Medusa Chef",
            encyclopedia = greeceEncyclopedia
        ),
        CountryData(
            id = "uk",
            displayName = "United Kingdom",
            nativeName = "United Kingdom",
            flagEmoji = "🇬🇧",
            isoCode = "GB",
            region = "Europe",
            subregion = "Northern Europe",
            continent = "Europe",
            language = "English",
            population = "67 Million",
            capital = "London",
            welcomeMessage = "Welcome to the UK!",
            cultureDesc = "Tea time and historic landmarks.",
            fact = "The UK is the only country not to have its name on its stamps.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.42f, 0.25f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 10),
            foods = listOf(
                FoodData("uk_fish_and_chips", "🐟", "Fish and Chips", "Classic battered cod"),
                FoodData("uk_shepherds_pie", "🥧", "Shepherd's Pie", "Minced lamb"),
                FoodData("uk_sunday_roast", "🥩", "Sunday Roast", "Roasted meat and veg"),
                FoodData("uk_cornish_pasty", "🥟", "Cornish Pasty", "Baked savory pastry"),
                FoodData("uk_afternoon_tea", "☕", "Afternoon Tea", "Tea with scones"),
                FoodData("uk_beef_wellington", "🥩", "Beef Wellington", "Steak in pastry", isSpecial = true),
                FoodData("uk_haggis", "🍲", "Haggis", "Savory meat pudding"),
                FoodData("uk_welsh_rarebit", "🍞", "Welsh Rarebit", "Cheese on toast"),
                FoodData("uk_eton_mess", "🍨", "Eton Mess", "Meringue and cream"),
                FoodData("uk_full_english", "🍳", "Full English Breakfast", "Traditional breakfast")
            ),
            landmarks = listOf("Big Ben", "Stonehenge", "Tower of London", "Edinburgh Castle"),
            bossId = "Tea Titan",
            encyclopedia = ukEncyclopedia
        ),
        CountryData(
            id = "netherlands",
            displayName = "Netherlands",
            nativeName = "Nederland",
            flagEmoji = "🇳🇱",
            isoCode = "NL",
            region = "Europe",
            subregion = "Western Europe",
            continent = "Europe",
            language = "Dutch",
            population = "17.5 Million",
            capital = "Amsterdam",
            welcomeMessage = "Welkom in Nederland!",
            cultureDesc = "Tulips, canals, and bicycles.",
            fact = "The Netherlands has more bicycles than people.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.48f, 0.32f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 15),
            foods = listOf(
                FoodData("netherlands_stroopwafel", "🧇", "Stroopwafel", "Caramel waffle"),
                FoodData("netherlands_herring", "🐟", "Herring", "Raw fish with onions"),
                FoodData("netherlands_bitterballen", "🧆", "Bitterballen", "Meat-based snack"),
                FoodData("netherlands_gouda_cheese", "🧀", "Gouda Cheese", "Famous Dutch cheese"),
                FoodData("netherlands_poffertjes", "🥞", "Poffertjes", "Mini fluffy pancakes"),
                FoodData("netherlands_stamppot", "🍲", "Stamppot", "Mash with veg & sausage", isSpecial = true),
                FoodData("netherlands_erwtensoep", "🥣", "Erwtensoep", "Split pea soup"),
                FoodData("netherlands_kibbeling", "🐟", "Kibbeling", "Fried fish pieces"),
                FoodData("netherlands_tompouce", "🍰", "Tompouce", "Pastry with cream"),
                FoodData("netherlands_hagelslag", "🍞", "Hagelslag", "Chocolate sprinkles")
            ),
            landmarks = listOf("Keukenhof", "Kinderdijk Windmills", "Rijksmuseum", "Anne Frank House"),
            bossId = "Windmill Giant",
            encyclopedia = netherlandsEncyclopedia
        ),
        CountryData(
            id = "portugal",
            displayName = "Portugal",
            nativeName = "Portugal",
            flagEmoji = "🇵🇹",
            isoCode = "PT",
            region = "Europe",
            subregion = "Southern Europe",
            continent = "Europe",
            language = "Portuguese",
            population = "10.3 Million",
            capital = "Lisbon",
            welcomeMessage = "Bem-vindo a Portugal!",
            cultureDesc = "Coastal beauty and fado music.",
            fact = "Portugal is the world's largest producer of cork.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.38f, 0.52f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 20),
            foods = listOf(
                FoodData("portugal_pastel_de_nata", "🧁", "Pastel de Nata", "Custard tart"),
                FoodData("portugal_bacalhau", "🐟", "Bacalhau", "Salted cod dish"),
                FoodData("portugal_caldo_verde", "🥣", "Caldo Verde", "Green kale soup"),
                FoodData("portugal_sardinhas", "🐟", "Sardinhas Assadas", "Grilled sardines"),
                FoodData("portugal_francesinha", "🥪", "Francesinha", "Rich meat sandwich"),
                FoodData("portugal_polvo", "🐙", "Polvo à Lagareiro", "Roasted octopus", isSpecial = true),
                FoodData("portugal_alheira", "🌭", "Alheira", "Bread sausage"),
                FoodData("portugal_arroz_de_pato", "🥘", "Arroz de Pato", "Baked duck rice"),
                FoodData("portugal_bifana", "🥪", "Bifana", "Pork sandwich"),
                FoodData("portugal_queijada", "🧁", "Queijada de Sintra", "Sweet cheese tart")
            ),
            landmarks = listOf("Belém Tower", "Pena Palace", "Jerónimos Monastery", "Luis I Bridge"),
            bossId = "Fado Phantom",
            encyclopedia = portugalEncyclopedia
        ),
        CountryData(
            id = "austria",
            displayName = "Austria",
            nativeName = "Österreich",
            flagEmoji = "🇦🇹",
            isoCode = "AT",
            region = "Europe",
            subregion = "Central Europe",
            continent = "Europe",
            language = "German",
            population = "9 Million",
            capital = "Vienna",
            welcomeMessage = "Willkommen in Österreich!",
            cultureDesc = "Alps, music, and coffee culture.",
            fact = "Austria is one of the most mountainous countries in Europe.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.55f, 0.38f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 25),
            foods = listOf(
                FoodData("austria_wiener_schnitzel", "🥩", "Wiener Schnitzel", "Breaded veal cutlet"),
                FoodData("austria_sachertorte", "🍰", "Sachertorte", "Chocolate cake"),
                FoodData("austria_apfelstrudel", "🥧", "Apfelstrudel", "Apple pastry"),
                FoodData("austria_knodel", "🥟", "Knödel", "Dumplings"),
                FoodData("austria_kaiserschmarrn", "🥞", "Kaiserschmarrn", "Shredded pancake"),
                FoodData("austria_tafelspitz", "🥩", "Tafelspitz", "Boiled beef", isSpecial = true),
                FoodData("austria_goulash", "🥘", "Austrian Goulash", "Beef stew"),
                FoodData("austria_linzer_torte", "🥧", "Linzer Torte", "Nutty jam pastry"),
                FoodData("austria_backhendl", "🍗", "Backhendl", "Fried chicken"),
                FoodData("austria_tiroler_grostl", "🍳", "Tiroler Gröstl", "Potato & meat fry")
            ),
            landmarks = listOf("Schönbrunn Palace", "Hallstatt", "St. Stephen's Cathedral", "Hofburg"),
            bossId = "Waltz Wizard",
            encyclopedia = austriaEncyclopedia
        ),
        CountryData(
            id = "switzerland",
            displayName = "Switzerland",
            nativeName = "Schweiz",
            flagEmoji = "🇨🇭",
            isoCode = "CH",
            region = "Europe",
            subregion = "Western Europe",
            continent = "Europe",
            language = "German, French, Italian",
            population = "8.7 Million",
            capital = "Bern",
            welcomeMessage = "Grüezi mitenand!",
            cultureDesc = "Alpine peaks and precision.",
            fact = "Switzerland has enough nuclear shelters for its entire population.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.51f, 0.38f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 30),
            foods = listOf(
                FoodData("switzerland_fondue", "🫕", "Cheese Fondue", "Melted cheese dip"),
                FoodData("switzerland_rosti", "🥔", "Rösti", "Potato pancake"),
                FoodData("switzerland_chocolate", "🍫", "Swiss Chocolate", "Smooth chocolate"),
                FoodData("switzerland_raclette", "🧀", "Raclette", "Melted cheese"),
                FoodData("switzerland_muesli", "🥣", "Birchermüesli", "Oats and fruit"),
                FoodData("switzerland_geschnetzeltes", "🥘", "Zürcher Geschnetzeltes", "Veal in cream sauce", isSpecial = true),
                FoodData("switzerland_alplermagronen", "🍝", "Älplermagronen", "Alpine macaroni"),
                FoodData("switzerland_capuns", "🥟", "Capuns", "Chard wraps"),
                FoodData("switzerland_nusstorte", "🥧", "Nusstorte", "Nut tart"),
                FoodData("switzerland_landjager", "🌭", "Landjäger", "Dried sausage")
            ),
            landmarks = listOf("Matterhorn", "Chillon Castle", "Jungfraujoch", "Lucerne Bridge"),
            bossId = "Matterhorn Monk",
            encyclopedia = switzerlandEncyclopedia
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
            bossId = "Desert Guardian",
            encyclopedia = sudanEncyclopedia
        ),
        CountryData(
            id = "egypt",
            displayName = "Egypt",
            nativeName = "Misr",
            flagEmoji = "🇪🇬",
            isoCode = "EG",
            region = "Africa",
            subregion = "Northern Africa",
            continent = "Africa",
            language = "Arabic",
            population = "109 Million",
            capital = "Cairo",
            welcomeMessage = "Ahlan wa sahlan fi Misr!",
            cultureDesc = "Land of pharaohs and the Nile.",
            fact = "The Great Pyramid of Giza is the only one of the original Seven Wonders still standing.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.62f, 0.6f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 50),
            foods = listOf(
                FoodData("egypt_koshary", "🍛", "Koshary", "Lentils, rice & pasta"),
                FoodData("egypt_ful_medames", "🥘", "Ful Medames", "Stewed fava beans"),
                FoodData("egypt_molokhia", "🥣", "Molokhia", "Jute leaf soup"),
                FoodData("egypt_fatta", "🥘", "Fatta", "Layered bread, rice and meat"),
                FoodData("egypt_hawawshi", "🥙", "Hawawshi", "Spiced meat in pita"),
                FoodData("egypt_taameya", "🧆", "Taameya", "Bean falafel"),
                FoodData("egypt_basbousa", "🍰", "Basbousa", "Semolina cake"),
                FoodData("egypt_kunafa", "🥧", "Kunafa", "Sweet shredded pastry", isSpecial = true),
                FoodData("egypt_om_ali", "🍲", "Om Ali", "Bread pudding"),
                FoodData("egypt_kofta", "🍢", "Kofta", "Grilled meat skewers")
            ),
            landmarks = listOf("Pyramids of Giza", "Luxor Temple", "Abu Simbel", "Karnak"),
            bossId = "Sphinx Riddler",
            encyclopedia = egyptEncyclopedia
        ),
        CountryData(
            id = "morocco",
            displayName = "Morocco",
            nativeName = "Al-Maghrib",
            flagEmoji = "🇲🇦",
            isoCode = "MA",
            region = "Africa",
            subregion = "Northern Africa",
            continent = "Africa",
            language = "Arabic, Berber",
            population = "37 Million",
            capital = "Rabat",
            welcomeMessage = "Marhaban bikum fi al-Maghrib!",
            cultureDesc = "A kingdom of colors and spices.",
            fact = "Morocco is only 8 miles from Europe across the Strait of Gibraltar.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.4f, 0.6f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 60),
            foods = listOf(
                FoodData("morocco_tagine", "🥘", "Tagine", "Slow-cooked stew"),
                FoodData("morocco_couscous", "🍛", "Couscous", "Steamed semolina"),
                FoodData("morocco_harira", "🥣", "Harira", "Tomato & lentil soup"),
                FoodData("morocco_pastilla", "🥧", "Pastilla", "Savory-sweet meat pie"),
                FoodData("morocco_zaalouk", "🥗", "Zaalouk", "Eggplant salad"),
                FoodData("morocco_rfissa", "🍛", "Rfissa", "Lentils and shredded bread", isSpecial = true),
                FoodData("morocco_msemen", "🥞", "Msemen", "Folded flatbread"),
                FoodData("morocco_chebakia", "🥨", "Chebakia", "Sesame cookie"),
                FoodData("morocco_mint_tea", "🍵", "Mint Tea", "Maghrebi tea"),
                FoodData("morocco_briouat", "🥟", "Briouat", "Fried pastry")
            ),
            landmarks = listOf("Jardin Majorelle", "Hassan II Mosque", "Chefchaouen", "Atlas Mountains"),
            bossId = "Souk Sorcerer",
            encyclopedia = moroccoEncyclopedia
        ),
        CountryData(
            id = "kenya",
            displayName = "Kenya",
            nativeName = "Kenya",
            flagEmoji = "🇰🇪",
            isoCode = "KE",
            region = "Africa",
            subregion = "Eastern Africa",
            continent = "Africa",
            language = "Swahili, English",
            population = "53 Million",
            capital = "Nairobi",
            welcomeMessage = "Karibu Kenya!",
            cultureDesc = "Safari heartland and diverse tribes.",
            fact = "Kenya is world-famous for its long-distance runners.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.65f, 0.72f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 70),
            foods = listOf(
                FoodData("kenya_ugali", "🍚", "Ugali", "Cornmeal porridge"),
                FoodData("kenya_sukuma_wiki", "🥬", "Sukuma Wiki", "Collard greens"),
                FoodData("kenya_nyama_choma", "🥩", "Nyama Choma", "Grilled roasted meat"),
                FoodData("kenya_githeri", "🍲", "Githeri", "Maize & bean stew"),
                FoodData("kenya_pilau", "🍛", "Pilau", "Spiced rice"),
                FoodData("kenya_chapati", "🫓", "Chapati", "Soft flatbread"),
                FoodData("kenya_mukimo", "🥘", "Mukimo", "Mashed potatoes & veg", isSpecial = true),
                FoodData("kenya_mandazi", "🍩", "Mandazi", "Fried doughnut"),
                FoodData("kenya_kachumbari", "🥗", "Kachumbari", "Onion & tomato salad"),
                FoodData("kenya_tilapia", "🐟", "Tilapia", "Fried lake fish")
            ),
            landmarks = listOf("Maasai Mara", "Mount Kenya", "Amboseli National Park"),
            bossId = "Savanna Sentinel",
            encyclopedia = kenyaEncyclopedia
        ),
        CountryData(
            id = "ethiopia",
            displayName = "Ethiopia",
            nativeName = "Ityop'iya",
            flagEmoji = "🇪🇹",
            isoCode = "ET",
            region = "Africa",
            subregion = "Eastern Africa",
            continent = "Africa",
            language = "Amharic",
            population = "120 Million",
            capital = "Addis Ababa",
            welcomeMessage = "Inkwan dehna mat'achihu!",
            cultureDesc = "Ancient heritage and coffee's birthplace.",
            fact = "Ethiopia follows a calendar that is 7 years behind the Gregorian calendar.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.68f, 0.68f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 80),
            foods = listOf(
                FoodData("ethiopia_injera", "🫓", "Injera", "Sourdough flatbread"),
                FoodData("ethiopia_doro_wat", "🍲", "Doro Wat", "Spicy chicken stew"),
                FoodData("ethiopia_shiro", "🥣", "Shiro", "Chickpea powder stew"),
                FoodData("ethiopia_tibs", "🥘", "Tibs", "Sautéed meat"),
                FoodData("ethiopia_kitfo", "🥩", "Kitfo", "Minced raw beef"),
                FoodData("ethiopia_misir_wat", "🍛", "Misir Wat", "Red lentil stew"),
                FoodData("ethiopia_genfo", "🥣", "Genfo", "Stiff porridge"),
                FoodData("ethiopia_buna", "☕", "Buna Coffee", "Traditional coffee", isSpecial = true),
                FoodData("ethiopia_beyaynetu", "🥗", "Beyaynetu", "Vegetarian platter"),
                FoodData("ethiopia_kocho", "🍞", "Kocho", "Enset bread")
            ),
            landmarks = listOf("Lalibela", "Simien Mountains", "Axum Obelisks", "Gondar Castle"),
            bossId = "Obelisk Guardian",
            encyclopedia = ethiopiaEncyclopedia
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
            bossId = "Sushi Dragon",
            encyclopedia = japanEncyclopedia
        ),
        CountryData(
            id = "south_korea",
            displayName = "South Korea",
            nativeName = "Daehan Minguk",
            flagEmoji = "🇰🇷",
            isoCode = "KR",
            region = "Asia",
            subregion = "Eastern Asia",
            continent = "Asia",
            language = "Korean",
            population = "51 Million",
            capital = "Seoul",
            welcomeMessage = "Hanguk-e osin geol hwanyeonghamnida!",
            cultureDesc = "Dynamic culture and traditions.",
            fact = "South Korea has the world's fastest average internet speed.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.90f, 0.38f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 100),
            foods = listOf(
                FoodData("south_korea_kimchi", "🥬", "Kimchi", "Fermented vegetables"),
                FoodData("south_korea_bibimbap", "🥗", "Bibimbap", "Mixed rice bowl"),
                FoodData("south_korea_bulgogi", "🥩", "Bulgogi", "Marinated grilled beef"),
                FoodData("south_korea_tteokbokki", "🍢", "Tteokbokki", "Spicy rice cakes"),
                FoodData("south_korea_japchae", "🍝", "Japchae", "Glass noodle stir-fry"),
                FoodData("south_korea_samgyeopsal", "🥩", "Samgyeopsal", "Grilled pork belly"),
                FoodData("south_korea_kimbap", "🍱", "Kimbap", "Seaweed rice rolls"),
                FoodData("south_korea_hottek", "🥞", "Hotteok", "Sweet pancakes", isSpecial = true),
                FoodData("south_korea_sundubu_jjigae", "🍲", "Sundubu-jjigae", "Soft tofu stew"),
                FoodData("south_korea_pajeon", "🥞", "Pajeon", "Scallion pancake")
            ),
            landmarks = listOf("N Seoul Tower", "Gyeongbokgung Palace", "Bukchon Hanok Village"),
            bossId = "Tiger of Korea",
            encyclopedia = southKoreaEncyclopedia
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
            bossId = "Great Wall Dragon",
            encyclopedia = chinaEncyclopedia
        ),
        CountryData(
            id = "vietnam",
            displayName = "Vietnam",
            nativeName = "Việt Nam",
            flagEmoji = "🇻🇳",
            isoCode = "VN",
            region = "Asia",
            subregion = "South-Eastern Asia",
            continent = "Asia",
            language = "Vietnamese",
            population = "98 Million",
            capital = "Hanoi",
            welcomeMessage = "Chào mừng đến với Việt Nam!",
            cultureDesc = "Beautiful landscapes and vibrant food.",
            fact = "Vietnam is the world's second-largest coffee exporter.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.83f, 0.55f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 110),
            foods = listOf(
                FoodData("vietnam_pho", "🍜", "Pho", "Noodle soup"),
                FoodData("vietnam_banh_mi", "🥖", "Banh Mi", "Baguette sandwich"),
                FoodData("vietnam_goi_cuon", "🌯", "Goi Cuon", "Fresh spring rolls"),
                FoodData("vietnam_bun_cha", "🥗", "Bun Cha", "Grilled pork with noodles"),
                FoodData("vietnam_banh_xeo", "🥞", "Banh Xeo", "Savory crepes"),
                FoodData("vietnam_com_tam", "🍛", "Com Tam", "Broken rice"),
                FoodData("vietnam_cao_lau", "🍜", "Cao Lau", "Regional noodles"),
                FoodData("vietnam_che", "🍧", "Che", "Sweet dessert", isSpecial = true),
                FoodData("vietnam_pho_bo", "🍜", "Pho Bo", "Beef noodle soup"),
                FoodData("vietnam_bun_bo_hue", "🍜", "Bun Bo Hue", "Spicy noodle soup")
            ),
            landmarks = listOf("Ha Long Bay", "Hội An", "Imperial City of Huế"),
            bossId = "Monsoon Dragon",
            encyclopedia = vietnamEncyclopedia
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
            bossId = "Elephant King",
            encyclopedia = thailandEncyclopedia
        ),
        CountryData(
            id = "malaysia",
            displayName = "Malaysia",
            nativeName = "Malaysia",
            flagEmoji = "🇲🇾",
            isoCode = "MY",
            region = "Asia",
            subregion = "South-Eastern Asia",
            continent = "Asia",
            language = "Malay",
            population = "33 Million",
            capital = "Kuala Lumpur",
            welcomeMessage = "Selamat datang ke Malaysia!",
            cultureDesc = "Multicultural diversity and flavors.",
            fact = "The Petronas Towers were the world's tallest buildings from 1998 to 2004.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.83f, 0.72f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 120),
            foods = listOf(
                FoodData("malaysia_nasi_lemak", "🍛", "Nasi Lemak", "Coconut milk rice"),
                FoodData("malaysia_laksa", "🍜", "Laksa", "Spicy noodle soup"),
                FoodData("malaysia_char_kway_teow", "🍝", "Char Kway Teow", "Stir-fried flat noodles"),
                FoodData("malaysia_roti_canai", "🫓", "Roti Canai", "Flaky flatbread"),
                FoodData("malaysia_satay", "🍢", "Satay", "Grilled meat skewers"),
                FoodData("malaysia_hainanese_chicken_rice", "🍚", "Chicken Rice", "Poached chicken and rice"),
                FoodData("malaysia_cendol", "🍧", "Cendol", "Sweet iced dessert", isSpecial = true),
                FoodData("malaysia_rendang", "🥘", "Rendang", "Slow-cooked meat"),
                FoodData("malaysia_nasi_kerabu", "🍛", "Nasi Kerabu", "Blue-tinted rice"),
                FoodData("malaysia_teh_tarik", "☕", "Teh Tarik", "Pulled milk tea")
            ),
            landmarks = listOf("Petronas Towers", "Batu Caves", "Mount Kinabalu"),
            bossId = "Rainforest Protector",
            encyclopedia = malaysiaEncyclopedia
        ),
        CountryData(
            id = "indonesia",
            displayName = "Indonesia",
            nativeName = "Indonesia",
            flagEmoji = "🇮🇩",
            isoCode = "ID",
            region = "Asia",
            subregion = "South-Eastern Asia",
            continent = "Asia",
            language = "Indonesian",
            population = "273 Million",
            capital = "Jakarta",
            welcomeMessage = "Selamat datang di Indonesia!",
            cultureDesc = "Archipelago of a thousand cultures.",
            fact = "Indonesia consists of over 17,000 islands.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.85f, 0.80f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 130),
            foods = listOf(
                FoodData("indonesia_nasi_goreng", "🍛", "Nasi Goreng", "Fried rice"),
                FoodData("indonesia_rendang", "🥘", "Rendang", "Spicy beef stew"),
                FoodData("indonesia_satay", "🍢", "Satay", "Meat skewers"),
                FoodData("indonesia_gado_gado", "🥗", "Gado-gado", "Vegetable salad"),
                FoodData("indonesia_soto", "🍲", "Soto Ayam", "Chicken soup"),
                FoodData("indonesia_bakso", "🍲", "Bakso", "Meatball soup"),
                FoodData("indonesia_martabak", "🥞", "Martabak", "Sweet stuffed pancake", isSpecial = true),
                FoodData("indonesia_nasi_padang", "🍛", "Nasi Padang", "Padang rice assortment"),
                FoodData("indonesia_pempek", "🥟", "Pempek", "Savory fish cake"),
                FoodData("indonesia_tempeh", "🥓", "Tempeh", "Fried fermented soybeans")
            ),
            landmarks = listOf("Borobudur", "Prambanan", "Mount Bromo", "Bali"),
            bossId = "Java Guardian",
            encyclopedia = indonesiaEncyclopedia
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
            bossId = "Spice Naga",
            encyclopedia = indiaEncyclopedia
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
            bossId = "Bazaar Genie",
            encyclopedia = turkeyEncyclopedia
        )
    )

    val americasPack = listOf(
        CountryData(
            id = "canada",
            displayName = "Canada",
            nativeName = "Canada",
            flagEmoji = "🇨🇦",
            isoCode = "CA",
            region = "Americas",
            subregion = "Northern America",
            continent = "North America",
            language = "English, French",
            population = "38 Million",
            capital = "Ottawa",
            welcomeMessage = "Welcome to Canada!",
            cultureDesc = "Great white north and maple syrup.",
            fact = "Canada has more lakes than the rest of the world combined.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.15f, 0.20f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 140),
            foods = listOf(
                FoodData("canada_poutine", "🍟", "Poutine", "Fries with gravy"),
                FoodData("canada_maple_syrup", "🍁", "Maple Syrup", "Pure maple sap"),
                FoodData("canada_butter_tart", "🧁", "Butter Tart", "Sweet pastry shell"),
                FoodData("canada_beaver_tails", "🥖", "BeaverTails", "Fried dough pastry"),
                FoodData("canada_nanaimo_bar", "🍫", "Nanaimo Bar", "Chocolate dessert bar"),
                FoodData("canada_montreal_bagel", "🥯", "Montreal Bagel", "Dense sweet bagel"),
                FoodData("canada_tourtiere", "🥧", "Tourtière", "Savory meat pie", isSpecial = true),
                FoodData("canada_split_pea_soup", "🥣", "Split Pea Soup", "Traditional soup"),
                FoodData("canada_bannock", "🍞", "Bannock", "Indigenous flatbread"),
                FoodData("canada_lobster_roll", "🥪", "Lobster Roll", "Seafood roll")
            ),
            landmarks = listOf("Niagara Falls", "CN Tower", "Banff National Park", "Old Quebec"),
            bossId = "Northern Guardian",
            encyclopedia = canadaEncyclopedia
        ),
        CountryData(
            id = "mexico",
            displayName = "Mexico",
            nativeName = "México",
            flagEmoji = "🇲🇽",
            isoCode = "MX",
            region = "Americas",
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
                FoodData("mexico_tamales", "🫔", "Tamales", "Steamed corn dough", isSpecial = true)
            ),
            landmarks = listOf("Chichen Itza", "Teotihuacan", "Palenque", "Cozumel"),
            bossId = "Piñata Titan",
            encyclopedia = mexicoEncyclopedia
        ),
        CountryData(
            id = "peru",
            displayName = "Peru",
            nativeName = "Perú",
            flagEmoji = "🇵🇪",
            isoCode = "PE",
            region = "Americas",
            subregion = "South America",
            continent = "South America",
            language = "Spanish",
            population = "33 Million",
            capital = "Lima",
            welcomeMessage = "¡Bienvenidos al Perú!",
            cultureDesc = "Inca heritage and world-class food.",
            fact = "Peru grows over 3,000 varieties of potatoes.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.22f, 0.68f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 150),
            foods = listOf(
                FoodData("peru_ceviche", "🥗", "Ceviche", "Citrus cured fish"),
                FoodData("peru_lomo_saltado", "🥩", "Lomo Saltado", "Beef stir-fry"),
                FoodData("peru_aji_de_gallina", "🍛", "Ají de Gallina", "Spicy chicken stew"),
                FoodData("peru_cuy_chactado", "🍗", "Cuy Chactado", "Fried guinea pig"),
                FoodData("peru_papas_a_la_huancaina", "🥔", "Papas a la Huancaína", "Potatoes in cheese sauce"),
                FoodData("peru_anticuchos", "🍢", "Anticuchos", "Beef heart skewers", isSpecial = true),
                FoodData("peru_causa_rellena", "🥔", "Causa Rellena", "Layered potato salad"),
                FoodData("peru_rocoto_relleno", "🌶️", "Rocoto Relleno", "Spicy stuffed peppers"),
                FoodData("peru_picarones", "🍩", "Picarones", "Sweet doughnuts"),
                FoodData("peru_chicha_morada", "🥤", "Chicha Morada", "Purple corn drink")
            ),
            landmarks = listOf("Machu Picchu", "Nazca Lines", "Lake Titicaca", "Colca Canyon"),
            bossId = "Sun Temple Guardian",
            encyclopedia = peruEncyclopedia
        ),
        CountryData(
            id = "chile",
            displayName = "Chile",
            nativeName = "Chile",
            flagEmoji = "🇨🇱",
            isoCode = "CL",
            region = "Americas",
            subregion = "South America",
            continent = "South America",
            language = "Spanish",
            population = "19 Million",
            capital = "Santiago",
            welcomeMessage = "¡Bienvenidos a Chile!",
            cultureDesc = "Patagonia peaks and coastal beauty.",
            fact = "Chile is the most earthquake-prone country in the world.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.23f, 0.85f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 160),
            foods = listOf(
                FoodData("chile_empanada", "🥟", "Empanada", "Meat pastry"),
                FoodData("chile_pastel_de_choclo", "🥧", "Pastel de Choclo", "Corn meat pie"),
                FoodData("chile_curanto", "🍲", "Curanto", "Traditional pit-cooked meal"),
                FoodData("chile_humitas", "🌽", "Humitas", "Steamed corn husks"),
                FoodData("chile_cazuela", "🥣", "Cazuela", "Hearty stew"),
                FoodData("chile_completo", "🌭", "Completo", "Loaded hot dog", isSpecial = true),
                FoodData("chile_mote_con_huesillo", "🥤", "Mote con Huesillo", "Peach wheat drink"),
                FoodData("chile_pebre", "🥣", "Pebre", "Spicy condiment"),
                FoodData("chile_caldillo_de_congrio", "🍲", "Caldillo de Congrio", "Eel soup"),
                FoodData("chile_charquican", "🍲", "Charquicán", "Potato mash with meat")
            ),
            landmarks = listOf("Torres del Paine", "Easter Island", "Atacama Desert", "Valparaíso"),
            bossId = "Andes Protector",
            encyclopedia = chileEncyclopedia
        ),
        CountryData(
            id = "argentina",
            displayName = "Argentina",
            nativeName = "Argentina",
            flagEmoji = "🇦🇷",
            isoCode = "AR",
            region = "Americas",
            subregion = "South America",
            continent = "South America",
            language = "Spanish",
            population = "45 Million",
            capital = "Buenos Aires",
            welcomeMessage = "¡Bienvenidos a Argentina!",
            cultureDesc = "Tango, beef, and soccer passion.",
            fact = "Argentina was the first country to use fingerprints for identification.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.28f, 0.82f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 170),
            foods = listOf(
                FoodData("argentina_asado", "🥩", "Asado", "BBQ beef"),
                FoodData("argentina_empanada", "🥟", "Empanada", "Savory pastry"),
                FoodData("argentina_choripan", "🥪", "Choripán", "Chorizo sandwich"),
                FoodData("argentina_milanesa", "🍗", "Milanesa", "Breaded fillet"),
                FoodData("argentina_locro", "🍲", "Locro", "Corn meat stew"),
                FoodData("argentina_alfajores", "🍪", "Alfajores", "Caramel cookies", isSpecial = true),
                FoodData("argentina_dulce_de_leche", "🍯", "Dulce de Leche", "Caramel spread"),
                FoodData("argentina_mate", "🧉", "Mate", "Herbal infusion"),
                FoodData("argentina_chimichurri", "🥣", "Chimichurri", "Garlic herb sauce"),
                FoodData("argentina_fugazzeta", "🍕", "Fugazzeta", "Onion cheese pizza")
            ),
            landmarks = listOf("Iguazu Falls", "Perito Moreno Glacier", "Mount Fitz Roy", "Teatro Colón"),
            bossId = "Patagonia Spirit",
            encyclopedia = argentinaEncyclopedia
        )
    )

    val oceaniaPack = listOf(
        CountryData(
            id = "australia",
            displayName = "Australia",
            nativeName = "Australia",
            flagEmoji = "🇦🇺",
            isoCode = "AU",
            region = "Oceania",
            subregion = "Australia/NZ",
            continent = "Oceania",
            language = "English",
            population = "26 Million",
            capital = "Canberra",
            welcomeMessage = "G'day mate! Welcome to Oz!",
            cultureDesc = "Great Barrier Reef and Outback.",
            fact = "90% of Australians live on the coast.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.85f, 0.82f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 180),
            foods = listOf(
                FoodData("australia_meat_pie", "🥧", "Meat Pie", "Iconic pastry"),
                FoodData("australia_vegemite_toast", "🍞", "Vegemite", "Yeast spread"),
                FoodData("australia_lamington", "🍰", "Lamington", "Coated sponge cake"),
                FoodData("australia_pavlova", "🍨", "Pavlova", "Meringue dessert"),
                FoodData("australia_barramundi", "🐟", "Barramundi", "Grilled sea bass"),
                FoodData("australia_kangaroo_steak", "🥩", "Kangaroo", "Lean meat", isSpecial = true),
                FoodData("australia_anzac_biscuit", "🍪", "Anzac Biscuit", "Oat biscuit"),
                FoodData("australia_damper", "🍞", "Damper", "Soda bread"),
                FoodData("australia_witchetty_grub", "🐛", "Witchetty Grub", "Bush tucker"),
                FoodData("australia_flat_white", "☕", "Flat White", "Espresso drink")
            ),
            landmarks = listOf("Sydney Opera House", "Uluru", "Great Barrier Reef", "The Twelve Apostles"),
            bossId = "Outback Guardian",
            encyclopedia = australiaEncyclopedia
        ),
        CountryData(
            id = "new_zealand",
            displayName = "New Zealand",
            nativeName = "Aotearoa",
            flagEmoji = "🇳🇿",
            isoCode = "NZ",
            region = "Oceania",
            subregion = "Australia/NZ",
            continent = "Oceania",
            language = "English, Māori",
            population = "5 Million",
            capital = "Wellington",
            welcomeMessage = "Kia ora! Welcome to NZ!",
            cultureDesc = "Middle Earth and Māori spirit.",
            fact = "New Zealand was the first country to give women the right to vote.",
            status = ContentStatus.FULL,
            mapLocation = Offset(0.95f, 0.88f),
            mission = MissionConfig(requiredFoods = 10, requiredCoins = 190),
            foods = listOf(
                FoodData("new_zealand_hangi", "🍲", "Hāngī", "Māori pit-cooked meal"),
                FoodData("new_zealand_hokey_pokey", "🍦", "Hokey Pokey", "Honeycomb ice cream"),
                FoodData("new_zealand_pavlova", "🍰", "Pavlova", "Kiwi fruit dessert"),
                FoodData("new_zealand_meat_pie", "🥧", "NZ Meat Pie", "Savory pastry"),
                FoodData("new_zealand_green_mussels", "🦪", "Mussels", "Large native mussels"),
                FoodData("new_zealand_kumara", "🥔", "Kūmara", "Sweet potato", isSpecial = true),
                FoodData("new_zealand_whitebait_fritter", "🍳", "Whitebait Fritter", "Fish omelette"),
                FoodData("new_zealand_anzac_biscuit", "🍪", "ANZAC Biscuit", "Oat biscuit"),
                FoodData("new_zealand_kiwi_fruit", "🥝", "Kiwi Fruit", "Iconic fruit"),
                FoodData("new_zealand_lamington", "🍰", "NZ Lamington", "Raspberry sponge cake")
            ),
            landmarks = listOf("Milford Sound", "Hobbiton", "Waitomo Caves", "Sky Tower"),
            bossId = "Southern Sky Guardian",
            encyclopedia = newZealandEncyclopedia
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

    val allCountries: List<CountryData> = europePack + africaPack + asiaPack + middleEastPack + americasPack + oceaniaPack + comingSoonPack
    private val playableCountriesCache: List<CountryData> = allCountries.filter { it.status != ContentStatus.COMING_SOON }

    fun getCountry(id: String): CountryData? = allCountries.find { it.id == id }
    fun getFirstCountry(): CountryData = allCountries.first { it.id == "germany" }
    
    fun getPlayableCountries(): List<CountryData> = playableCountriesCache

    fun getNextCountry(currentId: String): CountryData {
        val playable = playableCountriesCache
        val index = playable.indexOfFirst { it.id == currentId }
        if (index == -1 || index == playable.size - 1) return playable.first()
        return playable[index + 1]
    }

    fun getPreviousCountry(currentId: String): CountryData {
        val playable = playableCountriesCache
        val index = playable.indexOfFirst { it.id == currentId }
        if (index == -1 || index == 0) return playable.last()
        return playable[index - 1]
    }

    init {
        validateData()
    }

    private fun validateData() {
        val countryIds = mutableSetOf<String>()
        val foodIds = mutableSetOf<String>()

        allCountries.forEach { country ->
            if (country.id.isBlank()) throw IllegalStateException("Blank country ID found")
            if (countryIds.contains(country.id)) throw IllegalStateException("Duplicate country ID: ${country.id}")
            countryIds.add(country.id)

            if (country.status == ContentStatus.FULL && country.encyclopedia == null) {
                throw IllegalStateException("Missing encyclopedia for playable country ${country.id}")
            }

            country.foods.forEach { food ->
                if (food.id.isBlank()) throw IllegalStateException("Blank food ID in ${country.id}")
                if (foodIds.contains(food.id)) throw IllegalStateException("Duplicate food ID: ${food.id}")
                foodIds.add(food.id)
            }
        }
    }
}
