FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/rating-service.jar rating-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "rating-service.jar"]