package com.example.scaredclarity

import android.content.Context

class VerseRepository(context: Context) {

    val verses = mutableListOf<Verse>()

    init {

        val inputStream = context.assets.open("verses.csv")
        val reader = inputStream.bufferedReader()

        reader.readLine() // skip header

        reader.forEachLine { line ->

            val columns = parseCSVLine(line)

            if (columns.size >= 6) {

                val emotion = columns[0]
                val description = columns[1] + " " + columns[4]
                val verseNumber = columns[2]
                val lesson = columns[5]

                verses.add(
                    Verse(
                        emotion = emotion,
                        description = description,
                        verseNumber = verseNumber,
                        lesson = lesson
                    )
                )
            }
        }
    }

    private fun parseCSVLine(line: String): List<String> {

        val result = mutableListOf<String>()
        val current = StringBuilder()
        var insideQuotes = false

        for (char in line) {

            when (char) {

                '"' -> insideQuotes = !insideQuotes

                ',' -> {
                    if (insideQuotes) {
                        current.append(char)
                    } else {
                        result.add(current.toString())
                        current.clear()
                    }
                }

                else -> current.append(char)
            }
        }

        result.add(current.toString())

        return result
    }
}