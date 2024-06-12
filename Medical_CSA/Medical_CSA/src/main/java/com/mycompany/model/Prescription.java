/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */
public class Prescription {
    private Patient patient;
    private Doctor doctor;
    private String medication;
    private String dose;
    private String instructions;
    private int duration;
    
    //Constructors
    public Prescription(){
        
    }
    
    public Prescription(Patient patient, Doctor doctor, String medication, 
            String dose, String instructions, int duration){
        this.patient=patient;
        this.doctor=doctor;
        this.medication=medication;
        this.dose=dose;
        this.instructions=instructions;
        this.duration=duration;     
    }
    
    //Getters
    public Patient getPatient(){
        return patient;
    }
    
    public Doctor getDoctor(){
        return doctor;
    }
     
    public String getMedication(){
        return medication;
    }
    
    public String dose(){
        return dose;
    }
    
    public String getInstructions(){
        return instructions;
    }
    
    public int getDuration(){
        return duration;
    }
    
    //Setters
    public void setPatient(Patient patient){
        this.patient=patient;
    }
    
    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }
    
    public void setMedication(String medication){
        this.medication=medication;
    }
    
    public void setDose(String dose){
        this.dose=dose;
    }
    
    public void setInstructions(String instructions){
        this.instructions=instructions;
    }
    
    public void setDuration(int duration){
        this.duration=duration;
    }
}
