package vn.thanhpt.expense_tracker.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class User(
        @Id @GeneratedValue() val id: String = "",
        val name: String = "",
        val email: String = "",
        val phone: String = ""
)
