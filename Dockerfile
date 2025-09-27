# Use official OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file into container
COPY target/hospital_app-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
