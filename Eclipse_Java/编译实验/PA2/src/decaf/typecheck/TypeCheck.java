package decaf.typecheck;
import java.security.acl.Owner;

import decaf.error.*;
import decaf.ast.*;
import decaf.Driver;
import decaf.ast.ArrayRef;
import decaf.ast.ArrayType;
import decaf.ast.BoolConst;
import decaf.ast.BoolType;
import decaf.ast.ClassDefn;
import decaf.ast.ClassType;
import decaf.ast.DoubleType;
import decaf.ast.ExprStmt;
import decaf.ast.Field;
import decaf.ast.ForStmt;
import decaf.ast.IntConst;
import decaf.ast.IntType;
import decaf.ast.LValue;
import decaf.ast.NegExpr;
import decaf.ast.NewObjExpr;
import decaf.ast.NoActionASTVisitor;
import decaf.ast.Program;
import decaf.ast.Statement;
import decaf.ast.StmtBlock;
import decaf.ast.StringType;
import decaf.ast.ThisExpr;
import decaf.ast.VarDecl;
import decaf.ast.VarRef;
import decaf.ast.VoidType;
import decaf.error.BadArrElementError;
import decaf.error.ClassNotFoundError;
import decaf.error.DecafError;
import decaf.error.IncompatUnOpError;
import decaf.error.NotArrayError;
import decaf.error.RefNonStaticError;
import decaf.error.SubNotIntError;
import decaf.error.UndeclVarError;
import decaf.scope.ScopeStack;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.symbol.Variable;
import decaf.type.BaseType;

// TODO: 补充其它类型结点的检查函数，基本上所有的ast结点都需要相应的检查函数
public class TypeCheck extends NoActionASTVisitor {

	private ScopeStack table;

	private Function currentFunction;
	
	private Class currentClass;
	
	private int loopCount;

	public TypeCheck(ScopeStack table) {
		this.table = table;
		loopCount=0;
	}

	public static void checkType(Program tree) {
		new TypeCheck(Driver.getDriver().getTable()).visit(tree);
	}

	@Override
	public void visit(NegExpr negExpr) {
		negExpr.expr.accept(this);
		if (negExpr.expr.type.equal(BaseType.ERROR)
				|| negExpr.expr.type.equal(BaseType.INT)
				|| negExpr.expr.type.equal(BaseType.DOUBLE)) {
			negExpr.type = negExpr.expr.type;
		} else {
			issueError(new IncompatUnOpError(negExpr.getLocation(), "-",
					negExpr.expr.type.toString()));
			negExpr.type = BaseType.ERROR;
		}
	}
	
	public void visit(AddExpr addExpr){
		addExpr.left.accept(this);
		addExpr.right.accept(this);
	    if(addExpr.left.type.equal(BaseType.INT)&&addExpr.right.type.equal(BaseType.INT)
				||
				addExpr.left.type.equal(BaseType.DOUBLE)&&addExpr.right.type.equal(BaseType.DOUBLE)
				||
				addExpr.left.type.equal(BaseType.ERROR)
				||
				addExpr.right.type.equal(BaseType.ERROR))
			addExpr.type=addExpr.left.type;
		else{
			issueError(new IncompatBinOpError(addExpr.getLocation(),addExpr.left.type.toString(),"+",addExpr.right.type.toString()));
			addExpr.type=addExpr.left.type;
		}
	}
	
	public void visit(SubExpr subExpr){
		subExpr.left.accept(this);
		subExpr.right.accept(this);
	    if(subExpr.left.type.equal(BaseType.INT)&&subExpr.right.type.equal(BaseType.INT)
				||
				subExpr.left.type.equal(BaseType.DOUBLE)&&subExpr.right.type.equal(BaseType.DOUBLE)
				||
				subExpr.left.type.equal(BaseType.ERROR)
				||
				subExpr.right.type.equal(BaseType.ERROR))
			subExpr.type=subExpr.left.type;
		else{
			issueError(new IncompatBinOpError(subExpr.getLocation(),subExpr.left.type.toString(),"-",subExpr.right.type.toString()));
			subExpr.type=subExpr.left.type;
		}
	}
	
