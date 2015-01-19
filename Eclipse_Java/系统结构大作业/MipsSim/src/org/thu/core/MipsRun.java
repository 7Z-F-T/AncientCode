package org.thu.core;

import org.thu.core.block.ALU;
import org.thu.core.block.Control;
import org.thu.core.block.Forwarding;
import org.thu.core.block.Stall;
import org.thu.core.enity.Inst;
import org.thu.core.enity.Instances;
import org.thu.core.enity.Registers;
import org.thu.core.latch.BasicLatch;

public class MipsRun {
	public static boolean PROG_DEBUG = true;// 是否断点
	public static boolean CODE_DEBUG = false;// 是否打开调试功能。

	public static int END_BREAKPOINT = 1,
					  END_NORMAL = 0,
					  END_HALT = -1;
	
	public static BreakPointOp bpo = new BreakPointOp();

	private static boolean willHalt = false;//下一个周期停机
	
	public static int reset() {
		CODE_DEBUG = true;
		// 时钟数
		MipsSimEnv.clock = 0;
		// ------------ IF ------------------
		// 当前的IP值
		MipsSimEnv.curip = -4;
		// 下一个IP
		MipsSimEnv.nxtip = 0;

		// -------------- id ------------------

		// 系统的寄存器的值
		MipsSimEnv.reg = new Registers();
		MipsSimEnv.control = new Control();

		MipsSimEnv.JIp = -1;// J型指令跳转地址
		MipsSimEnv.BIp = -1;// B型指令跳转地址
		MipsSimEnv.reg1IsZero = false;
		MipsSimEnv.jump = false;

		// --------------ex ----------------
		MipsSimEnv.alu = new ALU();

		// ------------- mem ----------------
		// 内存
		MipsSimEnv.instMemUnit.clear();
		MipsSimEnv.dataMemUnit.clear();

		// ------------- wb ---------------
		MipsSimEnv.regWData = 0;
		MipsSimEnv.regWNo = 0;

		// ---------------- other ---------------
		// 锁存器
		MipsSimEnv.ifid.clear();
		MipsSimEnv.idex.clear();
		MipsSimEnv.exmem.clear();
		MipsSimEnv.memwb.clear();

		// 冒险
		MipsSimEnv.stall = new Stall();
		MipsSimEnv.forwarding = new Forwarding();

		// 统计
		MipsSimEnv.stall_count = 0;
		MipsSimEnv.forward_count = 0;
		MipsSimEnv.inst_count = 0;
		MipsSimEnv.instReadMem_count = 0;
		MipsSimEnv.instWriteMem_count = 0;

		MipsSimEnv.isHalt = false;
		willHalt = false;
		pauseIp = -4;
		
		MipsSimEnv.errmsgs.clear();
		return 0;
	}

	public static long pauseIp = -4;//由于
	/**
	 * 为0表示正常运行，为-1表示停机，为1表示断点。
	 * 
	 * @return
	 */
	public static int singleStep() {
		if(MipsSimEnv.clock>100){
			CODE_DEBUG = false;
		}
		
		if (MipsSimEnv.isHalt)
			return END_HALT;

		// 计算curIp(根据pckeep和nxtIp获得)
		int tIp = MipsSimEnv.curip;
		if (!MipsSimEnv.stall.pcKeep)
			MipsSimEnv.curip = MipsSimEnv.nxtip;

		// 断点验证
		if (PROG_DEBUG && pauseIp!= MipsSimEnv.curip &&  bpo.shouldPause()) {
			pauseIp = MipsSimEnv.curip;
			MipsSimEnv.curip = tIp;
			return END_BREAKPOINT;
		}
		if (pauseIp!= MipsSimEnv.curip){
			pauseIp = -4;
		}

		MipsSimEnv.errmsgs.clear();
		
		MipsSimEnv.curip = tIp;
		// 时钟加1
		++MipsSimEnv.clock;

		updateLatch(); // 更新所有锁存器(除了PC)
		updatePC_();// 更新PC模块之后的内容，包括读取指令等。
		updateWb();
		updateMem();
		updateForwarding();
		updateEx();
		updateID();
		update_PC();// 更新PC模块之后的内容，包括计算nextIp等。

		updateCount();
		if (MipsRun.CODE_DEBUG) {
			dump();
		}

		if(willHalt){
			MipsSimEnv.isHalt = true;
			halt();
		}
		
		if (MipsSimEnv.memwb.out.wb.halt) {
			willHalt = true;
		}

		
		return END_NORMAL;
	}

