package main.tokenizers;

import java.util.List;

// We can create multiple tokenizers to test different tokenization strategies, making it extensible
public interface Tokenizer {
    List<String> tokenize(String content);
}
