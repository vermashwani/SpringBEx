FROM openjdk:8
WORKDIR /root/hello-world
COPY /target/HelloWorldApp-0.0.1-SNAPSHOT.jar /root/hello-world
EXPOSE 8080
ENTRYPOINT java -jar HelloWorldApp-0.0.1-SNAPSHOT.jar