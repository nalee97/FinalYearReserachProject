import java.io.*;
import java.util.HashSet;

public class IdentifierComparerWithExtractedIdentifiers {
    public void compareWithIdExtractor()
    {
        String inputFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt";
        String dictionaryFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\wordsAlpha.txt";
        String matchedFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\MatchedIdentifiers.txt";
        String unmatchedFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnmatchedIdentifiers.txt";

        HashSet<String> dictionary = new HashSet<String>();
        HashSet<String> matchedIdentifiers = new HashSet<String>();
        HashSet<String> unmatchedIdentifiers = new HashSet<String>();

        // Read in the dictionary file
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase()); // convert to lowercase
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Read in the input file and compare each identifier with the dictionary
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] identifiers = line.split("\\s+");
                for (String identifier : identifiers) {
                    if (dictionary.contains(identifier.toLowerCase())) { // convert to lowercase
                        matchedIdentifiers.add(identifier + " : " + line);
                    } else {
                        unmatchedIdentifiers.add(identifier);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write out the matched identifiers to a file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(matchedFile))) {
            for (String matchedIdentifier : matchedIdentifiers) {
                String[] parts = matchedIdentifier.split(" : ", 2);
                bw.write(parts[0] + " : " + parts[1]);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write out the unmatched identifiers to a file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(unmatchedFile))) {
            for (String identifier : unmatchedIdentifiers) {
                bw.write(identifier);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
