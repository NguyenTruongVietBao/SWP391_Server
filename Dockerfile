FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
COPY --from=build /target/demo-0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]