# Technical Report for CA2 Part 1: Build Tools with Gradle

## Introduction

This technical report is dedicated to documenting the process and insights gained from completing Part 1 of Class Assignment 2 (CA2), which centers around mastering build tools through the practical use of Gradle. The assignment encapsulates a hands-on approach, starting from setting up an example application to applying Gradle for task automation, including server execution, unit testing, and file management tasks.

## Setup and Initial Configuration

### Cloning and Setting Up the Example Application

1. **Clone the Example Application**:
   Begin by cloning the example application provided in the assignment description using the following command:
   ```bash
   cd path/to/your/workspace
   git clone https://bitbucket.org/pssmatos/gradle_basic_demo/ CA2/Part1
   cd CA2/Part1
   ```
   This clones the repository into a folder specifically created for this part of the assignment within your workspace.

2. Create an Issue for the clone task and assign it to yourself.
    - **Issue Title**: "Cloning the Example Application for CA2 Part 1"
    - **Description**: "Create a new issue to track the task of cloning the example application for CA2 Part 1."
    - **Assignee**: Your Name
3. **Initial Commit**:
   After cloning, make sure to add the project to your personal repository and commit it:
   ```bash
   git add .
   git commit -m "Fix #numberoftheissue: Cloned the example application for CA2 Part 1"
   ```

### Understanding the Application

Before diving into Gradle tasks, familiarize yourself with the project structure and Gradle configuration by reviewing the `build.gradle` file and the application's source code. Run the following command to explore Gradle tasks available in the project:
```bash
./gradlew tasks
```

The output should display a list of tasks available for the project, including application tasks, build tasks, and other custom tasks defined in the `build.gradle` file.
1. **Application tasks**:
    - `run`(Runs this project as a JVM application).
2. **Build tasks**:
    - `assemble` (Assembles the outputs of this project)
    - `build` (Assembles and tests this project)
    - `buildDependents` (Assembles and tests this project and all projects that depend on it)
    - `buildNeeded`- Assembles and tests this project and all projects it depends on.
    - `classes` (Assembles main classes)
    - `clean` (Deletes the build directory)
    - `jar` (Assembles a jar archive containing the main classes)
    - `testClasses` (Assembles test classes)
3. **Build Setup tasks**
    - `init` - Initializes a new Gradle build.
    - `wrapper` - Generates Gradle wrapper files.
4. **DevOps tasks**
backupSources - Backs up the source code to a backup folder
   - `runClient` - Launches a chat client that connects to a server on localhost:59001
   - `runServer` - Launches a chat server that listens on port 59001
   - `zipSources` - Creates a zip archive of the source code
5. **Distribution tasks**
   - `assembleDist` - Assembles the main distributions
   - `distTar` - Bundles the project as a distribution.
   - `distZip` - Bundles the project as a distribution.
   - `installDist` - Installs the project as a distribution as-is.
6. **Documentation tasks**
   - `javadoc` - Generates Javadoc API documentation for the main source code.
7. **Help tasks**
    - `buildEnvironment` - Displays all buildscript dependencies declared in root project 'basic_demo'.
    - `dependencies` - Displays all dependencies declared in root project 'basic_demo'.
    - `dependencyInsight` - Displays the insight into a specific dependency in root project 'basic_demo'.
    - `help` - Displays a help message.
    - `outgoingVariants` - Displays the outgoing variants of root project 'basic_demo'.
    - `projects` - Displays the sub-projects of root project 'basic_demo'.
    - `properties` - Displays the properties of root project 'basic_demo'.
    - `tasks` - Displays the tasks runnable from root project 'basic_demo'.
8. **Verification tasks**
    - `check` - Runs all checks.
    - `test` - Runs the test suite.


## Implementing Gradle Tasks

### Task 1: Execute the Server

1. Firstly create an issue for the task and assign it to yourself.
- **Issue Title**: "Implementing a Gradle Task to Execute the Server"
- **Description**: "Create a new issue to track the task of implementing a Gradle task to execute the server."
- **Assignee**: Your Name

2. To add a task for starting the server, modify the `build.gradle` file to include a custom task named `runServer` that uses the `JavaExec` type:

