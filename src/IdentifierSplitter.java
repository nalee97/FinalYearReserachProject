import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifierSplitter {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt"; // replace with the actual input file path
        String outputFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\splittedWords.txt"; // replace with the actual output file path

        try {
            ArrayList<String> identifiers = readIdentifiers(inputFile);
            ArrayList<ArrayList<String>> splitIdentifiers = splitIdentifiers(identifiers);
            writeOutput(splitIdentifiers, outputFile);
            System.out.println("Successfully split identifiers and saved to " + outputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + inputFile);
        } catch (IOException e) {
            System.out.println("Error writing output file: " + outputFile);
        }
    }

    public static ArrayList<String> readIdentifiers(String inputFile) throws FileNotFoundException {
        ArrayList<String> identifiers = new ArrayList<>();
        Scanner scanner = new Scanner(new File(inputFile));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                identifiers.add(line);
            }
        }
        scanner.close();
        return identifiers;
    }

    public static ArrayList<ArrayList<String>> splitIdentifiers(ArrayList<String> identifiers) {
        ArrayList<ArrayList<String>> splitIdentifiers = new ArrayList<>();
        for (String identifier : identifiers) {
            ArrayList<String> parts = new ArrayList<>();
            //parts.addAll(splitLine(identifier));
            parts.addAll(splitCamelCase(identifier));
            //parts.addAll(splitUnderscore(identifier));
            //parts.addAll(splitAny(identifier));
            splitIdentifiers.add(parts);

        }
        return splitIdentifiers;
    }

//    public static ArrayList<String> splitLine(String identifier) {
//        ArrayList<String> parts = new ArrayList<>();
//        Pattern pattern = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
//        Matcher matcher = pattern.matcher(identifier);
//
//        while (matcher.find()) {
//            String part = matcher.group();
//            String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
//            System.out.println(Arrays.toString(words));
//
//        }
//        return parts;
//
//
//    }

    public static ArrayList<String> splitCamelCase(String identifier) {
        ArrayList<String> parts = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-z]+|[A-Z][a-z]*|[0-9]+");
        Matcher matcher = pattern.matcher(identifier);
        while (matcher.find()) {
            String part = matcher.group();
            if (!part.isEmpty()) {
                parts.add(part);
            }
        }
        return parts;
    }
//
//    public static ArrayList<String> splitUnderscore(String identifier) {
//        ArrayList<String> parts = new ArrayList<>();
//        String[] split = identifier.split("_");
//        for (String part : split) {
//            if (!part.isEmpty()) {
//                parts.add(part);
//            }
//        }
//        return parts;
//    }
//
//    public static ArrayList<String> splitAny(String identifier) {
//        ArrayList<String> parts = new ArrayList<>();
//        String[] split = identifier.split("[^a-zA-Z0-9]+");
//        for (String part : split) {
//            if (!part.isEmpty()) {
//                parts.add(part);
//            }
//        }
//        return parts;
//    }

    public static void writeOutput(ArrayList<ArrayList<String>> splitIdentifiers, String outputFile) throws IOException {
        FileWriter writer = new FileWriter(new File(outputFile));
        for (ArrayList<String> parts : splitIdentifiers) {
            writer.write(String.join(",", parts) + "\n");
        }
        writer.close();
    }

}
