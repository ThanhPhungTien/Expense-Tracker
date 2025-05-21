FROM gradle:8.5-jdk17 AS build

WORKDIR /app

COPY build/libs/expense_tracker-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"] 