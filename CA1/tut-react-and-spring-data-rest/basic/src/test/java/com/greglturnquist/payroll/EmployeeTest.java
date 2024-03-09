package com.greglturnquist.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmptyConstructor() {
        //Arrange and Act
        Employee employee = new Employee();
        //Assert
        assertNull(employee.getId());
        assertNull(employee.getFirstName());
        assertNull(employee.getLastName());
        assertNull(employee.getDescription());
        assertNull(employee.getJobTitle());}

    @Test
    void testConstructor() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("Manager", employee.getDescription());
        assertEquals("Manager", employee.getJobTitle());}

    @Test
    void testConstructorInvalidFirstNameShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorInvalidLastNameShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "", "Manager", "Manager",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorInvalidDescriptionShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "", "Manager",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorInvalidJobTitleShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Manager", "",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorNullFirstNameShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(null, "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorNullLastNameShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", null, "Manager", "Manager",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorNullDescriptionShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", null, "Manager",1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorNullJobTitleShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Manager", null,1,"jpnmsr@gmail.com"));}


    @Test
    void testConstructorInvalidJobYearsShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Manager", "Manager",-1,"jpnmsr@gmail.com"));}

    @Test
    void testConstructorEmptyEmailShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Manager", "Manager",1,""));}

    @Test
    void testConstructorInvalidEmailShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsrgmail.com"));}

    @Test
    void testConstructorNullEmailShouldThrowException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Manager", "Manager",1,null));}
    @Test
    void testEqualsTrue() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        Employee employee2 = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertTrue(employee.equals(employee2));}

    @Test
    void testEqualsFalseDifferentObject(){
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        String employee2 = "John";
        //Assert
        assertFalse(employee.equals(employee2));}

    @Test
    void testEqualsFalseDifferentFirstName(){
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        Employee employee2 = new Employee("Jane", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertFalse(employee.equals(employee2));}

    @Test
    void testEqualsFalseDifferentLastName(){
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        Employee employee2 = new Employee("John", "Smith", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertFalse(employee.equals(employee2));}

    @Test
    void testEqualsFalseDifferentDescription(){
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        Employee employee2 = new Employee("John", "Doe", "Supervisor", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertFalse(employee.equals(employee2));}

    @Test
    void testEqualsFalseDifferentJobTitle(){
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        Employee employee2 = new Employee("John", "Doe", "Manager", "Supervisor",1,"jpnmsr@gmail.com");
        //Assert
        assertFalse(employee.equals(employee2));}

    @Test
    void testEqualsFalseDifferentJobYears(){
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        Employee employee2 = new Employee("John", "Doe", "Manager", "Manager",2,"jpnmsr@gmail.com");
        //Assert
        assertFalse(employee.equals(employee2));}

    @Test
    void testHashCode() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals(-1279355954, employee.hashCode());}


    @Test
    void getId() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertNull(employee.getId());}


    @Test
    void setId() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        employee.setId(1L);
        //Assert
        assertEquals(1L, employee.getId());}

    @Test
    void setIdInvalidShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setId(-1L));}

    @Test
    void getFirstName() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("John", employee.getFirstName());}

    @Test
    void setFirstName() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        employee.setFirstName("Jane");
        //Assert
        assertEquals("Jane", employee.getFirstName());}

    @Test
    void setFirstNameEmptyStringShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(""));}

    @Test
    void setFirstNameNullShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(null));}

    @Test
    void getLastName() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("Doe", employee.getLastName());}

    @Test
    void setLastName() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        employee.setLastName("Smith");
        //Assert
        assertEquals("Smith", employee.getLastName());}
    @Test
    void setLastNameEmptyStringShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setLastName(""));}

    @Test
    void setLastNameNullShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setLastName(null));}

    @Test
    void getDescription() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("Manager", employee.getDescription());}

    @Test
    void setDescription() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        employee.setDescription("Supervisor");
        //Assert
        assertEquals("Supervisor", employee.getDescription());}

    @Test
    void setDescriptionEmptyStringShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setDescription(""));}

    @Test
    void setDescriptionNullShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setDescription(null));}

    @Test
    void getJobTitle() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("Manager", employee.getJobTitle());}

    @Test
    void setJobTitle() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        employee.setJobTitle("Supervisor");
        //Assert
        assertEquals("Supervisor", employee.getJobTitle());}

    @Test
    void setJobTitleEmptyStringShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setJobTitle(""));}

    @Test
    void setJobTitleNullShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setJobTitle(null));}

    @Test
    void getJobYears() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals(1, employee.getJobYears());}

    @Test
    void setJobYears() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        employee.setJobYears(2);
        //Assert
        assertEquals(2, employee.getJobYears());}

    @Test
    void setJobYearsInvalidShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setJobYears(-1));}

    @Test
    void getEmail() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("jpnmsr@gmail.com", employee.getEmail());}

    @Test
    void setEmail() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");}

    @Test
    void setEmailEmptyStringShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(""));}

    @Test
    void setEmailNullShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(null));}

    @Test
    void setEmailInvalidEmailShouldThrowException() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail("jpnmsr.com"));}

    @Test
    void testToString() {
        //Arrange and Act
        Employee employee = new Employee("John", "Doe", "Manager", "Manager",1,"jpnmsr@gmail.com");
        //Assert
        assertEquals("Employee{id=null, firstName='John', lastName='Doe', description='Manager', jobTitle='Manager', jobYears=1, email='jpnmsr@gmail.com'}", employee.toString());}

}