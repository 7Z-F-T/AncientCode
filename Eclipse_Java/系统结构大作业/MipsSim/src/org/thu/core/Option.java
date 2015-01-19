package org.thu.core;

public class Option {
	public static final int REG_NUM = 32;
	public static final int IP_ADD = 4;
	public static final int IP_ADD_BITS = 2;
	
	public static final int MEM_MAX_SIZE = 10*1024*1024;//10Mb
	public static final int INST_MAX_NUM = 1000;
	
	public static final int CLOCK_MAX_NUM = 10000;
	
	public static final int MEM_SIZE = 0x100000;
	
	public static final int MEM_INST_READ_CYCLES = 2;//��ȡָ����Ҫ������
	public static final int MEM_DATA_READ_CYCLES = 2;//��ȡ������Ҫ������
	public static final int MEM_DATA_WRITE_CYCLES = 2;//д���ڴ���Ҫ������

	public static boolean USE_YANCHICAO = false;//�Ƿ�ʹ���ӳٲۡ�
}
