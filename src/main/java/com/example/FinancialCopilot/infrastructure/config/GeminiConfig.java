package com.example.FinancialCopilot.infrastructure.config;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public abstract class GeminiConfig {
    public static String GenerateText(String key, String text){
        Client client = Client.builder().apiKey(key).build();
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        text,
                        null);

        return response.text();
    }
}
