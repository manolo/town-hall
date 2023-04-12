
# Stage that builds the application, a prerequisite for the running stage
FROM openjdk:17-jdk-slim

# Add a non-root user for running the app
RUN useradd -m myuser
WORKDIR /usr/app
RUN chown myuser:myuser /usr/app

# Copy the compiled app to the image
USER myuser
COPY --chown=myuser:myuser . /usr/app/build

# Build the app for production
RUN (cd build && bash mvnw -ntp package -DskipTests -Pproduction)
# Save and rename the app
RUN cp build/target/*.jar /usr/app/app.jar
# Clean the container
RUN rm -rf ~/.m2 ~/.npm* build

# Expose the app port
EXPOSE 8080

# Execute the app
ENTRYPOINT ["java", "-jar", "./app.jar"]

