package com.mahmodhota.worldfoodadventure.game

val allSkins = listOf(
    CartSkin("default", "Default", 0, "player_cart"),
    CartSkin("golden", "Golden", 100, "player_cart_gold"),
    CartSkin("chef", "Chef", 200, "player_cart_chef"),
    CartSkin("legendary", "Legendary", 500, "player_cart_gold")
)

val allAchievements = listOf(
    Achievement("first_food", "First Food", "Collect your first food", "🥇", "Bronze"),
    Achievement("coin_collector", "Coin Collector", "100 total coins", "🪙", "Silver"),
    Achievement("healthy_player", "Healthy Player", "Reach 5 hearts", "❤️", "Gold"),
    Achievement("world_traveler", "World Traveler", "Complete all levels", "🌍", "Platinum"),
    Achievement("score_master", "Score Master", "1000 points", "⭐", "Gold"),
    Achievement("master_chef", "Master Chef", "Collect all foods", "👨‍🍳", "Legendary"),
    Achievement("chest_hunter", "Treasure Finder", "Open a reward chest", "📦", "Silver")
)

val allFoods = listOf(
    FoodInfo("italy_pizza", "🍕", "Pizza", "italy", "Classic Neapolitan style", listOf("Dough", "Tomato Sauce", "Mozzarella", "Basil"), "266 kcal"),
    FoodInfo("italy_pasta", "🍝", "Pasta", "italy", "Hand-rolled spaghetti", listOf("Flour", "Eggs", "Water"), "131 kcal"),
    FoodInfo("italy_gelato", "🍦", "Gelato", "italy", "Creamy frozen dessert", listOf("Milk", "Cream", "Sugar", "Flavorings"), "210 kcal"),
    FoodInfo("italy_lobster", "🦞", "Lobster", "italy", "Fresh Mediterranean seafood", listOf("Lobster", "Butter", "Garlic", "Lemon"), "98 kcal"),
    
    FoodInfo("germany_brezel", "🥨", "Pretzel", "germany", "Baked knot-shaped bread", listOf("Flour", "Yeast", "Salt", "Water"), "380 kcal"),
    FoodInfo("germany_wurst", "🌭", "Sausage", "germany", "Traditional grilled bratwurst", listOf("Pork", "Veal", "Spices"), "301 kcal"),
    FoodInfo("germany_cake", "🍰", "Black Forest cherry cake", "germany", "Chocolate", listOf("Chocolate", "Cream", "Cherries"), "450 kcal"),
    FoodInfo("germany_strudel", "🥧", "Pie", "germany", "Warm apple strudel", listOf("Apples", "Pastry", "Cinnamon"), "237 kcal"),
    
    FoodInfo("japan_sushi", "🍣", "Sushi", "japan", "Vinegared rice with seafood", listOf("Rice", "Raw Fish", "Seaweed"), "350 kcal"),
    FoodInfo("japan_ramen", "🍜", "Ramen", "japan", "Noodle soup in savory broth", listOf("Wheat Noodles", "Broth", "Pork/Toppings"), "436 kcal"),
    FoodInfo("japan_onigiri", "🍙", "Onigiri", "japan", "Rice triangle with seaweed", listOf("Rice", "Fillings", "Nori"), "203 kcal"),
    FoodInfo("japan_bento", "🍱", "Bento", "japan", "Traditional boxed lunch", listOf("Rice", "Fish/Meat", "Pickled Veg"), "600 kcal"),

    FoodInfo("france_croissant", "🥐", "Croissant", "france", "Buttery flaky pastry", listOf("Flour", "Butter", "Yeast", "Milk"), "406 kcal"),
    FoodInfo("france_baguette", "🥖", "Baguette", "france", "Classic French bread", listOf("Flour", "Water", "Yeast", "Salt"), "248 kcal"),
    FoodInfo("france_cheese", "🧀", "Cheese", "france", "Fine aged brie", listOf("Cow's Milk", "Cultures", "Rennet"), "334 kcal"),
    FoodInfo("france_macaron", "🍪", "Macaron", "france", "Colorful almond cookie", listOf("Almond Flour", "Egg Whites", "Sugar"), "100 kcal"),
    
    FoodInfo("spain_paella", "🥘", "Paella", "spain", "Traditional rice dish", listOf("Rice", "Saffron", "Seafood/Meat"), "350 kcal"),
    FoodInfo("spain_shrimp", "🍤", "Shrimp", "spain", "Fresh garlic shrimp", listOf("Shrimp", "Garlic", "Olive Oil"), "120 kcal"),
    FoodInfo("spain_wine", "🍷", "Wine", "spain", "Fine Spanish red", listOf("Grapes", "Yeasts"), "83 kcal"),
    FoodInfo("spain_churros", "🥨", "Churros", "spain", "Fried dough with sugar", listOf("Flour", "Water", "Salt", "Sugar"), "450 kcal"),

    FoodInfo("greece_gyro", "🥙", "Gyro", "greece", "Grilled meat in pita", listOf("Pork/Chicken", "Pita", "Tzatziki"), "600 kcal"),
    FoodInfo("greece_salad", "🥗", "Salad", "greece", "Fresh Greek salad", listOf("Tomatoes", "Cucumber", "Olives", "Feta"), "200 kcal"),
    FoodInfo("greece_feta", "🧀", "Feta", "greece", "Traditional sheep cheese", listOf("Sheep's Milk", "Brine"), "264 kcal"),
    FoodInfo("greece_baklava", "🍰", "Baklava", "greece", "Sweet honey pastry", listOf("Filo Pastry", "Nuts", "Honey"), "334 kcal"),

    FoodInfo("mexico_taco", "🌮", "Taco", "mexico", "Corn tortilla with filling", listOf("Tortilla", "Meat", "Onions", "Cilantro"), "156 kcal"),
    FoodInfo("mexico_burrito", "🌯", "Burrito", "mexico", "Large flour wrap", listOf("Flour Tortilla", "Beans", "Rice", "Meat"), "206 kcal"),
    FoodInfo("mexico_corn", "🌽", "Corn", "mexico", "Grilled street corn", listOf("Corn", "Mayo", "Chili", "Lime"), "106 kcal"),
    FoodInfo("mexico_tamales", "🫔", "Tamales", "mexico", "Steamed corn dough", listOf("Masa", "Fillings", "Corn Husk"), "285 kcal"),

    FoodInfo("india_curry", "🍛", "Curry", "india", "Spiced vegetable curry", listOf("Vegetables", "Spices", "Coconut Milk"), "325 kcal"),
    FoodInfo("india_biryani", "🥘", "Biryani", "india", "Fragrant basmati rice", listOf("Basmati Rice", "Meat", "Spices"), "482 kcal"),
    FoodInfo("india_naan", "🥯", "Naan", "india", "Oven-baked flatbread", listOf("Flour", "Yeast", "Yogurt"), "262 kcal"),
    FoodInfo("india_samosa", "🍘", "Samosa", "india", "Fried savory pastry", listOf("Pastry", "Potatoes", "Peas", "Spices"), "262 kcal"),

    FoodInfo("china_dumpling", "🥟", "Dumpling", "china", "Steamed pork dumpling", listOf("Dough", "Pork", "Ginger", "Chives"), "41 kcal"),
    FoodInfo("china_takeout", "🥡", "Takeout", "china", "Stir-fried noodles", listOf("Noodles", "Vegetables", "Soy Sauce"), "400 kcal"),
    FoodInfo("china_chopsticks", "🥢", "Chopsticks", "china", "Traditional utensils", listOf("Wood/Bamboo"), "0 kcal"),
    FoodInfo("china_mooncake", "🥮", "Mooncake", "china", "Traditional festival cake", listOf("Lotus Seed Paste", "Egg Yolk"), "400 kcal"),

    FoodInfo("thailand_soup", "🍲", "Soup", "thailand", "Spicy Tom Yum", listOf("Shrimp", "Lemongrass", "Chili"), "150 kcal"),
    FoodInfo("thailand_padthai", "🍛", "Pad Thai", "thailand", "Stir-fried rice noodles", listOf("Rice Noodles", "Tofu/Shrimp", "Peanuts"), "357 kcal"),
    FoodInfo("thailand_coconut", "🥥", "Coconut", "thailand", "Fresh tropical coconut", listOf("Coconut Water/Meat"), "354 kcal"),
    FoodInfo("thailand_mango", "🥭", "Mango", "thailand", "Mango sticky rice", listOf("Mango", "Sticky Rice", "Coconut Milk"), "160 kcal"),

    FoodInfo("turkey_kebab", "🥙", "Kebab", "turkey", "Grilled meat skewer", listOf("Lamb/Beef", "Spices", "Vegetables"), "200 kcal"),
    FoodInfo("turkey_kofte", "🥘", "Kofte", "turkey", "Turkish meatballs", listOf("Ground Meat", "Breadcrumbs", "Spices"), "250 kcal"),
    FoodInfo("turkey_tea", "🍵", "Tea", "turkey", "Strong black tea", listOf("Tea Leaves", "Water"), "2 kcal"),
    FoodInfo("turkey_delight", "🍬", "Delight", "turkey", "Sweet Turkish delight", listOf("Sugar", "Starch", "Nuts/Fruits"), "300 kcal"),

    FoodInfo("brazil_steak", "🥩", "Steak", "brazil", "Grilled picanha steak", listOf("Beef", "Rock Salt"), "250 kcal"),
    FoodInfo("brazil_feijoada", "🍲", "Feijoada", "brazil", "Black bean stew", listOf("Black Beans", "Pork", "Beef"), "350 kcal"),
    FoodInfo("brazil_cocktail", "🍹", "Cocktail", "brazil", "Fresh caipirinha", listOf("Cachaça", "Lime", "Sugar"), "250 kcal"),
    FoodInfo("brazil_coxinha", "🍗", "Coxinha", "brazil", "Chicken croquette", listOf("Dough", "Shredded Chicken", "Spices"), "250 kcal"),

    FoodInfo("usa_burger", "🍔", "Burger", "usa", "Juicy beef burger", listOf("Beef Patty", "Bun", "Lettuce", "Tomato"), "250 kcal"),
    FoodInfo("usa_fries", "🍟", "Fries", "usa", "Crispy golden fries", listOf("Potatoes", "Oil", "Salt"), "312 kcal"),
    FoodInfo("usa_hotdog", "🌭", "Hot Dog", "usa", "Classic ball-park snack", listOf("Sausage", "Bun", "Mustard"), "290 kcal"),
    FoodInfo("usa_doughnut", "🍩", "Doughnut", "usa", "Sweet glazed ring", listOf("Flour", "Sugar", "Glaze"), "190 kcal"),

    FoodInfo("sudan_bamia", "🥘", "Bamia", "sudan", "Okra stew with meat", listOf("Okra", "Lamb", "Tomato Paste"), "220 kcal"),
    FoodInfo("sudan_kisra", "🍞", "Kisra", "sudan", "Thin fermented bread", listOf("Sorghum Flour", "Water"), "150 kcal"),
    FoodInfo("sudan_ful", "🥜", "Ful Medames", "sudan", "Cooked fava beans", listOf("Fava Beans", "Oil", "Cumin"), "110 kcal"),
    FoodInfo("sudan_shaiya", "🍖", "Shaiya", "sudan", "Grilled seasoned lamb", listOf("Lamb", "Onions", "Spices"), "250 kcal"),
    FoodInfo("sudan_mulah", "🍛", "Mulah", "sudan", "Traditional meat stew", listOf("Meat", "Onions", "Yogurt/Milk"), "300 kcal"),
    FoodInfo("sudan_gurrasa", "🥟", "Gurrasa", "sudan", "Soft wheat flatbread", listOf("Wheat Flour", "Yeast", "Water"), "200 kcal")
)

