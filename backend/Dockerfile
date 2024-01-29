# Stage 1: Build Spring Boot application using Gradle
FROM gradle:7.4-jdk17 AS gradle-build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test

# Stage 2: Create a custom Java runtime
FROM eclipse-temurin:11 as jre-build
RUN $JAVA_HOME/bin/jlink \
         --add-modules java.base \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Stage 3: Build the final container
FROM eclipse-temurin:17-jre-alpine
ENV JAVA_HOME=/opt/java/openjdk-17
ENV PATH "$JAVA_HOME/bin:${PATH}"
COPY --from=jre-build /javaruntime $JAVA_HOME
RUN mkdir /opt/app
COPY --from=gradle-build /home/gradle/src/build/libs/backend-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/backend-0.0.1-SNAPSHOT.jar"]