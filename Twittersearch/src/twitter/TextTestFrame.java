package twitter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class TextTestFrame extends JFrame{
	public Twitter tw = new Twitter();
	TextTestFrame2 sp = new TextTestFrame2();
	JPanel panel = new JPanel();
	public JTextField C1 = new JTextField();
	public JTextField C2 = new JTextField();
	public JTextField C3 = new JTextField();



	public TextTestFrame() {

		setTitle("TwitterSearch");
		setSize(900,620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton button = new JButton("読み込み開始");
		button.setBackground(Color.PINK);
		final JButton button2 = new JButton("読み込み終了");
		button2.setBackground(Color.PINK);

		panel.setLayout(new GridLayout(5,2));
		panel.add(new JLabel("検索１：",SwingConstants.LEFT));
		panel.add(C1);
		panel.add(new JLabel("検索２：",SwingConstants.LEFT));
		panel.add(C2);
		panel.add(new JLabel("検索３：",SwingConstants.LEFT));
		panel.add(C3);
		panel.add(button);
		panel.add(button2);
		add(sp);
		sp.setVisible(false);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String c1 = "";
				c1 = C1.getText();
				String c2 = "";
				c2 = C2.getText();
				String c3 = "";
				c3 = C3.getText();
				//入力値検査
				if(c1.equals("") && c2.equals("") && c3.equals("")) {
					JOptionPane.showMessageDialog(panel, "項目は最低一つ必要です", "致命的なエラー",
							  JOptionPane.ERROR_MESSAGE);

				}else {

					System.out.println(c1 + " " + c2 + " " + c3 + "を調べます。");

					tw.Tw(c1,c2,c3);

				}


			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c1 = "";
				c1 = C1.getText();
				String c2 = "";
				c2 = C2.getText();
				String c3 = "";
				c3 = C3.getText();
				if(c1.equals("") && c2.equals("") && c3.equals("")) {
					JOptionPane.showMessageDialog(panel, "項目は最低一つ必要です", "致命的なエラー",
							  JOptionPane.ERROR_MESSAGE);
				}else {
					System.out.println(C1.getText());
				sp.getpanel(C1.getText(), C2.getText(), C3.getText());
				tw.twitterStream.shutdown();
				panel.setVisible(false);
				sp.setVisible(true);
				}
			}
		});
		add(panel,BorderLayout.NORTH);
	}
}
