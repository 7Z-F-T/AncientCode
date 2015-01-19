package org.thu.core;

public class SimUtils {
	/**
	 * ��datasѡ����select������.
	 * select��0��ʼ.<br/>
	 * datas����Ϊ0ʱ������-1.���select��Χ����datas��ѡ�����1����
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
	 * ���λΪ31�����λΪ0����ȡstartλ��endλ(����)��ֵ��
	 * startӦ�ô���end��
	 */
	public static int getBits(int value,int start,int end){
		if(start<end){
			int t = start;
			start = end;
			end = t;
		}
		if(start<0 || start>31 || end<0 || end>31){
			MipsSimEnv.errmsgs.add("getBits:��Χ����");
			if(MipsRun.CODE_DEBUG){
				System.err.println("getBits:��Χ����");
			}
			return -1;
		}
		
		return (value<<(31-start))>>>(end+31-start);
	}
}
