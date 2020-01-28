import javax.swing.*;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;

//Janne Kerola, opisknro 2311849

class NaapuritMain extends JFrame implements ItemListener, ActionListener{
    //variables for the main program
    ArrayList<Tontti> lotList = new ArrayList<Tontti>();
    ArrayList<Tontti> finLotList = new ArrayList<Tontti>();
    String credits = "Tekijä Janne Kerola, opisknro 2311849\n";
    String guide = "Täytä huoneistot kasvavassa järjestyksessä!\n";

    //variables for the GUI
    JTabbedPane tabPanel;
    JPanel lotPanel, buildPanel, apPanel, infoPanel;
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    //lotpanel
    JLabel filler, lotNameLbl, lotAddLbl, lotAreaLbl;
    JTextArea lotArea;
    JTextField lotNameFld, lotAddFld, lotAreaFld;
    JButton lotCreateBtn;
    JScrollPane lotScroll;
    
    //building panel
    JComboBox lotBox;
    JLabel buildRoomsLbl, buildLotLbl, buildBuildLbl;
    JTextField buildRoomsFld;
    JRadioButton oma, rivi, kerros;
    JButton buildCreateBtn, buildLoadLotBtn;
    JTextArea buildArea;
    ButtonGroup typeButtons;
    JScrollPane buildScroll;

    //apartment panel
    JLabel apLbl, apTenantLbl, apAreaLbl, apRoomLbl, apLotLbl;
    JTextField apTenantFld, apAreaFld, apRoomFld;
    JTextArea apArea;
    JComboBox apComboBox, lotBox2;
    JButton apCreateBtn, apTenantBtn;
    JScrollPane apScroll;

