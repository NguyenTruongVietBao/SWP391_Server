package com.math.mathcha.controller.FileController;

import com.math.mathcha.dto.request.QuestionDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class FileController {

    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/upload")
    public ResponseEntity<List<QuestionDTO>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        List<QuestionDTO> questions = new ArrayList<>();

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(questions);
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip header row if any
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                QuestionDTO question = new QuestionDTO();
                question.setContent(row.getCell(0).getStringCellValue());
                question.setTitle(row.getCell(1).getStringCellValue());
                question.setOption(new ArrayList<>());
                for (int i = 2; i <= 5; i++) {
                    question.getOption().add(row.getCell(i).getStringCellValue());
                }
                question.setCorrectAnswer(row.getCell(6).getStringCellValue());
                questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }

        return ResponseEntity.ok(questions);
    }


}

//    @GetMapping("/testTemplate")
//    public ResponseEntity<List<String>> testTemplate() {
//        List<String> data = new ArrayList<>();
//        String templatePath = "C:\\Users\\duypo\\Downloads\\Template.xlsx"; // Updated path
//
//        try (FileInputStream fis = new FileInputStream(templatePath);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
//
//            Iterator<Row> rowIterator = sheet.iterator();
//
//            if (rowIterator.hasNext()) {
//                rowIterator.next(); // Skip header row if any
//            }
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.iterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    data.add(cell.toString());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body(null);
//        }
//
//        return ResponseEntity.ok(data);
//    }
//

