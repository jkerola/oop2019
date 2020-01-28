import java.util.ArrayList;

class Subscription {
    //variables
    private String lehdenNimi;
    private String tilaajanNimi;
    private String toimitusOsoite;
    private double kuukausiHinta;
    //arraylists are private, with their own get/set methods
    private ArrayList<StandinSubscription> standList = new ArrayList<StandinSubscription>();
    private ArrayList<RegularSubscription> regList = new ArrayList<RegularSubscription>();

    //constructor
    public Subscription(String lehti, String tilaaja, String osoite, double hinta){
        this.lehdenNimi = lehti;
        this.tilaajanNimi = tilaaja;
        this.toimitusOsoite = osoite;
        this.kuukausiHinta = hinta;
    }
    //get methods, as requested
    public String getLehdenNimi(){
        return this.lehdenNimi;
    }
    public String getTilaajanNimi(){
        return this.tilaajanNimi;
    }
    public String getToimitusOsoite(){
        return this.toimitusOsoite;
    }
    public double getHinta(){
        return this.kuukausiHinta;
    }
    //set methods, as requested
    public void setLehdenNimi(String name){
        this.lehdenNimi = name;
    }
    public void setTilaajanNimi(String name){
        this.lehdenNimi = name;
    }
    public void setToimitusOsoite(String osoite){
        this.toimitusOsoite = osoite;
    }
    public void setHinta(double hinta){
        this.kuukausiHinta = hinta;
    }
    //super important methods
    public double calcPrice(){
        //inherited class overwrites
        return 0;
    }

    public String printInvoice(){
        //does not return anything, classes that inherit overwrite
        return "";
    };
    // creates a new subscription and adds it to list
    public void setRegSub(String mag, String name, String addr, double price, int dur){
        this.regList.add(new RegularSubscription(mag, name, addr, price, dur));
    }
    public void setStandSub(String mag, String name, String addr, double price, int sale){
        this.standList.add(new StandinSubscription(mag, name, addr, price, sale));
    }
    //returns subscriber lists
    public ArrayList<StandinSubscription> getStandSubs(){
        return this.standList;
    }
    public ArrayList<RegularSubscription> getRegSubs(){
        return this.regList;
    }

}