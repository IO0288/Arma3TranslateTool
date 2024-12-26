package cn.io0288.ExcelTranslateTool.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cn.io0288.ExcelTranslateTool.XML.Project.buildProject;

public class ReadXMLUsingDOM {
    public static Element get(String path) {
        File xmlFile = new File(path); // 替换为你的XML文件路径
        try {
            // 创建DocumentBuilderFactory实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder实例
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // 解析XML文件
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 获取根元素
            return doc.getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 打印所有元素
    public static void foreachXMLPrint(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) childNode;

                if (eElement.getTagName().equals("Project") || eElement.getTagName().equals("Package")) {
                    System.out.printf("\t%s name=\"%s\"\n", eElement.getTagName(), eElement.getAttribute("name"));
                    foreachXMLPrint(eElement.getChildNodes());
                } else if (eElement.getTagName().equals("Key")) {
                    // 语言Key
                    System.out.printf("\t\t%s ID=\"%s\"\n", eElement.getTagName(), eElement.getAttribute("ID"));
                    foreachXMLPrint(eElement.getChildNodes());
                } else {
                    // 语言Item
                    System.out.printf("\t\t\t%s\t=%s\t\n", eElement.getTagName(), eElement.getTextContent());
                }
            }
        }
    }
    // 根据元素对象构建数据实体类
    public static Project getXMLElement(NodeList nodeList) {
        // 获取父元素
        Node parentNode = nodeList.item(0).getParentNode();
        Element parentElement = (Element) parentNode;

        return buildProject(nodeList, parentElement);
    }
}
