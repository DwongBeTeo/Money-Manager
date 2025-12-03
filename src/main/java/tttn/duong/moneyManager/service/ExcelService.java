package tttn.duong.moneyManager.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tttn.duong.moneyManager.dto.IncomeDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelService {

    final private JavaMailSender mailSender;

    public byte[] generateIncomeExcel(List<IncomeDTO> incomes) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Income Details");

            // Create header style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Create data style
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);

            // Create currency style
            CellStyle currencyStyle = workbook.createCellStyle();
            currencyStyle.cloneStyleFrom(dataStyle);
            currencyStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"No.", "Name", "Category", "Amount", "Date", "Icon"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            // Fill data rows
            int rowNum = 1;
            BigDecimal totalAmount = BigDecimal.ZERO;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (IncomeDTO income : incomes) {
                Row row = sheet.createRow(rowNum++);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue(rowNum - 1);
                cell0.setCellStyle(dataStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(income.getName());
                cell1.setCellStyle(dataStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(income.getCategoryName());
                cell2.setCellStyle(dataStyle);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(income.getAmount().doubleValue());
                cell3.setCellStyle(currencyStyle);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(income.getDate().format(dateFormatter));
                cell4.setCellStyle(dataStyle);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(income.getIcon() != null ? income.getIcon() : "");
                cell5.setCellStyle(dataStyle);

                totalAmount = totalAmount.add(income.getAmount());
            }

            // Add total row
            Row totalRow = sheet.createRow(rowNum);
            Cell totalLabelCell = totalRow.createCell(2);
            totalLabelCell.setCellValue("TOTAL");
            totalLabelCell.setCellStyle(headerStyle);

            Cell totalValueCell = totalRow.createCell(3);
            totalValueCell.setCellValue(totalAmount.doubleValue());
            totalValueCell.setCellStyle(currencyStyle);
            Font totalFont = workbook.createFont();
            totalFont.setBold(true);
            CellStyle totalStyle = workbook.createCellStyle();
            totalStyle.cloneStyleFrom(currencyStyle);
            totalStyle.setFont(totalFont);
            totalValueCell.setCellStyle(totalStyle);

            // Auto-size columns
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            log.info("Excel file generated successfully with {} income records", incomes.size());
            return out.toByteArray();
        }
    }

    public void sendEmailWithExcelAttachment(String toEmail, String userName,
                                              byte[] excelBytes, String filename) throws Exception {
        log.info("job started: sending email with excel attachment");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject("Your Income Details - " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM yyyy")));

        // Create HTML body
        String htmlBody = String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; border-radius: 5px; }
                    .content { background-color: #f9f9f9; padding: 20px; margin-top: 20px; border-radius: 5px; }
                    .footer { margin-top: 20px; text-align: center; color: #777; font-size: 12px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h2>💰 Income Details Report</h2>
                    </div>
                    <div class="content">
                        <p>Hello <strong>%s</strong>,</p>
                        <p>Please find attached your income details for <strong>%s</strong>.</p>
                        <p>The Excel file contains all your income records with the following information:</p>
                        <ul>
                            <li>Income name and category</li>
                            <li>Amount and date</li>
                            <li>Total income summary</li>
                        </ul>
                        <p>Thank you for using Money Manager!</p>
                    </div>
                    <div class="footer">
                        <p>This is an automated email. Please do not reply.</p>
                        <p>&copy; 2025 Money Manager. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """,
                userName != null ? userName : "User",
                LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM yyyy"))
        );

        helper.setText(htmlBody, true);

        // Attach Excel file
        helper.addAttachment(filename, new ByteArrayResource(excelBytes));

        mailSender.send(message);
        log.info("Job ended: email has been sent");
    }
}
