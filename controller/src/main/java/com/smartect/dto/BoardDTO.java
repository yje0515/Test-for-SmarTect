package com.smartect.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data //getter + setter + toString + equals + hashCode
public class BoardDTO {
    private Integer boardId;

    private String title;

    private String content;

    private LocalDateTime createDate;
}
