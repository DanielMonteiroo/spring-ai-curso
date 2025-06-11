package com.curso.controller;

import com.curso.service.ChatService;
import com.curso.service.ImageService;
import com.curso.service.ReceitaService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAIController {

    private final ChatService chatService;
    private final ReceitaService receitaService;
    private final ImageService imageService;

    //Construtor
    public GenerativeAIController(ChatService chatService, ReceitaService receitaService,ImageService imageService) {
        this.chatService = chatService;
        this.receitaService = receitaService;
        this.imageService = imageService;
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

    //Endpoint gerar imagem
    @GetMapping("generate-images")
    public List<String> geradorImagens(@RequestParam String prompt,
                               @RequestParam(defaultValue = "hd") String qualidade,
                               @RequestParam(defaultValue = "1") Integer n,
                               @RequestParam(defaultValue = "1024") Integer altura,
                               @RequestParam(defaultValue = "1024") Integer largura){

       ImageResponse response = imageService.geradorImagem(prompt,qualidade,n,altura,largura);
       List<String> imagesURLS = response.getResults().stream().map(
               result->result.getOutput().getUrl()).toList();
       return imagesURLS;
    }
}
