package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeTest {
    String validFirstName = "Frodo";
    String validLastName = "Baggins";
    String validDescription = "ring bearer";
    String validJobTitle = "ring bearer";


    @Test
    void TestConstructorValidParametersShouldNotThrowException(){
        assertDoesNotThrow(()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,5));}

    @Test
    void TestConstructorEmptyNameShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee("","Baggins","ring bearer","ring bearer",5));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","","ring bearer","ring bearer",5));
        //Assert
        assertEquals(expectedMessage,exception1.getMessage());
        assertEquals(expectedMessage,exception2.getMessage());}

    @Test
    void TestConstructorNullNameShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(null,"Baggins","ring bearer","ring bearer",5));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo",null,"ring bearer","ring bearer",5));
        //Assert
        assertEquals(expectedMessage,exception1.getMessage());
        assertEquals(expectedMessage,exception2.getMessage());}

    @Test
    void TestConstructorEmptyDescriptionShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","","ring bearer",5));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullDescriptionShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins",null,"ring bearer",5));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorEmptyJobTitleShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer","",5));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullJobTitleShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer",null,5));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNegativeJobYearsShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer","ring bearer",-5));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void testEqualsTrue() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertTrue(result);}

    @Test
    void testEqualsFalseDifferentTypeOfObject() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        boolean result = employee.equals(new Object());
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentFirstName() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee("Bilbo",validLastName,validDescription,validJobTitle,5);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}
    @Test
    void testEqualsFalseSameObjectDifferentLastName() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,"Silva",validDescription,validJobTitle,5);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentDescription() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,"burglar",validJobTitle,5);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentJobTitle() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,"burglar",5);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentJobYears() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,10);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void getIdComesNull() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        Long result = employee.getId();
        //Assert
        assertNull(result);}

    @Test
    void setId() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        employee.setId(1L);
        //Assert
        assertEquals(1L,employee.getId());}

    @Test
    void getFirstName() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        String result = employee.getFirstName();
        //Assert
        assertEquals(validFirstName,result);}

    @Test
    void setFirstName() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        employee.setFirstName("Bilbo");
        //Assert
        assertEquals("Bilbo",employee.getFirstName());}

    @Test
    void getLastName() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        String result = employee.getLastName();
        //Assert
        assertEquals(validLastName,result);}

    @Test
    void setLastName() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        employee.setLastName("Baggins");
        //Assert
        assertEquals("Baggins",employee.getLastName());}

    @Test
    void getDescription() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        String result = employee.getDescription();
        //Assert
        assertEquals(validDescription,result);}

    @Test
    void setDescription() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        employee.setDescription("burglar");
        //Assert
        assertEquals("burglar",employee.getDescription());}

    @Test
    void getJobTitle() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        String result = employee.getJobTitle();
        //Assert
        assertEquals(validJobTitle,result);
    }

    @Test
    void setJobTitle() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        employee.setJobTitle("burglar");
        //Assert
        assertEquals("burglar",employee.getJobTitle());}

    @Test
    void getJobYears() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        int result = employee.getJobYears();
        //Assert
        assertEquals(5,result);}

    @Test
    void setJobYears() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        employee.setJobYears(10);
        //Assert
        assertEquals(10,employee.getJobYears());}

    @Test
    void setJobYearsNegativeShouldThrowException() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> employee.setJobYears(-5));
        //Assert
        assertEquals("Invalid jobYears",exception.getMessage());}

    @Test
    void testToString() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        String result = employee.toString();
        //Assert
        assertEquals("Employee{id=null, firstName='Frodo', lastName='Baggins', description='ring bearer', jobTitle='ring bearer', jobYears='5'}",result);}
    }