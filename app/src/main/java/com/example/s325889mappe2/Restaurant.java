package com.example.s325889mappe2;

public class Restaurant {

    String navn, addresse, telefonNr, type;
    Long ID;


    public Restaurant(Long ID, String navn, String addresse, String telefonNr, String type) {
        this.navn = navn;
        this.addresse = addresse;
        this.telefonNr = telefonNr;
        this.type = type;
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
