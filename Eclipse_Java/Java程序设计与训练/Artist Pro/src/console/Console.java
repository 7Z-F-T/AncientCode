package console;
import frame.*;
import javax.swing.*;

/**
 * 实例化主窗口MainFrame，启动程序并实现程序的初始化
 * @author 侯杰
 *
 */
public class Console {
	public MainFrame frame;
	public static void main(String[] args) {
		//使用quaqua提供的外观
		try{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			ch.randelshofer.quaqua.util.Methods.invokeStatic(JFrame.class,"setDefaultLookAndFeelDecorated", Boolean.TYPE, Boolean.TRUE);
            ch.randelshofer.quaqua.util.Methods.invokeStatic(JDialog.class,"setDefaultLookAndFeelDecorated", Boolean.TYPE, Boolean.TRUE);
		}
		catch(Exception e){
			System.err.println("Error changing look-and-feel: "+e.getMessage());
		}
		//以下是本机上所有可用的其他外观
		//javax.swing.UIManager$LookAndFeelInfo[Metal javax.swing.plaf.metal.MetalLookAndFeel]
		//javax.swing.UIManager$LookAndFeelInfo[CDE/Motif com.sun.java.swing.plaf.motif.MotifLookAndFeel]
		//javax.swing.UIManager$LookAndFeelInfo[Windows com.sun.java.swing.plaf.windows.WindowsLookAndFeel]
		//javax.swing.UIManager$LookAndFeelInfo[Windows Classic com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel]

		//启动程序
		Console console=new Console();
		MainFrame frame=new MainFrame();
		frame.initialize();
		SwingUtilities.updateComponentTreeUI(frame);
		frame.run();		
	}
}
