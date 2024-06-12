/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */


public class Appointment {
    private int appointmentId;
    private String date;
    private String time;
    private Patient patient;
    private Doctor doctor;
    
    //Constructors
    public Appointment(){
        
    }
    
    public Appointment(int appointmentId, String date,String time, Doctor doctor, Patient patient){
        this.appointmentId=appointmentId;
        this.date=date;
        this.time=time;
        this.doctor=doctor;
        this.patient=patient;
    }
    
    //Getters
    public int getAppointmentId(){
        return appointmentId;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getTime(){
        return time;
    }
    
    public Patient getPatient(){
        return patient;
    }
    
    public Doctor getDoctor(){
        return doctor;
    }
    
    //Setters
    public void setAppointmentId(int appointmentId){
        this.appointmentId=appointmentId;
    }
    
    public void setDate(String date){
        this.date=date;
    }
    
    public void setTime(String time){
        this.time=time;
    }
    
    public void setPatient(Patient patient){
        this.patient=patient;
    }
    
    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }
}