	private static void halt() {
		MipsSimEnv.isHalt = true;
		System.out.println("系统停机.");
	}

	private static void updatePC_() {
		if (!MipsSimEnv.stall.pcKeep)
			MipsSimEnv.curip = MipsSimEnv.nxtip;

		int nxtIp = MipsSimEnv.curip + Option.IP_ADD;
		MipsSimEnv.ifid.in.ip = nxtIp;

		// 读取指令
		if (MipsSimEnv.instMemUnit.isReady()) {// 发送读取数据请求
			MipsSimEnv.instMemUnit.read(MipsSimEnv.curip);
		} else {
			/*
			 * 前面已经申请过了，Skip
			 */
		}

		MipsSimEnv.instMemUnit.update();// 必不可少

		if (MipsSimEnv.instMemUnit.isReadFinish()) {// 如果数据已经读取到了
			Integer inst =  MipsSimEnv.instMemUnit.getReadData();
			if(inst == null)
				inst = Instances.HALT.code;
			MipsSimEnv.ifid.in.ir = inst;
		} else {
			MipsSimEnv.ifid.in.ir = Instances.NOP.code;//便于调试
		}
	}

	private static void update_PC() {
		boolean bBranch = MipsSimEnv.reg1IsZero ^ MipsSimEnv.control.Bneq; // xor
		int selectNxtIp = (MipsSimEnv.control.BBranch && bBranch) ? 2 : 0;
		selectNxtIp = MipsSimEnv.control.Jbranch ? selectNxtIp + 1
				: selectNxtIp;

		if (MipsRun.CODE_DEBUG && selectNxtIp == 3) {
			MipsSimEnv.errmsgs.add("J跳转和B跳转同时成立.");
			System.err.println("J跳转和B跳转同时成立.");
		}

		MipsSimEnv.jump = selectNxtIp>0;
		
		MipsSimEnv.stall.updateYanchicao();
		
		MipsSimEnv.nxtip = SimUtils.mux(selectNxtIp, 
				MipsSimEnv.ifid.in.ip,
				MipsSimEnv.JIp, 
				MipsSimEnv.BIp, 
				-1);
	}

	private static void updateID() {
		MipsSimEnv.idex.in.ir = MipsSimEnv.ifid.out.ir;
		MipsSimEnv.idex.in.ip = MipsSimEnv.ifid.out.ip;

		Integer tIr = MipsSimEnv.ifid.out.ir;
		
		
		// 指令parse
		MipsSimEnv.idex.in.imm = (short) tIr.intValue();
		MipsSimEnv.idex.in.instRs = SimUtils.getBits(tIr, 25, 21);
		MipsSimEnv.idex.in.instRt = SimUtils.getBits(tIr, 20, 16);
		MipsSimEnv.idex.in.instRd = SimUtils.getBits(tIr, 15, 11);

		// Control
	//	MipsSimEnv.control.update(MipsSimEnv.clock);

		// B jump Ip
		int offset = SimUtils.signedExtended(MipsSimEnv.idex.in.imm);
		
		MipsSimEnv.BIp = offset + MipsSimEnv.ifid.out.ip;

		// J jump Ip
		offset =((MipsSimEnv.idex.in.ir & 0x03ffffff)<<6)>>6;
		MipsSimEnv.JIp = offset + MipsSimEnv.ifid.out.ip;

		// reg
		MipsSimEnv.reg.readReg1No = MipsSimEnv.idex.in.instRs;
		MipsSimEnv.reg.readReg2No = MipsSimEnv.idex.in.instRt;
		MipsSimEnv.reg.regWrite = MipsSimEnv.memwb.out.wb.regWrite;
		MipsSimEnv.reg.writeRegNo = MipsSimEnv.regWNo;
		MipsSimEnv.reg.writeRegData = MipsSimEnv.regWData;

		MipsSimEnv.reg.update(MipsSimEnv.clock);

		MipsSimEnv.idex.in.reg1Data = MipsSimEnv.reg.reg1Data;
		MipsSimEnv.idex.in.reg2Data = MipsSimEnv.reg.reg2Data;

		// reg1EqualRe2
		int reg1NewData = SimUtils.mux(
				MipsSimEnv.forwarding.selectBJumpData, 
				MipsSimEnv.idex.in.reg1Data,
				MipsSimEnv.regWData,
				MipsSimEnv.exmem.out.aluRes,
				0
				);
		MipsSimEnv.reg1IsZero = reg1NewData == 0;

		MipsSimEnv.stall.update(MipsSimEnv.clock);

		MipsSimEnv.idex.in.wb.copy(MipsSimEnv.control.wb);
		MipsSimEnv.idex.in.mem.copy(MipsSimEnv.control.mem);
		MipsSimEnv.idex.in.ex.copy(MipsSimEnv.control.ex);
	}

