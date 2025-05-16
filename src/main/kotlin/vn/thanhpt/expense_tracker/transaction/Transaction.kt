package vn.thanhpt.expense_tracker.transaction

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Table(name = "transactions")
@Schema(description = "Transaction entity representing a financial transaction")
data class Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(description = "Unique identifier of the transaction", example = "1")
        val id: Long = 0,
        @Schema(description = "ID of the transaction type", example = "1") val typeId: Long = 0,
        @Schema(description = "Name of the transaction type", example = "Food")
        val typeName: String = "",
        @Schema(
                description = "Month of the transaction (1-12)",
                example = "5",
                minimum = "1",
                maximum = "12"
        )
        val month: Int = 0,
        @Schema(description = "Year of the transaction", example = "2024") val year: Int = 0,
        @Schema(description = "Amount of the transaction", example = "100000") val value: Long = 0,
        @Schema(
                description = "Mode of the transaction (0: Expense, 1: Income)",
                example = "0",
                allowableValues = ["0", "1"]
        )
        val mode: Int = 0,
        @Schema(description = "Description of the transaction", example = "Lunch at restaurant")
        val description: String = "",
        @Schema(
                description = "Unix timestamp when the transaction was created",
                example = "1715846400"
        )
        val createdTime: Int = 0,
        @Schema(
                description = "Unix timestamp when the transaction was last updated",
                example = "1715846400"
        )
        val updateTime: Int = 0,
        @Schema(
                description = "ID of the entity this transaction is for (e.g., family ID)",
                example = "1"
        )
        val transactionFor: Int = 0
) {
    constructor() : this(0, 0, "", 0, 0, 0, 0, "", 0, 0, 0)
}
