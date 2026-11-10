package com.programacion.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;
import java.util.List;

public class BusquedaSemanticaMain {
    static void main() {
        var pathModel = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/model.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/tokenizer.json");
        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);
        var documentos = List.of(
                "Java es un lenguaje de programacion orientado a objetos",
                "Python es un lenguaje popular para inteligencia artificial",
                "HTML5 es un lenguaje de marcado");
        List<Embedding> embeddings = documentos.stream()
                .map(model::embed)
                .map(dev.langchain4j.model.output.Response::content)
                .toList();

        String consulta = "un lenguaje orientado a objetos";
        Embedding queryEmbedding = model.embed(consulta).content();

        String mejorDocumento = "";
        double mejorScore = -1.0;

        for (int i = 0; i < documentos.size(); i++) {
            double score = CosineSimilarity.between(queryEmbedding, embeddings.get(i));
            System.out.printf("Documento %d - Similitud: %.4f%n", i, score);
            if (score > mejorScore) {
                mejorScore = score;
                mejorDocumento = documentos.get(i);
            }
        }

        System.out.printf("Mejor documento: %s (Similitud: %.4f)%n", mejorDocumento, mejorScore);
    }
}