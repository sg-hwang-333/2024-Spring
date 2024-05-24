package com.example.validation_study;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBasedExceptionHandling {
    // 가장 정직한 에러 핸들링은 직접 에러를 처리하고 응답 메시지를 만들어 주는 것
    @GetMapping("/error_handle_from_method1")
    public void errorHandleFromMethod(@RequestParam("num") Integer num, HttpServletResponse response) throws Exception {
        try {
            response.setStatus(200);
            response.setHeader("Content-Type", "text/plain; charset=utf-8");
            // 0으로 나누면 에러 발생
            response.getWriter().write("계산 결과 : " + (10 / num));
        } catch(Exception e) {
            // 직접 catch 블록 내부에서 상태 코드값 설정하고 응답 메시지 반환
            response.setStatus(400);
            response.setHeader("Content-Type", "text/plain; charset=utf-8");
            response.getWriter().write("잘못된 파라미터 전달");
        }
    }

}