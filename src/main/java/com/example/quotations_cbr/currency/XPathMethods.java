package com.example.quotations_cbr.currency;

import org.w3c.dom.Document;

import javax.xml.xpath.*;
import java.time.LocalDate;

public class XPathMethods {

    public static Currency getData(Document document, String id, LocalDate date) {

        // Создаем объект XPathFactory
        XPathFactory xpathFactory = XPathFactory.newInstance();

        // Получаем экзмепляр XPath для создания XPathExpression выражений
        XPath xpath = xpathFactory.newXPath();
        String name = getString(document, xpath, id, "Name");
        // Проверяем что в документе есть требуемое значение
        if (name.equals("")) return null;
        // Получаем значение Value из XML документа
        double value = getValue(document, xpath, id);

        return new Currency(value, date);
    }
    private static double getValue(Document document, XPath xpath, String id) {
        String value = null;
        try {
            //Задаем выражение для получения значение Value из XML документа
            XPathExpression xPathExpression = xpath.compile(
                    "/ValCurs/Valute[@ID='" + id + "']/Value/text()"
            );
            value = (String) xPathExpression.evaluate(document, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        if (value.equals("")) { return 0.0;}
        return Double.parseDouble(value.replace(',', '.').trim());
    }
    private static String getString(Document document, XPath xpath, String id, String required) {
        String name = null;
        try {
            //Задаем выражение для получения проверочного значения Name
            XPathExpression xPathExpression = xpath.compile(
                    "/ValCurs/Valute[@ID='" + id + "']/" + required + "/text()"
            );
            name = (String) xPathExpression.evaluate(document, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return name;
    }
}
