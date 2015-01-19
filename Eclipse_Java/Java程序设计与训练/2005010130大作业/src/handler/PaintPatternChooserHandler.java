package handler;
import javax.swing.*;
import frame.*;
import frame.CopyShapeProArray;
import frame.Function;
import frame.MainFrame;
import java.awt.event.*;
/**
 * ��ͼ��ʽѡ���¼�������
 * @author ���
 *
 */
public class PaintPatternChooserHandler {
	/**
	 * �洢�ղŵĻ�ͼģʽ����Ҫ��;Ϊ�ݶ������ק������ָ�ԭ���ܡ�
	 */
	public static int previousFucntion;
	/**�������Ʊ��ߡ���ť�¼�����
	 * 
	 * @author ���
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
	/**��������ڲ�����ť�¼�����
	 * 
	 * @author ���
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
	/**��ͬʱ���б��ߺ��ڲ���䡱��ť�¼�����
	 * 
	 * @author ���
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
	/**���ݶ���䡱��ť�¼�����
	 * 
	 * @author ���
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
					//���´���ť�󣬻�ͼģʽ��Ϊ��קʽ�ݶ���䡣
					PaintPatternChooserHandler.previousFucntion=frame.currentFunction;
					frame.currentFunction=Function.GRADIENT;
				}
				else{
					//�ٴ����º󣬻�ͼģʽ�ָ�
					frame.currentFunction=PaintPatternChooserHandler.previousFucntion;
				}
			}
		}
	}
	/**�������ݶ���䡱��ѡ���¼�����
	 * 
	 * @author ���
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
					//�޸Ķ����������ΪFillType.GRADIENT
					frame.gradientFillButton.setEnabled(true);
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.fillType=FillType.GRADIENT;
					frame.repaintFlip.setFlip();
				}
				else{
					//��ԭ�������ΪFillType.SOLID
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
