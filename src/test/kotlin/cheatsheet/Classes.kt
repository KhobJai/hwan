package cheatsheet

// Primary Constructor
class Person2(val name: String, val age: Int)

val adam = Person("Adam", 100)

//Secondary Constructors
class Person3(val name: String) {
    private var age: Int? = null

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }
}

// Above can be replaced with default params
class Person4(val name: String, val age: Int? = null)

// Inheritance & Implementation
open class Vehicle
class Car : Vehicle()

interface Runner {
    fun run()
}

class Machine : Runner {
    override fun run() {
        // ...
    }
}
// Control Flow

enum class Direction {
    NORTH, SOUTH, EAST, WEST
}
// CONTROL FLOW
// If Statements

class WrapMe {

    fun doThing() {
        println("THing")
    };
    fun doOtherThing() {
        println("Otehr THing")
    };
    val direction = Direction.NORTH;
    var someBoolean = true;
    fun warp() {

        if (someBoolean) {
            doThing()
        } else {
            doOtherThing()
        }
        // For Loops
        for (i in 0..10) {
        } // 1 - 10
        for (i in 0 until 10) // 1 - 9
            (0..10).forEach { }
        for (i in 0 until 10 step 2) // 0, 2, 4, 6, 8
        // When Statements

            when (direction) {
                Direction.NORTH -> {
                    print("North")
                }
                Direction.SOUTH -> print("South")
                Direction.EAST, Direction.WEST -> print("East or West")
//                        "N/A" -> print("Unavailable")
                else -> print("Invalid Direction")
            }
        var x = 0
        // While Loops
        while (x > 0) {
            x--
        }

        do {
            x--
        } while (x > 0)
    }
}