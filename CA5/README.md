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
    - Pull the Jenkins Docker image by running:
      ```bash
      docker pull jenkins/jenkins:lts
      ```
    - Start a Jenkins container:
      ```bash
      docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
      ```
3. **Access Jenkins**
    - Open Jenkins in your browser at `http://localhost:8080`.

4. **Configure Jenkins**
    - Complete the initial setup wizard.
    - Install necessary plugins such as Git, Gradle, Nodejs and HTMLPublisher.

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
    - Choose "Git" as the SCM and enter the URL of your repository.
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

#### Spring Boot Application

1. **Create a New Job**
    - Click on "New Item" to create a new pipeline job.
    - Enter a name for your job (e.g., `CA5-Pipeline-SpringBoot`), select "Pipeline", and click "OK".

2. **Configure Pipeline Script**
    - In the job configuration page, scroll down to the "Pipeline" section.
    - Select "Pipeline script from SCM".
    - Choose "Git" as the SCM and enter the URL of your repository.
    - Specify the relative path to your `Jenkinsfile` (e.g., `CA5/spring-boot/Jenkinsfile`).

3. **Write Jenkinsfile**
    - In your repository, create a file named `Jenkinsfile` inside the `CA5/spring-boot` folder.
    - Define the pipeline stages:

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ozepedro/devops-23-24-JPE-PSM-1122345.git', branch: 'main'
            }
        }
        stage('Assemble') {
            steps {
               dir('CA2/Part2/demo'){
                    sh 'chmod +x ./gradlew'
                    sh './gradlew assemble'
                }
        }
        stage('Test') {
            steps {
                dir('CA2/Part1/demo') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew test'
                }
                junit '**/build/test-results/test/*.xml'
            }
        }
        stage('Javadoc') {
            steps {
                sh './gradlew javadoc'
                publishHTML([reportDir: 'build/docs/javadoc', reportFiles: 'index.html', reportName: 'Javadoc'])
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.war', allowEmptyArchive: true
            }
        }
        stage('PublishImage') {
            steps {
                script {
                    dockerImage = docker.build("ozepedrorodrigues/spring-boot-app:${env.BUILD_NUMBER}")
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerHubCredentials') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}}
```
- Eventhough `Javadoc` is a task that is initially available in gradle, it requires adjustments to the `build.gradle` file to work properly. In this case, the `javadoc` task was used to generate the Javadoc and the `publishHTML` step was used to publish it in Jenkins.
```groovy
// Configure the existing javadoc task
javadoc {
    source = sourceSets.main.allJava
    classpath = configurations.compileClasspath
    destinationDir = file("$buildDir/docs/javadoc")
    options.encoding = 'UTF-8'
    options.memberLevel = JavadocMemberLevel.PUBLIC
    options.links("https://docs.oracle.com/javase/8/docs/api/")
}
```
- In order to the `PublishImage` stage to work, you need to create a credential in Jenkins with the ID `dockerHubCredentials` and your DockerHub credentials.
- The `docker` command is available in Jenkins by default, so you don't need to install any additional plugins.
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