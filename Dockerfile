# Start with a base image containing Java runtime
FROM openjdk:17-jdk

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's .jar file
ARG JAR_FILE=receipt-processor-challenge-0.0.1-SNAPSHOT.jar


# Add the application's .jar to the container
COPY ${JAR_FILE} receipt-processor-challenge-0.0.1-SNAPSHOT.jar

# Run the jar file
CMD ["java", "-jar", "receipt-processor-challenge-0.0.1-SNAPSHOT.jar"]


