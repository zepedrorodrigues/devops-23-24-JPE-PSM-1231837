# Technical Report for CA1: Version Control with Git

## Introduction

This technical report documents the development and findings of Class Assignment 1 (CA1) focused on implementing version control best practices using Git, and exploring an alternative version control system for comparison. The assignment is divided into two parts: working directly on the master branch initially, followed by the introduction of branching for feature development and bug fixes.

## Part 1: Direct Work on Master Branch

### Objective

To implement the initial setup and a new feature directly on the master branch without using additional branches.

### Implementation Steps

#### Setup and Initial Commit

1. Navigate to the project directory (assuming the Tutorial React.js and Spring Data REST Application is already locally available):
   ```bash
   cd path/to/TutorialReactSpringDataREST
   ```
   - command cd changes the current directory

2. Copy the application into a new CA1 folder:
   ```bash
   cp -r . ../CA1
   cd ../CA1
   ```
   - command cp copies the directories and files stated ('.', the current directory), the '-r' notation says it will be copied recursively (all its contents) and '../CA1' is the destination folder

3. Initialize the Git repository (if not already a Git repository):
   ```bash
   git init
   ```
   - adds a ".git" directory to the current directory (the added directory contains alal the information required for the repository work process)

4. Add all files to the staging area:
   ```bash
   git add .
   ```
   - before being ready to be commited and then pushed to the remote repository, changes must be added to a staging area, covered by this command. The "." notation indicates that ALL the unstaged files in the repository directory should be staged.

5. Commit the added files:
   ```bash
   git commit -m "Initial commit with the Tutorial application"
   ```
   - creates a new commit, containing the current contents of the index (the staged changes to the files in the repository) and the given log message (after "-m") describing the changes.

6. Push the commit to the remote repository:
   ```bash
   git remote add origin <repository-URL>
   git push -u origin master
   ```
   - the first step is only necessary if the local repository is not yet linked to the remote one, as it is its function.
   - The second step  uploads local repository content to the remote repository location.

#### Version Tagging

1. Tag the initial version of the application as `v1.1.0` and push the tag to the remote repository:
   ```bash
   git tag v1.1.0 -m "Initial version, not including Job Years"
   git push origin v1.1.0
   ```
   - creates a tag named 'v1.1.0', holding the message stated above (after "-m")
   - pushes (same logic as before) the corresponding tag to the origin of the repository, saving the information about the state of the project in the tag itself

#### New Feature - Job Years and Completion

1. Creation of Issues
   - In the remote repository, issues should be created for this new feature (named after the portions of the function needed to create, e.g "JobYears untested", "Jobyears feature testing", "JobYears debugged") 
   - These issues are issued numbers, that will be afterwards used in the way I'll further on explain.
   
2. Add new JobYears Field to the Emplyee class constructor with validations::
    ```java
    public Employee(String firstName, String lastName, String description,String JobTitle, int jobYears) {
		if(!validString(firstName, lastName, description, JobTitle)|| !validJobYears(jobYears))
			throw new IllegalArgumentException();
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobTitle = JobTitle;
		this.jobYears = jobYears;
		this.email = email;
	}
   
   private boolean validString(String ... strings){
		for(String str : strings){
			if(str == null || str.isEmpty()){
				return false;}}
		return true;}
   
   private boolean validJobYears(int jobYears){
		 if(jobYears < 0){return false;}
		 return true;}
    ```
3. Add the new field to the _Employee_ class getters and setters:
   ```java
   public int getJobYears() {
       return jobYears;
   }

   public void setJobYears(int jobYears) {
   if(!validJobYears(jobYears)) throw new IllegalArgumentException();    
   this.jobYears = jobYears;
   }
   ```
4. Add the new field to the _Employee_ class toString method:
   ```java
   public String toString() {
   return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + ", jobYears=" + this.jobYears + '}';
   ```
5. Add the new field to the equals method:
    ```java
    public boolean equals(Object o) {
		 if (this == o) return true;
		 if (o == null || getClass() != o.getClass()) return false;
		 Employee employee = (Employee) o;
		 return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
			Objects.equals(jobYears, employee.jobYears)
	}
    ```

6. Add the new field to the _Employee_ class hashCode method:
    ```java
    public int hashCode() {
		 return Objects.hash(id, firstName, lastName, description, jobYears);
	}
    ```
