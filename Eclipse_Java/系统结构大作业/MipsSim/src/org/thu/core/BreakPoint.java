package org.thu.core;

/**
 * �ϵ㡣<br/>
 * ���ϵ���������ʱ����ʹ��ģ������ͣ���С�
 */
public class BreakPoint {
	
	/**
	 * �ϵ�����
	 */
	public String name;
	
	/**
	 * �Ƿ����
	 */
	public boolean enabled;
	
	/**
	 * ���õ�ַ
	 */
	public long addr;
	
	public BreakPoint(String name,long addr){
		this.name = name;
		this.addr = addr;
		this.enabled = true;
	}
	
	/**
	 * �жϵ�ǰ�����Ƿ�����ϵ�
	 */
	public boolean shouldPause(){
		return MipsSimEnv.curip == addr;
	}
}
