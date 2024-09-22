package com.receiptProcessor.receiptProcessor.models

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class ReceiptTest : DescribeSpec({
    describe("calculatePoints") {
        it("should be 28") {
            val receipt = buildReceipt(
                retailer = "Target",
                purchaseDate = "2022-01-01",
                purchaseTime = "13:01",
                items = listOf(
                    ProcessReceiptItem("Mountain Dew 12PK", 6.49),
                    ProcessReceiptItem("Emils Cheese Pizza", 12.25),
                    ProcessReceiptItem("Knorr Creamy Chicken", 1.26),
                    ProcessReceiptItem("Doritos Nacho Cheese", 3.35),
                    ProcessReceiptItem("   Klarbrunn 12-PK 12 FL OZ  ", 12.00),
                ),
                total = 35.35,
            )

            receipt.calculatePoints() shouldBe 28
        }
    }

    describe("isOddDate") {
        it("is odd if 2022-01-01") {
            val receipt = buildReceipt(
                purchaseDate = "2022-01-01",
                purchaseTime = "13:01",
                total = 35.35,
            )

            receipt.isOddDate() shouldBe true
        }

        it("is even if 2022-01-02") {
            val receipt = buildReceipt(
                purchaseDate = "2022-01-02",
                purchaseTime = "13:01",
                total = 35.35,
            )

            receipt.isOddDate() shouldBe false
        }
    }

    describe("isBetween") {
        it("should return true if it is 15:00") {
            "15:00".toTime().isBetween(startTime = "14:00".toTime(), endTime = "16:00".toTime()) shouldBe true
        }

        it("should return false if it is 11:00") {
            "11:00".toTime().isBetween(startTime = "14:00".toTime(), endTime = "16:00".toTime()) shouldBe false
        }

        it("should return false if it is 17:00") {
            "17:00".toTime().isBetween(startTime = "14:00".toTime(), endTime = "16:00".toTime()) shouldBe false
        }
    }

    describe("countAlphaNumeric") {
        it("should return 6 if it is Target") {
            "Target".countAlphaNumeric() shouldBe 6
        }

        it("should not count & and space") {
           "M&M Corner Market".countAlphaNumeric() shouldBe 14
        }
    }

    describe("countPairs") {
        it("count pairs") {
            listOf(
                ProcessReceiptItem("Mountain Dew 12PK", 6.49),
                ProcessReceiptItem("Emils Cheese Pizza", 12.25),
                ProcessReceiptItem("Knorr Creamy Chicken", 1.26),
                ProcessReceiptItem("Doritos Nacho Cheese", 3.35),
                ProcessReceiptItem("   Klarbrunn 12-PK 12 FL OZ  ", 12.00),
            ).countPairs() shouldBe 2
        }

    }
})

private fun buildReceipt(
    id: ReceiptId = ReceiptId(""),
    retailer: String = "Target",
    purchaseDate: String,
    purchaseTime: String,
    items: List<ProcessReceiptItem> = listOf(),
    total: Double,
): Receipt =
    Receipt(
        id,
        retailer,
        purchaseDate,
        purchaseTime,
        items,
        total,
    )
