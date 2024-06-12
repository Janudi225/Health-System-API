/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author Janudi Gunasekara
 */

import com.mycompany.model.Billing;
import com.mycompany.model.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BillingDAO class handles data access operations for the Billing model.
 */
public class BillingDAO {

    // Initializing the logger
    private static final Logger logger = Logger.getLogger(BillingDAO.class.
            getName());
    
    //Initializing a list to store billings
    private static final List<Billing> billings = new ArrayList<>();
  
    static {
        // Adding initial billings to the list   
        billings.add(new Billing(new Patient("Leukamia",
                "critical",8,"Pushpa","071896545",
                "Badulla"),74861,50000,7500));
        billings.add(new Billing(new Patient("Deabetes",
                "ill",5,"Vinuth","07878655",
                "Jaffna"),7981,750000,15000));
        billings.add(new Billing(new Patient("Common cold",
                "mild",3,"Dasan","0775443545",
                "Kaluthara"),74861,20000,1500));
    }
    
    /**
     * Gets all billing records.
     * @return A list of all billing records.
     */
    public List<Billing> getAllBillings() {
        try {
            logger.log(Level.INFO, "Fetching all billing records");
            return billings;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all billings: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching all billings", 
                    e);
        }
    }

    /**
     * Gets a billing record by invoice number.
     * @param invoiceNumber The invoice number to retrieve.
     * @return The billing record with the specified invoice number, or null if 
     * not found.
     */
    public Billing getBillingByInvoiceNumber(int invoiceNumber) {
        try {
            logger.log(Level.INFO, "Fetching billing record with invoice number: {0}", 
                    invoiceNumber);
            for (Billing billing : billings) {
                if (billing.getInvoiceNumber()==invoiceNumber) {
                    return billing;
                }
            }
            logger.log(Level.WARNING, "Billing record with invoice number: {0} not found", 
                    invoiceNumber);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching billing record by invoice number: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error fetching billing record by invoice number", 
                    e);
        }
    }

    /**
     * Adds a new billing record.
     * @param billing The billing record to add.
     */
    public void addBilling(Billing billing) {
        try {
            logger.log(Level.INFO, "Adding new billing record: {0}", 
                    billing);
            billings.add(billing);
            logger.log(Level.INFO, "Billing record added: {0}", 
                    billing);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding billing record: {0}", 
                    e.getMessage());
            throw new RuntimeException("Error adding billing record", 
                    e);
        }
    }

    /**
     * Updates an existing billing record.
     * @param updatedBilling The updated billing record.
     */
    public void updateBilling(Billing updatedBilling) {
        try {
            boolean found = false;
            for (int i = 0; i < billings.size(); i++) {
                Billing billing = billings.get(i);
                // Check for the same invoice number
                if (billing.getInvoiceNumber() == updatedBilling.getInvoiceNumber()) {
                    billings.set(i, updatedBilling);
                    found = true;
                    logger.log(Level.INFO, "Updated billing: {0}", 
                            updatedBilling);
                    break;
                }
            }
            if (!found) {
                logger.log(Level.WARNING, "Billing with invoice number: "
                        + "{0} not found for updating", updatedBilling.getInvoiceNumber());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating billing: {0}", 
                    e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a billing record by invoice number.
     * @param invoiceNumber The invoice number of the billing record to delete.
     */
    public void deleteBillingByInvoiceNumber(int invoiceNumber) {
        try {
            boolean removed = billings.removeIf(billing -> billing.getInvoiceNumber
        ()==invoiceNumber);
            if (removed) {
                logger.log(Level.INFO, "Deleted billing with invoice number: "
                        + "{0}", invoiceNumber);
            } else {
                logger.log(Level.WARNING, "Billing with invoice number: {0}"
                        + " not found for deletion", invoiceNumber);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting billing: {0}", 
                    e.getMessage());
            throw e;
        }
    }
}