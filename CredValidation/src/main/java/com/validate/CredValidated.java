package com.validate;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name= "validate") 

public class CredValidated {
	@Id @GeneratedValue //(strategy = GenerationType.IDENTITY) 
    @Column(name = "ID")

    private long ID;
    @Column(name = "name")
    private String name;
    
    @Column(name = "passcode")
    private String passcode;

    
    public CredValidated() {
        
    }
    
    public CredValidated(long id, String name, String passcode) {
            this.ID = id;
            this.name = name;
            this.passcode = passcode;
    }
    

    public long getID() {return this.ID; }
    public String getName() { return this.name;}
    public String getPasscode() { return this.passcode;}
    
    public void setID(long id) { this.ID = id;}
    public void setName(String name) { this.name = name;}
    public void setPasscode(String passcode) { this.passcode = passcode;}
}
