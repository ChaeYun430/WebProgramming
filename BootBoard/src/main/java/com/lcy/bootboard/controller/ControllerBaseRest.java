package com.lcy.bootboard.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ControllerBaseRest {


    //API서버(순수한 데이터만 전송) 제작
    //JSON (JavaScript Object Notation) : 구조를 가진 데이터(객체)를 자바스크립트의 "객체표기법"으로 표현한 순수한 문자열
    //스프링에서는 jackson-databind라는 별도의 라이브러리 추가 후 개발
    //부트는 web항목을 추가할 때 자동으로 포함됨
    @GetMapping("/baseArr")
    public String[] baseArr(){
        log.info("baseArr..............");
        return new String[]{"AAA", "BBB", "CCC"};
    }
    //서버에서 해당 데이터는 "application/json"으로 전송

}
