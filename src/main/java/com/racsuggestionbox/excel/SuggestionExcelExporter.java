package com.racsuggestionbox.excel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.racsuggestionbox.entity.SuggestionBO;

public class SuggestionExcelExporter {
	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<SuggestionBO> listSuggestions;
	     
	    public SuggestionExcelExporter(List<SuggestionBO> listSuggestions) {
	        this.listSuggestions = listSuggestions;
	        workbook = new XSSFWorkbook();
	    }
	 
	 
	    private void writeHeaderLine() {
	        sheet = workbook.createSheet("Suggestion");	         
	        Row row = sheet.createRow(0);	         
	        CellStyle style = workbook.createCellStyle();  //cellstyle
	      CreationHelper createHelper =workbook.getCreationHelper();
	      style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
	      
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	         
	        createCell(row, 0, "ID", style);      
	        createCell(row, 1, "First Name", style);       
	        createCell(row, 2, "TopicArea", style);    
	        createCell(row, 3, "Topic", style);
	        createCell(row, 4, "Status", style);
	        createCell(row, 5, "Submitted Date", style);
	         
	    }
	     
	    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } 
	        else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }
	        else if (value instanceof LocalDateTime ) {

	        	
	        	String val =  value.toString();
	        
	        	cell.setCellValue((String) val);
	        }
	        else {
	            cell.setCellValue((String) value);
	        }
	            cell.setCellStyle(style);
	    }
	     
	    private void writeDataLines() {
	        int rowCount = 1;
	 
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	                 
	        for (SuggestionBO suggestion : listSuggestions) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	             
	            createCell(row, columnCount++, suggestion.getSuggestionId(), style);
	            createCell(row, columnCount++, suggestion.getFirstName(), style);
	            createCell(row, columnCount++, suggestion.getTopicArea().getTopicAreaName(), style);
	            createCell(row, columnCount++, suggestion.getTopic().getTopicName(), style);
	            createCell(row, columnCount++, suggestion.getStatus(), style);
	            createCell(row, columnCount++, suggestion.getCreatedDate(), style);
	             
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws IOException {
	        writeHeaderLine();
	        writeDataLines();	         
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();	         
	        outputStream.close();
	         
	    }
}
