package handler;
import frame.*;
import java.awt.event.*;
/**
 * 聊天“发送”摁钮事件处理器。将当前输入的文字以及表情转义符通过chatTextAnalyzer
 * 分析后显示到聊天记录框中
 * @author 侯杰
 *
 */
public class ChatSendButtonHandler implements ActionListener{
	MainFrame frame;
	/**
	 * 新建一个“发送”摁钮事件处理器
	 * @param frm 主程序框架
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
			//进行文本分析处理，尤其是转义符处理
			new ChatTextAnalyzer(frame, frame.chatRecord);
			frame.privateWords.setText("");
			frame.retellFlip.setFlip();
		}
	}
}
