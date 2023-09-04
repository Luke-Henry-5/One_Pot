package DairyQueen;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class jtable extends JFrame {
    
    public jtable() {
        super("Dairy Queen Menu");
        
        // Create a table model with columns "Item", "Size", "Price"
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Size");
        model.addColumn("Price");
        
        // Add Dairy Queen menu items to the table model
        model.addRow(new Object[]{"Blizzard", "Mini (M)", "$3.89"});
        model.addRow(new Object[]{"Blizzard", "Small (S)", "$4.09"});
        model.addRow(new Object[]{"Blizzard", "Medium (M)", "$4.69"});
        model.addRow(new Object[]{"Blizzard", "Large (L)", "$5.59"});
        model.addRow(new Object[]{"DQ Sandwich", "", "$2.49"});
        model.addRow(new Object[]{"Chicken Strip Basket", "4 Piece (S)", "$6.79"});
        model.addRow(new Object[]{"Chicken Strip Basket", "6 Piece (M)", "$7.99"});
        model.addRow(new Object[]{"Cheeseburger", "", "$3.29"});
        model.addRow(new Object[]{"GrillBurger with Cheese", "", "$4.79"});
        model.addRow(new Object[]{"Chicken Bacon Ranch Sandwich", "", "$5.29"});
        model.addRow(new Object[]{"3-Pc. Chicken Strip Meal", "Small (S)", "$6.79"});
        model.addRow(new Object[]{"3-Pc. Chicken Strip Meal", "Medium (M)", "$7.29"});
        model.addRow(new Object[]{"3-Pc. Chicken Strip Meal", "Large (L)", "$7.79"});
        model.addRow(new Object[]{"1/4 lb. GrillBurger", "", "$3.99"});
        model.addRow(new Object[]{"1/2 lb. GrillBurger", "", "$5.29"});
        model.addRow(new Object[]{"1/3 lb. Double with Cheese", "", "$4.99"});
        model.addRow(new Object[]{"Chili Cheese Dog", "", "$2.89"});
        model.addRow(new Object[]{"Chili Cheese Dog Meal", "Small (S)", "$6.09"});
        model.addRow(new Object[]{"Chili Cheese Dog Meal", "Medium (M)", "$6.59"});
        model.addRow(new Object[]{"Chili Cheese Dog Meal", "Large (L)", "$7.09"});
        model.addRow(new Object[]{"Chicken Sandwich", "", "$4.49"});
        model.addRow(new Object[]{"KC BBQ Bacon Cheeseburger", "", "$5.29"});
        model.addRow(new Object[]{"Iron Grilled Cheese", "", "$2.79"});
        model.addRow(new Object[]{"Iron Grilled Supreme BLT", "", "$4.29"});
        model.addRow(new Object[]{"Iron Grilled Supreme", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Southwest", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Bacon Cheeseburger", "", "$4.29"});
        model.addRow(new Object[]{"Iron Grilled Classic Club", "", "$4.29"});
        model.addRow(new Object[]{"Iron Grilled Ranch", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Philly", "", "$4.29"});
        model.addRow(new Object[]{"Iron Grilled Double BLT", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Bacon Ranch", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Club", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Mozzarella", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Philly", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Bacon", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Mushroom", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Ranch", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Chicken Deluxe", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey BLT", "", "$3.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey Club", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey Mozzarella", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey Mushroom", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey Ranch", "", "$4.79"});
        model.addRow(new Object[]{"Iron Grilled Turkey Deluxe", "", "$4.79"});
        model.addRow(new Object[]{"Bacon Cheese GrillBurger", "", "$4.29"});
        model.addRow(new Object[]{"Bacon Double Cheese GrillBurger", "", "$5.29"});
        model.addRow(new Object[]{"1/3 lb. Cheese GrillBurger", "", "$4.49"});
        model.addRow(new Object[]{"1/2 lb. Cheese GrillBurger", "", "$5.79"});
        model.addRow(new Object[]{"3-Pc. Chicken Strip Snack", "", "$3.99"});
        model.addRow(new Object[]{"6-Pc. Chicken Strip Snack", "", "$5.99"});
        model.addRow(new Object[]{"4-Pc. Chicken Snack", "", "$4.99"});
        model.addRow(new Object[]{"Regular French Fries", "", "$2.69"});
        model.addRow(new Object[]{"Large French Fries", "", "$3.09"});
        model.addRow(new Object[]{"Onion Rings", "", "$2.99"});
        model.addRow(new Object[]{"Cheese Curds", "", "$3.99"});
        model.addRow(new Object[]{"Small Soft Drink", "", "$1.89"});
        model.addRow(new Object[]{"Medium Soft Drink", "", "$2.19"});
        model.addRow(new Object[]{"Large Soft Drink", "", "$2.39"});
        model.addRow(new Object[]{"Small Orange Julius", "", "$3.19"});
        model.addRow(new Object[]{"Medium Orange Julius", "", "$3.59"});
        model.addRow(new Object[]{"Large Orange Julius", "", "$3.99"});
        model.addRow(new Object[]{"DQ Shake", "Mini (M)", "$2.89"});
        model.addRow(new Object[]{"DQ Shake", "Small (S)", "$3.09"});
        model.addRow(new Object[]{"DQ Shake", "Medium (M)", "$3.89"});
        model.addRow(new Object[]{"DQ Shake", "Large (L)", "$4.09"});
        model.addRow(new Object[]{"Arctic Rush", "Mini (M)", "$1.99"});
        model.addRow(new Object[]{"Arctic Rush", "Small (S)", "$2.29"});
        model.addRow(new Object[]{"Arctic Rush", "Medium (M)", "$2.69"});
        model.addRow(new Object[]{"Arctic Rush", "Large (L)", "$3.09"});
        model.addRow(new Object[]{"Misty Slush", "Small (S)", "$1.99"});
        model.addRow(new Object[]{"Misty Slush", "Medium (M)", "$2.69"});
        model.addRow(new Object[]{"Misty Slush", "Large (L)", "$3.09"});
        model.addRow(new Object[]{"Premium Fruit Smoothie", "Small (S)", "$3.89"});
        model.addRow(new Object[]{"Premium Fruit Smoothie", "Medium (M)", "$4.39"});
        model.addRow(new Object[]{"Premium Fruit Smoothie", "Large (L)", "$4.89"});
        model.addRow(new Object[]{"DQ Bakes! Hot Desserts a la Mode", "Fudge Stuffed Cookie", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Hot Desserts a la Mode", "Apple Tart", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Hot Desserts a la Mode", "Triple Chocolate Brownie", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "Chocolate", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "Caramel", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "Strawberry", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "Peanut Butter", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "Chocolate Chip Cookie Dough", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "OREO", "$3.69"});
        model.addRow(new Object[]{"DQ Bakes! Waffle Bowl Sundae", "REESE'S Peanut Butter Cup", "$3.69"});

        
        // Create a JTable with the table model
        JTable table = new JTable(model);

        // Set the column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add the scroll pane to a panel
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        
        // Add the panel to the frame
        add(panel);
        

        // Set the frame size and visibility
        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new jtable();
    }
}


