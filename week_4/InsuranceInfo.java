class InsuranceInfo{
    //variables
    private Property property;
    private double value;

    //constructor
    public InsuranceInfo(Property property, double value){
        this.property = property;
        this.value = value;
    }
    //set methods
    public void setProperty(Property n){
        this.property = n;
    }
    public void setValue(double value){
        this.value = value;
    }
    //get methods
    public Property getProperty(){
        return this.property;
    }
    public double getValue(){
        return this.value;
    }
}