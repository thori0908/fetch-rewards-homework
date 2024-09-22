package com.receiptProcessor.receiptProcessor.models

data class ProcessReceiptItem(
    val shortDescription: String,
    val price: Double,
) {
    fun trimmedLengthOfDescription(): Int {
        return shortDescription
            .trim()
            .filter { char ->
                char.isLetterOrDigit() || char == '_' || char == ' ' || char == '-'
            }.length
    }
}
