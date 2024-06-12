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

import com.mycompany.model.Appointment;
import com.mycompany.dao.AppointmentDAO;

/**
 * This class provides RESTful endpoints for managing Appointment resources. 
 */
@Path("/Appointments")
public class AppointmentResource {
    
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(AppointmentResource.
            class.getName());
    
    /**
     * Retrieves all appointments.
     *
     * @return A list of all appointments.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            logger.log(Level.INFO, "Retrieved all appointments");
            return Response.ok(appointments).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all appointments: {0}",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving appointments.").
                    build();
        }
    }
    
    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID, or a NOT FOUND response if 
     * not found.
     */
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                logger.log(Level.INFO, "Retrieved appointment with ID: {0}", 
                        appointmentId);
                return Response.ok(appointment).build();
            } else {
                logger.log(Level.WARNING, "Appointment with ID: {0} not found", 
                        appointmentId);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Appointment not found.").build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving appointment by ID: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity
        ("An error occurred while retrieving the appointment.").build();
        }
    }
    
    /**
     * Adds a new appointment.
     *
     * @param appointment The appointment to add.
     * @return A CREATED response indicating the appointment was added successfully.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            logger.log(Level.INFO, "Added new appointment: {0}", 
                    appointment);
            return Response.status(Response.Status.CREATED).entity
        ("Appointment added successfully.").build();
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error adding appointment: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid appointment data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding appointment: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding the appointment.").
                    build();
        }
    }
    
    /**
     * Updates an existing appointment.
     *
     * @param appointmentId The ID of the appointment to update.
     * @param updatedAppointment The updated appointment object.
     * @return A NO CONTENT response indicating the appointment was updated 
     * successfully.
     */
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId,
            Appointment updatedAppointment) {
        try {
            Appointment existingAppointment = appointmentDAO.getAppointmentById
        (appointmentId);

            if (existingAppointment != null) {
                updatedAppointment.setAppointmentId(appointmentId);
                appointmentDAO.updateAppointment(updatedAppointment);
                logger.log(Level.INFO, "Updated appointment with ID: {0}",
                        appointmentId);
                return Response.status(Response.Status.CREATED).entity
        ("Appointment updated successfully.").build();
            } else {
                logger.log(Level.WARNING, "Appointment with ID: {0} not "
                        + "found for updating", appointmentId);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Appointment not found.").build();
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error updating appointment: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid appointment data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating appointment: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity
        ("An error occurred while updating the appointment.").build();
        }
    }
    
    /**
     * Deletes an appointment by its ID.
     *
     * @param appointmentId The ID of the appointment to delete.
     * @return A NO CONTENT response indicating the appointment was deleted 
     * successfully.
     */
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            logger.log(Level.INFO, "Deleted appointment with ID: {0}", 
                    appointmentId);
            return Response.status(Response.Status.CREATED).entity
        ("Appointment deleted successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting appointment: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while deleting the appointment.").
                    build();
        }
    }
}
