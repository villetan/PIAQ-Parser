/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.services.TiedostonLukijaPalvelu;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ville
 */
public class TiedostonKirjoittajaXML {

    
    //JAXB -Ooppa github
    public void kirjoitaTiedosto(ArrayList<Mittaus> mittaukset, ArrayList<String> otsikko) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder tiedostonRakentaja = factory.newDocumentBuilder();
            Document dokumentti = tiedostonRakentaja.newDocument();
            Element root = dokumentti.createElement("Workbook");
            dokumentti.appendChild(root);

            Element worksheet = dokumentti.createElement("Worksheet");
            root.appendChild(worksheet);
            int i = 0;
            for (Mittaus mittaus : mittaukset) {

                Element row = dokumentti.createElement("Row");
                worksheet.appendChild(row);

                for (String mittauksenAvain : mittaus.getMittaukset().keySet()) {
                    if (i == 0) {
                        Element cell = dokumentti.createElement("Cell");
                        row.appendChild(cell);

                        Element data = dokumentti.createElement("Data");
                        cell.appendChild(data);
                        data.setAttribute("otsikko", mittauksenAvain);
                    } else {
                        Element cell = dokumentti.createElement("Cell");
                        row.appendChild(cell);

                        Element data = dokumentti.createElement("Data");
                        cell.appendChild(data);

                        data.setAttribute(mittauksenAvain, "" + mittaus.getMittauksenArvo(mittauksenAvain));
                    }
                }
                i++;
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(dokumentti);
            StreamResult result = new StreamResult(new File("/home/ville/file.xml"));

            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TiedostonKirjoittajaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
