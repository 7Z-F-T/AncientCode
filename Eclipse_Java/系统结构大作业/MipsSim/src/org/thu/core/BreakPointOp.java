package org.thu.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BreakPointOp {
	Map<Long,BreakPoint> bps;
	
	public BreakPointOp(){
		bps = new HashMap<Long,BreakPoint>();
	}
	
	/**
	 * 添加断点
	 */
	public boolean addAddrBreakpoint(long addr){
		if(bps.containsKey(addr))
			return false;
		String name = Long.toHexString(addr).toUpperCase();
		BreakPoint bp = new BreakPoint(name,addr);
		bps.put(addr, bp);
		return true;
	}
	
	/**
	 * 删除断点
	 */
	public boolean removeAddrBreakpoint(long addr){
		if(bps.containsKey(addr)){
			bps.remove(addr);
			return true;
		}
		return false;
	}
	
	/**
	 * 获得所有断点
	 */
	public Collection<BreakPoint> getAllBreakPoint(){
		return bps.values();
	}
	
	/**
	 * 设置断点的有效性。
	 */
	public void setAddrBreakpointEnabled(long addr,boolean enabled){
		if(bps.containsKey(addr)){
			bps.get(addr).enabled = enabled;
		}
	}
	
	/**
	 * 获得断点的有效性
	 */
	public boolean getBreakpointEnabled(){
		return true;
	}
	
	/**
	 * 验证所有断点。<br/>
	 */
	public boolean shouldPause(){
		for(BreakPoint bp : bps.values()){
			if(bp.shouldPause())
				return true;
		}
		return false;
	}
}
