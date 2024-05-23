   FROM openjdk:17-jdk-slim

   WORKDIR /ca4-part1

   COPY ./build/libs/basic_demo-0.1.0.jar /ca4-part1/basic_demo-0.1.0.jar

   CMD ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]