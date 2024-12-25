package cn.io0288.ExcelTranslateTool.Excel;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
public class BaiduTranslateApi {
    private static final String TRANS_API_URL = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    private static final String APP_ID = "20190722000320285";
    private static final String SECRET_KEY = "tBFOaXGIaXLb0eZ1GGzY";

    public static String run(String query){
        return run(query, "en", "zh");
    }
    public static String run(String query, String from, String to) {
        try {
            String salt = String.valueOf(System.currentTimeMillis());
            String sign = MD5.create().digestHex(APP_ID + query + salt + SECRET_KEY);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("q", query);
            params.add("from", from);
            params.add("to", to);
            params.add("appid", APP_ID);
            params.add("salt", salt);
            params.add("sign", sign);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(TRANS_API_URL, requestEntity, String.class);
            Thread.sleep(150);//百度翻译API标准版QPS:1高级版QPS:10
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getDst(String res){
        JSONObject obj = JSON.parseObject(res);
        JSONArray transResult = obj.getJSONArray("trans_result");
        if (transResult == null || transResult.isEmpty())
            return "";
        return transResult.getJSONObject(0).getString("dst");
    }
}
