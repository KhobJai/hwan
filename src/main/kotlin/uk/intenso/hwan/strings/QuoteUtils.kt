package uk.intenso.hwan.strings

fun doubleQuote(str: String): String {
    return '"' + str + '"'
}

fun singleQuote(str:String):String {
    return "\'" + str + "\'";
}

fun quote(str: String):String = doubleQuote(str)