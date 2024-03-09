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
	private String jobTitle;
	private int jobYears;

	public Employee() {}

	public Employee(String firstName, String lastName, String description,String JobTitle, int jobYears) {
		if(!validString(firstName, lastName, description, JobTitle)|| !validJobYears(jobYears))
			throw new IllegalArgumentException();
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobTitle = JobTitle;
		this.jobYears = jobYears;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description)&&
			Objects.equals(jobTitle, employee.jobTitle)&&
			Objects.equals(jobYears, employee.jobYears);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, description, jobTitle);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id == null || id < 0)
			throw new IllegalArgumentException();
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(!validString(firstName))
			throw new IllegalArgumentException();
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(!validString(lastName))
			throw new IllegalArgumentException();
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(!validString(description))
			throw new IllegalArgumentException();
		this.description = description;
	}

	public String getJobTitle() {return jobTitle;}

	public void setJobTitle(String JobTitle) {
		if(!validString(JobTitle))
			throw new IllegalArgumentException();
		this.jobTitle = JobTitle;}

	public int getJobYears() {return jobYears;}

	public void setJobYears(int jobYears) {
		if(!validJobYears(jobYears))
			throw new IllegalArgumentException();
		this.jobYears = jobYears;}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", jobTitle='" + jobTitle + '\'' +
			", jobYears=" + jobYears +
			'}';
	}

	private boolean validString(String ... strings){
		for(String str : strings){
			if(str == null || str.isEmpty()){
				return false;}}
		return true;}

	private boolean validJobYears(int jobYears){
		if(jobYears < 0)
			return false;
		return true;}
}
// end::code[]
