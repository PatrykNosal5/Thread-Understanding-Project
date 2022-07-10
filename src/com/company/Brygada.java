package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.pracujeJako.*;

public class Brygada {
    protected int liczbaMachniecBrygady = 0;
    protected ArrayList<Osoba> pracownicy = new ArrayList<>();
    protected int maxLiczbaPracownikow;
    protected Brygadzista brygadzista = null;

    public Brygada(int maxLiczbaPracownikow){
        this.maxLiczbaPracownikow = maxLiczbaPracownikow;
        //nie musi byc tu brygadzisty, zalezy od punktu widzenia
    }

    public synchronized void zwiekszMachnieciaBrygady(){
        liczbaMachniecBrygady++;
    }

    public void sprawdzPensje(){
        Thread t = new Thread(() ->{
            try {
                Thread.sleep(brygadzista.dlugoscZmiany + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(Osoba e :pracownicy){
                if(e.pracujeJako == KOPACZ){
                    System.out.println(e.pracujeJako + " " + e.imie + " " + e.nazwisko + " zarabia: "+ ((Kopacz) e).pobierzPensje() );
                }
                if(e.pracujeJako == ARCHITEKT){
                    System.out.println(e.pracujeJako + " " + e.imie + " " + e.nazwisko + " zarabia: "+ ((Architekt) e).pobierzPensje() );
                }
            }
            System.out.println(brygadzista.pracujeJako + " " + brygadzista.imie + " " + brygadzista.nazwisko + " zarabia: "+ brygadzista.pobierzPensje());
        });
        t.start();
    }

    public List<Kopacz> listaKopaczy(){
        return pracownicy.stream()
                .filter(o -> o.pracujeJako == KOPACZ) /*|| o.pracujeJako==BRYGADZISTA*///mozna, nie trrzeba
                .map(Kopacz::cast)
                .collect(Collectors.toList());
    }

    public int ileArchitektow(){
        return (int)(pracownicy.stream()
                .filter(o -> o.pracujeJako == ARCHITEKT)
                .count());
    }
    public boolean czyPelnaBrygada(){
        return this.pracownicy.size()>=maxLiczbaPracownikow;
    }

    public void dodajPracownika(Osoba o){
        if(o.imie!=null) {
            if (!czyPelnaBrygada()) {
                this.pracownicy.add(o);
                o.brygada = this;
            }
        }
    }

    public void dodajPracownikow(ArrayList<Osoba> nowiPracownicy){
        if(!czyPelnaBrygada()){
            pracownicy.addAll(nowiPracownicy);
            for(Osoba o : nowiPracownicy){
                o.brygada=this;
            }
        }
    }

    public void setBrygadzista(Brygadzista b){
        if(b.imie!=null) {
        this.brygadzista = b;
        brygadzista.brygada = this;
        }
    }
}

