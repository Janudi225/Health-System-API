/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resources;

/**
 *
 * @author Janudi Gunasekara
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.model.Person;
import com.mycompany.dao.PersonDAO;

/**
 * This class provides RESTful endpoints for managing Person resources.
 */
@Path("/Persons")
public class PersonResource {
    
    private final PersonDAO personDAO = new PersonDAO();
    
    // Logger instance for logging information and errors
    private static final Logger logger = Logger.getLogger(PersonResource.
            class.getName());
    
    /**
     * Retrieves all persons.
     *
     * @return A list of all persons.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        try {
            List<Person> persons = personDAO.getAllPersons();
            logger.log(Level.INFO, "Retrieved all persons");
            return Response.ok(persons).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all persons: {0}",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving persons.").build();
        }
    }
    
    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return The person with the specified ID, or a NOT FOUND response if not 
     * found.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) {
        try {
            Person person = personDAO.getPersonById(id);
            if (person != null) {
                logger.log(Level.INFO, "Retrieved person with ID: {0}", 
                        id);
                return Response.ok(person).build();
            } else {
                logger.log(Level.WARNING, "Person with ID: {0} not found",
                        id);
                return Response.status(Response.Status.NOT_FOUND).entity(
                        "Person not found.").build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving person by ID: {0}",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving the person.").build();
        }
    }
    
    /**
     * Adds a new person.
     *
     * @param person The person to add.
     * @return A CREATED response indicating the person was added successfully.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        try {
            personDAO.addPerson(person);
            logger.log(Level.INFO, "Added new person: {0}", person);
            return Response.status(Response.Status.CREATED).entity("Person "
                    + "added successfully.").build();
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error adding person: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    "Invalid person data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding person: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding the person.").build();
        }
    }
    
    /**
     * Updates an existing person.
     *
     * @param id The ID of the person to update.
     * @param updatedPerson The updated person object.
     * @return A CREATED response indicating the person was updated successfully.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        try {
            updatedPerson.setId(id);
            personDAO.updatePerson(updatedPerson);
            logger.log(Level.INFO, "Updated person with ID: {0}", 
                    id);
            return Response.status(Response.Status.CREATED).entity(
                    "Person updated successfully.").build();            
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error updating person: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    "Invalid person data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating person: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while updating the person.").build();
        }
    }
    
    /**
     * Deletes a person by their ID.
     *
     * @param id The ID of the person to delete.
     * @return A NO CONTENT response indicating the person was deleted successfully.
     */
    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            personDAO.deletePerson(id);
            logger.log(Level.INFO, "Deleted person with ID: {0}", 
                    id);
            return Response.status(Response.Status.CREATED).entity
        ("Person deleted successfully.").build();     
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting person: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while deleting the person.").build();
        }
    }
}

