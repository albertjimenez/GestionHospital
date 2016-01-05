package modelo.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class T {
	public static void main(String[] args) {
		JFrame v = new JFrame();
		JPanel p = new JPanel();
		JButton bot = new JButton("Hola");
		bot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
