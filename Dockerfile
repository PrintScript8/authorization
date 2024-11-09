# Use the official OpenJDK 21 image as a base
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build context to the container
COPY build/libs/authorization-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8086 to the host
EXPOSE 8087

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]