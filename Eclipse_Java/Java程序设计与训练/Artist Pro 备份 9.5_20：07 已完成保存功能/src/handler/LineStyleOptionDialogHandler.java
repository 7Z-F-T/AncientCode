package handler;

import frame.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

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
		public void actionPerformed(ActionEvent e){
			if(dialog.style1.isSelected()){
				//BasicStroke stroke=new BasicStroke(frame.lineWidthChooserSlider.getValue(),BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f);
				frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),continuum);
				frame.lineStyleIndicator2.setText("Continuum");
				frame.previewCanvas.repaint();
				dialog.setVisible(false);
			}
			else if(dialog.style2.isSelected()){
				//BasicStroke stroke=new BasicStroke(frame.lineWidthChooserSlider.getValue(),BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,dash,0);
				frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dash);
				frame.lineStyleIndicator2.setText("Dash");
				frame.previewCanvas.repaint();
				dialog.setVisible(false);
			}
			else if(dialog.style3.isSelected()){
				//BasicStroke stroke=new BasicStroke(frame.lineWidthChooserSlider.getValue(),BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,dash_dot,0);
				frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dash_dot);
				frame.lineStyleIndicator2.setText("Dash-Dot");
				frame.previewCanvas.repaint();
				dialog.setVisible(false);
			}
			else if(dialog.style4.isSelected()){
				//BasicStroke stroke=new BasicStroke(frame.lineWidthChooserSlider.getValue(),BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,dot,0);
				frame.currentPaintProperty.currentStroke=new BasStroke(frame.lineWidthChooserSlider.getValue(),dot);
				frame.lineStyleIndicator2.setText("Dot");
				frame.previewCanvas.repaint();
				dialog.setVisible(false);
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
