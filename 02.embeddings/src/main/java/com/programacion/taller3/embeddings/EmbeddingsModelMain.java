package com.programacion.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EmbeddingsModelMain {
    static void main() {
        var pathModel = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/model.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/tokenizer.json");
        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel,pathTokenizer, PoolingMode.MEAN);
        var text = "Hola, como estas?";
        Response<Embedding> response = model.embed(text);
        Embedding embedding = response.content();
        System.out.println("Dimension del embedding: "+embedding.dimension());
        System.out.println("Embedding vector: " + embedding.vectorAsList());

    }
}
