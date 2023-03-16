//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashSet;
//import java.util.Set;
//
//public class IdentifierChecker {
////    public static void main(String[] args) {
////        //use identifier_splitter class output as an input
////        String identifiersFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt";
////        String dictionaryFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
////        Set<String> identifiers = readIdentifiersFromFile(identifiersFile);
////        Set<String> dictionary = readWordsFromFile(dictionaryFile);
////        Set<String> unrecognizedWords = compareIdentifiersToDictionary(identifiers, dictionary);
////        try (PrintWriter writer = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt")) {
////            for (String n : unrecognizedWords) {
////                writer.println(n);
////
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////
////    }
//    public void identfierchecker()
//    {
//        String identifiersFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\splitIdentifiers.txt";
//        String dictionaryFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
//        Set<String> identifiers = readIdentifiersFromFile(identifiersFile);
//        Set<String> dictionary = readWordsFromFile(dictionaryFile);
//        Set<String> unrecognizedWords = compareIdentifiersToDictionary(identifiers, dictionary);
//        try (PrintWriter writer = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedWords.txt")) {
//            for (String n : unrecognizedWords) {
//                writer.println(n);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static Set<String> readIdentifiersFromFile(String filename) {
//        Set<String> identifiers = new HashSet<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                identifiers.add(line.trim());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return identifiers;
//    }
//
//    public static Set<String> readWordsFromFile(String filename) {
//        Set<String> words = new HashSet<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.trim().split("\\s+");
//                for (String part : parts) {
//                    words.add(part);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return words;
//    }
//
////    public static Set<String> compareIdentifiersToDictionary(Set<String> identifiers, Set<String> dictionary) {
////        Set<String> unrecognizedWords = new HashSet<>();
////        for (String identifier : identifiers) {
////            String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])|_");
////            for (String word : words) {
////                if (!dictionary.contains(word)) {
////                    unrecognizedWords.add(word);
////                }
////            }
////        }
////        return unrecognizedWords;
//
//
// //   }
// public static Set<String> compareIdentifiersToDictionary(Set<String> identifiers, Set<String> dictionary) {
//     Set<String> unrecognizedIdentifiers = new HashSet<>(identifiers);
//     for (String identifier : identifiers) {
//         String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])|_");
//         for (String word : words) {
//             if (dictionary.contains(word)) {
//                 unrecognizedIdentifiers.remove(identifier);
//                 break;
//             }
//         }
//     }
//     return unrecognizedIdentifiers;
// }
//
//
//
//}
import java.io.*;
import java.util.*;

public class IdentifierChecker {

//    public static void main(String[] args) {
//        String identifiersFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\splitIdentifiers.txt";
//        String dictionaryFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
//        Set<String> identifiers = readIdentifiersFromFile(identifiersFile);
//        Set<String> dictionary = readWordsFromFile(dictionaryFile);
//        Set<String> unrecognizedIdentifiers = compareIdentifiersToDictionary(identifiers, dictionary);
//        Set<String> recognizedIdentifiers = new HashSet<>(identifiers);
//        recognizedIdentifiers.removeAll(unrecognizedIdentifiers);
//        try (PrintWriter unrecognizedWriter = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt");
//             PrintWriter recognizedWriter = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\RecognizedIdentifiers.txt")) {
//            for (String unrecognized : unrecognizedIdentifiers) {
//                unrecognizedWriter.println(unrecognized);
//            }
//            for (String recognized : recognizedIdentifiers) {
//                recognizedWriter.println(recognized);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void identifierChecker()
    {
        String identifiersFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\splitIdentifiers.txt";
        String dictionaryFile = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Dictionarywords.txt";
        Set<String> identifiers = readIdentifiersFromFile(identifiersFile);
        Set<String> dictionary = readWordsFromFile(dictionaryFile);
        Set<String> unrecognizedIdentifiers = compareIdentifiersToDictionary(identifiers, dictionary);
        Set<String> recognizedIdentifiers = new HashSet<>(identifiers);
        recognizedIdentifiers.removeAll(unrecognizedIdentifiers);
        try (PrintWriter unrecognizedWriter = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\UnrecognizedIdentifiers.txt");
             PrintWriter recognizedWriter = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\RecognizedIdentifiers.txt")) {
            for (String unrecognized : unrecognizedIdentifiers) {
                unrecognizedWriter.println(unrecognized);
            }
            for (String recognized : recognizedIdentifiers) {
                recognizedWriter.println(recognized);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> readIdentifiersFromFile(String filename) {
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

    public static Set<String> readWordsFromFile(String filename) {
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

    public static Set<String> compareIdentifiersToDictionary(Set<String> identifiers, Set<String> dictionary) {
        Set<String> unrecognizedIdentifiers = new HashSet<>();
        for (String identifier : identifiers) {
            String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])|_");
            boolean recognized = false;
            for (String word : words) {
                if (dictionary.contains(word)) {
                    recognized = true;
                    break;
                }
            }
            if (!recognized) {
                unrecognizedIdentifiers.add(identifier);
            }
        }
        return unrecognizedIdentifiers;
    }
}
