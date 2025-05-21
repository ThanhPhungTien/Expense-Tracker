package vn.thanhpt.expense_tracker.auth

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import vn.thanhpt.expense_tracker.common.ApiErrorResponse

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val objectMapper = ObjectMapper()

    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val errorResponse =
                ApiErrorResponse(
                        code = HttpServletResponse.SC_UNAUTHORIZED,
                        message = "Unauthorized: Authentication token is missing or invalid"
                )

        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}
