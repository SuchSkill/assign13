package mvc;// PEK-MVCList/MainPanel.java
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class MainPanel extends    JPanel
                implements ListSelectionListener {

    JTextArea        infoArea;
    DefaultListModel model;
    JList            list;
    int              counter;

    MainPanel() {
        model = new DefaultListModel();
        model.addElement(new Person("Monroe",27));
        model.addElement(new Person("Novak",21));
        model.addElement(new Person("Dunaway",18));
        model.addElement(new Person("Hepburn",46));
        model.addElement(new Person("Minelli",43));
        model.addElement(new Person("Garbo",32));

        list = new JList(model);
        list.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setFont(new Font("Serif",Font.BOLD,16));

        JScrollPane scrollPers =
            new JScrollPane(list,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        infoArea = new JTextArea(10,14);
        infoArea.setFont(new Font("Serif",Font.BOLD,16));
        infoArea.setBackground(Color.blue);
        infoArea.setForeground(Color.white);
        JScrollPane scrollInfo =
            new JScrollPane(infoArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JSplitPane split =
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                           scrollInfo,scrollPers);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(0.40);

        int   width = 200, height = 100;
        float xjust = Component.CENTER_ALIGNMENT;

        JButton bRem = new JButton(
            new AbstractAction("Remove") {
                public void actionPerformed(ActionEvent e) {
                    if (list.isSelectionEmpty()) return;
                    int i = list.getSelectedIndex();
                    model.remove(i);
                    if (!model.isEmpty()) select(
                            Math.min(i,model.getSize()-1));
                }
            }
        );
        configButton(bRem,width,height,xjust);

        JButton bAddAfter = new JButton(
            new AbstractAction("Add after") {
                public void actionPerformed(ActionEvent e) {
                    if (list.isSelectionEmpty()) return;
                    int i = list.getSelectedIndex();
                    model.add(i+1,new Person("NewPerson" +
                                    ++counter,3*counter));
                    select(i);
                }
            }
        );
        configButton(bAddAfter,width,height,xjust);

        JButton bAddBefore =
            new JButton(new AbstractAction("Add before") {
                public void actionPerformed(ActionEvent e) {
                    if (list.isSelectionEmpty()) return;
                    int i = list.getSelectedIndex();
                    model.add(i,new Person("NewPerson" +
                                    ++counter,3*counter));
                    select(i);
                }
            }
        );
        configButton(bAddBefore,width,height,xjust);

        JButton bAddEnd = new JButton(
            new AbstractAction("Add at end") {
                public void actionPerformed(ActionEvent e) {
                    model.addElement(new Person("NewPerson"+
                                    ++counter,3*counter));
                    select(model.getSize()-1);
                }
            }
        );
        configButton(bAddEnd,width,height,xjust);

        JButton bInsert = new JButton(
            new AbstractAction("Insert here") {
                public void actionPerformed(ActionEvent e) {
                    if (list.isSelectionEmpty()) return;
                    int i = list.getSelectedIndex();
                    model.set(i,new Person("NewPerson" +
                                    ++counter,3*counter));
                    select(i);
                }
            }
        );
        configButton(bInsert,width,height,xjust);

        JPanel box = new JPanel();
        Dimension rig = new Dimension(1,5);
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        box.add(Box.createVerticalGlue());
        box.add(bRem);
        box.add(Box.createRigidArea(rig));
        box.add(bAddAfter);
        box.add(Box.createRigidArea(rig));
        box.add(bAddBefore);
        box.add(Box.createRigidArea(rig));
        box.add(bAddEnd);
        box.add(Box.createRigidArea(rig));
        box.add(bInsert);
        box.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        add(box,  BorderLayout.EAST);
        add(split,BorderLayout.CENTER);

        select(0);
    }

    void configButton(JButton b, int w, int h, float just) {
        b.setMaximumSize(new Dimension(w,h));
        b.setAlignmentX(just);
    }

    void select(int i) {
        list.ensureIndexIsVisible(i);
        list.setSelectedIndex(i);
        infoArea.setText(
                ((Person)model.getElementAt(i)).getData());
        infoArea.setCaretPosition(0);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() ||
            list.isSelectionEmpty()) return;
        select(list.getSelectedIndex());
    }
}
