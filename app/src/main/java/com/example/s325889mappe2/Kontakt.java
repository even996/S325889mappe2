package com.example.s325889mappe2;

public class Kontakt {

    String ID, navn, telefon;

    Kontakt(long ID, String navn, String telefon){
        this.ID = ID;
        this.navn = navn;
        this.telefon = telefon;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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
