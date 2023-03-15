import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifierSplitter {

    public void IdentifierSplitter()
    {
        String filePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt";
        String outputFilePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\split.txt";
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(outputFilePath);
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = splitLine(line);
                fw.write(Arrays.toString(words) + "\n");
            }
            br.close();
            fr.close();
            fw.close();
            System.out.println("Output written to file: " + outputFilePath);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static String[] splitLine(String line) {
        Pattern pattern = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
        Matcher matcher = pattern.matcher(line);
        String[] result = new String[0];

        while (matcher.find()) {
            String identifier = matcher.group();
            String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
            result = Arrays.copyOf(result, result.length + words.length);
            System.arraycopy(words, 0, result, result.length - words.length, words.length);
        }
        return result;
    }
}
