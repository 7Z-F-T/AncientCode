package org.thu.ui;
import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.border.*; 
import javax.swing.plaf.metal.*;

import org.thu.core.MipsRun;


import java.net.*;
import java.io.*;

public class MipsMenubar extends JMenuBar implements ActionListener{
	Mipsframe mipsframe;
	JMenu file,debug,about;
	JMenuItem fileNew,fileOpen,fileSave,fileSaveAs,fileExit,
	debugStart,debugStop,debugStep,debugContinue,debugAddB,debugDelB,debugSetBMA,debugSetM,debugSetR,
	aboutAbout;
	public MipsMenubar(Mipsframe mipsframe){
		this.mipsframe = mipsframe;
		
		file = new JMenu("File");
		this.add(file);
		debug = new JMenu("Debug");
		this.add(debug);
		about = new JMenu("About");
		this.add(about);
		
		fileNew=new JMenuItem("New");
        fileNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        file.add(fileNew);
        fileNew.addActionListener(this);
        
        fileOpen=new JMenuItem("Open");
        fileOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        file.add(fileOpen);
        fileOpen.addActionListener(this);
        
        fileSave=new JMenuItem("Save");
        fileSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        file.add(fileSave);
        fileSave.addActionListener(this);
        
        fileSaveAs=new JMenuItem("Save As");
        fileSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
        file.add(fileSaveAs);
        fileSaveAs.addActionListener(this);
        
        file.addSeparator();
        
        fileExit=new JMenuItem("Exit");
        fileExit.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
        file.add(fileExit);
        fileExit.addActionListener(this);
        
        debugStart=new JMenuItem("Start Debugging");
        debugStart.setAccelerator(KeyStroke.getKeyStroke("F7"));
        debug.add(debugStart);
        debugStart.addActionListener(this);
        
        debugStop=new JMenuItem("Stop Debugging");
        debugStop.setAccelerator(KeyStroke.getKeyStroke("F3"));
        debug.add(debugStop);
        debugStop.addActionListener(this);
        
        debug.addSeparator();
        
        debugStep=new JMenuItem("Step");
        debugStep.setAccelerator(KeyStroke.getKeyStroke("F6"));
        debug.add(debugStep);
        debugStep.addActionListener(this);
        
        debugContinue=new JMenuItem("Continue");
        debugContinue.setAccelerator(KeyStroke.getKeyStroke("F5"));
        debug.add(debugContinue);
        debugContinue.addActionListener(this);
        
        debug.addSeparator();
        
        debugAddB=new JMenuItem("Add breakpoint");
        debugAddB.setAccelerator(KeyStroke.getKeyStroke("F8"));
        debug.add(debugAddB);
        debugAddB.addActionListener(this);
        
        debugDelB=new JMenuItem("Del breakpoint");
        debugDelB.setAccelerator(KeyStroke.getKeyStroke("F9"));
        debug.add(debugDelB);
        debugDelB.addActionListener(this);
        
        debug.addSeparator();
        
        debugSetBMA=new JMenuItem("Set memory base addr");
        debugSetBMA.setAccelerator(KeyStroke.getKeyStroke("F10"));
        debug.add(debugSetBMA);
        debugSetBMA.addActionListener(this);
        
        debugSetM=new JMenuItem("Set memory contents");
        debugSetM.setAccelerator(KeyStroke.getKeyStroke("F11"));
        debug.add(debugSetM);
        debugSetM.addActionListener(this);
        
        debugSetR=new JMenuItem("Set register contents");
        debugSetR.setAccelerator(KeyStroke.getKeyStroke("F12"));
        debug.add(debugSetR);
        debugSetR.addActionListener(this);
        
        aboutAbout=new JMenuItem("About");
        aboutAbout.setAccelerator(KeyStroke.getKeyStroke("ctrl shift 1"));
        about.add(aboutAbout);
        aboutAbout.addActionListener(this);
        
        debugStop.setEnabled(false);
        debugStep.setEnabled(false);
        debugContinue.setEnabled(false);
        debugAddB.setEnabled(false);
        debugDelB.setEnabled(false);
        debugSetBMA.setEnabled(false);
        debugSetM.setEnabled(false);
        debugSetR.setEnabled(false);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			//新建
	        if(e.getSource()==fileNew){
	        	Mipsframe.mips_file = new Mips_file(null,mipsframe);
	        	this.mipsframe.setTitle("MipSim(Untitled.s)");
	        	this.mipsframe.refresh_text();
	        }
	        if(e.getSource()==fileOpen)//文件打开
	        {
	            JFileChooser open=new JFileChooser();
	            javax.swing.filechooser.FileFilter filter = new Filter("s");//文件过滤器
	            open.setFileFilter(filter);
	            int selected=open.showOpenDialog(mipsframe);
	            if(selected==JFileChooser.APPROVE_OPTION)
	            {
	                File myFile=open.getSelectedFile();
	                if(myFile.isFile()&&myFile.exists())
	                {
	                	Mipsframe.mips_file = new Mips_file(myFile,mipsframe);
	                	this.mipsframe.setTitle("MipSim("+myFile.getName()+")");
	                	this.mipsframe.refresh_text();
	                }
	            }
	        }
	        if(e.getSource()==fileSave)//保存
	        {
	            File myFile=Mipsframe.mips_file.file;
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
		                		return;
		                	}
		                }
	                    
