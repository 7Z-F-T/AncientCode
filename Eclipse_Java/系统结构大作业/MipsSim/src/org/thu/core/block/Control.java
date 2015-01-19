package org.thu.core.block;

import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;
import org.thu.core.enity.Inst;
import org.thu.core.enity.Instances;
import org.thu.core.latch.ControlEX;
import org.thu.core.latch.ControlMEM;
import org.thu.core.latch.ControlWB;


/**
 * 控制单元
 */
public class Control extends BasicBlock{	
	public int ir = 0;// 指令
	
	// ID或EX的控制线
	public ControlEX ex = new ControlEX();
	
	// MEM的控制线
	public ControlMEM mem = new ControlMEM();
	
	// WB的控制线
	public ControlWB wb = new ControlWB();

	public boolean IF_Flush;
	public boolean ID_Flush;
	public boolean EX_Flush;
	
	public boolean Jbranch;//J型指令跳转
	public boolean BBranch;//B型指令跳转
	public boolean Bneq; // B型指令是否是BNEQZ指令
	
	@Override
	public void update(long clock) {
		if(!shouldUpate(clock))
			return;
		this.clock = clock;

		ir = MipsSimEnv.ifid.out.ir;
		
		clear();
		
		Inst inst = new Inst(ir);

		if(inst.opcode == Instances.HALT.opcode){
			wb.halt = true;
		}
		else if(inst.opcode == Instances.LW.opcode){	
			ex.aluOp = ALUControl.ADD;
			ex.aluSrc = true;
			mem.memRead = true;
			wb.regWrite = true;
			wb.memToReg = true;
		}
		else if(inst.opcode == Instances.SW.opcode){		
			ex.aluOp = ALUControl.ADD;
			ex.aluSrc = true;
			mem.memWrite = true;
		}
		else if(inst.opcode == Instances.J.opcode){
			Jbranch = true;
			ID_Flush = true;
		}
		else if(inst.opcode == Instances.BEQZ.opcode){
			BBranch = true;
			Bneq = false;
			ID_Flush = true;
		}
		else if(inst.opcode == Instances.BNEZ.opcode){
			BBranch = true;
			Bneq = true;
			ID_Flush = true;
		}
		else if( (Instances.ADDI.opcode<=inst.opcode && inst.opcode<=Instances.XORI.opcode)
				||(Instances.SLLI.opcode<=inst.opcode && inst.opcode<=Instances.SLEI.opcode)){
			ex.aluSrc = true;
			wb.regWrite = true;
			int control = ALUControl.NONE;
			if(Instances.ADDI.opcode == inst.opcode){
				control = ALUControl.ADD;
			}
			else if(Instances.ADDUI.opcode == inst.opcode){
				control = ALUControl.ADDU;
			}
			else if(Instances.SUBI.opcode == inst.opcode){
				control = ALUControl.SUB;
			}
			else if(Instances.SUBUI.opcode == inst.opcode){
				control = ALUControl.SUBU;
			}
			else if(Instances.ANDI.opcode == inst.opcode){
				control = ALUControl.AND;
			}
			else if(Instances.ORI.opcode == inst.opcode){
				control = ALUControl.OR;
			}
			else if(Instances.XORI.opcode == inst.opcode){
				control = ALUControl.XOR;
			}
			else if(Instances.SLLI.opcode == inst.opcode){
				control = ALUControl.SLL;
			}
			else if(Instances.SRLI.opcode == inst.opcode){
				control = ALUControl.SRL;
			}
			else if(Instances.SRAI.opcode == inst.opcode){
				control = ALUControl.SRA;
			}
			else if(Instances.SEQI.opcode == inst.opcode){
				control = ALUControl.SEQ;
			}
			else if(Instances.SNEI.opcode == inst.opcode){
				control = ALUControl.SNE;
			}
			else if(Instances.SLTI.opcode == inst.opcode){
				control = ALUControl.SLT;
			}
			else if(Instances.SGTI.opcode == inst.opcode){
				control = ALUControl.SGT;
			}
			else if(Instances.SLEI.opcode == inst.opcode){
				control = ALUControl.SLE;
			}
			ex.aluOp = control;
		}
		else if(inst.opcode == 0 || inst.opcode == 1){ // SLL...
			ex.regDst = true;
			wb.regWrite = true;
			int control = ALUControl.NONE;
			
			if(inst.opcode == 0){
				if(Instances.SLL.func == inst.func){
					control = ALUControl.SLL;
				}
				else if(Instances.SRL.func == inst.func){
					control = ALUControl.SRL;
				}
				else if(Instances.SRA.func == inst.func){
					control = ALUControl.SRA;
				}
				else if(Instances.ADD.func == inst.func){
					control = ALUControl.ADD;
				}
				else if(Instances.ADDU.func == inst.func){
					control = ALUControl.ADDU;
				}
				else if(Instances.SUB.func == inst.func){
					control = ALUControl.SUB;
				}
				else if(Instances.SUBU.func == inst.func){
					control = ALUControl.SUBU;
				}
				else if(Instances.AND.func == inst.func){
					control = ALUControl.AND;
				}
				else if(Instances.OR.func == inst.func){
					control = ALUControl.OR;
				}
				else if(Instances.XOR.func == inst.func){
					control = ALUControl.XOR;
				}
				else if(Instances.SEQ.func == inst.func){
					control = ALUControl.SEQ;
				}
				else if(Instances.SNE.func == inst.func){
					control = ALUControl.SNE;
				}
				else if(Instances.SLT.func == inst.func){
					control = ALUControl.SLT;
				}
				else if(Instances.SGT.func == inst.func){
					control = ALUControl.SGT;
				}
				else if(Instances.SLE.func == inst.func){
					control = ALUControl.SLE;
				}
				else{
					ex.aluOp = ALUControl.NONE;
					ex.regDst = false;
					wb.regWrite = false;
				}
			}
			else{
				if(Instances.MULT.func == inst.func){
					control = ALUControl.MULT;
				}
				else if(Instances.DIV.func == inst.func){
					control = ALUControl.DIV;
				}
				else if(Instances.MULTU.func == inst.func){
					control = ALUControl.MULTU;
				}
				else if(Instances.DIVU.func == inst.func){
					control = ALUControl.DIVU;
				}
			}
			ex.aluOp = control;
		}
		else{
			MipsSimEnv.errmsgs.add("错误的指令0x"+Integer.toHexString(ir)+".");
			if(MipsRun.CODE_DEBUG){
				System.err.println("错误的指令0x"+Integer.toHexString(ir)+".");
			}
		}
	}
	
	public void clear(){
		ex.clear();
		mem.clear();
		wb.clear();
		IF_Flush = false;
		ID_Flush = false;
		EX_Flush = false;
		Jbranch = false;
		BBranch = false;
		Bneq = false;
	}
	
	public void dump(){
		System.out.print("控制器\t");
		System.out.print(
				"IF_Flush:" + IF_Flush
				+",ID_Flush:"+ID_Flush
				+",EX_Flush:"+EX_Flush
				+ ",Jbranch:"+Jbranch
				+",BBranch:"+BBranch
				+",Bneq"+Bneq);
		System.out.print(",");
		ex.dump();
		System.out.print(",");
		mem.dump();
		System.out.print(",");
		wb.dump();
		System.out.println();
	}
}
