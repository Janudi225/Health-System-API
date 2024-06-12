/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */
public class Patient extends Person {
    private String medicalHistory;
    private String healthStatus;
    
    //constructors
    public Patient(){
        
    }
    
    public Patient(String medicalHistory,String healthStatus,int id, String name, String phoneNo, String address){
        super(id, name, phoneNo, address);
        this.medicalHistory=medicalHistory;
        this.healthStatus=healthStatus;
    }
    
    //getters
    public String getMedicalHistory(){
        return medicalHistory;
    }
    
    public String getHealthStatus(){
        return healthStatus;
    }
    
    //setters
    public void setMedicalHistory(String medicalHistory){
        this.medicalHistory=medicalHistory;
    }
    
    public void setHealthStatus(String healthStatus){
        this.healthStatus=healthStatus;
    }
}
