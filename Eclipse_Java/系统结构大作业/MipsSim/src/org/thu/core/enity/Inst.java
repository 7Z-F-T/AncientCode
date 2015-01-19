package org.thu.core.enity;

import org.thu.core.SimUtils;

public final class Inst {
	public final int opcode;
	public final int func;
	public final int rs;
	public final int rt;
	public final int rd;
	public final int imm;
	public final int jImm;
	public final int sa;

	public final int code;
	
	public Inst(int ir) {
		code = ir;
		opcode = SimUtils.getBits(ir, 31, 26);
		func = SimUtils.getBits(ir, 5, 0);
		rs = SimUtils.getBits(ir, 25, 21);
		rt = SimUtils.getBits(ir, 20, 16);
		rd = SimUtils.getBits(ir, 15, 11);
		imm = SimUtils.getBits(ir, 15, 0);
		jImm = SimUtils.getBits(ir, 25, 0);
		sa = SimUtils.getBits(ir, 10, 6);

	}
}