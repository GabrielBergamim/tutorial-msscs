# First stage: Build the application
FROM  cgr.dev/chainguard/maven:latest as builder

# Set the working directory inside the container
WORKDIR /home/app

# Ensure the directory is writable
RUN mkdir -p /home/app/mssc-auth/target && chmod -R 777 /home/app

COPY ../pom.xml ./pom.xml
COPY ../mssc-auth ./mssc-auth
COPY ../mssc-notification ./mssc-notification
COPY ../mssc-discovery ./mssc-discovery
COPY ../mssc-gateway ./mssc-gateway

RUN mvn -pl mssc-auth clean package -DskipTests

# Second stage: Create a minimal runtime image
FROM cgr.dev/chainguard/jdk:latest

# Set the working directory
WORKDIR /home/app

# Copy only the built jar file from the builder stage
COPY --from=builder /home/app/mssc-auth/target/mssc-auth.jar /home/app/mssc-auth.jar

# Expose the port on which the app runs
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/home/app/mssc-auth.jar"]
