package handler;
import frame.*;
import java.awt.event.*;
/**
 * ���조���͡���ť�¼�������������ǰ����������Լ�����ת���ͨ��chatTextAnalyzer
 * ��������ʾ�������¼����
 * @author ���
 *
 */
public class ChatSendButtonHandler implements ActionListener{
	MainFrame frame;
	/**
	 * �½�һ�������͡���ť�¼�������
	 * @param frm ��������
	 */
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
			//�����ı���������������ת�������
			new ChatTextAnalyzer(frame, frame.chatRecord);
			frame.privateWords.setText("");
			frame.retellFlip.setFlip();
		}
	}
}
