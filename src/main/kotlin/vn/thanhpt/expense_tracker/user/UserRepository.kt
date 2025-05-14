package vn.thanhpt.expense_tracker.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long> {
    fun findByPhone(phone: String): User?
    fun findByEmail(email: String): User?

    @Query(
            """
        SELECT u FROM User u JOIN Family f ON u.familyId = f.id WHERE LOWER(f.tag) LIKE LOWER(CONCAT('%', :tag, '%'))
    """
    )
    fun findByFamilyTagLike(@Param("tag") tag: String): List<User>
}
