package com.receiptProcessor.receiptProcessor.services

import com.receiptProcessor.receiptProcessor.models.ProcessReceiptItem
import com.receiptProcessor.receiptProcessor.models.ReceiptId
import com.receiptProcessor.receiptProcessor.repositories.ReceiptRepository

object ProcessReceiptService {
    fun execute(params: ProcessReceiptParams): ReceiptId {
        return ReceiptRepository.create(
            retailer = params.retailer,
            purchaseDate = params.purchaseDate,
            purchaseTime = params.purchaseTime,
            items = params.items.map { it.toModel() },
            total = params.total.toDouble(),
        )
    }
}

data class ProcessReceiptParams(
    val retailer: String,
    val purchaseDate: String,
    val purchaseTime: String,
    val items: List<ProcessReceiptItemParams>,
    val total: String,
)

data class ProcessReceiptItemParams(
    val shortDescription: String,
    val price: String,
) {
    fun toModel(): ProcessReceiptItem =
        ProcessReceiptItem(
            shortDescription,
            price.toDouble(),
        )
}
