import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifierSplitter {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt";
        String outputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\splittedwords.txt";

    }

    public static void IdentifierSplitter (){
        String filePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt";
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                splitLine(line);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

    }

    public static void splitLine(String line) {
        Pattern pattern = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String identifier = matcher.group();
            String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
            System.out.println(Arrays.toString(words));

        }


    }


}
