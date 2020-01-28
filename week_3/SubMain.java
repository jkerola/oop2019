//imports, GUI requires a lot
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import javax.swing.*;

// Janne Kerola, opisknro 2311849

class SubMain extends JFrame implements ItemListener, ActionListener{
    //init base subscription object that holds lists for further subscriptions
    Subscription subs = new Subscription("", "", "", 0);
    //variables for GUI
    JPanel bgPanel = new JPanel();
    BoxLayout bgLayout = new BoxLayout(bgPanel, BoxLayout.PAGE_AXIS);
    JButton addButton, printButton;
    JLabel nameLabel, addrLabel, magLabel, priceLabel, standinSubLabel, monthLabel, saleLabel;
    JTextField nameField, addrField, magField, priceField, monthField, saleField;
    JCheckBox standinSubCheck;
    JTextArea printBox;
    public boolean standCheck = false;

    public void printSubscriptionInvoice(Subscription subs){
        //printing function.
        //couldn't figure out how pass objects as multiple
        //choice parameters in time, so it prints both
        ArrayList<RegularSubscription> regList = subs.getRegSubs();
        ArrayList<StandinSubscription> standList = subs.getStandSubs();
        printBox.setText("Tulostetaan tilaustiedot\n\n");
        for(StandinSubscription n:standList){
            //System.out.println(n.printInvoice());
            printBox.append(n.printInvoice());
        }
        for(RegularSubscription m:regList){
            //System.out.println(m.printInvoice());
            printBox.append(m.printInvoice());
        }
        printBox.setCaretPosition(0);
    }



