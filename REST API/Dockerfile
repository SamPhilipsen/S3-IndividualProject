FROM openjdk:11
EXPOSE 8080
ADD build/libs/*.jar mainApplication.jar
ENTRYPOINT ["java","-jar","/mainApplication.jar"]