import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
public class Test extends JFrame {
private JFileChooser chooser = new JFileChooser();
private JTextPane textPane = new JTextPane();

public Test() {
Container contentPane = getContentPane();
JMenuBar menuBar = new JMenuBar();
JMenu insertMenu = new JMenu("Insert");
JMenuItem imageItem = new JMenuItem("image"),
chooserItem = new JMenuItem("color chooser");

insertMenu.add(imageItem);
insertMenu.add(chooserItem);

menuBar.add(insertMenu);
setJMenuBar(menuBar);

textPane.setFont(new Font("Serif", Font.ITALIC, 24));

contentPane.add(textPane, BorderLayout.CENTER);

chooserItem.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
JColorChooser chooser = new JColorChooser();
chooser.setMaximumSize(
chooser.getPreferredSize());
textPane.insertComponent(chooser);
}
});
imageItem.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
int option = 
chooser.showDialog(Test.this,"Pick An Image");

if(option == JFileChooser.APPROVE_OPTION) {
File file = chooser.getSelectedFile();

if(file != null) {
textPane.insertIcon(new ImageIcon(
file.getPath()));
}

}
}
});
}
public static void main(String args[]) {
GJApp.launch(new Test(), 
"Using JTextPane",300,300,450,300);
}
}
class GJApp extends WindowAdapter {
static private JPanel statusArea = new JPanel();
static private JLabel status = new JLabel(" ");

public static void launch(final JFrame f, String title,
final int x, final int y, 
final int w, int h) {
f.setTitle(title);
f.setBounds(x,y,w,h);
f.setVisible(true);

statusArea.setBorder(BorderFactory.createEtchedBorder());
statusArea.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
statusArea.add(status);
status.setHorizontalAlignment(JLabel.LEFT);

f.setDefaultCloseOperation(
WindowConstants.DISPOSE_ON_CLOSE);

f.addWindowListener(new WindowAdapter() {
public void windowClosed(WindowEvent e) {
System.exit(0);
}
});
}
static public JPanel getStatusArea() {
return statusArea;
}
static public void updateStatus(String s) {
status.setText(s);
}
}
