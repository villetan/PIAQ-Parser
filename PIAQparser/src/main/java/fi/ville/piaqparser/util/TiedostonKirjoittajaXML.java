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
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ville Tiedoston kirjoittamiseen tarkoitettu luokka. Kirjoittaa XML
 * muotoista dataa, joka on tarkoitettu avattavaksi erityisesti Libre officella
 * ja Excelillä.
 */
public class TiedostonKirjoittajaXML {

    private AikaKaantaja aikaKaantaja;
    private String saveToFilePath;

    public TiedostonKirjoittajaXML() {
        aikaKaantaja = new AikaKaantaja();

    }

    /**
     * Tiedoston kirjoittava metodi. Kirjoittaa uuden XML tiedoston.
     *
     *
     * @param mittaukset Luetut ja javassa muokatut mittaukset, valmiina
     * kirjoitukseen.
     */
    public void kirjoitaTiedosto(ArrayList<Mittaus> mittaukset) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder tiedostonRakentaja = factory.newDocumentBuilder();
            Document dokumentti = tiedostonRakentaja.newDocument();
            dokumentti.setXmlVersion("1.1");
            Element workbook = createWorkbookNode(dokumentti);
            createExcelSettings(dokumentti, workbook, mittaukset);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(dokumentti);
            StreamResult result = new StreamResult(new File("src/main/resources/"));

            if (kirjoitaTiedostoksi(result, transformer, source)) return;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TiedostonKirjoittajaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private boolean kirjoitaTiedostoksi(StreamResult result, Transformer transformer, DOMSource source) throws TransformerException {
        if (saveToFilePath != null) {
            File file = new File(getSaveToFilePath());
            if (!file.exists()) {
                result = new StreamResult(new File(getSaveToFilePath()));
            } else {
                for (int i = 1; i < 100; i++) {
                    File uusiFile = new File(file.getPath().replace(".xml", "(" + i + ").xml"));
                    result = new StreamResult(uusiFile);
                    if (kirjoitaUusiFileJosEdellinenOnJoOlemassa(uusiFile, transformer, source)) {
                        return true;
                    }
                }
            }
        }
        transformer.transform(source, result);
        return false;
    }

    private void createExcelSettings(Document dokumentti, Element workbook, ArrayList<Mittaus> mittaukset) throws DOMException {
        createDocumentPropertiesNode(dokumentti, workbook);
        createOfficeDocumentSettingsNode(dokumentti, workbook);
        createExcelWorkbookNode(dokumentti, workbook);
        createStylesNode(dokumentti, workbook);
        createWorksheetNode(dokumentti, workbook, mittaukset);
    }

    private boolean kirjoitaUusiFileJosEdellinenOnJoOlemassa(File uusiFile, Transformer transformer, DOMSource source) throws TransformerException {
        StreamResult result;
        if (!uusiFile.exists()) {
            result = new StreamResult(uusiFile);
            transformer.transform(source, result);
            return true;
        }
        return false;
    }

    private void createWorksheetNode(Document dokumentti, Element workbook, ArrayList<Mittaus> mittaukset) throws DOMException {
        Element worksheet = dokumentti.createElement("Worksheet");
        workbook.appendChild(worksheet);
        worksheet.setAttribute("ss:Name", "Sheet1");

        Element table = dokumentti.createElement("Table");
        worksheet.appendChild(table);
        table.setAttribute("ss:ExpandedColumnCount", "12");
        table.setAttribute("ss:ExpandedRowCount", "1000");
        table.setAttribute("x:FullColumns", "1");
        table.setAttribute("x:FullRows", "1");
        table.setAttribute("ss:DefaultRowHeight", "15");

        Element column1 = dokumentti.createElement("Column");
        table.appendChild(column1);
        column1.setAttribute("ss:Width", "52.5");
        column1.setAttribute("ss:Span", "1");
        Element column2 = dokumentti.createElement("Column");
        table.appendChild(column2);
        column2.setAttribute("ss:Index", "3");
        column2.setAttribute("ss:Width", "48.75");
        column2.setAttribute("ss:Span", "9");

        kirjoitaOtsikkoRivi(dokumentti, table, mittaukset);

        kirjoitaMittaukset(mittaukset, dokumentti, table);

        createWorksheetOptionsNode(dokumentti, worksheet);
    }

