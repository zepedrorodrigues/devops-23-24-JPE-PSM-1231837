package com.greglturnquist.payroll;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.junit.jupiter.api.BeforeEach;
=======
>>>>>>> db4d52f (CA1 First week assignment)
=======
import org.junit.jupiter.api.BeforeEach;
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeTest {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    String nullObject = null;
=======
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
    String validFirstName = "Frodo";
    String emptyString = "";
    String validLastName = "Baggins";
    String validDescription = "ring bearer";
    String validJobTitle = "ring bearer";
<<<<<<< HEAD
    int validJobYears = 5;

    String validEmail = "frodobaggins@gmail.com";
    Employee validEmployee;

    @BeforeEach
    void setUp() {
        validEmployee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
    }
=======
=======
    String emptyString = "";
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
    String validFirstName = "Frodo";
    String validLastName = "Baggins";
    String validDescription = "ring bearer";
    String validJobTitle = "ring bearer";
<<<<<<< HEAD
>>>>>>> db4d52f (CA1 First week assignment)
=======
    int validJobYears = 5;
    String validEmail = "baggins@gmail.com";
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")


    @Test
    void TestConstructorValidParametersShouldNotThrowException(){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        assertDoesNotThrow(()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail));}
=======
        assertDoesNotThrow(()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,5));}
>>>>>>> db4d52f (CA1 First week assignment)
=======
        assertDoesNotThrow(()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail));}
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        assertDoesNotThrow(()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,5));}
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")

    @Test
    void TestConstructorEmptyNameShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(emptyString,validLastName,validDescription,validJobTitle,validJobYears,validEmail));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,emptyString,validDescription,validJobTitle,validJobYears,validEmail));
=======
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee("","Baggins","ring bearer","ring bearer",5));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","","ring bearer","ring bearer",5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(emptyString,validLastName,validDescription,validJobTitle,5,validEmail));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,emptyString,validDescription,validJobTitle,5,validEmail));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee("","Baggins","ring bearer","ring bearer",5));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","","ring bearer","ring bearer",5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception1.getMessage());
        assertEquals(expectedMessage,exception2.getMessage());}

    @Test
    void TestConstructorNullNameShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(null,validLastName,validDescription,validJobTitle,validJobYears,validEmail));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,null,validDescription,validJobTitle,validJobYears,validEmail));
=======
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(null,"Baggins","ring bearer","ring bearer",5));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo",null,"ring bearer","ring bearer",5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(null,validLastName,validDescription,validJobTitle,validJobYears,validEmail));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,null,validDescription,validJobTitle,validJobYears,validEmail));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,()-> new Employee(null,"Baggins","ring bearer","ring bearer",5));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo",null,"ring bearer","ring bearer",5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception1.getMessage());
        assertEquals(expectedMessage,exception2.getMessage());}

    @Test
    void TestConstructorEmptyDescriptionShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,emptyString,validJobTitle,validJobYears,validEmail));
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","","ring bearer",5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,emptyString,validDescription,validJobYears,validEmail));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","","ring bearer",5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullDescriptionShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,nullObject,validJobTitle,validJobYears,validEmail));
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins",null,"ring bearer",5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,null,validDescription,validJobYears,validEmail));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins",null,"ring bearer",5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorEmptyJobTitleShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,emptyString,validJobYears,validEmail));
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer","",5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,emptyString,validJobYears,validEmail));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer","",5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNullJobTitleShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,null,validJobYears,validEmail));
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer",null,5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,null,validJobYears,validEmail));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer",null,5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void TestConstructorNegativeJobYearsShouldThrowException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer","ring bearer",-5));
>>>>>>> db4d52f (CA1 First week assignment)
=======
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
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,null));
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Employee("Frodo","Baggins","ring bearer","ring bearer",-5));
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Assert
        assertEquals(expectedMessage,exception.getMessage());}

    @Test
    void testEqualsTrue() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertTrue(result);}

    @Test
    void testEqualsFalseDifferentTypeOfObject() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee.equals(new Object());
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentFirstName() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        String validFirstName2 = "Bilbo";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName2,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee("Bilbo",validLastName,validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee("Bilbo",validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee("Bilbo",validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}
    @Test
    void testEqualsFalseSameObjectDifferentLastName() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        String validLastName2 = "Silva";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName2,validDescription,validJobTitle,validJobYears,validEmail);
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,"Silva",validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,"Silva",validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,"Silva",validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentDescription() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        String validDescription2 = "burglar";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription2,validJobTitle,validJobYears,validEmail);
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,"burglar",validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,"burglar",validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,"burglar",validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentJobTitle() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        String validJobTitle2 = "burglaro";
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle2,validJobYears,validEmail);
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,"burglar",5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,"burglar",validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,"burglar",5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void testEqualsFalseSameObjectDifferentJobYears() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,10);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        int newJobYears = 10;
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,newJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee1 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        Employee employee2 = new Employee(validFirstName,validLastName,validDescription,validJobTitle,10);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        boolean result = employee1.equals(employee2);
        //Assert
        assertFalse(result);}

    @Test
    void getIdComesNull() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        Long result = employee.getId();
        //Assert
        assertNull(result);}

    @Test
    void setId() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        employee.setId(1L);
        //Assert
        assertEquals(1L,employee.getId());}

    @Test
<<<<<<< HEAD
    void setIdNegativeIdShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setId(-5L));
        //Assert
        assertEquals("Invalid id",exception.getMessage());}

    @Test
    void getFirstName() {
        //Arrange
<<<<<<< HEAD
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
=======
    void getFirstName() {
        //Arrange
<<<<<<< HEAD
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> db4d52f (CA1 First week assignment)
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
        //Act
        String result = employee.getFirstName();
        //Assert
        assertEquals(validFirstName,result);}

    @Test
    void setFirstName() {
<<<<<<< HEAD
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
=======
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
>>>>>>> db4d52f (CA1 First week assignment)
        //Assert
        assertEquals(validLastName,result);}

    @Test
    void setLastName() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
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
>>>>>>> db4d52f (CA1 First week assignment)
        //Assert
        assertEquals(validDescription,result);}

    @Test
    void setDescription() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
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
>>>>>>> db4d52f (CA1 First week assignment)
        //Assert
        assertEquals(validJobTitle,result);
    }

    @Test
    void setJobTitle() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
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
>>>>>>> db4d52f (CA1 First week assignment)
        //Assert
        assertEquals(5,result);}

    @Test
    void setJobYears() {
        //Arrange
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        int newJobYears = 10;
        //Act
        validEmployee.setJobYears(newJobYears);
        //Assert
        assertEquals(newJobYears,validEmployee.getJobYears());}

    @Test
    void setJobYearsNegativeYearsShouldThrowException(){
        //Arrange and Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> validEmployee.setJobYears(-5));
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,validJobYears,validEmail);
>>>>>>> db4a3be (E-mail field added (not yet checked for @ presence))
=======
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
>>>>>>> 95c258c (Revert "E-mail field added (not yet checked for @ presence)")
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
>>>>>>> db4d52f (CA1 First week assignment)
        //Assert
        assertEquals("Invalid jobYears",exception.getMessage());}

    @Test
<<<<<<< HEAD
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
=======
    void testToString() {
        //Arrange
        Employee employee = new Employee(validFirstName,validLastName,validDescription,validJobTitle,5);
        //Act
        String result = employee.toString();
        //Assert
        assertEquals("Employee{id=null, firstName='Frodo', lastName='Baggins', description='ring bearer', jobTitle='ring bearer', jobYears='5'}",result);}
>>>>>>> db4d52f (CA1 First week assignment)
    }