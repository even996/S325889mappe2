package com.example.s325889mappe2;

import java.io.Serializable;

public class Kontakt implements Serializable {

    String navn, telefon;
    Long ID;

    Kontakt(long ID, String navn, String telefon){
        this.ID = ID;
        this.navn = navn;
        this.telefon = telefon;
    }

    Kontakt (String navn, String telefon){
        this.navn = navn;
        this.telefon = telefon;
    }




    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
