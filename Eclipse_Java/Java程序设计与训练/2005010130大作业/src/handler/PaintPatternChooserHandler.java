package handler;
import javax.swing.*;
import frame.*;
import frame.CopyShapeProArray;
import frame.Function;
import frame.MainFrame;
import java.awt.event.*;
/**
 * 绘图样式选择事件处理器
 * @author 侯杰
 *
 */
public class PaintPatternChooserHandler {
	/**
	 * 存储刚才的绘图模式。主要用途为梯度填充拖拽操作后恢复原功能。
	 */
	public static int previousFucntion;
	/**“仅绘制边线”摁钮事件处理
	 * 
	 * @author 侯杰
	 *
	 */
	public static class LineOnlyButton implements ActionListener{
		MainFrame frame;
		public LineOnlyButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				DrawHandler.currentSelectedShapePro.lined=true;
				DrawHandler.currentSelectedShapePro.fillType=FillType.NONE;
				frame.gradientFillCheck.setEnabled(false);
				frame.gradientFillButton.setEnabled(false);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				frame.previewCanvas.repaint();
			}
			else{
				frame.currentPaintProperty.currentLined=true;
				frame.currentPaintProperty.currentFillType=FillType.NONE;
				frame.gradientFillCheck.setEnabled(false);
				frame.gradientFillButton.setEnabled(false);
				frame.previewCanvas.repaint();
			}
		}
	}
	/**“仅填充内部”摁钮事件处理
	 * 
	 * @author 侯杰
	 *
	 */
	public static class FillOnlyButton implements ActionListener{
		MainFrame frame;
		public FillOnlyButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				DrawHandler.currentSelectedShapePro.lined=false;
				DrawHandler.currentSelectedShapePro.fillType=FillType.SOLID;
				frame.gradientFillCheck.setEnabled(true);
				frame.gradientFillCheck.setSelected(false);
				frame.gradientFillButton.setEnabled(false);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				frame.previewCanvas.repaint();
			}
			else{
				frame.currentPaintProperty.currentLined=false;
				frame.currentPaintProperty.currentFillType=FillType.SOLID;
				frame.gradientFillCheck.setEnabled(false);
				frame.gradientFillButton.setEnabled(false);
				frame.previewCanvas.repaint();
			}
		}
	}
	/**“同时具有边线和内部填充”摁钮事件处理
	 * 
	 * @author 侯杰
	 *
	 */
	public static class LineAndFillButton implements ActionListener{
		MainFrame frame;
		public LineAndFillButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				DrawHandler.currentSelectedShapePro.lined=true;
				DrawHandler.currentSelectedShapePro.fillType=FillType.SOLID;
				frame.gradientFillCheck.setEnabled(true);
				frame.gradientFillCheck.setSelected(false);
				frame.gradientFillButton.setEnabled(false);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				frame.previewCanvas.repaint();
			}
			else{
				frame.currentPaintProperty.currentLined=true;
				frame.currentPaintProperty.currentFillType=FillType.SOLID;
				frame.gradientFillCheck.setEnabled(false);
				frame.gradientFillButton.setEnabled(false);
				frame.previewCanvas.repaint();
			}
			frame.previewCanvas.repaint();
		}
	}
	/**“梯度填充”摁钮事件处理
	 * 
	 * @author 侯杰
	 *
	 */
	public static class GradientFillButton implements ActionListener{
		MainFrame frame;
		public GradientFillButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JToggleButton b=(JToggleButton)(e.getSource());
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				if(b.isSelected()==true){
					//摁下此摁钮后，绘图模式改为拖拽式梯度填充。
					PaintPatternChooserHandler.previousFucntion=frame.currentFunction;
					frame.currentFunction=Function.GRADIENT;
				}
				else{
					//再次摁下后，绘图模式恢复
					frame.currentFunction=PaintPatternChooserHandler.previousFucntion;
				}
			}
		}
	}
	/**“允许梯度填充”复选框事件处理
	 * 
	 * @author 侯杰
	 *
	 */
	public static class GradientFillCheck implements ActionListener{
		MainFrame frame;
		public GradientFillCheck(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JCheckBox b=(JCheckBox)(e.getSource());
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				if(b.isSelected()==true){
					//修改对象填充类型为FillType.GRADIENT
					frame.gradientFillButton.setEnabled(true);
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.fillType=FillType.GRADIENT;
					frame.repaintFlip.setFlip();
				}
				else{
					//还原填充类型为FillType.SOLID
					frame.gradientFillButton.setEnabled(false);
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.fillType=FillType.SOLID;
					frame.currentFunction=PaintPatternChooserHandler.previousFucntion;
					frame.repaintFlip.setFlip();
				}
				frame.canvas.repaint();
			}
		}
	}
}
