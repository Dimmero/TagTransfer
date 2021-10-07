import java.io.*;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import test.CycleForStats;

public class OutputToExcel {
    private HSSFWorkbook wb1;
    private  FileOutputStream fileOut;
    private  HSSFSheet sheet;

    public HSSFWorkbook getWb1() {
        return wb1;
    }

    public FileOutputStream getFileOut() {
        return fileOut;
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public OutputToExcel() throws FileNotFoundException {
        this.wb1 = new HSSFWorkbook();
        this.fileOut = new FileOutputStream("C:\\Users\\Dimmer\\Desktop\\Warranty(copy)\\b.xlsx");
        this.sheet = wb1.createSheet("Tags");
    }

    public static OutputToExcel outputToFile(OutputToExcel outputToExcel) throws IOException {

        for (int i = 0; i < ServiceTagParsing.listOfServiceTags.size(); i++) {
            outputToExcel.sheet.createRow(i);
            outputToExcel.sheet.getRow(i).createCell(0).setCellValue(ServiceTagParsing.listOfServiceTags.get(i));
        }
        outputToExcel.wb1.write(outputToExcel.fileOut);
        outputToExcel.wb1.close();
        return outputToExcel;
    }

    public static void outputToFileStats(OutputToExcel output) throws IOException {
        for (int i = 0; i < ServiceTagParsing.listOfServiceTags.size(); i++) {
                SeleniumDriver.initDriver("https://www.dell.com/support/home/pl-pl?app=products");
                output.fileOut = new FileOutputStream("C:\\Users\\Dimmer\\Desktop\\Warranty(copy)\\b.xlsx");
                CycleForStats.getACycle(i, output.fileOut, output.sheet, output.wb1);
                SeleniumDriver.closeDriver();
        }
    }
}
