 From openjdk:8
 WORKDIR /root/hello-world
 COPY HelloWorldApp-0.0.1-SNAPSHOT.jar /root/hello-world
 RUN ["chmod","+x","/root/hello-world/HelloWorldApp-0.0.1-SNAPSHOT.jar"]
 EXPOSE 8080
 ENTRYPOINT java -jar HelloWorldApp-0.0.1-SNAPSHOT.jar