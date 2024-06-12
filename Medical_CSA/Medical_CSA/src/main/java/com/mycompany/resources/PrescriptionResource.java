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

import com.mycompany.model.Prescription;
import com.mycompany.dao.PrescriptionDAO;

/**
 * This class provides RESTful endpoints for managing Prescription resources. 
 */
@Path("/Prescriptions")
public class PrescriptionResource {
    
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(AppointmentResource.
            class.getName());
    
    /**
     * Retrieves all prescriptions.
     *
     * @return A list of all prescriptions.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
            logger.log(Level.INFO, "Retrieved all prescriptions");
            return Response.ok(prescriptions).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all prescriptions: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity
        ("An error occurred while retrieving prescriptions.").build();
        }
    }
    
    /**
     * Retrieves a prescription by its patient ID.
     *
     * @param patientId The patient ID of the prescription to retrieve.
     * @return The prescription with the specified patient ID, or a NOT FOUND 
     * response if not found.
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionByPatient(@PathParam("patientId") int patientId) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionByPatient
        (patientId);
            if (prescription != null) {
                logger.log(Level.INFO, "Retrieved prescription with"
                        + " patient ID: {0}", patientId);
                return Response.ok(prescription).build();
            } else {
                logger.log(Level.WARNING, "Prescription with patientID: {0}"
                        + " not found", patientId);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Prescription not found.").build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving prescription by"
                    + " patient ID: {0}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving the prescription.").
                    build();
        }
    }
    
    /**
     * Adds a new prescription.
     *
     * @param prescription The prescription to add.
     * @return A CREATED response indicating the prescription was added
     * successfully.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            logger.log(Level.INFO, "Added new prescription: {0}", 
                    prescription);
            return Response.status(Response.Status.CREATED).entity
        ("Prescription added successfully.").build();
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error adding prescription: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid prescription data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding prescription: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding the prescription.").
                    build();
        }
    }
    
    /**
     * Updates an existing prescription.
     *
     * @param patientId The patient ID of the prescription to update.
     * @param updatedPrescription The updated prescription object.
     * @return A CREATED response indicating the appointment was updated 
     * successfully.
     */
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("patientId") int patientId, 
            Prescription updatedPrescription) {
        try {
            Prescription existingPrescription = prescriptionDAO.
                    getPrescriptionByPatient(patientId);

            if (existingPrescription != null) {
                updatedPrescription.getPatient().setId(patientId);
                prescriptionDAO.updatePrescription(updatedPrescription);
                logger.log(Level.INFO, "Updated prescription with patient ID: {0}", 
                        patientId);
                return Response.status(Response.Status.CREATED).entity
        ("Appointment updated successfully.").build();
            } else {
                logger.log(Level.WARNING, "Prescription with patient ID: {0} "
                        + "not found for updating", patientId);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Prescription not found.").build();
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error updating prescription: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid prescription data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating prescription: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while updating the prescription.").
                    build();
        }
    }
    
    /**
     * Deletes a prescription by its patient ID.
     *
     * @param patientId The patient ID of the prescription to delete.
     * @return A CREATED response indicating the prescription was deleted successfully.
     */
    @DELETE
    @Path("/{patientId}")
    public Response deletePrescription(@PathParam("patientId") int patientId) {
        try {
            prescriptionDAO.deletePrescriptionByPatient(patientId);
            logger.log(Level.INFO, "Deleted prescription with patient ID: {0}", 
                    patientId);
            return Response.status(Response.Status.CREATED).entity
        ("Prescription deleted successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting prescription: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while deleting the prescription.").
                    build();
        }
    }
}
