package vn.thanhpt.expense_tracker.transaction

import jakarta.persistence.*

@Entity
@Table(name = "transactions")
data class Transaction(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val typeId: Long = 0,
        val typeName: String = "",
        val month: Int = 0,
        val year: Int = 0,
        val value: Long = 0,
        val mode: Int = 0,
        val description: String = "",
        val createdTime: Int = 0,
        val updateTime: Int = 0,
        val transactionFor: Int = 0
) {
    constructor() : this(0, 0, "", 0, 0, 0, 0, "", 0, 0, 0)
}
