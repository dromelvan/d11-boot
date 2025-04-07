# Remove 'arm64v8/' to build for non ARM architecture
FROM arm64v8/amazoncorretto:17-al2023-headless

WORKDIR /app

COPY d11-boot-application/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
