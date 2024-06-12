/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */
public class MedicalRecord {
    private Patient patient;
    private String diagnoses;
    private String treatments;
 
    //Constructors
    public MedicalRecord(){
        
    }
    
    public MedicalRecord(Patient patient, String diagnoses, String treatments){
        this.patient=patient;
        this.diagnoses=diagnoses;
        this.treatments=treatments;
    }
    
    //Getters
    public Patient getPatient(){
        return patient;
    }
    
    public String getDiagnoses(){
        return diagnoses;
    }
    public String getTreatments(){
        return treatments;
    }    
    
    //Setters
    public void setPatient(Patient patient){
        this.patient=patient;
    }
    
    public void setDiagnoses(String diagnoses){
        this.diagnoses=diagnoses;
    }
    
    public void setTreatments(String treatments){
        this.treatments=treatments;
    }
}

