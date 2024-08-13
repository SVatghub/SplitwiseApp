FROM openjdk:22-jdk
ADD target/splitwise-app.jar splitwise-app.jar
ENTRYPOINT ["java", "-jar", "splitwise-app.jar"]