package com.receiptProcessor.receiptProcessor.controllers

import com.receiptProcessor.receiptProcessor.models.ReceiptId
import com.receiptProcessor.receiptProcessor.services.GetReceiptPointsService
import com.receiptProcessor.receiptProcessor.services.ProcessReceiptParams
import com.receiptProcessor.receiptProcessor.services.ProcessReceiptService
import org.springframework.web.bind.annotation.*

@RestController
class ReceiptController {
    @PostMapping("/receipts/process")
    fun processReceipt(@RequestBody params: ProcessReceiptParams): ProcessReceiptParamsResponse {
        val id = ProcessReceiptService.execute(params)
        return ProcessReceiptParamsResponse(id.value)
    }

    @GetMapping("/receipts/{id}/points")
    fun getReceiptPoints(@PathVariable id: String): GetReceiptPointsResponse {
        val points = GetReceiptPointsService.execute(ReceiptId(id))
        return GetReceiptPointsResponse(points.toInt())
    }
}


data class ProcessReceiptParamsResponse(val id: String)
data class GetReceiptPointsResponse(val points: Int)
