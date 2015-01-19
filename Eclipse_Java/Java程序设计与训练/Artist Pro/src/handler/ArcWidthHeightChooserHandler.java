package handler;
import frame.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.geom.*;
/**
 * 圆角矩形圆角弧高，弧长修改事件处理器。注意只支持对圆角矩形操作
 * @author 侯杰
 *
 */
public class ArcWidthHeightChooserHandler {
	/**
	 * 圆角弧宽修改事件处理器
	 * @author 侯杰
	 *
	 */
	public static class ArcWidthChooser implements ChangeListener{
		MainFrame frame;
		/**
		 * 新建一个圆角弧宽修改事件处理器
		 * @param frm 主程序框架
		 */
		public ArcWidthChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			//从滑动条上获取值，进行修改，同时更新画板
			//若选中图形且选中的是圆角矩形，则更改其属性
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true&&DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
				((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth=value;
				frame.arcWidthIndicator.setText(""+value);
				frame.previewCanvas.repaint();
				frame.canvas.repaint();
			}
			//否则更改绘图的默认属性
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
	 * 圆角弧高修改事件处理器
	 * @author 侯杰
	 *
	 */
	public static class ArcHeightChooser implements ChangeListener{
		MainFrame frame;
		/**
		 * 新建一个圆角弧高修改事件处理器
		 * @param frm 主程序框架
		 */
		public ArcHeightChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			//从滑动条上获取值，进行修改，同时更新画板
			//若选中图形且选中的是圆角矩形，则更改其属性
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true&&DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
				((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight=value;
				frame.arcHeightIndicator.setText(""+value);
				frame.previewCanvas.repaint();
				frame.canvas.repaint();
			}
			//否则更改绘图的默认属性
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
