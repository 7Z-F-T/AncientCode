package org.thu.ui;
import java.io.*;

import javax.swing.*;


public class Mips_file {
	/**
	 *文件最大行数 
	 */
	public static final int maxlines = 10000;
	
	/**
	 *当前文件对象File 
	 */
	public File file;
	
	/**
	 *用来存放所有的命令 
	 */
	public String [] sentences;
	
	/**
	 *line的个数 
	 */
	public int linenum;
	
	/**
	 * mipsframe
	 */
	public Mipsframe mipsframe;
	
	/**
	 * 把文本文件openfile中的内容存放到该类的对象中
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
	 * 从textarea中读取文本并放入该对象中，在start debug前必须调用
	 * 首先把textarea存入文件，然后再从文件里读入 
	 */
	public boolean save_from_textarea(JTextPane textarea){
		File myFile=this.file;
        if(myFile==null)//如果之前没存
        {
            JFileChooser save=new JFileChooser();
            javax.swing.filechooser.FileFilter filter = new Filter("s");
            save.setFileFilter(filter);
            int selected=save.showSaveDialog(mipsframe);//弹出保存对话框
            if(selected==JFileChooser.APPROVE_OPTION)
            {
                myFile=save.getSelectedFile();
                if(myFile.getName().lastIndexOf(".")==-1)
                     myFile=new File(myFile.getPath()+".s");
                
                if(myFile.exists()){
                	int res = JOptionPane.showConfirmDialog(mipsframe, "文件已经存在，确认覆盖？");
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
        else{//如果之前存了直接保存
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
	 * 获得linenum数目
	 */
	public int getLinenum(){
		return linenum;
	}
	
	/**
	 * 获得第index个sentence
	 */
	public String getSentence(int index){
		if(index >= linenum){
			return null;
		}
		return this.sentences[index];
	}
}