val allBossesById = mapOf(
    "italy" to Boss("Pizza Monster", "🍕👹", 5, "italy", listOf("Tomato Splat", "Dough Slam")),
    "germany" to Boss("Pretzel King", "🥨👑", 8, "germany", listOf("Salt Rain", "Twist Crush")),
    "japan" to Boss("Sushi Dragon", "🍣🐉", 10, "japan", listOf("Wasabi Breath", "Rice Roll")),
    "france" to Boss("Cheese Phantom", "🧀👻", 12, "france", listOf("Brie Mist", "Stinky Trap")),
    "spain" to Boss("Bull General", "🐂🤺", 15, "spain", listOf("Charging Horns", "Paella Bomb")),
    "greece" to Boss("Medusa Chef", "🐍🍳", 15, "greece", listOf("Stone Olives", "Feta Freeze")),
    "mexico" to Boss("Piñata Titan", "🪅🥊", 18, "mexico", listOf("Candy Burst", "Confetti Punch")),
    "india" to Boss("Spice Naga", "🐍🌶️", 20, "india", listOf("Curry Blast", "Pepper Sting")),
    "china" to Boss("Great Wall Dragon", "🐉🐲", 25, "china", listOf("Firecracker", "Jade Wall")),
    "thailand" to Boss("Elephant King", "🐘🐘", 20, "thailand", listOf("Water Trunk", "Heavy Stomp")),
    "turkey" to Boss("Bazaar Genie", "🧞‍♂️🏺", 22, "turkey", listOf("Lamp Smoke", "Carpet Flight")),
    "brazil" to Boss("Carnival Colossus", "🎭🥁", 25, "brazil", listOf("Drum Wave", "Feather Gust")),
    "usa" to Boss("Burger Behemoth", "🍔🌋", 30, "usa", listOf("Cheese Lava", "Pickle Shot")),
    "sudan" to Boss("Desert Guardian", "🛡️🏜️", 260, "sudan", listOf("Sand Storm", "Ancient Spear"))
)

val allRanks = listOf(
    PlayerRank(1, "Home Cook", 0),
    PlayerRank(2, "Street Chef", 500),
    PlayerRank(3, "Restaurant Chef", 1200),
    PlayerRank(4, "Master Chef", 2500),
    PlayerRank(5, "World Chef", 4500),
    PlayerRank(6, "Legend Chef", 7000),
    PlayerRank(7, "Food Explorer", 10000),
    PlayerRank(8, "Grand Master", 14000),
    PlayerRank(9, "World Champion", 19000),
    PlayerRank(10, "Legend of Food", 25000)
)
