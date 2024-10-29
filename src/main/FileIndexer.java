package main;

import main.tokenizers.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FileIndexer {
    // Tokenizer sets the delimiter for splitting the content
    private final Tokenizer tokenizer;
    private final Map<String, Set<String>> targetWordsMap = new HashMap<>();

    public FileIndexer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void index(File file) {
        String fullPath = file.getPath();
        // If the path points to directory, then it lists all files within the directory
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                index(f);
            }
        } else {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                List<String> tokens = tokenizer.tokenize(content);
                for (String token : tokens) {
                    String modifiedPath = fullPath.replace("\\", "/");
                    targetWordsMap.computeIfAbsent(token.toLowerCase(), k -> new HashSet<>()).add(modifiedPath);
                }
                System.out.println("Indexed: " + file.getPath());
            } catch (IOException e) {
                System.out.println("Failed to index file: " + file.getPath());
            }
        }
    }

    public Set<String> search(String word) {
        return targetWordsMap.getOrDefault(word.toLowerCase(), new HashSet<>());
    }
}
