package org.thu.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.thu.core.block.ALU;
import org.thu.core.block.Control;
import org.thu.core.block.Forwarding;
import org.thu.core.block.MemUnit;
import org.thu.core.block.Stall;
import org.thu.core.enity.Memory;
import org.thu.core.enity.Registers;
import org.thu.core.latch.BasicLatch;
import org.thu.core.latch.EXMEM;
import org.thu.core.latch.IDEX;
import org.thu.core.latch.IFID;
import org.thu.core.latch.MEMWB;

public class MipsSimEnv {
	// 时钟数
	public static long clock = 0;
	// ------------ IF ------------------
	// 当前的IP值
	public static int curip = -4;
	// 下一个IP
	public static int nxtip = 0;
	
	//-------------- id ------------------
	
	// 系统的寄存器的值
	public static Registers reg = new Registers();
	public static Control control = new Control();
	
	public static int JIp = -1;//J型指令跳转地址
	public static int BIp = -1;//B型指令跳转地址
	public static boolean reg1IsZero = false; //reg1的数据为0
	public static boolean jump = false;//是否跳转，如果跳转并且不用延迟槽，那么需要将IF的代码请0.
	
	// --------------ex ----------------
	public static ALU alu = new ALU();
	
	// ------------- mem ----------------
	// 内存
	public static Memory instMem = new Memory(Option.MEM_SIZE);
	public static Memory dataMem = new Memory(Option.MEM_SIZE);
	
	public static MemUnit instMemUnit = new MemUnit(instMem,true,Option.MEM_INST_READ_CYCLES,-1);
	public static MemUnit dataMemUnit = new MemUnit(dataMem,Option.MEM_DATA_READ_CYCLES,Option.MEM_DATA_WRITE_CYCLES);
	
	// ------------- wb ---------------
	public static int regWData;
	public static int regWNo;
	
	//---------------- other ---------------
	// 锁存器
	public static IFID ifid = new IFID();
	public static IDEX idex = new IDEX();
	public static EXMEM exmem = new EXMEM();
	public static MEMWB memwb = new MEMWB();
	
	public static List<BasicLatch> latches = new ArrayList<BasicLatch>(4);
	static{
		latches.add(ifid);
		latches.add(idex);
		latches.add(exmem);
		latches.add(memwb);
	}
	
	// 冒险
	public static Stall stall = new Stall();
	public static Forwarding forwarding = new Forwarding();
	
	// 统计
	public static long stall_count = 0;
	public static long forward_count = 0;
	public static long inst_count = 0;
	public static long instReadMem_count = 0;
	public static long instWriteMem_count = 0;
	

	public static boolean isHalt = false;
	
	public static Vector<String> errmsgs = new Vector<String>();
}
