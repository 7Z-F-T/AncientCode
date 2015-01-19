package handler;
import frame.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.util.*;
public class LineChooserHandler {
	public static class LineStyleChooser implements ActionListener{
		MainFrame frame;
		public LineStyleChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			LineStyleOptionDialog dialog=new LineStyleOptionDialog(frame);
		}
	}
	public static class LineWidthChooser implements ChangeListener{
		MainFrame frame;
		public LineWidthChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			float[] dashPattern=frame.currentPaintProperty.currentStroke.getDashArray();
			float phase=frame.currentPaintProperty.currentStroke.getDashPhase();
			frame.currentPaintProperty.currentStroke=new BasicStroke(value,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,dashPattern,0);
			frame.lineWidthIndicator2.setText(""+value);
			frame.previewCanvas.repaint();
	    }
	}
}

