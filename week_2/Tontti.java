class Tontti{
    //Jäsenmuuttujat
    private String nimi;
    private String sijainti_x;
    private String sijainti_y;
    private double ala;
    //Liitettävät oliot
    private Rakennus rakennus;

    public Tontti(String osoite, String x, String y, double pinta){
        //constructori
        this.nimi = osoite;
        this.sijainti_x = x;
        this.sijainti_y = y;
        this.ala = pinta;
        rakennus = null;
    }
    public void setRakennus(Rakennus r){
        //liitetään rakennus tähän tonttiin
        this.rakennus = r;
    }/*
    public double getAla(){
        //hienosäätöä, estetään rakennusta olemasta tonttia suurempi
        return this.ala;
    }*/
    public String tiedot(){
        //tontin tulostusfunktio, mallia otettu puu/pesä/lintu koostedemosta.
        //kutsuu sisäisesti rakennuksen tulostusta, joka taas kutsuuu asukkaiden
        //tulostusta. palauttaa stringin joka on ulkomuodoltaan sopiva tulostettavaksi
        if (rakennus != null){
            return ("\n\nTontin nimi: " + nimi + "\nKoordinaatit: " + sijainti_x + ", " + sijainti_y
             + "\nPinta-ala: " + ala + " m²\n" +  rakennus.tiedot());
        }
        else{ //tämä on periaatteessa turha, ylijäämä testauksesta
            return ("Nimi: " + nimi + "\nGPS: " + sijainti_x + ", " + sijainti_y
             + "\nPinta-ala: " + ala + "\nEi rakennusta");
        }
    }

}