	public void visit(MulExpr mulExpr){
		mulExpr.left.accept(this);
		mulExpr.right.accept(this);
	    if(mulExpr.left.type.equal(BaseType.INT)&&mulExpr.right.type.equal(BaseType.INT)
				||
				mulExpr.left.type.equal(BaseType.DOUBLE)&&mulExpr.right.type.equal(BaseType.DOUBLE)
				||
				mulExpr.left.type.equal(BaseType.ERROR)
				||
				mulExpr.right.type.equal(BaseType.ERROR))
			mulExpr.type=mulExpr.left.type;
		else{
			issueError(new IncompatBinOpError(mulExpr.getLocation(),mulExpr.left.type.toString(),"*",mulExpr.right.type.toString()));
			mulExpr.type=mulExpr.left.type;
		}
	}
	
	public void visit(DivExpr divExpr){
		divExpr.left.accept(this);
		divExpr.right.accept(this);
	    if(divExpr.left.type.equal(BaseType.INT)&&divExpr.right.type.equal(BaseType.INT)
				||
				divExpr.left.type.equal(BaseType.DOUBLE)&&divExpr.right.type.equal(BaseType.DOUBLE)
				||
				divExpr.left.type.equal(BaseType.ERROR)
				||
				divExpr.right.type.equal(BaseType.ERROR))
			divExpr.type=divExpr.left.type;
		else{
			issueError(new IncompatBinOpError(divExpr.getLocation(),divExpr.left.type.toString(),"/",divExpr.right.type.toString()));
			divExpr.type=divExpr.left.type;
		}
	}
	
	public void visit(LesExpr lesExpr){
		lesExpr.left.accept(this);
		lesExpr.right.accept(this);
	    if(lesExpr.left.type.equal(BaseType.INT)&&lesExpr.right.type.equal(BaseType.INT)
				||
				lesExpr.left.type.equal(BaseType.DOUBLE)&&lesExpr.right.type.equal(BaseType.DOUBLE)
				||
				lesExpr.left.type.equal(BaseType.ERROR)
				||
				lesExpr.right.type.equal(BaseType.ERROR))
			lesExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(lesExpr.getLocation(),lesExpr.left.type.toString(),"<",lesExpr.right.type.toString()));
			lesExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(LeqExpr leqExpr){
		leqExpr.left.accept(this);
		leqExpr.right.accept(this);
	    if(leqExpr.left.type.equal(BaseType.INT)&&leqExpr.right.type.equal(BaseType.INT)
				||
				leqExpr.left.type.equal(BaseType.DOUBLE)&&leqExpr.right.type.equal(BaseType.DOUBLE)
				||
				leqExpr.left.type.equal(BaseType.ERROR)
				||
				leqExpr.right.type.equal(BaseType.ERROR))
			leqExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(leqExpr.getLocation(),leqExpr.left.type.toString(),"<=",leqExpr.right.type.toString()));
			leqExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(GtrExpr gtrExpr){
		gtrExpr.left.accept(this);
		gtrExpr.right.accept(this);
	    if(gtrExpr.left.type.equal(BaseType.INT)&&gtrExpr.right.type.equal(BaseType.INT)
				||
				gtrExpr.left.type.equal(BaseType.DOUBLE)&&gtrExpr.right.type.equal(BaseType.DOUBLE)
				||
				gtrExpr.left.type.equal(BaseType.ERROR)
				||
				gtrExpr.right.type.equal(BaseType.ERROR))
			gtrExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(gtrExpr.getLocation(),gtrExpr.left.type.toString(),">",gtrExpr.right.type.toString()));
			gtrExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(GeqExpr geqExpr){
		geqExpr.left.accept(this);
		geqExpr.right.accept(this);
	    if(geqExpr.left.type.equal(BaseType.INT)&&geqExpr.right.type.equal(BaseType.INT)
				||
				geqExpr.left.type.equal(BaseType.DOUBLE)&&geqExpr.right.type.equal(BaseType.DOUBLE)
				||
				geqExpr.left.type.equal(BaseType.ERROR)
				||
				geqExpr.right.type.equal(BaseType.ERROR))
			geqExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(geqExpr.getLocation(),geqExpr.left.type.toString(),">=",geqExpr.right.type.toString()));
			geqExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(ModExpr modExpr){
		modExpr.left.accept(this);
		modExpr.right.accept(this);
	    if(modExpr.left.type.equal(BaseType.INT)&&modExpr.right.type.equal(BaseType.INT)||
				modExpr.left.type.equal(BaseType.ERROR)
				||
				modExpr.right.type.equal(BaseType.ERROR))
			modExpr.type=BaseType.INT;
		else{
			issueError(new IncompatBinOpError(modExpr.getLocation(),modExpr.left.type.toString(),"%",modExpr.right.type.toString()));
			modExpr.type=BaseType.INT;
		}
	}
	
