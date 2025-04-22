FROM gradle:8.10.2-jdk17

WORKDIR /app/eureka-server
COPY src ./src
COPY build.gradle settings.gradle ./

RUN gradle clean bootJar -i --stacktrace

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/eureka-server/build/libs/eureka-server-0.0.1-SNAPSHOT.jar"]