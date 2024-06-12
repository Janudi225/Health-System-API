package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.Person;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PersonDAO class provides data access operations for managing Person objects.
 */
public class PersonDAO {
    //Initializing a list to store persons
    private static final List<Person> persons = new ArrayList<>();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(PersonDAO.class.
            getName());
    
    static {
        // Adding initial persons to the list
        try {
            persons.add(new Person(1, "Jane", "0702447598", 
                    "Kandy"));
            persons.add(new Person(2, "Mary", "0458562747", 
                    "Jaffna"));
            persons.add(new Person(3, "Sam", "007745745", 
                    "Matale"));
            logger.log(Level.INFO, "Initial persons added to the arraylist");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error initializing persons: {0}", 
                    e.getMessage());
        }
    }
    
    /**
     * Returns a list of all persons.
     *
     * @return A list of all persons.
     */
    public List<Person> getAllPersons() {
        logger.log(Level.INFO, "Fetching all persons");
        return persons;
    }
    
    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return The person with the specified ID, or null if not found.
     */
    public Person getPersonById(int id) {
        logger.log(Level.INFO, "Fetching person with ID: {0}", id);
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        logger.log(Level.WARNING, "Person with ID: {0} not found", 
                id);
        return null;
    }
    
    /**
     * Adds a new person to the list.
     *
     * @param person The person to add.
     */
    public void addPerson(Person person) {
        try {
            int newId = getNextId();
            person.setId(newId);
            persons.add(person);
            logger.log(Level.INFO, "Person added: {0}", person);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error adding person: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Updates an existing person in the list.
     *
     * @param updatedPerson The person with updated information.
     */
    public void updatePerson(Person updatedPerson) {
        try {
            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                if (person.getId() == updatedPerson.getId()) {
                    persons.set(i, updatedPerson);
                    logger.log(Level.INFO, "Person updated: {0}", updatedPerson);
                    return;
                }
            }
            logger.log(Level.WARNING, "Person with ID: {0} not found for updating", updatedPerson.getId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating person: {0}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * Deletes a person by their ID.
     *
     * @param id The ID of the person to delete.
     */
    public void deletePerson(int id) {
        try {
            boolean removed = persons.removeIf(person -> person.getId() == id);
            if (removed) {
                logger.log(Level.INFO, "Person deleted with ID: {0}", id);
            } else {
                logger.log(Level.WARNING, "Person with ID: {0} not found for deletion", id);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting person: {0}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * Gets the next available ID for a new person.
     *
     * @return The next available ID.
     */
    public int getNextId() {
        try {
            int maxId = Integer.MIN_VALUE;
            
            // Finding the current mmaximum id
            for (Person person : persons) {
                int id = person.getId();
                if (id > maxId) {
                    maxId = id;
                }
            }
            
            // Returning the next id value
            int nextId = maxId + 1;
            logger.log(Level.INFO, "Next available ID: {0}", nextId);
            return nextId;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calculating next ID: {0}", e.getMessage());
            throw e;
        }
    }
}
