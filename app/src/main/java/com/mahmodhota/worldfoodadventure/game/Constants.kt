package com.mahmodhota.worldfoodadventure.game

import androidx.compose.ui.unit.dp

val BOTTOM_AD_SAFE_ZONE_DP = 60.dp
val PLAYER_CART_SIZE_DP = 150.dp
val CART_BOTTOM_GAP_DP = 12.dp

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
    FoodInfo("sudan_gurrasa", "🥟", "Gurrasa", "sudan", "Soft wheat flatbread", listOf("Wheat Flour", "Yeast", "Water"), "200 kcal"),

    // UNITED KINGDOM (Phase 1)
    FoodInfo("uk_fish_and_chips", "🐟", "Fish and Chips", "uk", "Classic battered cod with thick-cut fries", listOf("Cod", "Potatoes", "Flour", "Beer/Water", "Oil"), "595 kcal"),
    FoodInfo("uk_shepherds_pie", "🥧", "Shepherd's Pie", "uk", "Minced lamb topped with mashed potatoes", listOf("Lamb", "Potatoes", "Carrots", "Onions", "Peas"), "350 kcal"),
    FoodInfo("uk_sunday_roast", "🥩", "Sunday Roast", "uk", "Roasted meat with Yorkshire pudding and veg", listOf("Beef/Lamb", "Flour", "Eggs", "Potatoes", "Gravy"), "450 kcal"),
    FoodInfo("uk_cornish_pasty", "🥟", "Cornish Pasty", "uk", "Baked pastry with beef and vegetables", listOf("Flour", "Beef", "Potato", "Swede", "Onion"), "375 kcal"),
    FoodInfo("uk_afternoon_tea", "☕", "Afternoon Tea", "uk", "Tea served with scones and clotted cream", listOf("Black Tea", "Flour", "Cream", "Jam", "Butter"), "250 kcal"),
    FoodInfo("uk_beef_wellington", "🥩", "Beef Wellington", "uk", "Beef fillet coated in pâté and puff pastry", listOf("Beef Fillet", "Mushrooms", "Puff Pastry", "Prosciutto"), "420 kcal"),
    FoodInfo("uk_haggis", "🍲", "Haggis", "uk", "Traditional Scottish savory pudding", listOf("Sheep's Pluck", "Oatmeal", "Onions", "Suet", "Spices"), "300 kcal"),
    FoodInfo("uk_welsh_rarebit", "🍞", "Welsh Rarebit", "uk", "Savory cheese sauce on toasted bread", listOf("Cheddar", "Ale", "Mustard", "Bread"), "320 kcal"),
    FoodInfo("uk_eton_mess", "🍨", "Eton Mess", "uk", "Meringue, strawberries, and whipped cream", listOf("Strawberries", "Meringue", "Double Cream"), "280 kcal"),
    FoodInfo("uk_full_english", "🍳", "Full English Breakfast", "uk", "Classic cooked breakfast with eggs and beans", listOf("Eggs", "Bacon", "Sausage", "Beans", "Mushrooms"), "600 kcal"),

    // NETHERLANDS (Phase 1)
    FoodInfo("netherlands_stroopwafel", "🧇", "Stroopwafel", "netherlands", "Waffle with caramel-like syrup filling", listOf("Flour", "Butter", "Sugar", "Syrup", "Cinnamon"), "450 kcal"),
    FoodInfo("netherlands_herring", "🐟", "Herring", "netherlands", "Traditional raw herring with onions", listOf("Herring", "Onions", "Pickles"), "160 kcal"),
    FoodInfo("netherlands_bitterballen", "🧆", "Bitterballen", "netherlands", "Deep-fried meat-based snack", listOf("Beef/Veal", "Butter", "Flour", "Breadcrumbs"), "280 kcal"),
    FoodInfo("netherlands_gouda_cheese", "🧀", "Gouda Cheese", "netherlands", "Famous Dutch mild cheese", listOf("Cow's Milk", "Cultures", "Rennet"), "350 kcal"),
    FoodInfo("netherlands_poffertjes", "🥞", "Poffertjes", "netherlands", "Mini fluffy buckwheat pancakes", listOf("Buckwheat Flour", "Yeast", "Milk", "Butter"), "220 kcal"),
    FoodInfo("netherlands_stamppot", "🍲", "Stamppot", "netherlands", "Mashed potatoes with vegetables and sausage", listOf("Potatoes", "Kale/Carrots", "Sausage"), "380 kcal"),
    FoodInfo("netherlands_erwtensoep", "🥣", "Erwtensoep", "netherlands", "Thick split pea soup", listOf("Split Peas", "Pork", "Celery", "Leeks"), "310 kcal"),
    FoodInfo("netherlands_kibbeling", "🐟", "Kibbeling", "netherlands", "Battered and fried white fish pieces", listOf("Cod/Pollock", "Batter", "Spices"), "340 kcal"),
    FoodInfo("netherlands_tompouce", "🍰", "Tompouce", "netherlands", "Pastry with cream and pink icing", listOf("Puff Pastry", "Custard", "Icing"), "320 kcal"),
    FoodInfo("netherlands_hagelslag", "🍞", "Hagelslag", "netherlands", "Chocolate sprinkles on buttered bread", listOf("Bread", "Butter", "Chocolate Sprinkles"), "210 kcal"),

    // PORTUGAL (Phase 1)
    FoodInfo("portugal_pastel_de_nata", "🧁", "Pastel de Nata", "portugal", "Creamy custard tart with cinnamon", listOf("Flour", "Sugar", "Milk", "Egg Yolks", "Cinnamon"), "290 kcal"),
    FoodInfo("portugal_bacalhau", "🐟", "Bacalhau à Brás", "portugal", "Shredded cod with eggs and potatoes", listOf("Salted Cod", "Eggs", "Potatoes", "Olives"), "350 kcal"),
    FoodInfo("portugal_caldo_verde", "🥣", "Caldo Verde", "portugal", "Traditional green kale soup", listOf("Kale", "Potatoes", "Chouriço", "Olive Oil"), "180 kcal"),
    FoodInfo("portugal_sardinhas", "🐟", "Sardinhas Assadas", "portugal", "Grilled fresh sardines", listOf("Sardines", "Coarse Salt"), "210 kcal"),
    FoodInfo("portugal_francesinha", "🥪", "Francesinha", "portugal", "Rich meat sandwich with beer sauce", listOf("Bread", "Steak", "Ham", "Sausage", "Cheese"), "900 kcal"),
    FoodInfo("portugal_polvo", "🐙", "Polvo à Lagareiro", "portugal", "Roasted octopus with potatoes", listOf("Octopus", "Potatoes", "Garlic", "Olive Oil"), "420 kcal"),
    FoodInfo("portugal_alheira", "🌭", "Alheira", "portugal", "Portuguese bread sausage", listOf("Poultry", "Bread", "Garlic", "Paprika"), "330 kcal"),
    FoodInfo("portugal_arroz_de_pato", "🥘", "Arroz de Pato", "portugal", "Oven-baked duck rice", listOf("Duck", "Rice", "Chouriço", "Onions"), "480 kcal"),
    FoodInfo("portugal_bifana", "🥪", "Bifana", "portugal", "Marinated pork sandwich", listOf("Pork", "Bread", "Garlic", "White Wine"), "450 kcal"),
    FoodInfo("portugal_queijada", "🧁", "Queijada de Sintra", "portugal", "Small sweet cheese tart", listOf("Cheese", "Sugar", "Eggs", "Flour"), "240 kcal"),

    // AUSTRIA (Phase 1)
    FoodInfo("austria_wiener_schnitzel", "🥩", "Wiener Schnitzel", "austria", "Breaded and fried veal cutlet", listOf("Veal", "Flour", "Eggs", "Breadcrumbs"), "450 kcal"),
    FoodInfo("austria_sachertorte", "🍰", "Sachertorte", "austria", "Rich chocolate cake with apricot jam", listOf("Chocolate", "Apricot Jam", "Butter", "Eggs"), "380 kcal"),
    FoodInfo("austria_apfelstrudel", "🥧", "Apfelstrudel", "austria", "Thin pastry filled with apples", listOf("Apples", "Pastry", "Cinnamon", "Raisins"), "237 kcal"),
    FoodInfo("austria_knodel", "🥟", "Knödel", "austria", "Savory potato or bread dumplings", listOf("Potatoes/Bread", "Flour", "Eggs"), "210 kcal"),
    FoodInfo("austria_kaiserschmarrn", "🥞", "Kaiserschmarrn", "austria", "Shredded pancake with plum compote", listOf("Flour", "Milk", "Eggs", "Sugar", "Plums"), "420 kcal"),
    FoodInfo("austria_tafelspitz", "🥩", "Tafelspitz", "austria", "Boiled beef in broth with horseradish", listOf("Beef", "Root Vegetables", "Horseradish"), "320 kcal"),
    FoodInfo("austria_goulash", "🥘", "Austrian Goulash", "austria", "Slow-cooked beef and onion stew", listOf("Beef", "Onions", "Paprika"), "410 kcal"),
    FoodInfo("austria_linzer_torte", "🥧", "Linzer Torte", "austria", "Nutty pastry with raspberry jam", listOf("Hazelnuts", "Raspberry Jam", "Flour", "Butter"), "340 kcal"),
    FoodInfo("austria_backhendl", "🍗", "Backhendl", "austria", "Austrian breaded fried chicken", listOf("Chicken", "Flour", "Eggs", "Breadcrumbs"), "520 kcal"),
    FoodInfo("austria_tiroler_grostl", "🍳", "Tiroler Gröstl", "austria", "Potato and meat fry-up", listOf("Potatoes", "Bacon/Beef", "Onions", "Fried Egg"), "450 kcal"),

    // SWITZERLAND (Phase 1)
    FoodInfo("switzerland_fondue", "🫕", "Cheese Fondue", "switzerland", "Melted cheese served in a pot", listOf("Gruyère", "Emmental", "White Wine", "Garlic"), "550 kcal"),
    FoodInfo("switzerland_rosti", "🥔", "Rösti", "switzerland", "Crispy grated potato pancake", listOf("Potatoes", "Butter", "Salt"), "310 kcal"),
    FoodInfo("switzerland_chocolate", "🍫", "Swiss Chocolate", "switzerland", "World-renowned smooth chocolate", listOf("Cocoa", "Milk", "Sugar"), "530 kcal"),
    FoodInfo("switzerland_raclette", "🧀", "Raclette", "switzerland", "Melted cheese scraped over potatoes", listOf("Raclette Cheese", "Potatoes", "Pickles"), "480 kcal"),
    FoodInfo("switzerland_muesli", "🥣", "Birchermüesli", "switzerland", "Oat-based breakfast with fruit", listOf("Oats", "Apples", "Nuts", "Yogurt", "Honey"), "290 kcal"),
    FoodInfo("switzerland_geschnetzeltes", "🥘", "Zürcher Geschnetzeltes", "switzerland", "Veal strips in mushroom cream sauce", listOf("Veal", "Mushrooms", "Cream", "White Wine"), "450 kcal"),
    FoodInfo("switzerland_alplermagronen", "🍝", "Älplermagronen", "switzerland", "Alpine macaroni with cheese and onions", listOf("Pasta", "Potatoes", "Cheese", "Onions", "Cream"), "580 kcal"),
    FoodInfo("switzerland_capuns", "🥟", "Capuns", "switzerland", "Swiss chard wraps with dough", listOf("Swiss Chard", "Dough", "Dried Meat", "Cream"), "390 kcal"),
    FoodInfo("switzerland_nusstorte", "🥧", "Nusstorte", "switzerland", "Engadine nut tart with caramel", listOf("Walnuts", "Sugar", "Cream", "Pastry"), "420 kcal"),
    FoodInfo("switzerland_landjager", "🌭", "Landjäger", "switzerland", "Semi-dried sausage snack", listOf("Beef", "Pork", "Lard", "Sugar", "Spices"), "210 kcal"),

    // EGYPT (Phase 2)
    FoodInfo("egypt_koshary", "🍛", "Koshary", "egypt", "Traditional mix of lentils, rice, and pasta with tomato sauce", listOf("Lentils", "Rice", "Pasta", "Tomato Sauce", "Crispy Onions"), "450 kcal"),
    FoodInfo("egypt_ful_medames", "🥘", "Ful Medames", "egypt", "Slow-cooked fava beans with olive oil and cumin", listOf("Fava Beans", "Garlic", "Lemon", "Olive Oil", "Cumin"), "110 kcal"),
    FoodInfo("egypt_molokhia", "🥣", "Molokhia", "egypt", "Jute leaf soup with garlic and coriander", listOf("Jute Leaves", "Broth", "Garlic", "Coriander"), "150 kcal"),
    FoodInfo("egypt_fatta", "🥘", "Fatta", "egypt", "Layered dish of rice, toasted bread, and meat with vinegar-garlic sauce", listOf("Rice", "Bread", "Meat", "Vinegar", "Garlic"), "520 kcal"),
    FoodInfo("egypt_hawawshi", "🥙", "Hawawshi", "egypt", "Pita bread stuffed with minced meat and spices", listOf("Pita Bread", "Minced Meat", "Onions", "Chili"), "380 kcal"),
    FoodInfo("egypt_taameya", "🧆", "Taameya", "egypt", "Egyptian falafel made with fava beans", listOf("Fava Beans", "Parsley", "Coriander", "Onions"), "160 kcal"),
    FoodInfo("egypt_basbousa", "🍰", "Basbousa", "egypt", "Sweet semolina cake soaked in syrup", listOf("Semolina", "Sugar", "Syrup", "Almonds"), "320 kcal"),
    FoodInfo("egypt_kunafa", "🥧", "Kunafa", "egypt", "Shredded pastry with cheese or cream and syrup", listOf("Shredded Dough", "Cream/Cheese", "Syrup"), "400 kcal"),
    FoodInfo("egypt_om_ali", "🍲", "Om Ali", "egypt", "Traditional Egyptian bread pudding", listOf("Puff Pastry", "Milk", "Nuts", "Raisins", "Sugar"), "350 kcal"),
    FoodInfo("egypt_kofta", "🍢", "Kofta", "egypt", "Grilled minced meat skewers", listOf("Minced Meat", "Onions", "Spices"), "250 kcal"),

    // MOROCCO (Phase 2)
    FoodInfo("morocco_tagine", "🥘", "Tagine", "morocco", "Slow-cooked stew named after the earthenware pot", listOf("Meat/Veg", "Spices", "Dried Fruits", "Olives"), "350 kcal"),
    FoodInfo("morocco_couscous", "🍛", "Couscous", "morocco", "Steamed semolina served with meat and vegetables", listOf("Semolina", "Meat", "Carrots", "Zucchini", "Chickpeas"), "400 kcal"),
    FoodInfo("morocco_harira", "🥣", "Harira", "morocco", "Tomato-based soup with lentils and chickpeas", listOf("Tomatoes", "Lentils", "Chickpeas", "Coriander", "Ginger"), "210 kcal"),
    FoodInfo("morocco_pastilla", "🥧", "Pastilla", "morocco", "Savory-sweet meat pie in flaky pastry", listOf("Filo Pastry", "Meat", "Almonds", "Sugar", "Cinnamon"), "450 kcal"),
    FoodInfo("morocco_zaalouk", "🥗", "Zaalouk", "morocco", "Cooked eggplant and tomato salad", listOf("Eggplant", "Tomatoes", "Garlic", "Spices"), "120 kcal"),
    FoodInfo("morocco_rfissa", "🍛", "Rfissa", "morocco", "Shredded bread with chicken, lentils, and fenugreek", listOf("Lentil Stew", "Chicken", "Trid Bread", "Fenugreek"), "550 kcal"),
    FoodInfo("morocco_msemen", "🥞", "Msemen", "morocco", "Square-shaped folded flatbread", listOf("Flour", "Semolina", "Butter", "Honey"), "280 kcal"),
    FoodInfo("morocco_chebakia", "🥨", "Chebakia", "morocco", "Honey-coated sesame cookie", listOf("Sesame", "Flour", "Honey", "Anise"), "300 kcal"),
    FoodInfo("morocco_mint_tea", "🍵", "Mint Tea", "morocco", "Traditional green tea with fresh mint and sugar", listOf("Green Tea", "Mint", "Sugar"), "2 kcal"),
    FoodInfo("morocco_briouat", "🥟", "Briouat", "morocco", "Fried triangular savory or sweet pastry", listOf("Filo Pastry", "Meat/Almonds", "Spices/Honey"), "240 kcal"),

    // KENYA (Phase 2)
    FoodInfo("kenya_ugali", "🍚", "Ugali", "kenya", "Sturdy maize flour porridge", listOf("Maize Flour", "Water"), "150 kcal"),
    FoodInfo("kenya_sukuma_wiki", "🥬", "Sukuma Wiki", "kenya", "Braised collard greens with onions and tomatoes", listOf("Collard Greens", "Onions", "Tomatoes", "Oil"), "80 kcal"),
    FoodInfo("kenya_nyama_choma", "🥩", "Nyama Choma", "kenya", "Grilled roasted meat, a national favorite", listOf("Beef/Goat", "Salt"), "250 kcal"),
    FoodInfo("kenya_githeri", "🍲", "Githeri", "kenya", "Traditional maize and bean stew", listOf("Maize", "Beans", "Vegetables"), "220 kcal"),
    FoodInfo("kenya_pilau", "🍛", "Pilau", "kenya", "Spiced rice dish influenced by coastal trade", listOf("Rice", "Spices", "Meat"), "350 kcal"),
    FoodInfo("kenya_chapati", "🫓", "Chapati", "kenya", "Soft, layered flatbread", listOf("Wheat Flour", "Water", "Oil"), "260 kcal"),
    FoodInfo("kenya_mukimo", "🥘", "Mukimo", "kenya", "Mashed potatoes, maize, beans, and greens", listOf("Potatoes", "Maize", "Beans", "Pumpkin Leaves"), "300 kcal"),
    FoodInfo("kenya_mandazi", "🍩", "Mandazi", "kenya", "Fried dough snack, similar to doughnuts", listOf("Flour", "Milk", "Sugar", "Coconut Milk"), "210 kcal"),
    FoodInfo("kenya_kachumbari", "🥗", "Kachumbari", "kenya", "Fresh onion and tomato salad", listOf("Tomatoes", "Onions", "Chili", "Coriander"), "45 kcal"),
    FoodInfo("kenya_tilapia", "🐟", "Fried Tilapia", "kenya", "Fresh fish from Lake Victoria", listOf("Tilapia", "Oil", "Lemon"), "180 kcal"),

    // ETHIOPIA (Phase 2)
    FoodInfo("ethiopia_injera", "🫓", "Injera", "ethiopia", "Sourdough flatbread that serves as a base for many dishes", listOf("Teff Flour", "Water"), "150 kcal"),
    FoodInfo("ethiopia_doro_wat", "🍲", "Doro Wat", "ethiopia", "Rich and spicy chicken stew with boiled eggs", listOf("Chicken", "Berbere", "Onions", "Eggs"), "420 kcal"),
    FoodInfo("ethiopia_shiro", "🥣", "Shiro", "ethiopia", "Thick chickpea or bean powder stew", listOf("Chickpea Flour", "Spices", "Onions"), "180 kcal"),
    FoodInfo("ethiopia_tibs", "🥘", "Tibs", "ethiopia", "Sautéed meat chunks with onions and peppers", listOf("Beef/Lamb", "Peppers", "Onions", "Spices"), "350 kcal"),
    FoodInfo("ethiopia_kitfo", "🥩", "Kitfo", "ethiopia", "Minced raw beef marinated in spices and butter", listOf("Beef", "Niter Kibbeh", "Mitmita"), "450 kcal"),
    FoodInfo("ethiopia_misir_wat", "🍛", "Misir Wat", "ethiopia", "Spicy red lentil stew", listOf("Red Lentils", "Berbere", "Onions"), "160 kcal"),
    FoodInfo("ethiopia_genfo", "🥣", "Genfo", "ethiopia", "Stiff porridge served with a spicy butter dip", listOf("Barley/Wheat Flour", "Niter Kibbeh", "Berbere"), "380 kcal"),
    FoodInfo("ethiopia_buna", "☕", "Buna Coffee", "ethiopia", "Traditional Ethiopian coffee ceremony", listOf("Coffee Beans", "Water", "Frankincense"), "2 kcal"),
    FoodInfo("ethiopia_beyaynetu", "🥗", "Beyaynetu", "ethiopia", "Colorful vegetarian platter served on injera", listOf("Injera", "Various Vegetable Wat"), "400 kcal"),
    FoodInfo("ethiopia_kocho", "🍞", "Kocho", "ethiopia", "Bread-like food made from fermented Enset", listOf("Enset Plant", "Fermentation"), "210 kcal"),

    // SOUTH KOREA (Phase 3)
    FoodInfo("south_korea_kimchi", "🥬", "Kimchi", "south_korea", "Traditional fermented vegetables, usually cabbage", listOf("Napa Cabbage", "Radish", "Chili Powder", "Garlic", "Ginger"), "15 kcal"),
    FoodInfo("south_korea_bibimbap", "🥗", "Bibimbap", "south_korea", "Mixed rice with vegetables, beef, and egg", listOf("Rice", "Sautéed Vegetables", "Gochujang", "Beef", "Egg"), "560 kcal"),
    FoodInfo("south_korea_bulgogi", "🥩", "Bulgogi", "south_korea", "Thinly sliced marinated grilled beef", listOf("Beef", "Soy Sauce", "Sugar", "Sesame Oil", "Garlic"), "310 kcal"),
    FoodInfo("south_korea_tteokbokki", "🍢", "Tteokbokki", "south_korea", "Simmered rice cakes in spicy gochujang sauce", listOf("Rice Cakes", "Gochujang", "Sugar", "Fish Cakes"), "350 kcal"),
    FoodInfo("south_korea_japchae", "🍝", "Japchae", "south_korea", "Stir-fried glass noodles with vegetables", listOf("Sweet Potato Starch Noodles", "Vegetables", "Beef", "Soy Sauce"), "180 kcal"),
    FoodInfo("south_korea_samgyeopsal", "🥩", "Samgyeopsal", "south_korea", "Grilled pork belly slices", listOf("Pork Belly", "Garlic", "Onion", "Sesame Oil"), "510 kcal"),
    FoodInfo("south_korea_kimbap", "🍱", "Kimbap", "south_korea", "Cooked rice and ingredients rolled in dried seaweed", listOf("Rice", "Dried Seaweed", "Vegetables", "Ham/Egg"), "320 kcal"),
    FoodInfo("south_korea_hottek", "🥞", "Hotteok", "south_korea", "Sweet pancake with cinnamon and nut filling", listOf("Flour", "Brown Sugar", "Cinnamon", "Walnuts"), "230 kcal"),
    FoodInfo("south_korea_sundubu_jjigae", "🍲", "Sundubu-jjigae", "south_korea", "Soft tofu stew with vegetables and chili", listOf("Soft Tofu", "Chili Powder", "Vegetables", "Egg"), "150 kcal"),
    FoodInfo("south_korea_pajeon", "🥞", "Pajeon", "south_korea", "Savory pancake with green onions", listOf("Flour", "Green Onions", "Eggs", "Seafood/Meat"), "240 kcal"),

    // VIETNAM (Phase 3)
    FoodInfo("vietnam_pho", "🍜", "Pho", "vietnam", "Vietnamese noodle soup with broth and herbs", listOf("Rice Noodles", "Beef/Chicken", "Broth", "Herbs", "Bean Sprouts"), "350 kcal"),
    FoodInfo("vietnam_banh_mi", "🥖", "Banh Mi", "vietnam", "French-style baguette with savory Vietnamese fillings", listOf("Baguette", "Pâté", "Meat", "Pickled Vegetables", "Cilantro"), "400 kcal"),
    FoodInfo("vietnam_goi_cuon", "🌯", "Goi Cuon", "vietnam", "Fresh spring rolls wrapped in rice paper", listOf("Rice Paper", "Shrimp/Pork", "Rice Vermicelli", "Herbs"), "120 kcal"),
    FoodInfo("vietnam_bun_cha", "🥗", "Bun Cha", "vietnam", "Grilled pork with rice vermicelli noodles", listOf("Grilled Pork", "Rice Noodles", "Dipping Sauce", "Herbs"), "450 kcal"),
    FoodInfo("vietnam_banh_xeo", "🥞", "Banh Xeo", "vietnam", "Crispy savory crepes filled with shrimp and pork", listOf("Rice Flour", "Turmeric", "Shrimp", "Pork", "Bean Sprouts"), "320 kcal"),
    FoodInfo("vietnam_com_tam", "🍛", "Com Tam", "vietnam", "Broken rice served with grilled pork", listOf("Broken Rice", "Grilled Pork", "Egg", "Vegetables"), "500 kcal"),
    FoodInfo("vietnam_cao_lau", "🍜", "Cao Lau", "vietnam", "Regional noodle dish from Hoi An", listOf("Cao Lau Noodles", "Pork", "Croutons", "Herbs"), "380 kcal"),
    FoodInfo("vietnam_che", "🍧", "Che", "vietnam", "Sweet dessert soup or pudding", listOf("Beans/Fruit", "Coconut Milk", "Sugar", "Jelly"), "250 kcal"),
    FoodInfo("vietnam_pho_bo", "🍜", "Pho Bo", "vietnam", "Beef noodle soup, a classic variation", listOf("Rice Noodles", "Beef Fillet", "Cinnamon", "Star Anise"), "360 kcal"),
    FoodInfo("vietnam_bun_bo_hue", "🍜", "Bun Bo Hue", "vietnam", "Spicy beef and pork noodle soup", listOf("Rice Vermicelli", "Beef", "Pork", "Lemongrass", "Chili"), "420 kcal"),

    // INDONESIA (Phase 3)
    FoodInfo("indonesia_nasi_goreng", "🍛", "Nasi Goreng", "indonesia", "Indonesian fried rice with a fried egg", listOf("Rice", "Sweet Soy Sauce", "Garlic", "Chili", "Egg"), "450 kcal"),
    FoodInfo("indonesia_rendang", "🥘", "Rendang", "indonesia", "Slow-cooked beef in coconut milk and spices", listOf("Beef", "Coconut Milk", "Lemongrass", "Turmeric", "Galangal"), "520 kcal"),
    FoodInfo("indonesia_satay", "🍢", "Satay", "indonesia", "Grilled meat skewers with peanut sauce", listOf("Chicken/Beef", "Peanut Sauce", "Sweet Soy Sauce", "Spices"), "210 kcal"),
    FoodInfo("indonesia_gado_gado", "🥗", "Gado-gado", "indonesia", "Vegetable salad with peanut dressing", listOf("Cabbage", "Spinach", "Tofu", "Tempeh", "Peanut Sauce"), "280 kcal"),
    FoodInfo("indonesia_soto", "🍲", "Soto Ayam", "indonesia", "Traditional yellow chicken soup", listOf("Chicken", "Turmeric", "Vermicelli", "Boiled Egg"), "240 kcal"),
    FoodInfo("indonesia_bakso", "🍲", "Bakso", "indonesia", "Meatball soup with noodles", listOf("Beef Meatballs", "Noodles", "Broth", "Celery"), "310 kcal"),
    FoodInfo("indonesia_martabak", "🥞", "Martabak Manis", "indonesia", "Sweet thick pancake with various toppings", listOf("Flour", "Chocolate", "Peanuts", "Cheese", "Condensed Milk"), "420 kcal"),
    FoodInfo("indonesia_nasi_padang", "🍛", "Nasi Padang", "indonesia", "Assortment of Padang dishes with rice", listOf("Rice", "Rendang", "Curries", "Chili Sauce"), "600 kcal"),
    FoodInfo("indonesia_pempek", "🥟", "Pempek", "indonesia", "Savory fish cake from Palembang", listOf("Fish", "Tapioca", "Cuko Sauce"), "260 kcal"),
    FoodInfo("indonesia_tempeh", "🥓", "Tempeh Goreng", "indonesia", "Fried fermented soybean cake", listOf("Soybeans", "Garlic", "Coriander", "Oil"), "190 kcal"),

    // MALAYSIA (Phase 3)
    FoodInfo("malaysia_nasi_lemak", "🍛", "Nasi Lemak", "malaysia", "Coconut milk rice with anchovies and sambal", listOf("Rice", "Coconut Milk", "Sambal", "Anchovies", "Peanuts", "Egg"), "500 kcal"),
    FoodInfo("malaysia_laksa", "🍜", "Laksa", "malaysia", "Spicy noodle soup with coconut or fish base", listOf("Rice Noodles", "Spices", "Shrimp/Fish", "Coconut Milk"), "420 kcal"),
    FoodInfo("malaysia_char_kway_teow", "🍝", "Char Kway Teow", "malaysia", "Stir-fried flat rice noodles", listOf("Flat Rice Noodles", "Shrimp", "Sprouts", "Chives", "Egg"), "550 kcal"),
    FoodInfo("malaysia_roti_canai", "🫓", "Roti Canai", "malaysia", "Flaky flatbread served with dhal", listOf("Flour", "Butter", "Water", "Lentil Curry"), "320 kcal"),
    FoodInfo("malaysia_satay", "🍢", "Satay", "malaysia", "Spiced grilled meat skewers", listOf("Chicken/Beef", "Turmeric", "Peanut Sauce", "Cucumber"), "220 kcal"),
    FoodInfo("malaysia_hainanese_chicken_rice", "🍚", "Chicken Rice", "malaysia", "Poached chicken with seasoned rice", listOf("Chicken", "Rice", "Ginger", "Garlic", "Chili Sauce"), "450 kcal"),
    FoodInfo("malaysia_cendol", "🍧", "Cendol", "malaysia", "Iced dessert with green jelly and coconut milk", listOf("Shaved Ice", "Coconut Milk", "Palm Sugar", "Pandan Jelly"), "280 kcal"),
    FoodInfo("malaysia_rendang", "🥘", "Rendang", "malaysia", "Malaysian style slow-cooked dry curry", listOf("Beef", "Coconut Milk", "Kerisik", "Kaffir Lime Leaves"), "510 kcal"),
    FoodInfo("malaysia_nasi_kerabu", "🍛", "Nasi Kerabu", "malaysia", "Blue-tinted rice with herbs and fish", listOf("Rice", "Butterfly Pea Flower", "Herbs", "Salted Egg"), "430 kcal"),
    FoodInfo("malaysia_teh_tarik", "☕", "Teh Tarik", "malaysia", "Pulled black tea with condensed milk", listOf("Black Tea", "Condensed Milk"), "120 kcal"),

    // CANADA (Phase 4)
    FoodInfo("canada_poutine", "🍟", "Poutine", "canada", "Classic fries topped with cheese curds and gravy", listOf("Fries", "Cheese Curds", "Brown Gravy"), "510 kcal"),
    FoodInfo("canada_maple_syrup", "🍁", "Maple Syrup", "canada", "Pure Canadian maple syrup", listOf("Maple Sap"), "50 kcal"),
    FoodInfo("canada_butter_tart", "🧁", "Butter Tart", "canada", "Sweet pastry shell with butter and sugar filling", listOf("Butter", "Sugar", "Eggs", "Pastry"), "280 kcal"),
    FoodInfo("canada_beaver_tails", "🥖", "BeaverTails", "canada", "Fried dough pastry stretched to resemble a beaver's tail", listOf("Flour", "Yeast", "Cinnamon", "Sugar"), "350 kcal"),
    FoodInfo("canada_nanaimo_bar", "🍫", "Nanaimo Bar", "canada", "No-bake dessert bar with chocolate and custard", listOf("Chocolate", "Custard", "Coconut", "Walnuts"), "250 kcal"),
    FoodInfo("canada_montreal_bagel", "🥯", "Montreal Bagel", "canada", "Dense, sweet bagel boiled in honey water", listOf("Flour", "Honey", "Eggs", "Sesame Seeds"), "290 kcal"),
    FoodInfo("canada_tourtiere", "🥧", "Tourtière", "canada", "French-Canadian meat pie", listOf("Minced Meat", "Spices", "Pie Crust"), "420 kcal"),
    FoodInfo("canada_split_pea_soup", "🥣", "Split Pea Soup", "canada", "Traditional Quebecois pea soup", listOf("Yellow Split Peas", "Ham", "Vegetables"), "190 kcal"),
    FoodInfo("canada_bannock", "🍞", "Bannock", "canada", "Indigenous flatbread", listOf("Flour", "Baking Powder", "Water"), "210 kcal"),
    FoodInfo("canada_lobster_roll", "🥪", "Lobster Roll", "canada", "Atlantic lobster in a buttery roll", listOf("Lobster", "Butter", "Mayonnaise", "Bun"), "380 kcal"),

    // PERU (Phase 4)
    FoodInfo("peru_ceviche", "🥗", "Ceviche", "peru", "Fresh raw fish cured in citrus juices", listOf("Raw Fish", "Lime Juice", "Onions", "Chili"), "140 kcal"),
    FoodInfo("peru_lomo_saltado", "🥩", "Lomo Saltado", "peru", "Stir-fried beef with onions and tomatoes", listOf("Beef", "Onions", "Tomatoes", "Soy Sauce", "Fries"), "480 kcal"),
    FoodInfo("peru_aji_de_gallina", "🍛", "Ají de Gallina", "peru", "Spicy chicken stew with yellow chili", listOf("Chicken", "Yellow Chili", "Bread", "Walnuts", "Milk"), "410 kcal"),
    FoodInfo("peru_cuy_chactado", "🍗", "Cuy Chactado", "peru", "Traditional fried guinea pig", listOf("Cuy", "Spices", "Oil"), "320 kcal"),
    FoodInfo("peru_papas_a_la_huancaina", "🥔", "Papas a la Huancaína", "peru", "Boiled potatoes in spicy cheese sauce", listOf("Potatoes", "Cheese", "Yellow Chili", "Crackers"), "290 kcal"),
    FoodInfo("peru_anticuchos", "🍢", "Anticuchos", "peru", "Grilled beef heart skewers", listOf("Beef Heart", "Garlic", "Vinegar", "Chili"), "250 kcal"),
    FoodInfo("peru_causa_rellena", "🥔", "Causa Rellena", "peru", "Layered potato and tuna salad", listOf("Potatoes", "Tuna/Chicken", "Lime", "Chili"), "310 kcal"),
    FoodInfo("peru_rocoto_relleno", "🌶️", "Rocoto Relleno", "peru", "Spicy stuffed peppers", listOf("Rocoto Pepper", "Minced Meat", "Cheese", "Eggs"), "350 kcal"),
    FoodInfo("peru_picarones", "🍩", "Picarones", "peru", "Sweet potato and squash doughnuts", listOf("Sweet Potato", "Squash", "Flour", "Chicha Syrup"), "280 kcal"),
    FoodInfo("peru_chicha_morada", "🥤", "Chicha Morada", "peru", "Sweet purple corn drink", listOf("Purple Corn", "Pineapple", "Cinnamon", "Sugar"), "110 kcal"),

    // CHILE (Phase 4)
    FoodInfo("chile_empanada", "🥟", "Empanada de Pino", "chile", "Baked pastry with meat, onions, and olives", listOf("Flour", "Beef", "Onions", "Olives", "Egg"), "350 kcal"),
    FoodInfo("chile_pastel_de_choclo", "🥧", "Pastel de Choclo", "chile", "Corn and meat pie", listOf("Corn", "Minced Meat", "Basil", "Chicken"), "450 kcal"),
    FoodInfo("chile_curanto", "🍲", "Curanto", "chile", "Traditional pit-cooked seafood and meat", listOf("Seafood", "Meat", "Potatoes", "Milcao"), "600 kcal"),
    FoodInfo("chile_humitas", "🌽", "Humitas", "chile", "Steamed corn husks with corn dough", listOf("Corn", "Onions", "Basil"), "210 kcal"),
    FoodInfo("chile_cazuela", "🥣", "Cazuela", "chile", "Hearty meat and vegetable stew", listOf("Beef/Chicken", "Pumpkin", "Corn", "Potatoes"), "380 kcal"),
    FoodInfo("chile_completo", "🌭", "Completo", "chile", "Chilean style loaded hot dog", listOf("Sausage", "Bun", "Avocado", "Tomato", "Mayo"), "420 kcal"),
    FoodInfo("chile_mote_con_huesillo", "🥤", "Mote con Huesillo", "chile", "Peach and husked wheat drink", listOf("Dried Peaches", "Husked Wheat", "Sugar", "Cinnamon"), "250 kcal"),
    FoodInfo("chile_pebre", "🥣", "Pebre", "chile", "Chilean condiment/salsa", listOf("Tomatoes", "Onions", "Coriander", "Chili"), "40 kcal"),
    FoodInfo("chile_caldillo_de_congrio", "🍲", "Caldillo de Congrio", "chile", "Conger eel soup", listOf("Conger Eel", "Vegetables", "White Wine"), "310 kcal"),
    FoodInfo("chile_charquican", "🍲", "Charquicán", "chile", "Potato and pumpkin mash with meat", listOf("Potatoes", "Pumpkin", "Minced Meat", "Corn"), "340 kcal"),

    // ARGENTINA (Phase 4)
    FoodInfo("argentina_asado", "🥩", "Asado", "argentina", "Traditional Argentine barbecue", listOf("Beef", "Pork", "Salt"), "600 kcal"),
    FoodInfo("argentina_empanada", "🥟", "Empanada", "argentina", "Savory baked or fried pastry", listOf("Flour", "Beef/Ham", "Cheese", "Onions"), "280 kcal"),
    FoodInfo("argentina_choripan", "🥪", "Choripán", "argentina", "Chorizo sandwich with chimichurri", listOf("Chorizo", "Bread", "Chimichurri"), "450 kcal"),
    FoodInfo("argentina_milanesa", "🍗", "Milanesa", "argentina", "Breaded meat fillet", listOf("Beef/Chicken", "Breadcrumbs", "Eggs"), "420 kcal"),
    FoodInfo("argentina_locro", "🍲", "Locro", "argentina", "Hearty corn and meat stew", listOf("Corn", "Beans", "Pork", "Spices"), "500 kcal"),
    FoodInfo("argentina_alfajores", "🍪", "Alfajores", "argentina", "Shortbread cookies with dulce de leche", listOf("Cornstarch", "Dulce de Leche", "Coconut"), "310 kcal"),
    FoodInfo("argentina_dulce_de_leche", "🍯", "Dulce de Leche", "argentina", "Sweet caramelized milk spread", listOf("Milk", "Sugar"), "300 kcal"),
    FoodInfo("argentina_mate", "🧉", "Mate", "argentina", "Traditional herbal infusion", listOf("Yerba Mate", "Water"), "2 kcal"),
    FoodInfo("argentina_chimichurri", "🥣", "Chimichurri", "argentina", "Garlic and parsley herb sauce", listOf("Parsley", "Garlic", "Oil", "Vinegar"), "120 kcal"),
    FoodInfo("argentina_fugazzeta", "🍕", "Fugazzeta", "argentina", "Double-crust pizza with lots of onions and cheese", listOf("Dough", "Cheese", "Onions"), "480 kcal"),

    // AUSTRALIA (Phase 4)
    FoodInfo("australia_meat_pie", "🥧", "Meat Pie", "australia", "Iconic pastry filled with minced meat and gravy", listOf("Pastry", "Minced Meat", "Gravy", "Tomato Sauce"), "450 kcal"),
    FoodInfo("australia_vegemite_toast", "🍞", "Vegemite on Toast", "australia", "Classic salty yeast extract spread", listOf("Bread", "Butter", "Vegemite"), "180 kcal"),
    FoodInfo("australia_lamington", "🍰", "Lamington", "australia", "Sponge cake coated in chocolate and coconut", listOf("Sponge Cake", "Chocolate", "Desiccated Coconut"), "240 kcal"),
    FoodInfo("australia_pavlova", "🍨", "Pavlova", "australia", "Meringue dessert topped with fruit", listOf("Egg Whites", "Sugar", "Cream", "Fruit"), "300 kcal"),
    FoodInfo("australia_barramundi", "🐟", "Barramundi", "australia", "Grilled Australian sea bass", listOf("Barramundi", "Lemon", "Herbs"), "210 kcal"),
    FoodInfo("australia_kangaroo_steak", "🥩", "Kangaroo Steak", "australia", "Lean and healthy kangaroo meat", listOf("Kangaroo Meat", "Salt", "Pepper"), "150 kcal"),
    FoodInfo("australia_anzac_biscuit", "🍪", "Anzac Biscuit", "australia", "Sweet oat and coconut biscuit", listOf("Oats", "Coconut", "Golden Syrup", "Butter"), "120 kcal"),
    FoodInfo("australia_damper", "🍞", "Damper", "australia", "Traditional Australian soda bread", listOf("Flour", "Water", "Milk", "Salt"), "230 kcal"),
    FoodInfo("australia_witchetty_grub", "🐛", "Witchetty Grub", "australia", "Nutty-flavored bush tucker", listOf("Witchetty Grub"), "100 kcal"),
    FoodInfo("australia_flat_white", "☕", "Flat White", "australia", "Australian style espresso and milk drink", listOf("Espresso", "Steamed Milk"), "110 kcal"),

    // NEW ZEALAND (Phase 4)
    FoodInfo("new_zealand_hangi", "🍲", "Hāngī", "new_zealand", "Traditional Māori pit-cooked meal", listOf("Meat", "Potatoes", "Kumara", "Pumpkin"), "520 kcal"),
    FoodInfo("new_zealand_hokey_pokey", "🍦", "Hokey Pokey Ice Cream", "new_zealand", "Vanilla ice cream with honeycomb toffee", listOf("Ice Cream", "Honeycomb Toffee"), "220 kcal"),
    FoodInfo("new_zealand_pavlova", "🍰", "Pavlova", "new_zealand", "Classic meringue dessert with kiwi fruit", listOf("Egg Whites", "Sugar", "Cream", "Kiwi Fruit"), "280 kcal"),
    FoodInfo("new_zealand_meat_pie", "🥧", "NZ Meat Pie", "new_zealand", "Flaky pastry with savory meat filling", listOf("Pastry", "Beef", "Cheese", "Gravy"), "440 kcal"),
    FoodInfo("new_zealand_green_mussels", "🦪", "Green Lipped Mussels", "new_zealand", "Large native NZ mussels", listOf("Mussels", "Garlic", "White Wine"), "160 kcal"),
    FoodInfo("new_zealand_kumara", "🥔", "Kūmara", "new_zealand", "Native New Zealand sweet potato", listOf("Kumara"), "110 kcal"),
    FoodInfo("new_zealand_whitebait_fritter", "🍳", "Whitebait Fritter", "new_zealand", "Small freshwater fish omelette", listOf("Whitebait", "Eggs", "Butter"), "210 kcal"),
    FoodInfo("new_zealand_anzac_biscuit", "🍪", "ANZAC Biscuit", "new_zealand", "Commemorative oat biscuit", listOf("Oats", "Flour", "Golden Syrup"), "120 kcal"),
    FoodInfo("new_zealand_kiwi_fruit", "🥝", "Kiwi Fruit", "new_zealand", "Iconic furry fruit", listOf("Kiwi Fruit"), "40 kcal"),
    FoodInfo("new_zealand_lamington", "🍰", "NZ Lamington", "new_zealand", "Sponge cake coated in raspberry or chocolate", listOf("Sponge Cake", "Jam/Chocolate", "Coconut"), "250 kcal")
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
    "sudan" to Boss("Desert Guardian", "🛡️🏜️", 260, "sudan", listOf("Sand Storm", "Ancient Spear")),

    // Phase 1 Bosses
    "uk" to Boss("Tea Titan", "☕🏰", 180, "uk", listOf("Scalding Splash", "Scone Slam")),
    "netherlands" to Boss("Windmill Giant", "🌬️🪁", 170, "netherlands", listOf("Blade Gust", "Tulip Trap")),
    "portugal" to Boss("Fado Phantom", "🎸🎭", 165, "portugal", listOf("Melancholy Note", "Wave Crash")),
    "austria" to Boss("Waltz Wizard", "🎻🏔️", 190, "austria", listOf("Tempo Shift", "Alpine Avalanche")),
    "switzerland" to Boss("Matterhorn Monk", "🏔️🧘", 200, "switzerland", listOf("Ice Wall", "Precision Bolt")),

    // Phase 2 Bosses
    "egypt" to Boss("Sphinx Riddler", "🦁🏺", 210, "egypt", listOf("Sand Vortex", "Stone Gaze")),
    "morocco" to Boss("Souk Sorcerer", "🏺🧙", 195, "morocco", listOf("Spice Mist", "Flying Carpet")),
    "kenya" to Boss("Savanna Sentinel", "🦒🛡️", 205, "kenya", listOf("Dust Storm", "Nature Strike")),
    "ethiopia" to Boss("Obelisk Guardian", "🗿⛰️", 220, "ethiopia", listOf("Rift Crack", "Ancient Light")),

    // Phase 3 Bosses
    "south_korea" to Boss("Tiger of Korea", "🐯🏔️", 230, "south_korea", listOf("Roaring Strike", "Gale Claw")),
    "vietnam" to Boss("Monsoon Dragon", "🐉⛈️", 225, "vietnam", listOf("Cloud Burst", "Lightning Whip")),
    "indonesia" to Boss("Java Guardian", "👹🌋", 240, "indonesia", listOf("Lava Surge", "Spirit Guard")),
    "malaysia" to Boss("Rainforest Protector", "🐅🌿", 235, "malaysia", listOf("Vine Whip", "Jungle Roar")),

    // Phase 4 Bosses
    "canada" to Boss("Northern Guardian", "🐺❄️", 240, "canada", listOf("Blizzard", "Ice Claw")),
    "peru" to Boss("Sun Temple Guardian", "☀️⛰️", 245, "peru", listOf("Sun Beam", "Stone Spike")),
    "chile" to Boss("Andes Protector", "🦅🏔️", 250, "chile", listOf("High Wind", "Mountain Slide")),
    "argentina" to Boss("Patagonia Spirit", "🐎🌬️", 255, "argentina", listOf("Wild Stampede", "Freezing Mist")),
    "australia" to Boss("Outback Guardian", "🦘🏜️", 260, "australia", listOf("Boomerang", "Dust Cyclone")),
    "new_zealand" to Boss("Southern Sky Guardian", "🥝✨", 265, "new_zealand", listOf("Kiwi Dash", "Starlight Beam"))
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
