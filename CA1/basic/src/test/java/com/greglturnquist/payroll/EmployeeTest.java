package com.greglturnquist.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeTest {
    String nullObject = null;
    String validFirstName = "Frodo";
    String emptyString = "";
    String validLastName = "Baggins";
    String validDescription = "ring bearer";
    String validJobTitle = "ring bearer";
    int validJobYears = 5;

    String validEmail = "frodobaggins@gmail.com";
    Employee validEmployee;

    @BeforeEach
    void setUp() {
        validEmployee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
    }


    @Test
    void TestConstructorValidParametersShouldNotThrowException(){
        assertDoesNotThrow(()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail));}

    @Test
    void TestConstructorEmptyNameShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(emptyString,validLastName,validDescription,validJobTitle,validJobYears,validEmail));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,emptyString,validDescription,validJobTitle,validJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception1.getMessage());
        assertEquals(expectedMessage,exception2.getMessage());}

    @Test
    void TestConstructorNullNameShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(null,validLastName,validDescription,validJobTitle,validJobYears,validEmail));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,null,validDescription,validJobTitle,validJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception1.getMessage());
        assertEquals(expectedMessage,exception2.getMessage());}

    @Test
    void TestConstructorEmptyDescriptionShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,emptyString,validJobTitle,validJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullDescriptionShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,nullObject,validJobTitle,validJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorEmptyJobTitleShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,emptyString,validJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullJobTitleShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,null,validJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNegativeJobYearsShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        int negativeJobYears = -5;
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,negativeJobYears,validEmail));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorEmptyEmailShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,emptyString));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullEmailShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,nullObject));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorEmailWithoutAtSignShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        String invalidEmail = "jpnmsrgmail.com";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,invalidEmail));
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void testEqualsTrue() {
        //Arrange
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertTrue(result);}

    @Test
    void testEqualsFalseDifferentTypeOfObject() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        boolean result = employee.equals(new Object());
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentFirstName() {
        //Arrange
        String validFirstName2 = "Bilbo";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName2,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}
    @Test
    void testEqualsFalseSameObjectDifferentLastName() {
        //Arrange
        String validLastName2 = "Silva";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName2,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentDescription() {
        //Arrange
        String validDescription2 = "burglar";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription2,validJobTitle,validJobYears,validEmail);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentJobTitle() {
        //Arrange
        String validJobTitle2 = "burglaro";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle2,validJobYears,validEmail);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentJobYears() {
        //Arrange
        int validJobYears2 = 10;
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears2,validEmail);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentEmails(){
        //Arrange
        String validEmail2 = "jpnmsr@gmail.com";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail2);
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void getIdComesNull() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        Long result = employee.getId();
        //Assert
        assertNull(result);}

    @Test
    void setId() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        employee.setId(1L);
        //Assert
        assertEquals(1L,employee.getId());}

    @Test
    void setIdNegativeIdShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setId(-5L));
        //Assert
        assertEquals("Invalid id",exception.getMessage());}

    @Test
    void getFirstName() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        //Act
        String result = employee.getFirstName();
        //Assert
        assertEquals(validFirstName,result);}

    @Test
    void setFirstName() {
        //Arrange and Act
        validEmployee.setFirstName("Bilbo");
        //Assert
        assertEquals("Bilbo",validEmployee.getFirstName());}

    @Test
    void setFirstNameEmptyStringShouldThrowException() {
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setFirstName(emptyString));
        //Assert
        assertEquals("Invalid firstName",exception.getMessage());}

    @Test
    void setFirstNameNullStringShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setFirstName(nullObject));
        //Assert
        assertEquals("Invalid firstName",exception.getMessage());}

    @Test
    void getLastName() {
        //Arrange and Act
        String result = validEmployee.getLastName();
        //Assert
        assertEquals(validLastName,result);}

    @Test
    void setLastName() {
        //Arrange
        String validLastName = "Borgins";
        //Act
        validEmployee.setLastName(validLastName);
        //Assert
        assertEquals(validLastName,validEmployee.getLastName());}

    @Test
    void setLastNameEmptyStringShouldThrowException() {
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setLastName(emptyString));
        //Assert
        assertEquals("Invalid lastName",exception.getMessage());}

    @Test
    void setLastNameNullStringShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setLastName(nullObject));
        //Assert
        assertEquals("Invalid lastName",exception.getMessage());}

    @Test
    void getDescription() {
        //Arrange and Act
        String result = validEmployee.getDescription();
        //Assert
        assertEquals(validDescription,result);}

    @Test
    void setDescription() {
        //Arrange
        String validDescription = "burglaro";
        // Act
        validEmployee.setDescription(validDescription);
        //Assert
        assertEquals(validDescription,validEmployee.getDescription());}

    @Test
    void setDescriptionEmptyStringShouldThrowException() {
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setDescription(emptyString));
        //Assert
        assertEquals("Invalid description",exception.getMessage());}

    @Test
    void setDescriptionNullStringShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setDescription(nullObject));
        //Assert
        assertEquals("Invalid description",exception.getMessage());}

    @Test
    void getJobTitle() {
        //Arrange and Act
        String result = validEmployee.getJobTitle();
        //Assert
        assertEquals(validJobTitle,result);
    }

    @Test
    void setJobTitle() {
        //Arrange
        String validJobTitle2 = "burglarito";
        //Act
        validEmployee.setJobTitle(validJobTitle2);
        //Assert
        assertEquals(validJobTitle2,validEmployee.getJobTitle());}

    @Test
    void setJobTitleEmptyStringShouldThrowException() {
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setJobTitle(emptyString));
        //Assert
        assertEquals("Invalid jobTitle",exception.getMessage());}

    @Test
    void setJobTitleNullStringShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setJobTitle(nullObject));
        //Assert
        assertEquals("Invalid jobTitle",exception.getMessage());}

    @Test
    void getJobYears() {
        //Arrange and Act
        int result = validEmployee.getJobYears();
        //Assert
        assertEquals(5,result);}

    @Test
    void setJobYears() {
        //Arrange
        int newJobYears = 10;
        //Act
        validEmployee.setJobYears(newJobYears);
        //Assert
        assertEquals(newJobYears,validEmployee.getJobYears());}

    @Test
    void setJobYearsNegativeYearsShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setJobYears(-5));
        //Assert
        assertEquals("Invalid jobYears",exception.getMessage());}

    @Test
    void getEmailTestValid(){
        //Arrange and Act
        String result = validEmployee.getEmail();
        //Assert
        assertEquals(validEmail,result);}

    @Test
    void setEmailTestValid(){
        //Arrange
        String validEmail2 = "jpnmsr@gmail.com";
        //Act
        validEmployee.setEmail(validEmail2);
        //Assert
        assertEquals(validEmail2,validEmployee.getEmail());}

    @Test
    void setEmailTestEmptyStringShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setEmail(emptyString));
        //Assert
        assertEquals("Invalid email",exception.getMessage());}

    @Test
    void setEmailTestNullStringShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setEmail(nullObject));
        //Assert
        assertEquals("Invalid email",exception.getMessage());}

    @Test
    void setEmailTestDoesNotContainAtShouldThrowException(){
        //Arrange
        String invalidEmail = "jpnmsrgmail.com";
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setEmail(invalidEmail));
        //Assert
        assertEquals("Invalid email",exception.getMessage());}

    @Test
    void testToString() {
        //Arrange and Act
        String expected = "Employee{id=null, firstName='Frodo', lastName='Baggins', description='ring bearer', jobTitle='ring bearer', jobYears='5', email='frodobaggins@gmail.com'}";
        String result = validEmployee.toString();
        //Assert
        assertEquals(expected,result);}
    }