package tabel;// PEO-Table/tabel.MyColorCellRend.java
 
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyColorCellRend extends JLabel
                             implements TableCellRenderer {
    public MyColorCellRend() {
        setOpaque(true);
    }

    public Component
    getTableCellRendererComponent(
            JTable table, Object color, boolean isSelected,
            boolean hasFocus, int row, int column) {
        setBackground((Color)color);
        return this;
    }
}

