FROM gradle:8.10.2-jdk17

WORKDIR /app/api-gateway
COPY src ./src
COPY build.gradle settings.gradle ./

RUN gradle clean bootJar -i --stacktrace

EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/app/api-gateway/build/libs/api-gateway-0.0.1-SNAPSHOT.jar"]