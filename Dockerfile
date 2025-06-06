FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY session-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
