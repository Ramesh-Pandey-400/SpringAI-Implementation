package com.example.SpringAiDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;

public class RecipeService {
    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getRecipe(String ingredients, String cuisine, String dietaryRestrictions) {
        var template = """
                I want to create a recipe using following ingredients:{ingredients}
                The cuisine type I prefer is {cuisine}.
                 Please consider following dietayRestrictions :{dietayRestrictions}.
                 Please provide me with a detailed recipe including  title,list of ingredients
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String,Object> params = Map.of(
                "ingredients",ingredients,
                "cuisine",cuisine,
                "dietaryRestrictions",dietaryRestrictions);
        Prompt prompt = promptTemplate.create(params);
        return  chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
