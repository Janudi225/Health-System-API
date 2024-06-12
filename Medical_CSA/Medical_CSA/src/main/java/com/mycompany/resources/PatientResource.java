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

import com.mycompany.model.Patient;
import com.mycompany.dao.PatientDAO;

/**
 * The PatientResource class provides RESTful endpoints for managing Patient data.
*/
@Path("/Patients")
public class PatientResource {
    
    private final PatientDAO patientDAO = new PatientDAO();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(PatientResource.
            class.getName());

    /**
     * Retrieves all patients.
     *
     * @return A list of all patients in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            logger.log(Level.INFO, "Retrieved all patients");
            return Response.ok(patients).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all patients: {0}",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving patients.").build();
        }
    }
    
    /**
     * Retrieves a patient by their ID.
     *
     * @param patientId The ID of the patient to retrieve.
     * @return The patient with the specified ID in JSON format.
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("patientId") int patientId) {
        try {
            logger.log(Level.INFO, "Fetching patient with ID: {0}", 
                    patientId);
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient == null) {
                logger.log(Level.WARNING, "Patient with ID: {0} not found", 
                        patientId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(patient).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching patient by ID: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving patient by id.")
                    .build();
        }
    }
    
    /**
     * Adds a new patient.
     *
     * @param patient The patient data to add.
     * @return A response indicating the success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            logger.log(Level.INFO, "Adding new patient: {0}", patient);
            patientDAO.addPatient(patient);
            return Response.status(Response.Status.CREATED).entity(
                    "Patient added successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding patient: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding a patient.").build();
        }
    }
    
    /**
     * Updates an existing patient by their ID.
     *
     * @param patientId The ID of the patient to update.
     * @param updatedPatient The updated patient data.
     * @return A response indicating the success or failure of the operation.
     */
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") int patientId, Patient
            updatedPatient) {
        try {
            logger.log(Level.INFO, "Updating patient with ID: {0}", 
                    patientId);
            Patient existingPatient = patientDAO.getPatientById(patientId);
            if (existingPatient == null) {
                logger.log(Level.WARNING, "Patient with ID: {0} not found "
                        + "for updating", patientId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            updatedPatient.setId(patientId);
            patientDAO.updatePatient(updatedPatient);
            return Response.status(Response.Status.CREATED).entity("Patient "
                    + "updated successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating patient: {0}", 
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Deletes a patient by their ID.
     *
     * @param patientId The ID of the patient to delete.
     * @return A response indicating the success or failure of the operation.
     */
    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") int patientId) {
        try {
            logger.log(Level.INFO, "Deleting patient with ID: {0}", 
                    patientId);
            patientDAO.deletePatient(patientId);
            return Response.status(Response.Status.CREATED).entity(
                    "Appointment deleted successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting patient: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while deleting the patient.").build();
        }
    }
}
