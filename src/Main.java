//mine
import Class.Schodule;
import Class.Payment;
//apache
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//standart
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] BFD= new String[4];
        try {
            FileInputStream streamL = new FileInputStream("input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(streamL));
            String buffer;
            int i=0;
            while ((buffer = reader.readLine()) != null) {
                BFD[i]=buffer;
                ++i;
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }
        Schodule IFTFT=new Schodule(Integer.valueOf(BFD[0]), BFD[1], BFD[2], BFD[3]);
        IFTFT.calculate();
        List<Payment> a=IFTFT.getList();
        Collections.sort(a, IFTFT.order());

        Workbook table = new HSSFWorkbook();
        Sheet sheet = table.createSheet("Schedule");
        Row Start = sheet.createRow(0);
        Cell head  = Start.createCell(1);
        head.setCellValue("Гафик плановых платежей равными долями");

        Row row = sheet.createRow(2);
        Cell date = row.createCell(0);
        Cell plat = row.createCell(1);
        date.setCellValue("Дата");
        plat.setCellValue("Платеж");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            for (int i = 0; i < a.size(); i++) {
                row = sheet.createRow(i + 3);
                date = row.createCell(0);
                sheet.autoSizeColumn(0);
                date.setCellValue(formatter.format(a.get(i).getPaymentDate().getTime()));

                plat = row.createCell(1);
                plat.setCellValue(String.valueOf(a.get(i).getAmount()));
            }
            table.write(new FileOutputStream("output.xls"));
            table.close();

        }catch (IOException error){
            error.printStackTrace();
        }
    }
}
