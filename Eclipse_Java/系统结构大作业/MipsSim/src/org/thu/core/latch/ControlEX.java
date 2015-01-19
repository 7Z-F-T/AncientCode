package org.thu.core.latch;

import org.thu.core.block.ALUControl;

/**
 * ID��EX�Ŀ�����
 */
public class ControlEX{
	public boolean regDst;// д��Ĵ�����Ŀ�����rt�е�λ�ã�0-��20-16��λ��1-��15-11��λ
	public int aluOp;
	public boolean aluSrc;//����ALU�ĵ�2������������Դ��0-�Ĵ�����1-������
	
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