    private void createWorksheetOptionsNode(Document dokumentti, Element worksheet) throws DOMException {
        Element worksheetOptions = dokumentti.createElement("WorksheetOptions");
        worksheet.appendChild(worksheetOptions);
        worksheetOptions.setAttribute("xmlns", "urn:schemas-microsoft-com:office:excel");

        Element selected = dokumentti.createElement("Selected");
        worksheetOptions.appendChild(selected);

        Element panes = dokumentti.createElement("Panes");
        worksheetOptions.appendChild(panes);

        Element pane = dokumentti.createElement("Pane");
        panes.appendChild(pane);

        Element number = dokumentti.createElement("Number");
        pane.appendChild(number);
        number.setTextContent("3");

        Element activeRow = dokumentti.createElement("ActiveRow");
        pane.appendChild(activeRow);
        activeRow.setTextContent("8");

        Element activeCol = dokumentti.createElement("ActiveCol");
        pane.appendChild(activeCol);
        activeCol.setTextContent("12");

        Element protectObjects = dokumentti.createElement("ProtectObjects");
        worksheetOptions.appendChild(protectObjects);
        protectObjects.setTextContent("False");

        Element protectScenarios = dokumentti.createElement("ProtectScenarios");
        worksheetOptions.appendChild(protectScenarios);
        protectScenarios.setTextContent("False");
    }

    private void kirjoitaMittaukset(ArrayList<Mittaus> mittaukset, Document dokumentti, Element table) throws DOMException {
        for (Mittaus mittaus : mittaukset) {

            kirjoitaYksiRivi(dokumentti, table, mittaus);

        }
    }

    private void kirjoitaYksiRivi(Document dokumentti, Element table, Mittaus mittaus) throws DOMException {
        Element row = dokumentti.createElement("Row");
        table.appendChild(row);

        Element cell = dokumentti.createElement("Cell");
        row.appendChild(cell);

        Element data = dokumentti.createElement("Data");
        cell.appendChild(data);
        data.setAttribute("ss:Type", "Number");
        data.setTextContent("" + aikaKaantaja.kaannaJavanAjastaPegasorin(mittaus.getAikaleima().getTime()));

        kirjoitaYksiSolu(mittaus, dokumentti, row);
    }

    private void kirjoitaYksiSolu(Mittaus mittaus, Document dokumentti, Element row) throws DOMException {
        for (String mittauksenAvain : mittaus.getMittaukset().keySet()) {

            Element cellMittaus = dokumentti.createElement("Cell");
            row.appendChild(cellMittaus);

            Element dataMittaus = dokumentti.createElement("Data");
            cellMittaus.appendChild(dataMittaus);

            dataMittaus.setAttribute("ss:Type", "Number");
            dataMittaus.setTextContent("" + mittaus.getMittauksenArvo(mittauksenAvain));

        }
    }

    private void createStylesNode(Document dokumentti, Element workbook) throws DOMException {
        Element styles = dokumentti.createElement("Styles");
        workbook.appendChild(styles);

        Element style = dokumentti.createElement("Style");
        styles.appendChild(style);
        style.setAttribute("ss:ID", "Default");
        style.setAttribute("ss:Name", "Normal");

        Element alignment = dokumentti.createElement("Alignment");
        style.appendChild(alignment);
        alignment.setAttribute("ss:Vertical", "Bottom");

        Element borders = dokumentti.createElement("Borders");
        style.appendChild(borders);

        Element font = dokumentti.createElement("Font");
        style.appendChild(font);
        font.setAttribute("ss:FontName", "Calibri");
        font.setAttribute("x:Family", "Swiss");
        font.setAttribute("ss:Size", "11");
        font.setAttribute("ss:Color", "#000000");

        Element interior = dokumentti.createElement("Interior");
        style.appendChild(interior);

        Element numberFormat = dokumentti.createElement("NumberFormat");
        style.appendChild(numberFormat);

        Element protection = dokumentti.createElement("Protection");
        style.appendChild(protection);
    }

