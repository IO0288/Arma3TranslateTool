package cn.io0288.ExcelTranslateTool.XML;

import lombok.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cn.io0288.ExcelTranslateTool.XML.Key.buildKey;
import static cn.io0288.ExcelTranslateTool.XML.Package.buildPackage;

@Getter
@Setter
@Builder
@ToString
public class Project {
    @NonNull
    private String name;
    private List<Package> packages;
    // Project
    public static Project buildProject(NodeList rootChildNodeList, Element parentElement) {
        List<Package> packages = new ArrayList<>();
        for (int i = 0; i < rootChildNodeList.getLength(); i++) {
            Node childNode = rootChildNodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) childNode;
//                System.out.printf("\t%s name=\"%s\"\n", eElement.getTagName(), eElement.getAttribute("name"));
                packages.add(buildPackage(eElement.getChildNodes(), eElement));
            }
        }

        return new Project(parentElement.getAttribute("name"),packages);
    }
}

