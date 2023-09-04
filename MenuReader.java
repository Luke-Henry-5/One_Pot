package DairyQueen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * Link it with venmo so that the "People" are linked with venmo profiles and that is how the money will move.
 * Having the Host be the one who launches the app (Person 1), and the rest will send money to him/her.
 * Before you pay: all "guests" enter their party code on venmo and the host enters it on his/her app: then when the hosts completes the app form
 * It will then automatically pay the host the guests' costs. The party goes in to agree on a tip, the tip is then calculated into the total, and thus divided among the group 
 */


 /*
  * Next: 

  * Future (bigger than me): Every guest gets their own screen for their menu. For mobile orders (doordash for example) 
   combine this service with a doordash order so that it orders food and dividees the money at the same time 
  */
public class MenuReader{
    static String selection;
    static int[] party;
    static int x;
    static String personalSelection = "";
    static String personalPrice = "";
    public static void main(String[] args) throws FileNotFoundException {
        guestOrHost();
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
    public static void hostGUI() throws FileNotFoundException {     
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
        // TODO: this button will bring up the menu selector, use this as the next step for the MIGHT NEED TO SET VISIBILITY TO FALSE INSTEAD OF DISPOSING
        button.addActionListener((event) -> {frame.dispose(); try{menuSelection(text);} catch (IOException e) {System.out.println("Menu Selection error");}});
        //button.addActionListener((event) -> {party = new int[Integer.parseInt(text.getText())]; frame.dispose(); try {processMenu();} catch (IOException e) {e.printStackTrace();}});
        panel.add(button);
     
        frame.getRootPane().setDefaultButton(button);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
     }
    //TODO: enter doesn't hit confirm, it goes to the next restaurant
     private static void menuSelection(JTextField text) throws FileNotFoundException {
        JFrame frame = new JFrame("Choose a Restaurant");
        Scanner scanner = new Scanner(new File("/Users/lukehenry/Documents/Coding /DairyQueen/Menus.txt"));
        String[] menus = scanner.nextLine().split(",");

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Restaurant"}, 0);
        JTable table = new JTable();
        table.setModel(model);
        for(String restaurant : menus) {
            //System.out.println(restaurant+".txt");
            model.addRow(new Object[]{restaurant});
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Add a search box above the table
        JTextField searchBox = new JTextField();
        searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
            private void filterTable() {
                String text = searchBox.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 100, 50);

        JPanel panel = new JPanel(new BorderLayout());
        
        JButton confirm = new JButton("Confirm");
        confirm.setBounds(50, 50, 50, 50);
        confirm.addActionListener((event) -> {
            party = new int[Integer.parseInt(text.getText())];
            try {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String restaurant = (String) model.getValueAt(selectedRow, 0);
                    frame.dispose();
                    processMenu(restaurant);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a restaurant", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        panel.add(scrollPane, BorderLayout.WEST);
        panel.add(confirm, BorderLayout.EAST);

        
        frame.getRootPane().setDefaultButton(confirm);
        frame.setSize(550, 400);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    public static void processMenu(String restaurant) throws IOException{

        // Delete Old Records
        File trashFile = new File("/Users/lukehenry/Documents/Coding /DairyQueen/ID_Prices.txt");
        trashFile.delete();  

        // Create an order for each person: Person 1 is the "Host"
        JFrame[] frame = new JFrame[25];
        for(x = party.length; x > 0; x--) {
            frame[x] = new JFrame("Person " + (x));
        

            // Create a new table model
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Item", "Size", "Price"}, 0);
            JTable table = new JTable();
            table.setModel(model);

            // Read the menu from the file and add it to the table model
            readMenu(model, restaurant);

            // Add a row sorter to the table
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            // Add a search box above the table
            JTextField searchBox = new JTextField();
            searchBox.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    filterTable();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    filterTable();
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    filterTable();
                }
                private void filterTable() {
                    String text = searchBox.getText();
                    if (text.trim().length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + text));
                    }
                }
            });

            // Set the column widths
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(2).setPreferredWidth(25);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);

            
            JScrollPane scrollPane = new JScrollPane(table);
            
            // Add the scroll pane to a panel
            JPanel panel = new JPanel(new BorderLayout());
            // JFrame frame = new JFrame("Menu Reader");
            panel.add(scrollPane, BorderLayout.WEST);
            
            // Create a button to record the selected ID to a file
            JButton button = new JButton("Record ID");

            // Create a label to show the line that is being written
            JLabel label = new JLabel();
            JTextField amountEntry = new JTextField();

            // Button Functionality
            button.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        gatherPersonalData(table, selectedRow, label, amountEntry);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                amountEntry.setText("");
            });

            
            // PANELING
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(button);
            buttonPanel.add(label);

            JButton confirm = new JButton("Confirm");
            JLabel amount = new JLabel("Amount:");

            // Create the two panels
            JPanel eastPanel = new JPanel(new GridLayout(10, 2), false);
            JPanel weastPanel = new JPanel(new GridLayout(1, 2), false);

            

