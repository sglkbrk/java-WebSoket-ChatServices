


FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080

ADD target/chat-service-0.0.1-SNAPSHOT.jar chat-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/chat-service-0.0.1-SNAPSHOT.jar"]