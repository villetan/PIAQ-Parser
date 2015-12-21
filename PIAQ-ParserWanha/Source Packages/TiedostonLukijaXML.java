
import hiekkalaatikko.Mittaus;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ville
 */
public class TiedostonLukijaXML {
    
 public ArrayList<Mittaus> lueXMLtiedosto(File file, String nodeName) {
        //Lisaa mittauksen mittaukset hashmpaille mittaukset!
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        ArrayList<Mittaus> rivitMittauksina = new ArrayList<>();
        HashMap<String, Integer> otsikonIndeksit = lueaHeaderMapiksi(file, nodeName);
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName(nodeName);
            int size=0;
            for (int i = 1; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                //System.out.println("Current element: " + nNode.getNodeName());
                Element eElement = (Element) nNode;
                //Esim. hakee sootA arvon.
                //System.out.println("SootA: " + eElement.getElementsByTagName("Data").item(1).getTextContent());
                Mittaus mittaus = new Mittaus();
                //lisataan mittaukset, ei aikaa.
                //Mittaus aika standardi toistaiseksi
                mittaus.setAikaleima(new Date(123456678));
                for (String mittauksenAvain : otsikonIndeksit.keySet()) {
                    mittaus.lisaaMittaus(mittauksenAvain, Double.parseDouble(eElement.getElementsByTagName("Data").item(otsikonIndeksit.get(mittauksenAvain)).getTextContent()));
                }
                rivitMittauksina.add(mittaus);
                size++;
            }
            System.out.println("Mittauksia käyty läpi: "+size);
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
    public HashMap<String, Integer> lueaHeaderMapiksi(File file, String nodeName) {
        HashMap<String, Integer> otsikkoRivi = new HashMap<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName(nodeName);

            Node nNode = nList.item(0);
            System.out.println("Otsikkorivi: " + nNode.getNodeName());

            Element eElement = (Element) nNode;
            //keino jotta saat hashmappiin otsikon nimet avaimiksi ja indeksit arvoiksi!! sitten item(n) palauttaa n:n sarakkeen!
            System.out.println("Node size: " + eElement.getElementsByTagName("Data").getLength());
            //tämä toimii, nyt lista size oikein! Tässä haetaan otsikkoriviä, nyt vain otsikkorivin koko, jotta voidaan laittaa indeksit kohdilleen
            System.out.println("Esimerkki node sisältö:" + eElement.getElementsByTagName("Data").item(0).getTextContent());
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
