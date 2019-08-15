FROM openjdk:12
ADD build/libs/restclient-0.0.1.jar restclient-0.0.1.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "restclient-0.0.1.jar"]
