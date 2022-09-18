package uk.intenso.hwan.kt

import java.util.Random

/**
 * Return a random number between 1 and 6
 */
fun rollDice(random: Random=Random()):Int {
    return random.nextInt(1,7)
}

fun roll(min:Int=0,max:Int=Int.MAX_VALUE,random: Random=Random()):Int {
    return random.nextInt(min,max);
}