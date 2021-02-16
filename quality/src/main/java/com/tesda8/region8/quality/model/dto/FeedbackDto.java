package com.tesda8.region8.quality.model.dto;

import com.google.common.collect.Lists;
import com.tesda8.region8.quality.model.entities.Customer;
import com.tesda8.region8.quality.model.entities.TesdaForm;
import com.tesda8.region8.util.enums.FeedbackQuery;
import com.tesda8.region8.util.enums.FeedbackResponse;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class FeedbackDto {

    private long id;
    private long number;
    private Customer customer;
    private LocalDateTime date;
    private String suggestion;
    private String controlNumber;
    private Boolean isRecommended;

    private FeedbackResponse totalRating;

    private List<FeedbackRequestDto> feedbackRequests;

    private TesdaForm tesdaForm;

    private String captchaResponse;

    public static FeedbackDto build(String controlNumber) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setFeedbackRequests(Lists.newArrayList());
        feedbackDto.setCustomer(new Customer());
        feedbackDto.setTesdaForm(new TesdaForm());
        feedbackDto.setControlNumber(controlNumber);
        Arrays.asList(FeedbackQuery.values()).forEach(
                feedbackQuery -> {
                    feedbackDto.getFeedbackRequests().add(new FeedbackRequestDto(feedbackQuery));
                }
        );
        return feedbackDto;
    }
}
