package com.tesda8.region8.certification.service;

import com.tesda8.region8.program.registration.model.dto.CertificateDto;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.dto.TrainerFilter;
import com.tesda8.region8.program.registration.service.TrainerService;
import com.tesda8.region8.program.registration.service.impl.CompendiumExcelServiceImpl;
import com.tesda8.region8.util.enums.CertificateType;
import com.tesda8.region8.util.service.ApplicationUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistryTrainerExcelService {

    private static Logger logger = LoggerFactory.getLogger(CompendiumExcelServiceImpl.class);

    private TrainerService trainerService;

    public RegistryTrainerExcelService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public void parseRegistryReport(HttpServletResponse httpServletResponse, TrainerFilter trainerFilter) throws IOException {
        List<TrainerDto> trainerDtoList = trainerService.getAllTrainerByFilter(trainerFilter);
        InputStream is = getClass().getResourceAsStream("/REGISTRY_TEMPLATE.xlsx");
        Workbook workbook = new XSSFWorkbook(is);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeight((short)150);
        style.setFont(font);
        style.setWrapText(true);

        addRowsToReport(datatypeSheet, trainerDtoList, style);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    private void addRowsToReport(Sheet sheet, List<TrainerDto> trainerDtoList, CellStyle cellStyle) {
        int rowCount = 5;
        int dataCount = 1;
        for (TrainerDto trainerDto : trainerDtoList) {
            List<CertificateDto> ncCertificateDtoList = trainerDto.getCertificates().stream()
                    .filter(certificateDto -> certificateDto.getCertificateType().equals(CertificateType.NC))
                    .filter(certificateDto -> !certificateDto.getIsDeleted())
                    .collect(Collectors.toList());
            List<CertificateDto> nttcCertificateDtoList = trainerDto.getCertificates().stream()
                    .filter(certificateDto -> certificateDto.getCertificateType().equals(CertificateType.NTTC))
                    .filter(certificateDto -> !certificateDto.getIsDeleted())
                    .collect(Collectors.toList());
            List<CertificateDto> tmCertificateDtoList = trainerDto.getCertificates().stream()
                    .filter(certificateDto -> certificateDto.getCertificateType().equals(CertificateType.TM))
                    .filter(certificateDto -> !certificateDto.getIsDeleted())
                    .collect(Collectors.toList());
            for (int i = 0; i < ncCertificateDtoList.size(); i++) {
                Row row = sheet.createRow(rowCount++);
                row.setRowStyle(cellStyle);
                ApplicationUtil.createCell(row, 0, dataCount++);
                ApplicationUtil.createCell(row, 4, trainerDto.getLastName());
                ApplicationUtil.createCell(row, 5, trainerDto.getFirstName());
                ApplicationUtil.createCell(row, 6, trainerDto.getMiddleInitial());
                ApplicationUtil.createCell(row, 7, trainerDto.getNameExt());
                ApplicationUtil.createCell(row, 8, trainerDto.getFullName());
                ApplicationUtil.createCell(row, 9, ApplicationUtil.formatLocalDateTimeToString2(trainerDto.getBirthdate()));
                ApplicationUtil.createCell(row, 10, trainerDto.getSex().label);
                ApplicationUtil.createCell(row, 11, trainerDto.getAddress());
                ApplicationUtil.createCell(row, 12, trainerDto.getEmailAddress());
                ApplicationUtil.createCell(row, 13, trainerDto.getContactNumber());
                ApplicationUtil.createCell(row, 14, trainerDto.getEducationalAttainment().label);
                ApplicationUtil.createCell(row, 15, trainerDto.getTrainingInstitution());
                ApplicationUtil.createCell(row, 16, trainerDto.getInstitutionType() == null ? "" : trainerDto.getInstitutionType().label);
                ApplicationUtil.createCell(row, 17, trainerDto.getTeachingExperienceYear());
                ApplicationUtil.createCell(row, 18, trainerDto.getIndustryExperienceYear());
                ApplicationUtil.createCell(row, 20, ncCertificateDtoList.get(i).getQualificationTitle());
                ApplicationUtil.createCell(row, 21, ncCertificateDtoList.get(i).getCertificateNumber());
                ApplicationUtil.createCell(row, 22, ApplicationUtil.formatLocalDateTimeToString2(ncCertificateDtoList.get(i).getDateIssued()));
                ApplicationUtil.createCell(row, 23, ApplicationUtil.formatLocalDateTimeToString2(ncCertificateDtoList.get(i).getExpirationDate()));
                ApplicationUtil.createCell(row, 24, tmCertificateDtoList.get(0).getCertificateNumber());
                ApplicationUtil.createCell(row, 25, ApplicationUtil.formatLocalDateTimeToString2(tmCertificateDtoList.get(0).getDateIssued()));
                ApplicationUtil.createCell(row, 26, ApplicationUtil.formatLocalDateTimeToString2(tmCertificateDtoList.get(0).getExpirationDate()));
                ApplicationUtil.createCell(row, 30, nttcCertificateDtoList.get(i).getCertificateNumber());
                ApplicationUtil.createCell(row, 31, ApplicationUtil.formatLocalDateTimeToString2(nttcCertificateDtoList.get(i).getDateIssued()));
                ApplicationUtil.createCell(row, 32, ApplicationUtil.formatLocalDateTimeToString2(nttcCertificateDtoList.get(i).getExpirationDate()));
                ApplicationUtil.createCell(row, 33, nttcCertificateDtoList.get(i).getClnNtcNumber());
            }
        }
    }
}
