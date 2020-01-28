import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

//Janne Kerola, opisknro 2311849
class NaapuritMain{
    //pääohljelma viikkotehtävälle
    private static Scanner sc = new Scanner(System.in);
    public static void main(String Args []){
        //tilapäiset muuttujat olioiden luomista varten
        String field1, field2, field3, field4, field5;
        double area = -1;
        int asukkaat = -1, huoneet = -1, laskuri = 0;

        //tontti pitää luoda alkuun
        System.out.println("Naapurusto!\n");
        System.out.println("Tontin alustus");
        System.out.println("Anna tontin nimi: ");
        field1 = sc.nextLine();
        System.out.println("Anna tontin leveyskoordinaatit: ");
        field2 = sc.nextLine();
        System.out.println("Anna tontin korkeuskoordinaatit: ");
        field3 = sc.nextLine();
        //pinta-alan syötön tarkistus
        //tämän olisi voinut muokata omaksi metodikseen
        //mutta virheilmoitusten yms vuoksi helpompi näin
        while(area < 0){
            try{
                System.out.println("Anna tontin pinta-ala: ");
                area = sc.nextDouble();
            }catch(InputMismatchException e){
                System.out.println("Pinta-alan täytyy olla nollaa suurempi luku!");
                sc.next();
            }
        }
        //kutsutaan tontin constructoria annetuilla tiedoilla
        Tontti tontti = new Tontti(field1, field2, field3, area);
        //nollataan area muuttuja rakennusta varten
        area = -1;
        System.out.println("\nRakennuksen alustus!");
        //sama "metodi"
        while(area < 0){
            try{
                System.out.println("Anna rakennuksen pinta-ala: ");
                area = sc.nextDouble();
                //hienosäätöä, rakennus ei voi olla tonttia suurempi
                //paitsi silloin kun se on kerrostalo
                /*
                if(area > tontti.getAla()){
                    area = -1;
                    System.out.println("Rakennuksen pinta-ala ei voi olla tonttia suurempi!");
                }*/
            }catch(InputMismatchException e){
                System.out.println("Pinta-alan täytyy olla nollaa suurempi luku!");
                sc.next();
            }
        }
        //jälleen
        while(huoneet < 0){
            try{
                System.out.println("Anna huoneistojen lukumäärä: ");
                huoneet = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Huoneita täytyy olla nollaa suurempi lukumäärä!");
                sc.next();
            }
        }//jälleen
        while(asukkaat < 0){
            try{
                System.out.println("Huomio! Joudut luomaan jokaisen asukkaan erikseen!");
                System.out.println("Anna asukkaiden lukumäärä: ");
                asukkaat = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Asukkaita täytyy olla nollaa suurempi luku!");
                sc.next();
            }
        }
        //tyhjennetään merkkipuskurista rivinvaihtomerkki \n
        sc.nextLine();
        //kutsutaan rakennuksen constructoria
        Rakennus rakennus = new Rakennus(huoneet, area, asukkaat);
        tontti.setRakennus(rakennus);
        while (laskuri < asukkaat){
            //while looppi jossa luodaan annettu määrä asukkaita
            System.out.println("Anna "+ (laskuri+1) + ". asukkaan nimi: ");
            field4 = sc.nextLine();
            System.out.println("Anna asukkaan syntymäaika: ");
            field5 = sc.nextLine();
            //luodaan ja liitetään asukas rakennukseen samalla rivillä
            rakennus.setAsukas(new Asukas(field4, field5), laskuri);
            laskuri += 1;
        }
        //kutsutaan ylimmän tason tiedot metodia, joka sisällyttää kaikki tiedot
        //kutsumalla rakennus.tiedot() saisit pelkästään rakennuksen ja asukkaiden tiedot
        //ja rakennus.asukasTiedot() pelkästään asukkaiden tiedot.
        System.out.println(tontti.tiedot());
    }
}