package org.thu.core.block;

import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;

public abstract class BasicBlock {
	protected long clock = MipsSimEnv.clock;
	public abstract void update(long clock);
	
	public boolean shouldUpate(long clock){
		if(MipsRun.CODE_DEBUG && ( clock-this.clock>1 || clock<this.clock)){
			MipsSimEnv.errmsgs.add("ϵͳ���õ�Clock��Block�д��ںܴ���졣");
			System.err.println("ϵͳ���õ�Clock��Block�д��ںܴ���졣");
		}
		return this.clock<clock;
	}
}
