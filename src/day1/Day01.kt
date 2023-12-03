package day1

import println
import readInput
import java.io.File

fun main() {
    val d = Day01(File("src/day1/day01.txt").readLines())

    println(d.solvePart1())
    println(d.solvePart2())
}


class Day01(private val input: List<String>) {

    private val words: Map<String, Int> = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun solvePart1(): Int =
        input.sumOf { calibrationValue(it) }

    fun solvePart2(): Int =
        input.sumOf { row ->
            calibrationValue(
                row.mapIndexedNotNull { index, c ->
                    if (c.isDigit())
                        c
                    else
                        row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                            words[candidate]
                        }
                }.joinToString()
            )
        }


    private fun calibrationValue(row: String): Int {
        val firstDigit: Char = row.first(){it.isDigit()}
        val lastDigit: Char = row.last(){it.isDigit()}
        return "$firstDigit$lastDigit".toInt()
    }


    private fun String.possibleWordsAt(startingAt: Int): List<String> =
        (3 until 7).map { len ->
            substring(startingAt, (startingAt + len).coerceAtMost(length))
        }
}
