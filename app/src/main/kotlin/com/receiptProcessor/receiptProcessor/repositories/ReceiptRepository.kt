package com.receiptProcessor.receiptProcessor.repositories

import com.receiptProcessor.receiptProcessor.models.ProcessReceiptItem
import com.receiptProcessor.receiptProcessor.models.Receipt
import com.receiptProcessor.receiptProcessor.models.ReceiptId

object ReceiptRepository {
    private val records = mutableMapOf<ReceiptId, Receipt>()

    fun findBy(id: ReceiptId): Receipt? = this.records[id]

    fun create(
        retailer: String,
        purchaseDate: String,
        purchaseTime: String,
        items: List<ProcessReceiptItem>,
        total: Double,
    ): ReceiptId {
        val receipt = Receipt(
            id = ReceiptId.generate(),
            retailer = retailer,
            purchaseDate = purchaseDate,
            purchaseTime = purchaseTime,
            items = items,
            total = total,
        )

        this.records[receipt.id] = receipt

        return receipt.id
    }
}