	public void visit(EquExpr equExpr){
		equExpr.left.accept(this);
		equExpr.right.accept(this);
		if(equExpr.left.type.compatible(equExpr.right.type)
			||
			equExpr.right.type.compatible(equExpr.left.type)||
			equExpr.left.type.equal(BaseType.ERROR)
			||
			equExpr.right.type.equal(BaseType.ERROR))
			equExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(equExpr.getLocation(),equExpr.left.type.toString(),"==",equExpr.right.type.toString()));
			equExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(NeqExpr neqExpr){
		neqExpr.left.accept(this);
		neqExpr.right.accept(this);
		if(neqExpr.left.type.compatible(neqExpr.right.type)
			||
			neqExpr.right.type.compatible(neqExpr.left.type)||
			neqExpr.left.type.equal(BaseType.ERROR)
			||
			neqExpr.right.type.equal(BaseType.ERROR))
			neqExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(neqExpr.getLocation(),neqExpr.left.type.toString(),"!=",neqExpr.right.type.toString()));
			neqExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(AndExpr andExpr){
		andExpr.left.accept(this);
		andExpr.right.accept(this);
	    if(andExpr.left.type.equal(BaseType.BOOL)&&andExpr.right.type.equal(BaseType.BOOL)||
				andExpr.left.type.equal(BaseType.ERROR)
				||
				andExpr.right.type.equal(BaseType.ERROR))
			andExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(andExpr.getLocation(),andExpr.left.type.toString(),"&&",andExpr.right.type.toString()));
			andExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(OrExpr orExpr){
		orExpr.left.accept(this);
		orExpr.right.accept(this);
	    if(orExpr.left.type.equal(BaseType.BOOL)&&orExpr.right.type.equal(BaseType.BOOL)||
				orExpr.left.type.equal(BaseType.ERROR)
				||
				orExpr.right.type.equal(BaseType.ERROR))
			orExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatBinOpError(orExpr.getLocation(),orExpr.left.type.toString(),"||",orExpr.right.type.toString()));
			orExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(NotExpr notExpr){
		notExpr.expr.accept(this);
	    if(notExpr.expr.type.equal(BaseType.BOOL)||
				notExpr.expr.type.equal(BaseType.ERROR))
			notExpr.type=BaseType.BOOL;
		else{
			issueError(new IncompatUnOpError(notExpr.getLocation(),"!",notExpr.expr.type.toString()));
			notExpr.type=BaseType.BOOL;
		}
	}
	
	public void visit(ReadIntExpr riExpr){
		riExpr.type=BaseType.INT;
	}
	
	public void visit(ReadLineExpr riExpr){
		riExpr.type=BaseType.STRING;
	}
	
