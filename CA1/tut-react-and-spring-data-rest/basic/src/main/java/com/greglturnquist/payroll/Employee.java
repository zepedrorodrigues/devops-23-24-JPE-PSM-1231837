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
	private String JobTitle;

	public Employee() {}

	public Employee(String firstName, String lastName, String description,String JobTitle) {
		if(!validString(firstName, lastName, description, JobTitle))
			throw new IllegalArgumentException("Invalid input for Employee constructor. All fields must be non-null and non-empty.");
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.JobTitle = JobTitle;
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
			Objects.equals(JobTitle, employee.JobTitle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, description,JobTitle);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id == null || id < 0)
			throw new IllegalArgumentException("Invalid input for setId. id must be non-null and non-negative.");
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(!validString(firstName))
			throw new IllegalArgumentException("Invalid input for setFirstName. firstName must be non-null and non-empty.");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(!validString(lastName))
			throw new IllegalArgumentException("Invalid input for setLastName. lastName must be non-null and non-empty.");
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(!validString(description))
			throw new IllegalArgumentException("Invalid input for setDescription. description must be non-null and non-empty.");
		this.description = description;
	}

	public String getJobTitle() {return JobTitle;}

	public void setJobTitle(String JobTitle) {
		if(!validString(JobTitle))
			throw new IllegalArgumentException("Invalid input for setJobTitle. JobTitle must be non-null and non-empty.");
		this.JobTitle = JobTitle;}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", JobTitle='" + JobTitle + '\'' +
			'}';
	}

	private boolean validString(String ... strings){
		for(String str : strings){
			if(str == null || str.isEmpty()){
				return false;}}
		return true;}
}
// end::code[]
