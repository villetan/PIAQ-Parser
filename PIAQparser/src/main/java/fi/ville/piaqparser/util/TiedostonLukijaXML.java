package fi.ville.piaqparser.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import fi.ville.piaqparser.domain.Mittaus;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author ville
 */
//Ohjeita otettu http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
public class TiedostonLukijaXML {

    
    //lue xml Listaksi mittaus-olioita.
    public ArrayList<Mittaus> lueMittauksetListaksi(File file, String nodeName) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        ArrayList<Mittaus> rivitMittauksina = new ArrayList<>();
        HashMap<String, Integer> otsikonIndeksit = lueHeaderMapiksi(file, nodeName);
        AikaKaantaja aikaKaantaja=new AikaKaantaja();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
           
            NodeList nList = doc.getElementsByTagName(nodeName);
            
            for (int i = 1; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                
                Element eElement = (Element) nNode;
                
                Mittaus mittaus = new Mittaus();
                long aika= Long.valueOf(eElement.getElementsByTagName("Data").item(0).getTextContent()).longValue();
                aika=aikaKaantaja.kaannaPegasorinAjastaJavaan(aika);
                Date date = new Date(aika);
                mittaus.setAikaleima(date);
                for (String mittauksenAvain : otsikonIndeksit.keySet()) {
                    mittaus.lisaaMittaus(mittauksenAvain, Double.parseDouble(eElement.getElementsByTagName("Data").item(otsikonIndeksit.get(mittauksenAvain)).getTextContent()));
                }
                rivitMittauksina.add(mittaus);
                
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TiedostonLukijaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TiedostonLukijaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TiedostonLukijaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
       return rivitMittauksina; 
    }

    //Lukee otsikon HashMapiksi jossa avaimet on otsikon nimi ja arvo on sarakkeen numero 0sta lukien.
    // ei ota huomioon aikaleimaa jonka oletetaan olevan ensimmäisenä.
    public HashMap<String, Integer> lueHeaderMapiksi(File file, String nodeName) {
        HashMap<String, Integer> otsikkoRivi = new HashMap<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(nodeName);

            Node nNode = nList.item(0);

            Element eElement = (Element) nNode;
            for (int i = 1; i < eElement.getElementsByTagName("Data").getLength(); i++) {
                otsikkoRivi.put(eElement.getElementsByTagName("Data").item(i).getTextContent(), i);
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TiedostonLukijaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TiedostonLukijaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TiedostonLukijaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return otsikkoRivi;
    }

}
