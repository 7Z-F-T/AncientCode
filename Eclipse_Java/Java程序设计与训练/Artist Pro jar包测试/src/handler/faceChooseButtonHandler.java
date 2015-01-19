package handler;
import handler.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import frame.*;
public class faceChooseButtonHandler implements ActionListener{
	MainFrame frame;
	public faceChooseButtonHandler(MainFrame frm){
		frame=frm;
	}
	public void actionPerformed(ActionEvent e){
		faceChooseDialog faceChoose=new faceChooseDialog(frame);
	}
}
