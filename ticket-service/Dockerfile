FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app/ticket-service

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar", "/app/ticket-service/target/ticket-service-0.0.1-SNAPSHOT.jar"]