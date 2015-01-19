package org.thu.core;

/**
 * 断点。<br/>
 * 当断点满足条件时，会使得模拟器暂停运行。
 */
public class BreakPoint {
	
	/**
	 * 断点名称
	 */
	public String name;
	
	/**
	 * 是否可用
	 */
	public boolean enabled;
	
	/**
	 * 设置地址
	 */
	public long addr;
	
	public BreakPoint(String name,long addr){
		this.name = name;
		this.addr = addr;
		this.enabled = true;
	}
	
	/**
	 * 判断当前环境是否满足断点
	 */
	public boolean shouldPause(){
		return MipsSimEnv.curip == addr;
	}
}
