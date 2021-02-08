package com.tesda8.region8.quality.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.enums.FeedbackQuery;
import com.tesda8.region8.util.enums.FeedbackResponse;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class FeedbackRequest extends Auditable<String> {

    @Enumerated(EnumType.STRING)
    private FeedbackQuery feedbackQuery;
    @Enumerated(EnumType.STRING)
    private FeedbackResponse feedbackResponse;

    @ManyToOne
    @JoinColumn(name = "FEEDBACK_ID")
    @JsonManagedReference
    private Feedback feedback;
}
