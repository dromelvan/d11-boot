FROM openjdk:10
RUN useradd --user-group --system --create-home --no-log-init d11
USER d11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=production", "-jar","/app.jar"]
