package org.thu.core.block;

import org.thu.core.enity.Memory;


/**
 * �ڴ����ģ�飬֧��ʵʱ�ض�д���ܡ�
 * ��������Ϊֻ��״̬��
 * @author Thihy
 *
 */
public class MemUnit {
	public Memory mem;
	
	public boolean readOnly = false;
	
	public int readNeedCycles = 1;
	public int writeNeedCycles = 1;
	
	public int readCycles = -1;//�Ѿ���ȡ������
	public int writeCycles = -1;//�Ѿ�д�������
	
	public int readAddr = -1;
	public int writeAddr = -1;
	public int writeData = 0;
	
	public MemUnit(Memory mem,int readNeedCycles,int writeNeedCycles){
		this(mem,false,readNeedCycles,writeNeedCycles);
	}

	public MemUnit(Memory mem,boolean readOnly,int readNeedCycles,int writeNeedCycles){
		this.mem = mem;
		this.readOnly = readOnly;
		
		this.readNeedCycles = readNeedCycles;
		this.writeNeedCycles = writeNeedCycles;
		reset();
	}
	
	public void commit(){
		reset();
	}
	
	/**
	 * ���ı�ֻ���ԡ�
	 */
	private void reset(){
		this.readCycles = -1;
		this.writeCycles = -1;
		
		this.readAddr = -1;
		this.writeAddr = -1;
		this.writeData = 0;
	}
	
	public void clear(){
		reset();
		mem.clear();
	}
	
	/**
	 * �Ƿ��ܹ����ʣ�����ڴ����ڶ�����������дʱ�������Է��ʡ�
	 * @return 
	 */
	public boolean isReady(){
		return this.readCycles < 0 && this.writeCycles < 0;
	}
	
	public boolean isReadReady(){
		return this.readCycles<0;
	}
	
	public boolean isWriteReady(){
		return this.writeCycles <0;
	}
	
	/**
	 * ��ʼ��ȡaddr�����ݡ�
	 * @return ������Է��ʣ���Ż�true�����򷵻�false
	 */
	public boolean read(int addr){
		if(!isReady())
			return false;
		this.readAddr = addr;	
		this.readCycles = 0;
		return true;
	}
	
	/**
	 * �Ƿ�ɹ���ɶ�ȡ����
	 * @return
	 */
	public boolean isReadFinish(){
		return this.readCycles >= readNeedCycles;
	}
	
	/**
	 * ���ض�ȡ�������ݡ�
	 * ������ݻ�û�гɹ���ȡ������Ҫһ����ʱ�䣩���򷵻�-1��
	 * ��Ҫע�⣺�������ݺ󣬳�������Reset���Ա������һ��������
	 */
	public Integer getReadData(){
		if(!isReadFinish())
			return null;
		Integer res = mem.readWord(this.readAddr);
		reset();
		return res;
	}
	
	/**
	 * ��ʼ��dataд��addr
	 * @return ������Է��ʣ���Ż�true�����򷵻�false
	 */
	public boolean write(int addr,int writeData){
		if(!isReady() || readOnly)
			return false;
		
		this.writeAddr = addr;
		this.writeData = writeData;
		this.writeCycles = 0;
		return true;
	}
	
	/**
	 * �Ƿ�ɹ����д�����
	 * @return
	 */
	public boolean isWriteFinish(){
		if(readOnly)
			return false;
		return this.writeCycles >= writeNeedCycles;
	}
	
	public boolean commitWrite(){
		if(!isWriteFinish())
			return false;
		this.mem.writeWord(this.writeAddr, this.writeData);
		reset();
		return true;
	}
	
	/**
	 * ÿ�����ڽ���֮ǰ����Ҫ���ô˳���һ�Ρ�
	 */
	public void update(){
		if(this.readCycles >=0)
			++ this.readCycles;
		if(this.writeCycles>=0)
			++ this.writeCycles;
		commitWrite();
	}
	
	public void dump(String name){
		System.out.print(name+"\t");
		if(isReady()){
			System.out.print("����");
		}
		else{
			if(this.readCycles>=0){
				System.out.print("���ڶ�"+readCycles+"/"+readNeedCycles);
			}
			if(this.writeCycles>=0){
				System.out.print("����д"+writeCycles+"/"+writeNeedCycles);
			}
		}
		System.out.println();
	}
}
