
package excelread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


/**
 *
 * @author bretbartlett
 */
public class Excelread {


    public static List<String> main() throws FileNotFoundException, IOException {
        
    List<String> Ings = new ArrayList<String>();  
        
        
    String inputFile = "/Users/bretbartlett/Documents/Kitchology/Canonical.xls";

    File inputWorkbook = new File(inputFile);
    Workbook w;
    
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      
      for (int k = 0; k < w.getNumberOfSheets() ; k++) {
          
      Sheet sheet = w.getSheet(k);

      for (int j = 0; j < sheet.getColumns(); j++) {
        for (int i = 0; i < sheet.getRows(); i++) {
          Cell cell = sheet.getCell(j, i);
          CellType type = cell.getType();
          if (type == CellType.LABEL) {
            Ings.add(" " + cell.getContents() + " ");
          }
        }
      }
    }
    } catch (BiffException e) {
      e.printStackTrace();
    }
        
        return sortNames.SortIngs(Ings);
        
    }
}
