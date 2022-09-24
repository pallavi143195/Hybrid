package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String excelpath)throws Throwable{
		FileInputStream fi=new FileInputStream(excelpath);
		wb=new XSSFWorkbook(fi);
	}
	//count no of rows in sheet
	public int rowcount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//count no of cell in a row
	public int cellcount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//get cell data
	public String getcelldata(String sheetname,int row,int column)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata =(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//write set cell data
	public void setcelldata(String sheetname,int row,int column,String status,String writeexcelpath) throws Throwable
	{
		//get sheet from wb
		XSSFSheet ws=wb.getSheet(sheetname);
		//get  row from sheet
		XSSFRow rownum=ws.getRow(row);
		//create cell 
		XSSFCell cell=rownum.createCell(column);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
	
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
			
		}
		else if(status.equalsIgnoreCase("blocked"))
		{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
			
		}
		
		FileOutputStream fo=new FileOutputStream(writeexcelpath);
		wb.write(fo);
	}
	public static void main(String[] args)throws Throwable {
		ExcelFileUtil xl=new ExcelFileUtil("D:\\pallavi\\DDT_framework\\TestInput\\TestData.xlsx");
		//count no of rows
		int rc=xl.rowcount("Login");
		int cc=xl.cellcount("Login");
		System.out.println(rc+" "+cc);
		for (int i = 1; i < rc; i++)
		{
			String user=xl.getcelldata("Login", i,0);
			String pass=xl.getcelldata("Login", i, 1);
			System.out.println(user+" "+pass);
			xl.setcelldata("Login", i,2,"pass","D:\\pallavi\\DDT_framework\\TestOutput\\TestDataaa.xlsx");	
			//xl.setcelldata("Login", i,2,"failed","D:\\pallavi\\DDT_framework\\TestOutput\\TestDataaa.xlsx");
			//xl.setcelldata("Login", i,2,"blocked","D:\\pallavi\\DDT_framework\\TestOutput\\TestDataaa.xlsx");
		}
	}
}