	public void visit(NullExpr nullExpr){
		nullExpr.type=BaseType.NULL;
	}
	
	
	
	
	public void visit(CallExpr callExpr){
		if(callExpr.receiver==null){
			Symbol s=currentClass.getAssociatedScope().lookupVisible(callExpr.method);
			if(s==null){
				issueError(new FieldNotFoundError(callExpr.getLocation(),callExpr.method,((decaf.type.ClassType)(currentClass.getType())).toString()));
				callExpr.type=BaseType.ERROR;
			}
			else{
				if(s.isFunction()){
					Function f=(Function)s;
					if(f.isStatik()==true){
						if(callExpr.actuals.size()!=f.getType().numOfParams())
							issueError(new BadArgCountError(callExpr.getLocation(),callExpr.method,f.getType().numOfParams(),callExpr.actuals.size()));
						else if(callExpr.actuals.size()!=0){
							for(int i=0;i<callExpr.actuals.size();i++){
								callExpr.actuals.get(i).accept(this);
								if(!callExpr.actuals.get(i).type.compatible(f.getType().getArgList().get(i)))
									issueError(new BadArgTypeError(callExpr.actuals.get(i).getLocation(),i+1,callExpr.actuals.get(i).type.toString(),f.getType().getArgList().get(i).toString()));
							}
						}
						callExpr.type=f.getReturnType();
					}
					else{
						if(currentFunction.isStatik())
							issueError(new RefNonStaticError(callExpr.getLocation(),currentFunction.getName(),f.getName()));
						if(callExpr.actuals.size()!=f.getType().numOfParams()-1)
							issueError(new BadArgCountError(callExpr.getLocation(),callExpr.method,f.getType().numOfParams()-1,callExpr.actuals.size()));
						else if(callExpr.actuals.size()!=0){
							for(int i=1;i<=callExpr.actuals.size();i++){
								callExpr.actuals.get(i-1).accept(this);
								if(!callExpr.actuals.get(i-1).type.compatible(f.getType().getArgList().get(i)))
									issueError(new BadArgTypeError(callExpr.actuals.get(i-1).getLocation(),i,callExpr.actuals.get(i-1).type.toString(),f.getType().getArgList().get(i).toString()));
							}
						}
						callExpr.type=f.getReturnType();
					}
				}
				else{
					issueError(new NotClassMethodError(callExpr.getLocation(),s.getName(),((decaf.type.ClassType)(currentClass.getType())).toString()));
					callExpr.type=BaseType.ERROR;
				}
			}
		}
		else{//receiver!=null
			callExpr.receiver.usedForRef=true;
			callExpr.receiver.accept(this);
			if(callExpr.receiver.type.equal(BaseType.ERROR))
				callExpr.type=BaseType.ERROR;
			else if(callExpr.receiver.isClass==true){
				Class c=table.lookupClass(((decaf.type.ClassType)(callExpr.receiver.type)).getSymbol().getName());
				Symbol s=c.getAssociatedScope().lookupVisible(callExpr.method);
				if(s==null){
					issueError(new FieldNotFoundError(callExpr.getLocation(),callExpr.method,((decaf.type.ClassType)(callExpr.receiver.type)).toString()));
					callExpr.type=BaseType.ERROR;
				}
				else{
					if(s.isFunction()){
						Function f=(Function)s;
						if(f.isStatik()==false){
							issueError(new NotClassFieldError(callExpr.getLocation(),callExpr.method,((decaf.type.ClassType)(callExpr.receiver.type)).toString()));
							if(callExpr.actuals.size()!=f.getType().numOfParams()-1)
								issueError(new BadArgCountError(callExpr.getLocation(),callExpr.method,f.getType().numOfParams()-1,callExpr.actuals.size()));
							else if(callExpr.actuals.size()!=0){
								for(int i=1;i<=callExpr.actuals.size();i++){
									callExpr.actuals.get(i-1).accept(this);
									if(!callExpr.actuals.get(i-1).type.compatible(f.getType().getArgList().get(i)))
										issueError(new BadArgTypeError(callExpr.actuals.get(i-1).getLocation(),i,callExpr.actuals.get(i-1).type.toString(),f.getType().getArgList().get(i).toString()));
								}
							}
							callExpr.type=f.getReturnType();
						}
						else{
							if(callExpr.actuals.size()!=f.getType().numOfParams())
								issueError(new BadArgCountError(callExpr.getLocation(),callExpr.method,f.getType().numOfParams(),callExpr.actuals.size()));
							else if(callExpr.actuals.size()!=0){
								for(int i=0;i<callExpr.actuals.size();i++){
									callExpr.actuals.get(i).accept(this);
									if(!callExpr.actuals.get(i).type.compatible(f.getType().getArgList().get(i)))
										issueError(new BadArgTypeError(callExpr.actuals.get(i).getLocation(),i+1,callExpr.actuals.get(i).type.toString(),f.getType().getArgList().get(i).toString()));
								}
							}
							callExpr.type=f.getReturnType();
						}
					}
					else{
						issueError(new NotClassMethodError(callExpr.getLocation(),s.getName(),((decaf.type.ClassType)(callExpr.receiver.type)).toString()));
						callExpr.type=BaseType.ERROR;
					}
				}
			}
			else if(callExpr.receiver.type.isClassType()){
				Symbol s=((decaf.type.ClassType)(callExpr.receiver.type)).getClassScope().lookupVisible(callExpr.method);
				if(s==null){
					issueError(new FieldNotFoundError(callExpr.getLocation(),callExpr.method,((decaf.type.ClassType)(callExpr.receiver.type)).toString()));
					callExpr.type=BaseType.ERROR;
				}
				else{ 
					if(s.isFunction()){
						Function f=(Function)s;
						if(f.isStatik()==true){
							if(callExpr.actuals.size()!=f.getType().numOfParams())
								issueError(new BadArgCountError(callExpr.getLocation(),callExpr.method,f.getType().numOfParams(),callExpr.actuals.size()));
							else if(callExpr.actuals.size()!=0){
								for(int i=0;i<callExpr.actuals.size();i++){
									callExpr.actuals.get(i).accept(this);
									if(!callExpr.actuals.get(i).type.compatible(f.getType().getArgList().get(i)))
										issueError(new BadArgTypeError(callExpr.actuals.get(i).getLocation(),i+1,callExpr.actuals.get(i).type.toString(),f.getType().getArgList().get(i).toString()));
								}
							}
							callExpr.type=f.getReturnType();
						}
						else{
							if(callExpr.actuals.size()!=f.getType().numOfParams()-1)
								issueError(new BadArgCountError(callExpr.getLocation(),callExpr.method,f.getType().numOfParams()-1,callExpr.actuals.size()));
							else if(callExpr.actuals.size()!=0){
								for(int i=1;i<=callExpr.actuals.size();i++){
									callExpr.actuals.get(i-1).accept(this);
									if(!callExpr.actuals.get(i-1).type.compatible(f.getType().getArgList().get(i)))
										issueError(new BadArgTypeError(callExpr.actuals.get(i-1).getLocation(),i,callExpr.actuals.get(i-1).type.toString(),f.getType().getArgList().get(i).toString()));
								}
							}
							callExpr.type=f.getReturnType();
						}
					}
					else{
						issueError(new NotClassMethodError(callExpr.getLocation(),s.getName(),((decaf.type.ClassType)(callExpr.receiver.type)).toString()));
						callExpr.type=BaseType.ERROR;
					}
				}
			}
			else if(callExpr.receiver.type.isArrayType()){
				if(!callExpr.method.equals("length")){
					issueError(new NotClassFieldError(callExpr.getLocation(),callExpr.method,callExpr.receiver.type.toString()));
					callExpr.type=BaseType.INT;
				}
				else if(callExpr.actuals.size()!=0){
					issueError(new BadLengthArgError(callExpr.getLocation(),callExpr.actuals.size()));
					callExpr.type=BaseType.INT;
				}
				else
					callExpr.type=BaseType.INT;
			}
			else{
				if(callExpr.method.equals("length")){
					issueError(new BadLengthError(callExpr.getLocation()));
					callExpr.type=BaseType.ERROR;
				}
				else{
					issueError(new NotClassFieldError(callExpr.getLocation(),callExpr.method,((decaf.type.Type)(callExpr.receiver.type)).toString()));
					callExpr.type=BaseType.ERROR;
				}
			}
			
		}
	}
	
