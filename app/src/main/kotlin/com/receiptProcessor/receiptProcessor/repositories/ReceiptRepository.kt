package com.receiptProcessor.receiptProcessor.repositories

import com.receiptProcessor.receiptProcessor.models.Receipt
import com.receiptProcessor.receiptProcessor.models.ReceiptId

object ReceiptRepository {
    fun findBy(id: ReceiptId): Receipt? = null

    fun create(): ReceiptId {
        TODO("Not yet implemented")
    }
}