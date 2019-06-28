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
public class Admin extends JFrame{
    
    private JButton delete,exit;
    private All panel;
    private boolean open;
    
    /**
     *
     * @param baza
     */
    public Admin(Baza baza) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,450);
        setLayout(null);
        setResizable(false);
        setTitle("Panel");
        setLocationRelativeTo(null);
        this.open=open;
        
        add(panel = new All(baza));
        panel.setBounds(6, 10, 580, 220);
        
        add(delete = new JButton("Usuń"));
        delete.setBounds(198, 300, 100, 30);
        
        add(exit = new JButton("Wyloguj"));
        exit.setBounds(302, 300, 100, 30);
        
        panel.repaint();
        panel.refresh(baza);
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                for (int i = 0; i < baza.sizeListaKlientow(); i++) {
                    if (panel.getNick().equals(baza.getListaKlientow(i).getNazwa())) {
                        baza.delListaKlientow(baza.getListaKlientow(i));
                        baza.refresh();
                        panel.repaint();
                        panel.refresh(baza);
                    }
                }
                
        } } );
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JFrame f = new MainFrame();
                dispose();
                
        } } );
        
    }
    
}
