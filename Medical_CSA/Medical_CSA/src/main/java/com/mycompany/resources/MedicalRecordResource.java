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

import com.mycompany.model.MedicalRecord;
import com.mycompany.dao.MedicalRecordDAO;
import com.mycompany.model.Patient;

/**
 * This class provides RESTful endpoints for managing medical record resources. 
 */
@Path("/MedicalRecords")
public class MedicalRecordResource {
    
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(AppointmentResource.
            class.getName());
    
    /**
     * Retrieves all medical records.
     *
     * @return A list of all medical records.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.
                    getAllMedicalRecords();
            logger.log(Level.INFO, "Retrieved all medical records");
            return Response.ok(medicalRecords).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all medical records: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity
        ("An error occurred while retrieving medical records.").build();
        }
    }
    
    /**
     * Retrieves a medical record by person ID.
     *
     * @param personID The ID of the person whose medical record to retrieve.
     * @return The medical record with the specified ID, or a NOT FOUND response
     * if not found.
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordByPatientId(@PathParam("patientId") int patientId) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordByPatientId(
                    patientId);
            if (medicalRecord != null) {
                logger.log(Level.INFO, "Retrieved medical record with patient"
                        + " ID: {0}", patientId);
                return Response.ok(medicalRecord).build();
            } else {
                logger.log(Level.WARNING, "Medical record with patient ID: "
                        + "{0} not found", patientId);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Medical record not found.").build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving medical record by "
                    + "patient ID: {0}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving the medical record.").
                    build();
        }
    }
    
    /**
     * Adds a new  medical record.
     *
     * @param medicalRecord The medical record to add.
     * @return A CREATED response indicating the medical record was added successfully.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            logger.log(Level.INFO, "Added new medical record: {0}",
                    medicalRecord);
            return Response.status(Response.Status.CREATED).entity
        ("Medical record added successfully.").build();
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error adding medical record: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid medical record data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding medical record: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding the medical record.").
                    build();
        }
    }
    
    /**
     * Updates an existing medical record.
     *
     * @param patientId The patient ID of the medical record to update.
     * @param updatedMedicalRecord The updated appointment object.
     * @return A NO CONTENT response indicating the appointment was updated 
     * successfully.
     */
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("patientId") int patientId, 
            MedicalRecord updatedMedicalRecord) {
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.
                    getMedicalRecordByPatientId(patientId);

            if (existingMedicalRecord != null) {
                updatedMedicalRecord.getPatient().setId(patientId);
                medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);
                logger.log(Level.INFO, "Updated medical record with patient "
                        + "ID: {0}", patientId);
                return Response.status(Response.Status.CREATED).entity
        ("Medical record updated successfully.").build();
            } else {
                logger.log(Level.WARNING, "MedicalRecord with patient ID:"
                        + " {0} not found for updating", patientId);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Medical record not found.").build();
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error updating medical record: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid medical record data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating medical record: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while updating the medical record.").
                    build();
        }
    }
    
    /**
     * Deletes medical record by its patient ID.
     *
     * @param patientId The patient ID of the medical record to delete.
     * @return A NO CONTENT response indicating the medical record was deleted 
     * successfully.
     */
    @DELETE
    @Path("/{patientId}")
    public Response deleteMedicalRecord(@PathParam("patientId") int patientId) {
        try {
            medicalRecordDAO.deleteMedicalRecordByPatientId(patientId);
            logger.log(Level.INFO, "Deleted medical record with patient ID: "
                    + "{0}", patientId);
            return Response.status(Response.Status.CREATED).entity
        ("Medical record deleted successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting medical record: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while deleting the medical record.").
                    build();
        }
    }
}
