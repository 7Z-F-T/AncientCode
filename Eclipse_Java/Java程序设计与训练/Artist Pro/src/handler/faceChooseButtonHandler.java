package handler;
import java.awt.event.*;
import frame.*;
/**
 * ����ӱ��顱�����¼�������
 * @author ���
 *
 */
public class faceChooseButtonHandler implements ActionListener{
	MainFrame frame;
	/**
	 * �½�һ������ӱ��顱�����¼�������
	 * @param frm ��������
	 */
	public faceChooseButtonHandler(MainFrame frm){
		frame=frm;
	}
	public void actionPerformed(ActionEvent e){
		faceChooseDialog faceChoose=new faceChooseDialog(frame);
	}
}
