package vn.thanhpt.expense_tracker.auth

import java.time.Instant
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vn.thanhpt.expense_tracker.user.User
import vn.thanhpt.expense_tracker.user.UserRepository

@Service
class AuthService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtService: JwtService,
        private val authenticationManager: AuthenticationManager
) : IAuthService {

    override fun login(request: LoginRequest): AuthResponse {
        val authentication =
                authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken(request.email, request.password)
                )
        val user = authentication.principal as User
        val token = jwtService.generateToken(user)
        return AuthResponse(token, user.toUserResponse())
    }

    override fun register(request: RegisterRequest): AuthResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        val user =
                User(
                        email = request.email,
                        hashedPassword = passwordEncoder.encode(request.password),
                        fullName = request.fullName,
                        createdTime = Instant.now().epochSecond.toInt(),
                        updateTime = Instant.now().epochSecond.toInt()
                )
        val savedUser = userRepository.save(user)
        val token = jwtService.generateToken(savedUser)
        return AuthResponse(token, savedUser.toUserResponse())
    }

    @Transactional
    override fun changePassword(request: ChangePasswordRequest) {
        val currentUser = getCurrentUser()
        if (!passwordEncoder.matches(request.currentPassword, currentUser.password)) {
            throw RuntimeException("Current password is incorrect")
        }
        currentUser.hashedPassword = passwordEncoder.encode(request.newPassword)
        currentUser.updateTime = Instant.now().epochSecond.toInt()
        userRepository.save(currentUser)
    }

    override fun forgotPassword(request: ForgotPasswordRequest) {
        val user =
                userRepository.findByEmail(request.email)
                        ?: throw RuntimeException("User not found")
        // TODO: Implement email sending with reset token
    }

    override fun resetPassword(request: ResetPasswordRequest) {
        // TODO: Implement token validation and password reset
    }

    override fun logout() {
        SecurityContextHolder.clearContext()
    }

    private fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.principal as User
    }

    private fun User.toUserResponse() =
            UserResponse(id = id, email = email, fullName = fullName, emailVerified = emailVerified)
}
