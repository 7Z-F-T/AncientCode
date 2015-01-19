package handler;
import frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * 颜色选择事件处理器。
 * @author 侯杰
 *
 */
public class ColorChooserHandler {
	/**
	 * 边线颜色选择事件处理器。
	 * @author 侯杰
	 *
	 */
	public static class LineColorChooser implements ActionListener{
		MainFrame frame;
		/**
		 * 新建一个边线颜色选择事件处理器
		 * @param frm 主程序框架
		 */
		public LineColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//调用系统的JColorChooser选择颜色
			Color selected=JColorChooser.showDialog(frame,"Set Line Color",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				//若当前选中了图形，则修改选中图形的颜色
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.lineColor=selected;
					frame.lineColorIndicator.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				//否则修改系统的绘图默认颜色
				else{
					frame.currentPaintProperty.currentLineColor=selected;
					frame.lineColorIndicator.setBackground(selected);
				}
			}
		}
	}
	/**
	 * 填充颜色选择事件处理器。
	 * @author 侯杰
	 *
	 */
	public static class FillColorChooser implements ActionListener{
		MainFrame frame;
		/**
		 * 新建一个填充颜色选择事件处理器
		 * @param frm 主程序框架
		 */
		public FillColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//调用系统的JColorChooser选择颜色
			Color selected=JColorChooser.showDialog(frame,"Set Fill Color 1",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				//若当前选中了图形，则修改选中图形的颜色
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.fillColor=selected;
					DrawHandler.currentSelectedShapePro.gradientFillColor=new GradPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,selected,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					frame.fillColorIndicator.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				//否则修改系统的绘图默认颜色
				else{
					frame.currentPaintProperty.currentFillColor=selected;
					frame.fillColorIndicator.setBackground(selected);
				}
			}
		}
	}
	/**
	 * 填充颜色2选择事件处理器。
	 * @author 侯杰
	 *
	 */
	public static class FillColorChooser2 implements ActionListener{
		MainFrame frame;
		/**
		 * 新建一个填充颜色2选择事件处理器
		 * @param frm 主程序框架
		 */
		public FillColorChooser2(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//调用系统的JColorChooser选择颜色
			Color selected=JColorChooser.showDialog(frame,"Set Fill Color 2",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					//若当前选中了图形，则修改选中图形的颜色
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.gradientFillColor=new GradPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,DrawHandler.currentSelectedShapePro.gradientFillColor.color1,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,selected);
					frame.fillColorIndicator2.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				//否则修改系统的绘图默认颜色
				else{
					frame.currentPaintProperty.currentGradientFillColor.color2=selected;
					frame.fillColorIndicator2.setBackground(selected);
				}
			}
		}
	}
}
