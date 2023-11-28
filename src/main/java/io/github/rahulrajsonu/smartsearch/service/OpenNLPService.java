package io.github.rahulrajsonu.smartsearch.service;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenNLPService {

    private final TokenizerME tokenizer;

    public OpenNLPService() throws IOException {
        try (InputStream modelIn = getClass().getResourceAsStream("/models/en-token.bin")) {
            TokenizerModel model = new TokenizerModel(modelIn);
            tokenizer = new TokenizerME(model);
        }
    }

    public List<String> tokenize(String text) {
        String[] tokens = tokenizer.tokenize(text);
        return Arrays.asList(tokens);
    }
}
