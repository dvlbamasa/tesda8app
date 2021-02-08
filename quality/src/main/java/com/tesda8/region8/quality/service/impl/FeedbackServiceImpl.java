package com.tesda8.region8.quality.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.quality.model.dto.CustomerFilter;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.model.entities.Customer;
import com.tesda8.region8.quality.model.entities.Feedback;
import com.tesda8.region8.quality.model.entities.QFeedback;
import com.tesda8.region8.quality.repository.FeedbackRepository;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.quality.service.mapper.FeedbackMapper;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.service.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private static Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);


    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    @Transactional
    public void createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = feedbackMapper.feedbackToEntity(feedbackDto);
        feedback.getFeedbackRequests().forEach(
                feedbackRequest -> feedbackRequest.setFeedback(feedback));
        feedback.setDate(LocalDateTime.now());
        feedback.getCustomer().setFullName(feedbackDto.getCustomer().fetchFullName());
        Feedback savedFeedback = feedbackRepository.save(feedback);
        savedFeedback.setControlNumber(ApplicationUtil.formatLocalDateTime(LocalDateTime.now()) + savedFeedback.getId());
        feedbackRepository.save(savedFeedback);
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
        if (customerFilter.getEmailAddress() != null) {
            booleanBuilder.and(QFeedback.feedback.customer.emailAddress.containsIgnoreCase(customerFilter.getEmailAddress()));
        }
        if (customerFilter.getDateFrom() != null) {
            booleanBuilder.and(QFeedback.feedback.date.goe(ApplicationUtil.convertToLocalDateTimeViaInstant(customerFilter.getDateFrom())));
        }
        if (customerFilter.getDateTo() != null) {
            booleanBuilder.and(QFeedback.feedback.date.loe(ApplicationUtil.convertToLocalDateTimeViaInstant(customerFilter.getDateTo())));
        }

        Predicate predicate = booleanBuilder.getValue();

        List<Feedback> feedbackList = predicate == null ?
                feedbackRepository.findAll() : (List<Feedback>) feedbackRepository.findAll(predicate);

        return feedbackList.stream()
                .map(feedback -> feedbackMapper.feedbackToDto(feedback))
                .collect(Collectors.toList());
    }
}
