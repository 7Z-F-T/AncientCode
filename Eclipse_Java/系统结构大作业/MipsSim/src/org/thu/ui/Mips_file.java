package org.thu.ui;
import java.io.*;

import javax.swing.*;


public class Mips_file {
	/**
	 *�ļ�������� 
	 */
	public static final int maxlines = 10000;
	
	/**
	 *��ǰ�ļ�����File 
	 */
	public File file;
	
	/**
	 *����������е����� 
	 */
	public String [] sentences;
	
	/**
	 *line�ĸ��� 
	 */
	public int linenum;
	
	/**
	 * mipsframe
	 */
	public Mipsframe mipsframe;
	
	/**
	 * ���ı��ļ�openfile�е����ݴ�ŵ�����Ķ�����
	 */
	public Mips_file(File openfile,Mipsframe mipsframe){
		this.mipsframe = mipsframe;
		this.file = openfile;
		sentences = new String [maxlines];
		String temp_s;
		int i = 0;
		try{
			BufferedReader  br = new BufferedReader(new InputStreamReader(new FileInputStream(openfile)));
			temp_s = br.readLine();
			while(temp_s != null){
				this.sentences[i] = temp_s;
				i++;
				temp_s = br.readLine();
			}
			br.close();
		}catch(Exception e){}
		this.linenum = i;
	}
	
	/**
	 * ��textarea�ж�ȡ�ı�������ö����У���start debugǰ�������
	 * ���Ȱ�textarea�����ļ���Ȼ���ٴ��ļ������ 
	 */
	public boolean save_from_textarea(JTextPane textarea){
		File myFile=this.file;
        if(myFile==null)//���֮ǰû��
        {
            JFileChooser save=new JFileChooser();
            javax.swing.filechooser.FileFilter filter = new Filter("s");
            save.setFileFilter(filter);
            int selected=save.showSaveDialog(mipsframe);//��������Ի���
            if(selected==JFileChooser.APPROVE_OPTION)
            {
                myFile=save.getSelectedFile();
                if(myFile.getName().lastIndexOf(".")==-1)
                     myFile=new File(myFile.getPath()+".s");
                
                if(myFile.exists()){
                	int res = JOptionPane.showConfirmDialog(mipsframe, "�ļ��Ѿ����ڣ�ȷ�ϸ��ǣ�");
                	if(res != JOptionPane.YES_OPTION){
                		return false;
                	}
                }
                
                this.file=myFile;mipsframe.setTitle("MipsSim ("+myFile.getName()+")");
                mipsframe.save(myFile);
            }else{
            	return false;
            }
        }
        else{//���֮ǰ����ֱ�ӱ���
        	mipsframe.save(myFile);
        }
        sentences = new String [maxlines];
		String temp_s;
		int i = 0;
		try{
			BufferedReader  br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
			temp_s = br.readLine();
			while(temp_s != null){
				this.sentences[i] = temp_s;
				i++;
				temp_s = br.readLine();
			}
			br.close();
		}catch(Exception e){}
		this.linenum = i;
		return true;
	}
	
	/**
	 * ���linenum��Ŀ
	 */
	public int getLinenum(){
		return linenum;
	}
	
	/**
	 * ��õ�index��sentence
	 */
	public String getSentence(int index){
		if(index >= linenum){
			return null;
		}
		return this.sentences[index];
	}
}
