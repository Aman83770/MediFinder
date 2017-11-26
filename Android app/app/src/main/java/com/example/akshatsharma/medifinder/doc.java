package com.example.akshatsharma.medifinder;

/**
 * Created by Dell on 25-11-2015.
 */
public class doc {
    private String name,qualification,specialization,locality,city,fees,timings,no_of_days,email_id;

    public doc(String n,String q,String s,String l,String c,String f,String t,String nO,String e){
        this.setName(n);
        this.setQualification(q);
        this.setSpecialization(s);
        this.setLocality(l);
        this.setCity(c);
        this.setFees(f);
        this.setTimings(t);
        this.setNo_of_days(nO);
        this.setEmail_id(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(String no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
