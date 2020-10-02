import entities.EUser

fun testVarAndVal() {
    val name = "Batman"
    //name="Zorro"//compiler error since we can't modify its reference
    val eUser = EUser(name = "Bozo")
    //eUser=EUser(name="Joker")//compiler error
    eUser.name = "Mafalda"//ok since we do not modify the eUser reference
    //eUser.id=10//compiler error
}

fun testException(number: Int) {
    val percentage = if (number in 0..100) number
    else throw IllegalArgumentException("A percentage value must be between 0 and 100")
}

fun testUntil() {
    val myRange = 1 until 7
    for (i in myRange) {
        println(i)
    }
}

fun testTo() {
    val pair = 1 to "batman"
}

fun testMultilineString() {
    val text = """Hello Bozo, 
                        #I can write long texts containing 
                 #several lines this way!"""
    print(text.trimMargin("#"))
}

fun testRegex() {
    val regex = """\d{2}\.\d{2}\.\d{4}""".toRegex()
    var matches = regex.matches("04.08.2000")
    matches = regex.matches("04.08")
}

object MySingleton {
    fun printName()=println("Batman")
}

fun testCompanions(){
    MySingleton.printName()
    //o
}

fun main() {
    testCompanions()
}
