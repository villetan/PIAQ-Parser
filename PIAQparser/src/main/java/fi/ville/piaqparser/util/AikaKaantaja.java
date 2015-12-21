package fi.ville.piaqparser.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;

/**
 *
 * @author ville
 */
public class AikaKaantaja {

    private long dif;
    
    public AikaKaantaja(){
        //tämä siksi ettei joka kierroksella tarvitse laskea
        Date dates1 = new Date(1970,01,01,00,00,00);
        Date dates2 = new Date(2000,01,01,00,00,00);
        dif= dates2.getTime()-dates1.getTime();
        
    }
    
    public long kaannaPegasorinAjastaJavaan(long timeInSecondsSince2000Jan1){
        long timeInLong = dif+timeInSecondsSince2000Jan1*1000l;
        return timeInLong;
    }
    
    public long kaannaJavanAjastaPegasorin(long timeInMSSince1970Jan1){
        long timeInSec= (timeInMSSince1970Jan1-dif)/1000;
        return timeInSec;
    }
}
