package cn.io0288.ExcelTranslateTool.XML;

import cn.io0288.ExcelTranslateTool.TranslateApi.BaiduTranslateApi;
import org.w3c.dom.Element;

import java.util.logging.Logger;

public class XMLTranslateTool {
    public static void main(String[] args) throws Exception {
        Element projectElement = ReadXMLUsingDOM.get("C:\\Users\\zhang\\OneDrive\\桌面\\out.xml");
        if (projectElement != null) {
            Project project = ReadXMLUsingDOM.getXMLElement(projectElement.getChildNodes());
            System.out.printf("%s\n", project.getName());
            for (Package pkg : project.getPackages()) {
                System.out.printf("\t%s\n", pkg.getName());
                for (Key key : pkg.getKeys()) {
                    if (!key.getKV().containsKey("Chinesesimp")) {
                        String res = BaiduTranslateApi.run(key.getKV().get("English"));
                        String to = BaiduTranslateApi.getDst(res);
                        key.getKV().put("Chinesesimp", to);
                    }
                    key.getKV().forEach((k, v) -> System.out.printf("\t\t\t%s\n\t\t\t\t%s\n", k, v));
                }
            }
        }
    }
}

