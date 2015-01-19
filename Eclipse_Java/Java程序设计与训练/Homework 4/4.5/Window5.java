import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window5 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("4.5");
		final JTextArea text = new JTextArea("");
		frame.add(new JScrollPane(text,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
		text.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				text.setText("");
				if (e.getButton() == 1)
					text.append("������\r\n");
				else
					text.append("����Ҽ�\r\n");
				text.append("���λ��:");
				text.append("X=" + e.getX() + " Y=" + e.getY() + "\r\n");
				text.append("�������" + e.getClickCount());
			}
		});
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