	public void visit(ThisExpr thisExpr){
		if(currentFunction.isStatik()){
			issueError(new ThisInStaticFuncError(thisExpr.getLocation()));
			thisExpr.type=BaseType.ERROR;
		}
		else
			thisExpr.type=currentClass.getType();
	}
	
	
	public void visit(LValueExpr lvExpr){
		lvExpr.lv.usedForRef=lvExpr.usedForRef;
		lvExpr.lv.accept((this));
		lvExpr.type=lvExpr.lv.type;
		lvExpr.isClass=lvExpr.lv.isClass;
	}

	public void visit(BoolConst boolConst) {
		boolConst.type = BaseType.BOOL;
	}

	@Override
	public void visit(IntConst intConst) {
		intConst.type = BaseType.INT;
	}
	
	public void visit(DoubleConst doubleConst) {
		doubleConst.type = BaseType.DOUBLE;
	}
	
	public void visit(StringConst stringConst) {
		stringConst.type = BaseType.STRING;
	}
	
	public void visit(VarDecl varDecl){
		varDecl.type.accept(this);
	}
	
	
	

	@Override
	public void visit(NewObjExpr newObjExpr) {
		Class c = table.lookupClass(newObjExpr.className);
		newObjExpr.symbol = c;
		if (c == null) {
			issueError(new ClassNotFoundError(newObjExpr.getLocation(),
					newObjExpr.className));
			newObjExpr.type = BaseType.ERROR;
		} else {
			newObjExpr.type = c.getType();
		}
	}
	
