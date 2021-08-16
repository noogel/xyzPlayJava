// Generated from /home/xyz/IdeaProjects/xyzPlayJava/drools-example/src/main/resources/antlr4/SearchParser.g4 by ANTLR 4.9.1
package noogel.xyz.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SearchParser}.
 */
public interface SearchParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SearchParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(SearchParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(SearchParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(SearchParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(SearchParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identityExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentityExpr(SearchParser.IdentityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identityExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentityExpr(SearchParser.IdentityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lrExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLrExpr(SearchParser.LrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lrExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLrExpr(SearchParser.LrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(SearchParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link SearchParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(SearchParser.BoolExprContext ctx);
}