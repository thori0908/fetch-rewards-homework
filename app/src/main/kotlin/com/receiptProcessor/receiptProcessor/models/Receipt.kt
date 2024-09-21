package com.receiptProcessor.receiptProcessor.models

import java.time.LocalDateTime

data class Receipt(
    val id: ReceiptId,
    val retailer: String,
    val purchasedAt: LocalDateTime,
    val items: List<ProcessReceiptItem>,
    val total: Double,

) {
    // 6 points - retailer name has 6 characters
    // 10 points - 4 items (2 pairs @ 5 points each)
    // 3 Points - "Emils Cheese Pizza" is 18 characters (a multiple of 3)
    // item price of 12.25 * 0.2 = 2.45, rounded up is 3 points
    // 3 Points - "Klarbrunn 12-PK 12 FL OZ" is 24 characters (a multiple of 3)
    // item price of 12.00 * 0.2 = 2.4, rounded up is 3 points
    // 6 points - purchase day is odd
    fun calculatePoints(): Double = TODO()
}

data class ProcessReceiptItem(
    val shortDescription: String,
    val price: Double,
)

data class ReceiptId(val value: String)
