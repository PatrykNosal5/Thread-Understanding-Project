package com.company;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Kopacz kopacz1 = new Kopacz("Jan","Kowalczyk","30000000000"/*0*/,394444745,98);//nie 11 cyfr
        Kopacz kopacz2 = new Kopacz("Pawel","Kowalski","31000000000",397422436,95);
        Kopacz kopacz3 = new Kopacz("Michal","Kiszewski","32000000000",397422436,95);
        Brygadzista b1 = new Brygadzista("Maciej","Nowak", "33000000000",975623259,110, "Nowy", 14000);
        Architekt architekt2 = new Architekt("Alojzy","Bak","34000000000",311111111,90,Specjalizacja.spec1);
        Brygada brygada = new Brygada(5);


        //KOD DO ODKOMENTOWANIA W CELU SPRAWDZENIA

        /*ArrayList<Osoba> nowiPracownicy = new ArrayList<>();
        Kopacz k1 = new Kopacz("Jan","Kowalczyk","30000000001",394444745,98);
        Kopacz k2 = new Kopacz("Pawel","Kowalski","31000000002",397422436,95);
        Kopacz k3 = new Kopacz("Michal","Kiszewski","32000000003",397422436,95);
        Architekt architekt1 = new Architekt("Adolf","Kudlinski","34000000004",300000000,90,Specjalizacja.spec2);
        nowiPracownicy.add(k1);
        nowiPracownicy.add(k2);
        nowiPracownicy.add(k3);
        nowiPracownicy.add(architekt1);
        brygada.dodajPracownikow(nowiPracownicy);*/


        brygada.dodajPracownika(kopacz2);
        brygada.dodajPracownika(kopacz1);
        kopacz3.dodajSieDoBrygady(brygada);
        architekt2.dodajSieDoBrygady(brygada);
        brygada.setBrygadzista(b1);

        //KOD DO ODKOMENTOWANIA W CELU SPRAWDZENIA
        //b1.dodajSieDoBrygady(brygada);

        architekt2.powiedzIleRazyKopales();

        System.out.println();

        System.out.println("Jest " + brygada.ileArchitektow()+ " architekt(ow) w brygadzie");
        if(brygada.czyPelnaBrygada()){
            System.out.println("Brygada jest pelna.");
        }else{
            System.out.println("Brygada nie jest pelna.");
        }

        System.out.println();

        architekt2.powiedzCoRobisz();
        architekt2.zakonczDzialanie();
        b1.powiedzCoRobisz();
        kopacz1.powiedzCoRobisz();
        kopacz2.powiedzCoRobisz();
        kopacz3.powiedzCoRobisz();

        System.out.println();

        b1.sprawdzCzyBrygadaNiezdolnaDoPracy();


        kopacz1.kop();
        kopacz2.kop();
        kopacz3.kop();

        //KOD DO ODKOMENTOWANIA W CELU SPRAWDZENIA
        //kopacz1.zakonczDzialanie();

        brygada.sprawdzPensje();


        //KOD DO ODKOMENTOWANIA W CELU SPRAWDZENIA

        /*architekt2.pobierzPensje();
        architekt2.dodajSieDoBrygady(brygada);
        kopacz1.pobierzPensje();
        kopacz1.powiedzIleRazyKopales();
        kopacz1.zakonczDzialanie();
        b1.pobierzPensje();
        b1.powiedzIleRazyKopales();
        b1.zakonczDzialanie();*/

    }
}
