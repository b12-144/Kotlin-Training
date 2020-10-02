
fun createReadOnlyListOfChars() {
    val list = listOf('a', 'b', 'c')
    println(list.size) // 3
    println("list.contains('a') is ${list.contains('a')}") // true
    println(list.indexOf('b')) // 1
    println(list[2]) // c
}

fun createMutableList() {
    //option 1
    val list: MutableList<String> = mutableListOf()
    list.add("Bozo")
    list.add("Mafalda")
    val result = list.joinToString(separator = ", ")
    println(result)//Bozo, Mafalda

    //option 2
    val list2: List<String> = emptyList()
    val newList = list2.toMutableList()
    newList.add("Bozo")
    newList.add("Mafalda")
    val result2 = newList.joinToString(separator = ", ")
    println(result2)//Bozo, Mafalda

    //option 3
    val list3 = listOf("Bozo", "Mafalda")
    val modified = list3 + "Paquita" // [Bozo, Mafalda, Paquita]
    println(modified)

    //option 4
    var list4= mutableListOf<String>("Bozo")
    list4.add("Mafalda")
}

fun mapWithFor(){
    val map=mapOf(1 to "one", 2 to "two", 3 to "three")
    for((key,value) in map){
        println("$key=$value")
    }
}

fun listWithFor(){
    val list=listOf("a","b","c")
    for((index,element) in list.withIndex()){
        println("$index: $element")
    }
}

fun testSetOf(){
    val mySet= setOf<String>("Bozo","Mafalda")
    var ok=mySet.contains("Bozo")//return true
}

fun testHashSetOf(){
    val hashSet=hashSetOf(1,10,20)
    hashSet.add(30)
    val contains= hashSet.contains(30)//returns true
}

fun testArrayListOf(){
    val list= arrayListOf<Int>(1,2,3,4)
    var javaClassName=list.javaClass
    var element=list.getOrNull(3)
}

fun main() {
    testArrayListOf()
}
