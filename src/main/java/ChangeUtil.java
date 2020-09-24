import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ChangeUtil {
    public static File[] getDirectory() {
        File file = new File("D:\\chromeDownload\\2020航天城\\2020航天城");
        int i = 0;
        if(file.isDirectory()) {
            File[] fileGet = file.listFiles();
            for(File file1 : fileGet) {
                String fileOriName = file1.getName().toLowerCase().replace(".jpg","");
                String newName = getNumFromExcl("S"+fileOriName);
                if("".equals(newName)){
                    System.out.println("原名称："+fileOriName+"因无法在模板中找到对应身份信息修改失败");
                    continue;
                }
                if(file1.renameTo(new File("C:\\Users\\A\\Desktop\\meta\\pic航天城2020"+"\\"+newName+".JPG"))) {
                    //System.out.println(fileOriName+"修改成功");
                }else{
                    System.out.println(fileOriName+"修改失败 原因："+newName+"  该名称已存在");
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        getDirectory();
    }
    public static String getNumFromExcl(String ori) {
        Workbook wb =null;
        Sheet sheet = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = "C:\\Users\\A\\Desktop\\meta\\11.xlsx";
        wb = readExcel(filePath);
        sheet = wb.getSheetAt(0);
        int length = sheet.getLastRowNum();
        for(int i = 0; i<length; i++){
            Row row = sheet.getRow(i);
            String oriTmpl = row.getCell(0).getStringCellValue();
            if(oriTmpl.equals(ori)){
                return  String.valueOf(row.getCell(1).getStringCellValue());
            }
        }
        return "";
    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
}