	                    Mipsframe.mips_file.file=myFile;mipsframe.setTitle("MipsSim ("+myFile.getName()+")");
	                    mipsframe.save(myFile);
	                }
	            }
	            else{//如果之前存了直接保存
	            	mipsframe.save(myFile);
	            }
	        }
	        if(e.getSource()==fileSaveAs)//另存为
	        {
	            JFileChooser save=new JFileChooser();
	            javax.swing.filechooser.FileFilter filter = new Filter("s");
	            save.setFileFilter(filter);
	            int selected=save.showSaveDialog(mipsframe);
	            if(selected==JFileChooser.APPROVE_OPTION)
	            {
	                File myFile=save.getSelectedFile();
	                if(myFile.getName().lastIndexOf(".")==-1)
	                     myFile=new File(myFile.getPath()+".s");
	                
	                if(myFile.exists()){
	                	int res = JOptionPane.showConfirmDialog(mipsframe, "文件已经存在，确认覆盖？");
	                	if(res != JOptionPane.YES_OPTION){
	                		return;
	                	}
	                }
	                
	                Mipsframe.mips_file.file=myFile;
	                mipsframe.setTitle("MipsSim ("+myFile.getName()+")");
	                mipsframe.save(myFile);
	            }
	        }
	         if(e.getSource()==fileExit)//退出
	        {
	            System.exit(1);
	        }
	         
	         
	         if(e.getSource() == this.debugStart){
	        	 this.mipsframe.startdebug();
	         }
	         if(e.getSource() == this.debugStop){
	        	 this.mipsframe.stopdebug();
	         }
	         if(e.getSource() == this.debugStep){
	        	 this.mipsframe.stepdebug();
	         }
	         if(e.getSource() == this.debugContinue){
	        	 this.mipsframe.continuedebug();
	         }
	         if(e.getSource() == this.debugAddB){
	        	 if(mipsframe.jl.getSelectedIndex() > Mipsframe.file_in_binary.commandsize) return;
	        	 Mipsframe.file_in_binary.commands[mipsframe.jl.getSelectedIndex()].setbreakpoint(true);
	        	 MipsRun.bpo.addAddrBreakpoint(mipsframe.jl.getSelectedIndex()<<2);
	        	 
	        	 mipsframe.refresh_text();
	        	 mipsframe.jl.repaint();
	         }
	         if(e.getSource() == this.debugDelB){
	        	 if(mipsframe.jl.getSelectedIndex() > Mipsframe.file_in_binary.commandsize) return;
	        	 Mipsframe.file_in_binary.commands[mipsframe.jl.getSelectedIndex()].setbreakpoint(false);
	        	 MipsRun.bpo.removeAddrBreakpoint(mipsframe.jl.getSelectedIndex()<<2);
	        	 
	        	 mipsframe.refresh_text();
	        	 mipsframe.jl.repaint();
	         }
	         if(e.getSource() == this.debugSetBMA){
	        	 this.mipsframe.setBMADialog = new SetBMADialog(mipsframe);
	     		this.mipsframe.setBMADialog.setVisible(true);
	         }
	         if(e.getSource() == this.debugSetM){
	        	 this.mipsframe.setMDialog = new SetMDialog(mipsframe);
	        	 this.mipsframe.setMDialog.setVisible(true);
	         }
	         if(e.getSource() == this.debugSetR){
	        	 this.mipsframe.setRDialog = new SetRDialog(mipsframe);
	        	 this.mipsframe.setRDialog.setVisible(true);
	         }
	}
	
}
