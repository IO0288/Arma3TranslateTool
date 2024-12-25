package cn.io0288.ExcelTranslateTool.Excel;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class DataRow {
    @ExcelProperty("id")
    private String id;
    @ExcelProperty("en")
    private String english;
    @ExcelProperty("zh")
    private String chinesesimp;
}
