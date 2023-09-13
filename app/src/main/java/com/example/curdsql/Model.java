package com.example.curdsql;

public class Model {
    private int id;
    private String firstname;
    private String lastname;
    private String locationh;
    private String ponnername;
    private String ponnerporiman;
    private String advancetkg;
    private String bakitkg;
    //generate constructor


    public Model(int id, String firstname, String lastname, String locationh, String ponnername, String ponnerporiman, String advancetkg, String bakitkg) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.locationh = locationh;
        this.ponnername = ponnername;
        this.ponnerporiman = ponnerporiman;
        this.advancetkg = advancetkg;
        this.bakitkg = bakitkg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocationh() {
        return locationh;
    }

    public void setLocationh(String locationh) {
        this.locationh = locationh;
    }

    public String getPonnername() {
        return ponnername;
    }

    public void setPonnername(String ponnername) {
        this.ponnername = ponnername;
    }

    public String getPonnerporiman() {
        return ponnerporiman;
    }

    public void setPonnerporiman(String ponnerporiman) {
        this.ponnerporiman = ponnerporiman;
    }

    public String getAdvancetkg() {
        return advancetkg;
    }

    public void setAdvancetkg(String advancetkg) {
        this.advancetkg = advancetkg;
    }

    public String getBakitkg() {
        return bakitkg;
    }

    public void setBakitkg(String bakitkg) {
        this.bakitkg = bakitkg;
    }
}