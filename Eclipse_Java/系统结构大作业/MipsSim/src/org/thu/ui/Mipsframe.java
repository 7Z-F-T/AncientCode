package org.thu.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;
import org.thu.core.Option;
import org.thu.core.complier.Complier;

/**
 *frame类
 */

public class Mipsframe extends JFrame implements MouseListener,KeyListener{
	/**
	 * 寄存器显示面板
	 */
	public JTable registers;
	
	/**
	 * 数据内存显示面板
	 */
	public JTable dataMemory;
	
	
	/**
	 * 菜单栏
	 */
	public MipsMenubar mipsmenubar;
	
	/**
	 *显示文本区域 
	 */
	public JTextPane textarea;
	
	/**
	 * 输出信息
	 */
	public JTextArea output;

	/**
	 * mips_file对象
	 */
	public static Mips_file  mips_file;
	
	/**
	 * 二进制可执行代码
	 */
	public static File_in_binary file_in_binary;
	
	/**
	 * 是否处于debug状态
	 */
	public static boolean debug;
	
	/**
	 * 内存面板显示的基地址
	 */
	public int base_memory_address;
	
	/**
	 * 改变显示内存基地址的首地址对话框
	 */
	public SetBMADialog setBMADialog;
	
	/**
	 * 设置内存内容对话框
	 */
	public SetMDialog setMDialog;
	
	/**
	 * 设置寄存器内容对话框
	 */
	public SetRDialog setRDialog;
	
	public Complier complier;
	
	JTabbedPane jtp;
	
	JList jl ;
	
	JTextField datawritecycles,datareadcycles,instreadcycles;
	
	JCheckBox yanshicao_box;
	
	public static boolean[][] register_changed;
	public static boolean[][] memory_changed;
	
	JPanel showout_panel;
	
	JTable ifid,idexe,exemem,memwb;
	
	JTextField cycles_count_field,stall_count_field,forward_count_field,inst_count_field,
	instReadMem_count_field,instWriteMem_count_field;

