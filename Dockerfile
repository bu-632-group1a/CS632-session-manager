FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY session-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENV DB_URL="jdbc:postgresql://databasehost"
ENV DB_USERNAME="username"
ENV DB_PASSWORD="password"