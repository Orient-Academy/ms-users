FROM openjdk:19
COPY build/libs/ms-users-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]