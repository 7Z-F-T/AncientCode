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
		int length=text.length();//��ȡtext ����
		char[] every=new char[length];
		int count=0;//��ʼ�ַ���λ�ã��仯
		Document doc = frame.publicWords.getStyledDocument();
		SimpleAttributeSet attr=new SimpleAttributeSet();
	    Boolean hasFace=false;//��־�������Ƿ��б���ת���
	    for(int i=0;i<every.length;i++)
		{
		   every[i]=text.charAt(i);
		     if(every[i]=='/')  //ʶ����Ϣ���Ƿ���/��
		    	 hasFace=true;
		}
	    //�������
	    for(int i=0;i<every.length;i++)
		{
		   if(hasFace==false)
			   break;//û�б���������账��
		   
		   if(every[i]=='/')
		   {
			   String str=null;
		       str=text.substring(count,i);      //�õ�����ǰ������
		    
		       try{
		    	   if(str!=null)
		    		   doc.insertString(doc.getLength(), str, attr);//��ӱ���ǰ������
		       }catch(Exception e){
		    	   System.out.println("a big error here");
		       }
		       
		    
		       String iconName;
		       int j=i;
		       if(i!=every.length-1){//  /���滹���ַ���Ӧ���Ǳ�������
		    	   for(j=i+1;j<every.length;j++){
		    		   if(every[j]==' ') break;
		    	   }
		    	   iconName=text.substring(i+1, j);
			       //�жϵ��������ֱ���
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
			       if(i!=every.length-1)//��������滹������
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