	public void visit(NewArrayExpr naExpr){
		naExpr.elementType.accept(this);
		naExpr.length.accept(this);
		if(!naExpr.elementType.type.equal(BaseType.VOID)&&!naExpr.elementType.type.equal(BaseType.ERROR))
			naExpr.type=new decaf.type.ArrayType(naExpr.elementType.type);
		if(naExpr.elementType.type.equal(BaseType.VOID)){
			issueError(new BadArrElementError(naExpr.elementType.getLocation()));
			naExpr.type=BaseType.ERROR;
		}
		if(naExpr.elementType.type.equal(BaseType.ERROR))
			naExpr.type=BaseType.ERROR;
		if(!naExpr.length.type.equal(BaseType.INT)){
				issueError(new BadNewArrayLength(naExpr.length.getLocation()));
		}
		
	}

	@Override
	public void visit(ArrayRef arrayRef) {
		arrayRef.lvKind = LValue.Kind.ARRAY_ELEMENT;
		arrayRef.array.accept(this);
		if (!arrayRef.array.type.isArrayType()) {
			issueError(new NotArrayError(arrayRef.array.getLocation()));
			arrayRef.type = BaseType.ERROR;
		} else {
			arrayRef.type = ((decaf.type.ArrayType) arrayRef.array.type)
					.getElementType();
		}
		arrayRef.index.accept(this);
		if (!arrayRef.index.type.equal(BaseType.INT)) {
			issueError(new SubNotIntError(arrayRef.getLocation()));
		}
	}

