package com.receiptProcessor.receiptProcessor.services

import com.receiptProcessor.receiptProcessor.exceptions.ResourceNotFoundException
import com.receiptProcessor.receiptProcessor.models.ReceiptId
import com.receiptProcessor.receiptProcessor.repositories.ReceiptRepository

object GetReceiptPointsService {
    fun execute(id: ReceiptId): Double {
        val receipt = ReceiptRepository.findBy(id) ?: throw ResourceNotFoundException("No receipt found for that id")
        return receipt.calculatePoints()
    }
}
