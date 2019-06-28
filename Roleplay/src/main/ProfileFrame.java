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
 * @author 
 */
public class ProfileFrame extends JFrame{
    
    private static JLabel info;
    private JButton para,wyjdz,losuj,szukaj,edit,friends;
    private JFrame l=null,s=null,ed=null,f=null, p=null;
    private FriendsFrame fr=null;
    
    ProfileFrame(Baza baza, Klient klient){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,450);
        setLayout(null);
        setResizable(false);
        setTitle("Zalogowano");
        setLocationRelativeTo(null);
        klient.setPrzyjaciele();
        
        add(info = new JLabel("Witaj " + klient.getImie(), JLabel.CENTER));
        info.setBounds(100, 40, 300, 20);

        add(losuj = new JButton("Losuj"));
        losuj.setBounds(200, 100, 100, 30);
        
        add(para = new JButton("Para"));
        para.setBounds(200, 135, 100, 30);
        
        add(szukaj = new JButton("Szukaj"));
        szukaj.setBounds(200, 170, 100, 30);
        
        add(friends = new JButton("Znajomi"));
        friends.setBounds(200, 205, 100, 30);
       
        add(edit = new JButton("Edytuj"));
        edit.setBounds(200, 240, 100, 30);
        
        add(wyjdz = new JButton("Wyloguj"));
        wyjdz.setBounds(200, 330, 100, 30);
        
        losuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(l==null || !l.isVisible()){
                    l = new RandomProfileFrame(baza, klient);
                    l.setVisible(true);
                    dispose();
                }
        } } );
        
        para.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(p==null || !p.isVisible()){
                    p = new PairFrame(baza, klient);
                    p.setVisible(true);
                    dispose();
                }
        } } );
        
        szukaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(s==null || !s.isVisible()){
                    s = new SearchFrame(baza,klient);
                    s.setVisible(true);
                    dispose();
                }
        } } );
        
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ed==null || !ed.isVisible()){
                    ed = new EditFrame(baza,klient);
                    ed.setVisible(true);
                    dispose();
                }
        } } );
        
        friends.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fr==null || !fr.isVisible()){
                    fr = new FriendsFrame(baza,klient);
                    fr.setVisible(true);
                }
        } } );
        
        wyjdz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f = new MainFrame();
                if(fr!=null){
                    fr.dispose();
                }
                if(l!=null){
                    l.dispose();
                }
                if(p!=null){
                    p.dispose();
                }
                if(s!=null){
                    s.dispose();
                }
                if(ed!=null){
                    ed.dispose();
                }
                dispose();
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
