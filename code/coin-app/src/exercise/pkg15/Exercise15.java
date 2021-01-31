/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.pkg15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author King David
 */
public class Exercise15 extends Application {
    
        static int cents =0;
        static int quarter = 25;
        static int dime = 10;
        static int nickel = 5;
        static int penny = 1;
        static int totalQuarter =0;
        static int totalDime = 0;
        static int totalNickel = 0;
        static int totalPenny = 0;
        static int temp =0;
        ImageView ivcanc = new ImageView();
       
        
    @Override
    public void start(Stage primaryStage) {
       
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
        FlowPane coins = new FlowPane();
        
        Label lbl = new Label("Enter cents between 1 and 100: ");
        TextField input = new TextField();
        
        input.setOnAction(e -> {
            coins.getChildren().clear();
            cents = Integer.parseInt(input.getText());
            breakDown();
            System.out.println(totalQuarter + " Quarters");
            System.out.println(totalDime + " Dime");
            System.out.println(totalNickel + " Nickel");
            System.out.println(totalPenny + " Penny");
            
            for(int i=0; i< totalQuarter; i++)
            {
                Image twoFive = new Image("images/quarter.png");
                ivcanc = new ImageView(twoFive);
                ivcanc.setFitHeight(80);
                ivcanc.setFitWidth(80);
                coins.getChildren().add(ivcanc); 
            }
            
            for(int i=0; i< totalDime; i++)
            {
                Image oneZero = new Image("images/dime.png");
                ivcanc = new ImageView(oneZero);
                ivcanc.setFitHeight(60);
                ivcanc.setFitWidth(60);
                coins.getChildren().add(ivcanc); 
            }
            
            for(int i=0; i< totalNickel; i++)
            {
                Image five = new Image("images/nickel.png");
                ivcanc = new ImageView(five);
                ivcanc.setFitHeight(60);
                ivcanc.setFitWidth(60);
                coins.getChildren().add(ivcanc); 
            }
            
            for(int i=0; i< totalPenny; i++)
            {
                Image one = new Image("images/penny.png");
                ivcanc = new ImageView(one);
                ivcanc.setFitHeight(50);
                ivcanc.setFitWidth(50);
                coins.getChildren().add(ivcanc); 
            }
            
            //coin.getChildren().add(ivcanc);
            
            clear();
        });
        
        grid.add(lbl, 1, 0);
        grid.add(input, 2, 0);
        grid.setPadding(new Insets(10,10,10, 10));
        border.setTop(grid);
        border.setCenter(coins);
        
        Scene scene = new Scene(border, 1000, 250);
        
        primaryStage.setTitle("Exercise 15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void clear()
    {
        totalQuarter =0;
        totalDime = 0;
        totalNickel = 0;
        totalPenny = 0;
        temp =0;
    
    }
    public static void breakDown()
    {
         if(cents >= 25)
        {
           totalQuarter = (cents / quarter); 
           temp=cents % quarter;
           if(temp >= 10)
           {
               totalDime = temp / dime;
               temp = temp % dime;
               if(temp >=5 )
               {
                 totalNickel = temp / nickel;
                 temp = temp % nickel;
               }
               totalPenny = temp;
           }
           else 
           {
              if(temp >=5 )
               {
                 totalNickel = temp / nickel;
                 temp = temp % nickel;
               }
               totalPenny = temp;
                  
           }
        
        }
        else
        {
            if(cents >= 10)
            {
               totalDime = cents / dime;
               temp = cents % dime;
               if(temp >=5 )
               {
                 totalNickel = temp / nickel;
                 temp = temp % nickel;
               }
               totalPenny = temp;
            }
            else
            {
               if(cents >=5 )
               {
                 totalNickel = cents / nickel;
                 temp = cents % nickel;
               }
               totalPenny = temp;
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
