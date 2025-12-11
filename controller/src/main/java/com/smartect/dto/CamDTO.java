package com.smartect.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CamDTO {

    // 카메라 이름 (CCTV-01)
    private String name;

    // 카메라 물리적 위치 (정문, 복도, 전시관 내부 등)
    private String location;

    // ONLINE / OFFLINE
    private String status;

    // 스트리밍 FPS
    private  int fps;

    // RTSP 스트림 ID 또는 식별자
    private String streamId;
}
