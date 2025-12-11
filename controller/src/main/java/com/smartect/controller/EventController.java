package com.smartect.controller;

import com.smartect.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EventController {

    // === 더미 이벤트 데이터 === //
    private static final List<EventDTO> dummyEvents = new ArrayList<>();

    static {
        dummyEvents.add(EventDTO.builder()
                .id(1L)
                .cameraName("CCTV-01")
                .eventType("FIRE")
                .severity("HIGH")
                .location("전시관 A존")
                .description("화재 의심 연기가 감지되었습니다.")
                .timestamp(LocalDateTime.now().minusMinutes(3))
                .thumbnailUrl("/img/fire_sample.jpg")
                .build());

        dummyEvents.add(EventDTO.builder()
                .id(2L)
                .cameraName("CCTV-03")
                .eventType("TOUCH")
                .severity("LOW")
                .location("전시물 B-12")
                .description("관람객이 전시물을 손으로 만진 것으로 감지됨.")
                .timestamp(LocalDateTime.now().minusMinutes(15))
                .thumbnailUrl("/img/touch_sample.jpg")
                .build());

        dummyEvents.add(EventDTO.builder()
                .id(3L)
                .cameraName("CCTV-02")
                .eventType("TRESPASS")
                .severity("MEDIUM")
                .location("출입금지 구역 C-07")
                .description("관람객이 출입금지 구역에 진입했습니다.")
                .timestamp(LocalDateTime.now().minusMinutes(21))
                .thumbnailUrl("/img/trespass.jpg")
                .build());
    }

    // 메인 페이지
    @GetMapping("/eventboard")
    public String eventboard() {
        return "eventboard"; // Thymeleaf 템플릿
    }

    // 비동기 목록 API (검색 + 페이징)
    @GetMapping("/api/events")
    @ResponseBody
    public List<EventDTO> getEvents(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false) String categories,   // "FIRE,SUSPICIOUS"
            @RequestParam(defaultValue = "ALL") String period,   // TODAY, WEEK, MONTH, ALL
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // 기본 스트림
        LocalDateTime now = LocalDateTime.now();
        var stream = dummyEvents.stream();

        // 1) 키워드 필터 (카메라명 / 위치 / 타입)
        if (!keyword.isBlank()) {
            String lower = keyword.toLowerCase();
            stream = stream.filter(e ->
                    e.getEventType().toLowerCase().contains(lower)
                            || e.getCameraName().toLowerCase().contains(lower)
                            || e.getLocation().toLowerCase().contains(lower)
            );
        }

        // 2) 카테고리 필터 (다중선택: FIRE,SUSPICIOUS,ACCESS ...)
        if (categories != null && !categories.isBlank()) {
            Set<String> categorySet = Arrays.stream(categories.split(","))
                    .map(String::toUpperCase)
                    .collect(Collectors.toSet());

            stream = stream.filter(e ->
                    categorySet.contains(e.getEventType().toUpperCase())
            );
        }

        // 3) 기간 필터
        switch (period.toUpperCase()) {
            case "TODAY" -> {
                LocalDateTime start = now.toLocalDate().atStartOfDay();
                stream = stream.filter(e -> !e.getTimestamp().isBefore(start));
            }
            case "WEEK" -> {
                LocalDateTime weekAgo = now.minusDays(7);
                stream = stream.filter(e -> !e.getTimestamp().isBefore(weekAgo));
            }
            case "MONTH" -> {
                LocalDateTime monthAgo = now.minusMonths(1);
                stream = stream.filter(e -> !e.getTimestamp().isBefore(monthAgo));
            }
            case "ALL" -> {
                // 필터 없음
            }
        }

        // 일단은 페이징 없이 전체 반환 (원하면 나중에 페이지 처리 추가)
        return stream
                .sorted(Comparator.comparing(EventDTO::getTimestamp).reversed())
                .collect(Collectors.toList());
    }


    // 상세정보 API
    @GetMapping("/api/events/{id}")
    @ResponseBody
    public EventDTO getEventDetail(@PathVariable Long id) {
        return dummyEvents.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
