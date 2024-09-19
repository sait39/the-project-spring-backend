# Stage 1: Build the JAR using Maven
FROM gradle:8.10.1-jdk21 AS builder
WORKDIR /app

# Copy the pom.xml and other necessary files for dependencies
COPY ../gradlew ../build.gradle ../settings.gradle ./
COPY ../gradle ./gradle

# Download dependencies (to speed up subsequent builds)
RUN ./gradlew build --no-daemon --parallel --stacktrace --exclude-task test

# Copy the rest of the project files
COPY ../src ./src

# Build the application
RUN ./gradlew bootJar --no-daemon --stacktrace --parallel

# Stage 2: Package the application into a small runtime image
# Use an official OpenJDK 21 runtime as a parent image
FROM eclipse-temurin:21-jdk

RUN mkdir /opt/app

# Copy the application JAR file into the container at /app
COPY --from=builder /app/build/libs/*.jar /opt/app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
