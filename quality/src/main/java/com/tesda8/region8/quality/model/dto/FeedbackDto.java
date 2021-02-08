package com.tesda8.region8.quality.model.dto;

import com.google.common.collect.Lists;
import com.tesda8.region8.quality.model.entities.Customer;
import com.tesda8.region8.quality.model.entities.TesdaForm;
import com.tesda8.region8.util.enums.FeedbackQuery;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class FeedbackDto {

    private int id;
    private Customer customer;
    private LocalDateTime date;
    private String suggestion;
    private String controlNumber;
    private Boolean isRecommended;

    private List<FeedbackRequestDto> feedbackRequests;

    private TesdaForm tesdaForm;

    public static FeedbackDto build() {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setFeedbackRequests(Lists.newArrayList());
        feedbackDto.setCustomer(new Customer());
        feedbackDto.setTesdaForm(new TesdaForm());
        Arrays.asList(FeedbackQuery.values()).forEach(
                feedbackQuery -> {
                    feedbackDto.getFeedbackRequests().add(new FeedbackRequestDto(feedbackQuery));
                }
        );
        return feedbackDto;
    }
}
