FROM openjdk:8-jre
VOLUME /tmp
EXPOSE 8080
ADD target/notebookservice-web-0.2.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
