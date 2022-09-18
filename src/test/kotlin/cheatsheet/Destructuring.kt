package cheatsheet

// Destructuring Declarations
// Objects & Lists
// ComponentN Functions
class Man(val name:String, val age:Int) {
    operator fun component1(): String {
        return name;
    }

    operator fun component2(): Int {
        return age
    }
}


class Wrapme {
    fun wrapper() {
         val man = Man("Adam", 100)
        val (name, age) = man

        val pair = Pair(1, 2)
        val (first, second) = pair

        val coordinates = arrayOf(1, 2, 3)
        val (x, y, z) = coordinates

    }
}


