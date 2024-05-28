# Technical Report for CA4 Part 2: Containers with Docker 

## Introduction

The goal of this class assignment was to use Docker to create two images and a docker-compos file and run a container with those images. In this
assignment 2 dockerfiles and a dokcer-compose file were created, one image to run the web app and the second one to host an H2 database.
The final result of the assignment can be found [here](https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837).

## Getting started

To complete this assignment first we need to copy the contents of the CA2 Part2 project to the same folder as the Dockerfiles and Docker-Compose file.

```bash
cp -r /directory/Of/CA2/Part2/* /current/directory
```
Now the contents off the previously developped app are made available so Docker can turn them into images, upload them to a container and then run them.
Don't forget to delete them after concluding the process (for memory saving purposes).


## Web Dockerfile

The first Dockerfile created was the web Dockerfile, titled `Dockerfile-web`. This Dockerfile is responsible for creating an image that runs the app. It first copies the contents of the react-and-spring-data-rest-basic folder and then changes the permissions of the gradlew file to make it executable to then build the project and run it. The Dockerfile is shown below:

```Dockerfile
FROM gradle:7.6.2-jdk17
WORKDIR /app
COPY . /app
RUN gradle clean build
EXPOSE 8080
CMD ["java", "-jar", "build/libs/react-and-spring-rest-data-basic-0.0.1-SNAPSHOT.jar"]

```
1. FROM gradle:7.6.2-jdk17:
- This line specifies the base image for your Docker image. It uses the Gradle image with version 7.6.2 and JDK 17.

2. WORKDIR /app:
- This sets the working directory inside the Docker container to /app. All subsequent commands will be run in this directory.

3. COPY . /app:
- This copies all files from the current directory on the host machine to the /app directory in the Docker container.

4. RUN gradle clean build:
- This runs the gradle clean build command inside the container, which cleans any previous builds and then builds the project. This will generate the required artifacts, such as the JAR file.

5. EXPOSE 8080:
- This indicates that the container listens on port 8080 at runtime. It's a way of documenting which ports are used by the application, but it doesn't actually publish the port to the host.

6. CMD ["java", "-jar", "build/libs/react-and-spring-rest-data-basic-0.0.1-SNAPSHOT.jar"]:
- This sets the default command to run when the container starts. It runs the Java application by executing the JAR file located at build/libs/react-and-spring-rest-data-basic-0.0.1-SNAPSHOT.jar.

## Database Dockerfile

This Dockerfile is responsible for creating an image that runs the H2 database. It first copies the contents of the h2 folder and then runs the H2 database. The Dockerfile is shown below:

```Dockerfile
FROM ubuntu:focal
RUN apt-get update && \
    apt-get install -y wget openjdk-17-jdk-headless && \
    rm -rf /var/lib/apt/lists/* \
WORKDIR /opt/h2
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O h2.jar
EXPOSE 8082
EXPOSE 9092
CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-ifNotExists", "-web", "-webAllowOthers", "-webPort", "8082", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092"]
```
1. FROM ubuntu:focal
- This specifies the base image for the Docker image, which is Ubuntu Focal (20.04).

