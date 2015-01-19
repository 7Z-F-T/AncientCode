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
	 * ��Ӷϵ�
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
	 * ɾ���ϵ�
	 */
	public boolean removeAddrBreakpoint(long addr){
		if(bps.containsKey(addr)){
			bps.remove(addr);
			return true;
		}
		return false;
	}
	
	/**
	 * ������жϵ�
	 */
	public Collection<BreakPoint> getAllBreakPoint(){
		return bps.values();
	}
	
	/**
	 * ���öϵ����Ч�ԡ�
	 */
	public void setAddrBreakpointEnabled(long addr,boolean enabled){
		if(bps.containsKey(addr)){
			bps.get(addr).enabled = enabled;
		}
	}
	
	/**
	 * ��öϵ����Ч��
	 */
	public boolean getBreakpointEnabled(){
		return true;
	}
	
	/**
	 * ��֤���жϵ㡣<br/>
	 */
	public boolean shouldPause(){
		for(BreakPoint bp : bps.values()){
			if(bp.shouldPause())
				return true;
		}
		return false;
	}
}