	@Override
	public void visit(VarRef varRef) {
		if (varRef.owner == null) {
			Symbol v = table.lookupBeforeLocation(varRef.name, varRef
					.getLocation());
			if (v == null) {
				issueError(new UndeclVarError(varRef.getLocation(), varRef.name));
				varRef.type = BaseType.ERROR;
			} else if (v.isVariable()) {
				Variable var = (Variable) v;
				varRef.type = var.getType();
				varRef.symbol = var;
				if (var.isLocalVar()) {
					varRef.lvKind = LValue.Kind.LOCAL_VAR;
				} else if (var.isParam()) {
					varRef.lvKind = LValue.Kind.PARAM_VAR;
				} else {
					if (currentFunction.isStatik()) {
						issueError(new RefNonStaticError(varRef.getLocation(),
								currentFunction.getName(), varRef.name));
					} else {
						varRef.owner = new ThisExpr(varRef.getLocation());
						varRef.owner.accept(this);
					}
					varRef.lvKind = LValue.Kind.MEMBER_VAR;
				}
			} else {
				varRef.type = v.getType();
				if (v.isClass()) {
					if (varRef.usedForRef) {
						varRef.isClass = true;
					} else {
						issueError(new UndeclVarError(varRef.getLocation(),
								varRef.name));
						varRef.type = BaseType.ERROR;
					}

				}
			}
		} else {
			// TODO: owner != null
			varRef.owner.usedForRef=true;
			varRef.owner.accept(this);
			if(varRef.owner.type.equal(BaseType.ERROR))
				varRef.type=BaseType.ERROR;
			else if(varRef.owner.isClass==true){
				Class c=table.lookupClass(((decaf.type.ClassType)(varRef.owner.type)).getSymbol().getName());
					Symbol s=c.getAssociatedScope().lookupVisible(varRef.name);
					if(s==null){
						issueError(new NotClassFieldError(varRef.getLocation(),varRef.name,((decaf.type.ClassType)(varRef.owner.type)).toString()));
						varRef.type=BaseType.ERROR;
					}
					else{
						if(s.isVariable()){
							issueError(new NotClassFieldError(varRef.getLocation(),varRef.name,((decaf.type.ClassType)(varRef.owner.type)).toString()));
							varRef.type=s.getType();
						}
						else if(s.isFunction()){
							varRef.type=s.getType();
						}
					}
			}
			else if(varRef.owner.type.isClassType()){
				Symbol s=((decaf.type.ClassType)(varRef.owner.type)).getClassScope().lookupVisible(varRef.name);
				if(s==null){
					issueError(new FieldNotFoundError(varRef.getLocation(),varRef.name,((decaf.type.ClassType)(varRef.owner.type)).toString()));
					varRef.type=BaseType.ERROR;
				}
				else if(s.isVariable()){
					if(!currentClass.getType().compatible((decaf.type.ClassType)(varRef.owner.type))){
						issueError(new FieldNotAccessError(varRef.getLocation(),varRef.name,((decaf.type.ClassType)(varRef.owner.type)).toString()));
						varRef.type=s.getType();
					}
					else
						varRef.type=s.getType();
				}
				else if(s.isFunction()){
					varRef.type=s.getType();
				}
			}
			else{
				issueError(new NotClassFieldError(varRef.getLocation(),varRef.name,((decaf.type.Type)(varRef.owner.type)).toString()));
				varRef.type=BaseType.ERROR;
			}
				
		}
	}

	@Override
	public void visit(Program program) {
		table.open(program.globalScope);
		for (ClassDefn cd : program.classes) {
			cd.accept(this);
		}
		table.close();
	}

	public void visit(ClassDefn classDefn) {
		currentClass=classDefn.symbol;
		table.open(classDefn.symbol.getAssociatedScope());
		for (Field f : classDefn.fields) {
			f.accept(this);
		}
		table.close();
		currentClass=null;
	}

	public void visit(FuncDefn funcDefn){
		funcDefn.returnType.accept(this);
		currentFunction=funcDefn.symbol;
		table.open(funcDefn.symbol.getAssociatedScope());
		for (VarDecl d : funcDefn.formals) {
			d.accept(this);
		}
		funcDefn.body.accept(this);
		table.close();
		currentFunction=null;
	}
	@Override
	public void visit(StmtBlock stmtBlock) {
		table.open(stmtBlock.associatedScope);
		for (Statement s : stmtBlock.block) {
			s.accept(this);
		}
		table.close();
	}
	
	public void visit(AssignStmt assignStmt){
		assignStmt.left.accept(this);
		assignStmt.expr.accept(this);
		if(!assignStmt.expr.type.compatible(assignStmt.left.type)){
			issueError(new IncompatBinOpError(assignStmt.getLocation(),assignStmt.left.type.toString(),"=",assignStmt.expr.type.toString()));
		}
	}
	
	public void visit(ForStmt forStmt) {
		if(forStmt.init!=null){
			forStmt.init.accept(this);
		}
		forStmt.condition.accept(this);
		if(!forStmt.condition.type.compatible(BaseType.BOOL)){
			issueError(new BadTestExpr(forStmt.condition.getLocation()));
		}
		if(forStmt.update!=null){
			forStmt.update.accept(this);
		}
		if(forStmt.loopBody != null) {
			loopCount++;
			forStmt.loopBody.accept(this);
			loopCount--;
		}
	}
	
	public void visit(BreakStmt breakStmt){
		if(loopCount==0)
			issueError(new BreakOutOfLoopError(breakStmt.getLocation()));
	}
	
