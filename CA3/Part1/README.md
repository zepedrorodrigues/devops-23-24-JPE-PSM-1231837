# Technical Report for CA3 Part 1: Virtualization with Vagrant

## Introduction

In the evolving field of software development, the ability to create and manage consistent development environments is crucial. Virtualization offers a robust solution by allowing developers to simulate multiple operating systems on a single hardware platform, facilitating better control over diverse development scenarios. This part of the Class Assignment 3 (CA3) focuses on harnessing the capabilities of virtualization using VirtualBox and Vagrant, specifically tailored for students utilizing either traditional PCs or Apple machines with ARM64 architecture.

The primary objective of this assignment is to apply virtualization techniques to migrate and run existing projects from previous assignments within a virtualized Ubuntu environment. This setup will replicate the development environment uniformly across different systems, ensuring that all functionalities and dependencies are managed with consistency and isolation from the host systems.

This report aims to document the processes involved in setting up the virtual machine (VM), configuring necessary development tools, and running two specific projects: a Spring Boot tutorial and a Gradle demonstration. Through detailed steps and guided explanations, this report will serve as a comprehensive tutorial, allowing future students to replicate the setup and understand the practical implications of virtualization in software development workflows.

## Setup and Configuration

This section outlines the steps to set up and configure the virtual machine using VirtualBox (or UTM for Apple users with ARM64 architecture), along with the installation of necessary development tools required for the projects.

### Creating the Virtual Machine

#### 1. **Download and Install VirtualBox or UTM**: Start by downloading VirtualBox from [Oracle's website](https://www.virtualbox.org/) or UTM from [UTM's website](https://mac.getutm.app/) if you are using an Apple machine with ARM64.


#### 2. **Setting Up a New VM**:
  - Open VirtualBox/UTM and click on "New" to create a new virtual machine.
  - Name your VM (e.g., "UbuntuDev") and choose "Linux" as the type and "Ubuntu (64-bit)" as the version.
  - Allocate memory (RAM): Recommended to set at least 2048 MB for adequate performance.
  - Create a virtual hard disk: Choose VDI (VirtualBox Disk Image) and allocate at least 20 GB of space. Set the storage on the physical hard disk as dynamically allocated.

