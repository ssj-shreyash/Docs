package com.example.shrey.docs;

import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

public class Specimen {

    public String Name;
    public int Contact;
    public String address;


    public Specimen(String name, int contact, String address) {
        Name = name;
        Contact = contact;
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public int getContact() {
        return Contact;
    }

    public String getAddress() {
        return address;
    }
}