	private JScrollPane textAreaSP;

	
	public Mipsframe(){
		this.setBounds(0, 0, 1012,734);
		this.setTitle("MipSim");
		this.setResizable(true);
		this.setLayout(null);
		
		this.mipsmenubar = new MipsMenubar(this);
		this.setJMenuBar(mipsmenubar);
		
		this.base_memory_address = 0;
		
		jtp = new JTabbedPane();
		jtp.setBounds(2, 498,1001, 179);
		
		String[][] memo;
		memo = new String[16][17];
		for(int i = 0;i<16;i++){
			memo[i][0] = String.format("0x%04x", i*16);
			if(memo[i][0].length() == 1)
				memo[i][0] = "00"+memo[i][0];
			if(memo[i][0].length() == 2)
				memo[i][0] = "0"+memo[i][0];
			for(int j = 1;j<17;j++){
				memo[i][j] = new String("00");
			}
		}
		String [] colume = new String[17];
		colume[0] = new String("Address");
		for(int i = 1;i<17;i++){
			colume[i] = new String(""+(i-1));
		}
		dataMemory= new JTable(new MyMemoryTableModel(memo,colume));
		//memory.setShowVerticalLines(false);
		dataMemory.getTableHeader().setReorderingAllowed(false); 
		dataMemory.setEnabled(true);
		dataMemory.getColumnModel().getColumn(0).setPreferredWidth(200); 
		this.makeFace_memory(dataMemory);
		dataMemory.setFont(new Font("Courier New",Font.PLAIN,15));
		
		
		JScrollPane jsp1 = new JScrollPane(dataMemory);
		//jsp1.setBounds(303,410,700,277);
		jtp.add("Memory",jsp1);
		
		String[][] register;
		register = new String[8][8];
		for(int i = 0;i<8;i++){
			register[i][0] = new String("R"+i);
			register[i][1] = new String("0x00000000");
			register[i][2] =  new String("R"+(i+8));
			register[i][3] = new String("0x00000000");
			register[i][4] =  new String("R"+(i+16));
			register[i][5] = new String("0x00000000");
			register[i][6] =  new String("R"+(i+24));
			register[i][7] = new String("0x00000000");
		}
		colume = new String[8];
		colume[0] = new String("Register");
		colume[1] = new String("Value");
		colume[2] = new String("Register");
		colume[3] = new String("Value");
		colume[4] = new String("Register");
		colume[5] = new String("Value");
		colume[6] = new String("Register");
		colume[7] = new String("Value");
		registers = new JTable(register,colume);
		registers.getTableHeader().setReorderingAllowed(false); 
		registers.getColumnModel().getColumn(0).setPreferredWidth(40); 
		registers.getColumnModel().getColumn(2).setPreferredWidth(40); 
		this.makeFace_register(registers);
		registers.setFont(new Font("Courier New",Font.PLAIN,15));
		registers.setEnabled(false);
		JScrollPane jsp3 = new JScrollPane(registers);
		//jsp3.setBounds(2,410,300,277);
		jtp.add("Registers",jsp3);
		
		output = new JTextArea();
		output.setEditable(false);
		output.setBackground(new Color(255,230,255));
		output.setFont(new Font("宋体",Font.PLAIN,20));
		jtp.add("Output",new JScrollPane(output));
		
		this.add(jtp);
		
		textarea = new UnwrapTextPane();
		textarea.setBackground(new Color(255,255,230));
		textarea.addMouseListener(this);
		textarea.addKeyListener(this);;
		this.textarea.setFont(new Font("Courier New",Font.PLAIN,16));
		
		textAreaSP = new JScrollPane(textarea);
		textAreaSP.setBounds(2,2,742,495);
		textAreaSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		textAreaSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
		
		jl=new JList();
		String[] line_num = new String[Option.INST_MAX_NUM];
		for(int i = 0;i<Option.INST_MAX_NUM;i++){
			line_num[i] = /*""+(i+1)+"/"+*/Integer.toHexString(i+1)+" ";
		}
		jl.setListData(line_num);
		jl.setFixedCellHeight(19);
		jl.setFixedCellWidth(30);
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MyCellRenderer render = new MyCellRenderer();
		jl.setCellRenderer(render);
		textAreaSP.setRowHeaderView(jl);
		this.add(textAreaSP);
		
		
		
		
		
		showout_panel = new JPanel();
		showout_panel.setBounds(745,2, 257, 516);
		showout_panel.setLayout(null);	
		
		
		JPanel cyclepanel  = new JPanel();
		cyclepanel.setBounds(2,2, 253, 310);
		cyclepanel.setLayout(null);
		
		JTabbedPane register_panel = new JTabbedPane();
		register_panel.setBounds(2,324,253,167);
		
		JLabel datawrite_label = new JLabel("Data write cycles :");
		JLabel dataread_label = new JLabel(" Data read cycles :");
		JLabel insread_label = new JLabel("   Inst read cycles :");
		this.datareadcycles = new JTextField(""+MipsSimEnv.dataMemUnit.readNeedCycles);
		this.datawritecycles = new JTextField(""+MipsSimEnv.dataMemUnit.writeNeedCycles);
		this.instreadcycles = new JTextField(""+MipsSimEnv.instMemUnit.readNeedCycles);
		this.yanshicao_box = new JCheckBox("使用延迟槽");
		this.yanshicao_box.setSelected(Option.USE_YANCHICAO);
		
		datawrite_label.setBounds(20,10,150,30);
		cyclepanel.add(datawrite_label);
		dataread_label.setBounds(20,40,150,30);
		cyclepanel.add(dataread_label);
		insread_label.setBounds(20,70,150,30);
		cyclepanel.add(insread_label);
		this.datareadcycles.setBounds(140,45,100,20);
		cyclepanel.add(datareadcycles);
		this.datawritecycles.setBounds(140,15,100,20);
		cyclepanel.add(datawritecycles);
		this.instreadcycles.setBounds(140,75,100,20);
		cyclepanel.add(instreadcycles);
		this.yanshicao_box.setBounds(100,100,100,30);
		cyclepanel.add(this.yanshicao_box);
		
		colume = new String[3];
		colume[0] = new String("Name");
		colume[1] = new String("in");
		colume[2] = new String("out");
		
		String [][] value = new String[2][3];
		for(int i = 0;i<2;i++){
			value[i][1] ="0";
			value[i][2] ="0";
		}
		value[0][0] = new String("IP");value[0][1] = new String("0");
		value[1][0] = new String("IR");value[1][1] = new String("0");
		ifid = new JTable(value,colume);
		ifid.getTableHeader().setReorderingAllowed(false); 
		ifid.setEnabled(false);
		register_panel.add("IF/ID",new JScrollPane(ifid));
		value = new String[16][3];
		for(int i = 0;i<16;i++){
			value[i][1] ="0";
			value[i][2] ="0";
		}
		value[0][0] = "IP";value[1][0] = "IR";value[2][0] = "Reg1Data";
		value[3][0] = "Reg2Data";value[4][0] = "Imm";
		value[5][0] = "InstRs";value[6][0] = "InstRt";value[7][0] = "InstRd";
		value[8][0] = "regDst"; value[9][0] = "aluOp";value[10][0] = "aluSrc";
		value[11][0] = "memRead";value[12][0] = "memWrite";
		value[13][0] = "regWrite";value[14][0] = "memToReg";value[15][0] = "halt";
		idexe = new JTable(value,colume);
		idexe.getTableHeader().setReorderingAllowed(false); 
		idexe.setEnabled(false);
		register_panel.add("ID/EXE",new JScrollPane(idexe));
		value = new String[10][3];
		for(int i = 0;i<10;i++){
			value[i][1] ="0";
			value[i][2] ="0";
		}
		value[0][0] = "IP";value[1][0] = "IR";value[2][0] = "ALURes";
		value[3][0] = "Reg2Data";value[4][0] = "InstRw";
		value[5][0] = "memRead";value[6][0] = "memWrite";
		value[7][0] = "regWrite";value[8][0] = "memToReg";value[9][0] = "halt";
		exemem = new JTable(value,colume);
		exemem.getTableHeader().setReorderingAllowed(false); 
		exemem.setEnabled(false);
		register_panel.add("EXE/MEM",new JScrollPane(exemem));
		value = new String[8][3];
		for(int i = 0;i<8;i++){
			value[i][1] ="0";
			value[i][2] ="0";
		}
		value[0][0] = "IP";value[1][0] = "IR";value[2][0] = "MemData";
		value[3][0] = "ALURes";value[4][0] = "InstRw";
		value[5][0] = "regWrite";value[6][0] = "memToReg";value[7][0] = "halt";
		memwb = new JTable(value,colume);
		memwb.getTableHeader().setReorderingAllowed(false); 
		memwb.setEnabled(false);
		register_panel.add("MEM/WB",new JScrollPane(memwb));
		
		JLabel label_cycles_count = new JLabel("         Cycles Count :");
		JLabel label_stall_count = new JLabel("             Stall Count :");
		JLabel label_forward_count = new JLabel("      Forward Count :");
		JLabel label_inst_count = new JLabel("               Inst Count :");
		JLabel label_instReadMem_count = new JLabel("InstReadMem Count :");
		JLabel label_instWriteMem_count = new JLabel("InstWriteMen Count :");
		this.cycles_count_field = new JTextField("0");
		this.cycles_count_field.setEditable(false);
		this.stall_count_field = new JTextField("0");
		this.stall_count_field.setEditable(false);
		this.forward_count_field = new JTextField("0");
		this.forward_count_field.setEditable(false);
		this.inst_count_field = new JTextField("0");
		this.inst_count_field.setEditable(false);
		this.instReadMem_count_field = new JTextField("0");
		this.instReadMem_count_field.setEditable(false);
		this.instWriteMem_count_field = new JTextField("0");
		this.instWriteMem_count_field.setEditable(false);
		label_cycles_count.setBounds(20,130,150,30);
		cyclepanel.add(label_cycles_count);
		this.cycles_count_field.setBounds(140,135,100,20);
		cyclepanel.add(cycles_count_field);
		label_stall_count.setBounds(20,160,150,30);
		cyclepanel.add(label_stall_count);
		this.stall_count_field.setBounds(140,165,100,20);
		cyclepanel.add(stall_count_field);
		
		label_forward_count.setBounds(20,190,150,30);
		cyclepanel.add(label_forward_count);
		this.forward_count_field.setBounds(140,195,100,20);
		cyclepanel.add(forward_count_field);
		label_inst_count.setBounds(20,220,150,30);
		cyclepanel.add(label_inst_count);
		this.inst_count_field.setBounds(140,225,100,20);
		cyclepanel.add(inst_count_field);
		label_instReadMem_count.setBounds(8,250,150,30);
		cyclepanel.add(label_instReadMem_count);
		this.instReadMem_count_field.setBounds(140,255,100,20);
		cyclepanel.add(instReadMem_count_field);
		label_instWriteMem_count.setBounds(10,280,150,30);
		cyclepanel.add(label_instWriteMem_count);
		this.instWriteMem_count_field.setBounds(140,285,100,20);
		cyclepanel.add(instWriteMem_count_field);
		
		
		showout_panel.add(register_panel);
		showout_panel.add(cyclepanel);

        this.add(showout_panel);
        
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        mips_file = new Mips_file(null,this);
        file_in_binary = new File_in_binary(this);
        debug = false;
        this.complier = new Complier(this);
        
    	this.setTitle("MipSim(Untitled.s)");
    	this.refresh_text();
    	
    	register_changed = new boolean[4][8];
    	for (int i = 0;i<4;i++){
    		for(int j = 0;j<8;j++){
    			register_changed[i][j] = false;
    		}
    	}
    	memory_changed = new boolean[17][16];
    	for (int i = 0;i<17;i++){
    		for(int j = 0;j<16;j++){
    			memory_changed[i][j] = false;
    		}
    	}
    	
    	addComponentListener(new   ComponentAdapter()   {   
            public   void   componentResized(ComponentEvent   e)   { 
            	if(e.getID() == ComponentEvent.COMPONENT_RESIZED){
                	Rectangle b = (e.getSource()!=null
              		       ? ((Component)e.getSource()).getBounds()
              		       : null);
                	int jTabHeight = 20;
                	int newWidth = b.width - showout_panel.getBounds().width- 18;
                	int newHeight = b.height - jtp.getBounds().height - mipsmenubar.getHeight() - jTabHeight - 20;
                	if(newWidth<0)
                		newWidth = 0;
                	if(newHeight<0)
                		newHeight = 0;
                	textAreaSP.setBounds(2, 2, newWidth, newHeight);
                	
                	Rectangle tb = jtp.getBounds();
                	tb.width = b.width -18;
                	tb.y = 2 + newHeight;
                	jtp.setBounds(tb);
                	
                	tb = showout_panel.getBounds();
                	tb.height = newHeight;
                	tb.x = 2 + newWidth;
                	showout_panel.setBounds(tb);
                	
                	textAreaSP.updateUI();
                	jtp.updateUI();
                	showout_panel.updateUI();
            	}
            }   
        });  
    	
	}
	
