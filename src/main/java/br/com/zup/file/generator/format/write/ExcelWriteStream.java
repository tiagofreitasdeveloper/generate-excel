package br.com.zup.file.generator.format.write;

import br.com.zup.file.generator.format.annotation.AttributeFile;
import br.com.zup.file.generator.format.gateway.http.exception.RecordNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ExcelWriteStream implements DocumentTypeWrite{

    public ByteArrayInputStream write(List<?> objects) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        writeCells(workbook, objects);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (FileNotFoundException e) {
            throw new RecordNotFoundException("arquivo não encontrado");
        } catch (IOException e) {
            throw new Exception("IO Exception");
        }

    }

    private void writeCells(SXSSFWorkbook workbook, List<?> objects) {
        SXSSFSheet sheet = workbook.createSheet("Custodia");
        SXSSFRow rowHeader = sheet.createRow(0);

        int rowIndex = 1;

        for (Object obj : objects) {
            Class clazz = obj.getClass();
            SXSSFRow row = sheet.createRow(rowIndex++);

            int columnIndex = 0;

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Annotation annotation = field.getAnnotation(AttributeFile.class);

                if (annotation != null) {
                    AttributeFile annotationFile = (AttributeFile) annotation;
                    createHeader(rowHeader, annotationFile.columnName(), columnIndex);

                    SXSSFCell cell = row.createCell(columnIndex++);
                    setCellValue(field, obj, cell);
                }
            }
        }

    }

    private void createHeader(SXSSFRow row, String columnName, int columnIndex) {
        SXSSFCell cell = row.createCell(columnIndex);
        cell.setCellValue(columnName);
    }

    private void setCellValue(Field field, Object obj, Cell cell) {
        try {
            Object value = field.get(obj);
            cell.setCellValue(value == null ? "" : value.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
