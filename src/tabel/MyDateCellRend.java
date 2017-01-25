package tabel;// PEO-Table/tabel.MyDateCellRend.java
 
import java.awt.Component;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyDateCellRend extends JLabel
                            implements TableCellRenderer {
    public MyDateCellRend() {
        setOpaque(true);
        setBackground(java.awt.Color.WHITE);
    }

    public Component
    getTableCellRendererComponent(
            JTable table, Object val, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Date d = (Date)val;
        setHorizontalAlignment(JLabel.CENTER);
        setText(new SimpleDateFormat("EEEE",
                    Locale.FRENCH).format(d));
        return this;
    }
}