	/**
	 * 更新textarea中显示内容，当打开，新建，关闭文件时调用
	 * debug为false时从mips_file更新到textarea中
	 * debug为true时从file_in_binary更新到textarea中
	 */
	public void refresh_text(){

		this.textarea.setText("");
		
		if(debug  == false){
			if(mips_file == null){
				return;
			}
			String str = "";
			for(int i = 0;i<mips_file.getLinenum();i++){
				String _s = mips_file.getSentence(i);
				str = str + _s + "\n";
				//print_command(_s);
				//this.addDocs("\n",Color.black,false);
			}
			this.textarea.setText(str);
			lastColorString = "";
			color_text();
			this.textarea.select(0, 0);
		}else{
			if(file_in_binary == null){
				return;
			}
			int ifIndex = -1, idIndex = -1,exIndex = -1,meIndex = -1,wbIndex = -1;
			for(int i = 0;i<file_in_binary.commandsize;i++){
				
				if(i == MipsSimEnv.ifid.in.ip/4-1){
					ifIndex = textarea.getDocument().getLength();
					this.addDocs(" IF->",Color.blue,false);
				}else if(i == MipsSimEnv.ifid.out.ip/4-1){
					idIndex = textarea.getDocument().getLength();
					this.addDocs(" ID->",Color.green,false);
				}else if(i == MipsSimEnv.idex.out.ip/4-1){
					exIndex = textarea.getDocument().getLength();
					this.addDocs("EXE->",Color.MAGENTA,false);
				}else if(i == MipsSimEnv.exmem.out.ip/4-1){
					meIndex = textarea.getDocument().getLength();
					this.addDocs("MEM->",Color.red,false);
				}else if(i == MipsSimEnv.memwb.out.ip/4-1){
					wbIndex = textarea.getDocument().getLength();
					this.addDocs(" WB->",Color.pink,false);
				}else{
					this.addDocs("     ",Color.black,false);
				}
				
				
				this.addDocs("\t",Color.black,false);
				
				this.addDocs("["+String.format("%05x", i*4)+"]",Color.DARK_GRAY,false);
				
				this.addDocs("  ",Color.gray,false);
				
				byte [] temp_byte = file_in_binary.commands[i].getCommand();
				for(int j= 0;j<4;j++){
					String _s = String.format("%02x",temp_byte[3-j]);
					this.addDocs(_s,Color.black,false);
				}
				
				this.addDocs("  ",Color.black,false);
				
				this.print_command(file_in_binary.commands[i].string_command);
				
				String note = file_in_binary.commands[i].getNote();
				if(note.length()>0)
					this.addDocs("\t;"+ note, Color.lightGray, false);
				this.addDocs("\n",Color.MAGENTA,false);
			}
			
			int index = ifIndex >0 ?ifIndex:(idIndex>0?idIndex : (exIndex>0?exIndex : ( meIndex>0?meIndex : (wbIndex>0?wbIndex : 0  )  )  ) );

			textarea.select(index, index);
		}

		
	}
	
