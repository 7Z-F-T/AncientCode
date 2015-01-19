package org.thu.core.block;

import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;
import org.thu.core.Option;
import org.thu.core.enity.Inst;
import org.thu.core.enity.Instances;


/**
 * 冒险单元。对于冒险的含义设为：只要需要在流水中插入NOP的(除了跳转指令)，就作为1次冒险。
 */
public class Stall extends BasicBlock{
	/**
	 *  是否保持PC的值
	 */
	public boolean pcKeep; 
	/**
	 * 在ID段插入气泡
	 */
	public boolean ifFlush;
	
	/**
	 * 在ex中插入气泡
	 */
	public boolean idFlush;
	/**
	 * 是否在mem中插入气泡
	 */
	public boolean exFlush;
	
	/**
	 * 是否在wb中插入气泡
	 */
	public boolean memFlush;
	
	/**
	 *  是否保持ID的值
	 */
	public boolean idKeep;
	/**
	 * 保持EX的值
	 */
	public boolean exKeep;
	/**
	 * 保持mem的值
	 */
	public boolean memKeep;
	
	//如果有冲突，则优先保持
	
	public boolean hasStall;
	
	private String des;//冒险描述
	
	/*
	 * 流水线存在如下几种冒险：<br/>
	 * 1. 读内存然后取。 <br/>
	 * 	  lw r2,20
	 *    add r1,r2,r3 
	 */
	@Override
	public void update(long clock) {
		// TODO Stall
		
		pcKeep = false;
		idKeep = false;
		exFlush = false;
		exKeep = false;
		idFlush = false;
		memKeep = false;
		memFlush = false;
		
		hasStall = false;
		des = "";
		if(!MipsSimEnv.instMemUnit.isReady()){ // 指令还没有读到，向EX段插入1个气泡，不向ID插气泡，是因为ID阶段可能是跳转指令
			idKeep = true;
			idFlush = true;
			pcKeep = true;
			
			if(MipsRun.CODE_DEBUG){
				des = (">>>冒险：指令读取等待。");
			}
			hasStall = true;
		}	
		
		if(!MipsSimEnv.dataMemUnit.isReady()){ // 如果数据内存还没有成功读取到数据或写入数据，则暂停。
			memFlush = true;
			memKeep = true;
			//可以很复杂哦
			pauseEx();
			if(MipsRun.CODE_DEBUG){
				des = (">>>冒险：数据读取等待。");
			}
			hasStall = true;
		}
		
		/*
		 * LW
		 * USE
		 */
		if(MipsSimEnv.idex.out.mem.memRead //id段上一条指令需要读取数据
				&& MipsSimEnv.exmem.in.instRw!=0
				&&(
						MipsSimEnv.exmem.in.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs
						|| MipsSimEnv.exmem.in.instRw == new Inst(MipsSimEnv.ifid.out.ir).rt )
						){
			idFlush = true;
			idKeep = true;
			pcKeep = true;//暂停PC
			if(MipsRun.CODE_DEBUG){
				des = (">>>冒险：LW-USE。");
			}
			hasStall = true;
		}
		
		
		/*
		 * 1. Write rx --ex
		 * 2. b rx  --id
		 * 在ex中插入气泡，保持id，使得指令1到达MEM中，然后通过转发来实现
		 */
		if(MipsSimEnv.idex.out.wb.regWrite
				&& MipsSimEnv.exmem.in.instRw!=0
				&& (new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BEQZ.opcode
						|| new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BNEZ.opcode)
				&& MipsSimEnv.exmem.in.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs ){
			idFlush = true;
			idKeep = true;
			pcKeep = true;
			if(MipsRun.CODE_DEBUG){
				des = (">>>冒险：WRITE-B。");
			}
			hasStall = true;
		}
		
		/*
		 * 1. lw rx -- mem
		 *    气泡  --ex，见上面
		 * 2. b rx  -- id
		 * 在ex和mem中都要插入棋盘，保持id，使得指令1在wb中，通过转发来读取rx。
		 * 这里只需在EX中插入气泡
		 */
		if(MipsSimEnv.exmem.out.mem.memRead
				&& MipsSimEnv.exmem.out.instRw!=0
				&& (new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BEQZ.opcode
						|| new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BNEZ.opcode)
				&& MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs ){
			idFlush = true;
			idKeep = true;
			pcKeep = true;
			if(MipsRun.CODE_DEBUG){
				des = (">>>冒险：LW-USE。");
			}
			hasStall = true;
		}
	}

	public void updateYanchicao() {
		ifFlush = false;
		/*
		 * 如果不使用延迟槽。
		 * 那么如果ID阶段的指令是J或者B，并且成功跳转，那么需要将IF的指令清零，通过在ID阶段插入气泡实现
		 */
		if(MipsSimEnv.jump && MipsSimEnv.instMemUnit.isReady()){
			if(!Option.USE_YANCHICAO){
				ifFlush = true;
				hasStall = true;
				
				des = (">>>冒险：延时槽。");
			}
		}
	
		if(hasStall){
			MipsSimEnv.stall_count ++ ;
		}
	}
	
	//暂停EX及其之前的指令
	private void pauseEx(){
		/*
		 * 如果EX段是NOP的话，就没有必要暂停EX。
		 * 但是如果此NOP是由于LW - USE 冒险，那么需要暂停
		 * 
		 * 此函数在MEM为LW或者SW指令时调用，而此时如果EX段为NOP，那么只有两种情况
		 * ID为跳转指令，ID指令用到了LW的值。前者下一周期ID段的指令为NOP，那么此时无需暂停，因为
		 * ID和EX都是NOP，这样就可以提交一定的效率。后一种情况，是不能删除NOP的，所以需要暂停。
		 */
		if(MipsSimEnv.idex.out.ir == Instances.NOP.code){
			if(MipsSimEnv.exmem.out.mem.memRead //id段上一条指令需要读取数据
					&& MipsSimEnv.exmem.out.instRw!=0
					&&(
							MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs
							|| MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rt )){
				//如果此NOP是由于LW - USE 冒险，那么需要暂停
			}
			else{
				return;
			}
		}
		
		exKeep = true;
		if(MipsSimEnv.ifid.out.ir != Instances.NOP.code){//如果ID段是NOP的话，就没有必要暂停ID
			idKeep = true;
			pcKeep = true;//暂停PC
		}
	}
	

	public void dump(){
		System.out.println("冒险器\tpcKeep:"+pcKeep+",ifFlush:"
				+ifFlush+",idFlush:"+idFlush+",exFlush:"+exFlush+",idKeep:"+idKeep
				+",exKeep:"+exKeep+",memKeep:"+memKeep
				+",memFlush:"+memFlush);
		if(hasStall)
			System.out.println(des);
	}

}