    private void createExcelWorkbookNode(Document dokumentti, Element workbook) throws DOMException {
        Element excelWorkbook = dokumentti.createElement("ExcelWorkbook");
        workbook.appendChild(excelWorkbook);
        excelWorkbook.setAttribute("xmlns", "urn:schemas-microsoft-com:office:excel");

        Element windowHeight = dokumentti.createElement("WindowHeight");
        excelWorkbook.appendChild(windowHeight);
        windowHeight.setTextContent("12345");

        Element windowWidth = dokumentti.createElement("WindowWidth");
        excelWorkbook.appendChild(windowWidth);
        windowWidth.setTextContent("28800");

        Element windowTopX = dokumentti.createElement("WindowTopX");
        excelWorkbook.appendChild(windowTopX);
        windowTopX.setTextContent("0");

        Element windowTopY = dokumentti.createElement("WindowTopY");
        excelWorkbook.appendChild(windowTopY);
        windowTopY.setTextContent("0");

        Element protectStructure = dokumentti.createElement("ProtectStructure");
        excelWorkbook.appendChild(protectStructure);
        protectStructure.setTextContent("False");

        Element protectWindows = dokumentti.createElement("ProtectWindows");
        excelWorkbook.appendChild(protectWindows);
        protectWindows.setTextContent("False");
    }

    private void createOfficeDocumentSettingsNode(Document dokumentti, Element workbook) throws DOMException {
        Element officeDocumentSettings = dokumentti.createElement("OfficeDocumentSettings");
        workbook.appendChild(officeDocumentSettings);
        officeDocumentSettings.setAttribute("xmlns", "urn:schemas-microsoft-com:office:office");

        Element allowPNG = dokumentti.createElement("AllowPNG");
        officeDocumentSettings.appendChild(allowPNG);
    }

    private void createDocumentPropertiesNode(Document dokumentti, Element workbook) throws DOMException {
        Element documentProperties = dokumentti.createElement("DocumentProperties");
        workbook.appendChild(documentProperties);

        documentProperties.setAttribute("xmlns", "urn:schemas-microsoft-com:office:office");

        Element author = dokumentti.createElement("Author");
        documentProperties.appendChild(author);
        author.setTextContent("Omistaja");

        Element lastAuthor = dokumentti.createElement("LastAuthor");
        documentProperties.appendChild(lastAuthor);
        lastAuthor.setTextContent("Omistaja");

        Element created = dokumentti.createElement("Created");
        documentProperties.appendChild(created);
        created.setTextContent("" + new Date(System.currentTimeMillis()));

        Element version = dokumentti.createElement("Version");
        documentProperties.appendChild(version);
        version.setTextContent("15.00");
    }

    private Element createWorkbookNode(Document dokumentti) throws DOMException {
        Element workbook = dokumentti.createElement("Workbook");
        dokumentti.appendChild(workbook);
        workbook.setAttribute("xmlns", "urn:schemas-microsoft-com:office:spreadsheet");
        workbook.setAttribute("xmlns:o", "urn:schemas-microsoft-com:office:office");
        workbook.setAttribute("xmlns:x", "urn:schemas-microsoft-com:office:excel");
        workbook.setAttribute("xmlns:ss", "urn:schemas-microsoft-com:office:spreadsheet");
        workbook.setAttribute("xmlns:html", "http://www.w3.org/TR/REC-html40");
        return workbook;
    }

    private void kirjoitaOtsikkoRivi(Document dokumentti, Element table, ArrayList<Mittaus> mittaukset) throws DOMException {
        Element rowHeader = dokumentti.createElement("Row");
        table.appendChild(rowHeader);
        Mittaus mittaus1 = mittaukset.get(0);
        Element cellTime = dokumentti.createElement("Cell");
        rowHeader.appendChild(cellTime);

        Element dataTime = dokumentti.createElement("Data");
        cellTime.appendChild(dataTime);
        dataTime.setAttribute("ss:Type", "String");
        dataTime.setTextContent("Time");

        for (String mittauksenAvain : mittaus1.getMittaukset().keySet()) {

            Element cell = dokumentti.createElement("Cell");
            rowHeader.appendChild(cell);

            Element data = dokumentti.createElement("Data");
            cell.appendChild(data);
            data.setAttribute("ss:Type", "String");
            data.setTextContent(mittauksenAvain);
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
