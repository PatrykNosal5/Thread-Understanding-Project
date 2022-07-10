package com.company;
import java.util.Collections;
import java.util.List;

public class Brygadzista extends Kopacz{
    protected String pseudonim;
    protected int dlugoscZmiany;
    protected Integer pensja = 7000;
    protected boolean state = true;//pozwala na latwe przerwanie watku
    public Brygadzista(String imie, String nazwisko, String pesel, Integer nrTelefonu, double waga,String pseudonim,int dlugoscZmiany) {
        super(imie,nazwisko,pesel,nrTelefonu,waga, com.company.pracujeJako.BRYGADZISTA);
        //domyslam sie ze dlugosc zmiany nalezy do indywidualnej dl zmiany brygadzisty, a nie brygady, bo jest to jego pole
        try {
            if (listaPeseli.contains(pesel)) {
                this.imie=null;
                throw new nieUnikalnyPeselException();
            }
            if(pesel.length()!=11){
                throw new cyfryPeseluException();
            }
            else {
                listaPeseli.add(pesel);
            }
            this.dlugoscZmiany = dlugoscZmiany;
            this.pseudonim = pseudonim;
        } catch (nieUnikalnyPeselException | cyfryPeseluException dp) {
            dp.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public synchronized void sprawdzCzyBrygadaNiezdolnaDoPracy(){
        List<Kopacz> listaKopaczy = brygada.listaKopaczy();
        Thread t = new Thread(() ->{
            Integer zdolniDoPracy = 0;
            Boolean czyZdolni = false;
            Kopacz kopacz;
            try {
                Thread.sleep(dlugoscZmiany);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < listaKopaczy.size(); i++) {
                if(state){
                    //dla kazdego elementu z nowoej listy tworzymy obiekt i przypisujemy obiekt z listy
                    kopacz = listaKopaczy.get(i);
                    if (kopacz.czyZdolnyDoPracy) {
                        //sprawdzamy czy kopacz moze kopac, jesli tak to brygda moze pracowac
                        czyZdolni=true;
                        zdolniDoPracy++;
                    }

                }
                }

        if (!czyZdolni) {
            try {
                throw new BrygadaNiezdolnaDoPracy();
            } catch (BrygadaNiezdolnaDoPracy bndp) {
                bndp.printStackTrace();
            }
        }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BRYGADA MA " + zdolniDoPracy +"/"+listaKopaczy.size() + " CZLONKOW BRYGADY ZDOLNYCH DO PRACY.");
            if(brygada.liczbaMachniecBrygady>=listaKopaczy.size()*12){// ile srednio wykopia + lekka nadwyzka
                this.pensja+=30;
                for(Kopacz k : listaKopaczy) {
                    k.pensja+=10;
                }
                System.out.println("Brygada osiagnela dzisiaj swietne wyniki, dzienna premia dla pracownikow to 10zl ,a dla brygadzisty 30!!!");
            }//zwiekszamy pensje za prace brygady ponad norme
            System.out.println("BRYGADA WYKONALA DZISIAJ " + brygada.liczbaMachniecBrygady + " MACHNIEC LOPATA.");
            Collections.sort(listaKopaczy);//uzywamy tego zeby nie zmienial sie rozmiar listy, przy zwyklej liscie kopaczy byly problemy , bo elementy byly z niej dynamicznie usuwane, bo byla ona aktualizowana, jako size = liczba kopaczy
            if(listaKopaczy.get(0).liczbaMachniecLopata>12) {//warunek dla dodatku to minimum 12 machniec
                listaKopaczy.get(0).pensja += 50;//zwiekszamy o 50 pensje najlepszego kopacza
                System.out.println("Pracownikiem dnia został: " + listaKopaczy.get(0).imie + " " + listaKopaczy.get(0).nazwisko + " z " + listaKopaczy.get(0).liczbaMachniecLopata + " machnieciami lopata. Otrzymuje on 50zl premii!!!");
            }
            });
        t.start();


    }
    @Override
    public void powiedzCoRobisz() {
        System.out.println("Pracuje jako " +this.pracujeJako);
    }
    @Override
    public int pobierzPensje() {
        return pensja;
    }
    @Override
    public void zakonczDzialanie() {
        czyZdolnyDoPracy=false;
        this.state = false;
    }
    @Override
    public void dodajSieDoBrygady(Brygada brygada) {
        if(this.imie!=null) {
            brygada.dodajPracownika(this);
            brygada.setBrygadzista(this);
        }
    }

}
