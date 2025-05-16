package vn.thanhpt.expense_tracker.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
@Schema(description = "User entity representing an application user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(description = "Unique identifier of the user", example = "1")
        val id: Long = 0,
        @Column(unique = true)
        @Schema(description = "Email address of the user", example = "user@example.com")
        val email: String = "",
        @Schema(description = "Hashed password of the user") var hashedPassword: String = "",
        @Schema(description = "Full name of the user", example = "John Doe")
        val fullName: String = "",
        @Schema(description = "Whether the user's email is verified", example = "false")
        val emailVerified: Boolean = false,
        @Schema(description = "Unix timestamp when the user was created", example = "1715846400")
        val createdTime: Int = 0,
        @Schema(
                description = "Unix timestamp when the user was last updated",
                example = "1715846400"
        )
        var updateTime: Int = 0
) : UserDetails {
        constructor() : this(0, "", "", "", false, 0, 0)

        override fun getAuthorities(): Collection<GrantedAuthority> {
                return listOf(SimpleGrantedAuthority("ROLE_USER"))
        }

        override fun getUsername(): String = email

        override fun getPassword(): String = hashedPassword

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true

        override fun isCredentialsNonExpired(): Boolean = true

        override fun isEnabled(): Boolean = true

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is User) return false
                return id == other.id
        }

        override fun hashCode(): Int {
                return id.hashCode()
        }
}
