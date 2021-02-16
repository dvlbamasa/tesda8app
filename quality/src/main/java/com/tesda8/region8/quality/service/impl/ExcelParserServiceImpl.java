package com.tesda8.region8.quality.service.impl;

import com.google.common.base.Strings;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SummaryReportDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.service.ExcelParserService;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.util.enums.FeedbackResponse;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.enums.TesdaServiceRendered;
import com.tesda8.region8.util.service.ApplicationUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelParserServiceImpl implements ExcelParserService {

    private FeedbackService feedbackService;

    @Autowired
    public ExcelParserServiceImpl(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public void parseMonitoringReport(HttpServletResponse response, SummaryReportFilter summaryReportFilter) throws IOException {
        List<FeedbackDto> feedbackDtoList = feedbackService.fetchMonitoringReport(summaryReportFilter);
        InputStream is = getClass().getResourceAsStream("/feedback_results_report.xlsx");
        Workbook workbook = new XSSFWorkbook(is);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        for (int j = 0; iterator.hasNext(); j++) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            for (int i = 0; cellIterator.hasNext(); i++) {
                Cell currentCell = cellIterator.next();
                if (j == 5 && i == 1) {
                    currentCell.setCellValue(
                            ApplicationUtil.formatLocalDateTimeToString(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateFrom())) + " - " +
                            ApplicationUtil.formatLocalDateTimeToString(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateTo())));
                }
            }
        }
        addRowsToMonitoringReport(datatypeSheet, feedbackDtoList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @Override
    public void parseSummaryReport(HttpServletResponse response, SummaryReportFilter summaryReportFilter) throws IOException {
        SummaryReportDto summaryReportDto = feedbackService.fetchSummaryReport(summaryReportFilter);
        InputStream is = getClass().getResourceAsStream("/feedback_analysis_report.xlsx");
        Workbook workbook = new XSSFWorkbook(is);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        int genderCounter = 0;
        int ageCounter = 0;
        int inquiryCounter = 0;
        int actionCounter = 0;
        int satisfactionCounter = 0;
        int totalRatingCounter = 0;
        for (int j = 0; iterator.hasNext(); j++) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            for (int i = 0; cellIterator.hasNext(); i++) {
                Cell currentCell = cellIterator.next();
                if (j == 5 && i == 0) {
                    currentCell.setCellValue("For " +
                            ApplicationUtil.formatLocalDateTimeToString(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateFrom())) + " - " +
                                    ApplicationUtil.formatLocalDateTimeToString(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateTo())));
                }
                if (j >= 8 && j <= 10 && genderCounter < 3) {
                    if (i == 5) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getNumberOfClientsByGender().get(genderCounter++).getCount()));
                    }
                } else if (j >= 13 && j <= 19 && ageCounter < 7) {
                    if (i == 5) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getNumberOfClientsByAgeGroup().get(ageCounter++).getCount()));
                    }
                } else if (j >= 23 && j <= 39 && inquiryCounter < 14) {
                    if (currentCell.getCellType() == CellType.NUMERIC && i == 5) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getNumberOfClientsByNatureOfInquiry().get(inquiryCounter++).getCount()));
                    }
                } else if (j >= 42 && j <= 50 && actionCounter < 9) {
                    if (i == 5) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getNumberOfClientsByActionProvided().get(actionCounter++).getCount()));
                    }
                } else if (j >= 53 && j <= 61 && satisfactionCounter < 9) {
                    if (i == 6) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getSatisfactionData().get(satisfactionCounter).getVerySatisfiedCount()));
                    } else if (i == 7) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getSatisfactionData().get(satisfactionCounter).getSatisfiedCount()));
                    } else if (i == 8) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getSatisfactionData().get(satisfactionCounter++).getPoorCount()));
                    }
                } else if (j >= 65 && j <= 69 && totalRatingCounter < 5) {
                    if (currentCell.getCellType() == CellType.NUMERIC && i == 4) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getOverallRatingData().get(totalRatingCounter).getCount()));
                    } else if (i == 7) {
                        currentCell.setCellValue(String.valueOf(summaryReportDto.getOverallRatingData().get(totalRatingCounter++).getPercentage()) + "%");
                    }
                }
            }
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @Override
    public void parseCustomerFeedback(HttpServletResponse response, Long id) throws IOException {
        FeedbackDto feedbackDto = feedbackService.getFeedback(id);
        InputStream is = getClass().getResourceAsStream("/customer_feedback_form.xlsx");
        Workbook workbook = new XSSFWorkbook(is);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        int satisfactionCounter = 0;
        for (int j = 0; iterator.hasNext(); j++) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            for (int i = 0; cellIterator.hasNext(); i++) {
                Cell currentCell = cellIterator.next();
                if (j == 3 && i == 3) {
                    currentCell.setCellValue(ApplicationUtil.formatLocalDateTimeToString(feedbackDto.getDate()));
                } else if (j == 5 && i == 2) {
                    currentCell.setCellValue(feedbackDto.getCustomer().fetchFullName());
                } else if (j == 5 && i == 6) {
                    currentCell.setCellValue(feedbackDto.getCustomer().getAge());
                } else if (j == 5 && i == 11) {
                    currentCell.setCellValue(feedbackDto.getCustomer().getGender().equals(Sex.MALE) ? "✓" : "");
                } else if (j == 5 && i == 13) {
                    currentCell.setCellValue(feedbackDto.getCustomer().getGender().equals(Sex.FEMALE) ? "✓" : "");
                } else if (j == 6 && i == 2) {
                    currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getCustomer().getAddress()) ? "" :
                            feedbackDto.getCustomer().getAddress());
                } else if (j == 7 && i == 2) {
                    currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getCustomer().getContactNumber()) ? "" :
                            feedbackDto.getCustomer().getContactNumber());
                } else if (j == 7 && i == 6) {
                    currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getCustomer().getEmailAddress()) ? "" :
                            feedbackDto.getCustomer().getEmailAddress());
                } else if (j >= 10 && j <= 20) {
                    if (i == 1) {
                        currentCell.setCellValue(feedbackDto.getFeedbackRequests()
                                .get(satisfactionCounter).getFeedbackResponse()
                                .equals(FeedbackResponse.VERY_SATISFACTORY) ? "✓" : "");
                    } else if (i == 3) {
                        currentCell.setCellValue(feedbackDto.getFeedbackRequests()
                                .get(satisfactionCounter).getFeedbackResponse()
                                .equals(FeedbackResponse.SATISFACTORY) ? "✓" : "");
                    } else if (i == 5) {
                        currentCell.setCellValue(feedbackDto.getFeedbackRequests()
                                .get(satisfactionCounter++).getFeedbackResponse()
                                .equals(FeedbackResponse.POOR) ? "✓" : "");
                    }
                } else if (j == 22) {
                    if (i == 1) {
                        currentCell.setCellValue(feedbackDto.getTotalRating()
                                .equals(FeedbackResponse.VERY_SATISFACTORY) ? "✓" : "");
                    } else if (i == 3) {
                        currentCell.setCellValue(feedbackDto.getTotalRating()
                                .equals(FeedbackResponse.SATISFACTORY) ? "✓" : "");
                    } else if (i == 5) {
                        currentCell.setCellValue(feedbackDto.getTotalRating()
                                .equals(FeedbackResponse.POOR) ? "✓" : "");
                    }
                } else if (j == 23) {
                    if (i == 1) {
                        currentCell.setCellValue(feedbackDto.getIsRecommended() ? "✓" : "");
                    } else if (i == 4) {
                        currentCell.setCellValue(feedbackDto.getIsRecommended() ? "" : "✓");
                    }
                } else if (j == 27) {
                    currentCell.setCellValue(feedbackDto.getSuggestion());
                }
            }
        }

        Sheet datatypeSheet2 = workbook.getSheetAt(1);
        Iterator<Row> iterator2 = datatypeSheet2.iterator();
        List<TesdaServiceRendered> serviceRenderedList = (List<TesdaServiceRendered>) feedbackDto.getTesdaForm().getServiceRenderedList();
        for (int j = 0; iterator2.hasNext(); j++) {
            Row currentRow = iterator2.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            for (int i = 0; cellIterator.hasNext(); i++) {
                Cell currentCell = cellIterator.next();
                if (j == 4 && i == 1) {
                    currentCell.setCellValue(feedbackDto.getControlNumber());
                } else if (j == 6) {
                    if (i == 0) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.COMPETENCY_ASSESSMENT) ||
                                serviceRenderedList.contains(TesdaServiceRendered.CERTIFICATION) ||
                                serviceRenderedList.contains(TesdaServiceRendered.ACCREDITATION) ||
                                serviceRenderedList.contains(TesdaServiceRendered.OTHERS_ASSESSMENT) ? "✓" : "");
                    } else if (i == 2) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.APPLICATION_PROG_REG) ||
                                serviceRenderedList.contains(TesdaServiceRendered.RE_REGISTRATION) ||
                                serviceRenderedList.contains(TesdaServiceRendered.OTHERS_PROG_REG) ? "✓" : "");
                    } else if (i == 4) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.REGULAR) ||
                                serviceRenderedList.contains(TesdaServiceRendered.SCHOLARSHIP) ||
                                serviceRenderedList.contains(TesdaServiceRendered.CAV_SO) ||
                                serviceRenderedList.contains(TesdaServiceRendered.OTHERS_TRAINING) ? "✓" : "");
                    } else if (i == 6) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.OTHERS_EMPLOYMENT) ||
                                serviceRenderedList.contains(TesdaServiceRendered.ADMIN) ? "✓" : "");
                    }
                } else if (j == 7 && i == 4) {
                    currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getTesdaForm().getEmploymentOthers()) ?
                            "" : feedbackDto.getTesdaForm().getEmploymentOthers());
                } else if (j == 9) {
                    if (i == 0) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.COMPETENCY_ASSESSMENT) ?
                                "✓" : "");
                    } else if (i == 2) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.APPLICATION_PROG_REG) ?
                                "✓" : "");
                    } else if (i == 4) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.REGULAR) ?
                                "✓" : "");
                    } else if (i == 6) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.ADMIN) ?
                                "✓" : "");
                    }
                }  else if (j == 11) {
                    if (i == 0) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.CERTIFICATION) ?
                                "✓" : "");
                    } else if (i == 2) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.RE_REGISTRATION) ?
                                "✓" : "");
                    } else if (i == 4) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.SCHOLARSHIP) ?
                                "✓" : "");
                    } else if (i == 6) {
                        currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getTesdaForm().getEmploymentAdmin()) ?
                                "" : feedbackDto.getTesdaForm().getEmploymentAdmin());
                    }
                } else if (j == 14) {
                    if (i == 0) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.ACCREDITATION) ?
                                "✓" : "");
                    } else if (i == 2) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.OTHERS_PROG_REG) ?
                                "✓" : "");
                    } else if (i == 4) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.CAV_SO) ?
                                "✓" : "");
                    }
                } else if (j == 17) {
                    if (i == 0) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.OTHERS_ASSESSMENT) ?
                                "✓" : "");
                    } else if (i == 2) {
                        currentCell.setCellValue(serviceRenderedList.contains(TesdaServiceRendered.OTHERS_TRAINING) ?
                                "✓" : "");
                    }
                } else if (j == 19) {
                    if (i == 0) {
                        currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getTesdaForm().getReferredTo()) ?
                                "" : "✓");
                    } else if (i == 4) {
                        currentCell.setCellValue(Strings.isNullOrEmpty(feedbackDto.getTesdaForm().getReferredTo()) ?
                                "" : feedbackDto.getTesdaForm().getReferredTo());
                    }
                } else if (j == 20) {
                    if (i == 0) {
                        currentCell.setCellValue("✓");
                    }
                } else if (j == 21) {
                    currentCell.setCellValue(feedbackDto.getTesdaForm().getActionTaken().label);
                }
            }
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    private void addRowsToMonitoringReport(Sheet sheet, List<FeedbackDto> feedbackDtoList) {
        int rowCount = 12;
        int dataCount = 1;
        System.out.println(feedbackDtoList.size());
        for (FeedbackDto feedbackDto : feedbackDtoList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(sheet, row, columnCount++, dataCount++);
            createCell(sheet, row, columnCount++, ApplicationUtil.formatLocalDateTimeToString(feedbackDto.getDate()));
            createCell(sheet, row, columnCount++, feedbackDto.getControlNumber());
            createCell(sheet, row, columnCount++, feedbackDto.getCustomer().getContactDetails());
            createCell(sheet, row, columnCount++, feedbackDto.getCustomer().getAge());
            createCell(sheet, row, columnCount++, feedbackDto.getCustomer().getGender().label);
            createCell(sheet, row, columnCount++, feedbackDto.getCustomer().getEmailAddress());
            createCell(sheet, row, columnCount++, feedbackDto.getTesdaForm().getTesdaOffice().label);
            createCell(sheet, row, columnCount++, feedbackDto.getTesdaForm().getServiceRequested());
            createCell(sheet, row, columnCount++, feedbackDto.getTesdaForm().getActionTaken().label);
            switch (feedbackDto.getTotalRating()) {
                case VERY_SATISFACTORY:
                    createCell(sheet, row, columnCount++, "✓");
                    columnCount+=2;
                    break;
                case SATISFACTORY:
                    createCell(sheet, row, ++columnCount, "✓");
                    columnCount+=2;
                    break;
                case POOR:
                    columnCount+=2;
                    createCell(sheet, row, columnCount++, "✓");
                    break;
                default:
                    break;
            }
            createCell(sheet, row, columnCount++, feedbackDto.getSuggestion());
            createCell(sheet, row, columnCount, "");
        }
    }

    private void createCell(Sheet sheet, Row row, int columnCount, Object value) {
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
