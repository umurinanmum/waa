package edu.mum.waa.download;

import edu.mum.waa.dto.AttendanceDatePresentDto;
import edu.mum.waa.dto.DatePresentDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Component
public class ExcelGenerator {

    public ByteArrayInputStream studentBlockReportToExcel(AttendanceDatePresentDto attendanceDatePresentDto) throws IOException {

        String[] COLUMNs = {"Date", "Status"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            Sheet sheet = workbook.createSheet("studentBlockReportToExcel");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (DatePresentDto datePresentDto : attendanceDatePresentDto.getDatePresentDtoList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(datePresentDto.getDate().toString());
                row.createCell(1).setCellValue(datePresentDto.isPresent());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}
