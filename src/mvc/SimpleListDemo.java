package mvc;// PEK-MVCList/SimpleListDemo.java
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SimpleListDemo extends JFrame {
    public static void main(String[] args) {
        new SimpleListDemo();
    }

    public SimpleListDemo() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("A simple, modifiable list");
        setContentPane(new MainPanel());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                pack();
                setVisible(true);
            }
        });
    }
}
