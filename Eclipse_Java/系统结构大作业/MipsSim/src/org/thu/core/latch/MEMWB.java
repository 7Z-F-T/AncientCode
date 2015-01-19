package org.thu.core.latch;

import org.thu.core.MipsSimEnv;
import org.thu.core.enity.Instances;


public class MEMWB extends BasicLatch{

	public Cell in = new Cell();
	public Cell out = new Cell();
	

	public void update(){
		if(MipsSimEnv.stall.memFlush){
			out.clear();
			return;
		}
		out.memData = in.memData;
		out.aluRes = in.aluRes;
		out.instRw = in.instRw;
		out.ip = in.ip;
		out.ir = in.ir;
		out.wb.copy(in.wb);
	}
	
	public class Cell{

		// WBµÄ¿ØÖÆÏß
		public ControlWB wb = new ControlWB();
		
		public int memData;
		public int aluRes;
		public int instRw;
		public int ip;
		public int ir;
		
		public void clear(){
			wb.clear();
			memData = 0;
			aluRes = 0;
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
		System.out.print("MEMWB(in)\tir:0x"+Integer.toHexString(in.ir)+
				",ip:0x"+Integer.toHexString(in.ip)+
				",aluRes:" + in.aluRes + 
				",memData:" + in.memData+
				",instRw:"+in.instRw);
		
		System.out.print(",");
		in.wb.dump();
		System.out.println();
		
		System.out.print("MEMWB(out)\tir:0x"+Integer.toHexString(out.ir)
				+",ip:0x"+Integer.toHexString(out.ip) +
				",aluRes:" + out.aluRes + 
				",memData:" + out.memData+
				",instRw:"+out.instRw);
		System.out.print(",");
		out.wb.dump();
		System.out.println();
	}
}
