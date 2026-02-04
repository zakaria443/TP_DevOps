# Utilisation de l'image officielle Tomcat avec Java 11
FROM tomcat:9.0-jdk11-openjdk

# Nettoyage du dossier webapps pour éviter les conflits
RUN rm -rf /usr/local/tomcat/webapps/*

# Copie du fichier généré par Maven (nom défini dans ton pom.xml)
# On le renomme ROOT.war pour qu'il soit accessible sur http://localhost:8080/
COPY target/tp-devops.war /usr/local/tomcat/webapps/ROOT.war

# Port par défaut de Tomcat
EXPOSE 8080

# Lancement de Tomcat
CMD ["catalina.sh", "run"]