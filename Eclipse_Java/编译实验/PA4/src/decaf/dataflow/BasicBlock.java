package decaf.dataflow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import decaf.machdesc.Asm;
import decaf.tac.Label;
import decaf.tac.Tac;
import decaf.tac.Temp;

public class BasicBlock {
	public int bbNum;

	public enum EndKind {
		BY_JUMP, BY_JZERO, BY_RETURN
	}

	public EndKind endKind;

	public int inDegree;

	public Temp var;

	public int[] next;

	public boolean cancelled;

	public boolean mark;

	public Label label;

	public Tac tacChain;

	public Set<Temp> def;

	public Set<Temp> liveUse;

	public Set<Temp> liveIn;

	public Set<Temp> liveOut;

	public Set<Temp> saves;

	public List<Asm> asms;

	public BasicBlock() {
		def = new TreeSet<Temp>(Temp.ID_COMPARATOR);
		liveUse = new TreeSet<Temp>(Temp.ID_COMPARATOR);
		liveIn = new TreeSet<Temp>(Temp.ID_COMPARATOR);
		liveOut = new TreeSet<Temp>(Temp.ID_COMPARATOR);
		next = new int[2];
		asms = new ArrayList<Asm>();
	}

	public void computeDefAndLiveUse() {
		Tac ct=tacChain;//ct:=currentTac
		if(ct==null){
			if(var!=null&&(endKind==EndKind.BY_RETURN||endKind==EndKind.BY_JZERO)){
				if(!def.contains(var))
					liveUse.add(var);
			}
			return;
		}
		while(ct!=null){
			Tac.Kind op=ct.opc;//op:=operation in currentTac
			if(op==Tac.Kind.ADD){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.ASSIGN){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.DIRECT_CALL){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.DIV){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.EQU){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.GEQ){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.GTR){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.INDIRECT_CALL){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.LAND){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.LEQ){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.LES){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.LNOT){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.LOAD){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.LOAD_IMM4){
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
			}
			if(op==Tac.Kind.LOAD_STR_CONST){
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
			}
			if(op==Tac.Kind.LOAD_VTBL){
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
			}
			if(op==Tac.Kind.LOR){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.MOD){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.MUL){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.NEG){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.NEQ){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			if(op==Tac.Kind.PARM){
				if(ct.op0!=null&&!def.contains(ct.op0))
					liveUse.add(ct.op0);
			}
			if(op==Tac.Kind.STORE){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op0!=null&&!def.contains(ct.op0))
					liveUse.add(ct.op0);
				}
			if(op==Tac.Kind.SUB){
				if(ct.op1!=null&&!def.contains(ct.op1))
					liveUse.add(ct.op1);
				if(ct.op2!=null&&!def.contains(ct.op2))
					liveUse.add(ct.op2);
				if(ct.op0!=null&&!def.contains(ct.op0))
					def.add(ct.op0);
				}
			ct=ct.next;
		}
		if(var!=null&&(endKind==EndKind.BY_RETURN||endKind==EndKind.BY_JZERO)){
			if(!def.contains(var))
				liveUse.add(var);
		}
	}

	public void analyzeLiveness() {
		Tac ct=tacChain;//ct:=currentTac
		if(ct==null) return;
		while(ct.next!=null)
			ct=ct.next;
		ct.liveOut=new TreeSet<Temp>(Temp.ID_COMPARATOR);
		Iterator<Temp> it=liveOut.iterator();
		while(it.hasNext()){
			ct.liveOut.add(it.next());
		}
		if(var!=null&&(endKind==EndKind.BY_RETURN||endKind==EndKind.BY_JZERO))
			ct.liveOut.add(var);
		while(ct!=null&&ct.prev!=null){
			ct.prev.liveOut=new TreeSet<Temp>(Temp.ID_COMPARATOR);
			it=ct.liveOut.iterator();
			while(it.hasNext()){
				ct.prev.liveOut.add(it.next());
			}
			if(ct.opc!=Tac.Kind.STORE&&ct.opc!=Tac.Kind.PARM){
				if(ct.op0!=null&&ct.op0.isConst==false&&ct.prev.liveOut.contains(ct.op0))
						ct.prev.liveOut.remove(ct.op0);
				if(ct.op1!=null&&ct.op1.isConst==false)
					ct.prev.liveOut.add(ct.op1);
				if(ct.op2!=null&&ct.op2.isConst==false)
					ct.prev.liveOut.add(ct.op2);
			}
			else if(ct.opc==Tac.Kind.STORE){
				ct.prev.liveOut.add(ct.op0);
				ct.prev.liveOut.add(ct.op1);
			}
			else if(ct.opc==Tac.Kind.PARM){
				ct.prev.liveOut.add(ct.op0);
			}
			ct=ct.prev;
		}
		
	}

	public void printTo(PrintWriter pw) {
		pw.println("BASIC BLOCK " + bbNum + " : ");
		for (Tac t = tacChain; t != null; t = t.next) {
			pw.println("    " + t);
		}
		switch (endKind) {
		case BY_JUMP:
			pw.println("END BY JUMP, goto " + next[0]);
			break;
		case BY_JZERO:
			pw.println("END BY JZERO, if " + var.name + " = ");
			pw.println("    0 : goto " + next[0] + "; 1 : goto " + next[1]);
			break;
		case BY_RETURN:
			if (var != null) {
				pw.println("END BY RETURN, result = " + var.name);
			} else {
				pw.println("END BY RETURN, void result");
			}
			break;
		}
	}

	public void printLivenessTo(PrintWriter pw) {
		pw.println("BASIC BLOCK " + bbNum + " : ");
		pw.println("  Def     = " + toString(def));
		pw.println("  liveUse = " + toString(liveUse));
		pw.println("  liveIn  = " + toString(liveIn));
		pw.println("  liveOut = " + toString(liveOut));

		for (Tac t = tacChain; t != null&&t.liveOut!=null; t = t.next) {
			pw.println("    " + t + " " + toString(t.liveOut));
		}

		switch (endKind) {
		case BY_JUMP:
			pw.println("END BY JUMP, goto " + next[0]);
			break;
		case BY_JZERO:
			pw.println("END BY JZERO, if " + var.name + " = ");
			pw.println("    0 : goto " + next[0] + "; 1 : goto " + next[1]);
			break;
		case BY_RETURN:
			if (var != null) {
				pw.println("END BY RETURN, result = " + var.name);
			} else {
				pw.println("END BY RETURN, void result");
			}
			break;
		}
	}

	public String toString(Set<Temp> set) {
		StringBuilder sb = new StringBuilder("[ ");
		for (Temp t : set) {
			if(t!=null)
				sb.append(t.name + " ");
		}
		sb.append(']');
		return sb.toString();
	}

	public void insertBefore(Tac insert, Tac base) {
		if (base == tacChain) {
			tacChain = insert;
		} else {
			base.prev.next = insert;
		}
		insert.prev = base.prev;
		base.prev = insert;
		insert.next = base;
	}

	public void insertAfter(Tac insert, Tac base) {
		if (tacChain == null) {
			tacChain = insert;
			insert.next = null;
			return;
		}
		if (base.next != null) {
			base.next.prev = insert;
		}
		insert.prev = base;
		insert.next = base.next;
		base.next = insert;
	}

	public void appendAsm(Asm asm) {
		asms.add(asm);
	}
}
