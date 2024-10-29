package main.tokenizers;

import java.util.Arrays;
import java.util.List;

public class DollarTokenizer implements Tokenizer {

    @Override
    public List<String> tokenize(String content) {
        // Splits only by the $ delimiter (dollar sign)
        return Arrays.asList(content.split("\\$"));
    }
}
