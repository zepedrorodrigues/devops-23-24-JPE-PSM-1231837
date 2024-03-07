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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component // <1>
public class DatabaseLoader implements CommandLineRunner { // <2>

	private final EmployeeRepository repository;

	@Autowired // <3>
	public DatabaseLoader(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception { // <4>
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer","ring bearer",0,"frodobaggins@gmail.com"));
		this.repository.save(new Employee("Bilbo", "Baggins", "burglar","burglar",100,"bilbobaggins@gmail.com"));}
=======
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer","ring bearer",0));
		this.repository.save(new Employee("Bilbo", "Baggins", "burglar","burglar",100));
=======
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer","ring bearer",0,"frodoBaggins@gmail.com"));
		this.repository.save(new Employee("Bilbo", "Baggins", "burglar","burglar",100,"bilbobaggins@gmail.com"));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer","ring bearer",0));
		this.repository.save(new Employee("Bilbo", "Baggins", "burglar","burglar",100));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
=======
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer","ring bearer",0,"frodobaggins@gmail.com"));
		this.repository.save(new Employee("Bilbo", "Baggins", "burglar","burglar",100,"bilbobaggins@gmail.com"));
>>>>>>> 0c48db2 (Email Field included)
	}
>>>>>>> db4d52f (CA1 First week assignment)
}
// end::code[]
