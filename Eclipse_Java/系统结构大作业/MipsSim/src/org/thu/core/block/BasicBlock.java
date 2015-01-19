package org.thu.core.block;

import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;

public abstract class BasicBlock {
	protected long clock = MipsSimEnv.clock;
	public abstract void update(long clock);
	
	public boolean shouldUpate(long clock){
		if(MipsRun.CODE_DEBUG && ( clock-this.clock>1 || clock<this.clock)){
			MipsSimEnv.errmsgs.add("系统调用的Clock比Block中存在很大差异。");
			System.err.println("系统调用的Clock比Block中存在很大差异。");
		}
		return this.clock<clock;
	}
}
