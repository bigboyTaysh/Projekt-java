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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Paulina
 */
public class AddProfileFrame extends JFrame{
    
    private JButton dodaj,wyjdz;
    private JLabel info, imie,nazwa,miasto, tagi, wiek;

    Calendar calendar;
    SimpleDateFormat dateFormat;
    
    private AddProfileFrame frame;
    private boolean open;
    
    AddProfileFrame(Baza baza, Klient zalogowany, Klient klient, boolean open){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,450);
        setLayout(null);
        setResizable(false);
        setTitle("Profil");
        setLocationRelativeTo(null);
        this.open=open;
        
        
        
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
        
        add(dodaj = new JButton("Dodaj do znajomych"));
        dodaj.setBounds(175, 200, 150, 30);
            
        add(wyjdz = new JButton("Cofnij"));
        wyjdz.setBounds(200, 250, 100, 30);
        
        imie.setText(klient.getImie());
        nazwa.setText(klient.getNazwa());
        wiek.setText(Integer.toString(klient.getAge()));
        miasto.setText(klient.getMiasto());
        tagi.setText(klient.getTagi());
        
        dodaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                zalogowany.addPrzyjaciele(klient);
                zalogowany.refresh();
                zalogowany.setPrzyjaciele();
                baza.refresh();
                
                if((frame==null || !frame.isVisible()) && open()==false){
                    frame = new AddProfileFrame(baza, zalogowany, klient, open());
                    frame.setVisible(true);
                }
                dispose();
        } } );
        
        wyjdz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
                
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
    public boolean open(){
        return this.open;
    }
    
}
