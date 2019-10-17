package com.example.s325889mappe2;

public class Restaurant {

    String navn, addresse, telefonNr, type;


    public Restaurant(String navn, String addresse, String telefonNr, String type) {
        this.navn = navn;
        this.addresse = addresse;
        this.telefonNr = telefonNr;
        this.type = type;
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
