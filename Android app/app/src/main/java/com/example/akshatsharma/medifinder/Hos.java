package com.example.akshatsharma.medifinder;

/**
 * Created by Dell on 02-12-2015.
 */
public class Hos {
    private String name,city,address,contact;

    public Hos(String n,String c,String a,String co){
        this.setName(n);
        this.setCity(c);
        this.setAddress(a);
        this.setContact(co);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
