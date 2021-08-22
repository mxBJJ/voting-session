FROM openjdk:11
ADD build/libs/votacao-api-0.0.1-SNAPSHOT.jar /
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/votacao-api-0.0.1-SNAPSHOT.jar"]