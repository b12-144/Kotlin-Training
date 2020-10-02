
open class Parent
class Child: Parent()

fun Parent.foo() = "parent"
fun Child.foo() = "child"


open class BaseClass{
    open fun printName()=println("Bozo")
}
class ChildClass:BaseClass(){
    override fun printName()=println("Mafalda")
}

class Person(val firstName: String, private val surName:String) {
}


fun main() {
    val person=Person("Mafalda","da Silva")
    val name=person.firstName//Mafalda
    //val surname=person.surName//compiler error
}