2. RUN apt-get update && apt-get install -y wget openjdk-17-jdk-headless && rm -rf /var/lib/apt/lists/*:
- This line runs multiple commands:
    - apt-get update updates the package list.
    - apt-get install -y wget openjdk-17-jdk-headless installs wget and the OpenJDK 17 headless package.
    - rm -rf /var/lib/apt/lists/* removes the package lists to reduce the image size.

3. WORKDIR /opt/h2:
- This sets the working directory inside the Docker container to /opt/h2.

4. RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O h2.jar:
- This downloads the H2 database JAR file from Maven Central and saves it as h2.jar in the /opt/h2 directory.

5. EXPOSE 8082:
- This indicates that the container listens on port 8082 for HTTP connections.

6. EXPOSE 9092:
- This indicates that the container listens on port 9092 for TCP connections.

7. CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-ifNotExists", "-web", "-webAllowOthers", "-webPort", "8082", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092"]:
- This sets the default command to run when the container starts, which runs the H2 database server with specified options:
    - ifNotExists: Only create the database if it does not already exist.
    - web: Start the web server.
    - webAllowOthers: Allow remote connections to the web server.
    - webPort 8082: Set the web server port to 8082.
    - tcp: Start the TCP server.
    - tcpAllowOthers: Allow remote connections to the TCP server.
    - tcpPort 9092: Set the TCP server port to 9092.


## Docker-compose file

This file is responsible for creating the two images and running the containers. The file is shown below:

```yaml
services:
  db:
    build:
      context: .
      dockerfile: Dockerfile-db
    container_name: h2-db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - h2-data:/opt/h2-data

  web:
    build:
      context: .
      dockerfile: Dockerfile-web
    container_name: spring-web
    ports:
      - "8080:8080"
    depends_on:
      - db

volumes:
  h2-data:
    driver: local
```
1. After the docker-compose file idb Service:
- Build Context: Specifies the current directory as the context and Dockerfile-db for building the Docker image.
- Container Name: Sets the container name to h2-db.
- Ports: Maps port 8082 and 9092 on the host to the same ports in the container, which are typically used by H2 for web console and TCP communication.
- Volumes: Mounts a named volume h2-data to /opt/h2-data in the container, which can be used to persist database data.

2. Web Service:
- Build Context: Specifies the current directory as the context and Dockerfile-web for building the Docker image.
- Container Name: Sets the container name to spring-web.
- Ports: Maps port 8080 on the host to port 8080 in the container, which is where the Spring web application will run.
- depends_on: Ensures that the db service is started before the web service.

3. Volumes:
- h2-data: Defines a named volume with the local driver for persisting data.


After the Docker-Compose file was created we can build the images by running the following command:

```bash
docker-compose build
```
Push the Web docker image to Docker Hub by running the following command:

```bash
docker tag part2-web ozepedrorodrigues/ca4_part2_web
```

```bash
docker push ozepedrorodrigues/ca4_part2_web
```

And finally push the Database docker image to Docker Hub by running the following command:

```bash
docker tag part2-db ozepedrorodrigues/ca4_part2_db
```

```bash
docker push ozepedrorodrigues/ca4_part2_db
```

After starting the containers, the web app can be accessible through the IP specified in the Dockerfile, in this particular case it would be http://localhost:8080. The database can be accessed through the H2 console by going to http://localhost:8082.

The Images should be available via [this](https://hub.docker.com/repository/docker/ozepedrorodrigues/ca4_part2_db/general) and [this](https://hub.docker.com/repository/docker/ozepedrorodrigues/ca4_part2_web/general).

## Alternative Implementation solution

We could also deploy both those images using a Kubernetes cluster, which has better scalability and availability of the app. The deployment of the app and the database could be done in separate pods, then connecting the app to the database using the service name (allowing better separation of concerns and better management of the app and the database).
Considering the images have already been pushed to DockerHub, we need to create a Kubernetes deployment file for the app and the database. The deployment file would look something like this:

Web app deployment file:
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ca4-part2-web
spec:
  replicas: 1
  selector:
    matchLabels:
      app: ca4-part2-web
  template:
    metadata:
      labels:
        app: ca4-part2-web
    spec:
      containers:
        - name: ca4-part2-web
          image: ozepedrorodrigues/ca4_part2_web
          ports:
            - containerPort: 8080

```

Web app service file:
```yaml
apiVersion: v1
kind: Service
metadata:
  name: ca4-part2-web
spec:
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30080
  selector:
    app: ca4-part2-web
```

Database deployment file:
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ca4-part2-db
spec:
  replicas: 1
  selector:
    matchLabels:
      app: ca4-part2-db
  template:
    metadata:
      labels:
        app: ca4-part2-db
    spec:
      containers:
        - name: ca4-part2-db
          image: ozepedrorodrigues/ca4_part2_db
          ports:
            - containerPort: 8082
            - containerPort: 9092
```

Database service file:
```yaml
apiVersion: v1
kind: Service
metadata:
  name: ca4-part2-db
spec:
  type: NodePort
  ports:
    - port: 8082
      targetPort: 8082
      nodePort: 30082
    - port: 9092
      targetPort: 9092
      nodePort: 30092
  selector:
    app: ca4-part2-db
```
Kubernetes uses a declarative approach (to manage a cluster's state). We define the desired state of the cluster in a YAML file and Kubernetes will make sure that the cluster is in that state, allowing for better management of the cluster, better scalability and availability.
It is not uncommon for Docker and Kubernetes to be used together (Docker to build images and Kubernetes to deploy and manage the containers).

## Conclusion
This assignment demonstrated how to use Docker to create images for a web application and an H2 database, and how to manage these containers using Docker Compose. The process included building Dockerfiles, setting up a Docker Compose file, and pushing images to Docker Hub. Additionally, it highlighted an alternative deployment method using Kubernetes for enhanced scalability and availability. This exercise provides a practical understanding of containerization and orchestration in modern application deployment.