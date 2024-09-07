package com.example.SpringAiDemo;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt,String quality,Integer n, Integer height, Integer width) {
        ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt, OpenAiImageOptions.builder()
                        .withModel("dall-e-2")
                        .withQuality(quality)
                        .withN(n)
                        .withHeight(height)
                        .withWidth(width)
                        .build())
        );
        return imageResponse;
    }
}