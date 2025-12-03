package tttn.duong.moneyManager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tttn.duong.moneyManager.dto.IncomeDTO;
import tttn.duong.moneyManager.entity.ProfileEntity;
import tttn.duong.moneyManager.service.EmailService;
import tttn.duong.moneyManager.service.ExcelService;
import tttn.duong.moneyManager.service.IncomeService;
import tttn.duong.moneyManager.service.ProfileService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ExcelController {

    private final IncomeService incomeService;
    private final ExcelService excelService;
    private final EmailService emailService;
    private final ProfileService profileService;

    @GetMapping("/excel/download/income")
    public ResponseEntity<ByteArrayResource> downloadIncomeExcel() {
        try {
            // Get current month incomes for current user
            List<IncomeDTO> incomes = incomeService.getCurrentMonthIncomesForCurrentUser();

            if (incomes.isEmpty()) {
                log.warn("No income data found for current month");
                return ResponseEntity.noContent().build();
            }

            // Generate Excel file
            byte[] excelBytes = excelService.generateIncomeExcel(incomes);

            // Create filename with current date
            String filename = "income_details_" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".xlsx";

            ByteArrayResource resource = new ByteArrayResource(excelBytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .contentLength(excelBytes.length)
                    .body(resource);

        } catch (Exception e) {
            log.error("Error generating income Excel file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/email/income-excel")
    public ResponseEntity<String> emailIncomeExcel() {
        try {
            // Get current user profile
            ProfileEntity profile = profileService.getCurrentProfile();
            String userEmail = profile.getEmail();

            // Get current month incomes
            List<IncomeDTO> incomes = incomeService.getCurrentMonthIncomesForCurrentUser();

            if (incomes.isEmpty()) {
                log.warn("No income data found for current month");
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No income data available for current month");
            }

            // Generate Excel file
            byte[] excelBytes = excelService.generateIncomeExcel(incomes);

            // Create filename
            String filename = "income_details_" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".xlsx";

            // Send email with attachment
            excelService.sendEmailWithExcelAttachment(userEmail, profile.getFullName(), excelBytes, filename);

            log.info("Income Excel file sent successfully to: {}", userEmail);
            return ResponseEntity.ok("Income details sent to your email successfully");

        } catch (Exception e) {
            log.error("Error sending income Excel via email", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send income details via email");
        }
    }


}
