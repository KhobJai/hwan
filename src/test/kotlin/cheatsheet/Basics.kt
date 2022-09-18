package cheatsheet

// Mutability
var mutableString: String = "Adam"
val immutableString: String = "Adam"
val inferredString = "Adam"

// Strings
val name = "Adam"
val greeting = "Hello, " + name
val greetingTemplate = "Hello, $name"
val interpolated = "Hello, ${name.toUpperCase()}"

// Numbers
val intNum = 10
val doubleNum = 10.0
val longNum = 10L
val floatNum = 10.0F

// Booleans
val trueBoolean = true
val falseBoolean = false
val andCondition = trueBoolean && falseBoolean
val orCondition = trueBoolean || falseBoolean

// Static Fields

class Person() {

    constructor(name:String,age:Int) : this() {

    }
    constructor(name: String) : this() {
        this.name = name
    }

    constructor(age: Int) : this() {
        this.age=age
    }

    companion object {
        val NAME_KEY = "name_key"
    }

    var age = 35;
    var name = "John";
    var department: Department? = null;

}

val key = Person.NAME_KEY

// NUll Safety
// val cannotBeNullString: String = null // Invalid
val canBeNullString: String? = null // Valid

// val cannotBeNull: Int = null // Invalid
val canBeNull: Int? = null // Valid

// Null Check
class NullCHeck {
    val name2: String? = "Adam"
    fun check() {
        if (name2 != null && name2.length > 0) {
            print("String length is ${name2.length}")
        } else {
            print("String is empty.")
        }

    }
}

val person = Person();

// Safe Op
val nullableStringLength: Int? = canBeNullString?.length
val nullableDepartmentHead: String? = person?.department?.head?.name

class Department {
    var head: Head? = null;
}

class Head {
    var name: String? = null
}

// ELvis
val nonNullStringLength: Int = canBeNullString?.length ?: 0
val nonNullDepartmentHead1: String = person?.department?.head?.name ?: ""
val nonNullDepartmentHead2: String = person?.department?.head?.name.orEmpty()

// Safe Casts
// Will not throw ClassCastException
val `null`: Person? = (String() as? Person)
