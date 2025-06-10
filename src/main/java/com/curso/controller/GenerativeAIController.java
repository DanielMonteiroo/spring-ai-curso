package com.curso.controller;

import com.curso.service.ChatService;
import com.curso.service.ReceitaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerativeAIController {

    private final ChatService chatService;
    private final ReceitaService receitaService;

    //Construtor
    public GenerativeAIController(ChatService chatService, ReceitaService receitaService) {
        this.chatService = chatService;
        this.receitaService = receitaService;
    }

    //Endpoint perguntas
    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){

        return chatService.getResponse(prompt);
    }

    //Endpoint perguntas com opções
    @GetMapping("ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt){

        return chatService.getResponseWithOptions(prompt);
    }

    //Endpoint criar receita
    @GetMapping("recipe-creator")
    public String criadorReceita(@RequestParam String ingredientes,@RequestParam (defaultValue = "any") String cozinha,
                                @RequestParam (defaultValue = "none") String restricaoDieta){

        return receitaService.createReceita(ingredientes,cozinha,restricaoDieta);
    }
}
