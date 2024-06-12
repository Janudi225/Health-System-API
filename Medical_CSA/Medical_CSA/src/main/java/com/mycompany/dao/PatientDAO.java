/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PatientDAO class provides data access operations for managing Patient objects.
 * It includes comprehensive logging and exception handling for better maintainability.
 */
public class PatientDAO {
    
    // Initializing a list to store patients
    private static final List<Patient> patients = new ArrayList<>();
    
    // Initializing a logger
    private static final Logger logger = Logger.getLogger(PatientDAO.class.getName());
      
    static {
        // Adding initial patients to the list
        patients.add(new Patient("cancer", "ill", 1, 
                "Jame", "07154572502", "Kandy"));
        patients.add(new Patient("diabetes", "ill", 2, 
                "Wame", "0561126444", "Gampaha"));
        patients.add(new Patient("cough", "ill", 5, 
                "Dave", "07745214", "Kandy"));
    }
    
    /**
     * Retrieves all patients.
     *
     * @return A list of all patients.
     */
    public List<Patient> getAllPatients() {
        logger.log(Level.INFO, "Fetching all patients");
        return patients;
    }
    
    /**
     * Retrieves a patient by their ID.
     *
     * @param id The ID of the patient to retrieve.
     * @return The patient with the specified ID, or null if not found.
     */
    public Patient getPatientById(int id) {
        logger.log(Level.INFO, "Fetching patient with ID: {0}", id);
        try {
            for (Patient patient : patients) {
                if (patient.getId() == id) {
                    return patient;
                }
            }
            logger.log(Level.WARNING, "Patient with ID: {0} not found", 
                    id);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching patient by ID: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Adds a new patient.
     *
     * @param patient The patient to add.
     */
    public void addPatient(Patient patient) {
        try {
            // Check if the patient already exists by ID
            if (getPatientById(patient.getId()) != null) {
                logger.log(Level.WARNING, "Patient with ID: {0} already "
                        + "exists", patient.getId());
                return;
            }
            patients.add(patient);
            logger.log(Level.INFO, "Added new patient: {0}", patient);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding patient: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Updates an existing patient.
     *
     * @param updatedPatient The patient with updated information.
     */
    public void updatePatient(Patient updatedPatient) {
        try {
            boolean found = false;
            for (int i = 0; i < patients.size(); i++) {
                Patient patient = patients.get(i);
                if (patient.getId() == updatedPatient.getId()) {
                    patients.set(i, updatedPatient);
                    logger.log(Level.INFO, "Updated patient: {0}", 
                            updatedPatient);
                    found = true;
                    break;
                }
            }
            if (!found) {
                logger.log(Level.WARNING, "Patient with ID: {0} not found for updating", 
                        updatedPatient.getId());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating patient: {0}", 
                    e.getMessage());
            throw e;
        }
    }
    
    /**
     * Deletes a patient by their ID.
     *
     * @param id The ID of the patient to delete.
     */
    public void deletePatient(int id) {
        try {
            boolean removed = patients.removeIf(patient -> patient.getId() == id);
            if (removed) {
                logger.log(Level.INFO, "Deleted patient with ID: {0}", 
                        id);
            } else {
                logger.log(Level.WARNING, "Patient with ID: {0} not found for deletion",
                        id);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting patient: {0}", 
                    e.getMessage());
            throw e;
        }
    }
}
