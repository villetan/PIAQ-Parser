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
 * @author ville Luokka, joka sisältää logiikan XML-tiedostojen lukemiseen.
 * Ohjeita otettu
 * http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 *
 */
public class TiedostonLukijaXML {

    /**
     *
     * @param file luettava tiedosto
     * @param nodeName luettavan noden nimi, Näissä mittauksissa ROW
     * @return ArrayList mittaus-olioista jotka kuvaavat taulukon yhtä riviä.
     */
    public ArrayList<Mittaus> lueMittauksetListaksi(File file, String nodeName) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        ArrayList<Mittaus> rivitMittauksina = new ArrayList<>();
        HashMap<String, Integer> otsikonIndeksit = lueHeaderMapiksi(file, nodeName);
        AikaKaantaja aikaKaantaja = new AikaKaantaja();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(nodeName);

            for (int i = 1; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                Element eElement = (Element) nNode;
                
                Mittaus mittaus = new Mittaus();
                if(eElement.getElementsByTagName("Data").item(0).getTextContent().contains("Pegasor") || eElement.getElementsByTagName("Data").item(0).getTextContent().contains("serial") || eElement.getElementsByTagName("Data").item(0).getTextContent().contains("Time")){
                    continue;
                }
                if (eElement.getElementsByTagName("Data").item(0).getAttributes().getNamedItem("ss:Type").getNodeValue().contains("DateTime")) {

                    String luettuAika = eElement.getElementsByTagName("Data").item(0).getTextContent();
                    
                    String[] splitattu = luettuAika.split("T");
                    String dateOsio = splitattu[0];
                    String timeOsio = splitattu[1];
                    
                    String[] dateSplitattu = dateOsio.split("-");
                    int vuosi = Integer.parseInt(dateSplitattu[0]);
                    int kk = Integer.parseInt(dateSplitattu[1]);
                    int paiva = Integer.parseInt(dateSplitattu[2]);
                    String[] timeSplitattu = timeOsio.split(":");
                    int tunti = Integer.parseInt(timeSplitattu[0]);
                    int minuutti = Integer.parseInt(timeSplitattu[1]);
                    String sek = timeSplitattu[2].replace(".000", "");
                    int sekunti = Integer.parseInt(sek);
                    Date date = new Date(vuosi - 1900, kk - 1, paiva, tunti, minuutti, sekunti);

                    mittaus.setAikaleima(date);
                } else {
                    long aika = Long.valueOf(eElement.getElementsByTagName("Data").item(0).getTextContent()).longValue();

                    aika = aikaKaantaja.kaannaPegasorinAjastaJavaan(aika);
                    Date date = new Date(aika);
                    mittaus.setAikaleima(date);
                }
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

    /**
     * Lukee taulukkotiedoston otsikkorivin hasmapiksi, jossa avain on sarakkeen
     * arvo ja arvo on indeksi. Ei ota huomioon aikaleimaa, jonka oletetaan
     * olevan ensimmäinen sarake.
     *
     * @param file luettava tiedosto
     * @param nodeName luettavan solmun nimi, tässä tapauksessa yleisesti Row
     * @return HashMap, jossa avaimena sarakkeen nimi ja arvona indeksi
     */
    public HashMap<String, Integer> lueHeaderMapiksi(File file, String nodeName) {
        HashMap<String, Integer> otsikkoRivi = new HashMap<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(nodeName);
            Node nNode = nList.item(0);
            for (int j=0;j<nList.getLength(); j++) {
                Element riviElement=(Element) nList.item(j);
                if(riviElement.getElementsByTagName("Data").item(0).getTextContent().contains("Time")){
                   nNode=riviElement; 
                }
            }
            

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
