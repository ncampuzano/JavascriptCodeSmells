import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;
import java.util.ArrayList;

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
                    lvl + 1);
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
        // AnonymouesFunction test
        if (!ctx.getParent().getClass().getName().equals("JavaScriptParser$AssignmentExpressionContext")) {
            if (ctx.Identifier() == null) {
                manager.AddCodeSmell(SMELL.AnonymousFunction, ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine());
            }
        }

        // Broken Promises
        if(ctx.formalParameterList() != null) {
            JavaScriptParser.FormalParameterListContext parameters = ctx.formalParameterList();

        }
        return super.visitFunctionExpression(ctx);
    }

    @Override
    public T visitEqualityExpression(JavaScriptParser.EqualityExpressionContext ctx) {
        if (ctx.Equals_() != null) {
            manager.AddCodeSmell(SMELL.Equality, ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine());
        }
        return super.visitEqualityExpression(ctx);
    }
    @Override
    public T visitArgumentsExpression(JavaScriptParser.ArgumentsExpressionContext ctx) {
        if (ctx.arguments() != null) {
            JavaScriptParser.IdentifierExpressionContext identifierContext = null;
            if (ctx.singleExpression().getClass().getName().equals("JavaScriptParser$IdentifierExpressionContext")) {
                identifierContext = (JavaScriptParser.IdentifierExpressionContext) ctx.singleExpression();
            }
            List<ParseTree> childre = ctx.arguments().children;
            if (identifierContext != null && (!identifierContext.getText().equals("Promise")
                && !identifierContext.getText().equals("then") && !identifierContext.getText().equals("catch"))) {
                for(int i = 0; i < childre.size(); i++) {
                    if (childre.get(i).getClass().getName().equals("JavaScriptParser$FunctionExpressionContext")) {
                        manager.AddCodeSmell(SMELL.BrokenPromise, ctx.getStart().getLine(),
                                ctx.getStart().getCharPositionInLine());
                        return super.visitArgumentsExpression(ctx);
                    }
                }
            }
        }
        return super.visitArgumentsExpression(ctx);
    }
}