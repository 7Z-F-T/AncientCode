package org.thu.core.latch;

/**
 * WB的控制线
 */
public class ControlWB {
	public boolean regWrite;//是否需要写入寄存器
	public boolean memToReg;//true - 由存储器提供写数据的值，false - 由ALU提供写数据的值
	public boolean halt;//是否停机
	
	public ControlWB(){
		clear();
	}
	
	public void copy(ControlWB controlWB){
		this.regWrite = controlWB.regWrite;
		this.memToReg = controlWB.memToReg;
		this.halt = controlWB.halt;
	}

	public void clear() {
		this.regWrite = false;
		this.memToReg = false;
		this.halt =false;
	}
	
	public void dump(){
		System.out.print("regWrite:"+regWrite+",memToReg:"+memToReg+",halt:"+halt);
	}
}
