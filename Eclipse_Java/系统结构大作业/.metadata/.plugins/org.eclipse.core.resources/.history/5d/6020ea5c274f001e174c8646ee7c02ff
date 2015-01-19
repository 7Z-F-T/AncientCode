package org.thu.ui;

/**
 * @author Administrator
 *二进制可执行代码，编译后从文件获得
 */
public class File_in_binary {
	Mipsframe mipsframe;
	
	public static final int maxsize = 1000;
	
	/**
	 * 用于保存从文件读进来的commands
	 */
	public Mips_sentence[] commands;
	
	/**
	 * commands的条数
	 */
	public int commandsize;
	
	public int pc1,pc2,pc3,pc4,pc5;
	
	public File_in_binary(Mipsframe mipsframe){
		this.mipsframe = mipsframe;
		commands = new Mips_sentence[maxsize];
		
		commandsize = 0;
		/*commandsize = 5;
		Byte [] t = {-1,2,3,4};
		this.commands[0] = new Mips_sentence(t);
		this.commands[1] = new Mips_sentence(t);
		this.commands[2] = new Mips_sentence(t);
		this.commands[3] = new Mips_sentence(t);
		this.commands[4] = new Mips_sentence(t);
		this.commands[1].setbreakpoint(true);*/
		
		this.pc1 = this.pc2 = this.pc3 = this.pc4 = this.pc5= -1;
		//this.pc1 = 1;
	}
	
	/**
	 * 用于编译后重新给该对象赋值，从文件赋值！
	 * 文件名自己定，但要统一！
	 */
	public void read_from_file(){
		////////////从文件读入，赋值给commands和commandsize，uncompleted!!
		//传入的size = Integer.toHexString(i*4),i为该sentence的下标
	}
}
