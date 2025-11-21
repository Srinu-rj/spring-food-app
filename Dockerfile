FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .
LABEL maintainer="sreenivasa raju | dnsrinu143@gmail.com"
LABEL description="A Docker image for a Spring Boot application."
EXPOSE 8998
COPY target/application-mysql.jar application-mysql.jar
ENTRYPOINT ["java","-jar", "application-mysql.jar"]











































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