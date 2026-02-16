package com.hit.aliasgameapp.data.repository

import com.hit.aliasgameapp.data.dao.WordDao
import com.hit.aliasgameapp.data.model.WordEntity
import com.hit.aliasgameapp.data.remote.RandomWordApi
import android.util.Log
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val api: RandomWordApi,
    private val wordDao: WordDao
) {

    suspend fun getCardWords(): Result<List<String>> {
        return try {
            var wordCount = wordDao.getWordCount()

            if (wordCount < 20) {
                try {
                    val response = api.getGameWords(500)
                    if (response.isSuccessful && response.body() != null) {
                        val entities = response.body()!!.map { WordEntity(word = it) }
                        wordDao.insertAll(entities)
                    }
                } catch (e: Exception) {
                    Log.e("GameRepo", "Internet failed, using backup: ${e.message}")
                }

                wordCount = wordDao.getWordCount()
            }

            if (wordCount < 8) {
                val backupWords = getBackupList().map { WordEntity(word = it) }
                wordDao.insertAll(backupWords)
            }

            val wordEntities = wordDao.getRandomCardWords()

            if (wordEntities.isNotEmpty()) {
                wordDao.deleteWords(wordEntities)

                val wordStrings = wordEntities.map { it.word }

                if (wordStrings.size < 8) {
                    val backupWords = getBackupList().map { WordEntity(word = it) }
                    wordDao.insertAll(backupWords)
                }

                Result.success(wordStrings)
            } else {
                Result.success(listOf("Error", "Loading", "Problem", "With", "Data", "Please", "Restart", "Game"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Call this when the user finishes the game
    suspend fun endCurrentGame() {
        wordDao.deleteAll()
    }

    // backup list of 240 words, will be loaded only if api fails
    private fun getBackupList(): List<String> {
        return listOf(
            "Dog", "Cat", "Elephant", "Lion", "Tiger", "Bear", "Wolf", "Fox", "Rabbit", "Mouse",
            "Horse", "Cow", "Pig", "Sheep", "Goat", "Chicken", "Duck", "Eagle", "Owl", "Parrot",
            "Penguin", "Whale", "Dolphin", "Shark", "Fish", "Frog", "Snake", "Lizard", "Turtle", "Spider",
            "Bee", "Butterfly", "Ant", "Mosquito", "Fly", "Worm", "Snail", "Crab", "Lobster", "Octopus",
            "Apple", "Banana", "Orange", "Grape", "Lemon", "Strawberry", "Watermelon", "Pineapple", "Peach", "Pear",
            "Tomato", "Potato", "Carrot", "Onion", "Garlic", "Cucumber", "Lettuce", "Spinach", "Corn", "Peas",
            "Bread", "Rice", "Pasta", "Pizza", "Burger", "Sandwich", "Soup", "Salad", "Steak", "Chicken",
            "Egg", "Cheese", "Milk", "Butter", "Yogurt", "Ice Cream", "Cake", "Cookie", "Chocolate", "Candy",
            "Coffee", "Tea", "Juice", "Water", "Soda", "Beer", "Wine", "Salt", "Pepper", "Sugar",
            "Table", "Chair", "Sofa", "Bed", "Desk", "Lamp", "Clock", "Mirror", "Rug", "Curtain",
            "Door", "Window", "Wall", "Floor", "Ceiling", "Roof", "Stairs", "Key", "Lock", "Phone",
            "Computer", "Laptop", "Tablet", "TV", "Radio", "Camera", "Headphones", "Speaker", "Battery", "Charger",
            "Book", "Pen", "Pencil", "Paper", "Notebook", "Bag", "Box", "Bottle", "Cup", "Plate",
            "Bowl", "Fork", "Spoon", "Knife", "Pan", "Pot", "Oven", "Fridge", "Sink", "Soap",
            "Sun", "Moon", "Star", "Sky", "Cloud", "Rain", "Snow", "Wind", "Storm", "Thunder",
            "Lightning", "Rainbow", "Tree", "Flower", "Grass", "Leaf", "Bush", "Forest", "Mountain", "Hill",
            "Valley", "River", "Lake", "Ocean", "Sea", "Beach", "Sand", "Rock", "Stone", "Dirt",
            "Fire", "Water", "Ice", "Air", "Earth", "World", "Planet", "Space", "Galaxy", "Universe",
            "Head", "Face", "Eye", "Ear", "Nose", "Mouth", "Tooth", "Tongue", "Hair", "Neck",
            "Shoulder", "Arm", "Hand", "Finger", "Leg", "Foot", "Toe", "Heart", "Brain", "Blood",
            "Shirt", "Pants", "Shorts", "Dress", "Skirt", "Coat", "Jacket", "Sweater", "Hat", "Cap",
            "Shoes", "Socks", "Boots", "Gloves", "Scarf", "Belt", "Watch", "Glasses", "Ring", "Bag",
            "Doctor", "Nurse", "Teacher", "Student", "Police", "Firefighter", "Soldier", "Pilot", "Driver", "Chef",
            "Baker", "Farmer", "Artist", "Singer", "Actor", "Dancer", "Writer", "Judge", "Lawyer", "King",
            "Queen", "Prince", "Princess", "Baby", "Child", "Boy", "Girl", "Man", "Woman", "Friend",
            "House", "Home", "School", "Office", "Hospital", "Bank", "Store", "Shop", "Market", "Park",
            "Zoo", "Museum", "Cinema", "Theater", "Hotel", "Restaurant", "Cafe", "Airport", "Station", "Port",
            "Car", "Bus", "Truck", "Train", "Bike", "Boat", "Ship", "Plane", "Taxi", "Ambulance",
            "Run", "Walk", "Jump", "Swim", "Fly", "Eat", "Drink", "Sleep", "Dream", "Wake",
            "Talk", "Listen", "Read", "Write", "Draw", "Play", "Work", "Study", "Think", "Know",
            "Love", "Hate", "Happy", "Sad", "Angry", "Fear", "Hope", "Luck", "Time", "Life",
            "Start", "Stop", "Open", "Close", "Help", "Give", "Take", "Buy", "Sell", "Win"
        )
    }
}