	public void save(File file){
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(this.textarea.getText(), 0, this.textarea.getText().length());
			bw.close();
		}catch(Exception e){}
	}
	
	/**
	 * debug之前需要
	 *1)保存文件到磁盘，并更新mips_file;
	 *2)编译文件；
	 *3)把编译后生成的二进制文件读入到File_in_binary中
	 */
	public void startdebug(){
		this.jl.setSelectedIndex(0);
		boolean legal = mips_file.save_from_textarea(textarea);//1步
		if(!legal) return;
		
		file_in_binary.commandsize = 0;
		int result = this.complier.compiling();//编译
		this.jtp.setSelectedIndex(2);
		if(result != 0){
			int errorline = this.complier.errLine+1;
			output.setText("Error at Line 0x"+Integer.toHexString(errorline)+" : ");
			switch(result){
			case 1:output.append("该label之前已被定义！");break;
			case 2:output.append("操作数错误！");break;
			case 3:output.append("寄存器名称错误！");break;
			case 4:output.append("找不到对应的label！");break;
			case 5:output.append("label不能为空！");break;
			case 6:output.append("立即数越界！");break;
			case 7:output.append("立即数格式错误！");break;
			case 8:output.append("label中不能含有空格！");break;
			case 10:output.append("找不到操作码！");break;
			case 11:output.append("指令格式错误！");break;
			case 12:output.append("跳转指令的目标在代码范围之外");break;
			}
			return;
		}
		output.setText("Success!");
		MipsSimEnv.instMem.init(file_in_binary);
		
		debug = true;
		MipsRun.PROG_DEBUG = true;
		
		refresh();
		this.textarea.setEditable(false);
		
		this.mipsmenubar.debugStop.setEnabled(true);
		this.mipsmenubar.debugStep.setEnabled(true);
		this.mipsmenubar.debugContinue.setEnabled(true);
		this.mipsmenubar.debugAddB.setEnabled(true);
		this.mipsmenubar.debugDelB.setEnabled(true);
		this.mipsmenubar.debugSetBMA.setEnabled(true);
		this.mipsmenubar.debugSetM.setEnabled(true);
		this.mipsmenubar.debugSetR.setEnabled(true);
		this.mipsmenubar.debugStart.setEnabled(false);
		MipsSimEnv.dataMemUnit.readNeedCycles = Integer.parseInt(this.datareadcycles.getText()) ;
		MipsSimEnv.dataMemUnit.writeNeedCycles = Integer.parseInt(this.datawritecycles.getText());
		MipsSimEnv.instMemUnit.readNeedCycles = Integer.parseInt(this.instreadcycles.getText());
		Option.USE_YANCHICAO = this.yanshicao_box.isSelected();
		this.mipsmenubar.file.setEnabled(false);
		this.datawritecycles.setEditable(false);
		this.datareadcycles.setEditable(false);
		this.instreadcycles.setEditable(false);
		this.yanshicao_box.setEnabled(false);
		//
	}
	
	public void stopdebug(){
		debug = false;
		MipsRun.PROG_DEBUG = false;
		MipsRun.reset();
		
		refresh();
		refresh();//清除颜色
		
		this.textarea.setEditable(true);
		
		this.mipsmenubar.debugStop.setEnabled(false);
		this.mipsmenubar.debugStep.setEnabled(false);
		this.mipsmenubar.debugContinue.setEnabled(false);
		this.mipsmenubar.debugAddB.setEnabled(false);
		this.mipsmenubar.debugDelB.setEnabled(false);
		this.mipsmenubar.debugSetBMA.setEnabled(false);
		this.mipsmenubar.debugSetM.setEnabled(false);
		this.mipsmenubar.debugSetR.setEnabled(false);
		this.mipsmenubar.debugStart.setEnabled(true);
		this.mipsmenubar.file.setEnabled(true);
		this.datawritecycles.setEditable(true);
		this.datareadcycles.setEditable(true);
		this.instreadcycles.setEditable(true);
		this.yanshicao_box.setEnabled(true);
		//
	}
	
	public int stepdebug(){
		int res = MipsRun.singleStep();
		refresh();
		//uncompleted!!!注意考虑边缘情况

		if(res == MipsRun.END_HALT){
			output.append("\n系统已经停机.");
		}
		else if(res == MipsRun.END_BREAKPOINT){
			output.append("\n到达断点.");
		}
		return res;
	}
	

	public long protectColock = 0;//保护CLOCK，当CPU执行次数超过Option.CLOCK_MAX_NUM时，触发。
	public void continuedebug(){
		protectColock= 0;
		int res;
		while( true){
			res = MipsRun.singleStep();
			if(res != MipsRun.END_NORMAL){
				break;
			}
			protectColock ++ ;
			
			if(protectColock>Option.CLOCK_MAX_NUM){
				break;
			}
		}

		refresh();
		
		if(res == MipsRun.END_HALT){
			output.append("\n系统已经停机.");
		}
		else if(res == MipsRun.END_BREAKPOINT){
			output.append("\n到达断点.");
		}
		else if(protectColock>Option.CLOCK_MAX_NUM){
			output.append("\n已经执行了"+Option.CLOCK_MAX_NUM+"次，程序可能进入了死循环。");
		}
		//uncompleted!!!注意考虑边缘情况
	}
	
	public void refresh(){
		this.refresh_text();
		this.refreshDataMemory();
		this.refreshRegister();
		this.refresh_output();
		this.refreshshowout();
	}
	
	/**
	 * 刷新寄存器内容以便显示出来
	 */
	public void refreshRegister(){
		//!!!!!!!!!!!!!!!!!!!!!!!!uncompleted!使用registers.setValueAt()方法
		for(int i = 0;i<32;i++){
			int value = MipsSimEnv.reg.regs[i];
			String aValue =String.format("0x%08x", value);
			if(i < 8){
				if(!aValue.equals((String)registers.getValueAt(i, 1))){
					register_changed[0][i] = true;
				}else{
					register_changed[0][i] = false;
				}
				registers.setValueAt(aValue, i, 1);
			}else if(i<16){
				if(!aValue.equals((String)registers.getValueAt(i-8, 3))){
					register_changed[1][i-8] = true;
				}else{
					register_changed[1][i-8] = false;
				}
				registers.setValueAt(aValue, i-8, 3);
			}else if(i<24){
				if(!aValue.equals((String)registers.getValueAt(i-16, 5))){
					register_changed[2][i-16] = true;
				}else{
					register_changed[2][i-16] = false;
				}
				registers.setValueAt(aValue, i-16, 5);
			}else{
				if(!aValue.equals((String)registers.getValueAt( i-24, 7))){
					register_changed[3][i-24] = true;
				}else{
					register_changed[3][i-24] = false;
				}
				registers.setValueAt(aValue, i-24, 7);
			}
		}

		this.registers.repaint();
	}
	
	public void refreshshowout(){
		this.cycles_count_field.setText(""+MipsSimEnv.clock);
		this.stall_count_field.setText(""+MipsSimEnv.stall_count);
		this.forward_count_field.setText(""+MipsSimEnv.forward_count);
		this.inst_count_field.setText(""+MipsSimEnv.inst_count);
		instReadMem_count_field.setText(""+MipsSimEnv.instReadMem_count);
		instWriteMem_count_field.setText(""+MipsSimEnv.instWriteMem_count);
		this.ifid.setValueAt("0x"+Integer.toHexString(MipsSimEnv.ifid.in.ip), 0, 1);this.ifid.setValueAt("0x"+Integer.toHexString(MipsSimEnv.ifid.out.ip), 0, 2);
		this.ifid.setValueAt("0x"+Integer.toHexString(MipsSimEnv.ifid.in.ir), 1, 1);this.ifid.setValueAt("0x"+Integer.toHexString(MipsSimEnv.ifid.out.ir), 1, 2);
		
		this.idexe.setValueAt("0x"+Integer.toHexString(MipsSimEnv.idex.in.ip), 0, 1);this.idexe.setValueAt("0x"+Integer.toHexString(MipsSimEnv.idex.out.ip), 0, 2);
		this.idexe.setValueAt("0x"+Integer.toHexString(MipsSimEnv.idex.in.ir), 1, 1);this.idexe.setValueAt("0x"+Integer.toHexString(MipsSimEnv.idex.out.ir), 1, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.reg1Data, 2, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.reg1Data, 2, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.reg2Data, 3, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.reg2Data, 3, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.imm, 4, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.imm, 4, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.instRs, 5, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.instRs, 5, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.instRt, 6, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.instRt, 6, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.instRd, 7, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.instRd, 7, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.ex.regDst, 8, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.ex.regDst, 8, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.ex.aluOp, 9, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.ex.aluOp, 9, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.ex.aluSrc, 10, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.ex.aluSrc, 10, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.mem.memRead, 11, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.mem.memRead, 11, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.mem.memWrite, 12, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.mem.memWrite, 12, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.wb.regWrite, 13, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.wb.regWrite, 13, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.wb.memToReg, 14, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.wb.memToReg, 14, 2);
		this.idexe.setValueAt(""+MipsSimEnv.idex.in.wb.halt, 15, 1);this.idexe.setValueAt(""+MipsSimEnv.idex.out.wb.halt, 15, 2);
		
		this.exemem.setValueAt("0x"+Integer.toHexString(MipsSimEnv.exmem.in.ip), 0, 1);this.exemem.setValueAt("0x"+Integer.toHexString(MipsSimEnv.exmem.out.ip), 0, 2);
		this.exemem.setValueAt("0x"+Integer.toHexString(MipsSimEnv.exmem.in.ir), 1, 1);this.exemem.setValueAt("0x"+Integer.toHexString(MipsSimEnv.exmem.out.ir), 1, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.aluRes, 2, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.aluRes, 2, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.reg2Data, 3, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.reg2Data, 3, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.instRw, 4, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.instRw, 4, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.mem.memRead, 5, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.mem.memRead, 5, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.mem.memWrite, 6, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.mem.memWrite, 6, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.wb.regWrite, 7, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.wb.regWrite, 7, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.wb.memToReg, 8, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.wb.memToReg, 8, 2);
		this.exemem.setValueAt(""+MipsSimEnv.exmem.in.wb.halt, 9, 1);this.exemem.setValueAt(""+MipsSimEnv.exmem.out.wb.halt, 9, 2);
		
		this.memwb.setValueAt("0x"+Integer.toHexString(MipsSimEnv.memwb.in.ip), 0, 1);this.memwb.setValueAt("0x"+Integer.toHexString(MipsSimEnv.memwb.out.ip), 0, 2);
		this.memwb.setValueAt("0x"+Integer.toHexString(MipsSimEnv.memwb.in.ir), 1, 1);this.memwb.setValueAt("0x"+Integer.toHexString(MipsSimEnv.memwb.out.ir), 1, 2);
		this.memwb.setValueAt(""+MipsSimEnv.memwb.in.memData,2, 1);this.memwb.setValueAt(""+MipsSimEnv.memwb.in.memData, 2, 1);
		this.memwb.setValueAt(""+MipsSimEnv.memwb.in.aluRes, 3, 1);this.memwb.setValueAt(""+MipsSimEnv.memwb.out.aluRes, 3, 2);
		this.memwb.setValueAt(""+MipsSimEnv.memwb.in.instRw, 4, 1);this.memwb.setValueAt(""+MipsSimEnv.memwb.out.instRw, 4, 2);
		this.memwb.setValueAt(""+MipsSimEnv.memwb.in.wb.regWrite, 5, 1);this.memwb.setValueAt(""+MipsSimEnv.memwb.out.wb.regWrite, 5, 2);
		this.memwb.setValueAt(""+MipsSimEnv.memwb.in.wb.memToReg, 6, 1);this.memwb.setValueAt(""+MipsSimEnv.memwb.out.wb.memToReg, 6, 2);
		this.memwb.setValueAt(""+MipsSimEnv.memwb.in.wb.halt, 7, 1);this.memwb.setValueAt(""+MipsSimEnv.memwb.out.wb.halt, 7, 2);
	}
	/**
	 * 刷新要显示的内存的内容
	 */
	public void refreshDataMemory(){
		//!!!!!!!!!!!!!!!!!!!!!!!!uncompleted!使用memory.setValueAt()方法，注意地址要加上base_memory_address
		for(int i = 0;i<16;i++){
			dataMemory.setValueAt(String.format("0x%04x",i*16+this.base_memory_address), i, 0);
			for(int j = 1;j<=16;j++){
				if(!String.format("%02x",MipsSimEnv.dataMem.readByte(i*16+this.base_memory_address+j-1)).equals(dataMemory.getValueAt(i, j))){
					memory_changed[j][i] = true;
				}else{
					memory_changed[j][i] = false;
				}
				dataMemory.setValueAt(String.format("%02x",MipsSimEnv.dataMem.readByte(i*16+this.base_memory_address+j-1) ), i, j);
			}
		}
		this.dataMemory.repaint();
	}
	
	public void refresh_output(){
		
		for(String str:MipsSimEnv.errmsgs){
			output.append("\nError:"+str);
		}
	}
	
	private String lastColorString = "";
	public void color_text(){
		StyledDocument doc = (StyledDocument)textarea.getDocument();
		String content = textarea.getText();
		content = content.replace("\r", "");

		int cPos1 = 0,cPos2 = 0;
		
		int count = content.length();
		if(count > lastColorString.length())
			count = lastColorString.length();
		while(cPos1 < count && content.charAt(cPos1) == lastColorString.charAt(cPos1))
			++cPos1;
		
		if(cPos1>=content.length())
			return;
		
		while(cPos1>=0 && content.charAt(cPos1)!='\n')
			--cPos1;
		
		++cPos1;
				
		lastColorString = content; 
		while(true){
			if(cPos1<0 || cPos1>=content.length())
				break;
			cPos2 = content.indexOf('\n', cPos1);
			if(cPos2 < 0 ){
				cPos2 = content.length();
			}
			String _s = content.substring(cPos1,cPos2);
			//分出命令和注释
			String _cmd=_s,_note="";
			int pos = _s.indexOf(';');
			if(pos>=0){
				_cmd = _s.substring(0, pos);
				_note = _s.substring(pos);
			}
			
			AttributeSet set =getAttributeSet(Color.black,false,false);
			doc.setCharacterAttributes(cPos1, _cmd.length(), set, true);
			
			pos = 0;
			String [] _ss = _cmd.split("[\\s,()]");
			String p = "";
			for(int j = 0;j<_ss.length;j++){
				String ts = _ss[j].trim();
				if(ts.length() == 0)
					continue;

				while(pos<_cmd.length() && _cmd.charAt(pos)!= ts.charAt(0)){
					++pos;
				}
				
				
				if(is_command(ts)){
					set = getAttributeSet(Color.blue,false,false);
				}else if(is_register(ts)){
					set = getAttributeSet(Color.MAGENTA,false,false);
				}else if(is_num(ts)){
					set = getAttributeSet(Color.orange,false,false);
				}else{
					set = getAttributeSet(Color.black,false,false);
				}			
				
				doc.setCharacterAttributes(cPos1+pos, _ss[j].length(), set, true);
				
				pos += _ss[j].length();
			}
			
			set = getAttributeSet(Color.gray,false,false);
			
			doc.setCharacterAttributes(cPos1+_cmd.length(), _note.length(), set, true);
			
			cPos1 = cPos2 +1;
		}
		
	}
	
	public void print_command(String _s){
		//分出命令和注释
		String _cmd=_s,_note="";
		int pos = _s.indexOf(';');
		if(pos>=0){
			_cmd = _s.substring(0, pos);
			_note = _s.substring(pos);
		}
		
		pos = 0;
		String [] _ss = _cmd.split("[\\s,()]");
		String p = "";
		for(int j = 0;j<_ss.length;j++){
			String ts = _ss[j].trim();
			if(ts.length() == 0)
				continue;
			
			int tPos =pos;
			while(pos<_cmd.length() && _cmd.charAt(pos)!= ts.charAt(0)){
				++pos;
			}
			if(pos>tPos){
				this.addDocs(_cmd.substring(tPos,pos), Color.black, false);
				p = p +_cmd.substring(tPos,pos);
			}
			
			if(is_command(ts)){
				this.addDocs(ts,Color.blue,false);
			}else if(is_register(ts)){
				this.addDocs(ts,Color.MAGENTA,false);
			}else if(is_num(ts)){
				this.addDocs(ts,Color.orange,false);
			}else{
				this.addDocs(ts,Color.black,false);
			}

			p = p +ts;
			
			
			pos += _ss[j].length();
		}
		
		if(pos<_cmd.length())
			this.addDocs(_cmd.substring(pos), Color.black, false);
		
		this.addDocs("\t"+_note, Color.gray, false);
	}
	
	
	
	public boolean is_register(String ss){
		String s = ss.toUpperCase();
		return 
		s.equals("R1")||
		s.equals("R2")||
		s.equals("R3")||
		s.equals("R4")||
		s.equals("R5")||
		s.equals("R6")||
		s.equals("R7")||
		s.equals("R8")||
		s.equals("R9")||
		s.equals("R10")||
		s.equals("R11")||
		s.equals("R12")||
		s.equals("R13")||
		s.equals("R14")||
		s.equals("R15")||
		s.equals("R16")||
		s.equals("R17")||
		s.equals("R18")||
		s.equals("R19")||
		s.equals("R20")||
		s.equals("R21")||
		s.equals("R22")||
		s.equals("R23")||
		s.equals("R24")||
		s.equals("R25")||
		s.equals("R26")||
		s.equals("R27")||
		s.equals("R28")||
		s.equals("R29")||
		s.equals("R30")||
		s.equals("R31")||
		s.equals("R0");
	}
	
	public boolean is_command(String ss){
		String s = ss.toUpperCase();
		return 
		s.equals("LW")||
		s.equals("SW")||
		s.equals("J")||
		s.equals("BEQZ")||
		s.equals("BNEZ")||
		s.equals("ADDI")||
		s.equals("ADDUI")||
		s.equals("SUBI")||
		s.equals("SUBUI")||
		s.equals("ANDI")||
		s.equals("ORI")||
		s.equals("XORI")||
		s.equals("SLLI")||
		s.equals("SRLI")||
		s.equals("SRAI")||
		s.equals("SEQI")||
		s.equals("SNEI")||
		s.equals("SLTI")||
		s.equals("SGTI")||
		s.equals("SLEI")||
		s.equals("SLL")||
		s.equals("SRL")||
		s.equals("SRA")||
		s.equals("ADD")||
		s.equals("ADDU")||
		s.equals("SUB")||
		s.equals("SUBU")||
		s.equals("AND")||
		s.equals("OR")||
		s.equals("XOR")||
		s.equals("SEQ")||
		s.equals("SNE")||
		s.equals("SLT")||
		s.equals("SGT")||
		s.equals("SLE")||
		s.equals("MULT")||
		s.equals("DIV")||
		s.equals("MULTU")||
		s.equals("DIVU")||
		s.equals("HALT") ||
		s.equals("NOP");
	}
	
	public boolean is_num(String ss){
		try{
			Integer.decode(ss);
		}catch(Exception e){return false;}
		return true;
	}
	
	public   void   insert(String   str,   javax.swing.text.AttributeSet   attrSet)   {   
        Document   doc   =   textarea.getDocument();   
        try   {   
            doc.insertString(doc.getLength(),   str,   attrSet);  
        }   
        catch   (Exception   e)   {   
            System.out.println("BadLocationException:   "   +   e);   
        }   
    }   
  
    public   void   addDocs(String   str,Color   col,boolean   bold)   {   
        SimpleAttributeSet   attrSet   =   new   SimpleAttributeSet();   
        StyleConstants.setForeground(attrSet,   col);   
        //颜色   
        if(bold==true){   
            StyleConstants.setBold(attrSet,   true);   
        }//字体类型   
        insert(str,   attrSet);   
    }   

    public AttributeSet getAttributeSet(Color   col,boolean   bold,boolean italic){
    	SimpleAttributeSet   attrSet   =   new   SimpleAttributeSet();   
        StyleConstants.setForeground(attrSet,   col);   
        //颜色   
        if(bold==true){   
            StyleConstants.setBold(attrSet,   true);   
        }//字体类型   
        if(italic){
        	StyleConstants.setItalic(attrSet, true);
        }
        return attrSet;
    }
	
	
	
	public void makeFace_register(JTable table) {
		
            table.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRenderer(0,1));
            table.getColumnModel().getColumn(1).setCellRenderer(new MyTableCellRenderer(0,1));
            table.getColumnModel().getColumn(2).setCellRenderer(new MyTableCellRenderer(1,1));
            table.getColumnModel().getColumn(3).setCellRenderer(new MyTableCellRenderer(1,1));
            table.getColumnModel().getColumn(4).setCellRenderer(new MyTableCellRenderer(2,1));
            table.getColumnModel().getColumn(5).setCellRenderer(new MyTableCellRenderer(2,1));
            table.getColumnModel().getColumn(6).setCellRenderer(new MyTableCellRenderer(3,1));
            table.getColumnModel().getColumn(7).setCellRenderer(new MyTableCellRenderer(3,1));
	}
	
	public void makeFace_memory(JTable table) {
	    for(int i = 0;i<table.getColumnCount();i++){
	    	table.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRenderer(i,2));
	    }
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource() == this.textarea){
			if(!debug) return;
			int index = this.textarea.getSelectionStart();
			int sum = 0,i = 0;
       	 	if(index < 0) return;
       	 	while(sum + file_in_binary.commands[i].size < index){
       		 sum +=file_in_binary.commands[i].size+1;
       		 i++;
       		 if(i>=file_in_binary.commandsize) return;
       	 	}
       		 if(i>file_in_binary.commandsize) return;
       		 this.jl.setSelectedIndex(i);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!debug){
			color_text();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}

