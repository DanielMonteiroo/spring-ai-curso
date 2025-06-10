package com.curso.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ReceitaService {

    private final ChatModel chatModel;

    //Construtor
    public ReceitaService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    //Metodo para criar receita
    public String createReceita(String ingredientes,String cozinha, String restricaoDieta){

        var template = """
                eu preciso criar uma receita com os seguintes ingredientes: {ingredientes}
                o tipo de cozinha,eu prefiro a: {cozinha}
                por favor considerar a restrição de dieta seguida: {restricaoDieta}
                Por favor, forneça-me uma receita detalhada, incluindo título, lista de ingredientes e instruções de cozimento.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String,Object> parametros = Map.of(
                "ingredientes",ingredientes,
                "cozinha",cozinha,
                "restricaoDieta",restricaoDieta
        );
        Prompt prompt = promptTemplate.create(parametros);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }

}

