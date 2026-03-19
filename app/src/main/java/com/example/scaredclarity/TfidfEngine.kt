package com.example.scaredclarity

import kotlin.math.ln
import kotlin.math.sqrt

class TfidfEngine(private val verses: List<Verse>) {

    private val vocabulary = mutableSetOf<String>()
    private val verseVectors = mutableListOf<Map<String, Double>>()
    private val idf = mutableMapOf<String, Double>()

    init {

        val documents = verses.map { preprocess(it.description) }

        documents.forEach { vocabulary.addAll(it) }

        for (word in vocabulary) {

            val count = documents.count { word in it }

            idf[word] = ln(documents.size.toDouble() / (1 + count))
        }

        for (doc in documents) {

            val tf = doc.groupingBy { it }.eachCount()

            val vector = mutableMapOf<String, Double>()

            for (word in vocabulary) {

                val tfValue = (tf[word]?.toDouble() ?: 0.0) / doc.size
                val idfValue = idf[word] ?: 0.0

                vector[word] = tfValue * idfValue
            }

            verseVectors.add(vector)
        }
    }

    fun findBestMatch(userText: String): Verse {

        val tokens = preprocess(userText)

        val tf = tokens.groupingBy { it }.eachCount()

        val queryVector = mutableMapOf<String, Double>()

        for (word in vocabulary) {

            val tfValue = (tf[word]?.toDouble() ?: 0.0) / tokens.size
            val idfValue = idf[word] ?: 0.0

            queryVector[word] = tfValue * idfValue
        }

        var bestScore = -1.0
        var bestIndex = 0

        for (i in verseVectors.indices) {

            val score = cosineSimilarity(queryVector, verseVectors[i])

            if (score > bestScore) {
                bestScore = score
                bestIndex = i
            }
        }

        return verses[bestIndex]
    }

    private fun cosineSimilarity(
        v1: Map<String, Double>,
        v2: Map<String, Double>
    ): Double {

        var dot = 0.0
        var mag1 = 0.0
        var mag2 = 0.0

        for (word in vocabulary) {

            val a = v1[word] ?: 0.0
            val b = v2[word] ?: 0.0

            dot += a * b
            mag1 += a * a
            mag2 += b * b
        }

        if (mag1 == 0.0 || mag2 == 0.0) return 0.0

        return dot / (sqrt(mag1) * sqrt(mag2))
    }
}