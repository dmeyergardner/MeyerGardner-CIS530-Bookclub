# Start your image with a node base image
FROM eclipse-temurin:17-jdk-alpine

# The /app directory should act as the main application directory
WORKDIR /app

# Copy the app package and package-lock.json file
COPY target/bookclub-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (Render sets PORT environment variable)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]