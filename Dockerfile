# Use a Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside the builder container
WORKDIR /build

# Copy the entire project into the container
COPY . .

# Package the Spring Boot app (skipping tests for faster build)
RUN mvn clean package -DskipTests


# Use a smaller OpenJDK 17 image for the final container
FROM openjdk:17-jdk-slim

# Set working directory inside the final container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /build/target/blog_api-1.0.0.jar app.jar

# Set environment variable for the default port
ENV PORT=8080

# Expose port (for local Docker use — not necessary for Render)
EXPOSE 8080

# Run the Spring Boot JAR
CMD ["java", "-jar", "app.jar"]
