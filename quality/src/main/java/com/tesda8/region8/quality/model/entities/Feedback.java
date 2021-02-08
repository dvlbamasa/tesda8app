package com.tesda8.region8.quality.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Feedback extends Auditable<String> {

    @Embedded
    private Customer customer;
    private LocalDateTime date;
    private String suggestion;
    private String controlNumber;

    @Type(type = "yes_no")
    private Boolean isRecommended;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<FeedbackRequest> feedbackRequests;

    @Embedded
    private TesdaForm tesdaForm;

}
