enum SMELL {
    ThatThis, // https://elijahmanor.com/talks/js-smells/#/4/2
    ConCat, // https://elijahmanor.com/talks/js-smells/#/5/3
    ExtremeChain, // https://elijahmanor.com/talks/js-smells/#/6/2
}

public class CodeSmell {
    public SMELL type;
    public int line;
    public int column;
}