
### Stage 1: Image used for building the app
FROM openjdk:17-jdk-slim as build

# Copy sources to the image used for building
COPY . /tmp/build

# Build the app for production
RUN (cd tmp/build && bash mvnw -ntp package -DskipTests -Pproduction)
# Save and rename the app so as it can be taken in next stage
RUN ls -l /tmp/build/target/
RUN cp /tmp/build/target/*.jar /tmp/app.jar

### Stage 2: Image used for deploying and running the app
FROM openjdk:17-jdk-slim
# Add a non-root user for running the app
RUN useradd -m myuser
WORKDIR /usr/app
RUN chown myuser:myuser /usr/app
USER myuser

# Copy the compiled app to the image
COPY --from=build /tmp/app.jar /usr/app/

# Expose the app port
EXPOSE 8080

# Execute the app
ENTRYPOINT ["java", "-jar", "./app.jar"]

