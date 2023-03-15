import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Research {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\moham\\IdeaProjects\\final research\\src\\ExampleFile.txt");
        int length = (int) file.length();
        byte[] bytes = new byte[length];


        String content = null;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(bytes);
            content = new String(bytes);
            List<String> identifiers = IdentifierExtractor.extractIdentifiers(content);
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


        IdentifierSplitter is = new IdentifierSplitter();
        is.IdentifierSplitter();

        IdentifierChecker ir = new IdentifierChecker();
        ir.identfierchecker();

        DictionaryWordIdentifier Dc = new DictionaryWordIdentifier();
        Dc.DictinaryCompare();



    }
//    public static List<String> extractIdentifiers(String sourceCode) {
//        final List<String> identifiers = new ArrayList<>();
//
//        // Create an AST parser
//        ASTParser parser = ASTParser.newParser(AST.JLS10);
//        parser.setSource(sourceCode.toCharArray());
//        parser.setKind(ASTParser.K_COMPILATION_UNIT);
//
//        // Parse the source code
//        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
//
//        // Visit each node in the AST and extract the identifiers
//        cu.accept(new ASTVisitor() {
//
//            public boolean visit(TypeDeclaration declaration) {
//                SimpleName newClass = declaration.getName();
//                identifiers.add(newClass.getIdentifier().toString());
//                return true;
//            }
//
//            public boolean visit(MethodDeclaration declaration) {
//                SimpleName newMethod = declaration.getName();
//                identifiers.add(newMethod.getIdentifier().toString());
//                return true;
//            }
//
//            public boolean visit(VariableDeclarationFragment declaration1){
//                SimpleName newVariable = declaration1.getName();
//                identifiers.add(newVariable.getIdentifier().toString());
//                return true;
//            }
//
//        });
//
//        return identifiers;
//    }




//    public static void IdentifierSplitter (){
//            String filePath = "C:\\Users\\moham\\IdeaProjects\\final research\\src\\Output.txt";
//            try {
//                FileReader fr = new FileReader(filePath);
//                BufferedReader br = new BufferedReader(fr);
//                String line;
//                while ((line = br.readLine()) != null) {
//                    splitLine(line);
//                }
//                br.close();
//                fr.close();
//            } catch (Exception e) {
//                System.out.println("An error occurred while reading the file: " + e.getMessage());
//            }
//
//    }
//
//        public static void splitLine(String line) {
//            Pattern pattern = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
//            Matcher matcher = pattern.matcher(line);
//
//            while (matcher.find()) {
//                String identifier = matcher.group();
//                String[] words = identifier.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
//                System.out.println(Arrays.toString(words));
//
//        }


    }
