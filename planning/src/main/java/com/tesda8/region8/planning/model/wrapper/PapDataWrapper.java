package com.tesda8.region8.planning.model.wrapper;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import lombok.Data;

import java.util.List;

@Data
public class PapDataWrapper {

    private List<PapDataDto> tesdppData;
    private List<PapDataDto> tesdrpData;
    private List<PapDataDto> tesdpData;
    private List<PapDataDto> stoData;
    private List<PapDataDto> gassData;
}
