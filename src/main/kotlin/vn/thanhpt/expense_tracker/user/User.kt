package vn.thanhpt.expense_tracker.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @Column(nullable = false) val name: String,
        @Column(nullable = false, unique = true) val email: String,
        @Column(nullable = false, unique = true) val phone: String,
        val familyId: Long? = null,
        val isFamilyAdmin: Boolean = false
) {
        constructor() : this(name = "", email = "", phone = "")
}
