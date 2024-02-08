import android.health.connect.datatypes.units.Length

fun main() {
    ageChecker()
    println(maximumNumber(1, 18, 8))
    println(minimumNumber(1, 18, -8))
    println(calculateAverage(listOf(2, 5, 4, 1, 6, 8)))
    CPRChecker()
    numberPrinter()
    nameAbbreviator("Viktor", "Mekis", "Bach")
    gradeConverter(90)
    println(filterWordsByLength(listOf("hej", "med", "dig", "jeg", "hedder", "Viktor"), 5))
    val isbn1 = "0471958697"
    val isbn2 = "0-321-14653-0"
    val isbn3 = "877195869x"
    val isbn4 = "9780470059029"

    println("ISBN $isbn1 is valid: ${isValidISBN10(isbn1)}")
    println("ISBN $isbn2 is valid: ${isValidISBN10(isbn2)}")
    println("ISBN $isbn3 is valid: ${isValidISBN10(isbn3)}")
    println("ISBN $isbn4 is valid: ${isValidISBN10(isbn4)}")
}
// Exercise 1
// Define a method to find out if he/she is eligible to vote.

fun ageChecker() {
    println("Please enter your age")
    val usersAge: Int = readln().toInt()
    if (usersAge >= 18) {
        println("You are eligible to vote")
    } else {
        println("You are ineligible to vote")
    }
}

// Exercise 2
// Define two functions to print the maximum and the minimum number respectively among three numbers

fun maximumNumber(n1: Int, n2: Int, n3: Int): Int {
    return maxOf(n1, maxOf(n2, n3))
}

fun minimumNumber(n1: Int, n2: Int, n3: Int): Int {
    return minOf(n1, minOf(n2, n3))
}

// Exercise 3
// Write a Kotlin function named calculateAverage that takes in a list of numbers and returns their average.

fun calculateAverage(list: List<Int>): Double {
    return (list).average()
}

// Exercise 4
// Write a method that returns if a user has input a valid CPR number.

fun CPRChecker(): Boolean {
    println("Please enter your CPR number")
    val usersCPRNumber: String = readln()
    if (usersCPRNumber.length != 10) {
        println("Invalid CPR number")
        return false
    }
    val firstTwoDigits: String = usersCPRNumber[0].toString() + usersCPRNumber[1].toString()
    val middleTwoDigits: String = usersCPRNumber[5].toString() + usersCPRNumber[6].toString()

    return if (firstTwoDigits > 31.toString() || middleTwoDigits > 12.toString()) {
        println("Invalid CPR number")
        false
    } else {
        println("Valid CPR number")
        true
    }
}

// Exercise 5
// Write a program that prints the numbers from 1 to 100.

fun numberPrinter() {
    for (i in 1..100) {
        when {
            i % 3 == 0 && i % 5 == 0 -> println("FizzBuzz")
            i % 3 == 0 -> print("Fizz")
            i % 5 == 0 -> print("Buzz")
            else -> print(i)
        }
    }
}

// Exercise 6
// Write a program that takes your full name as input and displays the abbreviations of the first and middle names except the last name which is displayed as it is.

fun nameAbbreviator(firstName: String, middleName: String, lastName: String) {
    val firstNameAbbreviated: Char = firstName[0]
    val middleNameAbbreviated: Char = middleName[0]
    println("${firstNameAbbreviated}. ${middleNameAbbreviated}. ${lastName}")
}

// Exercise 7
// Write a program that takes a numerical grade (0-100) as input and prints out the corresponding american letter grade.

fun gradeConverter(grade: Int): Any {
    println("Enter the numerical grade (0-100): ")
    val grade: Int? = readlnOrNull()?.toInt()
    return when {
        grade in 90..100 -> println("A")
        grade in 80..89 -> println("B")
        grade in 70..79 -> println("C")
        grade in 60..69 -> println("D")
        grade in 0..59 -> println("F")
        else -> println("Invalid grade. Please enter a numerical grade between 0 and 100.")
    }
}

// Exercise 8
// filterWordsByLength

fun filterWordsByLength(list: List<String>, minimumLength: Int): List<String> {
    return list.filter { it.length >= minimumLength }
}

// Exercise 9
// ISBN Validation

fun isValidISBN10(isbn: String): Boolean {
    // Remove hyphens and convert to uppercase
    val sanitizedISBN = isbn.replace("-", "").uppercase()

    // Check length
    if (sanitizedISBN.length != 10)
        return false

    // Convert characters to numeric values
    val numericValues = sanitizedISBN.map {
        if (it == 'X') 10 else it.toString().toIntOrNull()
    }

    // Check if all characters are valid numeric values
    if (numericValues.contains(null))
        return false

    // Calculate the sum according to the formula
    val sum = (1..10).map { i ->
        numericValues[i - 1]!! * (11 - i)
    }.sum()

    // Check if the sum is divisible by 11
    return sum % 11 == 0
}