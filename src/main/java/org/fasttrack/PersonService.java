package org.fasttrack;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private List<Person> persons;
    private int idFree;

    public PersonService() {
        this.persons = new ArrayList<>();
        this.idFree = 1;
    }

    public Person addPerson (Person person) throws BadInputException {
        if (person.getName().length() == 0)
            throw new BadInputException("PersonService.addPerson: Name is null.");
        if (person.getAge() < 0 || person.getAge() > 120)
            throw new BadInputException("PersonService.addPerson: Age is not valid.");

        person.setId(this.idFree);
        this.idFree++;
        this.persons.add(person);

        return person;
    }

    public Person removePerson (int id){
        for (Person current : this.persons){
            if (current.getId() == id){
                this.persons.remove(current);
                return current;
            }
        }
        throw new IdNotFoundException("PersonService.removePerson: Invalid ID.");
    }

    public List<Person> getAllPersons (){
        return this.persons;
    }

    public List<Person> getPersonsOlderThan (int age) throws BadInputException {
        if (age >= 120 || age < 0){
            throw new BadInputException("PersonService.getPersonsOlderThan: Invalid age.");
        }
        List <Person> personsOlderThan = new ArrayList<>();
        for (Person current : this.persons){
            if (current.getAge() > age){
                personsOlderThan.add(current);
            }
        }
        return personsOlderThan;
    }

    public List<String> getAllPersonName() {
        List<String> allPersonName = new ArrayList<>();
        for (Person current : this.persons){
            allPersonName.add(current.getName());
        }
        return allPersonName;
    }

    public Person getPerson (String name) throws PersonNotFoundException, BadInputException {
        if (name.length() == 0){
            throw new BadInputException("PersonService.getPerson: Name is empty.");
        }

        for (Person currentPerson : this.persons){
            if (currentPerson.getName().equals(name)){
                return currentPerson;
            }
        }
        throw new PersonNotFoundException("PersonService.getPerson: Person not found.");
    }

    public Person getPersonById (int id) throws BadInputException, PersonNotFoundException {
        if (id < 0){
            throw new BadInputException("PersonService.getPersonById: Id invalid.");
        }
        for (Person currentPerson : this.persons){
            if (currentPerson.getId() == id){
                return currentPerson;
            }
        }
        throw new PersonNotFoundException("PersonService.getPersonById: Person not found.");
    }
}
