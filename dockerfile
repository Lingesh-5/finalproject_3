# Use OpenJDK 21 (or your Java version)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy Gradle/Maven build files if needed
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the app
RUN ./mvnw package -DskipTests

# -------- Runtime --------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
