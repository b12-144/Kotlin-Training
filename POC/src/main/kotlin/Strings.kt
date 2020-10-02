

fun testInterpolation(){
    val name="Bozo"
    var complete="Your name is $name"

    val list: List<String> = emptyList()
    complete="List length: ${list.size}"
}

fun main() {
    testInterpolation()
}
