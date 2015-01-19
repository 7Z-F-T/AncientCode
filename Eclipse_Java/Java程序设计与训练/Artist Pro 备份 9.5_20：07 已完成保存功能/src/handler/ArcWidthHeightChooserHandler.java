package handler;
import frame.*;
import handler.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class ArcWidthHeightChooserHandler {
	public static class ArcWidthChooser implements ChangeListener{
		MainFrame frame;
		public ArcWidthChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			frame.currentPaintProperty.currentArcWidth=value;
			frame.arcWidthIndicator.setText(""+value);
			frame.previewCanvas.repaint();
		}
	}
	public static class ArcHeightChooser implements ChangeListener{
		MainFrame frame;
		public ArcHeightChooser(MainFrame frm){
			frame=frm;
		}
		public void stateChanged(ChangeEvent e){
			JSlider slider=(JSlider)e.getSource();
			int value=slider.getValue();
			frame.currentPaintProperty.currentArcHeight=value;
			frame.arcHeightIndicator.setText(""+value);
			frame.previewCanvas.repaint();
		}
	}
}
