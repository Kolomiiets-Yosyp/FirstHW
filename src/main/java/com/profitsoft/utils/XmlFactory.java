package com.profitsoft.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlFactory {
    public void toXml(String directoryPath, HashMap<String, HashMap<String, Integer>> toXml) {
        for (String key : toXml.keySet()) {
            String fileName = directoryPath + File.separator + "statistics_by_" + key + ".xml";
            writeXml(fileName, toXml.get(key), key);
        }
    }

    private void writeXml(String fileName, HashMap<String, Integer> attributeCount, String key) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("statistics");
            doc.appendChild(rootElement);

            for (String subKey : attributeCount.keySet()) {
                Node attribute = createAttributeElement(doc, subKey, attributeCount.get(subKey));
                rootElement.appendChild(attribute);
            }

            // Записуємо XML в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Встановлюємо відступи
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Node createAttributeElement(Document doc, String value, Integer count) {
        Element item = doc.createElement("item");

        item.appendChild(createAttributeElements(doc, item, "value", value));
        item.appendChild(createAttributeElements(doc, item, "count", count.toString()));

        return item;
    }

    private Node createAttributeElements(Document doc, Element item, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