    public SubMain(){
        //window properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Subscription Manager");
        setSize(800, 400);
        setResizable(true);
        bgPanel.setLayout(bgLayout);
        add(bgPanel);

        //panel magic
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        bgPanel.add(topPanel);
        JPanel botPanel = new JPanel(new GridBagLayout());
        bgPanel.add(botPanel);
        
        //components
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 5;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        JLabel topTitle = new JLabel("Lehden tiedot");
        topPanel.add(topTitle, c);
        
        //filler
        c.gridy = 1;
        topPanel.add(new JLabel(" "), c);
        
        //magazine label
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        magLabel = new JLabel("Lehden nimi:");
        topPanel.add(magLabel, c);
        
        //magazine textfield
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.weightx = 0;
        magField = new JTextField(10);
        topPanel.add(magField, c);
        
        //standin subscription label
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 2;
        c.weightx = 1;
        standinSubLabel = new JLabel("Kestotilaus:");
        topPanel.add(standinSubLabel, c);

        //checkbox
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 3;
        standinSubCheck = new JCheckBox();
        standinSubCheck.addItemListener(this);
        topPanel.add(standinSubCheck, c);
        
        //price label
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 3;
        priceLabel = new JLabel("Hinta (e/kk):");
        topPanel.add(priceLabel, c);

        //price field
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.weightx = 0;
        priceField = new JTextField(4);
        topPanel.add(priceField, c);

        //duration label
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 2;
        c.weightx = 1;
        monthLabel = new JLabel("Tilauksen kesto (kk):");
        topPanel.add(monthLabel, c);

        //alternative  sale label
        saleLabel = new JLabel("Alennusprosentti (%):");
        saleLabel.setVisible(false);
        topPanel.add(saleLabel, c);

        //duration field
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 3;
        c.weightx = 0;
        monthField = new JTextField(4);
        topPanel.add(monthField, c);

        //alternative sale field
        saleField = new JTextField(4);
        saleField.setVisible(false);
        topPanel.add(saleField, c);

        //filler
        c.gridy = 4;
        topPanel.add(new JLabel(" "), c);
        c.gridy = 5;
        topPanel.add(new JLabel(" "), c);
        
        //midpanel
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 1;
        JLabel title2 = new JLabel("Asiakkaan tiedot");
        topPanel.add(title2, c);
        
        //filler
        c.gridy = 7;
        topPanel.add(new JLabel(" "), c);

        //client name label
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 8;
        nameLabel = new JLabel("Tilaajan nimi:");
        topPanel.add(nameLabel, c);

        //client name field
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.weightx = 0;
        nameField = new JTextField(10);
        topPanel.add(nameField, c);
        
        //delivery addr label
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 2;
        c.weightx = 1;
        addrLabel = new JLabel("Toimitusosoite:");
        topPanel.add(addrLabel, c);

        //delivery addr field
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 3;
        c.weightx = 0;
        addrField = new JTextField(10);
        topPanel.add(addrField, c);
        
        // filler
        c.gridy = 9;
        topPanel.add(new JLabel(" "), c);

        //some advanced filler
        c.gridy = 10;
        c.gridx = 0;
        c.gridwidth = 3;
        c.weightx = 1;
        topPanel.add(new JLabel(" "), c);

        //-------------buttons--------------
        //Add new
        c.gridx = 2;
        c.weightx = 0;
        addButton = new JButton("Lisää");
        addButton.addActionListener(this);
        topPanel.add(addButton, c);

        //print database
        c.gridx = 3;
        printButton = new JButton("Tulosta");
        printButton.addActionListener(this);
        topPanel.add(printButton, c);

        //printing area
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridy = 11;
        
        //-------------textbox---------------
        //textbox for detailed info (errors, confirms, print...)
        c.fill = GridBagConstraints.HORIZONTAL;
        printBox = new JTextArea("", 8, 50);
        printBox.setLineWrap(true);
        printBox.setWrapStyleWord(true);
        printBox.setText("Tekijä: Janne Kerola, opisknro 2311849\n");
        botPanel.add(printBox, c);
        JScrollPane printScroll = new JScrollPane(printBox);
        printScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        c.gridx = 3;
        c.weightx = 0;
        botPanel.add(printScroll, c);

    }
    ///--------button function-----------
    public void actionPerformed(ActionEvent m){
        //variables
        String name, add, mag, text;
        int dur, sale;
        double price;
        //check which button was pressed
        if(m.getSource() == addButton){
            //try catch for wrongful input
            try{
                name = nameField.getText();
                add = addrField.getText();
                mag = magField.getText();
                price = Double.parseDouble(priceField.getText());
                //check if standinsub checkbox is checked
                if(standCheck == true){
                    sale = Integer.parseInt(saleField.getText());
                    subs.setStandSub(mag, name, add, price, sale);
                    text = "Kestotilaus " + mag + " lisätty!\n";
                    printBox.append(text);
                } else {
                    dur = Integer.parseInt(monthField.getText());
                    subs.setRegSub(mag, name, add, price, dur);
                    text = "Normaalitilaus " + mag + " lisätty!\n";
                    printBox.append(text);
                }
            }catch(NumberFormatException virhe){
                //catch wrongful input, throw error on app
                //System.out.println("Virhe napattu!");
                printBox.append("Tarkista syöte! Et tarvitse erikoismerkkejä kuten € tai kk. Alennusprosentti ja tilauksen kesto ovat kokonaislukuja.\n");
                printBox.setCaretPosition(printBox.getDocument().getLength());
            }
        } else if(m.getSource() == printButton){
            //print button calls print function
            printSubscriptionInvoice(subs);
        }

    }
    //------------checkbox---------------------
    //checkbox hides/unhides alternative fields
    public void itemStateChanged(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED){
            //disable month box
            standCheck = true;
            monthField.setVisible(false);
            monthField.setText("");
            monthField.setEditable(false);
            monthLabel.setVisible(false);
            //enable sale box
            saleLabel.setVisible(true);
            saleField.setVisible(true);
            saleField.setEditable(true);
        } else {
            //enable month box
            standCheck = false;
            monthField.setVisible(true);
            monthField.setEditable(true);
            monthLabel.setVisible(true);
            //disable sale box
            saleLabel.setVisible(false);
            saleField.setVisible(false);
            saleField.setText("");
            saleField.setEditable(false);
        }
    }
    //main function which boots up the GUI
    public static void main(String[] args){
        //setVisible called only when needed
        new SubMain().setVisible(true);
    }
}