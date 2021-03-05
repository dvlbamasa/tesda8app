package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.ExpiredDocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredDocumentFilter {
    private ExpiredDocumentType expiredDocumentType;
}
