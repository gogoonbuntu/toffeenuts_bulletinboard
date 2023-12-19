package com.toffee.nuts.bulletinboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RequestController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        return "hello";
    }

    @GetMapping("CreateNewBoard")
    public String CreateNewBoard(Model model) {
        model.addAttribute("data", "Hello!");
        return "yes";
    }

    @GetMapping("gpt")
    public String CreateTistoryGPT(Model model) throws Exception {
        String result = ChatGPTController.call("난 지금 한국 여행가이드야. 전문적으로 여행명소를 추천해주지.", "12월달에 가볼만한 실내 데이트 명소들을 5가지 추천해주고, 그 5가지 지역에서 먹을만한 음식도 포함시켜서 자세히 설명해줘.");
        TistoryController.postNew("12월 겨울에 갈만한 실내 데이트 명소 TOP 5", result);
        return result;
    }
}
