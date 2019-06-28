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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author pc
 */
public class SearchFrame extends JFrame{
    
    private JLabel info; 
    private JTextField jNick,jName,jMinAge,jMaxAge,jCity;
    private JTextArea jTag;
    private JButton search,exit;
    private String nick,name,city,tags,malefemale;
    private int minAge, maxAge;
    private JFrame f;
    
    private ArrayList<Klient> foundKlient =  new ArrayList<Klient>();
                
    private Found panel;           
                
    
    SearchFrame(Baza baza, Klient klient){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(610,450);
        setLayout(null);
        setResizable(false);
        setTitle("Wyszukiwanie");
        setLocationRelativeTo(null);
        
        add(info = new JLabel("", JLabel.CENTER));
        info.setBounds(150, 40, 300, 20);
        
        add(info = new JLabel("Nick:"));
        info.setBounds(100, 10, 80, 20);
        
        add(jNick = new JTextField(15));
        jNick.setBounds(180, 10, 80, 20);
        
        add(info = new JLabel("Imię:"));
        info.setBounds(100, 30, 80, 20);
        
        add(jName = new JTextField(2));
        jName.setBounds(180, 30, 80, 20);
        
        add(info = new JLabel("Wiek od:"));
        info.setBounds(300, 10, 80, 20);
        
        add(jMinAge = new JTextField(15));
        jMinAge.setBounds(360, 10, 20, 20);
        
        add(info = new JLabel("do:"));
        info.setBounds(390, 10, 20, 20);
        
        add(jMaxAge = new JTextField(15));
        jMaxAge.setBounds(420, 10, 20, 20);
        
        add(info = new JLabel("Miasto:"));
        info.setBounds(300, 30, 80, 20);
        
        add(jCity = new JTextField(15));
        jCity.setBounds(360, 30, 80, 20);
        
        add(info = new JLabel("Płeć:"));
        info.setBounds(60, 50, 80, 20);
        
        JRadioButton maleButton = new JRadioButton("Mężczyzna", false);
        JRadioButton femaleButton = new JRadioButton("Kobieta", false);
        add(maleButton);
        maleButton.setBounds(100, 50, 90, 20);
        add(femaleButton);
        femaleButton.setBounds(190, 50, 90, 20);
       
        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);
        
        maleButton.setActionCommand("male");
        femaleButton.setActionCommand("female");
        
        add(info = new JLabel("Tagi:"));
        info.setBounds(280, 50, 60, 20);
         
        add(jTag = new JTextArea());
        jTag.setLineWrap(true);
        jTag.setWrapStyleWord(true);
        jTag.setBounds(340, 50, 150, 50);
        
        add(panel = new Found(baza, klient, foundKlient));
        panel.setBounds(10, 110, 580, 220);
            
        add(search = new JButton("Szukaj"));
        search.setBounds(198, 360, 100, 30);
        
        add(exit = new JButton("Cofnij"));
        exit.setBounds(302, 360, 100, 30);
        
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                clerFoundKlient();
                
                name = jName.getText();
                nick = jNick.getText();
                
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
                
                city = jCity.getText();
                
                try{    
                    malefemale = group.getSelection().getActionCommand();
                }
                catch(NullPointerException f){
                    malefemale = "";
                }
                
                tags = jTag.getText();
                
                
                    for(int i=0; i<baza.sizeListaKlientow(); i++){
                        if(name.equals("") || baza.getListaKlientow(i).getImie().toLowerCase().contains(name.toLowerCase())){  
                            if(nick.equals("") || baza.getListaKlientow(i).getNazwa().toLowerCase().contains(nick.toLowerCase())){
                                if((jMinAge.getText().equals("") && jMaxAge.getText().equals("")) || (jMinAge.getText().equals("") &&
                                        baza.getListaKlientow(i).getAge()<=maxAge) || (minAge<=baza.getListaKlientow(i).getAge() &&
                                        baza.getListaKlientow(i).getAge()<=maxAge) || (minAge<=baza.getListaKlientow(i).getAge() &&
                                        jMaxAge.getText().equals(""))){
                                    if(city.equals("") || baza.getListaKlientow(i).getMiasto().toLowerCase().equals(city.toLowerCase())){
                                        if(malefemale.equals("") || baza.getListaKlientow(i).getMalefemale().equals(malefemale)){
                                            if(tags.equals("") || baza.getListaKlientow(i).getTagi().toLowerCase().contains(tags.toLowerCase())){
                                                addFoundKlient(baza.getListaKlientow(i));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                
                panel.repaint();
                panel.refresh();
                
                
        } } );
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                f = new ProfileFrame(baza, klient);
                f.setVisible(true);
                
        } } );
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                dispose();
                f = new ProfileFrame(baza, klient);
                f.setVisible(true);
                
        } } );
    }
    
    /**
     *
     * @param k
     */
    public void addFoundKlient(Klient k){
        this.foundKlient.add(k);
    }
    
    /**
     *
     * @param i
     * @return
     */
    public Klient getFoundKlient(int i){
        return this.foundKlient.get(i);
    }
    
    /**
     *
     * @return
     */
    public int sizeFoundKlient(){
        return this.foundKlient.size();
    }
    
    /**
     *
     */
    public void clerFoundKlient(){
        this.foundKlient.clear();
    }
}
