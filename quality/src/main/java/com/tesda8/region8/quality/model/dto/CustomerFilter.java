package com.tesda8.region8.quality.model.dto;

import com.tesda8.region8.util.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilter {

    private String name;
    private Sex gender;
    private String address;
    private String contactNumber;
    private String emailAddress;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateFrom;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateTo;
}
