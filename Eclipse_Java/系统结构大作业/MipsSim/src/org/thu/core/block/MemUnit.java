package org.thu.core.block;

import org.thu.core.enity.Memory;


/**
 * 内存管理模块，支持实时地读写功能。
 * 可以设置为只读状态。
 * @author Thihy
 *
 */
public class MemUnit {
	public Memory mem;
	
	public boolean readOnly = false;
	
	public int readNeedCycles = 1;
	public int writeNeedCycles = 1;
	
	public int readCycles = -1;//已经读取的周期
	public int writeCycles = -1;//已经写入的周期
	
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
	 * 不改变只读性。
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
	 * 是否能够访问，如果内存正在读，或者正在写时，不可以访问。
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
	 * 开始读取addr的内容。
	 * @return 如果可以访问，则放回true，否则返回false
	 */
	public boolean read(int addr){
		if(!isReady())
			return false;
		this.readAddr = addr;	
		this.readCycles = 0;
		return true;
	}
	
	/**
	 * 是否成功完成读取操作
	 * @return
	 */
	public boolean isReadFinish(){
		return this.readCycles >= readNeedCycles;
	}
	
	/**
	 * 返回读取到的数据。
	 * 如果数据还没有成功获取到（需要一定的时间），则返回-1。
	 * 需要注意：返回数据后，程序将立即Reset，以便接受下一个操作。
	 */
	public Integer getReadData(){
		if(!isReadFinish())
			return null;
		Integer res = mem.readWord(this.readAddr);
		reset();
		return res;
	}
	
	/**
	 * 开始将data写入addr
	 * @return 如果可以访问，则放回true，否则返回false
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
	 * 是否成功完成写入操作
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
	 * 每个周期结束之前都需要调用此程序一次。
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
			System.out.print("空闲");
		}
		else{
			if(this.readCycles>=0){
				System.out.print("正在读"+readCycles+"/"+readNeedCycles);
			}
			if(this.writeCycles>=0){
				System.out.print("正在写"+writeCycles+"/"+writeNeedCycles);
			}
		}
		System.out.println();
	}
}
