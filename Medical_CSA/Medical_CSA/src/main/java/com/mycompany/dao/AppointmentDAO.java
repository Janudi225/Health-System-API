/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.Appointment;
import com.mycompany.model.Doctor;
import com.mycompany.model.Patient;
        
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The AppointmentDAO class provides data access operations for managing Appointment
 * objects.
 */
public class AppointmentDAO {
    //Initializing a list to store appointments
    private static final List<Appointment> appointments = new ArrayList<>();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(AppointmentDAO.class.
            getName());
    
    static {
        // Adding initial appointments to the list        
        try {
            appointments.add(new Appointment(1,"2022/01/02", 
                    "12:00",new Doctor(5,"Chemo", "Dr. Saman",
                            "071478569","Chillow"),new Patient(
                                    "Cancer","healthy",8
                                    ,"Saman","025892759","Hatton") ));
            appointments.add(new Appointment(2,"2022/01/02", 
                    "15:00",new Doctor(1,"ENT", "Dr. Rodrigo", 
                            "072367465","Chillow"),new Patient(
                                    "Cancer","healthy",8,
                                    "Saman","025892759","Hatton") ));
            appointments.add(new Appointment(3,"2022/02/10", 
                    "23:30",new Doctor(2,"Oncology", 
                            "Dr. Fernando", "078556987","Chillow"),
                    new Patient("Cancer","healthy",8,
                            "Saman","25892759","Hatton") ));
            logger.log(Level.INFO, "Initial appointments added to the "
                    + "arraylist");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error initializing appointments: {0}",
                    e.getMessage());
        }
    }
    
    
    /**
     * Retrieves all appointments.
     *
     * @return A list of all appointments.
     */
    public List<Appointment> getAllAppointments() {
        try {
            logger.log(Level.INFO, "Fetching all appointments");
            return appointments;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all appointments: {0}",
                    e.getMessage());
            throw new RuntimeException("Error fetching all appointments",
                    e);
        }
    }
    
    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID, or null if not found.
     */
    public Appointment getAppointmentById(int appointmentId) {
        logger.log(Level.INFO, "Fetching appointment with ID: {0}", 
                appointmentId);
        try {
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId() == appointmentId) {
                    return appointment;
                }
            }
            logger.log(Level.WARNING, "Appointment with ID: {0} not found",
                    appointmentId);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching appointment by ID: {0}",
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Adds a new appointment.
     *
     * @param appointment The appointment to add.
     */
    public void addAppointment(Appointment appointment) {
        try {
            int newAppointmentId = getNextAppointmentId();
            appointment.setAppointmentId(newAppointmentId);
            appointments.add(appointment);
            logger.log(Level.INFO, "Added new appointment: {0}", 
                    appointment);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding appointment: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Updates an existing appointment.
     *
     * @param updatedAppointment The appointment with updated information.
     */
    public void updateAppointment(Appointment updatedAppointment) {
        try {
            boolean found = false;
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                // Check for the same appointment ID
                if (appointment.getAppointmentId() == updatedAppointment.
                        getAppointmentId()) {
                    appointments.set(i, updatedAppointment);
                    found = true;
                    logger.log(Level.INFO, "Updated appointment: {0}", 
                            updatedAppointment);
                    break;
                }
            }
            if (!found) {
                logger.log(Level.WARNING, "Appointment with ID: {0} not"
                        + " found for updating", updatedAppointment.getAppointmentId());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating appointment: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    
    /**
     * Deletes an appointment by its ID.
     *
     * @param appointmentId The ID of the appointment to delete.
     */
    public void deleteAppointment(int appointmentId) {
        try {
            boolean removed = appointments.removeIf(appointment -> appointment.
                    getAppointmentId() == appointmentId);
            if (removed) {
                logger.log(Level.INFO, "Deleted appointment with ID: {0}", 
                        appointmentId);
            } else {
                logger.log(Level.WARNING, "Appointment with ID: {0} not"
                        + " found for deletion", appointmentId);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting appointment: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Gets the next available ID for a new appointment.
     *
     * @return The next available ID.
     */
    public int getNextAppointmentId() {
        try {
            int maxAppointmentId = Integer.MIN_VALUE;
            
            // Finding the current mmaximum id
            for (Appointment appointment : appointments) {
                int appointmentId = appointment .getAppointmentId();
                if (appointmentId > maxAppointmentId) {
                    maxAppointmentId = appointmentId;
                }
            }
            
            // Returning the next id value
            int nextAppointmentId = maxAppointmentId + 1;
            logger.log(Level.INFO, "Next available appointment ID: {0}", 
                    nextAppointmentId);
            return nextAppointmentId;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calculating next appointment ID: {0}", 
                    e.getMessage());
            throw e;
        }
    }           
}
