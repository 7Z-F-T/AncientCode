package handler;
import java.awt.event.*;
import frame.*;
/**
 * “添加表情”摁键事件处理器
 * @author 侯杰
 *
 */
public class faceChooseButtonHandler implements ActionListener{
	MainFrame frame;
	/**
	 * 新建一个“添加表情”摁键事件处理器
	 * @param frm 主程序框架
	 */
	public faceChooseButtonHandler(MainFrame frm){
		frame=frm;
	}
	public void actionPerformed(ActionEvent e){
		faceChooseDialog faceChoose=new faceChooseDialog(frame);
	}
}
