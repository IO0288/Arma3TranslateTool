package cn.io0288.ExcelTranslateTool.TranslateApi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestApi {
    @Test
    public void test() {
        String a = "apple";
        String res = BaiduTranslateApi.run(a);
        String dst = BaiduTranslateApi.getDst(res);

        assertEquals("apple should equal 苹果", "苹果", dst);
    }
}
