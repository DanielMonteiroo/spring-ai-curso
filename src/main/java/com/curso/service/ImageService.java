package com.curso.service;

import ch.qos.logback.core.net.DefaultSocketConnector;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel imageModel;

    //Construtor
    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    //Metodo gerador de Imagens
    public ImageResponse geradorImagem(String prompt,
           String qualidade,Integer n, Integer altura, Integer largura) {

        ImageResponse imageResponse = imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                        .quality(qualidade)
                        .N(n)
                        .height(altura)
                        .width(largura).build())
        );
        return imageResponse;
    }


}
