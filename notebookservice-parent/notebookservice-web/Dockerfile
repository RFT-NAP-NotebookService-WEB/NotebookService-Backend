FROM openjdk:8-jre
VOLUME /tmp
EXPOSE 8080
ADD target/notebookservice-web-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
