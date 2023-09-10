# Remove 'arm64v8/' to build for non ARM architecture
FROM arm64v8/amazoncorretto:17-al2023-headless

VOLUME /tmp
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=production", "-jar","/app.jar"]
