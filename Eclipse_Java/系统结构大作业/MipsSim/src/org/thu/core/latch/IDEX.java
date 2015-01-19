package org.thu.core.latch;

import org.thu.core.MipsSimEnv;
import org.thu.core.enity.Instances;

public class IDEX extends BasicLatch {
	public Cell in = new Cell();
	public Cell out = new Cell();

	public void update() {
		if(MipsSimEnv.stall.exKeep)
			return;
		if(MipsSimEnv.stall.idFlush || MipsSimEnv.control.ID_Flush){
			out.clear();
			return;
		}
		
		out.ip = in.ip;
		out.ir = in.ir;
		out.reg1Data = in.reg1Data;
		out.reg2Data = in.reg2Data;
		out.imm = in.imm;
		out.instRs = in.instRs;
		out.instRt = in.instRt;
		out.instRd = in.instRd;

		out.ex.copy(in.ex);
		out.mem.copy(in.mem);
		out.wb.copy(in.wb);
	}

	public class Cell {
		// ID或EX的控制线
		public ControlEX ex = new ControlEX();

		// MEM的控制线
		public ControlMEM mem = new ControlMEM();

		// WB的控制线
		public ControlWB wb = new ControlWB();

		public int ip;
		public int ir;
		public int reg1Data;
		public int reg2Data;
		public short imm;// 16位无符号整型
		public int instRs;
		public int instRt;
		public int instRd;
		
		public void clear(){
			ex.clear();
			mem.clear();
			wb.clear();
			
			ip = -4;
			ir = Instances.NOP.code;
			reg1Data = 0;
			reg2Data = 0;
			imm = 0;
			instRs = 0;
			instRt = 0;
			instRd = 0;
		}
	}

	public void clear(){
		in.clear();
		out.clear();
	}
	
	public void dump() {
		System.out.print("IDEX(in)\tir:0x" + Integer.toHexString(in.ir) + ",ip:0x"
				+ Integer.toHexString(in.ip) + ",reg1Data:" + in.reg1Data
				+ ",reg2Data:" + in.reg2Data
				+",imm:"+in.imm
				+",instRs:"+in.instRs
				+ ",instRt:"+in.instRt
				+",instRd:"+in.instRd);
		System.out.print(",");
		in.ex.dump();
		System.out.print(",");
		in.mem.dump();
		System.out.print(",");
		in.wb.dump();
		System.out.println();
		
		System.out.print("IDEX(out)\tir:0x" + Integer.toHexString(out.ir)
				+ ",ip:0x" + Integer.toHexString(out.ip) + ",reg1Data:"
				+ out.reg1Data + ",reg2Data:" + out.reg2Data
				+",imm:"+out.imm
				+",instRs:"+out.instRs
				+ ",instRt:"+out.instRt
				+",instRd:"+out.instRd);
		System.out.print(",");
		out.ex.dump();
		System.out.print(",");
		out.mem.dump();
		System.out.print(",");
		out.wb.dump();
		System.out.println();
	}
}
