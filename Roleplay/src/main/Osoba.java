/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author pc
 */
public abstract class Osoba {
    private String imie;
    private String nazwa;
    final int dzien;
    final int miesiac;
    final int rok; 
    private int age;
    private String miasto;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    /**
     *
     * @param imie
     * @param nazwa
     * @param dzien
     * @param miesiac
     * @param rok
     * @param miasto
     */
    public Osoba(String imie, String nazwa, int dzien, int miesiac, int rok, String miasto) {
        this.imie = imie;
        this.nazwa = nazwa;
        this.dzien = dzien;
        this.miesiac = miesiac;
        this.rok = rok;
        this.miasto = miasto;
    }
    
    /**
     *
     * @return
     */
    public int getAge(){
        
            calendar = Calendar.getInstance();
            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
            int nowMonth = calendar.get(Calendar.MONTH);
            int nowYear = calendar.get(Calendar.YEAR);
            
            int age = nowYear-rok;
            
            if (nowMonth < miesiac) {
                age--;
            } else if ((nowMonth == miesiac) && (nowDay < dzien)) {
                age--;
                if (age < 0) {
                    age = 0;
                }
            }
            
        return age;
    }

    /**
     *
     * @return
     */
    public String getImie() {
        return imie;
    }

    /**
     *
     * @param imie
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     *
     * @return
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     *
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     *
     * @return
     */
    public int getDzien() {
        return dzien;
    }

    /**
     *
     * @return
     */
    public int getMiesiac() {
        return miesiac;
    }

    /**
     *
     * @return
     */
    public int getRok() {
        return rok;
    }

    /**
     *
     * @return
     */
    public String getMiasto() {
        return miasto;
    }

    /**
     *
     * @param miasto
     */
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

}
