package handler;
import frame.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
/**
 * 边线线宽线型选择事件处理器
 * @author 侯杰
 *
 */
public class LineChooserHandler {
	/**
	 * 边线线型选择事件处理器
	 * @author 侯杰
	 *
	 */
	public static class LineStyleChooser implements ActionListener{
		MainFrame frame;
		/**
		 * 新建一个边线线型选择事件处理器
		 * @param frm 主程序框架
		 */
		public LineStyleChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			LineStyleOptionDialog dialog=new LineStyleOptionDialog(frame);
		}
	}
	/**
	 * 边线线宽选择事件处理器
	 * @author 侯杰
	 *
	 */
	public static class LineWidthChooser implements ChangeListener{
		MainFrame frame;
		/**
		 * 新建一个边线线宽选择事件处理器
		 * @param frm 主程序框架
		 */
		public LineWidthChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			//利用滑动条的数值来更改线宽
			String patternName=frame.currentPaintProperty.currentStroke.patternName;
			//如果选中了图形，就更改所选图形的线宽
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				float[] dashPattern=DrawHandler.currentSelectedShapePro.stroke.pattern;
				DrawHandler.currentSelectedShapePro.stroke=new BasStroke(value,dashPattern,patternName);
				frame.lineWidthIndicator2.setText(""+value);
				frame.canvas.repaint();
				frame.previewCanvas.repaint();
			}
			//否则更改系统默认的线宽
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

