package vn.thanhpt.expense_tracker.transaction

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction", description = "API liên quan đến giao dịch")
class TransactionController(private val service: TransactionService) {

        @Operation(
                summary = "Lấy toàn bộ transaction",
                description = "Retrieves a list of all transactions"
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully retrieved all transactions"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @GetMapping
        fun getAll() = service.findAll()

        @Operation(
                summary = "Get transaction by ID",
                description = "Retrieves a specific transaction by its ID"
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully retrieved the transaction"
                                ),
                                ApiResponse(
                                        responseCode = "404",
                                        description = "Transaction not found"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @GetMapping("/{id}")
        fun getById(
                @Parameter(description = "ID of the transaction to retrieve") @PathVariable id: Long
        ) = service.findById(id)

        @Operation(summary = "Create new transaction", description = "Creates a new transaction")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "201",
                                        description = "Successfully created the transaction"
                                ),
                                ApiResponse(responseCode = "400", description = "Invalid input"),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @PostMapping
        fun create(
                @Parameter(description = "Transaction object to create")
                @RequestBody
                transaction: Transaction
        ) = service.save(transaction)

        @Operation(
                summary = "Update transaction",
                description = "Updates an existing transaction by its ID"
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully updated the transaction"
                                ),
                                ApiResponse(
                                        responseCode = "404",
                                        description = "Transaction not found"
                                ),
                                ApiResponse(responseCode = "400", description = "Invalid input"),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @PutMapping("/{id}")
        fun update(
                @Parameter(description = "ID of the transaction to update") @PathVariable id: Long,
                @Parameter(description = "Updated transaction object")
                @RequestBody
                transaction: Transaction
        ) = service.update(id, transaction)

        @Operation(summary = "Delete transaction", description = "Deletes a transaction by its ID")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "204",
                                        description = "Successfully deleted the transaction"
                                ),
                                ApiResponse(
                                        responseCode = "404",
                                        description = "Transaction not found"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                )]
        )
        @DeleteMapping("/{id}")
        fun delete(
                @Parameter(description = "ID of the transaction to delete") @PathVariable id: Long
        ) = service.delete(id)
}
