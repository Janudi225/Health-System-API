/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */
public class Doctor extends Person{
    private String speciality; 
    
    //constructors
    public Doctor(){
        
    }
    
    public Doctor(int id, String speciality,String name, String phoneNo,String address){
        super(id, name, phoneNo, address);
        this.speciality=speciality;
    }
    
    //getters
    public String getSpeciality(){
        return speciality;
    }
    
    //setters
    public void setSpeciality(String speciality){
        this.speciality=speciality;
    }
}
