package com.receiptProcessor.receiptProcessor.models

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class ProcessReceiptItemTest : DescribeSpec({
    describe("trimmedLengthOfDescription") {
        it("the length of '   Klarbrunn 12-PK 12 FL OZ  ' should be 24") {
            val processReceiptItem = buildProcessReceiptItem("   Klarbrunn 12-PK 12 FL OZ  ")
            processReceiptItem.trimmedLengthOfDescription() shouldBe 24
        }

        it("the length of 'Klarbrunn' should be 9") {
            val processReceiptItem = buildProcessReceiptItem("Klarbrunn")
            processReceiptItem.trimmedLengthOfDescription() shouldBe 9
        }
    }

    describe("calculatePoints") {
        it("calculate") {
            ProcessReceiptItem("Emils Cheese Pizza", 12.25).calculatePoints() shouldBe 3.0
            ProcessReceiptItem("   Klarbrunn 12-PK 12 FL OZ  ", 12.00).calculatePoints() shouldBe 3.0
        }
    }
})

private fun buildProcessReceiptItem(
    shortDescription: String,
    price: Double = 10.0,
): ProcessReceiptItem = ProcessReceiptItem(shortDescription, price)