    //infopanel
    JTextArea printArea;
    JScrollPane printScroll;
    JButton printBtn;

    
    public NaapuritMain(){
        setSize(600, 500);
        setTitle("Naapurusto Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        tabPanel = new JTabbedPane();
        add(tabPanel);


        //--------- panel tabs ---------
        lotPanel = new JPanel();
        tabPanel.addTab("Tontti", null, lotPanel, "Tontin tiedot");
        buildPanel = new JPanel();
        tabPanel.addTab("Rakennus", null, buildPanel, "Rakennuksen tiedot");
        apPanel = new JPanel();
        tabPanel.addTab("Asunnot", null, apPanel, "Asuntojen tiedot");
        infoPanel = new JPanel();
        tabPanel.addTab("Tiedot", null, infoPanel, "Tulosta tiedot");


        //-------- lot panel --------
        lotPanel.setLayout(layout);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        lotPanel.add(new JLabel("Tontin tiedot"), c);
        
        //filler
        c.gridy = 1;
        filler = new JLabel("    ");
        lotPanel.add(filler, c);
        
        //name
        c.gridy = 2;
        lotNameLbl = new JLabel("Tontin nimi:");
        lotPanel.add(lotNameLbl, c);

        c.gridx = 1;
        lotNameFld = new JTextField(10);
        lotPanel.add(lotNameFld, c);

        //address
        c.gridx = 2;
        lotAddLbl = new JLabel("Osoite:");
        lotPanel.add(lotAddLbl, c);

        c.gridx = 3;
        lotAddFld = new JTextField(10);
        lotPanel.add(lotAddFld, c);


        //area
        c.gridy = 3;
        c.gridx = 0;
        lotAreaLbl = new JLabel("Tontin koko:");
        lotPanel.add(lotAreaLbl, c);

        c.gridx = 1;
        lotAreaFld = new JTextField(10);
        lotPanel.add(lotAreaFld, c);

        //button
        c.gridy = 4;
        c.gridx = 3;
        lotCreateBtn = new JButton("Luo tontti");
        lotCreateBtn.addActionListener(this);
        lotPanel.add(lotCreateBtn, c);

        //new lot message area
        c.gridy = 5;
        c.gridx = 1;
        lotArea = new JTextArea(4, 20);
        lotArea.setLineWrap(true);
        lotArea.setWrapStyleWord(true);
        lotArea.setEditable(false);
        lotScroll = new JScrollPane(lotArea);
        lotPanel.add(lotScroll, c);
        lotArea.setText(credits);
        

        //------------ BUILDING PANEL ------------
        buildPanel.setLayout(layout);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        buildBuildLbl = new JLabel("Rakennuksen tiedot:");
        buildPanel.add(buildBuildLbl, c);
        

        //first combobox
        c.gridy = 0;
        c.gridx = 1;
        lotBox = new JComboBox();
        lotBox.addItemListener(this);
        buildPanel.add(lotBox, c);

        //rooms
        c.gridy = 1;
        c.gridx = 0;
        buildRoomsLbl = new JLabel("Asuntojen määrä:");
        buildPanel.add(buildRoomsLbl, c);

        c.gridx = 1;
        buildRoomsFld = new JTextField(4);
        buildPanel.add(buildRoomsFld, c);

        //type radio buttons
        typeButtons = new ButtonGroup();
        
        c.gridx = 0;
        c.gridy = 2;
        oma = new JRadioButton("Omakotitalo", true);
        typeButtons.add(oma);
        buildPanel.add(oma, c);
        
        c.gridy = 3;
        rivi = new JRadioButton("Rivitalo");
        typeButtons.add(rivi);
        buildPanel.add(rivi, c);
        
        c.gridy = 4;
        kerros = new JRadioButton("Kerrostalo");
        typeButtons.add(kerros);
        buildPanel.add(kerros, c);
        
        
        //button
        c.gridx = 1;
        c.gridy = 3;
        buildCreateBtn = new JButton("Luo Rakennus");
        buildCreateBtn.addActionListener(this);
        buildCreateBtn.setVisible(false);
        buildPanel.add(buildCreateBtn, c);

        //info box
        c.gridx = 0;
        c.gridy = 6;
        buildArea = new JTextArea(3, 20);
        buildArea.setEditable(false);
        buildArea.setWrapStyleWord(true);
        buildArea.setLineWrap(true);
        buildScroll = new JScrollPane(buildArea);
        buildPanel.add(buildScroll, c);

        //------------ APARTMENT PANEL ------------
        apPanel.setLayout(layout);

        //second comboBox
        c.gridx = 0;
        c.gridy = 0;
        apLotLbl = new JLabel("Valitse tontti:");
        apPanel.add(apLotLbl, c);

        c.gridx = 1;
        lotBox2 = new JComboBox();
        lotBox2.addItemListener(this);
        apPanel.add(lotBox2, c);
        
        //third combobox
        c.gridx = 0;
        c.gridy = 1;
        apLbl = new JLabel("Asunnon numero:");
        apPanel.add(apLbl, c);

        c.gridx = 1;
        c.gridy = 1;
        apComboBox = new JComboBox();
        apPanel.add(apComboBox, c);
        

        //number of rooms
        c.gridy = 1;
        c.gridx = 2;
        apRoomLbl = new JLabel("Huoneita:");
        apPanel.add(apRoomLbl, c);
        
        c.gridx = 3;
        apRoomFld = new JTextField(4);
        apPanel.add(apRoomFld, c);

        //area
        c.gridx = 2;
        c.gridy = 3;
        apAreaLbl = new JLabel("Pinta-ala:");
        apPanel.add(apAreaLbl, c);
        
        c.gridx = 3;
        apAreaFld = new JTextField(4);
        apPanel.add(apAreaFld, c);
        
        //button
        c.gridy = 4;
        apCreateBtn = new JButton("Uusi Asunto");
        apCreateBtn.addActionListener(this);
        apCreateBtn.setVisible(false);
        apPanel.add(apCreateBtn, c);
        
        
        //info box
        c.gridx = 0;
        c.gridy = 7;
        apArea = new JTextArea(3, 20);
        apArea.setEditable(false);
        apArea.setWrapStyleWord(true);
        apArea.setLineWrap(true);
        apScroll = new JScrollPane(apArea);
        apPanel.add(apScroll, c);
        apArea.setText(guide);
        
        //tenantinfi
        c.gridy = 5;
        apTenantLbl = new JLabel("Asukkaan nimi: ");
        apPanel.add(apTenantLbl, c);

        c.gridx = 1;
        apTenantFld = new JTextField(10);
        apPanel.add(apTenantFld, c);

        //tenantbutton
        c.gridx = 0;
        c.gridy = 6;
        apTenantBtn = new JButton("Lisää asukas asuntoon");
        apTenantBtn.setVisible(false);
        apTenantBtn.addActionListener(this);
        apPanel.add(apTenantBtn, c);
        

        //------------ INFO PANEL ------------
        infoPanel.setLayout(layout);
        c.gridx = 0;
        c.gridy = 0;
        printBtn = new JButton("Tulosta tiedot");
        printBtn.addActionListener(this);
        infoPanel.add(printBtn, c);
        c.gridy = 1;
        c.fill = GridBagConstraints.PAGE_END;
        printArea = new JTextArea(20, 45);
        printScroll = new JScrollPane(printArea);
        printArea.setEditable(false);
        printArea.setWrapStyleWord(true);
        printArea.setLineWrap(true);
        infoPanel.add(printScroll, c);
        printArea.setText(credits);
        

    }
    //checks which building/lot is selected
    public void itemStateChanged(ItemEvent box){
        //recreate apartment box when building is selected
        if(box.getSource() == lotBox2){
            if(box.getStateChange() == ItemEvent.SELECTED){
            }
            if(apComboBox.getItemCount() > 0){
                apComboBox.removeAllItems();
            }
            int nums = 0;
            for(int i=0; i < finLotList.size(); i++){
                if(box.getItem() == finLotList.get(i).getName()){
                    nums = finLotList.get(i).getBuilding().getNumberOfApartments();
                }
            }
            for(int i=0; i < nums; i++){
                apComboBox.addItem(i + 1 + "");
            }
        }
            apPanel.validate();
            apPanel.repaint();
    }
    //check what button was pressed
    public void actionPerformed(ActionEvent btnPress){
        //create a lot
        if(btnPress.getSource() == lotCreateBtn){
            try{
                String name = lotNameFld.getText();
                String add = lotAddFld.getText();
                double area = Double.parseDouble(lotAreaFld.getText());
                if(area < 0){
                    throw new NumberFormatException();
                }
                lotList.add(new Tontti(name, add, area));

                //emtpy fields
                lotNameFld.setText("");
                lotAddFld.setText("");
                lotAreaFld.setText("");

                //delete list items
                if(lotBox.getItemCount() > 0){
                    lotBox.removeAllItems();
                }
                //recreate list
                for(int i=0; i<lotList.size(); i++){
                    lotBox.addItem(lotList.get(i).getName());
                }

                buildPanel.validate();
                buildCreateBtn.setVisible(true);
                buildPanel.repaint();
                lotArea.append("Uusi tontti " + name + " luotu!\n");
            } catch(NumberFormatException e){
                lotArea.append("Tarkista syöte! Pinta-ala ei saa sisältää kirjaimia tai olla negatiivinen.\n");
            }
        }
        //create a building for the lot
        if(btnPress.getSource() == buildCreateBtn){
            try{
                int apartments = Integer.parseInt(buildRoomsFld.getText());
                if(apartments < 0){
                    throw new NumberFormatException();
                }
                //ugly solution, figured out a better one later
                //leaving this in because it still works
                String IndexName = (String) lotBox.getSelectedItem();
                int trueIndex = 0;
                for(int i=0; i < lotList.size(); i++){
                    if(lotList.get(i).getName() == IndexName){
                        trueIndex = i;
                    }
                }
                //check radiobuttons for building type
                if(oma.isSelected()){
                    lotList.get(trueIndex).setOma(new Omakotitalo(apartments));
                }else if(rivi.isSelected()){
                    lotList.get(trueIndex).setRivi(new Rivitalo(apartments));
                }else if(kerros.isSelected()){
                    lotList.get(trueIndex).setKerros(new Kerrostalo(apartments));
                }
                buildRoomsFld.setText("");
                //finished lots/buildings
                finLotList.add(lotList.get(trueIndex));
                
                //remove all items
                if(lotBox2.getItemCount() > 0){
                    lotBox2.removeAllItems();
                }
                //recreate list
                for(int i=0; i<finLotList.size(); i++){
                    lotBox2.addItem(finLotList.get(i).getName());
                }

                apCreateBtn.setVisible(true);
                apTenantBtn.setVisible(true);
                apPanel.validate();
                apPanel.repaint();
                buildArea.append("Uusi rakennus asetettu tontille " + lotList.get(trueIndex).getName() + "!\n");
            } catch(NumberFormatException e){
                buildArea.append("Tarkista syöte! En hyväksy kirjaimia tai negatiivisiä lukuja.\n");
            }

        }
        //Create apartment
        if(btnPress.getSource() == apCreateBtn){
            try{
                double area = Double.parseDouble(apAreaFld.getText());
                int rooms = Integer.parseInt(apRoomFld.getText());
                if(area < 0 || rooms < 0){
                    throw new NumberFormatException();
                }
                int n = apComboBox.getSelectedIndex();
                int m = lotBox2.getSelectedIndex();
                finLotList.get(m).getBuilding().setApartment(n, new Asunto(area, rooms));

                //empty fields
                apAreaFld.setText("");
                apRoomFld.setText("");
                apArea.append("Huoneiston tiedot lisätty!\n");
            } catch(NumberFormatException e){
                apArea.append("Tarkista syöte! Ei negatiivisia lukuja tai kirjaimia.\n");
            } catch(IndexOutOfBoundsException m){
                apArea.append("Täytä huoneistot järjestyksessä!\n");
            }
        }
        //create a tenant for apartment
        if(btnPress.getSource() == apTenantBtn){
            try{
                int n = apComboBox.getSelectedIndex();
                int m = lotBox2.getSelectedIndex();
                String name = apTenantFld.getText();
                finLotList.get(m).getBuilding().getApartments().get(n).setAsukas(new Asukas(name));
                apArea.append("Asukas " + name + " lisätty!\n");
            } catch(IndexOutOfBoundsException n){
                apArea.append("Luo asunto ennen asukkaan asettamista!\n");
            }
        }
        if(btnPress.getSource() == printBtn){
            String tontti = "Kaikki tiedot:";
            for(int i=0; i < finLotList.size(); i++){
                tontti += finLotList.get(i).printInfo();
            }
            printArea.setText(tontti);
        }
        ///end
    }

    //main function
    public static void main(String [] args){
        new NaapuritMain().setVisible(true);
    }
}