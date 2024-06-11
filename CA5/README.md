# Class Assignment 5: CI/CD Pipelines with Jenkins - Technical Report

This tutorial provides a detailed guide for setting up CI/CD pipelines using Jenkins running in a Docker container. The assignment involves creating and configuring Jenkins pipelines for a Gradle project and a Spring Boot application. Below are the step-by-step instructions.

## Overview

**Topic:** CI/CD Pipelines with Jenkins  
**Start Date:** 19th May  
**End Date:** 13th June (no commits after this date)  
**Development Repository:** Your individual repository created for DevOps

## Operative Guidelines

1. **Repository Setup**
    - Use your own private repository (created in the first week, e.g., `devops-23-24-JPE-PSM-1122345`).
    - Create issue(s) in GitHub for your main tasks.
    - Create a folder in your repository for this class assignment (e.g., `CA5`).

2. **Folder Structure**
    - Inside the `CA5` folder, add all files specific to the assignment.
    - Include a `README.md` file with the technical report of the assignment.

3. **Commit Guidelines**
    - Teachers expect multiple commits to demonstrate ongoing progress.

## Goals/Requirements

### Gradle Project

## Goals/Requirements

### Gradle Project

1. **Jenkins Pipeline Setup**
    - Use the "gradle basic demo" project already present in your individual repository.
    - Create a simple pipeline similar to the example from the lectures.

2. **Pipeline Configuration**
    - Specify the relative path to the `Jenkinsfile` in the `ScriptPath` field (e.g., `ca2/part1/gradle-basic-demo/Jenkinsfile`).
    - Define the following stages in your pipeline:
        - **Checkout:** Checkout the code from the repository.
        - **Assemble:** Compile and produce the archive files with the application.
        - **Test:** Run unit tests.
        - **Report:** Generate test reports.
        - **Deploy:** Deploy the application.

### Spring Boot Application

1. **Jenkins Pipeline Setup**
    - After practicing with the Gradle project, create a pipeline in Jenkins to build the tutorial Spring Boot application, using the gradle "basic" version (developed in CA2, Part2).

2. **Pipeline Configuration**
    - Define the following stages in your pipeline:
        - **Checkout:** Checkout the code from the repository.
        - **Assemble:** Compile and produce the archive files with the application (do not use the `build` task of Gradle because it also executes the tests).
        - **Test:** Execute unit tests and publish the results in Jenkins using the `junit` step.
        - **Javadoc:** Generate the Javadoc for the project and publish it in Jenkins using the `publishHTML` step.
        - **Archive:** Archive the files generated during the Assemble stage (e.g., the war file).
        - **PublishImage:** Generate a Docker image with Tomcat and the war file, and publish it in DockerHub.

3. **Tagging**
    - At the end of this assignment, mark your repository with the tag `ca5`.

## Step-by-Step Instructions

### Step 1: Set Up Jenkins in a Docker Container

