FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/energymonitor-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]