package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.text.*;
public class chatTextAnalyzer {
	public chatTextAnalyzer(MainFrame frame, StringBuffer text){
		frame.publicWords.setText("");
		int length=text.length();//获取text 长度
		char[] every=new char[length];
		int count=0;//初始字符的位置，变化
		Document doc = frame.publicWords.getStyledDocument();
		SimpleAttributeSet attr=new SimpleAttributeSet();
	    Boolean hasFace=false;//标志文字中是否有表情转义符
	    for(int i=0;i<every.length;i++)
		{
		   every[i]=text.charAt(i);
		     if(every[i]=='/')  //识别信息中是否有/号
		    	 hasFace=true;
		}
	    //插入表情
	    for(int i=0;i<every.length;i++)
		{
		   if(hasFace==false)
			   break;//没有表情符号则不需处理
		   
		   if(every[i]=='/')
		   {
			   String str=null;
		       str=text.substring(count,i);      //得到表情前的文字
		    
		       try{
		    	   if(str!=null)
		    		   doc.insertString(doc.getLength(), str, attr);//添加表情前的文字
		       }catch(Exception e){
		    	   System.out.println("a big error here");
		       }
		       
		    
		       String iconName;
		       int j=i;
		       if(i!=every.length-1){//  /后面还有字符，应该是表情名称
		    	   for(j=i+1;j<every.length;j++){
		    		   if(every[j]==' ') break;
		    	   }
		    	   iconName=text.substring(i+1, j);
			       //判断到底是哪种表情
			       if(iconName.equals("smile")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.smileIcon);
			       }
			       if(iconName.equals("cry")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.cryIcon);
			       }
			       if(iconName.equals("angry")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.angryIcon);
			       }
			       if(iconName.equals("bye")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.byeIcon);
			       }
			       if(iconName.equals("haha")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.hahaIcon);
			       }
			       if(iconName.equals("bs")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.bsIcon);
			       }
			       if(iconName.equals("faint")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.faintIcon);
			       }
			       if(iconName.equals("omg")){
			    	   frame.publicWords.setCaretPosition(doc.getLength());
			    	   frame.publicWords.insertIcon(frame.omgIcon);
			       }
			       
			       i=j;
			       if(i!=every.length-1)//表情符后面还有文字
			    	   count=i;
		       }  
		    }
		}
        String last=text.substring(count, length);
	    try{
	    doc.insertString(doc.getLength(),last , attr);
	    }catch(Exception e){
	    System.out.println("a big error here");
	    }
	}
}
