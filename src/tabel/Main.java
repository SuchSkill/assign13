package tabel;// PEO-Table/Main.java
 
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;

import static javax.swing.JScrollPane.*;

public class Main {
    public static void main(String... args) {
        JTable table = new JTable(new MyTabModel());
        table.setDefaultRenderer(java.awt.Color.class,
                new MyColorCellRend());
        table.setDefaultRenderer(java.util.Date.class,
                new MyDateCellRend());
        table.getColumn("BMI").
            setCellRenderer(new MyBMICellRend());
          // God knows why that's needed; otherwise
          // table does not fill the whole scroll...
        table.setPreferredScrollableViewportSize(
                        table.getPreferredSize());
        JScrollPane scroll = new JScrollPane(table,
                VERTICAL_SCROLLBAR_ALWAYS,
                HORIZONTAL_SCROLLBAR_ALWAYS);
        JFrame f = new JFrame("Table");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(scroll);
        f.pack();
        f.setVisible(true);
    }
}
