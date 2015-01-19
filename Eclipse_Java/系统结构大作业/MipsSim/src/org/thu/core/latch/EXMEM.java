package org.thu.core.latch;

import org.thu.core.MipsSimEnv;
import org.thu.core.enity.Instances;


public class EXMEM extends BasicLatch{

	public Cell in = new Cell();
	public Cell out = new Cell();
	

	public void update(){
		if(MipsSimEnv.stall.memKeep)
			return;
		if(MipsSimEnv.stall.exFlush || MipsSimEnv.control.EX_Flush){
			out.clear();
			return;
		}
		
		out.aluRes = in.aluRes;
		out.reg2Data = in.reg2Data;
		out.instRw = in.instRw;
		out.ip = in.ip;
		out.ir = in.ir;
		
		out.mem.copy(in.mem);
		out.wb.copy(in.wb);
	}
	
	public class Cell{
		// MEM的控制线
		public ControlMEM mem = new ControlMEM();
		
		// WB的控制线
		public ControlWB wb = new ControlWB();
		
		public int aluRes;
		public int reg2Data;
		public int instRw;
		public int ip;
		public int ir;
		
		public void clear(){
			mem.clear();
			wb.clear();
			aluRes = 0;
			reg2Data = 0;
			instRw = 0;
			ip = -4;
			ir = Instances.NOP.code;
		}
	}
	
	public void clear(){
		in.clear();
		out.clear();
	}

	public void dump() {
		System.out.print("EXMEM(in)\tir:0x"+Integer.toHexString(in.ir)+
				",ip:0x"+Integer.toHexString(in.ip)+
				",aluRes:" + in.aluRes + 
				",reg2Data:" + in.reg2Data
				+",instRw:"+in.instRw);
		System.out.print(",");
		in.mem.dump();
		System.out.print(",");
		in.wb.dump();
		System.out.println();
		
		System.out.print("EXMEM(out)\tir:0x"+Integer.toHexString(out.ir)
				+",ip:0x"+Integer.toHexString(out.ip) +
				",aluRes:" + out.aluRes + 
				",reg2Data:" + out.reg2Data
				+",instRw:"+out.instRw);
		
		System.out.print(",");
		out.mem.dump();
		System.out.print(",");
		out.wb.dump();
		System.out.println();
	}
}
