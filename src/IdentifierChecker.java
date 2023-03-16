import java.io.*;
import java.util.*;

public class IdentifierChecker {

    private static final String IDENTIFIERS_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnmatchedIdentifiers.txt";
    private static final String DICTIONARY_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
    private static final String RECOGNIZED_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\RecognizedIdentifiers.txt";
    private static final String UNRECOGNIZED_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt";

    public void identifierChecker() {
        Set<String> identifiers = readIdentifiersFromFile(IDENTIFIERS_FILE);
        Set<String> dictionary = readWordsFromFile(DICTIONARY_FILE);
        Set<String> recognizedIdentifiers = new HashSet<>();
        Set<String> unrecognizedIdentifiers = new HashSet<>();

        for (String identifier : identifiers) {
            String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])|_");
            boolean isRecognized = false;
            for (String word : words) {
                if (dictionary.contains(word)) {
                    isRecognized = true;
                    break;
                }
            }
            if (isRecognized) {
                recognizedIdentifiers.add(identifier);
            } else {
                unrecognizedIdentifiers.add(identifier);
            }
        }

        writeIdentifiersToFile(RECOGNIZED_FILE, recognizedIdentifiers);
        writeIdentifiersToFile(UNRECOGNIZED_FILE, unrecognizedIdentifiers);
    }

    private static Set<String> readIdentifiersFromFile(String filename) {
        Set<String> identifiers = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                identifiers.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return identifiers;
    }

    private static Set<String> readWordsFromFile(String filename) {
        Set<String> words = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                for (String part : parts) {
                    words.add(part);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static void writeIdentifiersToFile(String filename, Set<String> identifiers) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String identifier : identifiers) {
                writer.println(identifier);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
