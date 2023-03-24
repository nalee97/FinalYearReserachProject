import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdentifierChecker {

    private static final String IDENTIFIERS_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\splitIdentifiers.txt";
    private static final String DICTIONARY_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\wordsAlpha.txt";
    private static final String RECOGNIZED_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\RecognizedIdentifiers.txt";
    private static final String UNRECOGNIZED_FILE = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt";

    public void identifierChecker() {
        List<String[]> identifierWords = readIdentifierWordsFromFile(IDENTIFIERS_FILE);
        Set<String> dictionary = readWordsFromFile(DICTIONARY_FILE);
        Set<String> recognizedIdentifiers = new HashSet<>();
        Set<String> unrecognizedIdentifiers = new HashSet<>();

        for (String[] words : identifierWords) {
            List<String> matchedWords = new ArrayList<>();
            for (String word : words) {
                if (dictionary.contains(word.toLowerCase())) {
                    matchedWords.add(word);
                }
            }

            if (!matchedWords.isEmpty()) {
                recognizedIdentifiers.add(String.join("", words) + ": " + String.join("; ", matchedWords));
            } else {
                unrecognizedIdentifiers.add(String.join("", words));
            }

        }

        writeIdentifiersToFile(RECOGNIZED_FILE, recognizedIdentifiers);
        writeIdentifiersToFile(UNRECOGNIZED_FILE, unrecognizedIdentifiers);
    }

    private static List<String[]> readIdentifierWordsFromFile(String filename) {
        List<String[]> identifierWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("[^a-zA-Z0-9]+");
                identifierWords.add(words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return identifierWords;
    }

    private static Set<String> readWordsFromFile(String filename) {
        Set<String> words = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                for (String part : parts) {
                    words.add(part.toLowerCase());
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
