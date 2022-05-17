package com.infybuzz.helperClasses;

import com.infybuzz.entity.StudentTypes.StudentSimple;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author dcaric on 17/05/2022
 * @project spring-boot-app
 */
public class ExportToXls {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<StudentSimple> students;

    public ExportToXls(List<StudentSimple> students) {
        this.students = students;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Students");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Student ID");

        cell = row.createCell(1);
        cell.setCellValue("First name");

        cell = row.createCell(2);
        cell.setCellValue("last name");

    }

    private void writeDataRows() {
        int rowCount = 1;
        for (StudentSimple student: students) {

            Row row = sheet.createRow(rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(student.getId());

            cell = row.createCell(1);
            cell.setCellValue(student.getFirstName());

            cell = row.createCell(2);
            cell.setCellValue(student.getLastName());

            rowCount++;
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();

        System.out.println("*** outputStream:" + outputStream.toString());
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}

