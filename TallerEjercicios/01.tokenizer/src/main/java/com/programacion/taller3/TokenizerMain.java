package com.programacion.taller3;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

public class TokenizerMain {
    static void main() {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_003);
        var text = "Hello, do you like tea? <|endoftext|> In the sunlit terraces of someunknownPlace.";

        //Encode:|
        IntArrayList tokenIds = tokenizer.encodeOrdinary(text);
        System.out.println("Tokens totales: "+tokenIds.size());
        System.out.println("tokenIds = " + tokenIds);

        //Decode:
        String textDecoded = tokenizer.decode((tokenIds));
        System.out.println("textDecoded = " + textDecoded);


    }
}
