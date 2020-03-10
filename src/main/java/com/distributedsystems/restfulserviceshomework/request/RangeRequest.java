package com.distributedsystems.restfulserviceshomework.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangeRequest {

    private String locationName;

    private LocalDate startDate;
    private LocalDate endDate;
}
