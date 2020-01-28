// Tekijä Janne Kerola,  opisknro. 2311849

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class PankkitiliMain{
    private static Scanner sc = new Scanner(System.in);

    public static double getApprovedNumber(double user_saldo, double saldotest){
        //metodi joka tarkistaa että syöte on sopivaa tilanteeseen.
        while (user_saldo == 0){
            System.out.println("Anna summa: ");
            try{
                saldotest = sc.nextDouble();
                if (saldotest >= 0){
                    user_saldo = saldotest;
                    saldotest = 0;
                } else {
                    //estetään negatiivisen saldon syöttö jo ennen pankkitilin luontia
                    System.out.println("Summan täytyy olla positiivinen!\n");
                    }
                } catch (InputMismatchException e){
                    //Virheen nappaus, kun käyttäjä yrittää syöttää tekstiä luvun sijasta
                    System.out.println("Summan täytyy olla luku!\n");
                    sc.next();
                }
        }
        return user_saldo;
    }

    public static void main(String [] args){
        //Pankkitilin muodostajan muuttujien alustus
        String user_name, user_account;
        double user_saldo, saldo, saldotest;
        int choice;
        boolean loop = true;

        //Käyttäjän nimi voi sisältää myös numeroita
        System.out.println("Pankkitiliohjelma!");
        System.out.println("Anna käyttäjän nimi: ");
        user_name = sc.nextLine();

        //Käyttäjän nimi ja tilinumero eivät ole muodon kannalta tärkeitä
        //Tilinumero voi tässä tapauksessa sisältää myös kirjaimia
        System.out.println("Anna tilinumero: ");
        user_account = sc.nextLine();
        //turhaa toistoa, mutta selkeyttää lukua
        saldo = 0;
        saldotest = -1;
        user_saldo = getApprovedNumber(saldo, saldotest);
        System.out.println();

        //luodaan pankkitili-olio
        Pankkitili user = new Pankkitili(user_name, user_account, user_saldo);
        System.out.println("Tilin tiedot");
        System.out.println("Käyttäjä: " + user.getOwner());
        System.out.println("Tilinumero: " + user.getAccount());
        System.out.println("Tilin saldo: " + user.getSaldo() + " €");
        System.out.println();
        
        //Tilinhallintavalinnat
        System.out.println("1. Nosto");
        System.out.println("2. Talletus");
        System.out.println("Poistu millä tahansa muulla komennolla.");
        //Virheen nappaus nextInt() takia.
        try {
            choice = sc.nextInt();
            if (choice == 1){
                System.out.println("Nostotapahtuma");
                //while looppi kunnes saadaan käypä summa
                while (true){
                    user_saldo = getApprovedNumber(saldo, saldotest);
                    //metodi tarkistaa tilin saldon, palauttaa false jos tilin kate ei riitä.
                    if (user.checkSaldo(user_saldo)){
                        // talletusmetodi rikkoo loopin
                        break;
                    } else {
                        System.out.println("Tilillä ei ole tarpeeksi katetta!\n");
                    }
                }
            } else if (choice == 2){
                System.out.println("Talletustapahtuma");
                while (true) {
                    //while loop kunnes käypä summa, talletettaessa katetta ei tarvitse tarkistaa
                    user_saldo = getApprovedNumber(saldo, saldotest);
                    user.deposit(user_saldo);
                    break;
                }
            } else {
                System.out.println("Hyvästi!");
            }
        } catch (InputMismatchException f){
            //virheen nappaus nextInt() takia
            System.out.println("Hyvästi!");
        }
        //lopuksi tulostetaan käyttäjän tilitiedot
        System.out.println();
        System.out.println("Tilin tiedot");
        System.out.println("Käyttäjä: " + user.getOwner());
        System.out.println("Tilinumero: " + user.getAccount());
        System.out.println("Tilin saldo: " + user.getSaldo() + " €");
        System.out.println();
    }
}