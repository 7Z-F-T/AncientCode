package handler;
import frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class ChatSendButtonHandler implements ActionListener{
	MainFrame frame;
	public ChatSendButtonHandler(MainFrame frm){
		frame=frm;
	}
	public void actionPerformed(ActionEvent e){
		String str=frame.privateWords.getText();
		if(str!=null){
			frame.chatRecord.append(frame.nickName);
			frame.chatRecord.append(" : ");
			frame.chatRecord.append(str);
			frame.chatRecord.append("\r\n");
			//frame.publicWords.setText(frame.chatRecord.toString());
			new chatTextAnalyzer(frame, frame.chatRecord);
			frame.privateWords.setText("");
			frame.retellFlip.setFlip();
		}
	}
}
