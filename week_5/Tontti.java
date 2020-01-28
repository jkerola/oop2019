class Tontti{
    //variables
    private String name;
    private String address;
    private double area;
    private Rakennus building;
    //constructor
    public Tontti(String name, String add, double area){
        this.name = name;
        this.address = add;
        this.area = area;
    }
    //set methods
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String add){
        this.address = add;
    }
    public void setArea(double area){
        this.area = area;
    }
    public void setOma(Omakotitalo build){
        this.building = build;
    }
    public void setRivi(Rivitalo build) {
        this.building = build;
    }
    public void setKerros(Kerrostalo build) {
        this.building = build;
    }
    //get methods
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public double getArea(){
        return this.area;
    }
    public Rakennus getBuilding(){
        return this.building;
    }
    public String printInfo(){
        String tontti = "";
        tontti += "\nTontin nimi: " + getName() + " Pinta-ala: " + getArea() + "\nOsoite: " + getAddress()
                + " Tyyppi: " + getBuilding().getType() + getBuilding().printInfo();
        return tontti;
        }
}   