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

import com.mycompany.model.Doctor;
import com.mycompany.dao.DoctorDAO;

@Path("/Doctors")
public class DoctorResource {
    
    private final DoctorDAO doctorDAO = new DoctorDAO();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(PatientResource.
            class.getName());
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            logger.log(Level.INFO, "Retrieved all doctors");
            return Response.ok(doctors).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all doctors: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving doctors.").build();
        }
    }
    
    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("doctorId") int doctorId) {
        try {
            logger.log(Level.INFO, "Fetching doctor with ID: {0}", 
                    doctorId);
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor == null) {
                logger.log(Level.WARNING, "Doctor with ID: {0} not found", 
                        doctorId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(doctor).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching doctor by ID: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving doctor by id.")
                    .build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            logger.log(Level.INFO, "Adding new doctor: {0}", doctor);
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity("Doctor "
                    + "added successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding doctor: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding a doctor.").build();
        }
    }
    
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor 
            updatedDoctor) {
        try {
            logger.log(Level.INFO, "Updating doctor with ID: {0}", 
                    doctorId);
            Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
            if (existingDoctor == null) {
                logger.log(Level.WARNING, "Doctor with ID: {0} not found"
                        + " for updating", doctorId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            updatedDoctor.setId(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            return Response.status(Response.Status.CREATED).entity("Doctor "
                    + "updated successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating doctor: {0}", 
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") int doctorId) {
        try {
            logger.log(Level.INFO, "Deleting doctor with ID: {0}", 
                    doctorId);
            doctorDAO.deleteDoctor(doctorId);
            return Response.status(Response.Status.CREATED).entity
        ("Doctor deleted successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting doctor: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while deleting the doctor.").build();
        }
    }

}
