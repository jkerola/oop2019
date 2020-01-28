import java.util.ArrayList;

class InsInfoContainer{
    //variables
    private ArrayList<InsuranceInfo> insDatabase;
    //constructor
    public InsInfoContainer(){
        this.insDatabase = new ArrayList<InsuranceInfo>();
    }
    //method which the main program calls to initiate
    //any creation, which in turn triggers inner methods to do work
    //only this function is called from outside!!
    public void createInsuranceInfo(String loc, String type, double value){
        InsuranceInfo a = new InsuranceInfo(new Property(loc, type), value);
        setInsuranceInfo(a);
    }
    //adds a newly created object into the protected database
    public void setInsuranceInfo(InsuranceInfo info){
        this.insDatabase.add(info);
    }
    //initiates the printing process, checks for parameters
    //calls for sort method if needed
    public String parsePrint(boolean fullPrint, boolean greater, double value){
        String info;
        if(fullPrint == true){
            info = printDatabase(this.insDatabase);
        }else {
            //calls sort method, which passes the new sorted 
            //array list forward for printing
            info = printDatabase(sortDatabase(value, greater));
        }
        return info;
    }
    //prints any given database.
    public String printDatabase(ArrayList database){
        String info = "";
        for(int i=0; i<database.size(); i++){
            //need casting for this to work
            InsuranceInfo n = (InsuranceInfo) database.get(i);
            info += "\nSijainti: " + n.getProperty().getLocation() +
            "\nTyyppi: " + n.getProperty().getType() + 
            "\nVakuutusarvo: " + n.getValue() + " euroa\n";
        }
        info = info + "\nHakuehdoilla löytyi " + database.size() + " kpl kohteita.\n";
        return info;
    }
    //sorting method for teh database
    //checks if property values are above/below a given parameter
    //creates a new list with objects that pass and returns the list
    public ArrayList sortDatabase(double value, boolean greater){
        ArrayList<InsuranceInfo> sortedList = new ArrayList<InsuranceInfo>();
        if(greater == true){
            for(int i=0; i<this.insDatabase.size(); i++){
                if(this.insDatabase.get(i).getValue() >= value){
                    sortedList.add(this.insDatabase.get(i));
                }
            }
        } else {
            for(int i=0; i<this.insDatabase.size(); i++){
                if(this.insDatabase.get(i).getValue() <= value){
                    sortedList.add(this.insDatabase.get(i));
                }
            }
        }
        return sortedList;
    }
}