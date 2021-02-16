package com.tesda8.region8.quality.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.dto.SatisfactionSummaryData;
import com.tesda8.region8.quality.model.dto.SummaryData;
import com.tesda8.region8.quality.model.dto.SummaryReportDto;
import com.tesda8.region8.quality.model.dto.SummaryReportFilter;
import com.tesda8.region8.quality.model.entities.Feedback;
import com.tesda8.region8.quality.model.entities.FeedbackRequest;
import com.tesda8.region8.quality.model.entities.QFeedback;
import com.tesda8.region8.quality.repository.FeedbackRepository;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.quality.service.mapper.FeedbackMapper;
import com.tesda8.region8.util.enums.ActionTaken;
import com.tesda8.region8.util.enums.AgeGroup;
import com.tesda8.region8.util.enums.FeedbackQuery;
import com.tesda8.region8.util.enums.FeedbackResponse;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.enums.TesdaServiceRendered;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.util.service.ReportUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private static Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    private static final String TOTAL = "Total";


    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public String generateControlNumber(LocalDateTime localDateTime) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QFeedback.feedback.date.goe(localDateTime.truncatedTo(ChronoUnit.DAYS)));
        Predicate predicate = booleanBuilder.getValue();

        List<Feedback> feedbackList = predicate == null ?
                feedbackRepository.findAll() : (List<Feedback>) feedbackRepository.findAll(predicate);
        long count = feedbackList.size() + 1;
        return ApplicationUtil.formatLocalDateTime(LocalDateTime.now()) + count;
    }

    @Override
    @Transactional
    public void createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = feedbackMapper.feedbackToEntity(feedbackDto);
        feedback.getFeedbackRequests().forEach(
                feedbackRequest -> feedbackRequest.setFeedback(feedback));
        feedback.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        feedback.getCustomer().setFullName(feedbackDto.getCustomer().fetchFullName());
        feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackDto> fetchAllCustomerFeedbacks(CustomerFilter customerFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (customerFilter.getName() != null) {
            booleanBuilder.and(QFeedback.feedback.customer.fullName.containsIgnoreCase(customerFilter.getName()));
        }
        if (customerFilter.getAddress() != null) {
            booleanBuilder.and(QFeedback.feedback.customer.address.containsIgnoreCase(customerFilter.getAddress()));
        }
        if (customerFilter.getContactNumber() != null) {
            booleanBuilder.and(QFeedback.feedback.customer.contactNumber.containsIgnoreCase(customerFilter.getContactNumber()));
        }
        if (customerFilter.getGender() != null && !customerFilter.getGender().equals(Sex.ALL)) {
            booleanBuilder.and(QFeedback.feedback.customer.gender.eq(customerFilter.getGender()));
        }
        if (customerFilter.getDateFrom() != null) {
            booleanBuilder.and(QFeedback.feedback.date.goe(ApplicationUtil.convertToLocalDateTimeViaInstant(customerFilter.getDateFrom())));
        }
        if (customerFilter.getDateTo() != null) {
            booleanBuilder.and(QFeedback.feedback.date.loe(ApplicationUtil.convertToLocalDateTimeViaInstant(customerFilter.getDateTo())));
        }
        if (customerFilter.getEmailAddress() != null) {
            booleanBuilder.and(QFeedback.feedback.customer.emailAddress.containsIgnoreCase(customerFilter.getEmailAddress()));
        }

        Predicate predicate = booleanBuilder.getValue();

        List<Feedback> feedbackList = predicate == null ?
                feedbackRepository.findAll() : (List<Feedback>) feedbackRepository.findAll(predicate);

        return feedbackList.stream()
                .map(feedback -> feedbackMapper.feedbackToDto(feedback))
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto getFeedback(Long id) {
        return feedbackRepository.findById(id)
                .map(feedbackMapper::feedbackToDto).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public SummaryReportDto fetchSummaryReport(SummaryReportFilter summaryReportFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (summaryReportFilter.getDateFrom() != null) {
            booleanBuilder.and(QFeedback.feedback.date.goe(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateFrom())));
        }
        if (summaryReportFilter.getDateTo() != null) {
            booleanBuilder.and(QFeedback.feedback.date.loe(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateTo())));
        }

        Predicate predicate = booleanBuilder.getValue();

        List<Feedback> feedbackList = predicate == null ?
                feedbackRepository.findAll() : (List<Feedback>) feedbackRepository.findAll(predicate);

        SummaryReportDto summaryReportDto = new SummaryReportDto();

        summaryReportDto.setNumberOfClientsByGender(getNumberOfClientsByGender(feedbackList));
        summaryReportDto.setNumberOfClientsByAgeGroup(getNumberOfClientsByAgeGroup(feedbackList));
        summaryReportDto.setNumberOfClientsByNatureOfInquiry(getNumberOfClientsByNatureOfInquiry(feedbackList));
        summaryReportDto.setNumberOfClientsByActionProvided(getNumberOfClientsByActionProvided(feedbackList));
        summaryReportDto.setSatisfactionData(getSatisfactionData(feedbackList));
        summaryReportDto.setOverallRatingData(getOverallRatingData(feedbackList));
        return summaryReportDto;
    }

    @Override
    public List<FeedbackDto> fetchMonitoringReport(SummaryReportFilter summaryReportFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (summaryReportFilter.getDateFrom() != null) {
            booleanBuilder.and(QFeedback.feedback.date.goe(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateFrom())));
        }
        if (summaryReportFilter.getDateTo() != null) {
            booleanBuilder.and(QFeedback.feedback.date.loe(ApplicationUtil.convertToLocalDateTimeViaInstant(summaryReportFilter.getDateTo())));
        }

        Predicate predicate = booleanBuilder.getValue();

        List<Feedback> feedbackList = predicate == null ?
                feedbackRepository.findAll() : (List<Feedback>) feedbackRepository.findAll(predicate);

        return feedbackList.stream()
                .map(feedbackMapper::feedbackToDto)
                .collect(Collectors.toList());
    }

    private List<SummaryData> getNumberOfClientsByGender(List<Feedback> feedbackList) {
        List<SummaryData> summaryDataList = Lists.newArrayList();

        Arrays.asList(Sex.values()).forEach(
                sex -> {
                    if (!sex.equals(Sex.ALL)) {
                        SummaryData summaryData = new SummaryData();
                        summaryData.setLabel(sex.label);
                        summaryData.setCount(feedbackList
                                .stream()
                                .filter(feedback -> feedback.getCustomer().getGender().equals(sex))
                                .count());
                        summaryDataList.add(summaryData);
                    }
                }
        );
        summaryDataList.add(getSummaryDataTotal(feedbackList));
        return summaryDataList;
    }

    private List<SummaryData> getNumberOfClientsByAgeGroup(List<Feedback> feedbackList) {
        List<SummaryData> summaryDataList = Lists.newArrayList();

        Arrays.asList(AgeGroup.values()).forEach(
                ageGroup -> {
                    SummaryData summaryData = new SummaryData();
                    summaryData.setLabel(ageGroup.label);

                    summaryData.setCount(feedbackList
                            .stream()
                            .filter(feedback -> feedback.getCustomer().getAge() != null &&
                                    feedback.getCustomer().getAge() >= ageGroup.min &&
                                    feedback.getCustomer().getAge() <= ageGroup.max)
                            .count());
                    summaryDataList.add(summaryData);
                }
        );
        summaryDataList.add(getSummaryDataTotal(feedbackList));
        return summaryDataList;
    }

    private List<SummaryData> getNumberOfClientsByNatureOfInquiry(List<Feedback> feedbackList) {
        List<SummaryData> summaryDataList = Lists.newArrayList();
        Arrays.asList(TesdaServiceRendered.values()).forEach(
                tesdaServiceRendered -> {
                    if (!tesdaServiceRendered.equals(TesdaServiceRendered.TOTAL)) {
                        SummaryData summaryData = new SummaryData();
                        summaryData.setLabel(tesdaServiceRendered.label);
                        summaryData.setType(tesdaServiceRendered.serviceType);
                        summaryData.setCount(
                                feedbackList.stream()
                                        .filter(feedback -> feedback.getTesdaForm().getServiceRenderedList().contains(tesdaServiceRendered))
                                        .count()
                        );
                        summaryDataList.add(summaryData);
                    }
                }
        );
        SummaryData summaryDataTotal = new SummaryData();
        summaryDataTotal.setLabel(TOTAL);
        summaryDataTotal.setType(TesdaServiceRendered.TOTAL.serviceType);
        feedbackList.forEach(
            feedback -> {
                summaryDataTotal.setCount(summaryDataTotal.getCount() + feedback.getTesdaForm().getServiceRenderedList().size());
            }
        );

        summaryDataList.add(summaryDataTotal);
        return summaryDataList;
    }

    private List<SummaryData> getNumberOfClientsByActionProvided(List<Feedback> feedbackList) {
        List<SummaryData> summaryDataList = Lists.newArrayList();
        Arrays.asList(ActionTaken.values()).forEach(
                actionTaken -> {
                    SummaryData summaryData = new SummaryData();
                    summaryData.setLabel(actionTaken.label);
                    summaryData.setCount(
                            feedbackList.stream()
                            .filter(feedback -> feedback.getTesdaForm().getActionTaken().equals(actionTaken))
                            .count()
                    );
                    summaryDataList.add(summaryData);
                }
        );
        summaryDataList.add(getSummaryDataTotal(feedbackList));
        return summaryDataList;
    }

    private List<SatisfactionSummaryData> getSatisfactionData(List<Feedback> feedbackList) {
        List<SatisfactionSummaryData> summaryDataList = Lists.newArrayList();
        SatisfactionSummaryData satisfactionSummaryDataTotal = new SatisfactionSummaryData();
        satisfactionSummaryDataTotal.setFeedbackQuery(TOTAL);
        Arrays.asList(FeedbackQuery.values()).forEach(
                feedbackQuery -> {
                    SatisfactionSummaryData satisfactionSummaryData = new SatisfactionSummaryData();
                    satisfactionSummaryData.setFeedbackQuery(feedbackQuery.label);
                    feedbackList.forEach(
                            feedback -> {
                                Optional<FeedbackRequest> feedbackRequest = feedback.getFeedbackRequests().stream()
                                        .filter(feedbackRequest1 -> feedbackRequest1.getFeedbackQuery().equals(feedbackQuery))
                                        .findFirst();
                                switch (feedbackRequest.get().getFeedbackResponse()) {
                                    case VERY_SATISFACTORY:
                                        satisfactionSummaryDataTotal.setVerySatisfiedCount(satisfactionSummaryDataTotal.getVerySatisfiedCount() + 1);
                                        satisfactionSummaryData.setVerySatisfiedCount(satisfactionSummaryData.getVerySatisfiedCount() + 1);
                                        break;
                                    case SATISFACTORY:
                                        satisfactionSummaryDataTotal.setSatisfiedCount(satisfactionSummaryDataTotal.getSatisfiedCount() + 1);
                                        satisfactionSummaryData.setSatisfiedCount(satisfactionSummaryData.getSatisfiedCount() + 1);
                                        break;
                                    case POOR:
                                        satisfactionSummaryDataTotal.setPoorCount(satisfactionSummaryDataTotal.getPoorCount() + 1);
                                        satisfactionSummaryData.setPoorCount(satisfactionSummaryData.getPoorCount() + 1);
                                        break;
                                    default:
                                        break;
                                }
                            }
                    );
                    summaryDataList.add(satisfactionSummaryData);
                }
        );
        summaryDataList.add(satisfactionSummaryDataTotal);
        return summaryDataList;
    }

    private List<SummaryData> getOverallRatingData(List<Feedback> feedbackList) {
        List<SummaryData> summaryDataList = Lists.newArrayList();
        SummaryData summaryDataNetTotal = new SummaryData();
        summaryDataNetTotal.setLabel("Net Satisfaction Rating");
        summaryDataNetTotal.setCount(null);
        Arrays.asList(FeedbackResponse.values()).forEach(
                feedbackResponse -> {
                    SummaryData summaryData = new SummaryData();
                    summaryData.setLabel(feedbackResponse.label);

                    long count = feedbackList.stream()
                            .filter(feedback -> feedback.getTotalRating().equals(feedbackResponse))
                            .count();

                    summaryData.setCount(count);
                    summaryData.setPercentage(ReportUtil.calculateRate(feedbackList.size(), count));
                    if (feedbackResponse.equals(FeedbackResponse.POOR)) {
                        summaryDataNetTotal.setPercentage(summaryDataNetTotal.getPercentage() - summaryData.getPercentage());
                    } else {
                        summaryDataNetTotal.setPercentage(summaryDataNetTotal.getPercentage() + summaryData.getPercentage());
                    }
                    summaryDataList.add(summaryData);
                }
        );
        SummaryData summaryDataTotal = getSummaryDataTotal(feedbackList);
        summaryDataTotal.setPercentage(feedbackList.size() > 0 ? 100.0 : 0.0);
        summaryDataList.add(summaryDataTotal);
        summaryDataList.add(summaryDataNetTotal);
        return summaryDataList;
    }

    private SummaryData getSummaryDataTotal(List<Feedback> feedBackList) {
        SummaryData summaryDataTotal = new SummaryData();
        summaryDataTotal.setLabel(TOTAL);
        summaryDataTotal.setCount((long) feedBackList.size());
        return summaryDataTotal;
    }
}
