package com.sid.numberhighlighter

import java.lang.Math.sqrt

sealed class RuleType {
    abstract fun checkCondition(number: Int): Boolean

    object Odd : RuleType() {
        override fun checkCondition(number: Int) = number % 2 != 0
    }

    object Even : RuleType() {
        override fun checkCondition(number: Int) = number % 2 == 0
    }

    object Prime : RuleType() {
        override fun checkCondition(number: Int): Boolean {
            if (number < 2) return false
            for (i in 2..sqrt(number.toDouble()).toInt()) {
                if (number % i == 0) return false
            }
            return true
        }
    }

    object Fibonacci : RuleType() {
        private val fibNumbers = generateFibonacci()

        override fun checkCondition(number: Int): Boolean = fibNumbers.contains(number)

        private fun generateFibonacci(): Set<Int> {
            val fibSet = mutableSetOf(0, 1)
            var a = 0
            var b = 1
            while (b <= 100) {
                val temp = a + b
                a = b
                b = temp
                fibSet.add(b)
            }
            return fibSet
        }
    }
}