class MyTableCellRenderer extends DefaultTableCellRenderer{
	public int col;
	/**
	 * 为1表示fresh_registers调用为2表示fresh_memory调用
	 */
	public int type;
	public MyTableCellRenderer(int i,int type){
		super();
		col = i;
		this.type = type;
	}
  public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
  {
	  setHorizontalAlignment(SwingConstants.CENTER);
	  if(this.type == 1){
		  if(row%2 == 0){
			  setBackground(new Color(255,231,206)); //设置奇数行底色
		  }
		  else if(row%2 == 1){
			  setBackground(new Color(255,206,231));//设置偶数行底色
		  }
		  
		  if(column %2 ==1){
			  if(Mipsframe.register_changed[col][row]){
				  setForeground(Color.RED);
			  }
			  else{
				  setForeground(Color.BLACK);
			  }
		  }
		  
		  return super.getTableCellRendererComponent(table, value,
				  isSelected, hasFocus, row, column); 
	 }else{
		 if(row%2 == 0){
			 setBackground(new Color(206,231,255)); //设置奇数行底色
		 }
	      else if(row%2 == 1){
	    	  setBackground(new Color(231,206,255));  //设置偶数行底色
	      }
		 if(column %2 == 1){
	 		 if(Mipsframe.memory_changed[col][row]){
	 			 setForeground(Color.RED);
	 		 }
			  else{
				  setForeground(Color.BLACK);
			  }			 
		 }
	       return super.getTableCellRendererComponent(table, value,
	            isSelected, hasFocus, row, column); 
		  }
  }
};


