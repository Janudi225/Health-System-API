/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */
public class Billing {
    private Patient patient;
    private int invoiceNumber;
    private double amount;
    private double outstanding;
    
    //constructors
    public Billing()
    {
        
    }    
    public Billing(Patient patient, int invoiceNumber, double amount, double outstanding){
        this.patient= patient;
        this.invoiceNumber=invoiceNumber;
        this.amount=amount;
        this.outstanding=outstanding;        
    }
    
    //getters
    public Patient getPatient(){
        return patient;
    }
    
    public int getInvoiceNumber(){
        return invoiceNumber;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public double getOutstanding(){
        return outstanding;
    }
    
    //setters
    public void setPatient(Patient patient){
        this.patient=patient;
    }
    
    public void setInvoiceNumber(int invoiceNumber){
        this.invoiceNumber=invoiceNumber;
    }
    
    public void setAmount(double amount){
        this.amount=amount;
    }
    
    public void setOutstanding(double outstanding){
        this.outstanding=outstanding;
    }
        
}
