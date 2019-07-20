import java.util.*;

public class CodeSmellsManager {
    ArrayList<CodeSmell> codeSmells = new ArrayList<>();

    public void AddCodeSmell(SMELL type, int line, int column) {
        CodeSmell codeSmell = new CodeSmell();
        codeSmell.type = type;
        codeSmell.line = line;
        codeSmell.column = column;
        codeSmells.add(codeSmell);
    }
    public void Print() {
        for(int i = 0; i < codeSmells.size(); i ++) {
            CodeSmell smell = codeSmells.get(i);
            System.out.println("Smell " + smell.type + " found in row " + smell.line + " column " + smell.column);
        }
    }
}


