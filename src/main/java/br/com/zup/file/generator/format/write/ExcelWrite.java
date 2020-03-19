package br.com.zup.file.generator.format.write;

import br.com.zup.file.generator.format.annotation.AttributeFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ExcelWrite implements DocumentTypeWrite{

    public ByteArrayInputStream write(List<?> objects) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        writeCells(workbook, objects);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void writeCells(XSSFWorkbook workbook, List<?> objects) {
        XSSFSheet sheet = createSheet(workbook,"custodia");
        Row rowHeader = sheet.createRow(0);

        int rowIndex = 1;

        for (Object obj : objects) {
            Class clazz = obj.getClass();
            Row row = sheet.createRow(rowIndex++);

            int columnIndex = 0;

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Annotation annotation = field.getAnnotation(AttributeFile.class);

                if (annotation != null) {
                    AttributeFile annotationFile = (AttributeFile) annotation;
                    createHeader(rowHeader, annotationFile.columnName(), columnIndex);

                    Cell cell = row.createCell(columnIndex++);
                    setCellValue(field, obj, cell);
                }
            }
        }

    }

    private void createHeader(Row row, String columnName, int columnIndex) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(columnName);
    }

    private XSSFSheet createSheet(XSSFWorkbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
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
