// Explore a simple class

println("UW Homework: Simple Kotlin")


// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn(x: Any): String{

    when(x){
       "Hello" -> return "world"
        0 -> return "zero"
        1 ->   return "one"
        2..10 -> return "low number"
        !in 2..10 -> return "a number"
        else -> return "I don't understand"
    }
}



// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(x: Int, y: Int) :Int{
    return x + y
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values

fun sub(a: Int, b: Int) :Int{
    return a - b
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments

fun mathOp(c: Int, d: Int, otherFun: (e: Int, f: Int) -> Int) :Int{
    return otherFun(c, d)
}

// write a class "Person" with first name, last name and age

class Person (val firstName: String, val lastName: String, var age: Int ){
  public override fun equals(other: Any?): Boolean {
      if(other == null || other !is Person)
        return false
    return this.firstName.equals(other.firstName) &&
            this.lastName.equals(other.lastName) &&
            this.age.equals(other.age)               
  }
  public override fun hashCode(): Int { 
      return firstName.hashCode() and  lastName.hashCode() 
}
  val debugString: String 
    get() = "[Person firstName:$firstName lastName:$lastName age:$age]"  
}


// write a class "Money"

class Money (val amount: Int, val currency: String){
  
    fun convert(otherCurrency: String): Money{
        var result = 0
         when (otherCurrency){
            "USD" -> 
                if(currency == "EUR" && amount > 0) {
                    result = 10 * amount / 15
                } else if (currency == "GBP" && amount > 0){
                    result = 10 * amount / 5
                } else if (currency == "CAN" && amount > 0){
                    result = 12 * amount / 15
                }
            "EUR" ->
                if(currency == "USD" && amount > 0) {
                    result = 15 * amount / 10
                } else if (currency == "GBP" && amount > 0){
                    result = 15 * (10 * amount / 5) / 10
                } else if (currency == "CAN" && amount > 0){
                    result = 15 * (12 * amount / 15) / 10
                }
            
            "GBP" -> 
                if(currency == "EUR" && amount > 0) {
                    result = 5 * (10 * amount / 15) / 10
                } else if (currency == "USD" && amount > 0){
                    result = 5 * amount / 10
                } else if (currency == "CAN" && amount > 0) {
                    result = 5 * (12 * amount / 15) / 10
                }
            "CAN" -> 
                if(currency == "EUR" && amount > 0) {
                    result = 15 * (10 * amount / 15) / 10
                } else if (currency == "GBP" && amount > 0){
                    result = 15 * (10 * amount / 5) / 10
                } else if (currency == "USD" && amount > 0){
                    result = 15 * amount / 12
                }
            else -> println("incorrect currency")
        }
        return Money(result, otherCurrency)
    }

    operator fun plus(a: Money): Money{
        return Money(amount + a.amount, currency)
    }
}



// ============ DO NOT EDIT BELOW THIS LINE =============

 print("When tests: ")
 val when_tests = listOf(
     "Hello" to "world",
     "Howdy" to "Say what?",
     "Bonjour" to "Say what?",
     0 to "zero",
     1 to "one",
     5 to "low number",
     9 to "low number",
     17.0 to "I don't understand"
 )
 for ((k,v) in when_tests) {
     print(if (whenFn(k) == v) "." else "!")
 }
 println("")

 print("Add tests: ")
 val add_tests = listOf(
     Pair(0, 0) to 0,
     Pair(1, 2) to 3,
     Pair(-2, 2) to 0,
     Pair(123, 456) to 579
 )
 for ( (k,v) in add_tests) {
     print(if (add(k.first, k.second) == v) "." else "!")
 }
 println("")

 print("Sub tests: ")
 val sub_tests = listOf(
     Pair(0, 0) to 0,
     Pair(2, 1) to 1,
     Pair(-2, 2) to -4,
     Pair(456, 123) to 333
 )
 for ( (k,v) in sub_tests) {
     print(if (sub(k.first, k.second) == v) "." else "!")
 }
 println("")

 print("Op tests: ")
 print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
 print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
 print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
 print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
 println("")


 print("Person tests: ")
 val p1 = Person("Ted", "Neward", 47)
 print(if (p1.firstName == "Ted") "." else "!")
 p1.age = 48
 print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
 println("")

 print("Money tests: ")
 val tenUSD = Money(10, "USD")
 val twelveUSD = Money(12, "USD")
 val fiveGBP = Money(5, "GBP")
 val fifteenEUR = Money(15, "EUR")
 val fifteenCAN = Money(15, "CAN")
 val convert_tests = listOf(
     Pair(tenUSD, tenUSD),
     Pair(tenUSD, fiveGBP),
     Pair(tenUSD, fifteenEUR),
     Pair(twelveUSD, fifteenCAN),
     Pair(fiveGBP, tenUSD),
     Pair(fiveGBP, fifteenEUR)
 )
 for ( (from,to) in convert_tests) {
     print(if (from.convert(to.currency).amount == to.amount) "." else "!")
 }
 val moneyadd_tests = listOf(
     Pair(tenUSD, tenUSD) to Money(20, "USD"),
     Pair(tenUSD, fiveGBP) to Money(20, "USD"),
     Pair(fiveGBP, tenUSD) to Money(10, "GBP")
 )
 for ( (pair, result) in moneyadd_tests) {
     print(if ((pair.first + pair.second).amount == result.amount &&
               (pair.first + pair.second).currency == result.currency) "." else "!")
 }
 println("")
