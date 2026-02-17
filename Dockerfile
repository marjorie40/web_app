# Image runtime Java 21
FROM eclipse-temurin:21-jre

# Repertoire de travail
WORKDIR /app

# Copier le jar genere
COPY target/webAppDemo-0.0.1-SNAPSHOT.jar web.jar

# Exposer le port de l'API
EXPOSE 9001

# Demarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "web.jar"]