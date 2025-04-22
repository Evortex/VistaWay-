FROM gradle:8.10.2-jdk17

WORKDIR /app/config-server
COPY src ./src
COPY build.gradle settings.gradle ./

RUN gradle clean bootJar -i --stacktrace

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app/config-server/build/libs/config-server-0.0.1-SNAPSHOT.jar"]