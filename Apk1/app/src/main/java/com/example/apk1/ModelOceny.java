package com.example.apk1;

public class ModelOceny {
    private String nazwa;
    private int ocena;

    public ModelOceny(String nazwa, int ocena) {
        this.nazwa = nazwa;
        this.ocena = ocena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
