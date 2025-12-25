FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/hotel-service.jar hotel-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "hotel-service.jar"]