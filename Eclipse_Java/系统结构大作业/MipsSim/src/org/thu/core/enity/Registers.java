package org.thu.core.enity;

import org.thu.core.Option;
import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;
import org.thu.core.block.BasicBlock;

public class Registers extends BasicBlock {
	public int[] regs;

	public Registers() {
		regs = new int[Option.REG_NUM];
	}

	public boolean regWrite;
	public int readReg1No;
	public int readReg2No;
	public int reg1Data;
	public int reg2Data;
	public int writeRegNo;
	public int writeRegData;

	@Override
	public void update(long clock) {
		if (!shouldUpate(clock))
			return;
		this.clock = clock;

		if (regWrite) {
			if(writeRegNo == 0 && writeRegData!=0){
				if(MipsRun.CODE_DEBUG){
					System.err.println("不能将非0值写入R0寄存器，R0寄存器的值必须为0");
				}
				MipsSimEnv.errmsgs.add("不能将非0值写入R0寄存器，R0寄存器的值必须为0");
			}
			else{
				regs[writeRegNo] = writeRegData;
			}
		}
		reg1Data = regs[readReg1No];
		reg2Data = regs[readReg2No];
	}

	public void dump() {
		if (regWrite) {
			System.out.println(String.format("写入寄存器%d，数据为%d", writeRegNo,
					writeRegData));
		}
		System.out.println(String.format("读取寄存器%d,%d，数据%d,%d", readReg1No,
				readReg2No, reg1Data, reg2Data));
		for(int reg : regs){
			System.out.print(reg+" ");
		}
		System.out.println();
	}
}
