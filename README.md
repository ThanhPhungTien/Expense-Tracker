# Expense Tracker

A Spring Boot-based expense tracking application that helps users manage their financial transactions and family expenses.

## Technology Stack

- **Backend Framework**: Spring Boot 3.4.5
- **Language**: Kotlin 2.0.0
- **Database**: PostgreSQL
- **Template Engine**: JTE (Java Template Engine)
- **Security**: Spring Security
- **Build Tool**: Gradle
- **JDK Version**: 17

## Project Structure

```
src/main/kotlin/vn/thanhpt/expense_tracker/
├── ExpenseTrackerApplication.kt    # Main application entry point
├── family/                        # Family management module
├── transaction/                   # Transaction management module
├── transactiontype/              # Transaction type definitions
└── user/                         # User management module
```

## Features

- User authentication and authorization
- Family group management
- Transaction tracking
- Transaction type categorization
- Web-based interface using JTE templates

## Prerequisites

- JDK 17 or higher
- PostgreSQL database
- Gradle 7.x or higher

## Getting Started

1. Clone the repository
2. Configure your PostgreSQL database connection in `application.properties`
3. Build the project:
   ```bash
   ./gradlew build
   ```
4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Development

The project uses Gradle as the build tool. Key dependencies include:

- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- PostgreSQL JDBC Driver
- JTE Template Engine
- Jackson for JSON processing

## Security

The application uses Spring Security for authentication and authorization. Security auto-configuration is currently disabled for development purposes.

## Database

The application uses PostgreSQL as its database. Make sure to have a PostgreSQL instance running and configure the connection properties in your application configuration.

## Testing

The project includes test dependencies for:
- JUnit 5
- Spring Boot Test
- Spring Security Test

Run tests using:
```bash
./gradlew test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
