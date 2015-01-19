package org.thu.core.latch;

import org.thu.core.MipsSimEnv;
import org.thu.core.enity.Instances;


public class IFID extends BasicLatch{
	public Cell in = new Cell();
	public Cell out = new Cell();
	
	public void update(){
		if(MipsSimEnv.stall.idKeep)
			return;
		if(MipsSimEnv.stall.ifFlush || MipsSimEnv.control.IF_Flush){
			out.clear();
			return;
		}
		
		out.ip = in.ip;
		out.ir = in.ir;
	}
	
	public void clear(){
		in.clear();
		out.clear();
	}
	
	public class Cell{
		public int ir;
		public int ip;
		
		public void clear(){
			ir = Instances.NOP.code;
			ip = -4;
		}
	}

	public void dump() {
		System.out.println("IFID(in)\tir:0x"+Integer.toHexString(in.ir)+",ip:0x"+Integer.toHexString(in.ip));
		System.out.println("IFID(out)\tir:0x"+Integer.toHexString(out.ir)+",ip:0x"+Integer.toHexString(out.ip));
	}
}
