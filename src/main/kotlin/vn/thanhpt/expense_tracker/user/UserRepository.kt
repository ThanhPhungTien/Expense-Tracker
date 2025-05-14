package vn.thanhpt.expense_tracker.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByPhone(phone: String): User?
    fun findByEmail(email: String): User?
}
