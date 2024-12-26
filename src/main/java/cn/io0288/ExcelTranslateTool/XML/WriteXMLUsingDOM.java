package cn.io0288.ExcelTranslateTool.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class WriteXMLUsingDOM {
    public static void run(String path) {
        try {
            // 创建DocumentBuilderFactory实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder实例
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // 创建Document对象
            Document doc = dBuilder.newDocument();

            // 创建根元素
            Element rootElement = doc.createElement("Books");
            doc.appendChild(rootElement);

            // 创建子元素
            Element bookElement = doc.createElement("Book");
            rootElement.appendChild(bookElement);

            Element idElement = doc.createElement("ID");
            idElement.appendChild(doc.createTextNode("1"));
            bookElement.appendChild(idElement);

            Element nameElement = doc.createElement("Name");
            nameElement.appendChild(doc.createTextNode("Java Basics"));
            bookElement.appendChild(nameElement);

            // 保存XML文件
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path)); // 替换为你的输出文件路径
            transformer.transform(source, result);

            System.out.println("XML file has been written successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