1. **Install Docker**
    - Follow the instructions to install Docker from [the official Docker website](https://docs.docker.com/get-docker/).

2. **Run Jenkins in Docker**
    - Create this following Dockerfile in `/CA5`
    ```Dockerfile
   FROM jenkins/jenkins:lts-jdk17
   USER root
   RUN apt-get update -qq \
   && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
   RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
   RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
   RUN apt-get update  -qq \
   && apt-get -y install docker-ce
   RUN usermod -aG docker jenkins
   ```
   Let's break down each step of the Dockerfile:

1. **FROM jenkins/jenkins:lts-jdk17**:
   - This specifies the base image for the Dockerfile. In this case, it's the Jenkins Long-Term Support (LTS) image with JDK 17.

2. **USER root**:
   - Switches the user to `root` to perform administrative tasks such as installing packages.

3. **RUN apt-get update -qq \ && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common**:
   - **apt-get update -qq**: Updates the list of available packages and their versions. The `-qq` flag makes the output less verbose.
   - **apt-get install -qqy**: Installs the specified packages. The `-qqy` flags make the installation process quieter and automatically answer "yes" to prompts.
      - **apt-transport-https**: Allows `apt` to use repositories accessed via HTTPS.
      - **ca-certificates**: Contains root certificates for SSL.
      - **curl**: A tool for transferring data with URLs.
      - **gnupg2**: A complete and free implementation of the OpenPGP standard.
      - **software-properties-common**: Adds `add-apt-repository` command.

4. **RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -**:
   - Downloads the GPG key for Docker's official repository and adds it to the system's list of trusted keys. This ensures the integrity and authenticity of the packages.

5. **RUN add-apt-repository \ "deb [arch=amd64] https://download.docker.com/linux/debian \ $(lsb_release -cs) \ stable"**:
   - Adds Docker's official repository to the list of package sources.
   - **[arch=amd64]**: Specifies the architecture.
   - **https://download.docker.com/linux/debian**: The URL of Docker's repository.
   - **$(lsb_release -cs)**: Dynamically inserts the Debian codename of the current release (e.g., "buster" for Debian 10).
   - **stable**: Specifies the stable release channel.

6. **RUN apt-get update -qq \ && apt-get -y install docker-ce**:
   - Updates the package list again to include packages from the newly added Docker repository.
   - Installs Docker Community Edition (`docker-ce`).

7. **RUN usermod -aG docker jenkins**:
   - Adds the `jenkins` user to the `docker` group, allowing Jenkins to run Docker commands without needing root privileges.

    - After writing it, build the Docker image:
      ```bash
      docker build -t jenkins-docker .
      ```
    - Start a Jenkins container:
      ```bash
       docker run -it -p 8080:8080 -p 50000:50000 -v //var/run/docker.sock:/var/run/docker.sock -v jenkins_home2:/var/jenkins_home jenkins-with-docker
      ```
3. **Access Jenkins**
    - Open Jenkins in your browser at `http://localhost:8080`.

4. **Configure Jenkins**
    - Complete the initial setup wizard.
    - Install necessary plugins such as Git, Gradle, Nodejs and HTMLPublisher.
    - Add NodeJs installer as a tool in Jenkins (required to run the Assemble stage of the Spring Boot pipeline).
    - Create a DockerHub credential with the ID `dockerHubCredentials` (required for the PublishImage stage of the Spring Boot pipeline).
    - Configure the DockerHub registry in the Jenkins global configuration.
    - Create a new pipeline job for each project (Gradle and Spring Boot).

### Step 2: Create Jenkins Pipeline

#### Gradle Project

1. **Access Jenkins Dashboard**
    - Open Jenkins in your browser (usually `http://localhost:8080`).

2. **Create a New Job**
    - Click on "New Item" to create a new pipeline job.
    - Enter a name for your job (e.g., `CA5-Pipeline-Gradle`), select "Pipeline", and click "OK".

3. **Configure Pipeline Script**
    - In the job configuration page, scroll down to the "Pipeline" section.
    - Select "Pipeline script from SCM".
    - Choose "Git" as the SCM and enter the URL of your repository (in my case https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837.git) .
    - Specify the relative path to your `Jenkinsfile` (e.g., `CA5/gradle/Jenkinsfile`).

4. **Write Jenkinsfile**
    - In your repository, create a file named `Jenkinsfile` inside the `CA5/gradle` folder.
    - Define the pipeline stages:

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837.git', branch: 'master'
            }
        }
        stage('Assemble') {
            steps {
                dir('CA2/Part1/gradle_demo') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew assemble'
                }
            }
        }
        stage('Test') {
            steps {
                dir('CA2/Part1/gradle_demo') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew test'
                }
               junit '**/build/test-results/test/*.xml'
            }
        }
        stage('Archive') {
            steps {
                dir('CA2/Part1/gradle_demo'){
                    archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
                }

            }
        }
    }
}
```
Let's break down the provided Jenkins pipeline script step by step.

### General Structure
This Jenkins pipeline script defines a series of steps to checkout the code, assemble the project, run tests, and archive the build artifacts.

### Stages Breakdown

#### 1. Checkout
```groovy
stage('Checkout') {
    steps {
        git url: 'https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837.git', branch: 'master'
    }
}
```
- This stage checks out the code from the specified Git repository and branch (`master`).

#### 2. Assemble
```groovy
stage('Assemble') {
    steps {
        dir('CA2/Part1/gradle_demo') {
            sh 'chmod +x ./gradlew'
            sh './gradlew assemble'
        }
    }
}
```
- This stage assembles the project using Gradle. The `chmod +x ./gradlew` command makes the Gradle wrapper executable.
- `./gradlew assemble` runs the Gradle `assemble` task, which compiles the source code and packages it into a JAR file.

#### 3. Test
```groovy
stage('Test') {
    steps {
        dir('CA2/Part1/gradle_demo') {
            sh 'chmod +x ./gradlew'
            sh './gradlew test'
        }
        junit '**/build/test-results/test/*.xml'
    }
}
```
- This stage runs tests using Gradle. Again, the `chmod +x ./gradlew` command ensures the Gradle wrapper is executable.
- `./gradlew test` runs the tests.
- The `junit '**/build/test-results/test/*.xml'` command publishes the test results using the JUnit plugin, making them available in the Jenkins UI.

#### 4. Archive
```groovy
stage('Archive') {
    steps {
        dir('CA2/Part1/gradle_demo') {
            archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
        }
    }
}
```
- This stage archives the built JAR file as a Jenkins artifact.
- The `dir('CA2/Part1/gradle_demo')` block ensures the commands are executed in the correct directory.
- `archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true` archives any JAR files found in the `build/libs` directory.

#### Spring Boot Application

1. **Create a New Job**
    - Click on "New Item" to create a new pipeline job.
    - Enter a name for your job (e.g., `CA5-Pipeline-SpringBoot`), select "Pipeline", and click "OK".

2. **Configure Pipeline Script**
    - In the job configuration page, scroll down to the "Pipeline" section.
    - Select "Pipeline script from SCM".
    - Choose "Git" as the SCM and enter the URL of your repository (in my case https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837.git).
    - Specify the relative path to your `Jenkinsfile` (e.g., `CA5/spring-boot/Jenkinsfile`).

3. **Write Jenkinsfile**
    - In your repository, create a file named `Jenkinsfile` inside the `CA5/spring-boot` folder.
    - Define the pipeline stages:

```groovy
pipeline {
   agent any

   environment{
      DOCKER_IMAGE = 'ozepedrorodrigues/jenkins-with-docker'
      DOCKER_REGISTRY = 'https://index.docker.io/v1/'
      DOCKER_CREDENTIALS_ID = 'dockerHubCredentials'
   }

   stages {
      stage('Checkout') {
         steps {
            git url: 'https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837.git', branch: 'master'
         }
      }
      stage('Create Dockerfile') {
         steps {
            dir('CA2/Part2/demo') {
               echo 'Creating Dockerfile...'
               script {
                  writeFile file: 'Dockerfile', text: '''
                        # Use the official OpenJDK image as a parent image
                        FROM openjdk:17-jdk-alpine

                        # Set the working directory in the container
                        WORKDIR /app

                        # Copy the WAR file from the host to the container
                        COPY build/libs/*.war app.war

                        # Expose the port that the application will run on
                        EXPOSE 8080

                        # Run the WAR file
                        ENTRYPOINT ["java", "-jar", "app.war"]
                        '''
               }
            }
         }
      }
      stage('Assemble') {
         steps {
            dir('CA2/Part2/demo') {
               sh 'chmod +x ./gradlew'
               sh './gradlew assemble'
            }
         }
      }
      stage('Test') {
         steps {
            dir('CA2/Part2/demo') {
               sh 'chmod +x ./gradlew'
               sh './gradlew test'
            }
            junit '**/build/test-results/test/*.xml'
         }
      }
      stage('Javadoc') {
         steps {
            dir('CA2/Part2/demo') {
               sh './gradlew javadoc'
               publishHTML(target: [
                       reportDir: 'build/docs/javadoc',
                       reportFiles: 'index.html',
                       reportName: 'Javadoc',
                       keepAll: true,
                       alwaysLinkToLastBuild: true,
                       allowMissing: false
               ])
            }
         }
      }
      stage('Archive') {
         steps {
            archiveArtifacts artifacts: 'CA2/Part2/demo/build/libs/*.war', allowEmptyArchive: true
         }
      }
      stage('PublishImage') {
         steps {
            dir('CA2/Part2/demo') {
               script {
                  echo 'Building Docker Image...'
                  sh 'docker info'
                  def app = docker.build("${env.DOCKER_IMAGE}:${env.BUILD_ID}", '.')
                  docker.withRegistry(env.DOCKER_REGISTRY, env.DOCKER_CREDENTIALS_ID) {
                     app.push()
                  }
               }
            }
         }
      }}
}
```
- Let's break down the provided Jenkins pipeline script step by step:

### General Structure
The pipeline script is structured using Jenkins' Declarative Pipeline syntax. It is used to define the steps required to build, test, and deploy your project.

### Environment Variables
```groovy
environment{
    DOCKER_IMAGE = 'ozepedrorodrigues/jenkins-with-docker'
    DOCKER_REGISTRY = 'https://index.docker.io/v1/'
    DOCKER_CREDENTIALS_ID = 'dockerHubCredentials'
}
```
- `DOCKER_IMAGE`: Name of the Docker image to be created.
- `DOCKER_REGISTRY`: Docker registry URL.
- `DOCKER_CREDENTIALS_ID`: Credentials ID used to authenticate with Docker Hub.

### Stages Breakdown

#### 1. Checkout
```groovy
stage('Checkout') {
    steps {
        git url: 'https://github.com/zepedrorodrigues/devops-23-24-JPE-PSM-1231837.git', branch: 'master'
    }
}
```
- This stage checks out the code from the specified Git repository and branch.

#### 2. Create Dockerfile
```groovy
stage('Create Dockerfile') {
    steps {
        dir('CA2/Part2/demo') {
            echo 'Creating Dockerfile...'
            script {
                writeFile file: 'Dockerfile', text: '''
                    FROM openjdk:17-jdk-alpine
                    WORKDIR /app
                    COPY build/libs/*.war app.war
                    EXPOSE 8080
                    ENTRYPOINT ["java", "-jar", "app.war"]
                '''
            }
        }
    }
}
```
- This stage creates a Dockerfile in the specified directory. The Dockerfile uses an OpenJDK base image and sets up the environment to run a Java application packaged as a WAR file.

#### 3. Assemble
```groovy
stage('Assemble') {
    steps {
        dir('CA2/Part2/demo') {
            sh 'chmod +x ./gradlew'
            sh './gradlew assemble'
        }
    }
}
```
- This stage assembles the project using Gradle. It first makes the Gradle wrapper executable and then runs the `assemble` task to build the project.

#### 4. Test
```groovy
stage('Test') {
    steps {
        dir('CA2/Part2/demo') {
            sh 'chmod +x ./gradlew'
            sh './gradlew test'
        }
        junit '**/build/test-results/test/*.xml'
    }
}
```
- This stage runs tests using Gradle. It then publishes the test results using the JUnit plugin.

#### 5. Javadoc
```groovy
stage('Javadoc') {
    steps {
        dir('CA2/Part2/demo') {
            sh './gradlew javadoc'
            publishHTML(target: [
                reportDir: 'build/docs/javadoc',
                reportFiles: 'index.html',
                reportName: 'Javadoc',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: false
            ])
        }
    }
}
```
- This stage generates Javadoc documentation using Gradle and then publishes the generated HTML files.

#### 6. Archive
```groovy
stage('Archive') {
    steps {
        archiveArtifacts artifacts: 'CA2/Part2/demo/build/libs/*.war', allowEmptyArchive: true
    }
}
```
- This stage archives the built WAR file as a Jenkins artifact.

#### 7. PublishImage
```groovy
stage('PublishImage') {
    steps {
        dir('CA2/Part2/demo') {
            script {
                echo 'Building Docker Image...'
                sh 'docker info'
                def app = docker.build("${env.DOCKER_IMAGE}:${env.BUILD_ID}", '.')
                docker.withRegistry(env.DOCKER_REGISTRY, env.DOCKER_CREDENTIALS_ID) {
                    app.push()
                }
            }
        }
    }
}
```
- This stage builds a Docker image from the Dockerfile created earlier. It then pushes the image to the Docker registry using the provided credentials.
- In order to the `PublishImage` stage to work, you need to create a credential in Jenkins with the ID `dockerHubCredentials` and your DockerHub credentials.
- The `docker` command is available in Jenkins by default, so you don't need to install any additional plugins.
- 
### Step 3: Run the Pipelines

1. **Build the Jobs**
    - Save the job configurations and click on "Build Now" for each job.
    - Monitor the build progress and ensure all stages complete successfully.

2. **Review Results**
    - Check the console output for detailed logs.
    - Verify the test reports, Javadoc, and deployment results.

### Conclusion

By following these steps, you will have fully functional CI/CD pipelines using Jenkins running in a Docker container for both your Gradle and Spring Boot projects. Ensure to commit your changes frequently and provide a concise technical report in the `README.md` file.

For any issues or further clarifications, feel free to reach out to the course instructors.