import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SoundPlay extends Applet{
	public void init(){
		//UI
		setLayout(new BorderLayout());
		final JList list=new JList();
		JButton play=new JButton("Play");
		JButton stop=new JButton("Stop");
		JButton playLoop=new JButton("Play loop");
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(play);
		panel.add(playLoop);
		panel.add(stop);
		add(panel,"South");
		add(list,"Center");
		//Fill the list
		DefaultListModel listModel=new DefaultListModel();
		listModel.addElement(getParameter("SoundFile1"));
		listModel.addElement(getParameter("SoundFile2"));
		listModel.addElement(getParameter("SoundFile3"));
		listModel.addElement(getParameter("SoundFile4"));
		listModel.addElement(getParameter("SoundFile5"));
		list.setModel(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//deal with events
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getAudioClip(getDocumentBase(),list.getSelectedValue().toString()).play();
	       	}
		});
		stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getAudioClip(getDocumentBase(),list.getSelectedValue().toString()).stop();
	       	}
		});
		playLoop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getAudioClip(getDocumentBase(),list.getSelectedValue().toString()).loop();
	       	}
		});
	}
}
