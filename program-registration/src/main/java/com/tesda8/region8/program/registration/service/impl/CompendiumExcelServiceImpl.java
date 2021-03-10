package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.service.CompendiumExcelService;
import com.tesda8.region8.program.registration.service.RegisteredProgramService;
import com.tesda8.region8.util.service.ApplicationUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class CompendiumExcelServiceImpl implements CompendiumExcelService {

    private static Logger logger = LoggerFactory.getLogger(CompendiumExcelServiceImpl.class);

    private RegisteredProgramService registeredProgramService;

    @Autowired
    public CompendiumExcelServiceImpl(RegisteredProgramService registeredProgramService) {
        this.registeredProgramService = registeredProgramService;
    }

    @Override
    public void parseCompendium(HttpServletResponse httpServletResponse, RegisteredProgramFilter registeredProgramFilter) throws IOException {
        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
        InputStream is = getClass().getResourceAsStream("/Compendium_template.xlsx");
        Workbook workbook = new XSSFWorkbook(is);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        Iterator<Row> iterator = datatypeSheet.iterator();
        for (int j = 0; iterator.hasNext(); j++) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            for (int i = 0; cellIterator.hasNext(); i++) {
                Cell currentCell = cellIterator.next();
                if (currentCell.getCellType().equals(CellType.STRING)) {
                    if (j == 5 && i == 0) {
                        currentCell.setCellValue("As of " + ApplicationUtil.formatLocalDateTimeToString2(ApplicationUtil.getLocalDateTimeNow()));
                    }
                }
            }
        }
        addRowsToCompendium(datatypeSheet, registeredProgramDtoList, style);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    private void addRowsToCompendium(Sheet sheet, List<RegisteredProgramDto> registeredProgramDtoList, CellStyle hssfCellStyle) {
        int rowCount = 9;
        for (RegisteredProgramDto registeredProgramDto : registeredProgramDtoList) {
            Row row = sheet.createRow(rowCount++);
            row.setRowStyle(hssfCellStyle);
            createCell(row, 1, registeredProgramDto.getOperatingUnit().label);
            createCell(row, 2, registeredProgramDto.getCongressionalDistrict().label);
            createCell(row, 3, registeredProgramDto.getInstitutionName());
            createCell(row, 4, registeredProgramDto.getInstitutionType().label);
            createCell(row, 5, registeredProgramDto.getInstitutionClassification().label);
            createCell(row, 6, registeredProgramDto.getAddress());
            createCell(row, 7, registeredProgramDto.getContactNumber());
            createCell(row, 8, registeredProgramDto.getSector().label);
            createCell(row, 9, registeredProgramDto.getName());
            createCell(row, 10, registeredProgramDto.getDuration() + " Hours");
            createCell(row, 11, registeredProgramDto.getProgramRegistrationNumber());
            createCell(row, 12, ApplicationUtil.formatLocalDateTimeToString2(registeredProgramDto.getDateIssued()));
            createCell(row, 13, registeredProgramDto.getCourseStatus().label);
        }
    }

    private void createCell(Row row, int columnCount, Object value) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }
    }
}
