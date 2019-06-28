/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public class FriendsFrame extends JFrame{
    
    private JButton delete,exit;
    private Friends panel;
    
    /**
     *
     * @param baza
     * @param klient
     */
    public FriendsFrame(Baza baza, Klient klient) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(610,450);
        setLayout(null);
        setResizable(false);
        setTitle("Przyajciele");
        setLocationRelativeTo(null);
        
        add(panel = new Friends(baza, klient));
        panel.setBounds(6, 10, 580, 220);
        
        add(delete = new JButton("Usuń"));
        delete.setBounds(198, 300, 100, 30);
        
        add(exit = new JButton("Cofnij"));
        exit.setBounds(302, 300, 100, 30);
        
        panel.repaint();
        panel.refresh(baza, klient);
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                for (int i = 0; i < baza.sizeListaKlientow(); i++) {
                    if (panel.getNick().equals(baza.getListaKlientow(i).getNazwa())) {
                        klient.delPrzyjaciele(baza.getListaKlientow(i));
                        baza.refresh();
                        klient.refresh();
                        klient.setPrzyjaciele();
                        panel.refresh(baza,klient);
                        panel.repaint();
                        
                        
                    }
                }
                
        } } );
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
        } } );
        
    }
    
}
