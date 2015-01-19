package handler;
import frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * ��ɫѡ���¼���������
 * @author ���
 *
 */
public class ColorChooserHandler {
	/**
	 * ������ɫѡ���¼���������
	 * @author ���
	 *
	 */
	public static class LineColorChooser implements ActionListener{
		MainFrame frame;
		/**
		 * �½�һ��������ɫѡ���¼�������
		 * @param frm ��������
		 */
		public LineColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//����ϵͳ��JColorChooserѡ����ɫ
			Color selected=JColorChooser.showDialog(frame,"Set Line Color",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				//����ǰѡ����ͼ�Σ����޸�ѡ��ͼ�ε���ɫ
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.lineColor=selected;
					frame.lineColorIndicator.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				//�����޸�ϵͳ�Ļ�ͼĬ����ɫ
				else{
					frame.currentPaintProperty.currentLineColor=selected;
					frame.lineColorIndicator.setBackground(selected);
				}
			}
		}
	}
	/**
	 * �����ɫѡ���¼���������
	 * @author ���
	 *
	 */
	public static class FillColorChooser implements ActionListener{
		MainFrame frame;
		/**
		 * �½�һ�������ɫѡ���¼�������
		 * @param frm ��������
		 */
		public FillColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//����ϵͳ��JColorChooserѡ����ɫ
			Color selected=JColorChooser.showDialog(frame,"Set Fill Color 1",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				//����ǰѡ����ͼ�Σ����޸�ѡ��ͼ�ε���ɫ
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.fillColor=selected;
					DrawHandler.currentSelectedShapePro.gradientFillColor=new GradPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,selected,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					frame.fillColorIndicator.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				//�����޸�ϵͳ�Ļ�ͼĬ����ɫ
				else{
					frame.currentPaintProperty.currentFillColor=selected;
					frame.fillColorIndicator.setBackground(selected);
				}
			}
		}
	}
	/**
	 * �����ɫ2ѡ���¼���������
	 * @author ���
	 *
	 */
	public static class FillColorChooser2 implements ActionListener{
		MainFrame frame;
		/**
		 * �½�һ�������ɫ2ѡ���¼�������
		 * @param frm ��������
		 */
		public FillColorChooser2(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//����ϵͳ��JColorChooserѡ����ɫ
			Color selected=JColorChooser.showDialog(frame,"Set Fill Color 2",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					//����ǰѡ����ͼ�Σ����޸�ѡ��ͼ�ε���ɫ
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.gradientFillColor=new GradPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,DrawHandler.currentSelectedShapePro.gradientFillColor.color1,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,selected);
					frame.fillColorIndicator2.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				//�����޸�ϵͳ�Ļ�ͼĬ����ɫ
				else{
					frame.currentPaintProperty.currentGradientFillColor.color2=selected;
					frame.fillColorIndicator2.setBackground(selected);
				}
			}
		}
	}
}
