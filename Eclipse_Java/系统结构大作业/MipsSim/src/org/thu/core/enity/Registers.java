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
					System.err.println("���ܽ���0ֵд��R0�Ĵ�����R0�Ĵ�����ֵ����Ϊ0");
				}
				MipsSimEnv.errmsgs.add("���ܽ���0ֵд��R0�Ĵ�����R0�Ĵ�����ֵ����Ϊ0");
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
			System.out.println(String.format("д��Ĵ���%d������Ϊ%d", writeRegNo,
					writeRegData));
		}
		System.out.println(String.format("��ȡ�Ĵ���%d,%d������%d,%d", readReg1No,
				readReg2No, reg1Data, reg2Data));
		for(int reg : regs){
			System.out.print(reg+" ");
		}
		System.out.println();
	}
}
