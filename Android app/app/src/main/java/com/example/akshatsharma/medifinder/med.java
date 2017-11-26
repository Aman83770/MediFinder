package com.example.akshatsharma.medifinder;

/**
 * Created by Dell on 25-11-2015.
 */
public class med {
    String name,telno,street,locality,region,postalcode;

    public med(String n,String t,String s,String l,String r,String p){
        this.setName(n);
        this.setTelno(t);
        this.setStreet(s);
        this.setLocality(l);
        this.setRegion(r);
        this.setPostalcode(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
}
