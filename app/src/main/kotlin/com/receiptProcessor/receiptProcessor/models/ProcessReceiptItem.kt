package com.receiptProcessor.receiptProcessor.models

import kotlin.math.ceil

data class ProcessReceiptItem(
    val shortDescription: String,
    val price: Double,
) {
    fun trimmedLengthOfDescription(): Int {
        return shortDescription
            .trim()
            .filter { char ->
                char.isLetterOrDigit() || char == '_' || char == ' ' || char == '-'
            }.length
    }

    fun calculatePoints(): Double =
        if (this.trimmedLengthOfDescription() % 3 == 0)
            ceil(price * 0.2)
        else
            0.0

    companion object {
        fun calculatePoints(items: List<ProcessReceiptItem>): Double {
            var points = 0.0
            items.map { item -> points += item.calculatePoints() }

            return points
        }
    }
}
