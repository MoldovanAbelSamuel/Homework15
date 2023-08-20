package org.fasttrack;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonServiceTest {
    private static PersonService service;

    @BeforeAll
    public static void setup(){
        service = new PersonService();
    }

    @Test
    @Order(1)
    public void addPersonTest() throws BadInputException {
        Person person1 = new Person("George", 22);
        Person person2 = new Person("Alin", 100);
        Person person3 = new Person("Marian", 54);
        Person person4 = new Person("Ion", 83);
        Person person5 = new Person("Marius", 15);
        Person person6 = new Person("", 15);
        Person person7 = new Person("Vasile", -5);
        Person person8 = new Person("Dan", 135);
        Assertions.assertEquals(1, service.addPerson(person1).getId());
        Assertions.assertEquals(2, service.addPerson(person2).getId());
        Assertions.assertEquals(3, service.addPerson(person3).getId());
        Assertions.assertEquals(4, service.addPerson(person4).getId());
        Assertions.assertEquals(5, service.addPerson(person5).getId());
        Assertions.assertEquals(5, service.getAllPersons().size());
        try {
            service.addPerson(person6);
        } catch (BadInputException exception) {
            Assertions.assertEquals("PersonService.addPerson: Name is null.", exception.getMessage());
        }
        try {
            service.addPerson(person7);
        } catch (BadInputException exception) {
            Assertions.assertEquals("PersonService.addPerson: Age is not valid.", exception.getMessage());
        }
        try {
            service.addPerson(person8);
        } catch (BadInputException exception) {
            Assertions.assertEquals("PersonService.addPerson: Age is not valid.", exception.getMessage());
        }
        Assertions.assertEquals(5, service.getAllPersons().size());
    }

    @Test
    @Order(2)
    public void removePersonTest(){
        Assertions.assertEquals(5, service.getAllPersons().size());
        service.removePerson(4);
        Assertions.assertEquals(4, service.getAllPersons().size());
        try {
            service.removePerson(4);
        } catch (IdNotFoundException exception){
            Assertions.assertEquals("PersonService.removePerson: Invalid ID.", exception.getMessage());
        }
        Assertions.assertEquals(4, service.getAllPersons().size());
    }

    @Test
    @Order(3)
    public void getAllPersonsTest(){
        Assertions.assertEquals(4, service.getAllPersons().size());
    }

    @Test
    @Order(4)
    public void getPersonsOlderThanTest() throws BadInputException {
        Assertions.assertEquals(1, service.getPersonsOlderThan(65).size());
        try {
            service.getPersonsOlderThan(121);
        } catch (BadInputException exception){
            Assertions.assertEquals("PersonService.getPersonsOlderThan: Invalid age.", exception.getMessage());
        }
        try {
            service.getPersonsOlderThan(-55);
        } catch (BadInputException exception){
            Assertions.assertEquals("PersonService.getPersonsOlderThan: Invalid age.", exception.getMessage());
        }
    }

    @Test
    @Order(5)
    public void getAllPersonNamesTest(){
        Assertions.assertEquals(4, service.getAllPersonName().size());
    }

    @Test
    @Order(6)
    public void getPersonTest () throws BadInputException, PersonNotFoundException {
        Person person1 = new Person(1, "George", 22);
        Person returnPerson = service.getPerson("George");
        Assertions.assertEquals(person1, returnPerson);
        try {
            service.getPerson("");
        } catch (BadInputException exception){
            Assertions.assertEquals("PersonService.getPerson: Name is empty.", exception.getMessage());
        }

        try {
            service.getPerson("Alina");
        } catch (PersonNotFoundException exception){
            Assertions.assertEquals("PersonService.getPerson: Person not found.", exception.getMessage());
        }
    }

    @Test
    @Order(7)
    public void getPersonByIdTest() throws BadInputException, PersonNotFoundException {
        Assertions.assertEquals("Marian", service.getPersonById(3).getName());
        try {
            service.getPersonById(-3);
        } catch (BadInputException exception){
            Assertions.assertEquals("PersonService.getPersonById: Id invalid.", exception.getMessage());
        }
        try {
            service.getPersonById(9);
        } catch (PersonNotFoundException exception){
            Assertions.assertEquals("PersonService.getPersonById: Person not found.", exception.getMessage());
        }

    }
}
