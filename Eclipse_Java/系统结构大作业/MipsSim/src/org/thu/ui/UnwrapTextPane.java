package org.thu.ui;

import java.awt.Dimension;

import javax.swing.JTextPane;

public class UnwrapTextPane extends JTextPane {

	public boolean getScrollableTracksViewportWidth() {
		return (getSize().width < getParent().getSize().width - 100);
	}

	public void setSize(Dimension d) {
		if (d.width < getParent().getSize().width) {
			d.width = getParent().getSize().width;
		}
		d.width += 100;
		super.setSize(d);
	}
}
