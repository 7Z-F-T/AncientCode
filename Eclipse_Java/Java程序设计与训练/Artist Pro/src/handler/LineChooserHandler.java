package handler;
import frame.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
/**
 * �����߿�����ѡ���¼�������
 * @author ���
 *
 */
public class LineChooserHandler {
	/**
	 * ��������ѡ���¼�������
	 * @author ���
	 *
	 */
	public static class LineStyleChooser implements ActionListener{
		MainFrame frame;
		/**
		 * �½�һ����������ѡ���¼�������
		 * @param frm ��������
		 */
		public LineStyleChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			LineStyleOptionDialog dialog=new LineStyleOptionDialog(frame);
		}
	}
	/**
	 * �����߿�ѡ���¼�������
	 * @author ���
	 *
	 */
	public static class LineWidthChooser implements ChangeListener{
		MainFrame frame;
		/**
		 * �½�һ�������߿�ѡ���¼�������
		 * @param frm ��������
		 */
		public LineWidthChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			//���û���������ֵ�������߿�
			String patternName=frame.currentPaintProperty.currentStroke.patternName;
			//���ѡ����ͼ�Σ��͸�����ѡͼ�ε��߿�
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				float[] dashPattern=DrawHandler.currentSelectedShapePro.stroke.pattern;
				DrawHandler.currentSelectedShapePro.stroke=new BasStroke(value,dashPattern,patternName);
				frame.lineWidthIndicator2.setText(""+value);
				frame.canvas.repaint();
				frame.previewCanvas.repaint();
			}
			//�������ϵͳĬ�ϵ��߿�
			else{
				float[] dashPattern=frame.currentPaintProperty.currentStroke.pattern;
				frame.currentPaintProperty.currentStroke=new BasStroke(value,dashPattern,patternName);
				frame.lineWidthIndicator2.setText(""+value);
				frame.previewCanvas.repaint();
			}
	    }
	}
	public static class LineWidthChooser2 extends MouseAdapter{
		MainFrame frame;
		public LineWidthChooser2(MainFrame frm){
			frame=frm;
		}
		public void mouseReleased(MouseEvent e){
			frame.repaintFlip.setFlip();
		}
		public void mousePressed(MouseEvent e){
			frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
		}
	}
}

