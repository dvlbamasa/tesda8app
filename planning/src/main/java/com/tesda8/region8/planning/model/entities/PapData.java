package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.enums.PapGroupType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "PAP_DATA")
public class PapData extends Auditable<String> {

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "papData")
    @JsonManagedReference
    List<SuccessIndicatorData> successIndicatorDataList;

    @Column(name = "PAP_GROUP_TYPE")
    @Enumerated(EnumType.STRING)
    private PapGroupType papGroupType;

    @Column(name = "IS_DELETED")
    @Type(type = "yes_no")
    private Boolean isDeleted = false;
}
