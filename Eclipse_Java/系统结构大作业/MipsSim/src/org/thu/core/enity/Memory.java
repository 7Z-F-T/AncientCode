package org.thu.core.enity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.thu.core.Option;
import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;
import org.thu.ui.File_in_binary;
import org.thu.ui.Mips_sentence;

public class Memory {
	public byte mem[];
	
	/**
	 * ��ʼ��MemoryΪsize��С��size��ByteΪ��λ�����Զ�������4�ı�����
	 */
	public Memory(int size){
		if(size % 4 !=0)
			size = (size/4+1) * 4;
		if(size>Option.MEM_MAX_SIZE)
			size = Option.MEM_MAX_SIZE;
		mem = new byte[size];
	}
	
	public int getMemSize(){
		return mem.length;
	}
	
	/**
	 * ����Mem��һ������
	 */
	public byte[] getMem(){
		byte tMem[] = new byte[getMemSize()];
		System.arraycopy(mem, 0, tMem, 0, tMem.length);
		return tMem;
	}
	
	public byte[] getMem(int off,int length){
		if(length<0){
			length = -length;
			off = off-length+1;
		}
		if(!isValidAddr(off))
			return new byte[0];
		int realLen = off+length>mem.length ? mem.length-off:length;
		byte tMem[] = new byte[realLen];
		System.arraycopy(mem, off, tMem, 0, realLen);
		return tMem;
	}
	
	/**
	 * ��ָ�����ж�ȡ����,����������ݹ���ʱ��ѡ��ǰ������ݡ�
	 * @param stream �� 
	 * @return
	 */
	public boolean init(InputStream stream){
		if(stream == null)
			return false;
		int length =0;
		try{
			length = stream.read(mem);
		}
		catch (IOException e) {
			MipsSimEnv.errmsgs.add("init:�ڴ��ļ���ʼ���ڴ�ʱ����IO�쳣");
			if(MipsRun.CODE_DEBUG){
				System.err.println("init:�ڴ��ļ���ʼ���ڴ�ʱ����IO�쳣");
			}
		}
		if(length<0)
			return false;
		return true;
	}
	
	public boolean init(File_in_binary file_in_binary){
		if(4 * file_in_binary.commandsize > Option.MEM_MAX_SIZE){
			MipsSimEnv.errmsgs.add("��ʼ�����������������趨��");
			if(MipsRun.CODE_DEBUG){
				System.err.println("��ʼ�����������������趨��");
			}
		}
		int size = 4 * (file_in_binary.commandsize + 10);
		if(size < Option.MEM_SIZE)
			size = Option.MEM_SIZE;
		
		mem = new byte[size];
		for(int i = 0;i<file_in_binary.commandsize;i++){
			Mips_sentence ms = file_in_binary.commands[i];
			writeWord(4*i, ms.getCommand());
		}
		if(MipsRun.CODE_DEBUG){
			System.out.println("�ɹ���ʼ���ڴ棬��СΪ:"+mem.length);
		}
		return true;
	}
	
	public boolean writeByte(int addr,byte b){
		if(!isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("writeByte:д��ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
			if(MipsRun.CODE_DEBUG){
				System.err.println("writeByte:д��ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
			}
			return false;
		}
		mem[addr] = b;
		return true;
	}
	
	public Byte readByte(int addr){
		if(!isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("readByte:��ȡ�ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
			if(MipsRun.CODE_DEBUG){
				MipsSimEnv.errmsgs.add("readByte:��ȡ�ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
				System.err.println();
			}
			return null;
		}
		return mem[addr];
	}
	
	/**
	 * ��λ��ǰ����λ�ں�
	 */
	public boolean writeWord(int addr,int data){
		if(!isValidAddr(addr+3) || !isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("writeWord:д��ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
			if(MipsRun.CODE_DEBUG){
				System.err.println("writeWord:д��ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
			}
			return false;
		}
		
		mem[addr+3] = 	(byte)((data>>>24) & 0xff);
		mem[addr+2] = 	(byte)((data>>>16) & 0xff);
		mem[addr+1] = 	(byte)((data>>>8 ) & 0xff);
		mem[addr+0] = 	(byte) ( data 	  & 0xff);
		return true;
	}
	
	/**
	 * ��λ��ǰ����λ�ں�
	 */
	public boolean writeWord(int addr ,byte[]data){
		if(data == null){
			MipsSimEnv.errmsgs.add("writeWord:byte[]data ΪNULL ��");
			if(MipsRun.CODE_DEBUG){
				System.err.println("writeWord:byte[]data ΪNULL ��");
			}
			return false; 
		}
		int i = 0;
		int t = 0;
		for(int j= 0;j<data.length;j++){
			i = i + (((int)data[j] & 0xff)<<t);
			t = t + 8;
		}

		return writeWord(addr,i);
	}
	
	/**
	 * ��λ��ǰ����λ�ں�
	 */
	public Integer readWord(int addr){
		if(!isValidAddr(addr+3) || !isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("readWord:��ȡ�ĵ�ַ "+ Integer.toHexString(addr) +" ��Ч��");
			if(MipsRun.CODE_DEBUG){
				System.err.println("readWord:��ȡ�ĵ�ַ "+ Integer.toString(addr) +" ��Ч��");
			}
			return null;
		}
		int res = mem[addr+3] & 0xff;
		res = (res<<8) + (mem[addr+2] & 0xff);
		res = (res<<8) + (mem[addr+1] & 0xff);
		res = (res<<8) + (mem[addr+0] & 0xff);
		
		return res;
	}
	
	protected boolean isValidAddr(int addr) {
		return addr>=0 && addr<mem.length;
	}

	public void clear() {
		Arrays.fill(mem, (byte)0);
	}
}
