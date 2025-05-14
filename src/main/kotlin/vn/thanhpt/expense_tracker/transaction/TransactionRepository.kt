package vn.thanhpt.expense_tracker.transaction

import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long>
