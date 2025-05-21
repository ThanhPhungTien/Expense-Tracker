# Stage 1: Build JAR
FROM gradle:8.4-jdk17 AS builder

# Copy toàn bộ project vào container
COPY . /home/app

WORKDIR /home/app

# Build file JAR
RUN gradle build --no-daemon

# Stage 2: Run JAR
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy JAR từ stage build
COPY --from=builder /home/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
