# Technical Report for CA2 Part 2: Build Tools with Gradle

## Introduction

This comprehensive report documents the methodologies, challenges, solutions, and key learning outcomes from undertaking Part 2 of Class Assignment 2 (CA2) in a DevOps course. The primary focus of this segment was to migrate a Maven-based Spring Boot application to utilize Gradle for build automation. This transition required not only the adaptation of build scripts but also the integration of frontend processing tools into the Gradle ecosystem, thereby showcasing Gradle’s flexibility and power in managing complex project structures.

## Setup and Conversion

### Creating and Configuring the Gradle Project

#### Creating a New Branch for Gradle Conversion
To isolate changes and ensure a smooth workflow, a new branch titled `tut-basic-gradle` was created. This practice supports modular development and version control best practices.

- **Command Used:** `git checkout -b tut-basic-gradle`
- **Rationale:** Isolating feature development ensures that the master branch remains stable and that any changes can be thoroughly tested before integration.

#### Initializing the Project with Spring Initializr
Utilizing Spring Initializr (https://start.spring.io), a new Gradle project was bootstrapped with dependencies tailored to replicate the Maven project's environment. This step is crucial for aligning the new Gradle setup with the project’s existing Spring Boot framework requirements.

- **Dependencies Selected:** Rest Repositories, Thymeleaf, JPA, H2.
- **Outcome:** A scaffolded Gradle project ready for the integration of existing application code.

### Adapting the Project
The original `src` directory was removed to pave the way for integrating the Maven project's source code. This approach ensures that the application logic and structure remain consistent across the migration.

- **Steps Undertaken:**
  1. Deleted the auto-generated `src` folder, using the bash command `rm -rf src`.
  2. Copied the Maven project’s `src` folder, along with `webpack.config.js` and `package.json`, into the new Gradle project directory, using the bash commands `cp webpack.config.js destination/directory` and `cp package.json destination/directory` .
  3. Removed the `src/main/resources/static/built/` directory to prevent conflicts with Gradle’s build process using the bash command `rm -rf src/main/resources/static/built/`.

## Frontend Integration

### Integrating the Frontend Build Process
The adoption of the `org.siouan.frontend-gradle-plugin` allowed for seamless integration of frontend build processes within the Gradle workflow, mimicking the functionality provided by Maven's frontend plugin.

- **Plugin Configuration:**
  - Specified the Node.js version to ensure compatibility with the project's frontend dependencies.
  - Defined custom scripts (`assembleScript`, `cleanScript`, `checkScript`) to manage frontend assets through Gradle commands.

### Adjusting `package.json` for Gradle
Modifications to `package.json` were necessary to bridge the Node.js-based frontend build process with Gradle's Java-oriented workflow.

- **Configuration Adjustments:**
  - The `scripts` section was updated to include commands for building, cleaning, and verifying the frontend codebase, ensuring these processes are compatible with Gradle’s lifecycle tasks.

## Building and Running the Application

### Executing the Build with Gradle
Running `./gradlew build` compiled both the Java and JavaScript codebases, signifying a successful integration of frontend tasks within the Gradle project.

### Launching the Application
`./gradlew bootRun` was executed to verify the application's functionality post-migration, with particular attention to the intactness of the frontend after the build process.

## Enhancing Build and Management Efficiency with Gradle Tasks

### Creating a Gradle Task to copy tjhe generated jar file
A custom gradle task was created to copy the generated `.jar` file into a folder named `dist` located at the project root folder level.
```groovy
task copyJarToDist(type: Copy) {
dependsOn build
from 'build/libs'
into 'dist'
include '*.jar'
}
```
- **Task Features:**
  - while running the `build` command, `.jar` file(s) will be generated. They will be stored in a file for future use and easier (and safer, providing a backup) access in future builds of the same software.

### Managing Webpack Build Artifacts
A specific Gradle task was designed to clean up the build directory by removing old Webpack artifacts, ensuring a fresh start for each build cycle.

``` groovy
task cleanWebpack(type: Delete) {
delete 'src/main/resources/static/built/'
}

clean.dependsOn cleanWebpack
```
- **Cleanup Configuration:**
  - Automatically triggered before the `clean` task to remove the `static/built` directory, preventing any potential conflicts or redundancies.

## Finalizing Changes and Documenting the Migration

### Committing and Merging Changes
After thorough testing, the changes were committed to the `tut-basic-gradle` branch and subsequently merged into the master branch, preserving the full history of changes.

- **Commands Used:**
  - `git checkout master` - switch to the master branch
  - `git merge --no-ff tut-basic-gradle` - merge changes while preserving the branch history

## Finalizing and Documentation

### Version Control and Documentation
The project was tagged with `ca2-part2` to denote the completion of this assignment phase.

- **Tagging Command:** `git tag -a ca2-part2 -m "Complete CA2 Part 2"`
- **Push Tags:** `git push origin --tags`

The migration process was documented in the `readme.md` file located in the `CA2/Part2/` directory, providing a clear, tutorial-style guide for reproducing the setup.

## Alternative Solution Analysis: Maven as the Alternative Tool

### Implementing the Maven Project

The alternative solution for CA2 Part 2 was developed using Maven, a widely used build automation tool. Here’s a detailed step-by-step guide on how Maven can replicate the tasks implemented in Gradle for the Spring Boot application, focusing on the same key areas: project initialization, frontend integration, packaging, and cleanup.

#### 1. Project Initialization with Maven
- **Spring Initializr:** Similar to the Gradle approach, we used https://start.spring.io but selected Maven as the build tool. The project was initialized with the same dependencies: Spring Web, Thymeleaf, Spring Data JPA, and H2 Database.
- **POM.xml Configuration:** The generated `pom.xml` file serves as Maven’s build configuration file, analogous to Gradle’s `build.gradle`. Dependencies and plugins were declared here.

#### 2. Frontend Integration
- **Frontend Plugin:** To integrate and manage frontend tasks, the `frontend-maven-plugin` was utilized. It was configured to install Node and npm, run npm install, and execute Webpack builds.
    ```xml
    <plugin>
        <groupId>com.github.eirslett</groupId>
        <artifactId>frontend-maven-plugin</artifactId>
        <version>1.9.1</version>
        <configuration>
            <nodeVersion>v16.13.0</nodeVersion>
            <npmVersion>6.14.8</npmVersion>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <goal>install-node-and-npm</goal>
                    <goal>npm</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```
- **Webpack and `package.json`:** The same `package.json` configurations were applied to facilitate npm scripts for building and cleaning the frontend.
    ```json
    "scripts": {
        "build": "webpack --config webpack.config.js",
        "clean": "rimraf ./built",
        "test": "echo \"Error: no test specified\" && exit 1"
    }
    ```

#### 3. Packaging for Distribution
- **Maven Assembly Plugin:** The Maven Assembly Plugin was configured to package the application into a distributable JAR, including all its dependencies.
    ```xml
    <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <configuration>
            <descriptorRefs>
                <descriptorRef>jar-with-dependencies</descriptorRef>
            </descriptorRefs>
            <archive>
                <manifest>
                    <mainClass>com.example.Application</mainClass>
                </manifest>
            </archive>
        </configuration>
    </plugin>
    ```

### 4. Copy the generated jar file to the target directory
- **Maven Resources Plugin:** Used to copy the generated JAR file to the target directory.
    ```xml
    <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-antrun-plugin</artifactId>
				<version>3.0.0</version>
				<executions>
					<execution>
						<phase>package</phase> <!-- Bind to the package phase -->
						<goals>
							<goal>run</goal>
						</goals>
						<configuration>
							<target>
								<echo message="Copying built JAR to dist directory..."/>
								<mkdir dir="${project.build.directory}/../dist"/> <!-- Ensure the directory exists -->
								<copy todir="${project.build.directory}/../dist">
									<fileset dir="${project.build.directory}" includes="*.jar"/>
								</copy>
							</target>
						</configuration>
					</execution>
				</executions>
			</plugin>
    ```
- After this implementation run this command to see the project build and copy the jar to the designated directory
    ```bash
    mvn clean package
    ```

#### 5. Cleaning Up Webpack Artifacts
- **Maven Clean Plugin:** Customized to remove the Webpack-generated files before the clean process.
    ```xml
    <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
            <filesets>
                <fileset>
                    <directory>src/main/resources/static/built</directory>
                </fileset>
            </filesets>
        </configuration>
    </plugin>
    ```
  
- After this implementation run this command to see the project run and clean the desired files
    ```bash
    mvn clean install
    ```


### Comparison: Maven vs. Gradle

The comparison between Maven and Gradle solutions for the Spring Boot application build automation highlights several differences and similarities:

#### Maven
- **XML Configuration:** Maven uses an XML-based configuration (`pom.xml`), which can be verbose but provides a structured approach to defining dependencies and plugins.
- **Lifecycle Phases:** Maven’s build lifecycle is fixed, making it straightforward to understand but less flexible compared to Gradle.
- **Plugin Ecosystem:** Maven has a robust set of plugins, such as the `frontend-maven-plugin`, which closely matches the functionality of Gradle plugins.

#### Gradle
- **Groovy/Kotlin DSL:** Gradle uses a Domain-Specific Language (DSL) for configuration, allowing for more concise and expressive build scripts.
- **Customizability:** Gradle offers more flexibility in defining custom tasks and extending build scripts, catering to complex and unconventional build requirements.
- **Performance:** Gradle features incremental builds and a daemon for faster build times, especially noticeable in larger projects with many dependencies.

### Conclusion

Both Maven and Gradle are capable of fulfilling the project requirements for CA2 Part 2, each with its own set of advantages. Maven’s structured and phase-driven approach offers predictability and a vast plugin repository, making it a solid choice for traditional Java projects. Gradle, with its flexible configuration and performance optimizations, excels in handling complex builds and multi-project setups. The choice between Maven and Gradle ultimately depends on the project’s specific needs, team familiarity, and performance considerations.
Migrating to Gradle from Maven offered an enriching learning experience, underscoring Gradle’s robustness and versatility in handling multi-faceted Java projects. This exercise not only demonstrated Gradle’s capacity for backend and frontend integration but also its adaptability to various development scenarios, making it an invaluable tool in the DevOps toolkit. The exploration of an alternative build tool further enriched this learning journey, providing a holistic view of build automation's role in streamlining development workflows.