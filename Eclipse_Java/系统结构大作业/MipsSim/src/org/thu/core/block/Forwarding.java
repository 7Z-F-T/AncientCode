package org.thu.core.block;

import org.thu.core.MipsSimEnv;
import org.thu.core.enity.Inst;

public class Forwarding extends BasicBlock{
	public int instRw_mem;
	public int instRw_wb;
	public int instRs_ex;
	public int instRt_ex;
	
	public int regWrite_mem;
	public int regWrite_wb;
	
	public int selectAluData1;
	public int selectAluData2;
	
	public int selectBJumpData;//B指令需要读取寄存器，使用这个来控制
	@Override
	public void update(long clock) {
		if(!shouldUpate(clock))
			return;
		this.clock = clock;
		
//		0 - MipsSimEnv.idex.out.reg1Data, 
//		1 - MipsSimEnv.regWData,
//		2 - MipsSimEnv.exmem.out.aluRes, 
//		3 - 0
		selectAluData1 = 0;
		
//		0 - MipsSimEnv.idex.out.reg2Data, 
//		1 - MipsSimEnv.regWData,
//		2 - MipsSimEnv.exmem.out.aluRes, 
//		3 - 0
		selectAluData2 = 0;
		
//		0 - MipsSimEnv.idex.in.reg1Data, 
//		1 - MipsSimEnv.regWData,
//		2 - MipsSimEnv.exmem.out.aluRes, 
//		3 - 0		
		selectBJumpData =0;

		/*
		 * 1. 从内存中读r1
		 * 2. 使用r1
		 */
		if(MipsSimEnv.memwb.out.wb.regWrite
				&& MipsSimEnv.memwb.out.instRw!=0
				&& MipsSimEnv.memwb.out.instRw == MipsSimEnv.idex.out.instRs)
			selectAluData1 = 1;
		
		if(MipsSimEnv.memwb.out.wb.regWrite
				&& MipsSimEnv.memwb.out.instRw!=0
				&& MipsSimEnv.memwb.out.instRw == MipsSimEnv.idex.out.instRt)
			selectAluData2 = 1;
		
		if(MipsSimEnv.memwb.out.wb.regWrite
				&& MipsSimEnv.memwb.out.instRw!=0
				&& MipsSimEnv.memwb.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs)
			selectBJumpData = 1;
		
		/*
		 * 1.写r1
		 * 2.使用r1
		 */
		if(MipsSimEnv.exmem.out.wb.regWrite
				&& MipsSimEnv.exmem.out.instRw!=0
				&& MipsSimEnv.exmem.out.instRw == MipsSimEnv.idex.out.instRs)
			selectAluData1 = 2;
		
		if(MipsSimEnv.exmem.out.wb.regWrite
				&& MipsSimEnv.exmem.out.instRw!=0
				&& MipsSimEnv.exmem.out.instRw == MipsSimEnv.idex.out.instRt)
			selectAluData2 = 2;
		
		if(MipsSimEnv.exmem.out.wb.regWrite
				&& MipsSimEnv.exmem.out.instRw!=0
				&& MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs)
			selectBJumpData = 2;
		
		if(selectAluData1>0 || selectAluData2>0 || selectBJumpData>0){
			MipsSimEnv.forward_count ++ ;
		}
		
	}
	
	public void dump(){
		String[] strs = {"寄存器值","内存的值","ALU的结果",""};
		System.out.println("转发器:\t操作数1："+strs[selectAluData1]+",操作数2："+strs[selectAluData2]
		                  +",跳转操作数："+strs[selectBJumpData]);
	}
	
}