7. Create an EmployeeTest class and add unit tests for the new field, ensuring that all methods and validations are covered:
   ```java
   class EmployeeTest {

    @Test
    void createEmployee_Success() throws InstantiationException {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        Employee employee = new Employee(firstName, lastName, description, jobYears, "frodo.baggins@shire.com");
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(description, employee.getDescription());
        assertEquals(String.valueOf(jobYears), employee.getJobYears());
    }

    @Test
    void createEmployee_InvalidFirstName(){
        String firstName = "";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));
    }

    @Test
    void createEmployee_InvalidLastName(){
        String firstName = "Frodo";
        String lastName = "";
        String description = "ring bearer";
        int jobYears = 1;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));
    }

    @Test
    void createEmployee_InvalidDescription(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "";
        int jobYears = 1;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));
    }

    @Test
    void createEmployee_InvalidJobYears(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = -1;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));
    }

    @Test
    void createEmployee_NullFirstName(){
        String firstName = null;
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 0;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));
    }

    @Test
    void createEmployee_NullLastName(){
        String firstName = "Frodo";
        String lastName = null;
        String description = "ring bearer";
        int jobYears = 0;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));

    }

    @Test
    void createEmployee_NullDescription(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = null;
        int jobYears = 0;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com"));
    }

   @Test
   void createEmployeeValidJobYears() throws IllegalArgumentException {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        Employee employee = new Employee(firstName, lastName, description, jobYears);} 
   
   @Test
    void createEmployee_ZeroJobYears() throws IllegalArgumentException {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 0;
        Employee employee = new Employee(firstName, lastName, description, jobYears,"frodo.baggins@shire.com");
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(description, employee.getDescription());
        assertEquals(String.valueOf(jobYears), employee.getJobYears());
    }
   
    @Test
    void createEmployee_NegativeJobYears(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = -1;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears);}
}

9. 9. Add the new field to the render methods in the app.js Javascript file:
```javascript
   class EmployeeList extends React.Component{
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
						<th>Job Years</th>
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}
````
```javascript
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobYears}</td>
			</tr>
		)
	}
}
````
10. Add the new field to the run method in the DatabaseLoader class (you can also add new entries):
```java
   	public void run(String... strings) throws Exception { // <4>
    this.repository.save(new Employee("Frodo", "Baggins", "ring bearer", 2));
    this.repository.save(new Employee("Bilbo", "Baggins", "burglar", 35));
    this.repository.save(new Employee("Gandalf", "the Grey", "wizard", 10000));
}
```

11. Open a bash in the basic folder of the app and run the following command:
```bash
./mvnw spring-boot:run
```
12. Open a browser and navigate to [http://localhost:8080/employees](http://localhost:8080/employees) to see the new field in action.
13. After implementing the feature and tests, add the changes and commit:
   ```bash
   git add .
   git commit -m "Fix #<issue-number> Added jobYears field to Employee entity with tests"
   ```
   - the commit message will be this one and the corresponding issues #<issue-number> will be closed

14. Tag the new version and push, and then mark the assignment completion:
   ```bash
   git tag v1.2.0 -m "Added jobYears field with tests and Debugged"
   git push
   git push origin v1.2.0
   git tag ca1-part1
   git push origin ca1-part1
   ```
   - the logic for these command lines have previously been explained
   - the tag message is important to keep the users/developers up to date on the most current state of the software being developed

## Part 2: Using Branches for Development

### Objective

To use branches for developing new features and fixing bugs, with the master branch serving as the stable version base.

### Implementation Steps

#### Feature Development - Email Field

1. Create issues in the remote repository to be further resolved during the Assignment
    - after creating the issues, you can associate them with specific project branches (to be created)
    - in the merging processes, the remote repository will look for indications in the commit messages of the merged branches that these commits have fixes issues #X.
2. Create and switch to the `email-field` branch:
   ```bash
   git checkout -b email-field
   ```
   - forces the creation of a new branch (checkout command changes the branch being worked on and the "-b" notation enables branch creation if the branch does not exist)
   - I opted for this way because it is the simplest and shortest way to create a branch
   - the alternative would be
   ```bash
   git branch email-field
   git checkout email-field
   ```
3. Add the new field to the _Employee_ class:
```java
private String email;
```
4. Add the new field to the _Employee_ class constructor and add validations to the _Employee_ class constructor so that the parameters are always valid:
```java
public Employee(String firstName, String lastName, String description,String JobTitle, int jobYears, String email) {
   if(!validString(firstName, lastName, description, JobTitle,email)|| !validJobYears(jobYears)|| !validEmail(email))
      throw new IllegalArgumentException();
   this.firstName = firstName;
   this.lastName = lastName;
   this.description = description;
   this.jobTitle = JobTitle;
   this.jobYears = jobYears;
   this.email = email;
}

