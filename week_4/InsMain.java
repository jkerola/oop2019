import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.NumberFormatException;

//viikkotehtävä 4, olio-ohjelmointi 2019
//tekijä Janne Kerola, opisknro 2311849

class InsMain extends JFrame implements ActionListener{
    //Init Container for insurance info
    InsInfoContainer container = new InsInfoContainer();
    boolean filterState = true;

    //variables needed for the GUI
    BoxLayout bgLayout;
    JPanel background, panel, print;
    JScrollPane scrollBar;
    JButton addBtn, searchBtn;
    JRadioButton greaterThan, lessThan;
    JLabel typeLbl, locLbl, priceLbl, filterLbl, filler;
    JTextField typeFld, locFld, priceFld, filterFld;
    JTextArea printBox;
    String credits = "Tekijä Janne Kerola, opisknro 2311849\n\n";
    String guide = "Etsi tyhjällä tulostaaksesi kaikki tiedot.\n";

    //constructor
    public InsMain(){
        //window settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setTitle("Insurance Inspector");
        setResizable(true);
        background = new JPanel();
        BoxLayout bgLayout = new BoxLayout(background, BoxLayout.PAGE_AXIS);
        add(background);
        
        //panel settings
        panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        background.add(panel);

        //printing area
        print = new JPanel(new GridBagLayout());
        background.add(print);

        //-----------  window elements ---------------

        //top panel
        //type
        c.anchor = GridBagConstraints.WEST;
        c.ipadx = 5;
        c.ipady = 2;
        c.gridx = 0;
        c.gridy = 1;
        typeLbl = new JLabel("Kiinteistön tyyppi: ");
        panel.add(typeLbl, c);

        c.gridx = 1;
        typeFld = new JTextField(10);
        panel.add(typeFld, c);

        //location
        c.gridx = 0;
        c.gridy = 0;
        locLbl = new JLabel("Kiinteistön osoite: ");
        panel.add(locLbl, c);

        c.gridx = 1;
        locFld = new JTextField(10);
        panel.add(locFld, c);

        //price
        c.gridx = 0;
        c.gridy = 2;
        priceLbl = new JLabel("Vakuutusarvo: ");
        panel.add(priceLbl, c);

        c.gridx = 1;
        priceFld = new JTextField(10);
        panel.add(priceFld, c);
        c.gridx = 2;
        panel.add(new JLabel("euroa"), c);
        
        //Filler
        c.gridy = 1;
        c.gridx = 2;
        filler = new JLabel("");
        panel.add(filler, c);

        //confirm button
        c.gridy = 4;
        c.gridx = 1;
        addBtn = new JButton("Lisää");
        addBtn.addActionListener(this);
        panel.add(addBtn, c);    

        //filler
        c.gridx = 0;
        c.gridy = 5;
        panel.add(filler, c);
        c.gridy = 6;
        panel.add(filler, c);

        //------------ Search and print ---------------
        //filter
        c.gridy = 7;
        filterLbl = new JLabel("Etsi vakuutusarvolla: ");
        panel.add(filterLbl, c);
        
        c.gridx = 2;
        filterFld = new JTextField(5);
        panel.add(filterFld, c);
        c.gridx = 3;
        panel.add(new JLabel("   euroa"), c);
        
        //radio buttons
        ButtonGroup radioGroup = new ButtonGroup();
        c.gridx = 1;
        greaterThan = new JRadioButton("suuremmat kuin >=", true);
        radioGroup.add(greaterThan);
        greaterThan.addActionListener(this);
        panel.add(greaterThan, c);
        
        c.gridy = 8;
        lessThan = new JRadioButton("pienemmät kuin <=");
        radioGroup.add(lessThan);
        lessThan.addActionListener(this);
        panel.add(lessThan, c);
        
        
        //search button
        c.gridy = 8;
        c.gridx = 0;
        searchBtn = new JButton("Etsi ja tulosta");
        searchBtn.addActionListener(this);
        panel.add(searchBtn, c);
        
        // ------------- Print window ---------------

        //text box
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        printBox = new JTextArea(credits, 12, 35);
        printBox.append(guide);
        printBox.setEditable(false);
        printBox.setLineWrap(true);
        printBox.setWrapStyleWord(true);
        scrollBar = new JScrollPane(printBox);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        print.add(scrollBar, c);

    }
    //function checks what buttons where pressed and calls appropriate methods
    public void actionPerformed(ActionEvent btnPress){
        //calls methods related to creating new info
        if(btnPress.getSource() == addBtn){
            //exception handling
            try{
                //temp variables for calling methods
                double value = Double.parseDouble(priceFld.getText());
                String loc = locFld.getText();
                String type = typeFld.getText();
                //container contains methods for creating objects
                //and adding them to the protected database ArrayList
                container.createInsuranceInfo(loc, type, value);
                //clear inputs
                priceFld.setText("");
                locFld.setText("");
                typeFld.setText("");
                printBox.append("\nUusi kohde lisätty!");
            } catch(NumberFormatException e){
                printBox.setText("Vakuutusarvo täytyy olla numero.\n");
            }
        //calls methods related to sorting/printing out info
        } else if(btnPress.getSource() == searchBtn){
            //same error handling
            try{
                double filterValue = Double.parseDouble(filterFld.getText());
                if(filterState == true){
                    printBox.setText("Etsitään tietokannasta hakuehdoilla vakuutusarvo >= " + filterValue + " euroa\n" + guide);
                } else {
                    printBox.setText("Etsitään tietokannasta hakuehdoilla vakuutusarvo <= " + filterValue + " euroa\n" + guide);
                }
                //calls InsInfoContainers method which inturn triggers
                //sorting methods etc. inside the container
                //returns a string with all info formatted
                String printedInfo = container.parsePrint(false, filterState, filterValue);
                if(printedInfo.equals("")){
                    //incase print is called for an empty database
                    printBox.append("Tietokannasta ei löytynyt hakuehtoja vastaavia kohteita.\n");
                } else {
                    printBox.append(printedInfo);
                }
            } catch(NumberFormatException n){
                //exception for when the filter field is empty, prints all info
                if(filterFld.getText().equals("")){
                    printBox.setText("Tulostetaan kaikki tiedot...\n");
                    printBox.append(container.parsePrint(true, filterState, 0));
                }else {
                    //error messages
                    printBox.setText("Ohjelma ei kykene seulomaan tietoja arvolla " + "'" + filterFld.getText() + "'.\n");
                    printBox.append(guide);
                }
            }
        //radio buttons toggle a boolean value that gets passed on to
        //insinfo container class for sorting purposes
        } else if(btnPress.getSource() == lessThan){
            filterState = false;
        } else if(btnPress.getSource() == greaterThan){
            filterState = true;
        }
    }
    public static void main(String [] args){
        //main function, starts GUI
        new InsMain().setVisible(true);

    }
}