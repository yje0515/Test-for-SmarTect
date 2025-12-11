package com.smartect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayEventDTO {
    private String type;
    private int count;
}
