package org.thu.core.latch;

/**
 * WB�Ŀ�����
 */
public class ControlWB {
	public boolean regWrite;//�Ƿ���Ҫд��Ĵ���
	public boolean memToReg;//true - �ɴ洢���ṩд���ݵ�ֵ��false - ��ALU�ṩд���ݵ�ֵ
	public boolean halt;//�Ƿ�ͣ��
	
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