```groovy
task runServer(type:JavaExec, dependsOn: classes){
    group = "DevOps"
    description = "Launches a chat server that listens on port 59001"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}
```
- `type:JavaExec` specifies that the task is of type `JavaExec`.
- `dependsOn: classes` ensures that the `classes` task is executed before the `runServer` task.
- `classpath = sourceSets.main.runtimeClasspath` sets the classpath for the server.
- `mainClass = 'basic_demo.ChatServerApp'` specifies the main class to be executed.
- `args '59001'` passes the port number as an argument to the server.
- `group` and `description` are used to provide information about the task.
- `runServer` is the name of the task.
3. After adding the task, execute the server using the following command:
```bash
./gradlew runServer
```
4. **Committing the Changes**:
   After implementing the server task, commit the changes to your repository:
   ```bash
   git add .
   git commit -m "Fix #numberoftheissue: Implemented a Gradle task to execute the server"
   git push
   ```
### Task 2: Simple Unit Test

1. Create an issue for this task
   - **Issue Title**: "Implementing a Simple Unit Test"
   - **Description**: "Create a new issue to track the task of implementing a simple unit test for the application."
   - **Assignee**: Your Name
2. **Unit Test Implementation**:
   Implement the provided unit test in `src/test/java/basic_demo/AppTest.java` as follows:

   ```java
   package basic_demo;

   import org.junit.Test;
   import static org.junit.Assert.*;

   public class AppTest {
       @Test public void testAppHasAGreeting() {
           App classUnderTest = new App();
           assertNotNull("app should have a greeting", classUnderTest.getGreeting());
       }
   }
   ```

3. **Adding JUnit Dependency**:
   Ensure JUnit 4.12 is added to your `build.gradle` for the test to run:
   ```groovy
   dependencies {
       testImplementation 'junit:junit:4.12'
   }
   ```

4. **Running the Test**:
   Execute the test using the Gradle command:
   ```bash
   ./gradlew test
   ```
5. **Committing the Changes**:
   After implementing the unit test, commit the changes to your repository:
   ```bash
   git add .
   git commit -m "Fix #numberoftheissue: Implemented a simple unit test"
   git push
   ```
### Task 3: Backup of Sources

1. Create an Issue for this task
   - **Issue Title**: "Implementing a Gradle Task to Backup Source Files"
   - **Description**: "Create a new issue to track the task of implementing a Gradle task to backup source files."
   - **Assignee**: Your Name
2. Create a task in the `build.gradle` file to backup source files to a `backup` directory:

```groovy
task backupSources(type: Copy) {
    group = "DevOps"
    description = "Backs up the source code to a backup folder"
    from 'src'
    into 'backup'
}
```

3. Execute the backup task, use:
```bash
./gradlew backupSources
```
4. **Committing the Changes**:
   After implementing the backup task, commit the changes to your repository:
   ```bash
   git add .
   git commit -m "Fix #numberoftheissue: Implemented a Gradle task to backup source files"
   git push
   ```

### Task 4: Archive of Sources
1. Create an Issue for this task
   - **Issue Title**: "Implementing a Gradle Task to Create a Zip Archive of Source Files"
   - **Description**: "Create a new issue to track the task of implementing a Gradle task to create a zip archive of source files."
   - **Assignee**: Your Name
2. Add a task to create a zip archive of the source files:

```groovy
task zipSources(type: Zip) {
    group = "DevOps"
    description = "Creates a zip archive of the source code"
    from 'src'
    archiveFileName = 'source-code.zip'
    destinationDirectory = file("$buildDir/distributions")
}
```

3. Execute the archive task with:
```bash
./gradlew zipSources
```
4. **Committing the Changes**:
   After implementing the archive task, commit the changes to your repository:
   ```bash
   git add .
   git commit -m "Fix #numberoftheissue: Implemented a Gradle task to create a zip archive of source files"
   git push
   ```

## Finalizing the Assignment

1. **Tagging the Repository**:
  After completing all tasks, tag your repository to signify the completion of CA2 Part 1:
  ```bash
  git tag -a ca2-part1 -m "Completed CA2 Part 1 with Gradle tasks"
  git push origin ca2-part1
  ```

2. **Updating README.md**:
  Make sure the README.md reflects all the steps, commands, and code snippets used throughout the assignment, following the markdown syntax for clarity.

## Conclusion

This part of Class Assignment 2 (CA2) provided an invaluable hands-on experience with Gradle, showcasing its power and flexibility as a build automation tool. By completing the tasks outlined in this report, including executing a server, adding unit tests, backing up, and archiving source files, the foundational knowledge of Gradle and its application in real-world scenarios was significantly enhanced.