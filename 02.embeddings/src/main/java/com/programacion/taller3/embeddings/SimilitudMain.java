package com.programacion.taller3.embeddings;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;

public class SimilitudMain {
    static void main() {
        var pathModel = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/model.onnx");
        var pathTokenizer = Paths.get("/home/labcom/Vídeos/Taller3_Ejercicios-main/TallerEjercicios/modelos/tokenizer.json");
        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel,pathTokenizer, PoolingMode.MEAN);
        var embedding1 = model.embed("Cual es el area de un circulo?").content();
        var embedding2 = model.embed("Cual es el area de un cuadrado?").content();
        var embedding3 = model.embed("Que hora es?").content();
        //Calcular la similitud entre los embeddings:
        double sim12 = CosineSimilarity.between(embedding1,embedding2);
        double sim13 = CosineSimilarity.between(embedding1,embedding3);
        double sim23 = CosineSimilarity.between(embedding2,embedding3);
        System.out.println("Similitud 1 y 2 = " + sim12);
        System.out.println("Similitud 1 y 3 = " + sim13);
        System.out.println("Similitud 2 y 3 = " + sim23);

    }
}
