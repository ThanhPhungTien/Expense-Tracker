package vn.thanhpt.expense_tracker.auth

interface IAuthService {
    fun login(request: LoginRequest): AuthResponse
    fun register(request: RegisterRequest): AuthResponse
    fun changePassword(request: ChangePasswordRequest)
    fun forgotPassword(request: ForgotPasswordRequest)
    fun resetPassword(request: ResetPasswordRequest)
    fun logout()
}
