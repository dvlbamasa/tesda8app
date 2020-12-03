package com.tesda8.region8.planning.model.dto;

import com.tesda8.region8.util.enums.PapGroupType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PapDataDto {

    private long id;
    private String name;
    private PapGroupType papGroupType;
    private Boolean isDeleted;
    private Long year;
    private List<SuccessIndicatorDataDto> successIndicatorDataList;
}
