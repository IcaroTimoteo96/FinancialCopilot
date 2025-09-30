package com.example.FinancialCopilot.infrastructure.entity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeminiEmbeddingService {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-embedding-001:embedContent";
    private final String apiKey;
    private final HttpClient httpClient;
    private final Gson gson;

    public GeminiEmbeddingService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public List<Float> getEmbeddingAsList(String text) {
        return getEmbeddingAsList(text, 768);
    }

    public List<Float> getEmbeddingAsList(String text, int outputDimensionality) {
        double[] doubleEmbedding = getEmbedding(text, outputDimensionality);
        return Arrays.stream(doubleEmbedding)
                .mapToObj(d -> (float) d)
                .collect(Collectors.toList());
    }

    public double[] getEmbedding(String text, int outputDimensionality) {
        try {
            String requestBody = buildRequestBody(text, outputDimensionality);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("x-goog-api-key", apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: " + response.statusCode() + " - " + response.body());
            }

            return parseEmbeddingResponse(response.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao chamar a API Gemini: " + e.getMessage(), e);
        }
    }

    private String buildRequestBody(String text, int outputDimensionality) {
        JsonObject requestJson = new JsonObject();

        JsonObject content = new JsonObject();
        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();
        part.addProperty("text", text);
        parts.add(part);
        content.add("parts", parts);
        requestJson.add("content", content);

        requestJson.addProperty("taskType", "RETRIEVAL_DOCUMENT");

        if (outputDimensionality > 0) {
            requestJson.addProperty("outputDimensionality", outputDimensionality);
        }

        return gson.toJson(requestJson);
    }

    private double[] parseEmbeddingResponse(String responseBody) {
        JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonObject embedding = jsonResponse.getAsJsonObject("embedding");
        JsonArray values = embedding.getAsJsonArray("values");

        double[] result = new double[values.size()];

        for (int i = 0; i < values.size(); i++) {
            result[i] = values.get(i).getAsDouble();
        }

        return result;
    }
}
