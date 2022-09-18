package cheatsheet

// Creation
val numArray = arrayOf(1, 2, 3)
val numList = listOf(1, 2, 3)
val mutableNumList = mutableListOf(1, 2, 3)
// Accessing
val firstItem = numList[0]
val firstItem2 = numList.first()
val firstItem3 = numList.firstOrNull()
// Maps
val faceCards = mutableMapOf("Jack" to 11, "Queen" to 12, "King" to 13)
val jackValue = faceCards["Jack"] // 11
class Wrapper {
    fun wrap() {
        faceCards["Ace"] = 1

// Mutability
        val immutableList = listOf(1, 2, 3)
        val mutableList = immutableList.toMutableList()
        val immutableMap = mapOf("Jack" to 11, "Queen" to 12, "King" to 13)
        val mutableMap = immutableMap.toMutableMap()

// Iterating
        for (item in immutableList) {
            print(item)
        }

        immutableList.forEach {
            print(it)
        }

        immutableList.forEachIndexed { index, item ->
            print("Item at $index is: $item")
        }
    }
}

// Filtering & Searching
val evenNumbers = numList.filter { it % 2 == 0 }
val containsEven = numList.any { it % 2 == 0 }
val containsNoEvens = numList.none { it % 2 == 0 }
val containsNoEvens1 = numList.all { it % 2 == 1 }
val firstEvenNumber: Int = numList.first { it % 2 == 0 }
val firstEvenOrNull: Int? = numList.firstOrNull { it % 2 == 0 }
//Note: it is the implicit name for a single parameter.