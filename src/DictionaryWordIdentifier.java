import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DictionaryWordIdentifier {

//    public static void main(String[] args) {
//        String inputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt";
//        String dictionaryFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
//        String outputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\recognizedIdentifiers.txt";
//        Set<String> dictionary = readDictionaryFromFile(dictionaryFilePath);
//        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String term = line.trim();
//                String closestWord = getClosestWord(term, dictionary);
//                writer.write("Closest real English word to \"" + term + "\": " + closestWord + "\n");
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading input file or writing output file: " + e.getMessage());
//        }
//    }

    public void DictinaryCompare()
    {
        String inputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedWords.txt";
        String dictionaryFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
        String outputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\recognizedWords.txt";
        Set<String> dictionary = readDictionaryFromFile(dictionaryFilePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String term = line.trim();
                String closestWord = getClosestWord(term, dictionary);
                writer.write("Closest real English word to \"" + term + "\": " + closestWord + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading input file or writing output file: " + e.getMessage());
        }
    }

    public static Set<String> readDictionaryFromFile(String filePath) {
        Set<String> dictionary = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
        }
        return dictionary;
    }

    public static String getClosestWord(String term, Set<String> dictionary) {
        int minDistance = Integer.MAX_VALUE;
        String closestWord = null;
        for (String word : dictionary) {
            int distance = computeEditDistance(term.toLowerCase(), word);
            if (distance < minDistance) {
                minDistance = distance;
                closestWord = word;
            }
        }
        return closestWord;
    }

    public static int computeEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = s.charAt(i-1) == t.charAt(j-1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i-1][j]+1, Math.min(dp[i][j-1]+1, dp[i-1][j-1]+cost));
            }
        }
        return dp[m][n];
    }
}
