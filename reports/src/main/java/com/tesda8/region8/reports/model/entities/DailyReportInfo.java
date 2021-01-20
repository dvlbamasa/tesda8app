package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class DailyReportInfo extends Auditable<String> {

}
