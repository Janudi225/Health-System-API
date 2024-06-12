/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Janudi Gunasekara
 */
public class Person {
    private int id;
    private String name;
    private String phoneNo;
    private String address;
    
    //Constructors
    public Person(){
        
    }
    
    public Person(int id, String name, String phoneNo, String address){
        this.id=id;
        this.name=name;
        this.phoneNo=phoneNo;
        this.address=address;    
    }
    
    
    //Getters
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPhoneNo(){
        return phoneNo;
    }
    
        
    public String  getAddress(){
        return address;
    }
    
    //Setters
    public void setId(int id){
        this.id=id;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setPhoneNo(String phoneNo){
        this.phoneNo=phoneNo;
    }
    
    public void setAddress(String address){
        this.address=address;
    }
    
}
