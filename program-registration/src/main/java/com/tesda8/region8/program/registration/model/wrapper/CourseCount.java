package com.tesda8.region8.program.registration.model.wrapper;

import com.tesda8.region8.util.enums.Sector;
import lombok.Data;

@Data
public class CourseCount {

    private Sector sector;
    private int count = 0;
}
