# ---- Build Stage ----
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
COPY --from=builder /app/target/application-mysql.jar application-mysql.jar
EXPOSE 8998

# ---- Runtime Stage ----
FROM openjdk:17-jdk
WORKDIR /app
ENTRYPOINT ["java", "-jar", "application-mysql.jar"]




# Step 1: Use official OpenJDK as base image
# FROM openjdk:17-jdk-slim
# # Step 2: Set working directory inside the container
# WORKDIR /app
# # Step 3: Copy the JAR file from the host to the container
# COPY target/application-mysql.jar application-mysql.jar
# # Step 4: Expose the application port
# EXPOSE  8998
# # Step 5: Run the Spring Boot application
# ENTRYPOINT ["java", "-jar", "application-mysql.jar"]
