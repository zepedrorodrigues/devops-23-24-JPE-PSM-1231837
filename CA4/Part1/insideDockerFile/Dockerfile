FROM gradle:jdk17 as builder
   LABEL author="José Pedro Rodrigues"
   WORKDIR /ca4-part1
   RUN git clone https://bitbucket.org/pssmatos/gradle_basic_demo.git
   WORKDIR /ca4-part1/gradle_basic_demo
   RUN chmod +x gradlew
   RUN ./gradlew build
   FROM openjdk:17-jdk-slim
   WORKDIR /ca4-part1
   COPY --from=builder /ca4-part1/gradle_basic_demo/build/libs/*.jar ca4-part1.jar
   CMD ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]