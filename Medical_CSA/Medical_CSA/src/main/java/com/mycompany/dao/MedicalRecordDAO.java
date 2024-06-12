/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.MedicalRecord;
import com.mycompany.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MedicalRecordDAO class handles data access operations for the MedicalRecord model.
 */
public class MedicalRecordDAO {

     // Initializing the logger
    private static final Logger logger = Logger.getLogger(MedicalRecordDAO.
            class.getName());
    
    //Initializing a list to store appointments
    private static final List<MedicalRecord> medicalRecords = new ArrayList<>();

    static {
        // Adding initial medical records to the list        
        try {
            medicalRecords.add(new MedicalRecord((new Patient("Liver "
                    + "Failure","ill",59,"Johnson","078569745",
                    "London")),"Critically ill with liver failure", 
                    "Blood tranfusions"));
            medicalRecords.add(new MedicalRecord((new Patient("Liver"
                    + " Failure","ill",5,"Peter","078569745",
                    "Scotland")),"Critically ill with liver failure", 
                    "Blood tranfusions"));
            medicalRecords.add(new MedicalRecord((new Patient("Liver "
                    + "Failure","ill",17,"Jack","078569745",
                    "New York")),"Critically ill with liver failure", 
                    "Blood tranfusions"));
            logger.log(Level.INFO, "Initial appointments added to the arraylist");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error initializing appointments: {0}", 
                    e.getMessage());
        }
    }
    
    /**
     * Retrieves all medical records.
     * 
     * @return A list of all medical records.
     */
    public List<MedicalRecord> getAllMedicalRecords() {
        try {
            logger.log(Level.INFO, "Fetching all medical records");
            return medicalRecords;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all medical records: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching all medical records", 
                    e);
        }
    }

    /**
     * Retrieves a single medical record by patient ID.
     * 
     * @param patientId The ID of the patient.
     * @return The medical record for the specified patient ID, or null if not found.
     */
    public MedicalRecord getMedicalRecordByPatientId(int patientId) {
        try {
            logger.log(Level.INFO, "Fetching medical record for patient ID: {0}", 
                    patientId);
            for (MedicalRecord record : medicalRecords) {
                if (record.getPatient().getId() == patientId) {
                    return record;
                }
            }
            logger.log(Level.WARNING, "Medical record for patient ID: {0} not found", 
                    patientId);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching medical record by patient ID: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching medical record by patient ID", 
                    e);
        }
    }

    /**
     * Adds a new medical record.
     * 
     * @param record The medical record to add.
     */
    public void addMedicalRecord(MedicalRecord record) {
        try {
            logger.log(Level.INFO, "Adding new medical record: {0}",
                    record);
            medicalRecords.add(record);
            logger.log(Level.INFO, "Medical record added: {0}", 
                    record);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding medical record: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error adding medical record", 
                    e);
        }
    }

    /**
     * Updates an existing medical record.
     * @param updatedRecord The updated medical record.
     */
    public void updateMedicalRecord(MedicalRecord updatedRecord) {
        try {
            logger.log(Level.INFO, "Updating medical record for patient ID: {0}",
                    updatedRecord.getPatient().getId());
            boolean found = false;
            for (int i = 0; i < medicalRecords.size(); i++) {
                MedicalRecord record = medicalRecords.get(i);
                if (record.getPatient().getId() == updatedRecord.getPatient().
                        getId()) {
                    medicalRecords.set(i, updatedRecord);
                    found=true;
                    logger.log(Level.INFO, "Updated medical record: {0}", 
                            updatedRecord);
                    break;
                }
            }
            if (!found) {
                logger.log(Level.WARNING, "Medical record for patient ID: {0} "
                        + "not found for updating", updatedRecord.getPatient().
                                getId());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating medical record: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error updating medical record", e);
        }
    }

    /**
     * Deletes a medical record by patient ID.
     * @param patientId The ID of the patient whose medical record should be deleted.
     */
    public void deleteMedicalRecordByPatientId(int patientId) {
        try {
            logger.log(Level.INFO, "Deleting medical record for patient ID: {0}",
                    patientId);
            boolean removed = medicalRecords.removeIf(record -> record.getPatient().
                    getId() == patientId);
            if (removed) {
                logger.log(Level.INFO, "Medical record for patient ID: {0} "
                        + "deleted successfully", patientId);
            } else {
                logger.log(Level.WARNING, "Medical record for patient ID: "
                        + "{0} not found for deletion", patientId);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting medical record: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error deleting medical record", 
                    e);
        }
    }
}
