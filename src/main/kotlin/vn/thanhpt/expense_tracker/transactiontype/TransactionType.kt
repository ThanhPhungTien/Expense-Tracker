package vn.thanhpt.expense_tracker.transactiontype

import jakarta.persistence.*

@Entity
@Table(name = "transaction_types")
data class TransactionType(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val name: String = "",
        val description: String = "",
        val mode: Int = 0,
        val createTime: Long = 0,
        val updateTime: Long = 0L
) {
        constructor() : this(0, "", "", 0, 0, 0L)
}
