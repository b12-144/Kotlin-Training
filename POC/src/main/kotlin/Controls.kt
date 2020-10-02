import entities.EUser
import enums.Color
import enums.Temperature

fun testSingleLineIfElse(a:Int, b:Int):Unit{
    val max=if(a>b) a else b
    println("Max = $max")
}

fun testWhen(color:Color):String{
    val result:String
    result = when(color){
        Color.Blue-> "cold"
        Color.Orange-> "mild"
        Color.Red-> {
            //it's possible to have multiple lines
            var a=10
            "hot"
        }
        else -> "I don't know"
    }
    return result
}

fun testWhen2(obj:Any):Unit{
    when(obj){
        is EUser ->println(obj.name)
        is String ->println("object is string")
    }
}

fun testForLoop():Unit{//there are more FOR examples under Collections
    println("ex0")
    val list=listOf("a", "b")
    for(s in list){
        println(s)
    }

    //with ranges below. This prints 1 up to 9
    println("ex1")
    for(i in 1..9){
        println(i)
    }

    //another way with ranges. This prints 1 up to 8!!!
    println("ex2")
    for(i in 1 until 9)
        println(i)


    //with downstep
    println("ex3")
    for(i in 9 downTo 1 step 2){
        println(i)
    }

    //it's possible to iterate over a string
    println("ex4")
    for (ch in "abc") {
        println(ch + 1)
    }

    println("ex5")
    for (c in '0'..'9') {//This prints 1 up to 9
        println(c)
    }
}

fun testInWithWhen(c:Char):String{
    return when(c){
        in '0'..'9'-> "It's digit"
        in 'a'..'z'-> "It's a letter"
        else -> "unknown"
    }
}

fun testRanges(){
    val intRange:IntRange=1..9
    val anotherIntRange=1 until 10
    val charRange: CharRange='a'..'z'
    val stringRange="ab".."az"
    val dt1=MyDate(2000,9,20)
    val dt2=MyDate(2020,9,20)
    val dateInRange=MyDate(2005,1,1) in dt1..dt2//returns true

}

fun main() {
    testRanges()
}
