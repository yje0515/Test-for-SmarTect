package com.smartect.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private Long id;                 // 이벤트 ID
    private String cameraName;       // 감지된 카메라
    private String eventType;        // FIRE / SMOKE / TRESPASS / TOUCH / CROWD 등
    private String severity;         // HIGH / MEDIUM / LOW
    private String location;         // 발생 장소
    private String description;      // 상세 설명
    private LocalDateTime timestamp; // 발생 시간
    private String thumbnailUrl;     // 이미지 경로 (더미)

}