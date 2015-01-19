package org.thu.core.latch;

import org.thu.core.block.ALUControl;

/**
 * ID或EX的控制线
 */
public class ControlEX{
	public boolean regDst;// 写入寄存器的目标号在rt中的位置，0-（20-16）位，1-（15-11）位
	public int aluOp;
	public boolean aluSrc;//控制ALU的第2个操作数的来源，0-寄存器，1-立即数
	
	public ControlEX(){
		clear();
	}
	
	public void copy(ControlEX controlIDEX){
		this.regDst = controlIDEX.regDst;
		this.aluOp = controlIDEX.aluOp;
		this.aluSrc = controlIDEX.aluSrc;
	}

	public void clear() {
		regDst = false;
		aluOp = ALUControl.NONE;
		aluSrc = false;
	}
	
	public void dump(){
		System.out.print("regDst:"+regDst+",aluOP:"+aluOp+",aluSrc:"+aluSrc);
	}
}