class MyMemoryTableModel extends DefaultTableModel {
	
	public MyMemoryTableModel(Object[][]data,Object[]cols){
		super(data,cols);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column>0;
	}	
	
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		if(column == 0){
			super.setValueAt(value, row, column);
			return;
		}
		
		String newValue;
		
		try{
			String byteValue = value.toString().length()>2? value.toString().substring(value.toString().length()-2) : value.toString();
			
			byte num = Integer.valueOf(byteValue, 16).byteValue();
			newValue = String.format("%02x", num);
			int addr = Integer.decode(getValueAt(row, 0).toString()) + (column - 1);
			
			if(addr<0){
				int i;
				i=1;
			}
			MipsSimEnv.dataMem.writeByte(addr, num);
			
			super.setValueAt(newValue, row, column);
		}
		catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		
	}
}



class MyCellRenderer extends JLabel implements ListCellRenderer {
    public MyCellRenderer() {
        setOpaque(true);
    }
    public Component getListCellRendererComponent(
        JList list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus)
    {
  
        setText(value.toString());
        setHorizontalAlignment(JLabel.RIGHT); 
        setVerticalAlignment(JLabel.BOTTOM);
        
        setBackground(isSelected ? new Color(255,206,231) : new Color(255,231,206));
   
        
        setForeground(Color.BLACK);
        if(Mipsframe.debug){
        	if(index < Mipsframe.file_in_binary.commandsize && Mipsframe.file_in_binary.commands[index].isbreakpoint()){
        		setForeground(Color.RED);
        	}
        }
        return this;
    }
}
