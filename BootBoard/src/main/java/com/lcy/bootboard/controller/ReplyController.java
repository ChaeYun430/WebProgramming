package com.lcy.bootboard.controller;


import com.lcy.bootboard.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//  Rest (Representational State Transfer) 방식 : 특정한 URI와 Http 메서드를 결합해서 '특정한 자원에 특정한 작업'을 지정하는 방식
//  Ajax (Asynchronous JavaScript And XML)방식 : 브라우저에서 서버를 호출하지만 모든 작업이 브라우저 내부에서 이루어지기 때문에
//                                              현재 브라우저의 브라우저 화면의 변화 없이 통신 가능
//  JSON (JavaScript Object Notation) : 자바스크립트 문법에 맞는 문자열로 데이터를 표현

//  메서드의 모든 리턴값이 JSP / thymeleaf로 전송되는 것이 아니라 JSON / XML로 처리
@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)// 해당 메서드를 받아서 소비하는 데이터의 종류 명시
    public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO) { //JSON문자열의 ReplyDTO 전환
        log.info(replyDTO);
        Map<String, Long> resultMap = Map.of("rno", 111L); // 불변 맵 생성
        return ResponseEntity.ok(resultMap);
        // 상태코드 전송 (200)
    }
}
