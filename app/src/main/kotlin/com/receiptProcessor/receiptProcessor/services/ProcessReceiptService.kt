package com.receiptProcessor.receiptProcessor.services

import com.receiptProcessor.receiptProcessor.controllers.ProcessReceiptParams
import com.receiptProcessor.receiptProcessor.models.ReceiptId
import com.receiptProcessor.receiptProcessor.repositories.ReceiptRepository

object ProcessReceiptService {
    fun execute(params: ProcessReceiptParams): ReceiptId {
        return ReceiptRepository.create()
    }
}
