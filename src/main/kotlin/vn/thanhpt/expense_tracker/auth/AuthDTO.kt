package vn.thanhpt.expense_tracker.auth

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Login request")
data class LoginRequest(
        @Schema(description = "Email address", example = "user@example.com") val email: String,
        @Schema(description = "Password", example = "password123") val password: String
)

@Schema(description = "Registration request")
data class RegisterRequest(
        @Schema(description = "Email address", example = "user@example.com") val email: String,
        @Schema(description = "Password", example = "password123") val password: String,
        @Schema(description = "Full name", example = "John Doe") val fullName: String
)

@Schema(description = "Change password request")
data class ChangePasswordRequest(
        @Schema(description = "Current password", example = "oldPassword123")
        val currentPassword: String,
        @Schema(description = "New password", example = "newPassword123") val newPassword: String
)

@Schema(description = "Forgot password request")
data class ForgotPasswordRequest(
        @Schema(description = "Email address", example = "user@example.com") val email: String
)

@Schema(description = "Reset password request")
data class ResetPasswordRequest(
        @Schema(description = "Reset token", example = "abc123...") val token: String,
        @Schema(description = "New password", example = "newPassword123") val newPassword: String
)

@Schema(description = "Authentication response")
data class AuthResponse(
        @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIs...")
        val accessToken: String,
        @Schema(description = "User information") val user: UserResponse
)

@Schema(description = "User response")
data class UserResponse(
        @Schema(description = "User ID", example = "1") val id: Long,
        @Schema(description = "Email address", example = "user@example.com") val email: String,
        @Schema(description = "Full name", example = "John Doe") val fullName: String,
        @Schema(description = "Whether email is verified", example = "false")
        val emailVerified: Boolean
)
