
package assignment1_david_audu;


public class Pizza 
{
    private double price;
    private String size; 
    private String crust;
    
    //Boolean Variables for TOPPINGS
    private boolean chicken; 
    private boolean beef;
    private boolean steak; 
    private boolean peperoni;
    private boolean ham;
    private boolean suasage;
    private boolean pineapple; 
    private boolean onion; 
    private boolean mushroom;
    private boolean olive; 
    private boolean spinach;
    private boolean peppers;

    public Pizza(String size, String crust, boolean chicken, boolean beef, boolean steak, boolean peperoni, boolean ham, boolean suasage, 
            boolean pineapple, boolean onion, boolean mushroom, boolean olive, boolean spinach, boolean peppers) {
        this.size = size;
        this.crust = crust;
        this.chicken = chicken;
        this.beef = beef;
        this.steak = steak;
        this.peperoni = peperoni;
        this.ham = ham;
        this.suasage = suasage;
        this.pineapple = pineapple;
        this.onion = onion;
        this.mushroom = mushroom;
        this.olive = olive;
        this.spinach = spinach;
        this.peppers = peppers;
        
        setPrice(this.size, this.chicken, this.beef, this.steak, this.peperoni, this.ham, this.suasage, this.pineapple,
                this.onion, this.mushroom, this.olive, this.spinach, this.peppers);
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public void setPrice(String size, boolean chicken, boolean beef, boolean steak, boolean peperoni,
            boolean ham, boolean suasage, boolean pineapple, boolean onion, boolean mushroom, boolean olive, boolean spinach, boolean peppers) {
      
      price = 0;
     if(size.equals("Small")) {price = price + 8;}
     if(size.equals("Medium")) {price = price + 10;}
     if(size.equals("Large"))  {price = price + 13;}
     if(chicken == true) {price = price + 1;} 
     if(beef == true)   {price = price + 0.50;}
     if(steak == true)   {price = price + 0.70;}
     if(peperoni == true)   {price = price + 0.50;}
     if(ham == true)   {price = price + 0.50;}
     if(suasage == true)   {price = price + 0.50;}
     if(pineapple == true)   {price = price + 0.25;}
     if(onion== true)   {price = price + 0.25;}
     if(mushroom == true)   {price = price + 0.25;}
     if(olive == true)   {price = price + 0.25;}
     if(spinach == true)   {price = price + 0.25;}
     if(peppers == true)   {price = price + 0.25;}

    }
    
    @Override
    public String toString() {
        String toppings ="\nTOPPINGS: ";
        if(chicken == true) {toppings = toppings + "Chicken ";} 
        if(beef == true)   {toppings = toppings + " Beef ";} 
        if(steak == true)    {toppings = toppings + " Steak ";} 
        if(peperoni == true)    {toppings = toppings + " Peperoni ";} 
        if(ham == true)    {toppings = toppings + " Ham ";} 
        if(suasage == true)    {toppings = toppings + " Suasage ";} 
        if(pineapple == true)   {toppings = toppings + " Pineapple ";} 
        if(onion== true)    {toppings = toppings + " Onion ";} 
        if(mushroom == true)    {toppings = toppings + " Mushroom ";} 
        if(olive == true)    {toppings = toppings + " Olive ";} 
        if(spinach == true)    {toppings = toppings + " Spinach ";} 
        if(peppers == true)    {toppings = toppings + " Green Peppers ";} 
        
        return size + " Pizza " + "\n" + crust +  " Crust " + toppings + "\nPrice: $" + price ;
    }   
    
}