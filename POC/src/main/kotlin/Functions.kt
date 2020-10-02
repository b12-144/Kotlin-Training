


fun testNamedArguments(content:String="defaultContent", size:Int):String{
    val result= "$content:$size"
    return result;
}

fun main() {
    testNamedArguments(size = 40)
}
