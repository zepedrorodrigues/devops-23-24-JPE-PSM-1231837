'use strict';

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
// end::vars[]

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/api/employees'}).done(response => {
			this.setState({employees: response.entity._embedded.employees});
		});
	}

	render() { // <3>
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
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
						<th>Job Title</th>
						<th>Job Years</th>
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
						<th>Email</th>
=======
>>>>>>> db4d52f (CA1 First week assignment)
=======
						<th>Email</th>
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
=======
						<th>Email</th>
>>>>>>> 0c48db2 (Email Field included)
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobTitle}</td>
				<td>{this.props.employee.jobYears}</td>
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
				<td>{this.props.employee.email}</td>
=======
>>>>>>> db4d52f (CA1 First week assignment)
=======
				<td>{this.props.employee.email}</td>
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
=======
				<td>{this.props.employee.email}</td>
>>>>>>> 0c48db2 (Email Field included)
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]
