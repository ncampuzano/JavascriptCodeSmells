public class VisitorJavascript<T> extends JavaScriptParserBaseVisitor<T> {
    public CodeSmellsManager manager = new CodeSmellsManager();

    @Override
    public T visitVariableDeclaration(JavaScriptParser.VariableDeclarationContext ctx) {
        if (ctx.singleExpression().getText().equals("this")) {
            manager.AddCodeSmell(SMELL.ThatThis, ctx.singleExpression().getStart().getLine(),
                    ctx.singleExpression().getStart().getCharPositionInLine());
        }
        return super.visitVariableDeclaration(ctx);
    }
}
