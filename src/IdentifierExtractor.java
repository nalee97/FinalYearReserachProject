import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.HashSet;
import java.util.Set;

public class IdentifierExtractor {
    public static Set<String> extractIdentifiers(String sourceCode) {
        final Set<String> identifiers = new HashSet<>();

        // Create an AST parser
        ASTParser parser = ASTParser.newParser(AST.JLS10);
        parser.setSource(sourceCode.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        // Parse the source code
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        // Visit each node in the AST and extract the identifiers
        cu.accept(new ASTVisitor() {

            public boolean visit(TypeDeclaration declaration) {
                SimpleName newClass = declaration.getName();
                identifiers.add(newClass.getIdentifier());
                return true;
            }

            public boolean visit(MethodDeclaration declaration) {
                SimpleName newMethod = declaration.getName();
                identifiers.add(newMethod.getIdentifier());
                return true;
            }

            public boolean visit(VariableDeclarationFragment declaration1) {
                SimpleName newVariable = declaration1.getName();
                identifiers.add(newVariable.getIdentifier());
                return true;
            }

        });

        return identifiers;
    }



}
