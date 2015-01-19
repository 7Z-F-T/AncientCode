package org.thu.core.block;

import org.thu.core.MipsRun;
import org.thu.core.MipsSimEnv;
import org.thu.core.Option;
import org.thu.core.enity.Inst;
import org.thu.core.enity.Instances;


/**
 * ð�յ�Ԫ������ð�յĺ�����Ϊ��ֻҪ��Ҫ����ˮ�в���NOP��(������תָ��)������Ϊ1��ð�ա�
 */
public class Stall extends BasicBlock{
	/**
	 *  �Ƿ񱣳�PC��ֵ
	 */
	public boolean pcKeep; 
	/**
	 * ��ID�β�������
	 */
	public boolean ifFlush;
	
	/**
	 * ��ex�в�������
	 */
	public boolean idFlush;
	/**
	 * �Ƿ���mem�в�������
	 */
	public boolean exFlush;
	
	/**
	 * �Ƿ���wb�в�������
	 */
	public boolean memFlush;
	
	/**
	 *  �Ƿ񱣳�ID��ֵ
	 */
	public boolean idKeep;
	/**
	 * ����EX��ֵ
	 */
	public boolean exKeep;
	/**
	 * ����mem��ֵ
	 */
	public boolean memKeep;
	
	//����г�ͻ�������ȱ���
	
	public boolean hasStall;
	
	private String des;//ð������
	
	/*
	 * ��ˮ�ߴ������¼���ð�գ�<br/>
	 * 1. ���ڴ�Ȼ��ȡ�� <br/>
	 * 	  lw r2,20
	 *    add r1,r2,r3 
	 */
	@Override
	public void update(long clock) {
		// TODO Stall
		
		pcKeep = false;
		idKeep = false;
		exFlush = false;
		exKeep = false;
		idFlush = false;
		memKeep = false;
		memFlush = false;
		
		hasStall = false;
		des = "";
		if(!MipsSimEnv.instMemUnit.isReady()){ // ָ�û�ж�������EX�β���1�����ݣ�����ID�����ݣ�����ΪID�׶ο�������תָ��
			idKeep = true;
			idFlush = true;
			pcKeep = true;
			
			if(MipsRun.CODE_DEBUG){
				des = (">>>ð�գ�ָ���ȡ�ȴ���");
			}
			hasStall = true;
		}	
		
		if(!MipsSimEnv.dataMemUnit.isReady()){ // ��������ڴ滹û�гɹ���ȡ�����ݻ�д�����ݣ�����ͣ��
			memFlush = true;
			memKeep = true;
			//���Ժܸ���Ŷ
			pauseEx();
			if(MipsRun.CODE_DEBUG){
				des = (">>>ð�գ����ݶ�ȡ�ȴ���");
			}
			hasStall = true;
		}
		
		/*
		 * LW
		 * USE
		 */
		if(MipsSimEnv.idex.out.mem.memRead //id����һ��ָ����Ҫ��ȡ����
				&& MipsSimEnv.exmem.in.instRw!=0
				&&(
						MipsSimEnv.exmem.in.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs
						|| MipsSimEnv.exmem.in.instRw == new Inst(MipsSimEnv.ifid.out.ir).rt )
						){
			idFlush = true;
			idKeep = true;
			pcKeep = true;//��ͣPC
			if(MipsRun.CODE_DEBUG){
				des = (">>>ð�գ�LW-USE��");
			}
			hasStall = true;
		}
		
		
		/*
		 * 1. Write rx --ex
		 * 2. b rx  --id
		 * ��ex�в������ݣ�����id��ʹ��ָ��1����MEM�У�Ȼ��ͨ��ת����ʵ��
		 */
		if(MipsSimEnv.idex.out.wb.regWrite
				&& MipsSimEnv.exmem.in.instRw!=0
				&& (new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BEQZ.opcode
						|| new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BNEZ.opcode)
				&& MipsSimEnv.exmem.in.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs ){
			idFlush = true;
			idKeep = true;
			pcKeep = true;
			if(MipsRun.CODE_DEBUG){
				des = (">>>ð�գ�WRITE-B��");
			}
			hasStall = true;
		}
		
		/*
		 * 1. lw rx -- mem
		 *    ����  --ex��������
		 * 2. b rx  -- id
		 * ��ex��mem�ж�Ҫ�������̣�����id��ʹ��ָ��1��wb�У�ͨ��ת������ȡrx��
		 * ����ֻ����EX�в�������
		 */
		if(MipsSimEnv.exmem.out.mem.memRead
				&& MipsSimEnv.exmem.out.instRw!=0
				&& (new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BEQZ.opcode
						|| new Inst(MipsSimEnv.ifid.out.ir).opcode == Instances.BNEZ.opcode)
				&& MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs ){
			idFlush = true;
			idKeep = true;
			pcKeep = true;
			if(MipsRun.CODE_DEBUG){
				des = (">>>ð�գ�LW-USE��");
			}
			hasStall = true;
		}
	}

	public void updateYanchicao() {
		ifFlush = false;
		/*
		 * �����ʹ���ӳٲۡ�
		 * ��ô���ID�׶ε�ָ����J����B�����ҳɹ���ת����ô��Ҫ��IF��ָ�����㣬ͨ����ID�׶β�������ʵ��
		 */
		if(MipsSimEnv.jump && MipsSimEnv.instMemUnit.isReady()){
			if(!Option.USE_YANCHICAO){
				ifFlush = true;
				hasStall = true;
				
				des = (">>>ð�գ���ʱ�ۡ�");
			}
		}
	
		if(hasStall){
			MipsSimEnv.stall_count ++ ;
		}
	}
	
	//��ͣEX����֮ǰ��ָ��
	private void pauseEx(){
		/*
		 * ���EX����NOP�Ļ�����û�б�Ҫ��ͣEX��
		 * ���������NOP������LW - USE ð�գ���ô��Ҫ��ͣ
		 * 
		 * �˺�����MEMΪLW����SWָ��ʱ���ã�����ʱ���EX��ΪNOP����ôֻ���������
		 * IDΪ��תָ�IDָ���õ���LW��ֵ��ǰ����һ����ID�ε�ָ��ΪNOP����ô��ʱ������ͣ����Ϊ
		 * ID��EX����NOP�������Ϳ����ύһ����Ч�ʡ���һ��������ǲ���ɾ��NOP�ģ�������Ҫ��ͣ��
		 */
		if(MipsSimEnv.idex.out.ir == Instances.NOP.code){
			if(MipsSimEnv.exmem.out.mem.memRead //id����һ��ָ����Ҫ��ȡ����
					&& MipsSimEnv.exmem.out.instRw!=0
					&&(
							MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rs
							|| MipsSimEnv.exmem.out.instRw == new Inst(MipsSimEnv.ifid.out.ir).rt )){
				//�����NOP������LW - USE ð�գ���ô��Ҫ��ͣ
			}
			else{
				return;
			}
		}
		
		exKeep = true;
		if(MipsSimEnv.ifid.out.ir != Instances.NOP.code){//���ID����NOP�Ļ�����û�б�Ҫ��ͣID
			idKeep = true;
			pcKeep = true;//��ͣPC
		}
	}
	

	public void dump(){
		System.out.println("ð����\tpcKeep:"+pcKeep+",ifFlush:"
				+ifFlush+",idFlush:"+idFlush+",exFlush:"+exFlush+",idKeep:"+idKeep
				+",exKeep:"+exKeep+",memKeep:"+memKeep
				+",memFlush:"+memFlush);
		if(hasStall)
			System.out.println(des);
	}

}
