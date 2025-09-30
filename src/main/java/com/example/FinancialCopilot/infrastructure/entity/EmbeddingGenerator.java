package com.example.FinancialCopilot.infrastructure.entity;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.embedding.Embedding;
import com.theokanning.openai.embedding.EmbeddingRequest;

import java.util.List;

public class EmbeddingGenerator {
    private final OpenAiService service;

    public EmbeddingGenerator(String apiKey) {
        service = new OpenAiService(apiKey);
    }

    public List<Double> generateEmbedding(String text) {
        EmbeddingRequest request = EmbeddingRequest.builder()
                .model("text-embedding-ada-002")
                .input(List.of(text))
                .build();

        List<Embedding> embeddings = service.createEmbeddings(request).getData();
        return embeddings.get(0).getEmbedding();
    }
}
