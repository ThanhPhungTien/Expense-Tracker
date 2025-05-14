package vn.thanhpt.expense_tracker.transactiontype

import org.springframework.data.jpa.repository.JpaRepository

interface TransactionTypeRepository : JpaRepository<TransactionType, Long> {
    fun findByName(name: String): TransactionType?
}
