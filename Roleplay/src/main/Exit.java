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
import javax.swing.JLabel;

/**
 *
 * @author pc
 */
public class Exit extends JFrame{
        
        private JLabel info;
        private JButton yes, no;
        
        Exit(){
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setSize(215,120);
            setLayout(null);
            setResizable(false);
            setTitle("Wyjście");
            setLocationRelativeTo(null);
            setAlwaysOnTop(true);
        
            add(info = new JLabel("Czy na pewno chcesz wyjść?",JLabel.CENTER));
            info.setBounds(0, 10, 200, 20);
        
            add(yes = new JButton("Tak"));
            yes.setBounds(45, 40, 55, 25);
        
            add(no = new JButton("Nie"));
            no.setBounds(105, 40, 55, 25);
            
            yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            } } );
            
            
            no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            } } );
            
            addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                dispose();
                
            } } );
        
        
        }
}
