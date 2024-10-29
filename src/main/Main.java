package main;

import main.tokenizers.NonWordDelimiterTokenizer;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static String INDEX_DIRECTORY = "src/files/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileIndexer fileIndexer = new FileIndexer(new NonWordDelimiterTokenizer());

        // To enable multiple consecutive searches/indexing - we use while true until user exits the app
        while (true) {
            introductionText();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    // Here we can index the files - making a hashset of words and their corresponding files
                    System.out.println("Enter the path to the file or directory (src/files prefix already included): ");
                    String path = scanner.nextLine();
                    fileIndexer.index(new File(INDEX_DIRECTORY + path));
                    newLine();
                    break;
                case "2":
                    // Here we can query out the files containing the given word
                    System.out.println("Enter the word to search for: ");
                    String word = scanner.nextLine();
                    System.out.println("Files containing the word '" + word + "':");
                    fileIndexer.search(word).forEach(System.out::println);
                    newLine();
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    newLine();
            }
        }
    }

    public static void newLine() {
        System.out.println("\n");
    }

    private static void introductionText() {
        System.out.println("Choose one of the provided options: ");
        System.out.println("1 - Index a file or directory");
        System.out.println("2 - Search for word");
        System.out.println("e - Exit");
    }
}