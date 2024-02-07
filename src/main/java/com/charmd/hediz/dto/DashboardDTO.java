package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DashboardDTO")
public class DashboardDTO {
    private int reserv_stat;
    private int count;
    private int sales_amount;
    private LocalDate date;
}
