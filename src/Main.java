import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.ArrayList;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // crear un analizador léxico que se alimenta a partir de la entrada (archivo  o consola)
            JavaScriptLexer lexer;
            if (args.length > 0)
                lexer = new JavaScriptLexer(CharStreams.fromFileName(args[0]));
            else
                lexer = new JavaScriptLexer(CharStreams.fromStream(System.in));

            // Identificar al analizador léxico como fuente de tokens para el sintactico
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // Crear el objeto correspondiente al analizador sintáctico que se alimenta a partir del buffer de tokens
            JavaScriptParser parser = new JavaScriptParser(tokens);
            ParseTree tree = parser.program(); // Iniciar el analisis sintáctico en la regla inicial: programa
            // ParseTreeWalker walker = new ParseTreeWalker();
            // walker.walk(new ListenerJavascript(),tree);
            // System.out.println();
            VisitorJavascript<Object> loader = new VisitorJavascript<>();
            loader.visit(tree);
            if (args.length > 1) {
                String[] y = args[1].split(",");
                ArrayList<Integer> smellsEnabled = new ArrayList<Integer>();
                for(String t : y) {
                    smellsEnabled.add(Integer.parseInt(t));
                }
                loader.manager.Print(smellsEnabled);
            } else {
                loader.manager.Print();
            }

            // System.out.println(tree.toStringTree(parser)); // imprime el arbol al estilo LISP
        } catch (Exception e) {
            System.err.println("Error (Test): " + e);
        }
    }
}
