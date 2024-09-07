package com.example.SpringAiDemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenAIController {

    @Autowired
    ChatService chatService;

    @Autowired
    ImageService imageService;

    @Autowired
    RecipeService recipeService;

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam  String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-questions")
    public String getResponseOptions(@RequestParam  String prompt){
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,@RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") Integer n,
                                       @RequestParam(defaultValue = "1024") int height,
                                       @RequestParam(defaultValue = "1024") int width){
        ImageResponse imageResponse = imageService.generateImage(prompt,quality,n,height,width);
        List<String> imageURLs = imageResponse.getResults().stream().map(result->
                result.getOutput().getUrl()).toList();
        return imageURLs;
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam  String ingredients,
                                @RequestParam (defaultValue = "any") String cuisine,
                                @RequestParam (defaultValue = " ") String dietayRestrictions){
        return recipeService.getRecipe(ingredients,cuisine,dietayRestrictions);
    }
}
