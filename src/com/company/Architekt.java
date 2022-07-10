package com.company;

enum Specjalizacja {//niezdefiniowane specjalizacje, przyznam sie ze nie wiedzialem jakie moga byc specjalizacje architekta na budowie
    spec1,
    spec2,
    spec3
}

public class Architekt extends Osoba implements IPracownik{
    protected Specjalizacja spec;
    protected Integer pensja = 8000;

    public Architekt(String imie, String nazwisko, String pesel, int nrTelefonu, double waga,Specjalizacja spec){
        super(imie,nazwisko,pesel,nrTelefonu,waga, com.company.pracujeJako.ARCHITEKT);
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
                this.spec = spec;
            }
            catch (nieUnikalnyPeselException | cyfryPeseluException dp) {
            dp.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int pobierzPensje() {
        return pensja;
    }

    @Override
    public void powiedzIleRazyKopales() {
        System.out.println("Architek nie dziala!");
    }

    @Override
    public void powiedzCoRobisz() {
        System.out.println("Pracuje jako " +this.pracujeJako);
    }

    @Override
    public void zakonczDzialanie() {
        System.out.println("Architekt nie wykonuje zadnych dzialan!");
    }

    @Override
    public void dodajSieDoBrygady(Brygada brygada) {

        if(this.imie!=null) {
            brygada.dodajPracownika(this);
        }

    }

}
