enum SMELL {
    ThatThis, //1 https://elijahmanor.com/talks/js-smells/#/4/2
    ConCat, //2 https://elijahmanor.com/talks/js-smells/#/5/3
    ExtremeChain, //3 https://elijahmanor.com/talks/js-smells/#/6/2
    AnonymousFunction, //4 https://elijahmanor.com/talks/js-smells/#/11/3
    Equality, //5 https://github.com/mohuk/js-code-smells#equality
    BrokenPromise, //6 https://github.com/mohuk/js-code-smells#broken-promises
    MultipleReturn, //7 https://github.com/mohuk/js-code-smells#multiple-return-statements
}

public class CodeSmell {
    public SMELL type;
    public int line;
    public int column;
}