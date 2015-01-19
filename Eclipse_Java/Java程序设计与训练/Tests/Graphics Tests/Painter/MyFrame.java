package com.libin.chapter19.exec1;
 
import java.awt.BorderLayout;
import java.awt.Container;
 
import javax.swing.JFrame;
 
public class MyFrame extends JFrame{
       
       Container content;
       MyViewer view;
       public MyFrame(){
              this.setTitle("一个基础的绘图程序测试");
              this.setBounds(100,150,400,300);
              this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              content=this.getContentPane();
              view=new MyViewer();
              content.add(view,BorderLayout.CENTER);
              
              this.setVisible(true);
              
       }
 
}