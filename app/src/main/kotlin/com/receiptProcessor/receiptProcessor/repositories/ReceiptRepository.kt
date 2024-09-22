package com.receiptProcessor.receiptProcessor.repositories

import com.receiptProcessor.receiptProcessor.models.ProcessReceiptItem
import com.receiptProcessor.receiptProcessor.models.Receipt
import com.receiptProcessor.receiptProcessor.models.ReceiptId

object ReceiptRepository {
    fun findBy(id: ReceiptId): Receipt? = TODO()

    fun create(
        retailer: String,
        purchaseDate: String,
        purchaseTime: String,
        items: List<ProcessReceiptItem>,
        total: Double,
    ): ReceiptId {
        TODO("Not yet implemented")
    }
}