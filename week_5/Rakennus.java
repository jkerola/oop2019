import java.util.ArrayList;

//base class
class Rakennus{
    //variables
    private int apartments;
    private ArrayList<Asunto> apartmentList;
    //constructor
    public Rakennus(int ap){
        this.apartments = ap;
        this.apartmentList = new ArrayList<Asunto>(this.apartments);
    }
    //set methods
    public void setNumberOfApartments(int ap){
        this.apartments = ap;
    }
    public void setApartment(int index, Asunto apartment){
        this.apartmentList.add(index, apartment);
    }
    //get methods
    public int getNumberOfApartments(){
        return this.apartments;
    }
    public ArrayList<Asunto> getApartments(){
        return this.apartmentList;
    }
    public String getType(){
        return "...";
    }
    public String printInfo(){
        String rakennus = "";
        rakennus += "\nRakennuksessa asuntoja: " + getNumberOfApartments();
        for(int i=0; i < apartmentList.size(); i++){
            rakennus += "\nAsunto nro: " + (i + 1) + apartmentList.get(i).printInfo();
        }
        return rakennus;
    }
}
/*
since the extended class types have no special features
and are rather short, creating separate files is messy.
i've defined them inside the same file, so
javac will create separate classes for them
but the code will be neatly in one place
*/
class Omakotitalo extends Rakennus{
    //constructor
    public Omakotitalo(int ap){
        super(ap);
    }    
    public String getType(){
        return "Omakotitalo";
    }
}

class Rivitalo extends Rakennus{
    //constructor
    public Rivitalo(int ap){
        super(ap);
    }
    public String getType(){
        return "Rivitalo";
    }
}

class Kerrostalo extends Rakennus{
    //constructor
    public Kerrostalo(int ap){
        super(ap);
    }
    public String getType(){
        return "Kerrostalo";
    }
}