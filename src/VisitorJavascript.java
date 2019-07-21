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
    /*
    Checks for the MULTIPLE RETURNS smell
    This smell may exists if a single function has more than one return in its body
    Uses the auxiliary function hasReturnStatement(), which returns how many return statements exist in a given statement
    (This number may be more than 0 for composite statements like an If statement.
    e.g. an If Statement may have up to two statements inside (The body and the else-body)
    this two bodies may contain any amount of statements inside
    those statements may be returnStatements
     */
    @Override
    public T visitFunctionBody(JavaScriptParser.FunctionBodyContext ctx) {
        int howManyReturnsSeen = 0;
        if(ctx.sourceElements()!= null){
            //For each statement in the body of the function
            for( int i = 0; i < ctx.sourceElements().sourceElement().size() ; i++){
                //Check how many returnStatements exist inside that statement
                howManyReturnsSeen += hasReturnStatement(ctx.sourceElements().sourceElement(i).statement());
                //If its more than one, create the smell
                if(howManyReturnsSeen > 1 ){
                    manager.AddCodeSmell(SMELL.MultipleReturn, ctx.sourceElements().sourceElement(i).statement().getStart().getLine(),
                            ctx.sourceElements().sourceElement(i).statement().getStart().getCharPositionInLine());
                    return super.visitFunctionBody(ctx);
                }
            }
        }
        return super.visitFunctionBody(ctx);
    }
    public int hasReturnStatement(JavaScriptParser.StatementContext ctx){
        int howMany = 0;
        if(ctx.returnStatement() != null){
            howMany++;
        }
        if(ctx.ifStatement()!= null) {
            //An if statement contains multiple statements inside, iterate over all of those
            for (int i = 0; i < ctx.ifStatement().statement().size(); i++){
                //If one of the statements is a block of statements, iterate over all of those too
                if (ctx.ifStatement().statement(i).block() != null && ctx.ifStatement().statement(i).block().statementList() != null) {
                    for (int j = 0; j < ctx.ifStatement().statement(i).block().statementList().statement().size(); j++) {
                        howMany += hasReturnStatement(ctx.ifStatement().statement(i).block().statementList().statement(j));
                    }
                }
                //If one of the statements is a return, count it
                if(ctx.ifStatement().statement(i).returnStatement()!= null){
                    howMany++;
                }
            }
        }
        return howMany;
    }

}