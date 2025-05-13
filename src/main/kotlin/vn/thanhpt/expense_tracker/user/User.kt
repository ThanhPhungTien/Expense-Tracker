package vn.thanhpt.expense_tracker.user

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
        @Id val id: String = "",
        val name: String = "",
        val email: String = "",
        val phone: String = ""
)
