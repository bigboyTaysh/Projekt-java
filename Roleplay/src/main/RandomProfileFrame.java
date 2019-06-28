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
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Paulina
 */
public class RandomProfileFrame extends JFrame{
    
    private JButton dodaj,losuj,wyjdz;
    private JLabel info,imie,nazwa,miasto, tagi, wiek;
    private int los;
    private Random r = new Random();
    private JFrame f,l;
    
    RandomProfileFrame(Baza baza, Klient klient){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,450);
        setLayout(null);
        setResizable(false);
        setTitle("Profil");
        setLocationRelativeTo(null);
        
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

        add(losuj = new JButton("Losuj"));
        losuj.setBounds(150, 250, 100, 30);

        add(wyjdz = new JButton("Cofnij"));
        wyjdz.setBounds(255, 250, 100, 30);
        
        los = r.nextInt(((baza.sizeListaKlientow())));

        imie.setText(baza.getListaKlientow(los).getImie());
        nazwa.setText(baza.getListaKlientow(los).getNazwa());
        wiek.setText(Integer.toString(baza.getListaKlientow(los).getAge()));
        miasto.setText(baza.getListaKlientow(los).getMiasto());
        tagi.setText(baza.getListaKlientow(los).getTagi());
        
        losuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                l = new RandomProfileFrame(baza, klient);
                l.setVisible(true);
        } } );

        dodaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < baza.sizeListaKlientow(); i++) {
                    if (nazwa.equals(baza.getListaKlientow(i).getNazwa())) {
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
}
