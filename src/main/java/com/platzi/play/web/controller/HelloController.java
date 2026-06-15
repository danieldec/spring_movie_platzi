package com.platzi.play.web.controller;

import com.platzi.play.dominio.service.PlatziPlayAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final PlatziPlayAiService aiService;
    private final String platform;

    public HelloController(@Value("${spring.application.name}") String plataform, PlatziPlayAiService aiService) {
        this.aiService = aiService;
        this.platform = plataform;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.aiService.generateGreeting(this.platform);
    }
}