            JLabel tip = new JLabel("Tip: Write 15% as '0.15' (optional)");
            JTextField tipTxt = new JTextField();
            weastPanel.add(tip);
            weastPanel.add(tipTxt);


            // Format grid
            JLabel[] dummyLabels = new JLabel[15];
            for(int i = 0; i < 13; i++) {
                switch(i){
                    case 1: 
                        eastPanel.add(confirm);
                        break;
                    case 8:
                        eastPanel.add(amount);
                        break;
                    case 10:
                        eastPanel.add(amountEntry);
                        break;
                    default:
                        dummyLabels[i] = new JLabel();
                        eastPanel.add(dummyLabels[i]);
                        break;
                }
            }
            // TODO: do you take all the percents individual and then add them up, or do you do one at a time and then chain the percents
            confirm.addActionListener(e -> {x++; frame[x].setVisible(false); try {writeFile();} catch (IOException e1) {e1.printStackTrace();} personalPrice = ""; personalSelection = ""; if(x == party.length) {calculateCost(tipTxt);};});
            panel.add(buttonPanel, BorderLayout.SOUTH);
            
            // Create a panel to hold the two panels side by side
            JPanel sideBySidePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            if(x==1)
            sideBySidePanel.add(weastPanel);
            sideBySidePanel.add(eastPanel);

            // Create a panel to hold the side-by-side panel
            JPanel combinedPanel = new JPanel(new GridLayout(1, 1), false);
            combinedPanel.add(sideBySidePanel);

            panel.add(combinedPanel);
            panel.add(searchBox, BorderLayout.NORTH);
            
            // Add the panel to the frame
            frame[x].add(panel);
            
            // Set the frame size and visibility
            frame[x].getRootPane().setDefaultButton(confirm);
            frame[x].setSize(1000, 400);
            frame[x].setLocationRelativeTo(null);
            frame[x].setVisible(true);
            frame[x].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    private static void calculateCost(JTextField tipTxt) {
        Scanner scan;
        double total = 0;
        JFrame frame = new JFrame("Fees");
        JPanel panel = new JPanel();
        frame.setSize(400, party.length * 60);
        panel.setLayout(null);
        frame.setLocationRelativeTo(null);
        int startYLbl = -105;
        JLabel[] item = new JLabel[25];
        double tip;

        // Check whether or not there was tip
        if(tipTxt.getText().equals("")) {
            tip = 0;
         } else {
            tip = round(Double.parseDouble(tipTxt.getText()),2);
         }
        
        // Calculate each persons total costs, calculate with tax and tip
        try {
            scan = new Scanner(new File("/Users/lukehenry/Documents/Coding /DairyQueen/ID_Prices.txt"));
            for(int i = 0; i < party.length; i++) {
                String[] costs = scan.nextLine().split(",");
                total = 0;
                for(String costString : costs) {
                    double cost = Double.parseDouble(costString);
                    total += cost;
                }
                System.out.println(round((total),2));
                double tax = round((total * 0.06),2);
                tip = round((total * (tip)),2);
                total += tax + tip;
                startYLbl += 30;
                item[i] = new JLabel("Person " + (i+1) + " owes: $" + round(total,2));
                item[i].setBounds(10, startYLbl, 400, 200);
                panel.add(item[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Faulure during calculate cost: FILE ERROR");
        }
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
     }
    private static void writeFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/lukehenry/Documents/Coding /DairyQueen/ID_Prices.txt", "rw");
        raf.seek(raf.length());
        raf.writeBytes(personalPrice);
        raf.writeBytes("\n");
        raf.close();
    }
    private static void gatherPersonalData(JTable table, int selectedRow, JLabel label, JTextField amountEntry) throws IOException {
        Object id = table.getValueAt(selectedRow, 0);
        String prePrice = (String)table.getValueAt(selectedRow, 3);
        double price = Double.parseDouble(prePrice.substring(1));
        int iterations;
            try {
                iterations = Integer.parseInt(amountEntry.getText());
            } catch (Exception z) {iterations = 1;}
            for(int i = 0; i < iterations; i++) {
                String individualSelection = id + ",";
                personalPrice += price + ",";
                update(label,individualSelection);
            }
    }
    private static void update(JLabel label, String individualSelection) {
        personalSelection += individualSelection;
        label.setText(personalSelection);
    }
    private static void readMenu(DefaultTableModel model, String restaurant) {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/lukehenry/Documents/Coding /DairyQueen/" + restaurant + ".txt"))) {
            String line;
            int id = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String item = parts[0];
                String size = "";
                String price = "";
                if (parts.length == 3) {
                    size = parts[1];
                    price = parts[2];
                } else if (parts.length == 2) {
                    price = parts[1];
                }
                model.addRow(new Object[]{id, item, size, price});
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateLabel(JLabel label, int id, int iterations) {
        if (selection == null) {
            selection = "";
        } else {
            selection += "";
        }
        for(int i = 0; i < iterations; i++) {
            selection += id + ",";
        }
        label.setText(selection);
    }
}

