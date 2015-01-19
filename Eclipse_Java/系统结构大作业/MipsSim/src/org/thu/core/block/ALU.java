package org.thu.core.block;

import org.thu.core.MipsRun;


public class ALU extends BasicBlock {
	public int data1;
	public int data2;
	public int aluControl;

	public int aluRes;

	public boolean immSrc;
	@Override
	public void update(long clock) {		
		int res = -1;
		
		long d1 = data1,d2 = data2;
		int signedImm = ((data2 & 0xFFFF)<<16)>>16;
		int unsignedImm = ((data2 & 0xFFFF)<<16)>>>16;
		
		switch (aluControl) {
		case ALUControl.SLL:
			res = data1 << (data2 & 0x1f);
			break;
		case ALUControl.SRL:
			res = data1 >>> (data2 & 0x1f);
			break;
		case ALUControl.SRA:
			res = data1 >> (data2 & 0x1f);
			break;
		case ALUControl.ADD:
			if(immSrc)
				res = data1 + signedImm;
			else
				res = data1 + data2;
			break;
		case ALUControl.ADDU:
			if(immSrc)
				res = data1 + unsignedImm;
			else
				res = data1 + data2;
			break;
		case ALUControl.SUB:
			if(immSrc)
				res = data1 - signedImm;
			else
				res = data1 - data2;
			break;
		case ALUControl.SUBU:
			if(immSrc)
				res = data1 - unsignedImm;
			else
				res = data1 - data2;
			break;
		case ALUControl.AND:
			res = data1 & (data2 & 0xffff);
			break;
		case ALUControl.OR:
			res = data1 | (data2 & 0xffff);
			break;
		case ALUControl.XOR:
			res = data1 ^ (data2 & 0xffff);
			break;
		case ALUControl.SEQ:
			res = (data1 == data2)?1:0;
			break;
		case ALUControl.SNE:
			res = (data1 != data2)?1:0;
			break;
		case ALUControl.SLT:
			res = (data1 < data2)?1:0;
			break;
		case ALUControl.SGT:
			res = (data1 > data2)?1:0;
			break;
		case ALUControl.SLE:
			res = (data1 <= data2)?1:0;
			break;
		case ALUControl.MULT:
			res = data1 * data2;
			break;
		case ALUControl.DIV:
			res = data1 / data2;
			break;
		case ALUControl.MULTU:
			res = (int)(d1*d2);
			break;
		case ALUControl.DIVU:
			res = (int)(d1/d2);
			break;
		default:
			if(MipsRun.CODE_DEBUG && aluControl > 0){
				System.err.println("没有指定的ALU操作:"+aluControl);
			}
			break;
		}
		aluRes = res;
	}

	public void dump(){
		System.out.println("ALU\t操作数1：0x"+Integer.toHexString(data1)+",操作数2：0x"+Integer.toHexString(data2)+",操作码："+aluControl+",结果：0x"+Integer.toHexString(aluRes));
	}
}
