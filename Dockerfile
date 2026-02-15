FROM maven:3.9.6-eclipse-temurin-22-jammy AS build
COPY . .
FROM openjdk:17 AS builder
COPY --from=build /target/spring-application-k8s.jar spring-application-k8s.jar
EXPOSE 8998
ENTRYPOINT ["java","-jar","application-mysql.jar"]
LABEL maintainer="SREENIVASA RAJU"
LABEL version="1.0.0"
LABEL description="Spring Boot Application with Postgress Database"
USER nobody





































# # Build stage
# FROM eclipse-temurin:17-jdk-jammy AS build
# WORKDIR /app
# COPY mvnw .
# COPY .mvn .mvn
# COPY pom.xml .
# COPY src src
# RUN chmod +x ./mvnw
# RUN ./mvnw clean package -DskipTests
#
# # Runtime stage
# FROM eclipse-temurin:17-jdk-jammy
# ARG PROFILE=dev
# ARG APP_VERSION=1.0.0
# WORKDIR /app
# COPY --from=build /app/target/*.jar /app/
# EXPOSE 8998
# ENV DB_URL=jdbc:postgresql://localhost:5432/spring
# ENV ACTIVE_PROFILE=${PROFILE}
# ENV JAR_VERSION=${APP_VERSION}
# CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} spring-security-asymmetric-encryption-${JAR_VERSION}.jar