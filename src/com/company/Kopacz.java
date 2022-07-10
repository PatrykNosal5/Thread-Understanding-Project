package com.company;

public class Kopacz extends Osoba implements IPracownik,Comparable<Kopacz>{
    protected int liczbaMachniecLopata = 0;
    protected boolean czyZdolnyDoPracy = true;
    protected Integer pensja = 4000;
    public Kopacz(String imie, String nazwisko, String pesel, Integer nrTelefonu, double waga) {
        super(imie,nazwisko,pesel,nrTelefonu,waga, com.company.pracujeJako.KOPACZ);
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
        } catch (nieUnikalnyPeselException | cyfryPeseluException dp) {
            dp.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Kopacz(String imie, String nazwisko, String pesel, Integer nrTelefonu, double waga, pracujeJako p) {
        super(imie,nazwisko,pesel,nrTelefonu,waga, com.company.pracujeJako.BRYGADZISTA);
    }


    public synchronized void kop() {
        Thread t = new Thread(() -> {
            int liczbaMachniec = ((int)(Math.random() * 18)) + 5;
            for (int i = 0; i < liczbaMachniec; i++) {//reprezentacja 1 machniecia
                int zlamanalopata = (int) (Math.random() * 100);
                if (zlamanalopata == 50 && czyZdolnyDoPracy) {
                    try {
                        zakonczDzialanie();
                        powiedzIleRazyKopales();
                        throw new zlamanaLopataException();
                    } catch (zlamanaLopataException e) {
                        System.err.println("Lopata byla wadliwa i zlamala sie niespodziewanie w trakcie uzytkowania" + "(" +imie + ")");
                    }
                }
                if(czyZdolnyDoPracy) {
                    System.out.println("Kopacz " + this.imie + " machnal lopata");
                    liczbaMachniecLopata++;
                    brygada.zwiekszMachnieciaBrygady();
                    try {
                        Thread.sleep((int) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (liczbaMachniecLopata >= 15) {
                        try {
                            zakonczDzialanie();
                            powiedzIleRazyKopales();
                            throw new zlamanaLopataException();
                        } catch (zlamanaLopataException e) {
                            zakonczDzialanie();
                            System.err.println("Lopata zuzyla sie i pekla" + "(" +imie + ")");
                        }
                    }
                }
                if(i==liczbaMachniec-1 && liczbaMachniec<15 && zlamanalopata != 50){
                    powiedzIleRazyKopales();
                }
            }
        });
        t.start();
    }
    @Override
    public int pobierzPensje() {
        return (pensja + liczbaMachniecLopata);
    }

    @Override
    public synchronized void powiedzIleRazyKopales() {//te metode stosuje w taki sposob ,ze czekamy na sam koniec programu i wtedy dopiero pokazujemy ile kopal razy oraz dlczego przestał
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kopalem " + liczbaMachniecLopata + " razy" + "(" + this.imie + ")");
        //zeby bylo opoznienie jakies tutaj
    }

    @Override
    public void powiedzCoRobisz() {
        System.out.println("Pracuje jako " + this.pracujeJako);
    }

    @Override
    public synchronized void zakonczDzialanie() {
        czyZdolnyDoPracy=false;
    }

    @Override
    public void dodajSieDoBrygady(Brygada brygada) {
        if(this.imie!=null) {
            brygada.dodajPracownika(this);
        }
    }

    public static Kopacz cast(Osoba osoba) {
        return (Kopacz)osoba;
    }


    @Override
    public int compareTo(Kopacz kopacz) {
        if(this.liczbaMachniecLopata == kopacz.liczbaMachniecLopata) return 0;
        if(this.liczbaMachniecLopata > kopacz.liczbaMachniecLopata) return -1;
        else return 1;
    }
}
