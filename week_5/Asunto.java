import java.util.ArrayList;

class Asunto{
    //variables
    private double area;
    private int rooms;
    private ArrayList<Asukas> tenants;
    //constructor
    public Asunto(double area, int rooms){
        this.area = area;
        this.rooms = rooms;
        this.tenants = new ArrayList<Asukas>();
    }
    //set methods
    public void setArea(double area){
        this.area = area;
    }
    public void setRooms(int rooms){
        this.rooms = rooms;
    }
    public void setAsukas(Asukas tenant){
        this.tenants.add(tenant);
    }
    //get methods
    public double getArea(){
        return this.area;
    }
    public int getRooms(){
        return this.rooms;
    }
    public ArrayList<Asukas> getTenants(){
        return this.tenants;
    }
    public String printInfo(){
        String asunto = "";
        asunto += "\nHuoneita: " + getRooms()
        + " Pinta-ala: " + getArea() + "\n{";
        for(int i=0; i < tenants.size(); i++){
            asunto += "\n    Asukas" + (i + 1) + ": " + getTenants().get(i).getName();
        }
        asunto += "\n}\n\n";
        return asunto;
    }
}