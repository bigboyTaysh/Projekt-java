/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Baza {
    
    private ArrayList<Klient> listaKlientow = new ArrayList<Klient>();

    /**
     *
     */
    public Baza() {
        refresh();
    }

    /**
     *
     * @param i
     * @return
     */
    public Klient getListaKlientow(int i) {
        return listaKlientow.get(i);
    }

    /**
     *
     * @param k
     */
    public void addListaKlientow(Klient k) {
        this.listaKlientow.add(k);
    }
    
    /**
     *
     * @return
     */
    public int sizeListaKlientow(){
        return this.listaKlientow.size();
    }
    
    /**
     *
     * @param klient
     */
    public void delListaKlientow(Klient klient){
        
        ArrayList<String> tempArray = new ArrayList<>();        
                
                String[] parts;
                String filePath = "zapis.txt", sentence;
                FileReader fileReader = null;
                Scanner reader = null;
            
                try {
                    fileReader = new FileReader(filePath);
                    reader = new Scanner(fileReader);
                
                while(reader.hasNextLine()){
                    sentence = reader.nextLine();
                    parts = sentence.split("-");
                    
                    if(parts[0].equals(klient.getLogin())){
                        this.listaKlientow.remove(klient);
                    }
                    else{
                        tempArray.add(sentence);
                    }
                };
            
                reader.close();
                
                PrintWriter pr = new PrintWriter(filePath);
                
                for (String str : tempArray) {
                    pr.println(str);
                }
                
                pr.close();
                
                } 
                    catch (FileNotFoundException ex) {
                        File plik = new File("zapis.txt");
                    } catch (IOException ex) {
                        Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }            
                finally {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        
        this.refresh();
    }
    
    /**
     *
     */
    public void refresh(){
            this.listaKlientow.clear();
            
            Klient klient;
            
            String zdanie, login, haslo, imie, nazwisko, miasto, malefemale, tagi, przyjaciele;
            int dzien, miesiac, rok;
            String[] parts;
            
            String filePath = "zapis.txt";
            BufferedReader fileReader = null;
            
            try {
            fileReader = new BufferedReader(new FileReader(filePath));

            while ((zdanie = fileReader.readLine()) != null) {
                parts = zdanie.split("-");
                login = parts[0];
                haslo = parts[1];
                imie = parts[2];
                nazwisko = parts[3];
                dzien = Integer.parseInt(parts[4]);
                miesiac = Integer.parseInt(parts[5]);
                rok = Integer.parseInt(parts[6]);
                miasto = parts[7];
                malefemale = parts[8];
                tagi = parts[9];
                przyjaciele = parts[10];

                klient = new Klient(login, haslo, imie, nazwisko, dzien, miesiac, rok, miasto, malefemale, tagi, przyjaciele);
                this.listaKlientow.add(klient);
                klient.setBaza(this);
                klient.setPrzyjaciele();
            };
        } 
                catch (FileNotFoundException ex) {
            File plik = new File("zapis.txt");

        } catch (IOException ex) {
            Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
