FROM openjdk:17-oracle
EXPOSE 8080
ADD target/memberregistration.jar memberregistration.jar
ENTRYPOINT [ "java","-jar","/memberregistration.jar"]
