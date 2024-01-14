FROM openjdk:17-oracle

WORKDIR /app

COPY target/studentService-0.0.1-SNAPSHOT.jar /app/studentService.jar

CMD java -jar studentService.jar