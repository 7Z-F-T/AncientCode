package handler;
import frame.*;
import java.awt.event.*;
/**
 * ��������ѡ��Ի����¼�������
 * @author ���
 *
 */
public class LineStyleOptionDialogHandler{
	public static class Ok implements ActionListener{
		MainFrame frame;
		LineStyleOptionDialog dialog;
		float[] continuum={3000};
		float[] dash={15,5,15,5,15,5,15,5};
		float[] dash_dot={10,3,2,3,10,3,2,3};
		float[] dot={2,3,2,3,2,3,2,3};
		public Ok(LineStyleOptionDialog dial,MainFrame frm){
			dialog=dial;
			frame=frm;
		}
		//������ѡ�Ĳ�ͬ���͸�������
		public void actionPerformed(ActionEvent e){
			if(dialog.style1.isSelected()){
				//��ѡ����ͼ�Σ��������ѡͼ�ε�����
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.stroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),continuum,new String("Continuum"));
					frame.lineStyleIndicator2.setText("Continuum");
					frame.previewCanvas.repaint();
					frame.canvas.repaint();
					frame.repaintFlip.setFlip();
					dialog.setVisible(false);
				}
				//�������ϵͳĬ�ϻ�ͼ����
				else{
					frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),continuum,new String("Continuum"));
					frame.lineStyleIndicator2.setText("Continuum");
					frame.previewCanvas.repaint();
					dialog.setVisible(false);
				}
				
			}
			else if(dialog.style2.isSelected()){
				//��ѡ����ͼ�Σ��������ѡͼ�ε�����
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.stroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dash,new String("Dash"));
					frame.lineStyleIndicator2.setText("Dash");
					frame.previewCanvas.repaint();
					frame.canvas.repaint();
					frame.repaintFlip.setFlip();
					dialog.setVisible(false);
				}
				//�������ϵͳĬ�ϻ�ͼ����
				else{
					frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dash,new String("Dash"));
					frame.lineStyleIndicator2.setText("Dash");
					frame.previewCanvas.repaint();
					dialog.setVisible(false);
				}
			}
			else if(dialog.style3.isSelected()){
				//��ѡ����ͼ�Σ��������ѡͼ�ε�����
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.stroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dash_dot,new String("Dash-Dot"));
					frame.lineStyleIndicator2.setText("Dash-Dot");
					frame.previewCanvas.repaint();
					frame.canvas.repaint();
					frame.repaintFlip.setFlip();
					dialog.setVisible(false);
				}
				//�������ϵͳĬ�ϻ�ͼ����
				else{
					frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dash_dot,new String("Dash-Dot"));
					frame.lineStyleIndicator2.setText("Dash-Dot");
					frame.previewCanvas.repaint();
					dialog.setVisible(false);
				}
			}
			else if(dialog.style4.isSelected()){
				//��ѡ����ͼ�Σ��������ѡͼ�ε�����
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.stroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dot,new String("Dot"));
					frame.lineStyleIndicator2.setText("Dot");
					frame.previewCanvas.repaint();
					frame.canvas.repaint();
					frame.repaintFlip.setFlip();
					dialog.setVisible(false);
				}
				//��ѡ����ͼ�Σ��������ѡͼ�ε�����
				else{
					frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dot,new String("Dot"));
					frame.lineStyleIndicator2.setText("Dot");
					frame.previewCanvas.repaint();
					dialog.setVisible(false);
				}
			}
		}
	}
		public static class Cancel implements ActionListener{
			LineStyleOptionDialog dialog;
			public Cancel(LineStyleOptionDialog dial){
				dialog=dial;
			}
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
			}
	    }
}
