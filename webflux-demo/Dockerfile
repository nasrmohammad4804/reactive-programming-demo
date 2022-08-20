FROM openjdk:17
ADD target/webflux-demo-0.0.1-SNAPSHOT.jar user-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","user-mysql.jar"]
