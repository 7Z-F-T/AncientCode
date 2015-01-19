package org.thu.core.latch;

/**
 * MEM的控制线
 */
public class ControlMEM {
	public boolean memRead; // 是否需要读取存储器
	public boolean memWrite;// 是否需要写入存储器
	
	public ControlMEM(){
		clear();
	}
	
	public void copy(ControlMEM controlMEM){
		this.memRead = controlMEM.memRead;
		this.memWrite = controlMEM.memWrite;
	}

	public void clear() {

		this.memRead = false;
		this.memWrite = false;
	}

	public void dump(){
		System.out.print("memRead:"+memRead+",memWrite:"+memWrite);
	}
}
