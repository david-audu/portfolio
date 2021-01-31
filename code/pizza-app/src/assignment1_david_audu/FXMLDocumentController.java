
package assignment1_david_audu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class FXMLDocumentController implements Initializable {
    
    private String size; 
    private String crust;
    
    Date d = new Date();
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
    ArrayList<Pizza> pizzaOrder = new ArrayList<Pizza>();
    Random r = new Random();
    int orderNumber = 1000;
    ToggleGroup sizeGroup = new ToggleGroup();
    ToggleGroup crustGroup = new ToggleGroup();
    
    
    @FXML
    private Label label;
    
    @FXML
    Label receipt = new Label();
    //RadioButtons for Pizza Sizes
    @FXML
    RadioButton small = new RadioButton();
    
    @FXML
    RadioButton medium = new RadioButton();
    
    @FXML
    RadioButton large = new RadioButton();
    
    //RadioButtons for Pizza Crust
    @FXML
    RadioButton thin = new RadioButton();
    
    @FXML
    RadioButton regular = new RadioButton();
    
    @FXML
    RadioButton thick = new RadioButton();
    
    @FXML
    CheckBox cBeef = new CheckBox();
    
    @FXML
    CheckBox cSteak = new CheckBox();
    
    @FXML
    CheckBox cPeperoni = new CheckBox();
    
    @FXML
    CheckBox cChicken = new CheckBox();
    
    @FXML
    CheckBox cHam = new CheckBox();
    
    @FXML
    CheckBox cSuasage = new CheckBox();
    
    @FXML
    CheckBox cPineapple = new CheckBox();
    
    @FXML
    CheckBox cOnion = new CheckBox();
    
    @FXML
    CheckBox cMushroom = new CheckBox();
    
    @FXML
    CheckBox cOlive = new CheckBox();
    
    @FXML
    CheckBox cSpinach = new CheckBox();
    
    @FXML
    CheckBox cPeppers = new CheckBox();
    
    @FXML
    Label dailySpecial = new Label();
    
    String [] day = d.toString().split(" ");
    
    
    
    /*The button will create a Pizza Object
    * and add it to an ArrayList
    */
    @FXML
    Button add = new Button();
    
    @FXML
    Button checkout = new Button();
    
    @FXML
    StackPane stack = new StackPane();
    
     public void writeToFile(String str[], String fileName){
        try{
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        
        for(int i=0; i<str.length; i++)
        {
          bw.write(str[i]);
          bw.newLine();
        }
        
        bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
   
        
      
    
    public void clearAll()
    {
       sizeGroup.getSelectedToggle().setSelected(false);
       crustGroup.getSelectedToggle().setSelected(false);
       cBeef.setSelected(false);
       cSteak.setSelected(false);
       cPeperoni.setSelected(false);
       cChicken.setSelected(false);
       cHam.setSelected(false);
       cSuasage.setSelected(false);
       cPineapple.setSelected(false);
       cOnion.setSelected(false);
       cMushroom.setSelected(false);
       cOlive.setSelected(false);
       cSpinach.setSelected(false);
       cPeppers.setSelected(false);
       stack.getChildren().clear();
    }
    public double roundTwoDecimalPlaces(double value)
    {
      value = value*100;
      value = Math.round(value);
      value = value /100;
      
     return value;
    }
    
    public double getTaxAmount(double value)
    {
        return roundTwoDecimalPlaces(value*0.13);
    }
    public void printReciept()
    {
       orderNumber++;
       String receipt="";
       receipt = receipt + "Ade's Kitchen\n";
       receipt = receipt + "6075 Mavis Road, \nMississauga, ON \nL5R 4G6\n" + d + "\n";
       receipt = receipt + "*******************\n";
       receipt = receipt + "Order Number: "+ orderNumber + "\n";
       
       double sum=0;
       for(int i=0; i<pizzaOrder.size(); i++)
       {
         receipt = receipt + pizzaOrder.get(i) + "\n";
         receipt = receipt + "----------------------\n";
         sum = sum + pizzaOrder.get(i).getPrice(); 
       }
       receipt = receipt + "----------------------\n";
       receipt = receipt + "Subtotal : $"+ roundTwoDecimalPlaces(sum) + "\n";
       receipt = receipt + "Taxes 13% : $"+ getTaxAmount(sum) + "\n";
       if(day[0].equals("Tue")|| day[0].equals("Fri"))
       {
         receipt = receipt + "50% DISCOUNT * Total: $"+ roundTwoDecimalPlaces((sum+getTaxAmount(sum))/2);
       }
       else
       {
           receipt = receipt + "Total: $"+ roundTwoDecimalPlaces(sum+getTaxAmount(sum));
       }
       
       
       
       String str="";
       str = str +"receipt"+ orderNumber + ".txt";
       String [] tokens = receipt.split("\n");
       writeToFile(tokens, str);
       //System.out.println(receipt);
    }
    
    public void addPizza()
    {
       try{
          Pizza p;
          if(size.equals(null) || crust.equals(null))
          {
            p=null;
          }
          else
          {
        
            p = new Pizza(size,crust,chicken,beef,steak,peperoni,ham, suasage, pineapple,onion,mushroom, olive, spinach, peppers);
            pizzaOrder.add(p);
            clearAll();  
          }
        }catch(Exception ex){
          
        }
       
    }
    
    public void createPizza()
    {   
        Image imgPizza = new Image("images/Pizza.png");
        ImageView view = new ImageView(imgPizza);
        stack.getChildren().add(view);
        
        if (small.isSelected())  {size = "Small";}
        if (medium.isSelected()) { size = "Medium";}
        if (large.isSelected())  {size = "Large";}

        if (thin.isSelected())  {crust = "Thin";}
        if (regular.isSelected()) { crust = "Regular";}
        if (thick.isSelected())  {crust = "Thick";}   
        
        if (cBeef.isSelected())  
        {
            Image imgBeef = new Image("images/beef.png");
            ImageView viewBeef = new ImageView(imgBeef);
            stack.getChildren().add(viewBeef);
            beef = true;
        } 
        if (cSteak.isSelected())  
        {   Image imgSteak = new Image("images/steak.png");
            ImageView viewSteak = new ImageView(imgSteak);
            stack.getChildren().add(viewSteak);
            steak = true;
        } 
        if (cPeperoni.isSelected())  
        { 
            Image imgPep = new Image("images/pepperoni.png");
            ImageView viewPep = new ImageView(imgPep);
            stack.getChildren().add(viewPep);
            peperoni = true;
        } 
        if (cChicken.isSelected())  
        {
            Image imgChick = new Image("images/chicken.png");
            ImageView viewChick = new ImageView(imgChick);
            stack.getChildren().add(viewChick);
            chicken = true;
        } 
        if (cHam.isSelected())  
        {  
            Image imgHam = new Image("images/ham.png");
            ImageView viewHam = new ImageView(imgHam);
            stack.getChildren().add(viewHam);
            ham = true;
        } 
        if (cSuasage.isSelected())  
        {
            Image imgSuasage = new Image("images/susauge.png");
            ImageView viewSuasage = new ImageView(imgSuasage);
            stack.getChildren().add(viewSuasage);
            suasage = true;
        } 
        if (cPineapple.isSelected()) 
        {
            Image imgPineapple = new Image("images/pineapple.png");
            ImageView viewPineapple = new ImageView(imgPineapple);
            stack.getChildren().add(viewPineapple);
            pineapple = true;
        } 
        if (cOnion.isSelected())  
        {
            Image imgOnion = new Image("images/onions.png");
            ImageView viewOnion = new ImageView(imgOnion);
            stack.getChildren().add(viewOnion);
            onion = true;
        } 
        if (cMushroom.isSelected())
        {
            Image imgMushroom = new Image("images/mush.png");
            ImageView viewMushroom = new ImageView(imgMushroom);
            stack.getChildren().add(viewMushroom);
            mushroom = true;
        } 
        
        if (cOlive.isSelected())  
        {
            Image imgOlive = new Image("images/olives.png");
            ImageView viewOlive = new ImageView(imgOlive);
            stack.getChildren().add(viewOlive);
            olive = true;
        } 
        
        if (cSpinach.isSelected()) 
        {
            Image imgSpinach = new Image("images/spinach.png");
            ImageView viewSpinach = new ImageView(imgSpinach);
            stack.getChildren().add(viewSpinach);
            spinach = true;
        } 
        if (cPeppers.isSelected()) 
        {
            Image imgPeppers = new Image("images/peppers.png");
            ImageView viewPeppers = new ImageView(imgPeppers);
            stack.getChildren().add(viewPeppers);
            peppers = true;
        } 
        
        if(day[0].equals("Tue")|| day[0].equals("Fri"))
        {
           dailySpecial.setText("Tuesday and Friday SPECIAL 50%OFF");     
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        small.setToggleGroup(sizeGroup);
        medium.setToggleGroup(sizeGroup);
        large.setToggleGroup(sizeGroup);

        thin.setToggleGroup(crustGroup);
        regular.setToggleGroup(crustGroup);
        thick.setToggleGroup(crustGroup);
    }    
    
}
