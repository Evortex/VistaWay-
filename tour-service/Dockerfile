FROM gradle:8.10.2-jdk17

WORKDIR /app/tour-service
COPY src ./src
COPY build.gradle settings.gradle ./

RUN gradle clean bootJar -i --stacktrace

ENTRYPOINT ["java", "-jar", "/app/tour-service/build/libs/tour-service-0.0.1-SNAPSHOT.jar"]
