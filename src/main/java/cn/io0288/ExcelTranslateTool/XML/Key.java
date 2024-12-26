package cn.io0288.ExcelTranslateTool.XML;

import lombok.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

@Getter
@Setter
@Builder
@ToString
public class Key {
    @NonNull
    private String ID;
    private HashMap<String, String> KV;

    // 构建Key类
    public static Key buildKey(NodeList keyChildNodeList, Element parentElement) {
        HashMap<String, String> kv = new HashMap<>();
        for (int i = 0; i < keyChildNodeList.getLength(); i++) {
            Node childNode = keyChildNodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) childNode;
//                System.out.printf("\t\t\t%s\t=%s\t%n", eElement.getTagName(), eElement.getTextContent());
                kv.put(eElement.getTagName(), eElement.getTextContent());
            }
        }

        return new Key(parentElement.getTagName(), kv);
    }
}
