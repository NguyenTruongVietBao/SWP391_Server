package com.math.mathcha.controller.FileController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class FileController {
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        List<String> data = new ArrayList<>();

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(data);
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip header row if any
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    data.add(cell.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }

        return ResponseEntity.ok(data);
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
}
