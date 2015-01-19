package handler;
import frame.*;
import java.awt.event.*;
/**
 * 工具箱摁钮事件处理器
 * @author 侯杰
 *
 */
public abstract class ToolBoxHandler {
	/**矩形摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class RectangleButton implements ActionListener{
		MainFrame frame;
		public RectangleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.RECTANGLE
			frame.currentFunction=Function.RECTANGLE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**圆角矩形摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class RoundRectangleButton implements ActionListener{
		MainFrame frame;
		public RoundRectangleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.ROUNDRECTANGLE
			frame.currentFunction=Function.ROUNDRECTANGLE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**直线摁扭
	 * 
	 * @author 侯杰
	 *
	 */
	public static class LineButton implements ActionListener{
		MainFrame frame;
		public LineButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.LINE
			frame.currentFunction=Function.LINE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**椭圆摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class EllipseButton implements ActionListener{
		MainFrame frame;
		public EllipseButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.ELLIPSE
			frame.currentFunction=Function.ELLIPSE;
			frame.currentPaintProperty.transformable=false;
			if(DrawHandler.currentSelectedShapePro!=null)
				DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			frame.previewCanvas.repaint();
		}
	}
	/**选择摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class SelectButton implements ActionListener{
		MainFrame frame;
		public SelectButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.SELECT
			frame.currentFunction=Function.SELECT;
			frame.currentPaintProperty.transformable=false;
		}
	}
	/**旋转摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class RotateButton implements ActionListener{
		MainFrame frame;
		public RotateButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.ROTATE
			frame.currentFunction=Function.ROTATE;
			frame.currentPaintProperty.transformable=true;
		}
	}
	/**缩放摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class ScaleButton implements ActionListener{
		MainFrame frame;
		public ScaleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.SCALE
			frame.currentFunction=Function.SCALE;
			frame.currentPaintProperty.transformable=true;
		}
	}
	/**拉扯摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class ShearButton implements ActionListener{
		MainFrame frame;
		public ShearButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为Function.SHEAR
			frame.currentFunction=Function.SHEAR;
			frame.currentPaintProperty.transformable=true;
		}
	}
	/**文字摁钮
	 * 
	 * @author 侯杰
	 *
	 */
	public static class TextButton implements ActionListener{
		MainFrame frame;
		public TextButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//设置当前绘图模式为文字
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
