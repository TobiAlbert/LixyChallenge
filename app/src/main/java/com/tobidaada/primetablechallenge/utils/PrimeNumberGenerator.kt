package com.tobidaada.primetablechallenge.utils

object PrimeNumberGenerator {
    fun generatePrimeNumbers(length: Int): List<Int> {
        val primeNumbers = ArrayList<Int>()

        var counter = 0
        var number = 2
        while (counter < length) {
            if (isPrimeNumber(number)) {
                primeNumbers.add(number)
                counter++
            }

            number++
        }

        return primeNumbers
    }

    private fun isPrimeNumber(number: Int): Boolean {
        var i = 2
        while (i < number) {
            if (number % i == 0) {
                return false
            }

            i++
        }
        return true
    }
}