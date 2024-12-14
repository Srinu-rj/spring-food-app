#FROM openjdk:17
#EXPOSE 8998
#COPY target/spring-image.jar .
#ENTRYPOINT ["java","-jar","/spring-image.jar"]
#

FROM openjdk:17-jdk-slim
WORKDIR /app
ENV PORT 8998
COPY target/spring-image.jar /app/spring-image.jar
EXPOSE 8998
ENTRYPOINT ["java", "-jar", "/app/spring-image.jar"]

#FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
#WORKDIR /opt
#ENV PORT 8998
#EXPOSE 8998
#COPY target/*.jar /opt/app.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar app.jar