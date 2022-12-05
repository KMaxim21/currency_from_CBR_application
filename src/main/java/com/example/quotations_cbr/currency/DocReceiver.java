package com.example.quotations_cbr.currency;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DocReceiver {

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    static {
        documentBuilderFactory.setNamespaceAware(true);
    }
    private static DocumentBuilder builder;
    private static Document doc = null;
    public static Document getData(LocalDate date) {

        try {
            // Задаем ссылку для получения XML документа
            URL url = new URL("https://cbr.ru/scripts/XML_daily.asp?date_req=" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            // Получаем документ
            builder = documentBuilderFactory.newDocumentBuilder();
            doc = builder.parse(url.openStream());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
