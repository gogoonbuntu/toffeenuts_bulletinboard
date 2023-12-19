package com.toffee.nuts.bulletinboard.controller;


import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.Arrays;


@Slf4j
public class ChatGPTController {
    public static String call(String who, String content) {
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("")
                .timeout(900)
                .apiHost("https://api.openai.com/") //反向代理地址
                .build()
                .init();

        Message system = Message.ofSystem(who);
        Message message = Message.of(content);

        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .messages(Arrays.asList(system, message))
                .maxTokens(3000)
                .temperature(0.9)
                .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        return res.toString();
    }
}
