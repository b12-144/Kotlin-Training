# Kotlin

* [Kotlin](https://kotlinlang.org/) is a cross-platform language created by [JetBrains](https://www.jetbrains.com/). It mainly targets JVM, but it's also possible to compile a Kotlin project to Javascript, or even to native code (via [LLVM](https://en.wikipedia.org/wiki/LLVM)). It's the preferred language for creation of native Android apps, since Android Studio 3.0. 
* Kotlin projects can be written using [Gradle](https://gradle.org/), IntelliJ or [Maven](https://maven.apache.org/) build tools. In case you opt for Gradle, it's possible to create Gradle configurations using Groovy language (default) or kotlin. In this case, you'll have `build.gradle.kts` file instead of `build.gradle`
* This README uses the POC project defined [here](./POC).
* In case you wish to understand Kotlin with Micronaut, follow the MicronautKotlinSample README defined [here](https://githubcloud.deere.com/JDFRegion3/MicronautKotlinSample)
* Jetbrains provides a good reference about the language [here](https://kotlinlang.org/docs/reference/). 

<!--toc generated automatically by running markdown-toc -i README.md-->

<!-- toc -->

- [Basics](#basics)
  * [Strings](#strings)
  * [Regular Expressions](#regular-expressions)
  * [Java Interop](#java-interop)
  * [Variables](#variables)
  * [Functions](#functions)
  * [Classes](#classes)
    + [Contructors](#contructors)
    + [Static members](#static-members)
    + [Singletons](#singletons)
    + [Inheritance](#inheritance)
  * [Enums](#enums)
  * [Controls](#controls)
    + [IF/Else](#ifelse)
    + [When](#when)
    + [For](#for)
      - [IN keyword](#in-keyword)
    + [Ranges](#ranges)
  * [Exceptions](#exceptions)
- [Extensions](#extensions)
  * [Until extension](#until-extension)
- [Standard Library](#standard-library)
  * [Collections](#collections)
  * [Conversions](#conversions)

<!-- tocstop -->

# Basics

* We can print a line using `println("Hello World!")`
* There´s no need to add `;` at the end of a statement, functions must have the `fun`  keyword.
* Here is how we instantiate a Kotlin object (Notice there's no `new` keyword): 

```kotlin
val x= MyKotlin() //we use val here to indiate the variable is final
x.myFunction()
```

## Strings

Strings in Kotlin are double quoted and here is how we do string interpolation:

```kotlin
val name="Bozo"
var complete="Your name is $name"

val list: List<String> = emptyList()
complete="List length: ${list.size}"
```

Multiline strings can be written like this:

```kotlin
val text="""Hello Bozo, 
                        #I can write long texts containing 
                 #several lines this way!"""
print(text.trimMargin("#"))
```

The code above prints:

```
Hello Bozo, 
I can write long texts containing 
several lines this way!
```

Notice that even though the second and third line have different left margins, the content start with `#`, which can be trimmed, removing the tabs or spaces up to `#`. 

More info about strings [here](https://kotlinlang.org/docs/reference/basic-types.html#strings).

## Regular Expressions

Regular expressions can be created easily with Kotlin using triple quotes, which is colored by IntelliJ to make easier to understand: 

```kotlin
val regex="""\d{2}\.\d{2}\.\d{4}""".toRegex()
var matches=regex.matches("04.08.2000")//true
matches=regex.matches("04.08")//false
```

## Java Interop

It´s possible to call a Kotlin function from Java. The Kotlin function is invoked as static as exemplified below:

```kotlin
//Myfile.kt
package intro
fun foo()=10
```

Than you can invoke the function above from Java:

```java
//MyJava.java
package other;
import intro.MyfileKt;

public class UsingFoo {
public static void main(String[] args){
    MyfileKt.foo();
    }
}
```

We can even change the Kotlin class name by using the annotation in your kotlin file: `@file:JvmName("MyKotlin")`

More info about Java Interop [here](https://kotlinlang.org/docs/reference/java-interop.html).

## Variables

* `val` are readonly or assigned once variable. `Val` corresponds to the `final` keyword in java. Although it's final, we can modify objects content created with `val`. We just can't modify its reference, so let's say we have `val` list. We can add new values to it. We just can't can't change its reference.
* `var` is any variable that allows the reference to be modified (mutable variable).
* Jetbrains encourages to use `val` whenever possible instead of `var`. 

Examples:

```kotlin
data class EUser(
    val id: Long = 0,
    var name: String,
)

val name="Batman"
name="Zorro"//compiler error since we can't modify its reference

val eUser= EUser(name = "Bozo")
eUser=EUser(name="Joker")//compiler error

eUser.name="Mafalda"//ok since we do not modify the eUser reference
eUser.id=10//compiler error
```

In Kotlin, variable types can be inferred. Ex: 

```kotlin
val greetings = "Hi" //String
val x=10//Int
```

We cannot change the type of an inferred type. Ex:

```kotlin
var x = 1
x = "abc" //compiler error
```

## Functions

Kotlin functions are similar to typescript. Ex:

```kotlin
fun  max(a:Int, b:Int):Int {
    return if(a>b)a else b
}
//or
fun  max(a:Int, b:Int)= if(a>b)a else b
```

Void functions in Kotlin are represented by returning `Unit`type 

It´s possible to have inner functions in kotlin. Ex: 

```kotlin
fun outer(){//top level function
    fun inner(){//local function
  	  var x:Int=10;
    }
}
```

We can use named arguments to pass only those arguments we want to a function. Ex:

```kotlin
fun namedArguments(content:String="defaultContent", size:Int){
}

namedArguments(size = 10)//valid
namedArguments(content = "Bozo")//not valid, since there's no default argument for content
```

Although Kotlin can infer types, it does not allow to automatically convert function arguments. Ex: 

```kotlin
fun displaySeparator(character: Char = '*', size: Int = 10) {
    repeat(size) {
        print(character)
    }
}
displaySeparator(3, '5')//Compiler error since first expected arg is Char and second is int
```

More info about Kotlin functions [here](https://kotlinlang.org/docs/reference/functions.html).

## Classes

Kotlin classes are quite similar to other languages, but there are some details that make it a little bit different. 

### Contructors

Kotlin can have one one primary constructor and one or more secondary constructors. Ex:

```kotlin
class Person(firstName: String) {//primary constructor cannot contain any code
    init{ //optional inititialization code block. It's executed before any secondary constructor
        print("hi")
    }

    constructor(age:Int):this("Bozo"){//secondary constructor MUST call the primary constructor
        print(" there")
    }
}

var person=Person("Joselito")//uses the primary constructor. Prints 'hi'
var anotherPerson=Person(12)//uses the secondary constructor. Prints 'hi there'
```

We can declare and initialize class properties from the primary constructor. Constructor arguments are `public` by default. Ex:

```kotlin
class Person(val firstName: String, private val surName:String) {
}

fun main() {
    val person=Person("Mafalda","da Silva")
    val name=person.firstName //Mafalda
    val surname=person.surName//compiler error since it's a private property
}
```



### Static members

For instance, it's not possible to define a class or a member as static. Take a look below:

```kotlin
class MyClass {
    companion object {
        val age=42
        fun printName()= println("Bozolino")
    }
    val age=12
    fun printSurname()= println("da Silva")
}
```

We can imagine `companion objects` as "static", although it's not under the hood. Using the code above, we can do this:

```kotlin
MyClass.printName()//"Bozolino"
val age=MyClass.age//42
```

Members inside companion objects can not be accessed from a regular class object. Ex:

```kotlin
val myObj=MyClass()
myObj.printSurname()//"da Silva"
val objAge=myObj.age//12

myObj.printName()//compiler error
```

More info about companion objects [here](https://kotlinlang.org/docs/reference/object-declarations.html#companion-objects).

### Singletons

Singleton objects are quite easier. You can create and use one like this:

```kotlin
object MySingleton {
    fun printName()=println("Batman")
}

MySingleton.printName()//"Batman"
val obj=MySingleton()//compiler error
```

### Inheritance

Classes and functions are final by default. If you wish to extend a class, add the keyword `open`. Ex:

```kotlin
open class BaseClass{//open indicates this class can be inherited
    open fun printName()=println("Bozo")//open indicates this function can be inherited
}

class ChildClass:BaseClass(){
    override fun printName()=println("Mafalda")
}

ChildClass().printName()//"Mafalda"
```

More info about classes [here](https://kotlinlang.org/docs/reference/classes.html)

## Enums

We can create simple enums in Kotlin like this:

```kotlin
enum class Color{
    Blue, //0
    Orange,
    Red
}
```

We can also set the enum values:

```kotlin
enum class Temperature(val n:Int) {//note we need a constructor value when setting values explicitly
    High(10),
    Medium(5),
    Low(0)
}
```

More info about enums [here](https://kotlinlang.org/docs/reference/enum-classes.html).

## Controls

Follow a list of Controls that can be used in Kotlin:

### IF/Else

Kotlin provides a regular if/else, but it does not have ternary operator, but we can write a single line if/else like this: `val max=if(a>b) a else b`

More examples with if/else [here](https://kotlinlang.org/docs/reference/control-flow.html#if-expression).

### When

When is similar to `switch`used in other programing languages, but there's no break and the `switch default` is handled by `else` inside `when`. Ex:

```kotlin
enum class Color{Blue,Orange,Red}
    
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
```

When can also be used to check the object type:

```kotlin
fun testWhen(obj:Any){
    when(obj){
        is EUser -> println(obj.name) 
        is String ->println("object is string")
    }
}

testWhen(EUser(name = "Bozolino"))//prints Bozolino
```

In the example above, if `obj` comes from a function return, we must use `val` inside when: `when ( val obj=getMyObj() ){...`. This way, we can use code completion for the obj. 

More examples with When [here](https://kotlinlang.org/docs/reference/control-flow.html#if-expression).

### For 

For is a little bit different in kotlin: 

```kotlin
val list=listOf("a", "b")
for(s in list){//note we don't use var or val for s
	println(s)
}
```

It's also possible to iterate through a map using for:

```kotlin
val map=mapOf(1 to "one", 2 to "two", 3 to "three")
for((key,value) in map){
	println("$key=$value")
}
//prints 
1=one
2=two
3=three
```

And we can also get the element and its index from a List using for:

```kotlin
val list=listOf("a","b","c")
for((index,element) in list.withIndex()){
    println("$index: $element")
}
```

Follow a few more examples below:

`for(i in 1 until 9)println(i)`: Prints 1,2,3,4,5,6,7,8. It does not print 9!

`for(i in 9 downTo 1 step 2)println(i)`: Prints 9,7,5,3,1

`for (c in '0'..'9')println(c)`: Prints 0,1,2,3,4,5,6,7,8,9

More examples with `for` [here](https://kotlinlang.org/docs/reference/control-flow.html#if-expression).

#### IN keyword

 `in` can be used in 2 situations: 

1. to check if a value is contained:

```kotlin
fun isLetter(c:Char){
	if(c in 'a'..'z')return true
	return false
}

fun containsElement(str:String):Boolean{
	return str in setOf<String>("Bozo","Mafalda")
}

val ok="ball" in "a".."k"//returns true since ball is betweeen a and k letter
```

2. or inside interations:

```kotlin
for(s in list)println(s)
```

We can also use `!in` to assure that a value is not in range. Ex:

```kotlin
fun isNotDigit(c:Char)=c !in '0'..'9'
```

It can also be used together with `when`. Ex: 

```kotlin
fun testInWithWhen(c:Char):String{
    return when(c){
        in '0'..'9'-> "It's digit"
        in 'a'..'z'-> "It's a letter"
        else -> "unknown"
    }
}
```


### Ranges

You can test if a value is in a range with different approaches:

```kotlin
data class MyDate(val year:Int, val month:Int, val dayOfMonth:Int):Comparable<MyDate>{
    override fun compareTo(other: MyDate): Int {
        return when{
            year!=other.year->year-other.year
            month!=other.month->month-other.month
            else->dayOfMonth - other.dayOfMonth
        }
    }
}

val intRange:IntRange=1..9//includes 9!
val anotherIntRange=1 until 10//does not include 10!
val charRange: CharRange='a'..'z'
val stringRange="ab".."az"
val dt1=MyDate(2000,9,20)
val dt2=MyDate(2020,9,20)
val dateInRange=MyDate(2005,1,1) in dt1..dt2//returns true
```

## Exceptions

There is no need to use `@Throws` annotation when using pure kotlin code.

Follow a sample on how to throw an exception manually: 

```kotlin
fun testException(number:Int){
    val percentage=if(number in 0..100)number
    else throw IllegalArgumentException("A percentage value must be between 0 and 100")
}

testException(200)//returns Exception in thread "main" java.lang.IllegalArgumentException: A percentage value must be between 0 and 100
```

You can create custom exceptions like this:

```kotlin
class CustomException(message:String): Exception(message)
```

and throw it:

```kotlin
throw CustomException("Mafalda caiu")
```


# Extensions

One of the cool things in Kotlin is to create extension functions for classes. It's even possible to create extensions for classes that we do not have the source code.  Follow a simple sample on how to create an extension function of String class that returns the last char of a String. The function can be written anywhere outside any class: 

```kotlin
//Extensions.kt
package com.mycompany.extensions

fun String.lastChar():Char{
    return this.get(this.length-1)
}
```
To use the extension created above, we just need to import it. The function extension above is found by IntelliJ and code completion works and popups the option `.lastChar()` whenever working with Strings. 

```kotlin
//main.kt
import com.mycompany.extensions.*

val name="Joselito"
val lastChar=name.lastChar()//returns o
```

**Note**: It's not possible to call a `private`  function from an extension. 

## Until extension

`until` returns a range that can be iterated later that goes up to the **PRIOR** element. It can be written in two ways: 

```kotlin
var myRange:IntRange=1.until(7)//returns 1,2,3,4,5,6
```

or

```kotlin
val myRange=1 until 7
for(i in myRange){
	println(i)//prints 1,2,3,4,5,6
}
```

Until is considered an `infix extension function` . Infix indicates that the function can be called without `.` and without parentheses. 

# Standard Library

Kotlin provides several features in its [Standard Library](https://kotlinlang.org/api/latest/jvm/stdlib/). Kotlin Stdlib is composed by java standard library + some kotlin `function extensions`. 

## Collections

It's possible to create collections by several ways in Kotlin. Follow a few examples below:

`val list = listOf('a', 'b', 'c')`: Creates a read-only list of Char. You cannot add new Chars into this list

`val list: MutableList<String> = mutableListOf()`: Creates a mutable list of Strings. You can add new Strings into this list

`val list=mutableListOf<String>("Bozo")`: Creates a mutable list of Strings. 

`val list= arrayListOf<Int>(1,2,3,4)`: Creates a read-write ArrayList

`var element=list.getOrNull(n)`: Extension method that returns the element at position `n` or null if there is no element at position `n`. 

`val mySet= setOf<String>("Bozo","Mafalda")`: Creates a set with values Bozo and Mafalda. Sets are readonly

`val hashSet=hashSetOf(1,10,20)`: Creates a hashset with values 1,10,20. Oposed to `set`, `hashset` is read-write. You can write elements later in it. 

As you might see above, listOf(...) always return a imutable list.

You can find out the corresponding Java class name of Std class like this:

```kotlin
val list= arrayListOf<Int>(1,2,3,4)
var javaClassName=list.javaClass//java.util.ArrayList
```

## Conversions

Kotlin provides several function extensions to convert values. Ex:

```kotlin
4.toString()//"4"
"4".toDouble()//4.0
4.toInt()//4
"barbosa".toIntOrNull()//null
```
