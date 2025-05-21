# Sử dụng JDK 17 chính thức và nhẹ
FROM eclipse-temurin:17-jdk-alpine

# Tạo thư mục app trong container
WORKDIR /app

# Copy file JAR đã build vào container
COPY build/libs/expense_tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose port ứng dụng (Render sẽ map cổng 8080 theo mặc định)
EXPOSE 8080

# Cấu hình entrypoint để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
