FROM gradle:8.10.2-jdk17 AS build

WORKDIR /app/ticket-service

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle clean build -x test --no-daemon

ENTRYPOINT ["java", "-jar", "/app/ticket-service/build/libs/ticket-service-0.0.1-SNAPSHOT.jar"]
