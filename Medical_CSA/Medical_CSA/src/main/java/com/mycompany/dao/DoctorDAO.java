/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DoctorDAO class handles data access operations for the Doctor model.
 */
public class DoctorDAO {
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(DoctorDAO.class.
            getName());
    
    //Initializing a list to store doctors
    private static final List<Doctor> doctors = new ArrayList<>();
    
    static {
        // Adding initial doctors to the list   
        doctors.add(new Doctor(1, "Cardiology", "Dr. Smith", 
                "0123456789", "Colombo"));
        doctors.add(new Doctor(2, "Neurology", "Dr. Jones", 
                "987654321", "Kandy"));
        doctors.add(new Doctor(3, "Pediatrics", "Dr. Brown", 
                "555555555", "Gampaha"));
    }
    
    /**
     * Gets all doctors.
     * @return A list of all doctors.
     */
    public List<Doctor> getAllDoctors() {
        try {
            logger.log(Level.INFO, "Fetching all doctors");
            return doctors;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all doctors: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching all doctors", e);
        }
    }
    
    /**
     * Gets a doctor by their ID.
     * @param id The ID of the doctor to retrieve.
     * @return The doctor with the specified ID, or null if not found.
     */
    public Doctor getDoctorById(int id) {
        try {
            logger.log(Level.INFO, "Fetching doctor with ID: {0}", 
                    id);
            for (Doctor doctor : doctors) {
                if (doctor.getId() == id) {
                    return doctor;
                }
            }
            logger.log(Level.WARNING, "Doctor with ID: {0} not found", 
                    id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching doctor by ID: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching doctor by ID", 
                    e);
        }
    }
    
    /**
     * Adds a new doctor.
     * @param doctor The doctor data to add.
     */
    public void addDoctor(Doctor doctor) {
        try {
            logger.log(Level.INFO, "Adding new doctor: {0}", doctor);
            int newId = getNextId();
            doctor.setId(newId);
            doctors.add(doctor);
            logger.log(Level.INFO, "Doctor added with ID: {0}", 
                    newId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding doctor: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error adding doctor", e);
        }
    }
    
    /**
     * Updates an existing doctor.
     * @param updatedDoctor The updated doctor data.
     */
    public void updateDoctor(Doctor updatedDoctor) {
        try {
            logger.log(Level.INFO, "Updating doctor with ID: {0}", 
                    updatedDoctor.getId());
            boolean updated = false;
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                if (doctor.getId() == updatedDoctor.getId()) {
                    doctors.set(i, updatedDoctor);
                    logger.log(Level.INFO, "Doctor updated: {0}", 
                            updatedDoctor);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                logger.log(Level.WARNING, "Doctor with ID: {0} not found "
                        + "for updating", updatedDoctor.getId());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating doctor: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error updating doctor", e);
        }
    }
    
    /**
     * Deletes a doctor by ID.
     * @param id The ID of the doctor to delete.
     */
    public void deleteDoctor(int id) {
        try {
            logger.log(Level.INFO, "Deleting doctor with ID: {0}", 
                    id);
            boolean removed = doctors.removeIf(doctor -> doctor.getId() == id);
            if (removed) {
                logger.log(Level.INFO, "Doctor with ID: {0} deleted successfully",
                        id);
            } else {
                logger.log(Level.WARNING, "Doctor with ID: {0} not found for deletion", 
                        id);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting doctor: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error deleting doctor", e);
        }
    }
    
    /**
     * Gets the next available ID for a new doctor.
     * @return The next available ID.
     */
    private int getNextId() {
        try {
            logger.log(Level.INFO, "Calculating next doctor ID");
            int maxId = Integer.MIN_VALUE;
            // Find the current maximum ID
            for (Doctor doctor : doctors) {
                int id = doctor.getId();
                if (id > maxId) {
                    maxId = id;
                }
            }
            return maxId + 1;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calculating next ID: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error calculating next ID", e);
        }
    }
}

