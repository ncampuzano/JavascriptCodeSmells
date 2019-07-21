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

    private boolean isLargeChain(JavaScriptParser.MemberDotExpressionContext ctx, int lvl) {
        if (lvl == 5)
            return true;
        if (ctx.singleExpression().getChild(0).getClass() == ctx.getClass()) {
            return isLargeChain(
                    (JavaScriptParser.MemberDotExpressionContext) ctx.singleExpression().getChild(0),
                    lvl+1);
        } else {
            return false;
        }
    }

    @Override
    public T visitMemberDotExpression(JavaScriptParser.MemberDotExpressionContext ctx) {

        if (isLargeChain(ctx, 0)) {
            manager.AddCodeSmell(SMELL.ExtremeChain, ctx.singleExpression().getStart().getLine(),
                    ctx.singleExpression().getStart().getCharPositionInLine());
        }
        return super.visitMemberDotExpression(ctx);
    }

    @Override
    public T visitFunctionExpression(JavaScriptParser.FunctionExpressionContext ctx) {
        if (!ctx.getParent().getClass().getName().equals("JavaScriptParser$AssignmentExpressionContext")) {
            if(ctx.Identifier() == null) {
                manager.AddCodeSmell(SMELL.AnonymousFunction, ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine());
            }
        }
        return super.visitFunctionExpression(ctx);
    }
}
