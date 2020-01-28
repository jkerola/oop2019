class Property {
    //variables
    private String location;
    private String type;
 
    //constructor
    public Property(String location, String type){
        this.location = location;
        this.type = type;
    }
    //set methods
    public void setLocation(String loc){
        this.location = loc;
    }
    public void setType(String type){
        this.type = type;
    }
    //get methods
    public String getLocation(){
        return this.location;
    }
    public String getType(){
        return this.type;
    }
}