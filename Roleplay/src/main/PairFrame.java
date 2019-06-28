/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Paulina
 */
public class PairFrame extends JFrame{
    
    private JButton dodaj,para,wyjdz;
    private String nick;
    private JLabel info, imie,nazwa,miasto, tagi, wiek;
    private JTextField jMinAge, jMaxAge;;
    private int pom=0;
    private Baza baza;
    private Klient klient;
    private ArrayList<Integer> ile = new ArrayList<Integer>();
    private JFrame f;
    private ButtonGroup group;
    private String actionCommand,minAgeText,maxAgeText;
    private int maxAge, minAge;
    
    JRadioButton maleButton, femaleButton;
    
    PairFrame(Baza baza, Klient klient){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,450);
        setLayout(null);
        setResizable(false);
        setTitle("Profil");
        setLocationRelativeTo(null);
        this.baza=baza;
        this.klient=klient;
        
        add(nazwa = new JLabel("",JLabel.CENTER));
        nazwa.setBounds(100, 40, 300, 20);
        
        add(info = new JLabel("Imię:"));
        info.setBounds(200, 60, 80, 20);
        
        add(imie = new JLabel(""));
        imie.setBounds(270, 60, 100, 20); 
        
        add(info = new JLabel("Wiek:"));
        info.setBounds(200, 80, 80, 20);
        
        add(wiek = new JLabel(""));
        wiek.setBounds(270, 80, 100, 20); 
        
        add(info = new JLabel("Miasto:"));
        info.setBounds(200, 100, 80, 20);
        
        add(miasto = new JLabel(""));
        miasto.setBounds(270, 100, 100, 20); 
        
        add(info = new JLabel("Tagi:"));
        info.setBounds(200, 120, 80, 20);
        
        add(tagi = new JLabel(""));
        tagi.setBounds(270, 120, 150, 20); 
        
        
        add(info = new JLabel("Wiek od:"));
        info.setBounds(190, 190, 80, 20);
        
        add(jMinAge = new JTextField(15));
        jMinAge.setBounds(245, 190, 20, 20);
        
        add(info = new JLabel("do:"));
        info.setBounds(270, 190, 20, 20);
        
        add(jMaxAge = new JTextField(15));
        jMaxAge.setBounds(290, 190, 20, 20);
        
        if(klient.getMalefemale().equals("male")){
            maleButton = new JRadioButton("Mężczyzna", false);
            femaleButton = new JRadioButton("Kobieta", true);
        } else {
            maleButton = new JRadioButton("Mężczyzna", true);
            femaleButton = new JRadioButton("Kobieta", false);
        }
        
        add(maleButton);
        maleButton.setBounds(170, 220, 90, 20);
        add(femaleButton);
        femaleButton.setBounds(270, 220, 90, 20);
       
        group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);
        
        maleButton.setActionCommand("male");
        femaleButton.setActionCommand("female");
        actionCommand=group.getSelection().getActionCommand();
        
        add(dodaj = new JButton("Dodaj do znajomych"));
        dodaj.setBounds(175, 250, 150, 30);

        add(para = new JButton("Para"));
        para.setBounds(150, 300, 100, 30);

        add(wyjdz = new JButton("Cofnij"));
        wyjdz.setBounds(255, 300, 100, 30);

        
        this.ile();
        this.next();
        
        
        para.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!(actionCommand.equals(group.getSelection().getActionCommand())) ||
                    (!jMinAge.getText().equals(minAgeText)) ||
                    (!jMaxAge.getText().equals(maxAgeText))){
                    actionCommand=group.getSelection().getActionCommand();
                    minAgeText=jMinAge.getText();
                    maxAgeText=jMaxAge.getText();
                    getPairFrame().ile();
                    getPairFrame().next();
                }
                else{
                    getPairFrame().next();
                }
                
        } } );

        dodaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                for (int i = 0; i < baza.sizeListaKlientow(); i++) {
                    if (nick.equals(baza.getListaKlientow(i).getNazwa())) {
                        klient.addPrzyjaciele(baza.getListaKlientow(i));
                        klient.setPrzyjaciele();
                        baza.refresh();
                        klient.refresh();
                        
                    }
                }

        } } );
        
        wyjdz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                f = new ProfileFrame(baza, klient);
                f.setVisible(true);
                
        } } );
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                dispose();
                
        } } );
    }
    
    /**
     *
     * @return
     */
    public PairFrame getPairFrame(){
        return this;
    }
    
    /**
     *
     */
    public void ile() {
        ile.clear();
        if(jMinAge.getText().equals("") && !jMaxAge.getText().equals("")){
                    maxAge = Integer.parseInt(jMaxAge.getText());
                }
                else if(jMaxAge.getText().equals("") && !jMinAge.getText().equals("")){
                    minAge = Integer.parseInt(jMinAge.getText());
                }   
                else if(!jMinAge.getText().equals("") && !jMaxAge.getText().equals("")){
                    maxAge = Integer.parseInt(jMaxAge.getText());
                    minAge = Integer.parseInt(jMinAge.getText());
                }
                else{}
        
        if (group.getSelection().getActionCommand() != null) {
            for (int i = 0; i < baza.sizeListaKlientow(); i++) {
                if (
                    group.getSelection().getActionCommand().equals(baza.getListaKlientow(i).getMalefemale()) &&
                    ((jMinAge.getText().equals("") && jMaxAge.getText().equals("")) || (jMinAge.getText().equals("") &&
                     baza.getListaKlientow(i).getAge()<=maxAge) || (minAge<=baza.getListaKlientow(i).getAge() &&
                     baza.getListaKlientow(i).getAge()<=maxAge) || (minAge<=baza.getListaKlientow(i).getAge() &&
                     jMaxAge.getText().equals(""))
                        )){
                    ile.add(i);
                } else {
                }
            }
        }
        this.pom = 0;
    }
    
    /**
     *
     */
    public void next() {
        if(ile.size()==0){
            nick = "";
            imie.setText("");
            nazwa.setText("");
            wiek.setText("");
            miasto.setText("");
            tagi.setText("");
        }
        if (pom < ile.size()) {
            nick = baza.getListaKlientow(ile.get(pom)).getNazwa();

            imie.setText(baza.getListaKlientow(ile.get(pom)).getImie());
            nazwa.setText(baza.getListaKlientow(ile.get(pom)).getNazwa());
            wiek.setText(Integer.toString(baza.getListaKlientow(ile.get(pom)).getAge()));
            miasto.setText(baza.getListaKlientow(ile.get(pom)).getMiasto());
            tagi.setText(baza.getListaKlientow(ile.get(pom)).getTagi());

            pom++;
        } else {
            pom = 0;
        }
    }
}
