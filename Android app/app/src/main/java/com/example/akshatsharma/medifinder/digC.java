package com.example.akshatsharma.medifinder;

/**
 * Created by Dell on 24-11-2015.
 */
public class digC {
    private String name,street,locality,region,postalcode,timings,telno,emailid,website;

    public digC(String n,String s,String l,String r, String p,String t,String tn,String e,String w){

        this.setName(n);
        this.setStreet(s);
        this.setLocality(l);
        this.setRegion(r);
        this.setPostalcode(p);
        this.setTimings(t);
        this.setTelno(tn);
        this.setEmailid(e);
        this.setWebsite(w);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
