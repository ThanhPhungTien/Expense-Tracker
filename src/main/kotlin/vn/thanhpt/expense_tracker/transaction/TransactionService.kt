package vn.thanhpt.expense_tracker.transaction

import org.springframework.stereotype.Service

@Service
class TransactionService(private val repo: TransactionRepository) {
    fun findAll(): List<Transaction> = repo.findAll()
    fun findById(id: Long): Transaction? = repo.findById(id).orElse(null)
    fun save(transaction: Transaction): Transaction = repo.save(transaction)
    fun update(id: Long, transaction: Transaction): Transaction? {
        return if (repo.existsById(id)) repo.save(transaction.copy(id = id)) else null
    }
    fun delete(id: Long) = repo.deleteById(id)
}
