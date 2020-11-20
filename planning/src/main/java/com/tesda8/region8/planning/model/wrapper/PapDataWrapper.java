package com.tesda8.region8.planning.model.wrapper;

import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import lombok.Data;

import java.util.List;

@Data
public class PapDataWrapper {

    private List<SuccessIndicatorDataDto> tesdppData;
    private List<SuccessIndicatorDataDto> tesdrpData;
    private List<SuccessIndicatorDataDto> tesdpData;
    private List<SuccessIndicatorDataDto> stoData;
    private List<SuccessIndicatorDataDto> gassData;
}
