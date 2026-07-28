FROM arm64v8/amazoncorretto:11-al2023-headless AS builder
WORKDIR /app
COPY build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM arm64v8/amazoncorretto:11-al2023-headless
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

ENTRYPOINT ["java", "-Dspring.profiles.active=production", "org.springframework.boot.loader.JarLauncher"]
