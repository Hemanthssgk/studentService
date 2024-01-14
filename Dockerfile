FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/studentService.jar

CMD java -jar studentService.jar