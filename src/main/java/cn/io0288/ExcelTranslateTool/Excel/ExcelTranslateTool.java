package cn.io0288.ExcelTranslateTool.Excel;

import cn.idev.excel.FastExcel;

import java.util.Scanner;

public class ExcelTranslateTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "";
        fileName = "C:\\Users\\zhang\\OneDrive\\桌面\\tmp.xlsx";

        boolean flag = true;
        while (flag && fileName.isEmpty()) {
            System.out.println("输入路径：");
            fileName = scanner.nextLine().trim().replace("\"", "");
            System.out.println("你输入的路径是：" + fileName);
            System.out.println("是否确认?(y/n)");
            if (scanner.nextLine().equals("y"))
                flag = false;
        }
        // 读取 Excel 文件
        FastExcel.read(fileName, DataRow.class, new DataRowListener()).sheet().doRead();
    }
}
