package cn.io0288.ExcelTranslateTool.XML;

import lombok.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static cn.io0288.ExcelTranslateTool.XML.Key.buildKey;

@Getter
@Setter
@Builder
@ToString
public class Package{
    @NonNull
    private String name;
    private List<Key> keys;
    // 构建Package类
    public static Package buildPackage(NodeList packageChildNodeList, Element parentElement) {
        List<Key> keys = new ArrayList<>();
        for (int i = 0; i < packageChildNodeList.getLength(); i++) {
            Node childNode = packageChildNodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) childNode;
//                System.out.printf("\t\t%s ID=\"%s\"\n", eElement.getTagName(), eElement.getAttribute("ID"));
                keys.add(buildKey(eElement.getChildNodes(), eElement));
            }
        }

        return new Package(parentElement.getAttribute("name"),keys);
    }
}
