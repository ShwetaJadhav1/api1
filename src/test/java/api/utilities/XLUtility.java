package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fO;
	public XSSFWorkbook workbook;
	public XSSFSheet Sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public XLUtility(String path)
	{
		this.path=path;
	}
	

	public int getRowCount(String sheetName)throws IOException
	
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		Sheet=workbook.getSheet(sheetName);
		int rowCount=Sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount ;
	}
	

	public int getCellCount(String sheetName,int rownum) throws IOException
	
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		Sheet=workbook.getSheet(sheetName);
		row=Sheet.getRow(rownum);
		int CellCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return  CellCount;
	}
	
  public String getCellData(String sheetName,int rownum,int colnum)throws IOException
	
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		Sheet=workbook.getSheet(sheetName);
		row=Sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch (Exception e) {
			// TODO: handle exception
			data="";
		}
		workbook.close();
		fi.close();
		return data;
		}

public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
{

	File xlfile=new File(path);
	if(!xlfile.exists())
	{
		workbook=new XSSFWorkbook();
		fO=new FileOutputStream(path);
		workbook.write(fO);
	}
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	
	if(workbook.getSheetIndex(sheetName)==-1)
		workbook.createSheet(sheetName);
	Sheet=workbook.getSheet(sheetName);
	
	if(Sheet.getRow(rownum)==null)
		Sheet.createRow(rownum);
	row=Sheet.getRow(rownum);
	
	cell=row.createCell(colnum);
	cell.setCellValue(data);
	fO=new FileOutputStream(path);
	workbook.write(fO);
	workbook.close();
	fi.close();
	fO.close();
	
}

public void fillGreenColor(String sheetName,int rownum,int colnum)throws IOException
{
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	Sheet=workbook.getSheet(sheetName);
	
	row=Sheet.getRow(rownum);
	cell=row.getCell(colnum);
	
	style=workbook.createCellStyle();
	
	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
}

public void fillRedColor(String sheetName,int rownum,int colnum)throws IOException
{
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	Sheet=workbook.getSheet(sheetName);
	
	row=Sheet.getRow(rownum);
	cell=row.getCell(colnum);
	
	style=workbook.createCellStyle();
	
	style.setFillForegroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	cell.setCellStyle(style);
	workbook.write(fO);
	workbook.close();
	fi.close();
	fO.close();
	
}
}

	
	
	
	
	
	
	
	
	
	
	

