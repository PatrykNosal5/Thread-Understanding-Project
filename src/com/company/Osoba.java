package com.company;
import java.util.*;

enum pracujeJako {
    KOPACZ,
    ARCHITEKT,
    BRYGADZISTA
}

public abstract class Osoba{
    protected String imie;
    protected String nazwisko;
    protected String pesel;
    protected Integer nrTelefonu;
    protected double waga;
    protected pracujeJako pracujeJako;
    protected Brygada brygada;
    protected static List<String> listaPeseli = new ArrayList<>();
    public Osoba(String imie, String nazwisko, String pesel, Integer nrTelefon, double waga, pracujeJako pracujeJako){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel ;
        this.nrTelefonu = nrTelefon;
        this.waga = waga;
        this.pracujeJako = pracujeJako;
    }
}
