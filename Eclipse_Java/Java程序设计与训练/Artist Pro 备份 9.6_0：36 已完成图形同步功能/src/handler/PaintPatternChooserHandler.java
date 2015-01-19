package handler;
import java.awt.*;
import javax.swing.*;
import frame.*;

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
			frame.currentPaintProperty.currentLined=true;
			frame.currentPaintProperty.currentFillType=FillType.NONE;
			frame.previewCanvas.repaint();
		}
	}
	public static class FillOnlyButton implements ActionListener{
		MainFrame frame;
		public FillOnlyButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentPaintProperty.currentLined=false;
			frame.currentPaintProperty.currentFillType=FillType.SOLID;
			frame.previewCanvas.repaint();
		}
	}
	public static class LineAndFillButton implements ActionListener{
		MainFrame frame;
		public LineAndFillButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentPaintProperty.currentLined=true;
			frame.currentPaintProperty.currentFillType=FillType.SOLID;
			frame.previewCanvas.repaint();
		}
	}
}