	private static void updateEx() {
		MipsSimEnv.exmem.in.ir = MipsSimEnv.idex.out.ir;
		MipsSimEnv.exmem.in.ip = MipsSimEnv.idex.out.ip;

		int regDst = MipsSimEnv.idex.out.ex.regDst ? 1 : 0;
		MipsSimEnv.exmem.in.instRw = SimUtils.mux(regDst,
				MipsSimEnv.idex.out.instRt, MipsSimEnv.idex.out.instRd);

		MipsSimEnv.exmem.in.reg2Data = SimUtils.mux(
				MipsSimEnv.forwarding.selectAluData2,
				MipsSimEnv.idex.out.reg2Data, 
				MipsSimEnv.regWData,
				MipsSimEnv.exmem.out.aluRes, 
				0);

		updateAlu();
		MipsSimEnv.exmem.in.aluRes = MipsSimEnv.alu.aluRes;

		MipsSimEnv.control.update(MipsSimEnv.clock);

		MipsSimEnv.exmem.in.wb.copy(MipsSimEnv.idex.out.wb);
		MipsSimEnv.exmem.in.mem.copy(MipsSimEnv.idex.out.mem);
	}

	private static void updateForwarding() {
		MipsSimEnv.forwarding.instRs_ex = MipsSimEnv.idex.out.instRs;
		MipsSimEnv.forwarding.instRt_ex = MipsSimEnv.idex.out.instRt;
		MipsSimEnv.forwarding.instRw_mem = MipsSimEnv.exmem.out.instRw;
		MipsSimEnv.forwarding.instRw_wb = MipsSimEnv.memwb.out.instRw;
		MipsSimEnv.forwarding.regWrite_mem = MipsSimEnv.exmem.out.wb.regWrite ? 1
				: 0;
		MipsSimEnv.forwarding.regWrite_wb = MipsSimEnv.memwb.out.wb.regWrite ? 1
				: 0;
		MipsSimEnv.forwarding.update(MipsSimEnv.clock);
	}

	private static void updateAlu() {
		MipsSimEnv.alu.data1 = SimUtils.mux(
				MipsSimEnv.forwarding.selectAluData1,
				MipsSimEnv.idex.out.reg1Data, 
				MipsSimEnv.regWData,
				MipsSimEnv.exmem.out.aluRes, 
				0);

		int tAluSrc = MipsSimEnv.idex.out.ex.aluSrc ? 1 : 0;
		MipsSimEnv.alu.immSrc = MipsSimEnv.idex.out.ex.aluSrc;
		
		MipsSimEnv.alu.data2 = SimUtils.mux(tAluSrc,
				MipsSimEnv.exmem.in.reg2Data,
				MipsSimEnv.idex.out.imm);
		
		MipsSimEnv.alu.aluControl = MipsSimEnv.idex.out.ex.aluOp;

		MipsSimEnv.alu.update(MipsSimEnv.clock);
	}

