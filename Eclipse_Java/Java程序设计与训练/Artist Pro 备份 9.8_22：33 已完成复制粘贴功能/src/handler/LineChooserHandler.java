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
			String patternName=frame.currentPaintProperty.currentStroke.patternName;
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				float[] dashPattern=DrawHandler.currentSelectedShapePro.stroke.pattern;
				DrawHandler.currentSelectedShapePro.stroke=new BasStroke(value,dashPattern,patternName);
				frame.lineWidthIndicator2.setText(""+value);
				frame.canvas.repaint();
				frame.previewCanvas.repaint();
			}
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
	}
}

