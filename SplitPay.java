package DairyQueen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class SplitPay {
   static String[] menu = {"Blizzard", "Sundae", "Burger", "Chicken Sandwich", "Fries", "Onion Rings"};
public static void main(String[] args) throws FileNotFoundException {
     buildGUI();
  }
public static void buildGUI() {
   JFrame frame = new JFrame("Order");
   JPanel panel = new JPanel();
   frame.setSize(400, menu.length * 50);
   panel.setLayout(null);
   frame.setLocationRelativeTo(null);

   int startYLbl = -105;
   int startYTxt = -20;
   JLabel[] item = new JLabel[25];
   JTextField[] item_txt = new JTextField[25];
   for(int i = 0; i < menu.length; i++) {
      startYLbl += 30;
      startYTxt += 30;
      item[i] = new JLabel("Number of " + menu[i] + getPlural(menu[i]));
      item[i].setBounds(10, startYLbl, 400, 200);
      item_txt[i] = new JTextField(20);
      item_txt[i].setBounds(220, startYTxt, 50, 30);
      panel.add(item[i]);
      panel.add(item_txt[i]);
   }
   startYLbl += 50;
   startYTxt += 50;
   
   JLabel people = new JLabel("Number of People");
   people.setBounds(10, startYLbl, 400, 200);
   JTextField people_txt = new JTextField(20);
   people_txt.setBounds(220, startYTxt , 50, 30);
   panel.add(people);
   panel.add(people_txt);


   JLabel output = new JLabel("Cost Per Person:");
   output.setBounds(220, startYLbl + 30, 400, 200);
   panel.add(output);

   JButton button = new JButton("Calculate");
   button.setBounds(10, startYTxt + 30, 100, 25);
   button.addActionListener((event) -> {try {writeFile(item_txt, people_txt, output);} catch (IOException e) {e.printStackTrace();}});
   panel.add(button);

   frame.add(panel);
   frame.setVisible(true);
  }

public static void writeFile(JTextField[] item_txt, JTextField people_txt, JLabel output) throws IOException {
   int number;
   File trashFile = new File("/Users/lukehenry/Documents/Coding /DairyQueen/DQ.txt");
   trashFile.delete();
   RandomAccessFile raf = new RandomAccessFile("/Users/lukehenry/Documents/Coding /DairyQueen/DQ.txt", "rw");
   for(int i = 0; i < menu.length; i++) {
      try {number = Integer.parseInt(item_txt[i].getText());} catch (Exception e) {number = 0;}
      for(int j = 0; j < number; j++) {
         raf.writeBytes((i+1) + ",");
      }
   }
   raf.seek(raf.length()-1);
   raf.writeBytes("\n" + people_txt.getText());
   raf.close();
   output.setText("Cost Per Person: " + calculateCost());
}
private static String getPlural(String string) {
   switch(string.charAt(string.length()-1)){
      case 'h':
         return "es";
      case 's':
         return "";
      default: 
         return "s";
   }
}
public static double calculateCost() throws FileNotFoundException {
   //Scanner input = new Scanner("https://linkprotect.cudasvc.com/url?a=https%3a%2f%2fSystem.in&c=E,1,URtT0UWKlzMo0jMBLunSF-yPVB9Oxh4NnKVzVn3CW4fHkZooGTucdk1Yc7nUii-4c9zu7Bb2ir9kgwt4do2uIJSbMYqXOiYlCHRUHxKVtPQxnLiRdlZ3mxok_Tc,&typo=1");

     Scanner input = new Scanner(new File("/Users/lukehenry/Documents/Coding /DairyQueen/DQ.txt"));

     // Create a virtual menu for Dairy Queen
     //String[] menu = {"Blizzard", "Sundae", "Burger", "Chicken Sandwich", "Fries", "Onion Rings"};

     // Display the menu to the user
     System.out.println("Dairy Queen Menu:");
     for (int i = 0; i < menu.length; i++) {
        System.out.println((i+1) + ". " + menu[i]);
     }

     // Prompt the user to select items from the menu
     System.out.print("Enter the numbers of the items you want to order (separated by commas): ");
     String[] selections = input.nextLine().split(",");

     // Calculate the total bill amount based on the user's selections
     double total = 0.0;
     for (String selection : selections) {
        int index = Integer.parseInt(selection.trim()) - 1;
        total += getPrice(menu[index]);
     }

     // Display the total bill amount to the user
     System.out.println("Your total bill amount is: $" + total);

     // Prompt the user to scan a QR code to split the bill
     System.out.print("Scan the QR code to split the bill with your friends: ");

     // Calculate the share per person and display the result to the user
     System.out.print("Enter the number of people: ");
     int numPeople = input.nextInt();

     double share = total / numPeople;
     System.out.println("Each person should pay: $" + share);
     return share;
}

  // Helper method to get the price of a Dairy Queen menu item
private static double getPrice(String item) {
     switch (item) {
        case "Blizzard":
           return 3.99;
        case "Sundae":
           return 2.99;
        case "Burger":
           return 4.99;
        case "Chicken Sandwich":
           return 5.99;
        case "Fries":
           return 1.99;
        case "Onion Rings":
           return 2.99;
        default:
           return 0.0;
     }
  }
}