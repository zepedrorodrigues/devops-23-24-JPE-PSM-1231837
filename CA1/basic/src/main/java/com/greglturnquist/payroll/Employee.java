/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private String	jobTitle;
	private int jobYears;
	private String email;

<<<<<<< HEAD
	private String email;

	public Employee() {}

	public Employee(String firstName, String lastName, String description, String jobTitle, int jobYears,String email) {
		if(!validString(firstName,lastName,description,jobTitle,email)||jobYears<0||!validEmail(email))
=======
	public Employee() {}

<<<<<<< HEAD
	public Employee(String firstName, String lastName, String description, String jobTitle, int jobYears) {
		if(!validString(firstName,lastName,description,jobTitle)||jobYears<0)
>>>>>>> db4d52f (CA1 First week assignment)
=======
	public Employee(String firstName, String lastName, String description, String jobTitle, int jobYears, String email) {
		if(!validString(firstName,lastName,description,jobTitle,email)||jobYears<0)
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
			{throw new IllegalArgumentException("Invalid parameters");}
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobTitle = jobTitle;
		this.jobYears = jobYears;
<<<<<<< HEAD
<<<<<<< HEAD
		this.email = email;
=======
>>>>>>> db4d52f (CA1 First week assignment)
	}
=======
		this.email = email;}
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))

	private boolean validString(String... strings) {
		for(String s: strings) {
			if(s == null || s.trim().length() == 0) {
				return false;}}
		return true;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description)&& Objects.equals(jobTitle, employee.jobTitle)&&
<<<<<<< HEAD
<<<<<<< HEAD
			Objects.equals(jobYears, employee.jobYears) && Objects.equals(email, employee.email);
=======
			Objects.equals(jobYears, employee.jobYears);
>>>>>>> db4d52f (CA1 First week assignment)
=======
			Objects.equals(jobYears, employee.jobYears)&& Objects.equals(email, employee.email);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, description);}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id<0)
			{throw new IllegalArgumentException("Invalid id");}
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(!validString(firstName))
			{throw new IllegalArgumentException("Invalid firstName");}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(!validString(lastName))
			{throw new IllegalArgumentException("Invalid lastName");}
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(!validString(description))
			{throw new IllegalArgumentException("Invalid description");}
		this.description = description;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
<<<<<<< HEAD
		if(!validString(jobTitle))
			{throw new IllegalArgumentException("Invalid jobTitle");}
=======
>>>>>>> db4d52f (CA1 First week assignment)
		this.jobTitle = jobTitle;}

	public int getJobYears() {
		return jobYears;}
<<<<<<< HEAD
	public int setJobYears(int jobYears) {
		if(jobYears<0)
		{throw new IllegalArgumentException("Invalid jobYears");}
		this.jobYears = jobYears;
		return jobYears;}

	public String getEmail() {
		return email;}

	public void setEmail(String email) {
		if(!validString(email)||!validEmail(email))
			{throw new IllegalArgumentException("Invalid email");}
		this.email = email;}

	private boolean validEmail(String email) {
		if(email == null || email.trim().length() == 0||!email.contains("@")) {
			return false;}
		return true;}



=======

	public int setJobYears(int jobYears) {
		if(jobYears<0)
			{throw new IllegalArgumentException("Invalid jobYears");}
		this.jobYears = jobYears;
		return jobYears;}

<<<<<<< HEAD
>>>>>>> db4d52f (CA1 First week assignment)
=======
	public String getEmail() {
		return email;}

	public void setEmail(String email) {
		this.email = email;}

>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", jobTitle='" + jobTitle + '\'' +
			", jobYears='" + jobYears + '\'' +
<<<<<<< HEAD
<<<<<<< HEAD
			", email='" + email + '\'' +
=======
>>>>>>> db4d52f (CA1 First week assignment)
=======
			", email='" + email + '\'' +
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
			'}';
	}
}
// end::code[]
