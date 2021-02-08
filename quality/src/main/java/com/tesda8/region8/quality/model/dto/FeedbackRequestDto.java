package com.tesda8.region8.quality.model.dto;

import com.tesda8.region8.util.enums.FeedbackQuery;
import com.tesda8.region8.util.enums.FeedbackResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class FeedbackRequestDto {

    private int id;
    private FeedbackQuery feedbackQuery;
    private FeedbackResponse feedbackResponse;

    public FeedbackRequestDto(FeedbackQuery feedbackQuery) {
        this.feedbackQuery = feedbackQuery;
    }
}
