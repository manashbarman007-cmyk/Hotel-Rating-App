FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/config-server.jar config-server.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "config-server.jar"]