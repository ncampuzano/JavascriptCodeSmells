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
        if (!isInside(codeSmells, codeSmell)) {
            codeSmells.add(codeSmell);
        }
    }
    public void Print() {
        String result = "[";
        for(int i = 0; i < codeSmells.size(); i ++) {
            CodeSmell smell = codeSmells.get(i);
            result += "{ \"type\": \"" + smell.type + "\", \"line\": \"" + smell.line + "\", \"column\": \"" + smell.column + "\"}";
            if (i + 1 < codeSmells.size()) {
                result += ",";
            }
        }
        result += "]";

        System.out.println(result);
    }
    private boolean isEnabled(ArrayList<Integer> x, CodeSmell smell) {
        if (smell.type == SMELL.ThatThis && x.contains(1)) {
            return true;
        }
        if (smell.type == SMELL.ConCat && x.contains(2)) {
            return true;
        }
        if (smell.type == SMELL.ExtremeChain && x.contains(3)) {
            return true;
        }
        if (smell.type == SMELL.AnonymousFunction && x.contains(4)) {
            return true;
        }
        if (smell.type == SMELL.Equality && x.contains(5)) {
            return true;
        }
        if (smell.type == SMELL.BrokenPromise && x.contains(6)) {
            return true;
        }
        if (smell.type == SMELL.MultipleReturn && x.contains(7)) {
            return true;
        }
        return false;
    }

    public void Print(ArrayList<Integer> enableSmells) {
        String result = "[";
        for(int i = 0; i < codeSmells.size(); i ++) {
            CodeSmell smell = codeSmells.get(i);
            if (isEnabled(enableSmells, smell)) {
                result += "{ \"type\": \"" + smell.type + "\", \"line\": \"" + smell.line + "\", \"column\": \"" + smell.column + "\"}";
                if (i + 1 < codeSmells.size()) {
                    result += ",";
                }
            }
        }
        result += "]";
        System.out.println(result);
    }
}


