FROM adoptopenjdk/openjdk11:latest

EXPOSE 8081

ADD SpringJPATest-0.0.1-SNAPSHOT.jar SpringJPATest-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "SpringJPATest-0.0.1-SNAPSHOT.jar" ]