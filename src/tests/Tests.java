package tests;

import main.FileIndexer;
import main.Main;
import main.tokenizers.DollarTokenizer;
import main.tokenizers.NonWordDelimiterTokenizer;
import org.junit.Test;
import java.io.File;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Here we are going to test only the NonWordDelimiterTokenizer
public class Tests {

    @Test
    public void indexingPlainFileShouldPass() {
        String fileName = "file1.txt";
        FileIndexer fileIndexer = new FileIndexer(new NonWordDelimiterTokenizer());
        fileIndexer.index(new File(Main.INDEX_DIRECTORY + fileName));

        Set<String> filesInvolved = fileIndexer.search("apple");

        // Should pass as there is 'apple' word in the file
        assertTrue(filesInvolved.contains(Main.INDEX_DIRECTORY + fileName));
    }

    @Test
    public void indexingPlainFileShouldNotPass() {
        String fileName = "file1.txt";
        FileIndexer fileIndexer = new FileIndexer(new NonWordDelimiterTokenizer());
        fileIndexer.index(new File(Main.INDEX_DIRECTORY + fileName));

        Set<String> filesInvolved = fileIndexer.search("ananas");

        // Should not pass as there is no 'ananas' word in the file
        assertFalse(filesInvolved.contains(Main.INDEX_DIRECTORY + fileName));
    }

    @Test
    public void indexingNestedFilesShouldPass() {
        // Indexing the plain file path the same way like the previous functions
        String fileName = "file1.txt";
        FileIndexer fileIndexer = new FileIndexer(new NonWordDelimiterTokenizer());
        fileIndexer.index(new File(Main.INDEX_DIRECTORY + fileName));

        // Indexing the nested file path
        String nestedFileName = "testFolder/file2.txt";
        fileIndexer.index(new File(Main.INDEX_DIRECTORY + nestedFileName));

        Set<String> filesInvolved = fileIndexer.search("apple");

        // Should pass as there is the word 'apple' in the file
        assertTrue(filesInvolved.contains(Main.INDEX_DIRECTORY + fileName) &&
                filesInvolved.contains(Main.INDEX_DIRECTORY + nestedFileName));
    }

    @Test
    public void indexingAllFilesInOneFolderShouldPass() {
        // Indexing the nested file path
        String folderName = "testFolder";
        FileIndexer fileIndexer = new FileIndexer(new NonWordDelimiterTokenizer());
        fileIndexer.index(new File(Main.INDEX_DIRECTORY + folderName));

        Set<String> filesInvolved = fileIndexer.search("apple");

        // Testing to see if all files are listed inside a directory
        assertTrue(filesInvolved.contains(Main.INDEX_DIRECTORY + folderName + "/file2.txt") &&
                filesInvolved.contains(Main.INDEX_DIRECTORY + folderName + "/file3.txt"));
    }

    @Test
    public void indexingWithDollarTokenizerShouldPass() {
        // This file has the '$' as delimiter
        // We use a different tokenizer to test if the word is found
        String fileName = "file4.txt";
        FileIndexer fileIndexer = new FileIndexer(new DollarTokenizer());
        fileIndexer.index(new File(Main.INDEX_DIRECTORY + fileName));

        Set<String> filesInvolved = fileIndexer.search("apple");

        assertTrue(filesInvolved.contains(Main.INDEX_DIRECTORY + fileName));
    }
}