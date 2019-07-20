public class ListenerJavascript extends JavaScriptParserBaseListener {

    @Override
    public void enterVariableDeclaration(JavaScriptParser.VariableDeclarationContext ctx) {
        if (ctx.singleExpression() != null) {
            System.out.println(ctx.singleExpression().getText());
        }
    }
    @Override
    public void exitVariableDeclaration(JavaScriptParser.VariableDeclarationContext ctx) {
        System.out.println(ctx.singleExpression().toString());

    }
}
