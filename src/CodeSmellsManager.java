import java.util.*;

public class CodeSmellsManager {
    ArrayList<CodeSmell> codeSmells = new ArrayList<>();

    private boolean isInside(ArrayList<CodeSmell> array, CodeSmell element) {
        for(int i = 0; i < array.size(); i ++) {
            CodeSmell ele = array.get(i);
            if (ele.column == element.column &&
                ele.line == element.line &&
                ele.type == element.type ) {

                return true;
            }
        }
        return false;
    }

    public void AddCodeSmell(SMELL type, int line, int column) {
        CodeSmell codeSmell = new CodeSmell();
        codeSmell.type = type;
        codeSmell.line = line;
        codeSmell.column = column;
        if (codeSmell.type == SMELL.AnonymousFunction)
            return;
        if (!isInside(codeSmells, codeSmell)) {
            codeSmells.add(codeSmell);
        }
    }
    public void Print() {
        for(int i = 0; i < codeSmells.size(); i ++) {
            CodeSmell smell = codeSmells.get(i);
            System.out.println("Smell " + smell.type + " found in row " + smell.line + " column " + smell.column);
        }
    }
}


