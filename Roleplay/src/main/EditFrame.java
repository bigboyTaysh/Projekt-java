/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author pc
 */
public class EditFrame extends JFrame{
    
    private JLabel info; 
    private JTextField nazwa,imie,miasto;
    private JPasswordField oldHaslo, newHaslo1, newHaslo2;
    private JTextArea jTags;
    private JButton edit,exit;
    private String sentence, filePath;
    private String[] parts;
    private JFrame f; 
    private boolean shouldBreak=false, changeHaslo=true;
    private Border defaultBorder = UIManager.getBorder("TextField.border");

    
    ArrayList<String> tempArray;
    
    /**
     *
     * @param baza
     * @param klient
     */
    public EditFrame(Baza baza,Klient klient){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,450);
        setLayout(null);
        setResizable(false);
        setTitle("Edycja");
        setLocationRelativeTo(null);
        
        add(info = new JLabel("Nick:"));
        info.setBounds(170, 60, 80, 20);
        
        add(nazwa = new JTextField(klient.getNazwa(),15));
        nazwa.setBounds(280, 60, 80, 20);
        
        add(info = new JLabel("Imię:"));
        info.setBounds(170, 80, 80, 20);
        
        add(imie = new JTextField(klient.getImie(),15));
        imie.setBounds(280, 80, 80, 20);
        
        add(info = new JLabel("Stare haslo:"));
        info.setBounds(170, 100, 80, 20);
        
        add(oldHaslo = new JPasswordField(15));
        oldHaslo.setBounds(280, 100, 80, 20);
        
        add(info = new JLabel("Nowe haslo:"));
        info.setBounds(170, 120, 80, 20);
        
        add(newHaslo1 = new JPasswordField(15));
        newHaslo1.setBounds(280, 120, 80, 20);
        
        add(info = new JLabel("Powtórz haslo:"));
        info.setBounds(170, 140, 150, 20);
        
        add(newHaslo2 = new JPasswordField(15));
        newHaslo2.setBounds(280, 140, 80, 20);
        
        add(info = new JLabel("Miasto:"));
        info.setBounds(170, 160, 80, 20);
        
        add(miasto = new JTextField(klient.getMiasto(),15));
        miasto.setBounds(280, 160, 80, 20);
        
        add(info = new JLabel("Tagi:"));
        info.setBounds(170, 180, 80, 20);
         
        add(jTags = new JTextArea(klient.getTagi()));
        jTags.setLineWrap(true);
        jTags.setWrapStyleWord(true);
        jTags.setBounds(280, 180, 150, 100);
            
        add(edit = new JButton("Edytuj"));
        edit.setBounds(150, 300, 100, 30);
        
        add(exit = new JButton("Cofnij"));
        exit.setBounds(255, 300, 100, 30);
        
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tempArray = new ArrayList<>();        
                
                if(imie.getText().length()<3 || imie.getText().length()>15 || imie.getText().contains(" ") || Pattern.compile( "[0-9]" ).matcher( imie.getText() ).find()){
                    imie.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    imie.setBorder(defaultBorder);
                }
                
                if(nazwa.getText().length()<3 || nazwa.getText().length()>15){
                    nazwa.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    nazwa.setBorder(defaultBorder);
                }
                
                
                if(oldHaslo.getText().equals(klient.getHaslo()) || oldHaslo.getText().equals("")){
                    oldHaslo.setBorder(defaultBorder);
                    if(!newHaslo1.getText().equals("") && newHaslo1.getText().length()<3 || newHaslo1.getText().length()>15){
                        newHaslo1.setBorder(BorderFactory.createLineBorder(Color.red));
                        shouldBreak = true;
                    }
                    else{
                        newHaslo1.setBorder(defaultBorder);
                        if(newHaslo2.getText().equals(newHaslo1.getText()) || newHaslo2.getText().equals("")){
                            newHaslo2.setBorder(defaultBorder);
                            if(oldHaslo.getText().equals("") && newHaslo1.getText().equals("") && newHaslo2.getText().equals("")){
                                changeHaslo=false;
                            }
                        }
                        else{
                            newHaslo2.setBorder(BorderFactory.createLineBorder(Color.red));
                            shouldBreak = true;
                        }
                    }
                }
                else{ 
                    oldHaslo.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                
                
                if(miasto.getText().length()<3 || miasto.getText().length()>15 || miasto.getText().contains(" ") || Pattern.compile( "[0-9]" ).matcher( miasto.getText() ).find()){
                    miasto.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    miasto.setBorder(defaultBorder);
                }
                
                if(!shouldBreak){
                    klient.setImie(imie.getText());
                    klient.setNazwa(nazwa.getText());
                    if(changeHaslo){
                        klient.setHaslo(newHaslo2.getText());
                    }
                    klient.setMiasto(miasto.getText());
                    klient.setTagi(jTags.getText().replaceAll("\n", " "));

                    filePath = "zapis.txt";
                    FileReader fileReader = null;
                    Scanner reader = null;

                    try {
                        fileReader = new FileReader(filePath);
                        reader = new Scanner(fileReader);

                        while (reader.hasNextLine()) {
                            sentence = reader.nextLine();
                            parts = sentence.split("-");

                            if (parts[0].equals(klient.getLogin())) {
                                tempArray.add(
                                        klient.getLogin() + "-"
                                        + klient.getHaslo() + "-"
                                        + klient.getImie() + "-"
                                        + klient.getNazwa() + "-"
                                        + klient.getDzien() + "-"
                                        + klient.getMiesiac() + "-"
                                        + klient.getRok() + "-"
                                        + klient.getMiasto() + "-"
                                        + klient.getMalefemale() + "-"
                                        + klient.getTagi() + "-"
                                        + klient.getPrzyjaciele()
                                );
                            } else {
                                tempArray.add(sentence);
                            }
                        };

                        reader.close();

                        PrintWriter pr = new PrintWriter(filePath);

                        for (String str : tempArray) {
                            pr.println(str);
                        }

                        pr.close();

                    } catch (FileNotFoundException ex) {
                        File plik = new File("zapis.txt");
                    } catch (IOException ex) {
                        Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (fileReader == null) {
                            try {
                                fileReader.close();
                            } catch (IOException ex) {
                                Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    dispose();
                    baza.refresh();
                    f = new ProfileFrame(baza, klient);
                    f.setVisible(true);
                } else {
                    shouldBreak=false;
                    changeHaslo=true;
                }
                
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
}
