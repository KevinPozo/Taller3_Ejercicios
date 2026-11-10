package com.programacion.taller3.embeddings;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.Paths;
import java.util.List;

public class InMemoryEmbeddingStoreMain {
    static void main() {
        var pathModel = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/model.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/tokenizer.json");
        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);
        var documentos = List.of(
                "Java es un lenguaje de programacion orientado a objetos",
                "Python es un lenguaje popular para inteligencia artificial",
                "Los emmbedings representan texto como vectores"
        );

        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();
        documentos.forEach(it -> {
            String key = "doc-" + (store.size() + 1);
            var embedding = model.embed(it);
            store.add(key, embedding.content(), TextSegment.from(it));
        });
        var consulta = "un lenguaje orientado a objetos";
        var searchQuery = EmbeddingSearchRequest.builder()
                .queryEmbedding(model.embed(consulta).content())
                .maxResults(2)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = store.search(searchQuery);
        for (var it : searchResult.matches()) {
            System.out.printf("Score: %.4f - %s%n", it.score(), it.embedded().text());
        }
    }
}