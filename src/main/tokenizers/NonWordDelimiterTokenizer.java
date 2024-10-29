package main.tokenizers;

import java.util.Arrays;
import java.util.List;

public class NonWordDelimiterTokenizer implements Tokenizer {
    @Override
    public List<String> tokenize(String content) {
        // Split by non word characters
        return Arrays.asList(content.split("\\W+"));
    }
}
