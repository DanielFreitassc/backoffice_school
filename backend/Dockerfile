# Use a base image that includes OpenJDK 22
FROM eclipse-temurin:22-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY . .

# Run Maven to build the project
RUN mvn clean install

EXPOSE 8080

RUN cp target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]