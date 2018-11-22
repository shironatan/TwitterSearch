package twitter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TextTestFrame2 extends JPanel {
	DBconnection con = new DBconnection();
	String[][] tabledata1 = new String[3][11];
	String[][] tabledata2 = new String[3][11];
	String[][] tabledata3 = new String[3][11];
	DefaultTableModel model,model2,model3;

	public void getpanel(String C1,String C2,String C3) {
		setSize(800,500);
		setLayout(null);
		setBackground(Color.white);
		JLabel check1 = new JLabel(" DataSearchResult.");
		check1.setBounds(350,20,120,20);
		check1.setOpaque(true);
		check1.setBackground(Color.PINK);
		add(check1);


		String[] columnNames = {"フォロー数","件数",
				"フォロワー数","件数",
				"ツイート数","件数",
				"アカウント作成年","件数",
				"リスト保有数","件数"};




		/*
		 * 分析
		 */



		if(!(C1.equals(""))) {
		this.C1();
		int max = Integer.parseInt(tabledata1[0][1]);
		String maxcolum = columnNames[0];
		String maxname = tabledata1[0][0];
		int i = 3;
		while(i < 10) {
			int j = Integer.parseInt(tabledata1[0][i]);
			if(max < j){
				max = j;
				maxcolum = columnNames[i-1];
				maxname = tabledata1[0][i-1];
			}
			i = i + 2;
		}
		JLabel check2 = new JLabel("　" + C1 + "　をつぶやいた人："
				+ maxcolum +"が" + maxname + "の人が多くつぶやいています。");
		add(check2);
		check2.setBounds(10,50,800,20);

		model = new DefaultTableModel(tabledata1,columnNames);
		JTable table = new JTable(model);

		//カラムの横幅設定
		table.getColumnModel().getColumn(1).setPreferredWidth( 15 );
		table.getColumnModel().getColumn(3).setPreferredWidth( 15 );
		table.getColumnModel().getColumn(5).setPreferredWidth( 15 );
		table.getColumnModel().getColumn(7).setPreferredWidth( 15 );
		table.getColumnModel().getColumn(9).setPreferredWidth( 15 );
		table.setEnabled(false);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10,70,770,71);
		add(sp);
		}

		if(!(C2.equals(""))) {
			this.C2();
			//分析

			int max = Integer.parseInt(tabledata2[0][1]);
			String maxcolum = columnNames[0];
			String maxname = tabledata2[0][0];
			int i = 3;
			while(i < 10) {
				int j = Integer.parseInt(tabledata2[0][i]);
				if(max < j){
					max = j;
					maxcolum = columnNames[i-1];
					maxname = tabledata2[0][i-1];
				}
				i = i + 2;
			}
			JLabel check3 = new JLabel("　" + C2 + "　をつぶやいた人："
					+ maxcolum +"が" + maxname + "の人が多くつぶやいています。");
			add(check3);
			check3.setBounds(10,150,800,20);
			model2 = new DefaultTableModel(tabledata2,columnNames);
			JTable table2 = new JTable(model2);
			//カラムの横幅設定
			table2.getColumnModel().getColumn(1).setPreferredWidth( 15 );
			table2.getColumnModel().getColumn(3).setPreferredWidth( 15 );
			table2.getColumnModel().getColumn(5).setPreferredWidth( 15 );
			table2.getColumnModel().getColumn(7).setPreferredWidth( 15 );
			table2.getColumnModel().getColumn(9).setPreferredWidth( 15 );
			table2.setEnabled(false);
			JScrollPane sp2 = new JScrollPane(table2);
			sp2.setBounds(10,170,770,71);
			add(sp2);
			}

		if(!(C3.equals(""))) {
			this.C3();
			//分析
			int max = Integer.parseInt(tabledata3[0][1]);
			String maxcolum = columnNames[0];
			String maxname = tabledata3[0][0];
			int i = 3;
			while(i < 10) {
				int j = Integer.parseInt(tabledata3[0][i]);
				if(max < j){
					max = j;
					maxcolum = columnNames[i-1];
					maxname = tabledata3[0][i-1];
				}
				i = i + 2;
			}
			JLabel check4 = new JLabel("　" + C3 + "　をつぶやいた人："
					+ maxcolum +"が" + maxname + "の人が多くつぶやいています。");
			add(check4);
			check4.setBounds(10,260,800,20);

			model3 = new DefaultTableModel(tabledata3,columnNames);
			JTable table3 = new JTable(model3);
			//カラムの横幅設定
			table3.getColumnModel().getColumn(1).setPreferredWidth( 15 );
			table3.getColumnModel().getColumn(3).setPreferredWidth( 15 );
			table3.getColumnModel().getColumn(5).setPreferredWidth( 15 );
			table3.getColumnModel().getColumn(7).setPreferredWidth( 15 );
			table3.getColumnModel().getColumn(9).setPreferredWidth( 15 );

			table3.setEnabled(false);
			JScrollPane sp3 = new JScrollPane(table3);
			sp3.setBounds(10,280,770,71);
			add(sp3);
			}



		/*
		 * データマイニング
		 */

		String[] follow = con.getfollowlist();
		String[] follower = con.getfollowerlist();
		String[] tweet = con.gettweetlist();
		String[] createdate = con.getcreatedatelist();
		String[] list = con.getlistlist();
		String[] check = new String[3];
		int i = 0;
		if(!(C1.equals(""))){
			check[i] = C1;
			i++;
		}
		if(!(C2.equals(""))){
			check[i] = C2;
			i++;
		}
		if(!(C3.equals(""))){
			check[i] = C3;
			i++;
		}


		JComboBox followcb = new JComboBox(follow);
		JComboBox followercb = new JComboBox(follower);
		JComboBox tweetcb = new JComboBox(tweet);
		JComboBox createdatecb = new JComboBox(createdate);
		JComboBox listcb = new JComboBox(list);
		JComboBox checkcb = new JComboBox(check);

		JLabel l1 = new JLabel("フォロー数");
		JLabel l2 = new JLabel("フォロワー数");
		JLabel l3 = new JLabel("ツイート数");
		JLabel l4 = new JLabel("アカウント作成日");
		JLabel l5 = new JLabel("リスト保有数");
		JLabel l6 = new JLabel("検索文字");

		l1.setBounds(10,380,100,20);
		l2.setBounds(120,380,100,20);
		l3.setBounds(230,380,100,20);
		l4.setBounds(340,380,110,20);
		l5.setBounds(450,380,100,20);
		l6.setBounds(560,380,100,20);
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);


		followcb.setBounds(10,400,100,20);
		followcb.setOpaque(true);
		followcb.setBackground(Color.WHITE);

		followercb.setBounds(120,400,100,20);
		followercb.setOpaque(true);
		followercb.setBackground(Color.WHITE);

		tweetcb.setBounds(230,400,100,20);
		tweetcb.setOpaque(true);
		tweetcb.setBackground(Color.WHITE);

		createdatecb.setBounds(340,400,100,20);
		createdatecb.setOpaque(true);
		createdatecb.setBackground(Color.WHITE);

		listcb.setBounds(450,400,100,20);
		listcb.setOpaque(true);
		listcb.setBackground(Color.WHITE);

		checkcb.setBounds(560,400,100,20);
		checkcb.setOpaque(true);
		checkcb.setBackground(Color.WHITE);
		add(followcb);
		add(followercb);
		add(tweetcb);
		add(createdatecb);
		add(listcb);
		add(checkcb);

		JButton button3 = new JButton("検索");
		button3.setBounds(670,400,100,20);
		button3.setOpaque(true);
		button3.setBackground(Color.WHITE);
		add(button3);

		JLabel l7 = new JLabel();
		l7.setBounds(10, 420, 800, 20);
		add(l7);

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int v1 = (int)followcb.getSelectedIndex() + 1;
				int v2 = (int)followercb.getSelectedIndex() + 1;
				int v3 = (int)tweetcb.getSelectedIndex() + 1;
				int v4 = (int)createdatecb.getSelectedIndex() + 1;
				int v5 = (int)listcb.getSelectedIndex() + 1;
				int v6 = (int)checkcb.getSelectedIndex() + 1;
				int result =con.getcheck(v1, v2, v3, v4, v5, v6);
				l7.setText("結果： " + result + " 件のデータが一致しています。" );
			}
		});

		int v7 = con.getCount();
		JLabel l8 = new JLabel(v7 + "件のデータを調べた結果");
		l8.setBounds(10, 450, 800, 20);
		add(l8);
		/*
		 * 関係性
		 */
		JLabel l9 = new JLabel();
		l9.setBounds(10, 470, 800, 20);
		add(l9);
		JLabel l10 = new JLabel();
		l10.setBounds(10, 490, 800, 20);
		add(l10);
		JLabel l11 = new JLabel();
		l11.setBounds(10, 510, 800, 20);
		add(l11);
		JLabel l12 = new JLabel();
		l12.setBounds(10, 530, 800, 20);
		add(l12);

		if(!(C1.equals("")) & !(C2.equals("")) & !(C3.equals(""))){
			int v8 = con.getch123();
			l9.setText(C1 + "," + C2 + "," + C3 + " を呟いたアカウント：" + v8 + "件");
		}
		if(!(C1.equals("")) & !(C2.equals(""))){
			int v9 = con.getch12();
			l10.setText(C1 + "," + C2 + " を呟いたアカウント：" + v9 + "件");
		}
		if(!(C1.equals("")) & !(C3.equals(""))){
			int v10 = con.getch13();
			l11.setText(C1 + "," + C3 + " を呟いたアカウント：" + v10 + "件");
		}
		if(!(C2.equals("")) & !(C3.equals(""))) {
			int v11 = con.getch23();
			l12.setText(C2 + "," + C3 + " を呟いたアカウント：" + v11 + "件");
		}

		//戻るボタン
		JButton button4 = new JButton("検索画面へ戻る");
		button4.setBounds(350, 550, 150, 20);
		button4.setOpaque(true);
		button4.setBackground(Color.WHITE);
		add(button4);

		TextTestFrame panel = new TextTestFrame();
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panel.setVisible(true);
			}
		});


	}




	/*
	 * check1用
	*/



	public void C1() {
		con.getDBconnection();

		//followテーブル
		String[] C1follow = new String[6];
		C1follow = con.rankfollow("check1");
		int j = 0;
		for(int i = 0; i < 3; i++) {
			tabledata1[i][0] = C1follow[j];
			j++;
			tabledata1[i][1] = C1follow[j];
			j++;
		}


		//followerテーブル
		String[] C1follower = new String[6];
		C1follower = con.rankfollower("check1");
		int j1 = 0;
		for(int i = 0; i< 3; i++) {
			tabledata1[i][2] = C1follower[j1];
			j1++;
			tabledata1[i][3] = C1follower[j1];
			j1++;
		}

		//tweetテーブル
		String[] C1tweet = new String[6];
		C1tweet = con.ranktweet("check1");
		int j2 = 0;
		for(int i = 0; i < 3; i++) {
			tabledata1[i][4] = C1tweet[j2];
			j2++;
			tabledata1[i][5] = C1tweet[j2];
			j2++;
		}

		//createdateテーブル
		String[] C1createdate = new String[6];
		C1createdate = con.rankcreatedate("check1");
		int j3 = 0;
		for(int i = 0;i < 3;i++) {
			tabledata1[i][6] = C1createdate[j3];
			j3++;
			tabledata1[i][7] = C1createdate[j3];
			j3++;
		}

		//listテーブル
		String[] C1list = new String[6];
		C1list = con.ranklist("check1");
		int j4 = 0;
		for(int i = 0; i < 3; i++) {
			tabledata1[i][8] = C1list[j4];
			j4++;
			tabledata1[i][9] = C1list[j4];
			j4++;
		}
	}
	public void C2() {
		con.getDBconnection();

		//followテーブル
		String[] C1follow = new String[6];
		C1follow = con.rankfollow("check2");
		int j = 0;
		for(int i = 0; i < 3; i++) {
			tabledata2[i][0] = C1follow[j];
			j++;
			tabledata2[i][1] = C1follow[j];
			j++;
		}


		//followerテーブル
		String[] C1follower = new String[6];
		C1follower = con.rankfollower("check2");
		int j1 = 0;
		for(int i = 0; i< 3; i++) {
			tabledata2[i][2] = C1follower[j1];
			j1++;
			tabledata2[i][3] = C1follower[j1];
			j1++;
		}

		//tweetテーブル
		String[] C1tweet = new String[6];
		C1tweet = con.ranktweet("check2");
		int j2 = 0;
		for(int i = 0; i < 3; i++) {
			tabledata2[i][4] = C1tweet[j2];
			j2++;
			tabledata2[i][5] = C1tweet[j2];
			j2++;
		}

		//createdateテーブル
		String[] C1createdate = new String[6];
		C1createdate = con.rankcreatedate("check2");
		int j3 = 0;
		for(int i = 0;i < 3;i++) {
			tabledata2[i][6] = C1createdate[j3];
			j3++;
			tabledata2[i][7] = C1createdate[j3];
			j3++;
		}

		//listテーブル
		String[] C1list = new String[6];
		C1list = con.ranklist("check2");
		int j4 = 0;
		for(int i = 0; i < 3; i++) {
			tabledata2[i][8] = C1list[j4];
			j4++;
			tabledata2[i][9] = C1list[j4];
			j4++;
		}
	}
	public void C3() {
		con.getDBconnection();

		//followテーブル
		String[] C1follow = new String[6];
		C1follow = con.rankfollow("check3");
		int j = 0;
		for(int i = 0; i < 3; i++) {
			tabledata3[i][0] = C1follow[j];
			j++;
			tabledata3[i][1] = C1follow[j];
			j++;
		}


		//followerテーブル
		String[] C1follower = new String[6];
		C1follower = con.rankfollower("check3");
		int j1 = 0;
		for(int i = 0; i< 3; i++) {
			tabledata3[i][2] = C1follower[j1];
			j1++;
			tabledata3[i][3] = C1follower[j1];
			j1++;
		}

		//tweetテーブル
		String[] C1tweet = new String[6];
		C1tweet = con.ranktweet("check3");
		int j2 = 0;
		for(int i = 0; i < 3; i++) {
			tabledata3[i][4] = C1tweet[j2];
			j2++;
			tabledata3[i][5] = C1tweet[j2];
			j2++;
		}

		//createdateテーブル
		String[] C1createdate = new String[6];
		C1createdate = con.rankcreatedate("check3");
		int j3 = 0;
		for(int i = 0;i < 3;i++) {
			tabledata3[i][6] = C1createdate[j3];
			j3++;
			tabledata3[i][7] = C1createdate[j3];
			j3++;
		}

		//listテーブル
		String[] C1list = new String[6];
		C1list = con.ranklist("check3");
		int j4 = 0;
		for(int i = 0; i < 3; i++) {
			tabledata3[i][8] = C1list[j4];
			j4++;
			tabledata3[i][9] = C1list[j4];
			j4++;
		}
	}

}
