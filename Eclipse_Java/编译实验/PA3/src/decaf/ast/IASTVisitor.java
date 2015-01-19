package decaf.ast;

public interface IASTVisitor {
	void visit(Program program);

	void visit(ClassDefn classDefn);

	void visit(VarDecl varDecl);

	void visit(FuncDefn funcDefn);

	void visit(IntType intType);

	void visit(BoolType boolType);

	void visit(DoubleType doubleType);

	void visit(StringType stringType);

	void visit(VoidType voidType);

	void visit(ClassType classType);

	void visit(ArrayType arrayType);

	void visit(AssignStmt assignStmt);

	void visit(ReturnStmt returnStmt);

	void visit(PrintStmt printStmt);

	void visit(IfStmt ifStmt);

	void visit(ForStmt forStmt);

	void visit(WhileStmt whileStmt);
	
	void visit(BreakStmt breakStmt);

	void visit(ExprStmt exprStmt);

	void visit(VarDeclStmt varDeclStmt);
	
	void visit(StmtBlock stmtBlock);

	void visit(CallExpr callExpr);

	void visit(VarRef varRef);

	void visit(ArrayRef arrayRef);

	void visit(LValueExpr lValueExpr);

	void visit(IntConst intConst);

	void visit(BoolConst boolConst);

	void visit(DoubleConst doubleConst);

	void visit(StringConst stringConst);

	void visit(ThisExpr thisExpr);

	void visit(NullExpr nullExpr);

	void visit(ReadIntExpr readIntExpr);

	void visit(ReadLineExpr readStringExpr);

	void visit(NewObjExpr newObjExpr);

	void visit(NewArrayExpr newArrayExpr);

	void visit(AddExpr addExpr);

	void visit(SubExpr subExpr);

	void visit(MulExpr mulExpr);

	void visit(DivExpr divExpr);

	void visit(ModExpr modExpr);

	void visit(NegExpr negExpr);

	void visit(AndExpr andExpr);

	void visit(OrExpr orExpr);

	void visit(NotExpr notExpr);

	void visit(GtrExpr gtrExpr);

	void visit(GeqExpr geqExpr);

	void visit(EquExpr equExpr);

	void visit(NeqExpr neqExpr);

	void visit(LeqExpr leqExpr);

	void visit(LesExpr lesExpr);
}
