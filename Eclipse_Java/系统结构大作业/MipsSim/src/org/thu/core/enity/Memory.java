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
	 * 初始化Memory为size大小，size以Byte为单位。会自动规整到4的倍数。
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
	 * 返回Mem的一个拷贝
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
	 * 从指定流中读取数据,如果流中数据过多时，选择前面的数据。
	 * @param stream 流 
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
			MipsSimEnv.errmsgs.add("init:在从文件初始化内存时发生IO异常");
			if(MipsRun.CODE_DEBUG){
				System.err.println("init:在从文件初始化内存时发生IO异常");
			}
		}
		if(length<0)
			return false;
		return true;
	}
	
	public boolean init(File_in_binary file_in_binary){
		if(4 * file_in_binary.commandsize > Option.MEM_MAX_SIZE){
			MipsSimEnv.errmsgs.add("初始化的内容容量超过设定。");
			if(MipsRun.CODE_DEBUG){
				System.err.println("初始化的内容容量超过设定。");
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
			System.out.println("成功初始化内存，大小为:"+mem.length);
		}
		return true;
	}
	
	public boolean writeByte(int addr,byte b){
		if(!isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("writeByte:写入的地址 "+ Integer.toString(addr) +" 无效。");
			if(MipsRun.CODE_DEBUG){
				System.err.println("writeByte:写入的地址 "+ Integer.toString(addr) +" 无效。");
			}
			return false;
		}
		mem[addr] = b;
		return true;
	}
	
	public Byte readByte(int addr){
		if(!isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("readByte:读取的地址 "+ Integer.toString(addr) +" 无效。");
			if(MipsRun.CODE_DEBUG){
				MipsSimEnv.errmsgs.add("readByte:读取的地址 "+ Integer.toString(addr) +" 无效。");
				System.err.println();
			}
			return null;
		}
		return mem[addr];
	}
	
	/**
	 * 低位在前，高位在后
	 */
	public boolean writeWord(int addr,int data){
		if(!isValidAddr(addr+3) || !isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("writeWord:写入的地址 "+ Integer.toString(addr) +" 无效。");
			if(MipsRun.CODE_DEBUG){
				System.err.println("writeWord:写入的地址 "+ Integer.toString(addr) +" 无效。");
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
	 * 低位在前，高位在后
	 */
	public boolean writeWord(int addr ,byte[]data){
		if(data == null){
			MipsSimEnv.errmsgs.add("writeWord:byte[]data 为NULL 。");
			if(MipsRun.CODE_DEBUG){
				System.err.println("writeWord:byte[]data 为NULL 。");
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
	 * 低位在前，高位在后
	 */
	public Integer readWord(int addr){
		if(!isValidAddr(addr+3) || !isValidAddr(addr)){
			MipsSimEnv.errmsgs.add("readWord:读取的地址 "+ Integer.toHexString(addr) +" 无效。");
			if(MipsRun.CODE_DEBUG){
				System.err.println("readWord:读取的地址 "+ Integer.toString(addr) +" 无效。");
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