	private static void updateMem() {
		MipsSimEnv.memwb.in.ir = MipsSimEnv.exmem.out.ir;
		MipsSimEnv.memwb.in.ip = MipsSimEnv.exmem.out.ip;

		MipsSimEnv.memwb.in.instRw = MipsSimEnv.exmem.out.instRw;
		MipsSimEnv.memwb.in.aluRes = MipsSimEnv.exmem.out.aluRes;

		MipsSimEnv.memwb.in.memData = 0;

		// 读取内存，需要2个周期
		if (MipsSimEnv.exmem.out.mem.memRead) {
			if (MipsSimEnv.dataMemUnit.isReady()) {// 发送读取数据请求
				MipsSimEnv.dataMemUnit.read(MipsSimEnv.exmem.out.aluRes);
			} else {
				/*
				 * 前面已经申请过了，Skip
				 */
			}
		} else if (MipsSimEnv.exmem.out.mem.memWrite) { // 写入内存
			if (MipsSimEnv.dataMemUnit.isReady()) {
				MipsSimEnv.dataMemUnit.write(MipsSimEnv.exmem.out.aluRes,
						MipsSimEnv.exmem.out.reg2Data);
			} else {/*
					 * 前面已经申请过了，Skip
					 */
			}
		} else {
			MipsSimEnv.dataMemUnit.commit();
		}

		MipsSimEnv.dataMemUnit.update();// 必不可少

		if (MipsSimEnv.dataMemUnit.isReadFinish()) {// 如果数据已经读取到了
			Integer data  = MipsSimEnv.dataMemUnit.getReadData();
			if(data == null)
				data = 0;
			MipsSimEnv.memwb.in.memData = data;
		} else {
			MipsSimEnv.memwb.in.memData = -1;//便于调试
		}

		MipsSimEnv.memwb.in.wb.copy(MipsSimEnv.exmem.out.wb);
	}

	private static void updateWb() {
		MipsSimEnv.regWNo = MipsSimEnv.memwb.out.instRw;
		int memToReg = MipsSimEnv.memwb.out.wb.memToReg ? 1 : 0;
		MipsSimEnv.regWData = SimUtils.mux(memToReg,
				MipsSimEnv.memwb.out.aluRes, MipsSimEnv.memwb.out.memData);
	}

	private static void updateLatch() {
		for (BasicLatch latch : MipsSimEnv.latches) {
			latch.update();
		}
	}

	public static void dump() {
		System.out.println("------------- " + MipsSimEnv.clock
				+ " -----------------");
		MipsSimEnv.ifid.dump();
		MipsSimEnv.idex.dump();
		MipsSimEnv.exmem.dump();
		MipsSimEnv.memwb.dump();
	
		MipsSimEnv.alu.dump();
		
		MipsSimEnv.reg.dump();
	
		MipsSimEnv.control.dump();
		MipsSimEnv.stall.dump();
		MipsSimEnv.forwarding.dump();
		MipsSimEnv.instMemUnit.dump("指令内存");
		MipsSimEnv.dataMemUnit.dump("数据内存");
		dumpVars();
	}

	public static void updateCount(){
		Inst inst = new Inst(MipsSimEnv.ifid.in.ir);
		if(!MipsSimEnv.stall.ifFlush && !MipsSimEnv.control.IF_Flush
				&& inst.code!=Instances.NOP.code && inst.code != Instances.HALT.code){
			MipsSimEnv.inst_count ++ ;
			
			if(inst.opcode == Instances.LW.opcode){
				MipsSimEnv.instReadMem_count++;
			}
			else if(inst.opcode == Instances.SW.opcode){
				MipsSimEnv.instWriteMem_count++;
			}
		}
	}
	
	public static void dumpVars() {
		System.out.println("变量\t"
				+ "clock:" + MipsSimEnv.clock
				+ ",curip:"+ MipsSimEnv.curip
				+ ",nxtip:" + MipsSimEnv.nxtip
				+ ",JIp:" + MipsSimEnv.JIp
				+ ",BIp:" + MipsSimEnv.BIp
				+ ",reg1IsZero:" + MipsSimEnv.reg1IsZero
				+ ",jump:"+MipsSimEnv.jump
				+ ",regWData:" + MipsSimEnv.regWData
				+ ",regWNo:" + MipsSimEnv.regWNo);
	}
	
/*
	public static void main(String[] args) {
		dump();
		int i = 0;
		while (MipsRun.singleStep() >= 0) {
			if (i++ > 10)
				break;
			dump();
		}
	}*/

}
