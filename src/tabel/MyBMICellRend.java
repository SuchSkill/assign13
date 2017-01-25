package tabel;// PEO-Table/tabel.MyBMICellRend.java
 
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyBMICellRend extends JLabel
                           implements TableCellRenderer {
    public MyBMICellRend() {
        setOpaque(true);
    }

    public Component
    getTableCellRendererComponent(
            JTable table, Object val, boolean isSelected,
            boolean hasFocus, int row, int column) {
        int b = (Integer)val;
        if (b <= 18)
            setBackground(Color.YELLOW);
        else if (b >= 25)
            setBackground(Color.RED);
        else
            setBackground(Color.WHITE);
        setHorizontalAlignment(JLabel.CENTER);
        setText(""+b);
        return this;
    }
}

