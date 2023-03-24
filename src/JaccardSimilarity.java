import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity {

    public void jaccardsimilarity()
    {
        String inputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt";
        String dictionaryFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\wordsAlpha.txt";
        String recognizedFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\recognizedWords.txt";
        String unrecognizedFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\unrecognizedWords.txt";

        double threshold = 0.7; // Jaccard similarity threshold
        int maxEditDistance = 2; // maximum edit distance for matching words

        Set<String> dictionary = readDictionaryFromFile(dictionaryFilePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter recognizedWriter = new BufferedWriter(new FileWriter(recognizedFilePath));
             BufferedWriter unrecognizedWriter = new BufferedWriter(new FileWriter(unrecognizedFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String term = line.trim();
                String closestWord = getClosestWord(term, dictionary, threshold, maxEditDistance);
                if (closestWord != null) {
                    recognizedWriter.write("Closest real English word to \"" + term + "\": " + closestWord + "\n");
                } else {
                    unrecognizedWriter.write(term + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file or writing output files: " + e.getMessage());
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

    public static String getClosestWord(String term, Set<String> dictionary, double threshold, int maxEditDistance) {
        String closestWord = null;
        int minDistance = maxEditDistance + 1;
        for (String word : dictionary) {
            double similarity = computeJaccardSimilarity(term.toLowerCase(), word);
            if (similarity >= threshold) {
                int distance = computeEditDistance(term.toLowerCase(), word);
                if (distance <= maxEditDistance && distance < minDistance) {
                    minDistance = distance;
                    closestWord = word;
                }
            }
        }
        return closestWord;
    }

    public static double computeJaccardSimilarity(String s, String t) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (char c : s.toCharArray()) {
            set1.add(c);
        }
        for (char c : t.toCharArray()) {
            set2.add(c);
        }
        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);
        if (union.isEmpty()) {
            return 0;
        } else {
            return (double) intersection.size() / union.size();
        }
    }

    public static int computeEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[m][n];
    }
}