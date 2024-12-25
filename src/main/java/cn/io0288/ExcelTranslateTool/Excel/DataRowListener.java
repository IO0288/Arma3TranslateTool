package cn.io0288.ExcelTranslateTool.Excel;

import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;

// 实现 ReadListener 接口，设置读取数据的操作
public class DataRowListener implements ReadListener<DataRow> {
    private List<DataRow> dataList = new ArrayList<>();
    @Override
    public void invoke(DataRow data, AnalysisContext context) {
        if (data.getChinesesimp() == null){
            String res = BaiduTranslateApi.run(data.getEnglish());
            String dst = BaiduTranslateApi.getDst(res);
            System.out.println("返回结果:\t" + dst);
            data.setChinesesimp(dst);

//            data.setChinesesimp("");
        }
        dataList.add(data);
        System.out.println("解析到一条数据" + JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据解析完成！共读取了 " + dataList.size() + " 条数据");
        System.out.println(JSON.toJSONString(dataList));

        // 创建一个名为“模板”的 sheet 页，并写入数据
        FastExcel.write("C:\\Users\\zhang\\OneDrive\\桌面\\out.xlsx", DataRow.class)
                .sheet("模板").doWrite(dataList);
    }
}