#### 3. **Install Ubuntu**:
- Download the Ubuntu Server ISO file from [Ubuntu's official site](https://ubuntu.com/download/server).
- Mount the ISO file to your VM: In VirtualBox, select your VM, click on "Settings," go to "Storage," click on "Empty" under Controller: IDE, then click on the disk icon next to "Optical Drive" and select "Choose a disk file..." Locate and select your downloaded ISO file.
- Start the VM and follow the on-screen instructions to install Ubuntu. During installation, select the standard utilities for a server and, if prompted, install OpenSSH server to enable remote connections.


#### 4. **Virtualization and Networking Setup**:
4.1 **Create a Host-Only Network**:
   - Open your VM application (e.g., VirtualBox).
   - Navigate to the **Host Network Manager**.
   - Click on **Create** to add a new host-only network.
   - Name and configure this network within my VM's network settings.

4.2 **Setup Networking for the VM**:
   - In your VM's settings, set **Network Adapter 2** to be a **Host-only Adapter**.
   - Check the IP range for the host-only network (e.g., `192.168.56.1/24`).
   - Assign an appropriate IP from this range to the adapter, such as `192.168.56.5`.

#### 5. Initial VM Setup
- Start your VM and log in.
- Update package repositories:
  ```bash
  sudo apt update
  ```
- Install necessary network tools:
  ```bash
  sudo apt install net-tools
  ```
- Configure the network interface by editing the Netplan configuration file:
  ```bash
  sudo nano /etc/netplan/01-netcfg.yaml
  ```

-This is how the `01-netcfg.yaml`should look like:
    
    network:
        version: 2
        renderer: networkd
        ethernets:
            enp0s3:
                dhcp4: yes
            enp0s8:
                addresses:
                    - 192.168.56.5/24

- Apply the changes with:
  ```bash
  sudo netplan apply
  ```

#### 6. Additional Utilities
- **SSH Setup**:
    - Install OpenSSH Server:
      ```bash
      sudo apt install openssh-server
      ```
    - Enable password authentication in the SSH configuration.
    - Restart SSH service:
      ```bash
      sudo systemctl restart ssh
      ```

- **FTP Setup**:
    - Install `vsftpd`:
      ```bash
      sudo apt install vsftpd
      ```
    - Enable write access in the FTP configuration.
    - Restart FTP service:
      ```bash
      sudo systemctl restart vsftpd
      ```

#### Software Installation

Once the Ubuntu server is up and running, proceed with installing the necessary software for your development environment:

1. **Update Your System**:
  - Open a terminal and run the following commands to update your system:
    ``` bash
    sudo apt update
    sudo apt upgrade
    ```

2. **Install Essential Tools**:
  - **Git**: To clone and manage your project repositories.
    ``` bash
    sudo apt install git
    ```
  - **Java Development Kit (JDK)**: Essential for running Java applications.
    ``` bash
    sudo apt install openjdk-17-jdk openjdk-17-jre
    ```
    - The installed JDK version 17 was installed in order for the projects (namingly, CA2 Part2 built with java 17) to be able to run
  

  - **Maven**: For building and managing Java-based projects.
    ``` bash
    sudo apt install maven
    ```
  - **Gradle**: For building and automating Java projects.
    ```bash
    wget https://services.gradle.org/distributions/gradle-8.6-bin.zip
    sudo mkdir /opt/gradle
    sudo unzip -d /opt/gradle gradle-8.6-bin.zip
    echo "export GRADLE_HOME=/opt/gradle/gradle-8.6" >> ~/.bashrc
    echo "export PATH=$PATH:$GRADLE_HOME/bin" >> ~/.bashrc
    source ~/.bashrc
    ```
    - The version 8.6 was chosen for compatibility purposes with Gradle-built projects

3. **Verify Installations**:
  - Ensure all installations are correct by checking the versions of the installed software:
    ``` bash
    git --version
    java -version
    mvn -version
    gradle -version
    ```

Following these steps will prepare your virtual machine with all the necessary tools and configurations to proceed with cloning your repository and running the projects. This setup ensures a standardized development environment that mimics a real-world server setup, providing a robust foundation for further development and learning.

## Project Migration and Setup

This section describes the necessary steps to clone your project repositories into the virtual machine and set up the environment for running the projects. It focuses on the "spring boot tutorial basic project" and the "gradle_basic_demo" project from previous assignments.

#### Cloning the Repository

1. **Open a Terminal in Your VM**:
    - Access the terminal through your VM's interface. If you are using SSH to connect to the VM, ensure it's set up during the Ubuntu installation.

2. **Clone Your Repository**:
    - Navigate to a directory where you want to store your projects, such as `/home/username/projects`.
    - Use the git command to clone your repository. Replace `<repository-url>` with the URL of your GitHub repository:
      ``` bash
      git clone <repository-url>
      ```
    - Enter your directory containing the projects:
      ``` bash
      cd dirctory/holding/projects
      ```

#### Setting Up the Projects

1.  - Configure Maven Wrapper and Gradle Wrapper to give executing permissions:
      ```bash
       chmod +x mvnw
       chmod +x gradlew
       ```
2. **CA1**:
    - Navigate to the project directory:
      ``` bash
      cd directory/with/spring-boot-tutorial-basic
      ```
    - Build the project using Maven:
      ``` bash
      ./mvnw clean install
      ```
    - Run the project:
      ``` bash
      ./mvnw spring-boot:run
      ```
    - Check that the application is running correctly by accessing it from your host machine’s web browser using the VM’s IP address and the port configured in the project.
   ```bash
    ip addr
    ```
   - put the IP and the port 8080 on the browser address.


3. **CA2 Part1**:
    - Navigate to the Gradle project directory:
      ``` bash
      cd directory/holding/gradle_basic_demo
      ```
    - Before building, ensure all Gradle dependencies are set up properly. Since some functionalities might not work due to the lack of a desktop environment, adjust the `build.gradle` if necessary.
   
    - Build the project using Gradle and run the server:
      ``` bash
      ./gradlew build
      ./gradlew runServer
      ```
    - Build the project in your computer terminal and run the client:
      ``` bash
      gradle runClient --args="192.168.56.5 59001"
      ```
    - The project should run smoothly
   


5. **CA2 Part2**:
   - Navigate to the basic folder:
   ``` bash
   cd directory/of/basic/project
   ```
   - Run with gradle:
   ``` bash
   ./gradlew build
    ./gradlew bootRun
   ```
   - Check your VM's IP:
   ```bash
   ip addr
   ```
   - Write `VM'sIP.8080` on your browser address. The application should run smoothly.

### Troubleshooting and Issues

In this section, we outline common problems that may arise during the setup and use of the virtualized environment, along with recommended solutions to ensure smooth operation of our development projects.

#### Network Configuration Errors

- **Symptom**: Inability to connect to the VM from the host machine.
- **Solution**: Verify the network adapter settings in VirtualBox or UTM. Ensure the network mode is set to 'Bridged' or 'NAT' with appropriate port forwarding configured.

#### Software Dependency Issues

- **Symptom**: Project fails to build due to missing or conflicting dependencies.
- **Solution**: Check the project's documentation for dependency requirements and ensure all dependencies are correctly installed. Use virtual environments where applicable to isolate and manage dependencies effectively.

#### Performance Issues

- **Symptom**: Applications run slowly or are unresponsive.
- **Solution**: Increase the VM’s allocated memory and CPU resources in VirtualBox or UTM settings. Ensure our host machine has sufficient resources to share.

#### Access Permissions

- **Symptom**: Permission denied errors when attempting to execute scripts or access certain files.
- **Solution**: Adjust the file permissions using `chmod` or change the ownership with `chown` if necessary. Ensure we are operating with the appropriate user privileges.

### Conclusion

Upon completion of CA3 Part 1, we will have successfully set up a virtualized development environment using VirtualBox or UTM and migrated two key projects into this environment. This exercise not only enhances our understanding of virtualization technology but also equips us with valuable skills in configuring and managing isolated development environments. These skills are crucial in a professional setting where development and testing environments often need to mirror production environments closely.

### Version Control and Documentation

Maintaining a detailed history of changes and proper documentation is essential for project management and future reference. This section provides guidelines on how to manage our version control commitments and documentation effectively.

#### Committing Changes to Your Repository

- **Regular Commits**: Make frequent commits to your repository with descriptive commit messages that clearly explain the changes or enhancements made.
  ```
  git add .
  git commit -m "Describe your changes here"
  git push origin main
  ```

#### Tagging for Release

- **Tagging the Final Submission**: Once you complete your assignment, tag your repository to mark the version of the project that corresponds to your submission.
  ```
  git tag -a ca3-part1 -m "Complete CA3 Part 1"
  git push origin --tags
  ```

#### Documentation

- **Readme File**: Document all steps, configurations, and notable decisions in the `readme.md` file located in the `/ca3/part1/` directory of your repository.
- **Use Markdown**: Employ Markdown syntax to format your documentation clearly and professionally. [Markdown Guide](https://en.wikipedia.org/wiki/Markdown).

By following these practices, we ensure that our project is not only well-documented for grading purposes but also prepared for any further development or deployment phases that might follow in your educational or professional journey.