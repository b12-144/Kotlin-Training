package com.jdeers.extensions

fun String.lastChar():Char{
    return this.get(this.length-1)
}

fun testConvertions() {
    4.toString()//"4"
    "4".toDouble()//4.0
    4.toInt()//4
    "barbosa".toIntOrNull()//null
}


fun main() {
//    val name="Joselito"
//    val lastChar=name.lastChar()//returns o

}
