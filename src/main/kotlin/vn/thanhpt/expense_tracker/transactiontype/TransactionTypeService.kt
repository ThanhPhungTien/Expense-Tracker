package vn.thanhpt.expense_tracker.transactiontype

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TransactionTypeService(private val repo: TransactionTypeRepository) {

    fun findAll(): List<TransactionType> = repo.findAll()

    fun findById(id: Long): TransactionType? = repo.findById(id).orElse(null)

    fun save(type: TransactionType): TransactionType {
        print("type name ${type.name}")
        repo.findByName(type.name)?.let {
            throw ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "TransactionType name already exists"
            )
        }
        return repo.save(type)
    }

    fun update(id: Long, type: TransactionType): TransactionType? {
        repo.findByName(type.name)?.let {
            if (it.id != id)
                    throw ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "TransactionType name already exists"
                    )
        }
        return if (repo.existsById(id)) repo.save(type.copy(id = id)) else null
    }

    fun delete(id: Long) = repo.deleteById(id)
}
