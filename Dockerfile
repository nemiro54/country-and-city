FROM openjdk:17-alpine
WORKDIR /app
COPY /target/*.jar app.jar
EXPOSE 8080
ENV POSTGRES_HOST $POSTGRES_HOST
CMD ["java", "-jar", "app.jar"]