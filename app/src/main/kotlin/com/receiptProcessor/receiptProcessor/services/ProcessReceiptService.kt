package com.receiptProcessor.receiptProcessor.services

import com.receiptProcessor.receiptProcessor.exceptions.InvalidArgumentException
import com.receiptProcessor.receiptProcessor.models.ProcessReceiptItem
import com.receiptProcessor.receiptProcessor.models.ReceiptId
import com.receiptProcessor.receiptProcessor.repositories.ReceiptRepository
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object ProcessReceiptService {
    fun execute(params: ProcessReceiptParams): ReceiptId {
        return ReceiptRepository.create(
            retailer = params.validRetailer(),
            purchaseDate = params.validatePurchaseDate(),
            purchaseTime = params.validatePurchaseTime(),
            items = params.validateItems(),
            total = params.validateTotal(),
        )
    }
}

data class ProcessReceiptParams(
    val retailer: String,
    val purchaseDate: String,
    val purchaseTime: String,
    val items: List<ProcessReceiptItemParams>,
    val total: String,
) {
    fun validRetailer(): String =
        this.retailer.ifEmpty { throw InvalidArgumentException("retailer is invalid") }

    fun validatePurchaseDate(): String {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            LocalDate.parse(purchaseDate, formatter)
            return this.purchaseDate
        } catch (e: DateTimeParseException) {
            throw InvalidArgumentException("purchaseDate is invalid")
        }
    }

    fun validateItems(): List<ProcessReceiptItem> =
        if (this.items.isNotEmpty())
            this.items.map { it.toModel() }
        else
            throw InvalidArgumentException("items is invalid")

    fun validatePurchaseTime(): String {
        try {
            LocalTime.parse(purchaseTime)
            return this.purchaseTime
        } catch (e: DateTimeParseException) {
            throw InvalidArgumentException("purchaseTime is invalid")
        }
    }

    fun validateTotal(): Double =
        this.total.toDoubleOrNull() ?: throw InvalidArgumentException("total is invalid")
}

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
