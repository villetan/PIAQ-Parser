/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ville
 */
public class TiedostonKirjoittajaCSV {
private String saveToFilePath;
    
    public void kirjoitaTiedosto(ArrayList<Mittaus> mittaukset) {
        String tiedostonNimi = ""+(mittaukset.get(0).palautaAikaleimaPVM() + "T" + mittaukset.get(0).palautaAikaleimaKellonaika() + "_" + mittaukset.get(mittaukset.size() - 1).palautaAikaleimaPVM() + "T" + mittaukset.get(mittaukset.size() - 1).palautaAikaleimaKellonaika() + ".csv");
        setSaveToFilePath(tiedostonNimi);
        try {
            
            File uusiFile = new File(tiedostonNimi);
            int i=1;
            while(true){
                if(!uusiFile.exists()){
                    break;
                }
                uusiFile=new File((tiedostonNimi+"("+i+")").replace(".csv", "")+".csv");
                i++;
            }
            FileWriter writer = new FileWriter(uusiFile,false);
            writer.append("Time");
            writer.append(",");
            for(String key : mittaukset.get(0).getMittaukset().keySet()){
                writer.append(key);
                writer.append(",");
            }
            writer.append("\n");
            for(Mittaus m:mittaukset){
                writer.append(m.palautaAikaleimaPVM()+"T"+m.palautaAikaleimaKellonaika());
                writer.append(",");
                for(String key :m.getMittaukset().keySet()){
                   writer.append(m.getMittauksenArvo(key)+"");
                   writer.append(",");
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TiedostonKirjoittajaCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the saveToFilePath
     */
    public String getSaveToFilePath() {
        return saveToFilePath;
    }

    /**
     * @param saveToFilePath the saveToFilePath to set
     */
    public void setSaveToFilePath(String saveToFilePath) {
        this.saveToFilePath = saveToFilePath;
    }
}
