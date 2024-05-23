# Technical Report for CA4 Part 1: Containers with Docker

## Introduction

Class Assignment 4 (CA4) focuses on using Docker to containerize the chat application developed in CA2. The objective is to create Docker images for the chat server, run the server in a container, and connect to it using the chat client from the host machine. This report documents the steps required to achieve these goals, providing detailed instructions for creating Dockerfiles, building and tagging images, and publishing them to Docker Hub. Additionally, it outlines how to document the process in the readme file and mark the repository with the appropriate tag upon completion.

## Goals/Requirements

The main goals and requirements for Part 1 of CA4 are as follows:

1. **Docker Image Creation**: Create Docker images for the chat server with two different approaches:
    - **Version 1**: Build the chat server inside the Dockerfile.
    - **Version 2**: Build the chat server on the host machine and copy the jar file into the Dockerfile.

2. **Docker Hub**: Tag the images and publish them to Docker Hub.

3. **Chat Client Connection**: Execute the chat client on the host computer and connect to the chat server running in the container.

4. **Documentation**: Provide a technical report in the readme.md file documenting the process, including justifications for any choices made.

5. **Tagging**: Mark the repository with the tag "ca4-part1" upon completion.

## Setup and Configuration

This section outlines the steps required to set up the Docker environment and fulfill the goals outlined in the assignment.

### Initial Setup

1. **Clone the Repository**:
    - Clone the chat application repository provided at [https://bitbucket.org/pssmatos/gradle_basic_demo/](https://bitbucket.org/pssmatos/gradle_basic_demo/).
    - Navigate to the directory of the cloned repository.

2. **Create Project Structure**:
    - Create a folder for Part 1 of CA4 in your repository (e.g., `CA4/Part1/`).
    - Create 2 folders `CA4/Part1/insideDockerFile` and `CA4/Part1/chatServerBuiltInHost` for the 2 portions of the assignment
    - Copy the necessary files from the chat application repository to this folder.

### Docker Image Creation

#### Version 1: Building the Chat Server Inside the Dockerfile

1. **Create Dockerfile**:
    - In the `CA4/Part1/insideDockerFile` directory, create a file named `Dockerfile`.
    - Add the following content to the Dockerfile to build the chat server inside the Dockerfile:
    ```dockerfile
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
    ```
   - We are going to use Java 17 and Gradle 8.7, as our project was developed using these versions.
   - If we didn't have such gradle wrapper,we would need something like:
    ```Dockerfile
    FROM gradle:8.7.0-jdk17 AS build
    ```
    - The author label is set to identify the author of the Dockerfile.
    - We first build the project using the gradle wrapper, and then we copy the jar file to the final image.
    - We use the `--from` flag to copy the jar file from the builder image to the final image.
    - We set the entrypoint to run the jar file with the ChatServerApp class and the port 59001.

2. **Build the Docker Image**:
    - Navigate to the `CA4/Part1/insideDockerFile` directory.
    - Run the following command to build the Docker image:
    ```bash
    docker build -t chat-server:v1 .
    ```

3. **Tag and Push the Image to Docker Hub**:
    - Tag the image:
    ```bash
    docker tag chat-server:v1 ozepedrorodrigues/chat-server:v1
    ```
    - Push the image to Docker Hub:
    ```bash
    docker push ozepedrorodrigues/chat-server:v1
    ```
4. **Run the Chat Server Container**:
    - Run the Docker container for the chat server:
    ```bash
    docker run -d -p 59001:59001 chat-server:version1
    ```
    - The `-d` flag runs the container in detached mode, and the `-p` flag maps the container port to the host port.
    - The chat server will be accessible at `localhost:59001`.
    - After this you may execute the chat client on the host machine and connect to the chat server at `localhost:59001`.
   ```bash
   cd /directory/of/root/project
    ./gradlew runClient
    ```
   - You can see containers running using:
   ```bash
    docker ps
    ```
    - You can make the container stop running using:
    ```bash
    docker stop <container_id>
    ```
    - You can remove the container using:
    ```bash
    docker rm <container_id>
    ```
    - You can remove the image using:
    ```bash
    docker rmi chat-server
    ```
#### Version 2: Building the Chat Server on the Host Machine

1. **Build the Chat Server**:
    - Clone the chat application repository provided at [https://bitbucket.org/pssmatos/gradle_basic_demo/](https://bitbucket.org/pssmatos/gradle_basic_demo/).
    - On your host machine, navigate to the root directory of the chat application and run the following command to build the chat server:
    ```bash
    cd /root/directory/of/chat/application
    ./gradlew build
    ```

2. **Create Dockerfile**:
    - In the `CA4/Part1/chatServerBuiltInHost` directory, create a file named `Dockerfile`.
    - Add the following content to the Dockerfile:
    ```dockerfile
   FROM openjdk:17-jdk-slim
   WORKDIR /ca4-part1
   COPY build/libs/basic_demo-0.1.0.jar /ca4-part1/basic_demo-0.1.0.jar
   CMD ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]
    ```
- For space purposes the build/libs folder was temporarily copied to the directory of the Dockerfile (in the repository)
- The jar file is used to build the image, which is pushed to Docker hub 
- Afterwards the build directory can be removed from the repository (in order to preserve space)

3. **Build the Docker Image**:
    - Navigate to the `CA4/Part1/` directory.
    - Run the following command to build the Docker image:
    ```bash
    docker build -t chat-server:v2 -f Dockerfile .
    ```

4. **Tag and Push the Image to Docker Hub**:
    - Tag the image:
    ```bash
    docker tag chat-server:v2 ozepedrorodrigues/chat-server:v2
    ```
    - Push the image to Docker Hub:
    ```bash
   docker push ozepedrorodrigues/chat-server:v2
    ```

### Chat Client Connection

1. **Run the Chat Server Container**:
    - Run the Docker container for the chat server:
    ```bash
    docker run -d -p 59001:59001 ozepedrorodrigues/docker_images_ca4/chat-server:v2
    ```
    - After this you may execute the chat client on the host machine and connect to the chat server at `localhost:59001`.
   ```bash
    cd /directory/of/root/project
    ./gradlew runClient
    ```
   
2. Both images should be available here:
3. https://hub.docker.com/u/ozepedrorodrigues

### Documentation

1. **Readme File**:
    - Document the entire process in the readme.md file located in the `CA4/Part1/` directory.
    - Include detailed instructions for creating the Docker images, tagging and pushing them to Docker Hub, and connecting the chat client to the server.
    - Use Markdown syntax for clear and organized formatting.
    - Provide examples, code snippets, and screenshots where necessary to illustrate the steps.

### Tagging Repository

1. **Tagging for Part 1 Completion**:
    - Once all the steps are completed and documented, tag your repository with the tag "ca4-part1" to mark the completion of Part 1 of the assignment.
   ```bash
   git tag -a ca4-part1 -m "Complete CA4 Part 1"
   git push origin --tags
   ```

## Conclusion

In summary, Part 1 of CA4 involves using Docker to containerize the chat application developed in CA2. By following the steps outlined in this report, you can create Docker images for the chat server, run the server in a container, and connect to it using the chat client. The documentation provided ensures clarity and reproducibility throughout the process, serving as a valuable resource for developers. By adhering to proper version control practices, such as tagging the repository with "ca4-part1," project management and traceability are enhanced, facilitating collaboration and future reference.