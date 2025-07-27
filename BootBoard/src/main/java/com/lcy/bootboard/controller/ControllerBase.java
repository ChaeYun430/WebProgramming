package com.lcy.bootboard.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/base")
@Log4j2
public class ControllerBase {

    //Thymeleaf 이용
    @GetMapping("/base")
    public void base(Model model){
        log.info("base");
        model.addAttribute("msg", "Wellcome Controller");
    }
}
