FROM gradle:8.5-jdk17 AS build

WORKDIR /app

# Copy only necessary files for dependency resolution first (for better caching)
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

RUN mkdir -p src/main/jte

# Download dependencies (optional but speeds up builds)
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the source code
COPY src ./src

# Build the JAR
RUN ./gradlew bootJar --no-daemon

# Use a lightweight JRE image for running the app
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]