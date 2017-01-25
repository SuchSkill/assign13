package tabel;// PEO-Table/tabel.MyTabModel.java
 
import java.awt.Color;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MyTabModel extends AbstractTableModel {

    ArrayList<Class>  classes = new ArrayList<>();
    ArrayList<String> colname = new ArrayList<>();

    ArrayList<String>  imie = new ArrayList<>();
    ArrayList<String>  nazw = new ArrayList<>();
    ArrayList<Date>    date = new ArrayList<>();
    ArrayList<Color>   colo = new ArrayList<>();
    ArrayList<Integer> wzro = new ArrayList<>();
    ArrayList<Integer> waga = new ArrayList<>();
    ArrayList<Integer>  bmi = new ArrayList<>();

    ArrayList<Object>  data = new ArrayList<>();

    Calendar cal = Calendar.getInstance();
    int ilerows = 0;

    private void readData() {
        try {
            classes.add(Class.forName("java.lang.String"));
            classes.add(Class.forName("java.lang.String"));
            classes.add(Class.forName("java.util.Date"));
            classes.add(Class.forName("java.awt.Color"));
            classes.add(Class.forName("java.lang.Integer"));
            classes.add(Class.forName("java.lang.Integer"));
            classes.add(Class.forName("java.lang.Integer"));
            colname.add("Imie");
            colname.add("Nazwisko");
            colname.add("Dzie\u0144 ur.");
            colname.add("Ulub. kolor");
            colname.add("Wzrost[cm]");
            colname.add("Waga[kg]");
            colname.add("BMI");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Path path = Paths.get("data.dat");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path,
                          Charset.forName("UTF-8"));
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        ilerows = lines.size();
        for (String line : lines) {
            String[] fields = line.split("\\s+");
            imie.add(fields[0]);
            nazw.add(fields[1]);
            String[] rmd = fields[2].split("-");
            int rok = Integer.parseInt(rmd[0]);
            int mie = Integer.parseInt(rmd[1]);
            int dzi = Integer.parseInt(rmd[2]);
            cal.set(rok,mie,dzi);
            date.add(cal.getTime());
            String[] cols = fields[3].split(",");
            Color c = new Color(Integer.parseInt(cols[0]),
                                Integer.parseInt(cols[1]),
                                Integer.parseInt(cols[2]));
            colo.add(c);
            int wz = Integer.parseInt(fields[4]);
            int wa = Integer.parseInt(fields[5]);
            wzro.add(wz);
            waga.add(wa);
            double wzr = wz/100.;
            double b = (double)wa/(wzr*wzr);

            bmi.add((int)((int)(Math.round(b))));
        }
        data.add(imie);
        data.add(nazw);
        data.add(date);
        data.add(colo);
        data.add(wzro);
        data.add(waga);
        data.add( bmi);
    }

    public MyTabModel() {
        readData();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return ((ArrayList)data.get(c)).get(r);
    }

    @Override
    public String getColumnName(int c) {
        return colname.get(c);
    }

    @Override
    public int getRowCount() {
        return ilerows;
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return classes.get(c);
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return false;
    }

    public static void main(String... args) {
        new MyTabModel();
    }
}
