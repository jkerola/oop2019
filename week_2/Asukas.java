class Asukas{
    //jäsenmuuttujat
    private String nimi;
    private String aika;

    public Asukas(String n, String a){
        //constructori
        this.nimi = n;
        this.aika = a;
    }

    public String tiedot(){
        //metodi tietojen tulostusta varten.
        return (nimi + ",  " + aika + "\n");
    }
}