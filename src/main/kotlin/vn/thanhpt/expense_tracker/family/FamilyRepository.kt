package vn.thanhpt.expense_tracker.family

import org.springframework.data.jpa.repository.JpaRepository

interface FamilyRepository : JpaRepository<Family, Long> {
    fun findByTagContainingIgnoreCase(tag: String): List<Family>
    fun findByNameContainingIgnoreCase(name: String): List<Family>
}