private boolean validEmail(String email){
   if(email == null || email.isEmpty()))
      return false;
   return true;
}
```
5. Add the new field to the _Employee_ class getters and setters:
```java
public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}
```
6. Add the new field to the _Employee_ class toString method:
```java
public String toString() {
return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + ", jobYears=" + this.jobYears + ", email='" + this.email + '\'' + '}';}
```
7.Add the new field to the equals method:
```java
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id) &&
            Objects.equals(firstName, employee.firstName) &&
            Objects.equals(lastName, employee.lastName) &&
            Objects.equals(description, employee.description) &&
            Objects.equals(jobYears, employee.jobYears) &&
            Objects.equals(email, employee.email);
}
```
8. Add the new field to the _Employee_ class hashCode method:
```java
public int hashCode() {
    return Objects.hash(id, firstName, lastName, description, jobYears, email);
}
```
9. Create an EmployeeTest class and add unit tests for the new field, ensuring that all methods and validations are covered (since the constructor was changed, the tests for the job years field will also be updated):
```java
cclass EmployeeTest {

    @Test
    void createEmployee_Success() throws IllegalArgumentException {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        Employee employee = new Employee(firstName, lastName, description, jobYears, "frodo.baggins@shire.com");
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(description, employee.getDescription());
        assertEquals(String.valueOf(jobYears), employee.getJobYears());
    }

    @Test
    void createEmployee_EmtpyEmail(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 0;
        String email = "";
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,email));
    }

    @Test
    void createEmployee_NullEmail(){
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 0;
        String email = null;
        assertThrows(InstantiationException.class, () -> new Employee(firstName, lastName, description, jobYears,email));
    }
}
```
10. Add the new field to the render methods in the app.js Javascript file:
```javascript
class EmployeeList extends React.Component{
    render() {
        const employees = this.props.employees.map(employee =>
            <Employee key={employee._links.self.href} employee={employee}/>
        );
        return (
            <table>
                <tbody>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Description</th>
                        <th>Job Years</th>
                        <th>Email</th>
                    </tr>
                    {employees}
                </tbody>
            </table>
        )
    }
}
```
```javascript
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobYears}</td>
				<td>{this.props.employee.email}</td>
			</tr>
		)
	}
}
```
11. Add the new field to the run method in the DatabaseLoader class (you can also add new entries):
```java
	public void run(String... strings) throws Exception { // <4>
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer", 2,"frodo.baggins@shire.com"));
		this.repository.save(new Employee("Bilbo", "Baggins", "burglar", 35, "bilbo.baggins@shire.com"));
		this.repository.save(new Employee("Gandalf", "the Grey", "wizard", 10000,"gandalf_the_grey@youshallnotpass.com"));
	}
