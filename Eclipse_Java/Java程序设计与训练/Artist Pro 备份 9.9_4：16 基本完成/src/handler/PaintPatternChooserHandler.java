package handler;
import java.awt.*;
import javax.swing.*;
import frame.*;

import frame.CopyShapeProArray;
import frame.Function;
import frame.MainFrame;

import java.awt.event.*;
import java.util.*;
public class PaintPatternChooserHandler {
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
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				frame.previewCanvas.repaint();
			}
			else{
				frame.currentPaintProperty.currentLined=true;
				frame.currentPaintProperty.currentFillType=FillType.NONE;
				frame.previewCanvas.repaint();
			}
		}
	}
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
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				frame.previewCanvas.repaint();
			}
			else{
				frame.currentPaintProperty.currentLined=false;
				frame.currentPaintProperty.currentFillType=FillType.SOLID;
				frame.previewCanvas.repaint();
			}
		}
	}
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
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				frame.previewCanvas.repaint();
			}
			else{
				frame.currentPaintProperty.currentLined=true;
				frame.currentPaintProperty.currentFillType=FillType.SOLID;
				frame.previewCanvas.repaint();
			}
			frame.previewCanvas.repaint();
		}
	}
}
