package DairyQueen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * Link it with venmo so that the "People" are linked with venmo profiles and that is how the money will move.
 * Having the Host be the one who launches the app (Person 1), and the rest will send money to him/her.
 * Before you pay: all "guests" enter their party code on venmo and the host enters it on his/her app: then when the hosts completes the app form
 * It will then automatically pay the host the guests' costs
 */


 /*
  * Next: larger menu - scroll bar to look throuh options, search bar

  * Future (bigger than me): Menu API/SQL datase/txt document, location services for tax
  */
public class IndividualPay {
   static String[] menu = {"Blizzard", "Sundae", "Burger", "Chicken Sandwich", "Fries", "Onion Rings"};
   static int[] party;
   static int x = 0;
public static void main(String[] args) throws FileNotFoundException {
     guestOrHost();
  }
public static void buildGUI() {
   JFrame[] frame = new JFrame[25];
   for(x = party.length; x > 0; x--) {
      frame[x] = new JFrame("Person " + (x));
      JPanel panel = new JPanel();
      frame[x].setSize(400, menu.length * 50);
      panel.setLayout(null);
      frame[x].setLocationRelativeTo(null);

      int startYLbl = -105;
      int startYTxt = -20;
      JLabel[] item = new JLabel[100];
      JTextField[] item_txt = new JTextField[100];
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

      JLabel tip = new JLabel("Tip Percentage (Optional):                          Write 15% as '0.15'");
      tip.setBounds(10, startYTxt - 20, 400, 25);
      JTextField tipTxt = new JTextField();
      tipTxt.setBounds(220, startYTxt - 20, 50, 30);

      if(x == 1) {
         panel.add(tip);
         panel.add(tipTxt);
      }
      
      JButton button = new JButton("Save");
      button.setBounds(10, startYTxt + 10, 100, 25);
      button.addActionListener((event) -> {try {x++; writeFile(item_txt);frame[x].setVisible(false); if(x == party.length) {calculateCost(tipTxt);}} catch (IOException e) {e.printStackTrace();}});
      panel.add(button);

      frame[x].getRootPane().setDefaultButton(button);
      frame[x].add(panel);
      frame[x].setVisible(true);
   }
}
public static void guestOrHost() throws FileNotFoundException{
   JFrame frame = new JFrame("Guest Or Host");
   JPanel panel = new JPanel();
   frame.setSize(400, 115);
   panel.setLayout(null);
   frame.setLocationRelativeTo(null);

   JButton host = new JButton("Host");
   host.setBounds(100, 20, 100, 25);
   host.addActionListener((event) -> {try { hostGUI(); } catch (FileNotFoundException e) {e.printStackTrace();}frame.dispose();});
   panel.add(host);

   JButton guest = new JButton("Guest");
   guest.setBounds(200, 20, 100, 25);
   guest.addActionListener((event) -> {guestGUI(); frame.dispose();});
   panel.add(guest);

   frame.add(panel);
   frame.setVisible(true);
}
public static void guestGUI() {
   JFrame frame = new JFrame("Guest");
   JPanel panel = new JPanel();
   frame.setSize(400, 115);
   panel.setLayout(null);
   frame.setLocationRelativeTo(null);

   JLabel partyLabel = new JLabel("Enter Party Host Code: ");
   partyLabel.setBounds(35, -65, 400, 200);
   panel.add(partyLabel);

   JTextField partyText = new JTextField();
   partyText.setBounds(190, 19, 50, 30);
   panel.add(partyText);

   JButton button = new JButton("Confirm");
   button.setBounds(250, 21, 100, 25);
   button.addActionListener((event) -> {int number = Integer.parseInt(partyText.getText()); System.out.println("CODE: " + number); frame.dispose();});
   panel.add(button);

   frame.add(panel);
   frame.getRootPane().setDefaultButton(button);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
}
public static void hostGUI() throws FileNotFoundException {
   File trashFile = new File("/Users/lukehenry/Documents/Coding /DairyQueen/DQ.txt");
   trashFile.delete();

   JFrame frame = new JFrame("Number of People ");
   JPanel panel = new JPanel();
   frame.setSize(400, 115);
   panel.setLayout(null);
   frame.setLocationRelativeTo(null);

   JLabel label = new JLabel("Enter Number of People: ");
   label.setBounds(10, -75, 400, 200);
   panel.add(label);
   JTextField text = new JTextField();
   text.setBounds(200, 11, 50, 30);
   panel.add(text);


   Random random = new Random();
   int min = 1000;
   int max = 9999;
   int randomNumber = random.nextInt((max - min) + 1) + min;
   
   JLabel partyLabel = new JLabel("Party Host Code: " + randomNumber);
   partyLabel.setBounds(10, -45, 400, 200);
   panel.add(partyLabel);
   
   JButton button = new JButton("Next");
   button.setBounds(270, 43, 100, 25);
   button.addActionListener((event) -> {party = new int[Integer.parseInt(text.getText())]; frame.dispose(); buildGUI();});
   panel.add(button);

   frame.getRootPane().setDefaultButton(button);
   frame.add(panel);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
}
public static void writeFile(JTextField[] item_txt) throws IOException {
   int number;
   RandomAccessFile raf = new RandomAccessFile("/Users/lukehenry/Documents/Coding /DairyQueen/DQ.txt", "rw");
   raf.seek(raf.length());
   for(int i = 0; i < menu.length; i++) {
      try {number = Integer.parseInt(item_txt[i].getText());} catch (Exception e) {number = 0;}
      for(int j = 0; j < number; j++) {
         raf.writeBytes((i+1) + ",");
      }
   }
   raf.seek(raf.length()-1);
   raf.writeBytes("\n");
   raf.close();
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
public static void calculateCost(JTextField tipTxt ) throws FileNotFoundException {   
   Scanner input = new Scanner(new File("/Users/lukehenry/Documents/Coding /DairyQueen/DQ.txt"));
   double[] fees = new double[party.length];
   double tip;
   if(tipTxt.getText().equals("")) {
      tip = 0;
   } else {
      tip = round(Double.parseDouble(tipTxt.getText()),2);
   }
   for(int i = 0; i < party.length; i++) {
      String[] selections = input.nextLine().split(",");
      double total = 0.0;
      for (String selection : selections) {
         int index = Integer.parseInt(selection.trim()) - 1;
         total += getPrice(menu[index]);
      }
      total *= 1.06;
      total = round(total, 2);
      total *= (1 + tip);
      fees[i] = round(total, 2);
   }
   JFrame frame = new JFrame("Fees");
   JPanel panel = new JPanel();
   frame.setSize(400, party.length * 60);
   panel.setLayout(null);
   frame.setLocationRelativeTo(null);

   int startYLbl = -105;
   JLabel[] item = new JLabel[25];
   for(int i = 0; i < party.length; i++) {
      startYLbl += 30;
      item[i] = new JLabel("Person " + (i+1) + " owes: $" + fees[i]);
      item[i].setBounds(10, startYLbl, 400, 200);
      panel.add(item[i]);
   }

   frame.add(panel);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
   input.close();
}
public static double round(double value, int places) {
   if (places < 0) throw new IllegalArgumentException();
   long factor = (long) Math.pow(10, places);
   value = value * factor;
   long tmp = Math.round(value);
   return (double) tmp / factor;
}
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