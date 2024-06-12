/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resources;

/**
 * @author Janudi Gunasekara
 */
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.model.Billing;
import com.mycompany.dao.BillingDAO;

@Path("/Billings")
public class BillingResource {
    
    private BillingDAO billingDAO=new BillingDAO();
    
    // Initializing the logger
    private static final Logger logger = Logger.getLogger(PatientResource.
            class.getName());
    
    /**
     * Retrieves all billings.
     *
     * @return A list of all billings in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            logger.log(Level.INFO, "Retrieved all billings");
            return Response.ok(billings).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching all billings: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while retrieving billings.").build();
        }
    }
      
    /**
     * Retrieves a billing by their invoice number.
     *
     * @param invoiceNumber The invoice number of the billing to retrieve.
     * @return The billing with the specified invoice number in JSON format.
     */
    @GET
    @Path("/{invoiceNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingByInvoiceNummber(@PathParam("invoiceNumber") int 
            invoiceNumber) {
        try {
            logger.log(Level.INFO, "Fetching billing with invoice number: {0}", 
                    invoiceNumber);
            Billing billing = billingDAO.getBillingByInvoiceNumber(invoiceNumber);
            if (billing == null) {
                logger.log(Level.WARNING, "Billing with invoice number: "
                        + "{0} not found", invoiceNumber);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(billing).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching billing by invoice "
                    + "number: {0}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity
        ("An error occurred while retrieving billing by invoice number.").build();
        }
    }
   
    /**
     * Adds a new billing.
     *
     * @param billing The billing data to add.
     * @return A response indicating the success or failure of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            logger.log(Level.INFO, "Adding new billing: {0}", billing);
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity("Billing "
                    + "added successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding billing: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while adding a billing.").build();
        }
    }
    
    /**
     * Updates an existing billing by their invoice number.
     *
     * @param invoiceNumber The invoice number of the billing to update.
     * @param updatedBilling The updated billing data.
     * @return A response indicating the success or failure of the operation.
     */
    @PUT
    @Path("/{invoiceNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("invoiceNumber") int invoiceNumber, 
            Billing updatedBilling) {
        try {
            Billing existingBilling = billingDAO.getBillingByInvoiceNumber(invoiceNumber);

            if (existingBilling != null) {
                updatedBilling.setInvoiceNumber(invoiceNumber);
                billingDAO.updateBilling(updatedBilling);
                logger.log(Level.INFO, "Updated billing with invoice number: "
                        + "{0}", invoiceNumber);
                return Response.status(Response.Status.CREATED).entity
        ("Billing updated successfully.").build();
            } else {
                logger.log(Level.WARNING, "Billing with invoice number:"
                        + " {0} not found for updating", invoiceNumber);
                return Response.status(Response.Status.NOT_FOUND).entity
        ("Billing not found.").build();
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error updating billing: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity
        ("Invalid billing data.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating billing: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("An error occurred while updating the billing.").build();
        }
    }
    
    /**
     * Deletes a billing by their invoice number.
     *
     * @param invoiceNumber The invoice number of the billing to delete.
     * @return A response indicating the success or failure of the operation.
     */
    @DELETE
    @Path("/{invoiceNumber}")
    public Response deleteBilling(@PathParam("invoiceNumber") int invoiceNumber) {
        try {
            billingDAO.deleteBillingByInvoiceNumber(invoiceNumber);
            logger.log(Level.INFO, "Deleted billing with invoice number: {0}", 
                    invoiceNumber);
            return Response.status(Response.Status.CREATED).entity
        ("Billing deleted successfully.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting billing: {0}", 
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity
        ("An error occurred while deleting the billing.").build();
        }
    }
    
    
}
