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
    @Override
    public T visitAdditiveExpression(JavaScriptParser.AdditiveExpressionContext ctx) {
        if (ctx.singleExpression(0).getText().startsWith("'") || ctx.singleExpression(0).getText().startsWith("\"")) {
            manager.AddCodeSmell(SMELL.ConCat, ctx.singleExpression(0).getStart().getLine(),
                    ctx.singleExpression(0).getStart().getCharPositionInLine());
        }
        return super.visitAdditiveExpression(ctx);
    }
}
