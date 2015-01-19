package handler;
import frame.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.geom.*;
/**
 * Բ�Ǿ���Բ�ǻ��ߣ������޸��¼���������ע��ֻ֧�ֶ�Բ�Ǿ��β���
 * @author ���
 *
 */
public class ArcWidthHeightChooserHandler {
	/**
	 * Բ�ǻ����޸��¼�������
	 * @author ���
	 *
	 */
	public static class ArcWidthChooser implements ChangeListener{
		MainFrame frame;
		/**
		 * �½�һ��Բ�ǻ����޸��¼�������
		 * @param frm ��������
		 */
		public ArcWidthChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			//�ӻ������ϻ�ȡֵ�������޸ģ�ͬʱ���»���
			//��ѡ��ͼ����ѡ�е���Բ�Ǿ��Σ������������
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true&&DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
				((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth=value;
				frame.arcWidthIndicator.setText(""+value);
				frame.previewCanvas.repaint();
				frame.canvas.repaint();
			}
			//������Ļ�ͼ��Ĭ������
			else{
				frame.currentPaintProperty.currentArcWidth=value;
				frame.arcWidthIndicator.setText(""+value);
				frame.previewCanvas.repaint();
			}
		}
	}
	public static class ArcWidthChooser2 extends MouseAdapter{
		MainFrame frame;
		public ArcWidthChooser2(MainFrame frm){
			frame=frm;
		}
		public void mouseReleased(MouseEvent e){
			frame.repaintFlip.setFlip();
		}
		public void mousePressed(MouseEvent e){
			frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
		}
	}
	/**
	 * Բ�ǻ����޸��¼�������
	 * @author ���
	 *
	 */
	public static class ArcHeightChooser implements ChangeListener{
		MainFrame frame;
		/**
		 * �½�һ��Բ�ǻ����޸��¼�������
		 * @param frm ��������
		 */
		public ArcHeightChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			//�ӻ������ϻ�ȡֵ�������޸ģ�ͬʱ���»���
			//��ѡ��ͼ����ѡ�е���Բ�Ǿ��Σ������������
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true&&DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
				((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight=value;
				frame.arcHeightIndicator.setText(""+value);
				frame.previewCanvas.repaint();
				frame.canvas.repaint();
			}
			//������Ļ�ͼ��Ĭ������
			else{
				frame.currentPaintProperty.currentArcHeight=value;
				frame.arcHeightIndicator.setText(""+value);
				frame.previewCanvas.repaint();
			}
		}
	}
	public static class ArcHeightChooser2 extends MouseAdapter{
		MainFrame frame;
		public ArcHeightChooser2(MainFrame frm){
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
