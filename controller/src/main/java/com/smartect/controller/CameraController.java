package com.smartect.controller;

import com.smartect.dto.CamDTO;
import com.smartect.dto.RealtimeLogDTO;
import com.smartect.dto.TodayEventDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CameraController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // --------------------
        // 1. CCTV 더미 데이터
        // --------------------
        List<CamDTO> cameras = List.of(
                new CamDTO("CCTV-01", "정문", "ONLINE", 30, "stream-01"),
                new CamDTO("CCTV-02", "복도", "ONLINE", 28, "stream-02"),
                new CamDTO("CCTV-03", "주차장", "OFFLINE", 0, "stream-03"),
                new CamDTO("CCTV-04", "비상구", "ONLINE", 31, "stream-04")
        );
        model.addAttribute("cameraList", cameras);


        // --------------------
        // 2. 오늘의 이벤트 더미 데이터
        // --------------------
        List<TodayEventDTO> todayEvents = List.of(
                new TodayEventDTO("위험행동", 12),
                new TodayEventDTO("경미", 3),
                new TodayEventDTO("기타", 2)
        );
        model.addAttribute("todayEventList", todayEvents);


        // --------------------
        // 3. 실시간 감지 스토리 더미 데이터
        // --------------------
        List<RealtimeLogDTO> realtimeLogs = List.of(
                new RealtimeLogDTO("[전시품훼손] 감지 (14:22)"),
                new RealtimeLogDTO("[접근금지구역] 감지 (14:29)"),
                new RealtimeLogDTO("[화재의심] 감지 (13:55)")
        );
        model.addAttribute("realtimeLogList", realtimeLogs);

        model.addAttribute("adminName", "관리자A");

        return "dashboard"; // dashboard.html 렌더링
    }
}
