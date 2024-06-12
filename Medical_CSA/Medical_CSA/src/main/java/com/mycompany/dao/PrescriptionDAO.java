/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.Prescription;
import com.mycompany.model.Patient;
import com.mycompany.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PrescriptionDAO class handles data access operations for the Prescription model.
 */
public class PrescriptionDAO {

    // Logger instance for logging information, warnings, and errors
    private static final Logger logger = Logger.getLogger(PrescriptionDAO.
            class.getName());

    // List to store all prescription records
    private static List<Prescription> prescriptions = new ArrayList<>();

    static {
        // Adding initial appointments to the list        
        try {
            prescriptions.add(new Prescription(new Patient("Cancer",
                    "healthy",8,"Saman","025892759",
                    "Hatton"),new Doctor(5,"Chemo", 
                    "Dr. Saman", "071478569","Chillow"),
                    "sdfefewfq","2 teaspoons","dcwfnwrjgowruigij",
                    5 ));
            prescriptions.add(new Prescription(new Patient("Cancer",
                    "healthy",6,"Perera","025892759",
                    "Hatton"),new Doctor(8,"ENT", "Dr. Kumar",
                            "071478569","Chillow"),"sdfefewfq",
                    "2 teaspoons","dcwfnwrjgowruigij",10 ));
            
            logger.log(Level.INFO, "Initial appointments added to the arraylist");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error initializing appointments: {0}",
                    e.getMessage());
        }
    }
    
    /**
     * Retrieves all prescriptions.
     * @return A list of all prescriptions.
     */
    public List<Prescription> getAllPrescriptions() {
        try {
            logger.log(Level.INFO, "Fetching all prescriptions");
            return prescriptions;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all prescriptions: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching all prescriptions", 
                    e);
        }
    }

    /**
     * Retrieves a prescription by patient ID.
     * @param patientId The ID of the patient.
     * @return The prescription for the specified patient ID, or null if not found.
     */
    public Prescription getPrescriptionByPatient(int patientId) {
        try {
            logger.log(Level.INFO, "Fetching prescription for patient ID: {0}", 
                    new Object[]{patientId});
            for (Prescription prescription : prescriptions) {
                if (prescription.getPatient().getId() == patientId) {
                    return prescription;
                }
            }
            logger.log(Level.WARNING, "Prescription for patient ID: {0} not found",
                    new Object[]{patientId});
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching prescription by patient ID{0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching prescription by patient IDS",
                    e);
        }
    }

    /**
     * Adds a new prescription.
     * @param prescription The prescription to add.
     */
    public void addPrescription(Prescription prescription) {
        try {
            logger.log(Level.INFO, "Adding new prescription: {0}", 
                    prescription);
            prescriptions.add(prescription);
            logger.log(Level.INFO, "Prescription added: {0}", 
                    prescription);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding prescription: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error adding prescription", e);
        }
    }

    /**
     * Updates an existing prescription.
     * @param updatedPrescription The updated prescription.
     */
    public void updatePrescription(Prescription updatedPrescription) {
        try {
            logger.log(Level.INFO, "Updating prescription for patient ID: {0}", 
                    new Object[]{updatedPrescription.getPatient().getId()});
            boolean updated = false;
            for (int i = 0; i < prescriptions.size(); i++) {
                Prescription prescription = prescriptions.get(i);
                // Check for the patient id
                if (prescription.getPatient().getId() == updatedPrescription.
                        getPatient().getId()) {
                    prescriptions.set(i, updatedPrescription);
                    logger.log(Level.INFO, "Prescription updated: {0}", 
                            updatedPrescription);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                logger.log(Level.WARNING, "Prescription for patient ID:"
                        + " {0} not found for updating", new Object[]{updatedPrescription.
                                getPatient().getId()});
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating prescription: {0}",
                    e.getMessage());
            throw new RuntimeException("Error updating prescription", 
                    e);
        }
    }

    /**
     * Deletes a prescription by patient ID.
     * @param patientId The ID of the patient whose prescription should be deleted.
     */
    public void deletePrescriptionByPatient(int patientId) {
        try {
            logger.log(Level.INFO, "Deleting prescription for patient ID: {0}",
                    patientId);
            boolean removed = prescriptions.removeIf(prescription -> prescription.
                    getPatient().getId() == patientId);
            if (removed) {
                logger.log(Level.INFO, "Prescription for patient ID: {0} "
                        + "deleted successfully", patientId);
            } else {
                logger.log(Level.WARNING, "Prescription for patient ID: {0}"
                        + " not found for deletion", patientId);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting prescription: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error deleting prescription", 
                    e);
        }
    }
}
