package console;
import frame.*;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
public class Console {
	public MainFrame frame;
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			ch.randelshofer.quaqua.util.Methods.invokeStatic(JFrame.class,"setDefaultLookAndFeelDecorated", Boolean.TYPE, Boolean.TRUE);
            ch.randelshofer.quaqua.util.Methods.invokeStatic(JDialog.class,"setDefaultLookAndFeelDecorated", Boolean.TYPE, Boolean.TRUE);
		}
		catch(Exception e){
			System.err.println("Error changing look-and-feel: "+e.getMessage());
		}
		//UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels(); 
		//for(int i=0;i<info.length;i++)
		//	System.out.println(info[i]);
		//All usable look and feel
		//javax.swing.UIManager$LookAndFeelInfo[Metal javax.swing.plaf.metal.MetalLookAndFeel]
		//javax.swing.UIManager$LookAndFeelInfo[CDE/Motif com.sun.java.swing.plaf.motif.MotifLookAndFeel]
		//javax.swing.UIManager$LookAndFeelInfo[Windows com.sun.java.swing.plaf.windows.WindowsLookAndFeel]
		//javax.swing.UIManager$LookAndFeelInfo[Windows Classic com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel]

		Console console=new Console();
		MainFrame frame=new MainFrame();
		frame.initialize();
		SwingUtilities.updateComponentTreeUI(frame);
		frame.run();		
	}
}
