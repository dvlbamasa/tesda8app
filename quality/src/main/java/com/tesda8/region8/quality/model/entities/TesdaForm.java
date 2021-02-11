package com.tesda8.region8.quality.model.entities;

import com.google.common.collect.Lists;
import com.tesda8.region8.util.enums.ActionTaken;
import com.tesda8.region8.util.enums.TesdaOffice;
import com.tesda8.region8.util.enums.TesdaServiceRendered;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.util.Collection;

@Data
@Embeddable
@NoArgsConstructor
public class TesdaForm {

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Collection<TesdaServiceRendered> serviceRenderedList = Lists.newArrayList();
    private String employmentOthers;
    private String employmentAdmin;
    private String referredTo;
    @Enumerated(EnumType.STRING)
    private TesdaOffice tesdaOffice;
    @Enumerated(EnumType.STRING)
    private ActionTaken actionTaken;

    @Transient
    public String getServiceRequested() {
        if (serviceRenderedList.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        serviceRenderedList.forEach(
                tesdaServiceRendered -> {
                    stringBuilder.append(tesdaServiceRendered.label.equals("Others") ?
                            tesdaServiceRendered.serviceType + " - " + tesdaServiceRendered.label :
                            tesdaServiceRendered.label)
                            .append(", ");
                }
        );
        return stringBuilder.substring(0, stringBuilder.length()-2);
    }
}
