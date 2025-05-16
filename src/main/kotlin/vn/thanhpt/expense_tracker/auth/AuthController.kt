package vn.thanhpt.expense_tracker.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and user management APIs")
class AuthController(private val authService: IAuthService) {

        @Operation(
                summary = "User login",
                description = "Authenticates a user and returns a JWT token",
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully authenticated"
                                ),
                                ApiResponse(
                                        responseCode = "401",
                                        description = "Invalid credentials"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                ),
                        ]
        )
        @PostMapping("/login")
        fun login(
                @Parameter(description = "Login credentials") @RequestBody request: LoginRequest
        ): ResponseEntity<AuthResponse> = ResponseEntity.ok(authService.login(request))

        @Operation(summary = "User registration", description = "Registers a new user")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "201",
                                        description = "Successfully registered"
                                ),
                                ApiResponse(
                                        responseCode = "400",
                                        description = "Invalid input or email already exists"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                ),
                        ]
        )
        @PostMapping("/register")
        fun register(
                @Parameter(description = "Registration information")
                @RequestBody
                request: RegisterRequest
        ): ResponseEntity<AuthResponse> = ResponseEntity.ok(authService.register(request))

        @Operation(
                summary = "Change password",
                description = "Changes the password for the authenticated user"
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully changed password"
                                ),
                                ApiResponse(
                                        responseCode = "400",
                                        description = "Invalid current password"
                                ),
                                ApiResponse(responseCode = "401", description = "Unauthorized"),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                ),
                        ]
        )
        @PostMapping("/change-password")
        fun changePassword(
                @Parameter(description = "Password change information")
                @RequestBody
                request: ChangePasswordRequest
        ): ResponseEntity<Unit> {
                authService.changePassword(request)
                return ResponseEntity.ok().build()
        }

        @Operation(
                summary = "Forgot password",
                description = "Initiates the password reset process"
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Password reset email sent"
                                ),
                                ApiResponse(
                                        responseCode = "404",
                                        description = "User not found",
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                ),
                        ]
        )
        @PostMapping("/forgot-password")
        fun forgotPassword(
                @Parameter(description = "Email address")
                @RequestBody
                request: ForgotPasswordRequest
        ): ResponseEntity<Unit> {
                authService.forgotPassword(request)
                return ResponseEntity.ok().build()
        }

        @Operation(
                summary = "Reset password",
                description = "Resets the password using a reset token"
        )
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully reset password"
                                ),
                                ApiResponse(
                                        responseCode = "400",
                                        description = "Invalid or expired token"
                                ),
                                ApiResponse(
                                        responseCode = "500",
                                        description = "Internal server error"
                                ),
                        ]
        )
        @PostMapping("/reset-password")
        fun resetPassword(
                @Parameter(description = "Password reset information")
                @RequestBody
                request: ResetPasswordRequest
        ): ResponseEntity<Unit> {
                authService.resetPassword(request)
                return ResponseEntity.ok().build()
        }

        @Operation(summary = "Logout", description = "Logs out the current user")
        @ApiResponses(
                value =
                        [
                                ApiResponse(
                                        responseCode = "200",
                                        description = "Successfully logged out"
                                ),
                                ApiResponse(responseCode = "401", description = "Unauthorized")]
        )
        @PostMapping("/logout")
        fun logout(): ResponseEntity<Unit> {
                authService.logout()
                return ResponseEntity.ok().build()
        }
}
