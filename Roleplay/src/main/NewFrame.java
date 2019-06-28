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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author student
 */
public class NewFrame extends JFrame{
    
    private JLabel info;
    private JTextArea tag;
    private JButton utw,wyjdz;
    private JPasswordField haslo;
    private JFrame f;
    private JTextField login,imie,nazwa,miasto,day,month,year;
    private String przyjaciele;
    private boolean shouldBreak=false;
    private Border defaultBorder = UIManager.getBorder("TextField.border");
    private DateValidator dateValidator;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    
    NewFrame(Baza baza){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,450);
        setTitle("Rejestracja");
        setLayout(null); 
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(info = new JLabel("Imię:"));
        info.setBounds(155, 60, 80, 20);
       
        add(imie = new JTextField(10));
        imie.setBounds(265, 60, 80, 20);
        
        add(info = new JLabel("Nick:"));
        info.setBounds(155, 80, 80, 20);
        
        add(nazwa = new JTextField(10));
        nazwa.setBounds(265, 80, 80, 20);
        
        add(info = new JLabel("Login:"));
        info.setBounds(155, 100, 80, 20);
        
        add(login = new JTextField(10));
        login.setBounds(265, 100, 80, 20);
        
        add(info = new JLabel("Hasło:"));
        info.setBounds(155, 120, 80, 20);
        
        add(haslo = new JPasswordField(10));
        haslo.setBounds(265, 120, 80, 20);
        
        add(info = new JLabel("Data(dd:mm:rrrr):"));
        info.setBounds(135, 140, 100, 20);
        
        add(day = new JTextField(2));
        day.setBounds(265, 140, 25, 20);
        
        add(month = new JTextField(2));
        month.setBounds(290, 140, 25, 20);
        
        add(year = new JTextField(4));
        year.setBounds(315, 140, 40, 20);
        
        add(info = new JLabel("Miasto:"));
        info.setBounds(155, 160, 80, 20);
        
        add(miasto = new JTextField(10));
        miasto.setBounds(265, 160, 80, 20);
        
        add(info = new JLabel("Płeć:"));
        info.setBounds(155, 180, 80, 20);
        
        JRadioButton maleButton = new JRadioButton("Mężczyzna", false);
        JRadioButton femaleButton = new JRadioButton("Kobieta", false);
        add(maleButton);
        maleButton.setBounds(265, 180, 100, 20);
        add(femaleButton);
        femaleButton.setBounds(365, 180, 80, 20);
       
        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);
        
        maleButton.setActionCommand("male");
        femaleButton.setActionCommand("female");
        
        add(info = new JLabel("Tagi:"));
        info.setBounds(155, 200, 80, 20);
        
        add(tag = new JTextArea());
        tag.setLineWrap(true);
        tag.setWrapStyleWord(true);
        tag.setBounds(265, 200, 150, 50);
        
        add(utw = new JButton("Zarejestruj"));
        utw.setBounds(135, 320, 100, 30);
        
        add(wyjdz = new JButton("Cofnij"));
        wyjdz.setBounds(240, 320, 100, 30);
        
        utw.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
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
                
                if(login.getText().length()<3 || login.getText().length()>15 || login.getText().contains(" ")){
                    login.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    login.setBorder(defaultBorder);
                }
                
                if(haslo.getText().length()<3 || haslo.getText().length()>15){
                    haslo.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    haslo.setBorder(defaultBorder);
                }
                
                for(int i=0; i<baza.sizeListaKlientow(); i++){
                    if(login.getText().equals(baza.getListaKlientow(i).getLogin())){
                        login.setBorder(BorderFactory.createLineBorder(Color.red));
                        shouldBreak = true;
                        break;
                    }
                    
                    else if(nazwa.getText().equals(baza.getListaKlientow(i).getNazwa())){
                        nazwa.setBorder(BorderFactory.createLineBorder(Color.red));
                        shouldBreak = true;
                        break;
                    }
                }
                
                dateValidator = new DateValidator();

                if (dateValidator.isThisDateValid(
                        day.getText() + "/"
                        + month.getText() + "/"
                        + year.getText(), "dd/MM/yyyy"
                )) {
                    day.setBorder(defaultBorder);
                    month.setBorder(defaultBorder);
                    year.setBorder(defaultBorder); 
                } else {
                    
                    day.setBorder(BorderFactory.createLineBorder(Color.red));
                    month.setBorder(BorderFactory.createLineBorder(Color.red));
                    year.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                
                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowYear = calendar.get(Calendar.YEAR);
                int age = nowYear - Integer.parseInt(year.getText());

                if (nowMonth < Integer.parseInt(month.getText())) {
                    age--;
                } else if ((nowMonth == Integer.parseInt(month.getText())) && (nowDay < Integer.parseInt(day.getText()))) {
                    age--;
                    if (age < 0) {
                        age = 0;
                    }
                }
                
                if(age >= 18){
                    day.setBorder(defaultBorder);
                    month.setBorder(defaultBorder);
                    year.setBorder(defaultBorder);
                }
                else{
                    day.setBorder(BorderFactory.createLineBorder(Color.red));
                    month.setBorder(BorderFactory.createLineBorder(Color.red));
                    year.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }   
                
                if(Integer.parseInt(year.getText())<1920){
                    year.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    year.setBorder(defaultBorder);
                }
                
                if(miasto.getText().length()<3 ||
                        miasto.getText().length()>20 ||
                        Pattern.compile( "[0-9]" ).matcher( miasto.getText() ).find()
                  ){ 
                    miasto.setBorder(BorderFactory.createLineBorder(Color.red));
                    shouldBreak = true;
                }
                else{
                    miasto.setBorder(defaultBorder);
                }
                    
                if(!shouldBreak){
                    przyjaciele=" ";
                    
                    try {
                        FileWriter zapis = null;
                        zapis = new FileWriter("zapis.txt", true);
                        zapis.write(
                                login.getText()+"-"+
                                haslo.getText()+"-"+
                                imie.getText()+"-"+
                                nazwa.getText()+"-"+
                                Integer.parseInt(day.getText())+"-"+
                                Integer.parseInt(month.getText())+"-"+
                                Integer.parseInt(year.getText())+"-"+
                                miasto.getText()+"-"+
                                group.getSelection().getActionCommand()+"-"+
                                tag.getText().replaceAll("\n", " ")+"-"+
                                przyjaciele+"\r\n"
                        );   
                        zapis.close();
                    }
                
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(NewFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(NewFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    dispose();
                    baza.refresh();
                    f = new OldFrame(baza);
                    f.setVisible(true);
                    
                } else {
                    shouldBreak=false;
                }
        } } );
        
        wyjdz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f = new MainFrame();
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
