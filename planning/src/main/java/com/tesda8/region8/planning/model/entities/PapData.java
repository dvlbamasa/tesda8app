package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.planning.service.audit.listener.PapDataAuditListener;
import com.tesda8.region8.util.enums.PapGroupType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(PapDataAuditListener.class)
public class PapData extends Auditable<String> {

    private String name;

    @OneToMany(mappedBy = "papData")
    @JsonManagedReference
    List<SuccessIndicatorData> successIndicatorDataList;

    @Enumerated(EnumType.STRING)
    private PapGroupType papGroupType;

    private Long year;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;
}
