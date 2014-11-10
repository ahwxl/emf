package com.bplow.todo.sample.sevice;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;


@Service
public class ReadExcelToDbService {
	
	/**
	 * 读excel
	 * @param is
	 * @throws IOException 
	 */
	public void readExcel(InputStream is) throws IOException{
		POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);//得到第一行
        if (row == null)
            row = sheet.createRow(2);
        HSSFCell cell = row.getCell(0);//得到第一个单元格内容
        if (cell == null)
            cell = row.createCell(3);
        System.out.println(cell.getStringCellValue());
        
        cell = row.getCell(1);//得到第一行的第二个单元格
        System.out.println(cell.getStringCellValue());
		
        cell = row.getCell(1);//得到第二个单元格;
        
        row = sheet.getRow(1);//得到第二行
        cell = row.getCell(0);//得到第二行，第一个单元格
        
        System.out.println(cell.getStringCellValue());
        cell = row.getCell(1);
        System.out.println(cell.getStringCellValue());
	}

}
