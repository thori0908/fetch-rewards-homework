package com.receiptProcessor.receiptProcessor.models

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

data class Receipt(
    val id: ReceiptId,
    val retailer: String,
    val purchaseDate: String,
    val purchaseTime: String,
    val items: List<ProcessReceiptItem>,
    val total: Double,
) {
    fun calculatePoints(): Double {
        var points = 0.0
        // One point for every alphanumeric character in the retailer name.
        points += retailer.countAlphaNumeric()

        // 50 points if the total is a round dollar amount with no cents.
        if (floor(total) == total) {
            points += 50
        }

        // 25 points if the total is a multiple of 0.25.
        if (total % 0.25 == 0.0) {
            points += 25
        }

        // 5 points for every two items on the receipt.
        points += (items.countPairs()) * 5

        // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        points += ProcessReceiptItem.calculatePoints(items)

        // 6 points if the day in the purchase date is odd.
        if (isOddDate()) {
            points += 6
        }

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        if (purchaseTime.toTime().isBetween(startTime = "14:00".toTime(), endTime = "16:00".toTime())) {
            points += 10
        }

        return points
    }

    fun isOddDate(): Boolean {
        val dateOfMonth = this.purchaseDate.split("-").getOrNull(2)?.toInt() ?: return false

        return dateOfMonth % 2 == 1
    }
}

fun String.countAlphaNumeric(): Int =
    this.filter { char -> char.isLetterOrDigit() }.length

fun String.toTime(): Date {
    val format = SimpleDateFormat("HH:mm")
    return format.parse(this)
}

fun List<ProcessReceiptItem>.countPairs(): Int = this.size / 2

fun Date.isBetween(startTime: Date, endTime: Date): Boolean {
    return this.after(startTime) && this.before(endTime)
}

data class ReceiptId(val value: String) {
    companion object {
        fun generate(): ReceiptId = ReceiptId(UUID.randomUUID().toString())
    }
}
