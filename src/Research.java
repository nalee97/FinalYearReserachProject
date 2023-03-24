import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Research {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\moham\\Desktop\\samplefiles\\Test3.java");
        int length = (int) file.length();
        byte[] bytes = new byte[length];


        String content = null;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(bytes);
            content = new String(bytes);
            Set<String> identifiers = (Set<String>) IdentifierExtractor.extractIdentifiers(content);
            try (PrintWriter writer = new PrintWriter("C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt")) {
                for (String s : identifiers) {
                    writer.println(s);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //IdentifierSplitter();


        } catch (IOException e) {
            e.printStackTrace();
        }
        IdentifierExtractor ex = new IdentifierExtractor();
        ex.toString();

        IdentifierComparerWithExtractedIdentifiers ice =new IdentifierComparerWithExtractedIdentifiers();
        ice.compareWithIdExtractor();

        IdentifierSplitter is = new IdentifierSplitter();
        is.IdentifierSplitter();

        IdentifierChecker ic = new IdentifierChecker();
        ic.identifierChecker();

//        DictionaryWordIdentifier Dc = new DictionaryWordIdentifier();
//        Dc.DictinaryCompare();
        JaccardSimilarity jc = new JaccardSimilarity();
        jc.jaccardsimilarity();



    }


    }