	public void visit(IfStmt ifStmt){
		ifStmt.condition.accept(this);
		if(!ifStmt.condition.type.compatible(BaseType.BOOL)){
			issueError(new BadTestExpr(ifStmt.condition.getLocation()));
		}
		if (ifStmt.trueBranch != null) {
			ifStmt.trueBranch.accept(this);
		}
		if (ifStmt.falseBranch != null) {
			ifStmt.falseBranch.accept(this);
		}
	}
	
	public void visit(WhileStmt whileStmt){
		whileStmt.condition.accept(this);
		if(!whileStmt.condition.type.compatible(BaseType.BOOL)){
			issueError(new BadTestExpr(whileStmt.condition.getLocation()));
		}
		if(whileStmt.loopBody!=null){
			loopCount++;
			whileStmt.loopBody.accept(this);
			loopCount--;
		}
	}
	
	
	
	
	public void visit(VarDeclStmt vdStmt){
		vdStmt.decl.accept(this);
	}

	@Override
	public void visit(ExprStmt exprStmt) {
		exprStmt.expr.accept(this);
	}
	
	public void visit(PrintStmt printStmt){
		for(int i=0;i<printStmt.exprs.size();i++){
			printStmt.exprs.get(i).accept(this);
			if(
					(!printStmt.exprs.get(i).type.equal(BaseType.BOOL))&&
					(!printStmt.exprs.get(i).type.equal(BaseType.INT))&&
					(!printStmt.exprs.get(i).type.equal(BaseType.STRING))
					)
				issueError(new BadPrintArgError(printStmt.exprs.get(i).getLocation(),(new Integer(i+1)).toString(),printStmt.exprs.get(i).type.toString()));
		}
	}
	
	public void visit(ReturnStmt returnStmt){
		if(returnStmt.expr!=null){
			returnStmt.expr.accept(this);
		}
		if(returnStmt.expr==null&&(!currentFunction.getReturnType().equal(BaseType.VOID))){
			issueError(new BadReturnTypeError(returnStmt.getLocation(),currentFunction.getReturnType().toString(),BaseType.VOID.toString()));
		}
		else if(returnStmt.expr.type.equal(BaseType.ERROR)&&currentFunction.getReturnType().equal(BaseType.VOID)){
			issueError(new BadReturnTypeError(returnStmt.getLocation(),currentFunction.getReturnType().toString(),returnStmt.expr.type.toString()));
		}
		else if(returnStmt.expr!=null&&!returnStmt.expr.type.compatible(currentFunction.getReturnType())){
			issueError(new BadReturnTypeError(returnStmt.getLocation(),currentFunction.getReturnType().toString(),returnStmt.expr.type.toString()));
		}
		
	}
	
	// visiting types
	@Override
	public void visit(IntType intType) {
		intType.type = BaseType.INT;
	}
	
	public void visit(BoolType boolType) {
		boolType.type = BaseType.BOOL;
	}
	
	public void visit(DoubleType doubleType) {
		doubleType.type = BaseType.DOUBLE;
	}
	
	public void visit(StringType stringType) {
		stringType.type = BaseType.STRING;
	}
	
	public void visit(VoidType voidType) {
		voidType.type = BaseType.VOID;
	}
	
	public void visit(ArrayType arrayType) {
		arrayType.elementType.accept(this);
		if (arrayType.elementType.type.equal(BaseType.ERROR)) {
			arrayType.type = BaseType.ERROR;
		} else if (arrayType.elementType.type.equal(BaseType.VOID)) {
			issueError(new BadArrElementError(arrayType.getLocation()));
			arrayType.type = BaseType.ERROR;
		} else {
			arrayType.type = new decaf.type.ArrayType(
					arrayType.elementType.type);
		}
	}
	
	public void visit(ClassType classType) {
		Class c = table.lookupClass(classType.name);
		if (c == null) {
			issueError(new ClassNotFoundError(classType.getLocation(),
					classType.name));
			classType.type = BaseType.ERROR;
		} else {
			classType.type = c.getType();
		}
	}
	

	private void issueError(DecafError error) {
		Driver.getDriver().issueError(error);
	}
}
