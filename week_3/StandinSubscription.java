class StandinSubscription extends Subscription{
    //added variables
    private int alennusProsentti;
    //constructor
    public StandinSubscription(String lehti, String tilaaja, String osoite, double hinta, int alennus){
        //invoking Subscription class constructor
        super(lehti, tilaaja, osoite, hinta);
        this.alennusProsentti = alennus;
    }
    public double calcPrice(){
        double hinta = this.getHinta();
        double ale = (double) this.getAlennusProsentti();
        return (hinta * 12 * (1 - (ale/100)));
    }
    public String printInvoice(){
        return (
            "Lehden nimi: " + this.getLehdenNimi() + ", kestotilaus\n" +
            "Hinta: " + this.getHinta() + " * 12kk, -" + this.getAlennusProsentti() + " % = " + String.format("%.2f",this.calcPrice()) + " €/vuosi" +
            "\nTilaajan nimi: " + this.getTilaajanNimi() +
            "    Toimitusosoite: " + this.getToimitusOsoite() + "\n\n"
        );
    }
    // note that other get/set methods are inherited from Subscription
    // get methods, as requested
    public int getAlennusProsentti(){
        return this.alennusProsentti;
    }
    // set methods, as requested
    public void setAlennusProsentti(int ale){
        this.alennusProsentti = ale;
    }
}