```

12. After implementing the feature and conducting tests, add and commit the changes, then push the branch:
   ```bash
   git add .
   git commit -m "Fixed #<issue-number> Added email field to Employee entity with validation and tests"
   git push origin email-field
   ```
   - all these commands have been previously explained
   - we are still working in the "temporary" branch for the email fixture, not in the master

13. Merge the feature branch into master and tag the new version:
   ```bash
   git checkout master
   git merge --no-ff email-field
   git tag v1.3.0 -m "email field included, not yet checked for '@' character presence"
   git push origin master
   git push origin v1.3.0
   ```
   - changes to the master branch and merges the previous changes made in the email branch with the master branch (all conflicts should be manually resolved)
   - the "--no-ff" notations stops the merge from making a "fast-forward merge" (happens when the current branch hasn't diverged from the target branch you're merging in. Instead of creating a new commit to represent the merge, Git just moves the current branch forward until it's at the same commit as the target branch.)
   - This is useful for preserving the history of a feature branch before it gets integrated into the main branch, making it easier to understand the flow of changes and to revert entire features if needed.
   - As always, every major step should be followed by a tag creation and pushing to origin of such tag

#### Bug Fix - Valid Email Check

1. Create the issues regarding this chapter
2. Create and switch to the `fix-invalid-email` branch:
   ```bash
   git checkout -b fix-invalid-email
   ```

3. Add validation to the _Employee_ class to ensure that the email field contains the '@' character:
```java
private boolean validEmail(String email){
   if(email == null || email.isEmpty() || !email.contains("@"))
      return false;
   return true;
}
```
4. Add tests to the _EmployeeTest_ class to ensure that the email validation is working:
```java
@Test
void createEmployee_InvalidEmail(){
    String firstName = "Frodo";
    String lastName = "Baggins";
    String description = "ring bearer";
    int jobYears = 1;
    String email = "frodo.baggins";
    assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears,email));
}
```
5. After fixing and testing, add, commit, and push the bug fix:
   ```bash
   git add .
   git commit -m "Fix #<issue-number> Implemented validation for email field in Employee entity"
   git push origin fix-invalid-email
   ```

3. Merge the bug fix into master and tag:
   ```bash
   git checkout master
   git merge fix-invalid-email
   git tag v1.3.1 -m "email field included, checked for '@' character presence"
   git push origin master
   git push origin v1.3.1
   ```

4. Mark the completion of part 2:
   ```bash
   git tag ca1-part2 -m "End of CA1 Part 2"
   git push origin ca1-part2
   ```

## Alternative Version Control System Analysis

### SVN as an Alternative

Subversion (SVN) is a centralized version control system,
contrasting with Git's decentralized nature. This fundamental difference influences how projects are managed and how teams collaborate. SVN manages version control through a central repository, which means that changes are directly committed to this central repository, unlike Git, where each developer has their own repository copy.

### Comparison to Git

1. **Centralized vs. Distributed**: SVN's centralized model means that there's a single source of truth for the project's history, which can simplify access control and revision tracking. However, it also means that operations like committing require connectivity to the server, whereas Git can handle commits locally.

2. **Branching and Merging**: Git's branching and merging are more lightweight and flexible compared to SVN. In Git, branches are used extensively for even small features or fixes, thanks to the ease of creating and merging branches. SVN supports branching but it's traditionally been more heavyweight, making it less common for trivial changes.

3. **Storage and Performance**: Git is generally faster than SVN for most operations, due to its local operation mode and efficient storage mechanisms. SVN can be slower, especially with large repositories or when network latency is an issue.

4. **Binary Files**: SVN handles binary files more gracefully than Git. In SVN, binary files are versioned in a way that only the differences between versions are stored, whereas Git stores the entire binary file for each change, which can quickly increase the repository size.

### Implementing the Assignment Goals with SVN

#### Initial Setup

1. **Create SVN Repository**: An SVN repository can be created on a server that all team members have access to.
   ```bash
   svnadmin create /path/to/repository
   ```

2. **Check Out the Repository**: Developers check out the repository to their local machines to start working on the project.
   ```bash
   svn checkout http://svn.example.com/repository
   ```

#### Adding the Tutorial Application

1. **Add Project to the Repository**: Navigate to the project directory and add the project files to the repository.
   ```bash
   cd myproject
   svn add *
   ```

2. **Initial Commit**: Commit the added files to the repository.
   ```bash
   svn commit -m "Initial import of the Tutorial React.js and Spring Data REST Application"
   ```

#### Version Tagging in SVN

1. **Create a Tag for the Initial Version**: SVN uses the concept of "copy" for branching and tagging. To tag a specific commit, you create a copy of it in the `tags` directory.
   ```bash
   svn copy http://svn.example.com/repository/trunk \
           http://svn.example.com/repository/tags/v1.1.0 \
           -m "Tagging the 1.1.0 release of the project."
   ```

#### Implementing New Features and Bug Fixes

1. **Branching for a New Feature**: Similar to tagging, create a branch by copying the trunk or another branch.
   ```bash
   svn copy http://svn.example.com/repository/trunk \
           http://svn.example.com/repository/branches/email-field \
           -m "Creating a branch for email field feature."
   ```

2. **Merging the Feature Branch**: After completion, the feature branch can be merged back into the trunk.
   ```bash
   cd path/to/working-copy/trunk
   svn merge --reintegrate http://svn.example.com/repository/branches/email-field
   svn commit -m "Merged the email-field feature into trunk."
   ```

### Implementing SVN as the Alternative

The implementation steps detailed above illustrate how SVN can be applied to achieve similar version control goals as Git for this assignment. The main difference lies in SVN's centralized approach, requiring constant connectivity for committing changes and less flexibility in branching and merging compared to Git. However, SVN's straightforward handling of binary files and its simpler model might be advantageous for certain projects or teams less familiar with Git's complexities.
