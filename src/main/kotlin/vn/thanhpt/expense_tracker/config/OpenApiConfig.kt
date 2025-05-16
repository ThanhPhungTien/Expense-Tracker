package vn.thanhpt.expense_tracker.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

        @Bean
        fun openAPI(): OpenAPI {
                return OpenAPI()
                        .info(
                                Info().title("Expense Tracker API")
                                        .description(
                                                "API documentation for Expense Tracker application"
                                        )
                                        .version("1.0")
                                        .contact(
                                                Contact()
                                                        .name("Thành Phùng Tiến")
                                                        .email("thanh.pt1@example.com")
                                        )
                                        .license(
                                                License()
                                                        .name("MIT License")
                                                        .url("https://opensource.org/licenses/MIT")
                                        )
                        )
                        .servers(
                                listOf(
                                        Server().url("http://localhost:8080")
                                                .description("Local development server")
                                )
                        )
                        .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                        .components(
                                io.swagger.v3.oas.models.Components()
                                        .addSecuritySchemes(
                                                "bearerAuth",
                                                SecurityScheme()
                                                        .type(SecurityScheme.Type.HTTP)
                                                        .scheme("bearer")
                                                        .bearerFormat("JWT")
                                        )
                        )
        }
}
