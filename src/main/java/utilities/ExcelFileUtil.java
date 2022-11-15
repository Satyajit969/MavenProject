package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;

	public ExcelFileUtil(String excelpath)throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
		wb= WorkbookFactory.create(fi);
	}

	public int rowcount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}

	public String getcelldata(String sheetname,int row,int column)
	{
		String data=" ";


		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata= (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();

		} 
		return data;
	}

	public void setcelldata(String sheetname,int row,int column,String status,String writeexcel)throws Throwable
	{

		Sheet ws=wb.getSheet(sheetname);

		Row rownum = ws.getRow(row);

		Cell cell = rownum.createCell(column);

		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
	}


	public static void main(String[] args) throws Throwable 
	{

		ExcelFileUtil xl = new ExcelFileUtil("D:\\xlsht\\Book123.xlsx");
		int rc = xl.rowcount("Login");
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String user=xl.getcelldata("Login", i, 0);
			String pass = xl.getcelldata("Login", i, 1);
			System.out.println(user+" "+pass);
			//xl.setcelldata("login", i, 2,  "pass","D:\\xlsht\\result.xlsx");
			//xl.setcelldata("Login", i, 2, "Fail", "D:\\xlsht\\result1.xlsx");
			xl.setcelldata("Login", i, 2, "Blocked", "D:\\xlsht\\result2.xlsx");
		}
	}

}
