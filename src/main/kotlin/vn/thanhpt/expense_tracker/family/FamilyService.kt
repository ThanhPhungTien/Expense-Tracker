package vn.thanhpt.expense_tracker.family

import org.springframework.stereotype.Service

@Service
class FamilyService(private val repo: FamilyRepository) {
    fun findAll(): List<Family> = repo.findAll()
    fun findById(id: Long): Family? = repo.findById(id).orElse(null)
    fun save(family: Family): Family = repo.save(family)
    fun update(id: Long, family: Family): Family? {
        return if (repo.existsById(id)) repo.save(family.copy(id = id)) else null
    }
    fun delete(id: Long) = repo.deleteById(id)
    fun searchByTag(tag: String): List<Family> = repo.findByTagContainingIgnoreCase(tag)
    fun searchByName(name: String): List<Family> = repo.findByNameContainingIgnoreCase(name)
}
