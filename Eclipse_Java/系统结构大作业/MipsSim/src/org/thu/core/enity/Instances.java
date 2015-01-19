package org.thu.core.enity;


public final class Instances {
	
	public final static Inst 
	// 数据传输指令
		LW = new Inst(0x8C000000),
		SW = new Inst(0xAC000000),
	//控制传输指令
		J = new Inst(0x8000000),
		BEQZ = new Inst(0x10000000),
		BNEZ = new Inst(0x14000000),
	//ALU，寄存器，立即数指令
		ADDI = new Inst(0x20000000),
		ADDUI = new Inst(0x24000000),
		SUBI = new Inst(0x28000000),
		SUBUI = new Inst(0x2C000000),
		ANDI = new Inst(0x30000000),
		ORI = new Inst(0x34000000),
		XORI = new Inst(0x38000000),
		SLLI = new Inst(0x50000000),
		SRLI = new Inst(0x58000000),
		SRAI = new Inst(0x5C000000),
		SEQI = new Inst(0x60000000),
		SNEI = new Inst(0x64000000),
		SLTI = new Inst(0x68000000),
		SGTI = new Inst(0x6C000000),
		SLEI = new Inst(0x70000000),
	//ALU，寄存器，寄存器指令
		SLL = new Inst(0x4),
		SRL = new Inst(0x6),
		SRA = new Inst(0x7),
		ADD = new Inst(0x20),
		ADDU = new Inst(0x21),
		SUB = new Inst(0x22),
		SUBU = new Inst(0x23),
		AND = new Inst(0x24),
		OR = new Inst(0x25),
		XOR = new Inst(0x26),
		SEQ = new Inst(0x28),
		SNE = new Inst(0x29),
		SLT = new Inst(0x2A),
		SGT = new Inst(0x2B),
		SLE = new Inst(0x2C),
		MULT = new Inst(0x400000E),
		DIV = new Inst(0x400000F),
		MULTU = new Inst(0x4000016),
		DIVU = new Inst(0x4000017),
	//HALT指令
		HALT = new Inst(0xFC000000),
		NOP = new Inst(0x0);
	
	public static final Inst []Arrays = {
		// 数据传输指令
		LW,
		SW,
	//控制传输指令
		J,
		BEQZ,
		BNEZ,
	//ALU，寄存器，立即数指令
		ADDI,
		ADDUI,
		SUBI,
		SUBUI,
		ANDI,
		ORI,
		XORI,
		SLLI,
		SRLI,
		SRAI,
		SEQI,
		SNEI,
		SLTI,
		SGTI,
		SLEI,
	//ALU，寄存器，寄存器指令
		SLL,
		SRL,
		SRA,
		ADD,
		ADDU,
		SUB,
		SUBU,
		AND,
		OR,
		XOR,
		SEQ,
		SNE,
		SLT,
		SGT,
		SLE,
		MULT,
		DIV,
		MULTU,
		DIVU,
	//HALT指令
		HALT,
		NOP
	};
}
