package com.uprr.netcontrol.training.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.core.JmsTemplate;

public class ChatUI {

	private ScrollingPanel m_scrollingPanel;
	private JmsTemplate m_jmsTemplate;
	private JComboBox m_comboBox;
	
	public ChatUI (final String displayName, final JmsTemplate template, ScrollingPanel scrollingPanel, final String myQueueName, final JmsAdminUtils jmsAdminUtils) {
		m_scrollingPanel = scrollingPanel;
		m_jmsTemplate = template;
		final JFrame frame = new JFrame(displayName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		JPanel panel = new JPanel();
		JTextArea messageText = new JTextArea();
		messageText.setEditable(false);
		messageText.setPreferredSize(new Dimension(100, 100));
		JLabel label = new JLabel("Enter Text");
		final JTextField tf = new JTextField(10);
		String[] queueNames = jmsAdminUtils.getQueueNames(myQueueName);
		m_comboBox = new JComboBox(queueNames);
		m_comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				String chattingWith = m_comboBox.getSelectedItem().toString();
				m_scrollingPanel.setMessage(m_scrollingPanel.getMessageHistory(chattingWith));
			}
		});
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final String messageText = tf.getText();
				addIncomingMessage(messageText, displayName, "CHAT." + m_comboBox.getSelectedItem().toString());
				tf.setText("");
				m_jmsTemplate.send("CHAT." + m_comboBox.getSelectedItem().toString(), new ChatMessageCreator(messageText, displayName, myQueueName));
			}

		});
		panel.add(label);
		panel.add(tf);
		panel.add(send);
		panel.add(scrollingPanel);
		frame.getContentPane().add(BorderLayout.NORTH,scrollingPanel);
		frame.getContentPane().add(BorderLayout.SOUTH,panel);
		frame.getContentPane().add(BorderLayout.CENTER,m_comboBox);
		frame.setVisible(true);
	}

	public void addIncomingMessage(String text, String userThatSentMessage, String chattingTo) {
		m_scrollingPanel.addMessage(text, userThatSentMessage, chattingTo);
	}
	
	public void changeUser(String inboundQueueName) {
		String namePart = StringUtils.substringAfter(inboundQueueName, "CHAT.");
		for (int i=0; i<m_comboBox.getItemCount(); i++) {
			String name = m_comboBox.getItemAt(i).toString();
			if(name.equals(namePart)) {
				m_comboBox.setSelectedIndex(i);
				break;
			}
		}
		m_scrollingPanel.setMessage(m_scrollingPanel.getMessageHistory(namePart));
	}
}
