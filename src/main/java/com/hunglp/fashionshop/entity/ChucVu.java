package com.hunglp.fashionshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="chucvu")
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machucvu")
    private int machucvu;
    private String tenchucvu;
    
    public ChucVu() {
    	
    }
    
    public ChucVu(int machucvu, String tenchucvu) {
    	this.machucvu = machucvu;
    	this.tenchucvu = tenchucvu;
    }
    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }
}