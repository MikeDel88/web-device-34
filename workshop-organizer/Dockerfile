ARG JDK=21-jdk-jammy
ARG TOMCAT=10.1.24-jre21-temurin-jammy

# =========================================
# Stage 1: Build du WAR avec Gradle
# =========================================
FROM eclipse-temurin:${JDK} AS builder
WORKDIR /app

# Wrapper et scripts d'abord pour profiter du cache des dépendances
COPY gradlew build.gradle settings.gradle ./
COPY gradle gradle
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies > /dev/null 2>&1 || true

COPY src src
RUN ./gradlew --no-daemon bootWar -x test

# =========================================
# Stage 2: Image d'exécution Tomcat
# =========================================
FROM tomcat:${TOMCAT}
LABEL authors="delamarremichael"

# curl nécessaire au healthcheck du compose
# Retirer les applis par défaut de Tomcat
RUN apt-get update && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/* && rm -rf /usr/local/tomcat/webapps/*

# Déployer le WAR en ROOT => application servie sur /
ARG VERSION=0.2.4
ARG WAR_FILE=/app/build/libs/workshop-organizer-${VERSION}.war
COPY --from=builder ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war

# Utilisateur non-root
RUN addgroup --system spring && adduser --system --ingroup spring spring \
    && chown -R spring:spring /usr/local/tomcat
USER spring:spring

EXPOSE 8080
CMD ["catalina.sh", "run"]
