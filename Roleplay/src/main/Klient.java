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

public class Klient extends Osoba{
    
    final String login;
    private String haslo;
    private String tagi;
    private String malefemale;
    private ArrayList<Klient> friendList = new ArrayList<Klient>();
    private Baza baza;
    private String przyjaciele;
    
    Klient(String login, String haslo, String imie, String nazwa,
            int dzien, int miesiac, int rok, String miasto,
            String malefemale, String tagi,String przyjaciele){
        
        super(imie, nazwa, dzien, miesiac, rok, miasto);
        this.login = login;
        this.haslo = haslo;
        this.tagi = tagi;
        this.malefemale = malefemale;
        this.baza = baza;
        this.przyjaciele=przyjaciele;
    }
    
    /**
     *
     */
    public void setPrzyjaciele(){
        this.friendList.clear();
        
        String[] parts;
        parts = this.przyjaciele.split("/");
        
        for(int i=0; i < parts.length ; i++){
            for(int j=0; j < this.baza.sizeListaKlientow(); j++){
                if(parts[i].equals(this.baza.getListaKlientow(j).getLogin()) && !parts[i].toLowerCase().contains(this.przyjaciele)){
                    addFriendList(this.baza.getListaKlientow(j));
                }
            }
        }
    }
    
    /**
     *
     * @param klient
     */
    public void addPrzyjaciele(Klient klient) {
        if(!this.przyjaciele.toLowerCase().contains(klient.getLogin().toLowerCase())){
            this.przyjaciele=this.przyjaciele+"/"+klient.getLogin();
        }

        
        String sentence, filePath;
        String[] parts;
        ArrayList<String> tempArray = new ArrayList<>();        
                
                
                filePath = "zapis.txt";
                FileReader fileReader = null;
                Scanner reader = null;
            
                try {
                    fileReader = new FileReader(filePath);
                    reader = new Scanner(fileReader);
                
                while(reader.hasNextLine()){
                    sentence = reader.nextLine();
                    parts = sentence.split("-");
                    
                    if(parts[0].equals(this.getLogin())){
                        tempArray.add(
                            this.getLogin() + "-" +
                            this.getHaslo() + "-" +
                            this.getImie() + "-" +
                            this.getNazwa() + "-" +
                            this.getDzien() + "-" + 
                            this.getMiesiac() + "-" +
                            this.getRok() + "-" + 
                            this.getMiasto() + "-" +
                            this.getMalefemale() + "-" +
                            this.getTagi() + "-" +
                            this.getPrzyjaciele()
                        );
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
                    if (fileReader == null) {
                        try {
                            fileReader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }   
    }
    
    /**
     *
     * @param klient
     */
    public void delPrzyjaciele(Klient klient){
        this.friendList.remove(klient);
        
        if(this.przyjaciele.toLowerCase().contains(klient.getLogin().toLowerCase())){
            this.przyjaciele=this.przyjaciele.replace("/"+klient.getLogin(), "");
        }
        
        
        String sentence, filePath;
        String[] parts;
        ArrayList<String> tempArray = new ArrayList<>();        
                
                
                filePath = "zapis.txt";
                FileReader fileReader = null;
                Scanner reader = null;
            
                try {
                    fileReader = new FileReader(filePath);
                    reader = new Scanner(fileReader);
                
                while(reader.hasNextLine()){
                    sentence = reader.nextLine();
                    parts = sentence.split("-");
                    
                    if(parts[0].equals(this.getLogin())){
                        tempArray.add(
                            this.getLogin() + "-" +
                            this.getHaslo() + "-" +
                            this.getImie() + "-" +
                            this.getNazwa() + "-" +
                            this.getDzien() + "-" + 
                            this.getMiesiac() + "-" +
                            this.getRok() + "-" + 
                            this.getMiasto() + "-" +
                            this.getMalefemale() + "-" +
                            this.getTagi() + "-" +
                            this.getPrzyjaciele()
                        );
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
                    if (fileReader == null) {
                        try {
                            fileReader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
    }
    
    /**
     *
     */
    public void refresh(){
            
            String zdanie, login, haslo, imie, nazwa, miasto, malefemale, tagi, przyjaciele;
            String[] parts;
            
            String filePath = "zapis.txt";
            BufferedReader fileReader = null;
            
            try {
                fileReader = new BufferedReader(new FileReader(filePath));
                
            while((zdanie = fileReader.readLine()) != null){
                parts = zdanie.split("-");
                login = parts[0];
                haslo = parts[1];
                imie = parts[2];
                nazwa = parts[3];
                miasto = parts[7];
                tagi = parts[9];
                przyjaciele = parts[10];
                
                if(login.equals(this.login)){
                    this.haslo=haslo;
                    super.setImie(imie);
                    super.setNazwa(nazwa);
                    super.setMiasto(miasto);
                    this.tagi=tagi;
                    this.przyjaciele=przyjaciele;
                }
                
            };
            
            } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                    File plik = new File("zapis.txt");
                    
                } catch (IOException ex) {
                    Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                }            
            finally {
                if (fileReader == null) {
                    try {
                        fileReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(OldFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }
    
    /**
     *
     * @return
     */
    public String getPolish(){
        if(this.getMalefemale().equals("male")){
            return "mężczyzna";
        }
        else{
            return "kobieta";
        }
    }
        
    /**
     *
     * @return
     */
    public int sizefriendList(){
        return this.friendList.size();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public Klient getPrzyjaciele(int i) {
        return friendList.get(i);
    }
    
    /**
     *
     * @return
     */
    public String getPrzyjaciele() {
        return this.przyjaciele;
    }
    
    /**
     *
     * @param k
     */
    public void addFriendList(Klient k){
        this.friendList.add(k);
    }
    
    /**
     *
     */
    public void clearFriendList(){
        this.friendList.clear();
    }
    
    /**
     *
     * @param baza
     */
    public void setBaza(Baza baza){
        this.baza=baza;
    }

    /**
     *
     * @param k
     */
    public void addListaKlientow(Klient k) {
        this.friendList.add(k);
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @return
     */
    public String getHaslo() {
        return haslo;
    }

    /**
     *
     * @return
     */
    public String getTagi() {
        return tagi;
    }

    /**
     *
     * @param tagi
     */
    public void setTagi(String tagi) {
        this.tagi = tagi;
    }
    
    /**
     *
     * @param haslo
     */
    public void setHaslo(String haslo){
        this.haslo = haslo;
    }
    
    /**
     *
     * @return
     */
    public String getImie(){
        return super.getImie();
    }
    
    /**
     *
     * @return
     */
    public String getNazwa(){
        return super.getNazwa();
    }
    
    /**
     *
     * @return
     */
    public int getDzien(){
        return super.getDzien();
    }
    
    /**
     *
     * @return
     */
    public int getMiesiac(){
        return super.getMiesiac();
    }
    
    /**
     *
     * @return
     */
    public int getRok(){
        return super.getRok();
    }
    
    /**
     *
     * @return
     */
    public String getMiasto(){
        return super.getMiasto();
    }
    
    /**
     *
     * @return
     */
    public String getMalefemale(){
        return malefemale;
    }
    
    /**
     *
     * @param imie
     */
    public void setImie(String imie){
        super.setImie(imie);
    }
    
    /**
     *
     * @param nazwa
     */
    public void setNazwa(String nazwa){
        super.setNazwa(nazwa);
    }
    
    /**
     *
     * @param miasto
     */
    public void setMiasto(String miasto){
        super.setMiasto(miasto);
    }
    
}
