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
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public class MainFrame extends JFrame{
    
    private JButton Login,Rej;
    private JFrame f;
    
    MainFrame(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Roleplay");
        setSize(500,450);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        
        
        add(Login = new JButton("Zaloguj"));
        Login.setBounds(195, 90, 100, 30);
        
        add(Rej = new JButton("Rejestracia"));
        Rej.setBounds(195, 120, 100, 30);
        
        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Baza baza = new Baza();
                
                f = new OldFrame(baza);
                setVisible(false);
                f.setVisible(true);
        } } );
        
        Rej.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Baza baza = new Baza();
                
                f = new NewFrame(baza);
                setVisible(false);
                f.setVisible(true);
        } } );
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(f==null || !f.isVisible()){
                    f = new Exit();
                    f.setVisible(true);
                }
        } } );
        
        setVisible(true);
    }
}
