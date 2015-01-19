package org.thu.core;

public class SimUtils {
	/**
	 * 从datas选出第select个数据.
	 * select从0开始.<br/>
	 * datas个数为0时，返回-1.如果select范围超出datas，选择最后1个。
	 */
	public static int mux(int select,int ... datas){
		if(datas.length == 0){
			return -1;
		}
		if(select>=datas.length-1)
			select = datas.length-1;
		return datas[select];
	}
	
	public static int signedExtended(short value){
		int res = value;
		return (res<<16)>>16;
	}
	
	public static int unsignedExtended(short value){
		int res = value;
		return (res<<16)>>>16;
	}
	
	public static int unsignedExtended(int value){
		int res = value;
		return res;
	}
	
	/**
	 * 最高位为31，最低位为0。获取start位至end位(包括)的值。
	 * start应该大于end。
	 */
	public static int getBits(int value,int start,int end){
		if(start<end){
			int t = start;
			start = end;
			end = t;
		}
		if(start<0 || start>31 || end<0 || end>31){
			MipsSimEnv.errmsgs.add("getBits:范围出错");
			if(MipsRun.CODE_DEBUG){
				System.err.println("getBits:范围出错");
			}
			return -1;
		}
		
		return (value<<(31-start))>>>(end+31-start);
	}
}
