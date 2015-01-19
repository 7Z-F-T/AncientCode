package handler;
import frame.*;
import java.awt.event.*;
/**
 * ��������ť�¼�������
 * @author ���
 *
 */
public abstract class ToolBoxHandler {
	/**������ť
	 * 
	 * @author ���
	 *
	 */
	public static class RectangleButton implements ActionListener{
		MainFrame frame;
		public RectangleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.RECTANGLE
			frame.currentFunction=Function.RECTANGLE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**Բ�Ǿ�����ť
	 * 
	 * @author ���
	 *
	 */
	public static class RoundRectangleButton implements ActionListener{
		MainFrame frame;
		public RoundRectangleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.ROUNDRECTANGLE
			frame.currentFunction=Function.ROUNDRECTANGLE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**ֱ����Ť
	 * 
	 * @author ���
	 *
	 */
	public static class LineButton implements ActionListener{
		MainFrame frame;
		public LineButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.LINE
			frame.currentFunction=Function.LINE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**��Բ��ť
	 * 
	 * @author ���
	 *
	 */
	public static class EllipseButton implements ActionListener{
		MainFrame frame;
		public EllipseButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.ELLIPSE
			frame.currentFunction=Function.ELLIPSE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**ѡ����ť
	 * 
	 * @author ���
	 *
	 */
	public static class SelectButton implements ActionListener{
		MainFrame frame;
		public SelectButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.SELECT
			frame.currentFunction=Function.SELECT;
			frame.currentPaintProperty.transformable=false;
		}
	}
	/**��ת��ť
	 * 
	 * @author ���
	 *
	 */
	public static class RotateButton implements ActionListener{
		MainFrame frame;
		public RotateButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.ROTATE
			frame.currentFunction=Function.ROTATE;
			frame.currentPaintProperty.transformable=true;
		}
	}
	/**������ť
	 * 
	 * @author ���
	 *
	 */
	public static class ScaleButton implements ActionListener{
		MainFrame frame;
		public ScaleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.SCALE
			frame.currentFunction=Function.SCALE;
			frame.currentPaintProperty.transformable=true;
		}
	}
	/**������ť
	 * 
	 * @author ���
	 *
	 */
	public static class ShearButton implements ActionListener{
		MainFrame frame;
		public ShearButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪFunction.SHEAR
			frame.currentFunction=Function.SHEAR;
			frame.currentPaintProperty.transformable=true;
		}
	}
	/**������ť
	 * 
	 * @author ���
	 *
	 */
	public static class TextButton implements ActionListener{
		MainFrame frame;
		public TextButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//���õ�ǰ��ͼģʽΪ����
			frame.currentFunction=Function.TEXT;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
}
