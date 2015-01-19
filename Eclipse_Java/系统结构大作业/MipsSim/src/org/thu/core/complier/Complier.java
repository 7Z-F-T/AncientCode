package org.thu.core.complier;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.thu.ui.Mips_sentence;
import org.thu.ui.Mipsframe;

public class Complier {
	public LinkedList<String> senList = new LinkedList<String>();
	public LinkedList<String> noteList = new LinkedList<String>();
	public Map<Integer,Integer> origline= new HashMap<Integer,Integer>(1024);
	public int sencount = 0;
	public HashMap<String, Integer> tabList = new HashMap();
	public String labels[] = new String[256];
	public Integer errLine = -1;
	Mipsframe mipsframe;

	public Complier(Mipsframe mipsframe) {
		this.mipsframe = mipsframe;
	}

	public int compiling() {
		this.sencount = 0;
		this.senList.clear();
		this.noteList.clear();
		this.tabList.clear();
		this.errLine = -1;
		String s,note = "";
		int count = 0;
		for (int i = 0; i < Mipsframe.mips_file.linenum; i++) {
			s = Mipsframe.mips_file.getSentence(i);
			note = "";
			if (s.endsWith("\r")) {
				s = s.substring(0, s.length() - 1).trim();
			}
			int pos;
			if (s.length() == 0)
				continue;
			pos = s.indexOf(';');
			if (pos >= 0) {
				if (pos > 0){
					note = s.substring(pos+1).trim();
					s = s.substring(0, pos).trim();
				}
				else
					continue;
			}

			pos = s.indexOf(':');
			if (pos >= 0) {
				if (pos > 0) {
					String tab = s.substring(0, pos).trim();
					if (tab.indexOf(' ') != -1 || tab.indexOf('\t') != -1) {
						errLine = i;
						return 8;
					}
					s = s.substring(pos + 1).trim();
					if (tabList.containsKey(tab)) {
						errLine = i;
						return 1;
					}
					tabList.put(tab.toUpperCase(), sencount);
					labels[sencount] = tab.toUpperCase();
					if (s.isEmpty())
						continue;
				} else if (pos == 0) {
					errLine = i;
					return 5;
				}
			}

			int firstpos = 0;
			char firstopp = s.charAt(firstpos);
			while (firstopp == ' ' || firstopp == '\t') {
				firstpos++;
				if (firstpos == s.length())
					break;
				firstopp = s.charAt(firstpos);
			}
			s = s.substring(firstpos).trim();

			int lastpos = s.length() - 1;
			if (lastpos >= 0) {
				char lastopp = s.charAt(lastpos);
				while (lastopp == ' ' || lastopp == '\t') {
					lastpos--;
					if (lastpos < 0)
						break;
					lastopp = s.charAt(lastpos);
				}
				s = s.substring(0, lastpos + 1).trim();
			}

			if (!s.isEmpty()) {
				senList.add(s);
				noteList.add(note);
				origline.put(sencount,i);
				sencount++;
			}
		}

		if(sencount == 0 || !senList.getLast().trim().toUpperCase().startsWith("HALT")){
			senList.add("HALT");
			noteList.add("Í£»úÖ¸Áî");
			sencount ++;
		}
		
		for (int i = 0; i < sencount; i++) {
			int instruction = 0;
			String formalSen = new String();
			
			String opp = new String();
			String ins = new String();
			String rd, rs, imm, rt;

			s = (String) senList.get(i);
			if(!s.contains(" "))
				s+=" ";
			int p1 = s.indexOf(' '), p2 = s.indexOf('\t'), p;
			if (p1 < 0) {
				p = p2;
			} else if (p2 < 0) {
				p = p1;
			} else {
				p = Math.min(p1, p2);
			}
			if (p <= 0) {
				errLine = origline.get(i);
				return 10;
			}
			opp = s.substring(0, p).trim();
			ins = s.substring(p + 1).trim();
			opp = opp.toUpperCase();
			ins = ins.toUpperCase();

			if (opp.equals("HALT")){
				instruction = IRCode.HALT<<26;
				formalSen = opp;
			}
			else if(opp.equals("NOP")){
				instruction = 0;
				formalSen = opp;
			}
			else if (opp.equals("J")) {
				int store = 0;
				store = IRCode.J << 26;
				if(ins.equals("")){
					errLine = origline.get(i);
					return 4;
				}
				int off = 0;
				if((ins.charAt(0) >= '0' && ins.charAt(0) <= '9') ||ins.charAt(0) == '+' || ins.charAt(0) == '-'){
					try{
						int num = Integer.decode(ins);
						off = num;
						store = store | ( (num*4) & 0x3FFFFFF);
						instruction = store;
						formalSen = opp + " " + getIntegerString(num & 0xFFFFFF);
					}catch(Exception e){
						errLine = origline.get(i);
						return 7;
					}
				}else{
					if (tabList.containsKey(ins)) {
						int num = tabList.get(ins);
						
						store = store | (((num - i - 1) * 4) & 0x3FFFFFF);
						off = num - i - 1;
						instruction = store;
						formalSen = opp + " " + getIntegerString((num - i - 1) & 0xFFFFFF);
					} else {
						errLine = origline.get(i);
						return 4;
					}
				}
				if(i + 1 + off >= sencount || i + 1 + off<0){
					errLine = origline.get(i);
					return 12;
				}
			}

			else if (opp.equals("BEQZ") || opp.equals("BNEZ")) {
				int store = 0;
				if (opp.equals("BEQZ"))
					store = IRCode.BEQZ << 26;
				else if (opp.equals("BNEZ"))
					store = IRCode.BNEZ << 26;

				int posdo;
				posdo = ins.indexOf(',');
				if (posdo <= 0) {
					errLine = origline.get(i);
					return 11;
				}

				rd = ins.substring(0, posdo).trim();
				ins = ins.substring(posdo + 1).trim();

				int res = anaReg(rd, i);
				if (res < 0)
					return res * (-1);
				else
					store = store | res << 21;
				
				if(ins.equals("")){
					errLine =origline.get(i);
					return 4;
				}
				
				int off = 0;
				
				if((ins.charAt(0) >= '0' && ins.charAt(0) <= '9') ||ins.charAt(0) == '+' || ins.charAt(0) == '-'){
					try{
						int num = Integer.decode(ins);
						off = num;
						store = store |  ( (num*4) & 0xFFFF);
						instruction = store;
						formalSen = opp + " " + getIntegerString(num & 0xFFFF);
					}catch(Exception e){
						errLine = origline.get(i);
						return 7;
					}
				}else{
					if (tabList.containsKey(ins)) {
						int num = tabList.get(ins);
						off = num - i -1;
						store = store | (((num - i - 1) * 4) & 0x1FFFFF);
						instruction = store;
						formalSen = opp + " " + rd + " , " + getIntegerString((num - i - 1) & 0xFFFF);
					} else {
						errLine = origline.get(i);
						return 4;
					}
				}
				

				if(i + 1 + off >= sencount || i + 1 + off<0){
					errLine = origline.get(i);
					return 12;
				}
			}

			else if ((opp.equals("ADDI")) || (opp.equals("ADDUI"))
					|| (opp.equals("SUBI")) || (opp.equals("SUBUI"))
					|| (opp.equals("ANDI")) || (opp.equals("ORI"))
					|| (opp.equals("XORI")) || (opp.equals("SLLI"))
					|| (opp.equals("SRLI")) || (opp.equals("SLEI"))
					|| (opp.equals("SRAI")) || (opp.equals("SEQI"))
					|| (opp.equals("SNEI")) || (opp.equals("SLTI"))
					|| (opp.equals("SGTI"))) {
				int store = 0;
				if (opp.equals("ADDI"))
					store = IRCode.ADDI << 26;
				else if (opp.equals("ADDUI"))
					store = IRCode.ADDUI << 26;
				else if (opp.equals("SUBI"))
					store = IRCode.SUBI << 26;
				else if (opp.equals("SUBUI"))
					store = IRCode.SUBUI << 26;
				else if (opp.equals("ANDI"))
					store = IRCode.ANDI << 26;
				else if (opp.equals("ORI"))
					store = IRCode.ORI << 26;
				else if (opp.equals("XORI"))
					store = IRCode.XORI << 26;
				else if (opp.equals("SLLI"))
					store = IRCode.SLLI << 26;
				else if (opp.equals("SRLI"))
					store = IRCode.SRLI << 26;
				else if (opp.equals("SLEI"))
					store = IRCode.SLEI << 26;
				else if (opp.equals("SRAI"))
					store = IRCode.SRAI << 26;
				else if (opp.equals("SEQI"))
					store = IRCode.SEQI << 26;
				else if (opp.equals("SNEI"))
					store = IRCode.SNEI << 26;
				else if (opp.equals("SLTI"))
					store = IRCode.SLTI << 26;
				else if (opp.equals("SGTI"))
					store = IRCode.SGTI << 26;

				int posdo;
				posdo = ins.indexOf(',');
				if (posdo <= 0) {
					errLine = origline.get(i);
					return 11;
				}

				rd = ins.substring(0, posdo).trim();
				ins = ins.substring(posdo + 1).trim();

				int res = anaReg(rd, i);
				if (res < 0)
					return res * (-1);
				else
					store = store | res << 16;

				posdo = ins.indexOf(',');
				if (posdo <= 0) {
					errLine = origline.get(i);
					return 11;
				}

				rs = ins.substring(0, posdo).trim();
				ins = ins.substring(posdo + 1).trim();

				res = anaReg(rs, i);
				if (res < 0)
					return res * (-1);
				else
					store = store | res << 21;
				

				imm = ins;
				int immnum;
				try {
					immnum = Integer.decode(imm);
				} catch (Exception ex) {
					errLine = origline.get(i);
					return 7;
				}
				int highImm = (immnum & 0xffff0000) >> 16;
				if ( highImm != 0 && highImm != -1) {
					errLine = origline.get(i);
					return 6;
				}

				store = store | (immnum & 0xFFFF);
				instruction = store;
				formalSen = opp + " " + rd + " , " + rs + " , " + getIntegerString(immnum & 0xFFFF);
			}

			else if (opp.equals("LW") || (opp.equals("SW"))) {
				int store = 0;
				//Ex: lw rt,imm(rs)
				if (opp.equals("LW")) {
					store = IRCode.LW << 26;

					int posdo;
					posdo = ins.indexOf(',');
					if (posdo <= 0) {
						errLine = origline.get(i);
						return 11;
					}
					rd = ins.substring(0, posdo).trim();
					ins = ins.substring(posdo + 1).trim();
					int res = anaReg(rd, i);
					if (res < 0)
						return res * (-1);
					else
						store = store | res << 16;

					posdo = ins.indexOf('(');
					if (posdo <= 0 || ins.charAt(ins.length() - 1) != ')') {
						errLine = origline.get(i);
						return 11;
					}

					imm = ins.substring(0, posdo).trim();
					rs = ins.substring(posdo + 1, ins.length() - 1).trim();
					int immnum;
					try {
						immnum = Integer.decode(imm);
					} catch (Exception ex) {
						errLine = origline.get(i);
						return 7;
					}
					int highImm = (immnum & 0xffff0000) >> 16;
					if ( highImm != 0 && highImm != -1) {
						errLine = origline.get(i);
						return 6;
					}

					store = store | (immnum & 0xFFFF);

					res = anaReg(rs, i);
					if (res < 0) {
						return res * (-1);
					} else
						store = store | res << 21;
					formalSen = opp + " " + rd + " , " + getIntegerString((immnum & 0xFFFF)) + " ( " + rs + " ) ";
				}

				else if (opp.equals("SW")) {
					store = IRCode.SW << 26;
					int posdo;
					posdo = ins.indexOf(',');
					if (posdo <= 0) {
						errLine = origline.get(i);
						return 11;
					}
					rd = ins.substring(posdo + 1).trim();
					ins = ins.substring(0, posdo).trim();
					int res = anaReg(rd, i);
					if (res < 0)
						return res * (-1);
					else
						store = store | res << 16;

					posdo = ins.indexOf('(');
					if (posdo <= 0 || ins.charAt(ins.length() - 1) != ')') {
						errLine = origline.get(i);
						return 11;
					}

					imm = ins.substring(0, posdo).trim();
					rs = ins.substring(posdo + 1, ins.length() - 1).trim();

					int immnum;
					try {
						immnum = Integer.decode(imm);
					} catch (Exception ex) {
						errLine = origline.get(i);
						return 7;
					}
					int highImm = (immnum & 0xffff0000) >> 16;
					if ( highImm != 0 && highImm != -1) {
						errLine = origline.get(i);
						return 6;
					}

					store = store | (immnum & 0xFFFF);

					res = anaReg(rs, i);
					if (res < 0)
						return res * (-1);
					else
						store = store | res << 21;

					formalSen = opp + " " + getIntegerString((immnum & 0xFFFF)) + " ( " + rs + " ) " + " , " + rd;
				}

				instruction = store;

			}

			else if ((opp.equals("MULT")) || (opp.equals("DIV"))
					|| (opp.equals("MULTU")) || (opp.equals("DIVU"))
					|| (opp.equals("ADD")) || (opp.equals("ADDU"))
					|| (opp.equals("SUB")) || (opp.equals("SUBU"))
					|| (opp.equals("AND")) || (opp.equals("OR"))
					|| (opp.equals("XOR")) || (opp.equals("SLL"))
					|| (opp.equals("SRL")) || (opp.equals("SLE"))
					|| (opp.equals("SRA")) || (opp.equals("SEQ"))
					|| (opp.equals("SNE")) || (opp.equals("SLT"))
					|| (opp.equals("SGT"))) {

				int store = 0;

				if (!opp.equals("MULT") && !opp.equals("DIV")
						&& !opp.equals("MULTU") && !opp.equals("DIVU")) {
					if (opp.equals("ADD"))
						store = (FuncCode.ADD & 0x3f);
					else if (opp.equals("ADDU"))
						store = (FuncCode.ADDU & 0x3f);
					else if (opp.equals("SUB"))
						store = (FuncCode.SUB & 0x3f);
					else if (opp.equals("SUBU"))
						store = (FuncCode.SUBU & 0x3f);
					else if (opp.equals("AND"))
						store = (FuncCode.AND & 0x3f);
					else if (opp.equals("OR"))
						store =( FuncCode.OR & 0x3f);
					else if (opp.equals("XOR"))
						store = (FuncCode.XOR & 0x3f);
					else if (opp.equals("SLL"))
						store = (FuncCode.SLL & 0x3f);
					else if (opp.equals("SRL"))
						store = (FuncCode.SRL & 0x3f);
					else if (opp.equals("SLE"))
						store = (FuncCode.SLE & 0x3f);
					else if (opp.equals("SRA"))
						store =( FuncCode.SRA & 0x3f);
					else if (opp.equals("SEQ"))
						store = (FuncCode.SEQ & 0x3f);
					else if (opp.equals("SNE"))
						store = (FuncCode.SNE & 0x3f);
					else if (opp.equals("SLT"))
						store = (FuncCode.SLT & 0x3f);
					else if (opp.equals("SGT"))
						store = (FuncCode.SGT & 0x3f);
				} else {
					store = 0x1 << 26;
					if (opp.equals("MULT"))
						store |= (FuncCode.MULT & 0x3f);
					else if (opp.equals("MULTU"))
						store |= (FuncCode.MULTU & 0x3f);
					else if (opp.equals("DIV"))
						store |= (FuncCode.DIV & 0x3f);
					else if (opp.equals("DIVU"))
						store |= (FuncCode.DIVU & 0x3f);
				}

				int posdo; // rd
				posdo = ins.indexOf(',');
				if (posdo <= 0) {
					errLine = origline.get(i);
					return 11;
				}

				rd = ins.substring(0, posdo).trim().trim();
				ins = ins.substring(posdo + 1).trim().trim();

				int res = anaReg(rd, i);
				if (res < 0)
					return res * (-1);
				else
					store = store | res << 11;

				// rs
				posdo = ins.indexOf(',');
				if (posdo <= 0) {
					errLine = origline.get(i);
					return 11;
				}

				rs = ins.substring(0, posdo).trim();
				ins = ins.substring(posdo + 1).trim();

				res = anaReg(rs, i);
				if (res < 0)
					return res * (-1);
				else
					store = store | res << 21;

				if (ins.isEmpty()) { // rt
					errLine = origline.get(i);
					return 11;
				}

				rt = ins;
				res = anaReg(rt, i);
				if (res < 0)
					return res * (-1);
				else
					store = store | res << 16;

				instruction = store;
				formalSen = opp + "  " + rd + " , " + rs + " , " + rt;
			}

			else {
				errLine = origline.get(i);
				return 2;
			}
			Mipsframe.file_in_binary.commands[i] = new Mips_sentence(
					instruction, formalSen);
			Mipsframe.file_in_binary.commands[i].setNote(noteList.get(i));
		}
		Mipsframe.file_in_binary.commandsize = sencount;
		return 0;
	}

	public int anaReg(String rr, int i) {

		if (rr.charAt(0) != 'R') {
			errLine = origline.get(i);
			return -3;
		}
		String num = rr.substring(1).trim();
		if (num.length() > 2 | num.isEmpty()) {
			errLine = origline.get(i);
			return -3;
		}
		int tempnum = 0;
		if (num.charAt(0) >= '0' && num.charAt(0) <= '9') {
			tempnum = num.charAt(0) - '0';
		} else {
			errLine = origline.get(i);
			return -3;
		}
		if (num.length() == 2) {
			if (num.charAt(1) >= '0' && num.charAt(1) <= '9') {
				tempnum = tempnum * 10 + num.charAt(1) - '0';
			} else {
				errLine = origline.get(i);
				return -3;
			}
		}
		if (tempnum >= 32) {
			errLine = origline.get(i);
			return -3;
		}
		return tempnum;
	}

	public String getIntegerString(Integer i){
		return "0x"+Integer.toHexString(i);
	}
}
