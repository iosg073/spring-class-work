package com.uprr.netcontrol.training.chat;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;

public class ScrollingPanel extends JPanel
implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Map<String, String> messagingMap;

	private JTextArea textArea;

	public ScrollingPanel() {
		super(new BorderLayout());

		textArea = new JTextArea();
		textArea.setEditable(false);

		//Put the drawing area in a scroll pane.
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setPreferredSize(new Dimension(200,200));

		//Lay out this demo.
		add(scroller, BorderLayout.CENTER);
		messagingMap = new HashMap<String, String>();
	}
	
	public void addMessage(String text, String userThatSentMessage, String chattingTo) {
		String s = userThatSentMessage + ": " + text;
		textArea.setText(getMessageHistory(StringUtils.substringAfter(chattingTo, "CHAT.")));
		if(textArea.getText()==null || "".equals(textArea.getText())) {
			textArea.setText(s);
		} else {
			textArea.setText(textArea.getText() + "\r\n" + s);
		}
		messagingMap.put(chattingTo, getMessage());
	}
	
	public String getMessage() {
		return textArea.getText();
	}
	
	public void setMessage(String message) {
		textArea.setText(message);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public String getMessageHistory(String chattingWith) {
		return messagingMap.get("CHAT." + chattingWith);
	}

}
