package handler;
import frame.*;
import handler.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class ColorChooserHandler {
	public static class LineColorChooser implements ActionListener{
		MainFrame frame;
		public LineColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Color selected=JColorChooser.showDialog(frame,"Set Line Color",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.lineColor=selected;
					frame.lineColorIndicator.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				else{
					frame.currentPaintProperty.currentLineColor=selected;
					frame.lineColorIndicator.setBackground(selected);
				}
			}
		}
	}
	public static class FillColorChooser implements ActionListener{
		MainFrame frame;
		public FillColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Color selected=JColorChooser.showDialog(frame,"Set Fill Color 1",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.fillColor=selected;
					DrawHandler.currentSelectedShapePro.gradientFillColor=new GradPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,selected,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					frame.fillColorIndicator.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				else{
					frame.currentPaintProperty.currentFillColor=selected;
					frame.fillColorIndicator.setBackground(selected);
				}
			}
		}
	}
	public static class FillColorChooser2 implements ActionListener{
		MainFrame frame;
		public FillColorChooser2(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Color selected=JColorChooser.showDialog(frame,"Set Fill Color 2",frame.currentPaintProperty.currentLineColor);
			if(selected!=null){
				if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
					frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
					DrawHandler.currentSelectedShapePro.gradientFillColor=new GradPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,DrawHandler.currentSelectedShapePro.gradientFillColor.color1,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,selected);
					frame.fillColorIndicator2.setBackground(selected);
					frame.repaintFlip.setFlip();
				}
				else{
					frame.currentPaintProperty.currentGradientFillColor.color2=selected;
					frame.fillColorIndicator2.setBackground(selected);
				}
			}
		}
	}
}
