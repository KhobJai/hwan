package cheatsheet

// Parameters & Return Types
fun printName() {
    print("Adam")
}

fun printName(person: Person) {
    print(person.name)
}

fun getGreeting(person: Person): String {
    return "Hello, ${person.name}"
}

fun getGreeting2(person: Person): String = "Hello, ${person.name}"
fun getGreeting3(person: Person) = "Hello, ${person.name}"
// Higher Order Functions
fun callbackIfTrue(condition: Boolean, callback: () -> Unit) {
    if (condition) {
        callback()
    }
}

class Wrap {
    var someBoolean  = true;
    fun wrap() {
        callbackIfTrue(someBoolean) {
            print("Condition was true")
        }
    }
}

// Extension Functions
fun Int.timesTwo(): Int {
    return this * 2
}

val four = 2.timesTwo()
// Default Parameters
fun getGreeting3(person: Person, intro: String = "Hello,"):String {
    return "$intro ${person.name}"
}

// Returns "Hello, Adam"
val hello = getGreeting(Person("Adam"))

// Returns "Welcome, Adam"
val welcome = getGreeting3(Person("Adam"), "Welcome,")
// Named Parameters

// All valid
val person1 = Person()
val person2 = Person("Adam", 100)
val person3 = Person(name = "Adam", age = 100)
val person4 = Person(age = 100)
val person5 = Person(age = 100, name = "Adam")

class Bundle {}
// Static Functions
class Fragment(val args: Bundle) {
    companion object {
        fun newInstance(args: Bundle): Fragment {
            return Fragment(args)
        }
    }
}
val fragment = Fragment.newInstance(Bundle())
// Companion Objects