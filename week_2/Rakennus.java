class Rakennus{
    //jäsenmuuttujat
    private int huoneet;
    private double ala;
    private int asukkaita;
    //Array asukas olioista, jolloin rakennuksessa voi asua monta ihmistä
    private Asukas[] asukkaat_lista;

    public Rakennus(int h, double a, int n){
        //constructori
        this.huoneet = h;
        this.ala = a;
        this.asukkaita = n;
        asukkaat_lista = new Asukas[n];
    }

    public void setAsukas(Asukas a, int n){
        //asettaa asukkaan tähän rakennukseen
        //indeksi n kertoo paikan listassa
        this.asukkaat_lista[n] = a;
    }

    public String asukasTiedot(){
        //asukaslistan tulostus eroaa hieman muusta.
        //jokainen asukas kutsuu omaa tulostus metodiaan
        String n = "\n\nAsukkaat:\n";
        for(int i = 0; i < asukkaat_lista.length; i++){
            n += asukkaat_lista[i].tiedot() + "";
        }
        return n;
    }
    public String tiedot(){
        //rakennuksen oma tietojen tulostusmetodi
        //kutsuu myös asukkaiden tulostusmetodit
        return("\nRakennuksen tiedot\nRakennuksen pinta-ala: " + ala + " m²"
        + "\nHuoneita: " + huoneet + "\nAsukkaita : " + asukkaita 
        + asukasTiedot());
    }
}