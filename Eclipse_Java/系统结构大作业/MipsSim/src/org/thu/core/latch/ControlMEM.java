package org.thu.core.latch;

/**
 * MEM�Ŀ�����
 */
public class ControlMEM {
	public boolean memRead; // �Ƿ���Ҫ��ȡ�洢��
	public boolean memWrite;// �Ƿ���Ҫд��洢��
	
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
