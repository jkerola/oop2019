class RegularSubscription extends Subscription{
    //Added variables
    private int tilauksenKesto;
    //SubConstructor
    public RegularSubscription(String lehti, String tilaaja, String osoite, double hinta, int kesto){
        //invoke Subscription class constructor
        super(lehti, tilaaja, osoite, hinta);
        this.tilauksenKesto = kesto;
    }
    public double calcPrice(){
        return (this.getTilauksenKesto() * this.getHinta());
    }
    public String printInvoice(){
        return (
            "Lehden nimi: " + this.getLehdenNimi() + ", normaalitilaus\n" +
            "Hinta: " + this.getTilauksenKesto() + "kk * " + this.getHinta() + " € = " + this.calcPrice() + " €" +
            "\nTilaajan nimi: " + this.getTilaajanNimi() +
            "    Toimitusosoite: " + this.getToimitusOsoite() + "\n\n"
        );
    }
    //note that other get/set methods are inherited from Subscription
    //get methods, as requested
    public int getTilauksenKesto(){
        return this.tilauksenKesto;
    }
    //set methods
    public void setTilauksenKesto(int kesto){
        this.tilauksenKesto = kesto;
    }

}