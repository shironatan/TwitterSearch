package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBconnection {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement st;
	ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10,rs11;
	int i,i1,i2,i3,i4,i5,i6;

	public  void getDBconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//DB接続先
			String url = "";
			String user = "";
			String pass = "";

			conn = DriverManager.getConnection(url,user,pass);
			st = conn.createStatement();
			System.out.println("DBconnection...");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void UnConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void getfollow(int friendsCount) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			rs = st.executeQuery("select MAX(No) from follow where "+ friendsCount + " >= Follow");
			while(rs.next()){
			    i = rs.getInt("MAX(No)");
			  }
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void getfollower(int followersCount) {
		try {
			rs1 = st.executeQuery("select MAX(No) from follower where "+ followersCount + " >= Follower");
			while(rs1.next()){
			    i1 = rs1.getInt("MAX(No)");
			  }
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void gettweetcount(int statusesCount) {
		try {
			rs2 = st.executeQuery("select MAX(id) from tweet where "+ statusesCount + " >= tweet");
			while(rs2.next()){
			    i2 = rs2.getInt("MAX(id)");
			  }
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void getcreateat(Date createdAt) {
		try {
			SimpleDateFormat format5 = new SimpleDateFormat("yyyy");
		    String yyyyFormat = format5.format( createdAt );
		    int year = Integer.parseInt(yyyyFormat);
			rs3 = st.executeQuery("select MAX(No) from createdate where " + year + " >= date");
			while(rs3.next()){
			    i3 = rs3.getInt("MAX(No)");
			  }
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void getlist(int listedCount) {
		try {
			rs4 = st.executeQuery("select MAX(No) from list where " + listedCount + " >= list");
			while(rs4.next()){
			    i4 = rs4.getInt("MAX(No)");
			  }
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public int getCount(String id) {
		try {
			rs5 = st.executeQuery("SELECT COUNT(*) FROM star where id = '" + id + "'");
			while(rs5.next()) {
				i5 = rs5.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return i5;
	}

	public void getDelete() {
		String sql = "delete from star";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void getUpdate(String id,int C1,int C2,int C3) {
		String sql = "UPDATE star SET check1 = check1 + " + C1 + ",check2 = check2 + " + C2 + ",check3 = check3 + " + C3 + " WHERE id = '" + id + "'";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public void getInsert(String id,int C1,int C2,int C3) {
		String sql = "INSERT INTO star (id,follow,follower,tweet,createdate,list,check1,check2,check3) VALUES(?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, id);
			ps.setInt(2, i);
			ps.setInt(3, i1);
			ps.setInt(4, i2);
			ps.setInt(5, i3);
			ps.setInt(6, i4);
			ps.setInt(7, C1);
			ps.setInt(8, C2);
			ps.setInt(9, C3);
			try {
				ps.executeUpdate();
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//followテーブルのランキング
	public String[] rankfollow(String check) {
		//SQL文
		String sql = ("SELECT t1.name as rank1,COUNT(*) AS rank2 from follow as t1,star as t2 "
				+ "WHERE t1.No = t2.follow "
				+ "AND t2." + check + " >= 1 "
				+ "GROUP BY t1.No "
				+ "ORDER BY COUNT(*) desc limit 3");
		try {
				rs6 = st.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] rankfollow = new String[6];
			int i = 0;
			try {
				while(rs6.next()){
					String rank1 = rs6.getString("rank1");
					String rank2 = rs6.getString("rank2");
					rankfollow[i] = rank1;
					rankfollow[i+1] = rank2;
					i = i + 2;
				  }
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return rankfollow;
	}

	//followerテーブルのランキング
	public String[] rankfollower(String check) {
		//SQL文
		String sql = ("SELECT t1.name as rank3, COUNT(*) as rank4 from follower as t1,star as t2 "
				+ "WHERE t1.No = t2.follower "
				+ "AND t2." + check + " >= 1 "
				+ "GROUP BY t1.No "
				+ "ORDER BY COUNT(*) desc limit 3");
			try {
				rs7 = st.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] rankfollower = new String[6];
			int i = 0;
			try {
				while(rs7.next()){
					String rank3 = rs7.getString("rank3");
					String rank4 = rs7.getString("rank4");
					rankfollower[i] = rank3;
					rankfollower[i+1] = rank4;
 					i = i + 2;
				  }
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return rankfollower;
	}

	//tweetテーブルのランキング
	public String[] ranktweet(String check) {
		//SQL文
		String sql = ("SELECT t1.name as rank3,COUNT(*) as rank4 from tweet as t1,star as t2 "
				+ "WHERE t1.id = t2.tweet "
				+ "AND t2." + check + " >= 1 "
				+ "GROUP BY t1.id "
				+ "ORDER BY COUNT(*) desc limit 3");
			try {
				rs8 = st.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] ranktweet = new String[6];
			int i = 0;
			try {
				while(rs8.next()){
					String rank3 = rs8.getString("rank3");
					String rank4 = rs8.getString("rank4");
					ranktweet[i] = rank3;
					ranktweet[i+1] = rank4;
					i = i + 2;
				  }
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return ranktweet;
	}

	//createdateテーブルのランキング
	public String[] rankcreatedate(String check) {
		//SQL文
		String sql = ("SELECT t1.name as rank4,COUNT(*) as rank5 from createdate as t1,star as t2 "
				+ "WHERE t1.No = t2.createdate "
				+ "AND t2." + check + " >= 1 "
				+ "GROUP BY t1.No "
				+ "ORDER BY COUNT(*) desc limit 3");
			try {
				rs9 = st.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] rankcreatedate = new String[6];
			int i = 0;
			try {
				while(rs9.next()){
					String rank4 = rs9.getString("rank4");
					String rank5 = rs9.getString("rank5");
					rankcreatedate[i] = rank4;
					rankcreatedate[i+1] = rank5;
					i = i + 2;
				  }
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return rankcreatedate;
	}

	//listテーブルのランキング
	public String[] ranklist(String check) {
		//SQL文
		String sql = ("SELECT t1.name as rank5,COUNT(*) as rank6 from list as t1,star as t2 "
				+ "WHERE t1.No = t2.list "
				+ "AND t2." + check + " >= 1 "
				+ "GROUP BY t1.No "
				+ "ORDER BY COUNT(*) desc limit 3");
			try {
				rs10 = st.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] ranklist = new String[6];
			int i = 0;
			try {
				while(rs10.next()){
					String rank5 = rs10.getString("rank5");
					String rank6 = rs10.getString("rank6");
					ranklist[i] = rank5;
					ranklist[i + 1] = rank6;
					i = i + 2;
				  }
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return ranklist;
	}

	//テーブル数
	public int getCount(){
		String sql = ("SELECT COUNT(*) as count from star");
		try {
			rs10 = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int i = 0;
		try {
			while(rs10.next()) {
				i = rs10.getInt("count");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return i;

	}

	//ツイート数


	//check1とcheck2が同一
	public int getch12() {
		String sql = ("SELECT COUNT(*) as count from star WHERE check1 >= 1 and check2 >= 1");
		try {
			rs10 = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int i = 0;
		try {
			while(rs10.next()) {
				i = rs10.getInt("count");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return i;
	}

	//check2とcheck3が同一
	public int getch23() {
		String sql = ("SELECT COUNT(*) as count from star WHERE check2 >= 1 and check3 >= 1");
		try {
			rs10 = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int i = 0;
		try {
			while(rs10.next()) {
				i = rs10.getInt("count");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return i;
	}
	//check1とcheck3が同一
	public int getch13() {
		String sql = ("SELECT COUNT(*) as count from star WHERE check1 >= 1 and check2 >= 3");
		try {
			rs10 = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int i = 0;
		try {
			while(rs10.next()) {
				i = rs10.getInt("count");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return i;
	}

	//すべて同一
	public int getch123() {
		String sql = ("SELECT COUNT(*) as count from star WHERE check1 >= 1 and check2 >= 1 and check3 >= 1");
		try {
			rs10 = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int i = 0;
		try {
			while(rs10.next()) {
				i = rs10.getInt("count");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return i;
	}

	//followテーブルのリスト
	public String[] getfollowlist() {
		String sql = ("SELECT name from follow");
			try {
				rs10 = st.executeQuery(sql);
			} catch (SQLTimeoutException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] list = new String[15];
			int i = 0;
			try {
				while(rs10.next()){
					String colum = rs10.getString("name");
					list[i] = colum;
					i++;
				  }
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return list;
	}

	//followerテーブルのリスト
		public String[] getfollowerlist() {
			String sql = ("SELECT name from follower");
				try {
					rs10 = st.executeQuery(sql);
				} catch (SQLTimeoutException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				String[] list = new String[15];
				int i = 0;
				try {
					while(rs10.next()){
						String colum = rs10.getString("name");
						list[i] = colum;
						i++;
					  }
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				return list;
		}

		//tweetテーブルのリスト
		public String[] gettweetlist() {
			String sql = ("SELECT name from tweet");
				try {
					rs10 = st.executeQuery(sql);
				} catch (SQLTimeoutException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				String[] list = new String[16];
				int i = 0;
				try {
					while(rs10.next()){
						String colum = rs10.getString("name");
						list[i] = colum;
						i++;
					  }
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				return list;
		}

		//createdateテーブルのリスト
		public String[] getcreatedatelist() {
			String sql = ("SELECT name from createdate");
				try {
					rs10 = st.executeQuery(sql);
				} catch (SQLTimeoutException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				String[] list = new String[15];
				int i = 0;
				try {
					while(rs10.next()){
						String colum = rs10.getString("name");
						list[i] = colum;
						i++;
					  }
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				return list;
		}

		//listテーブルのリスト
		public String[] getlistlist() {
			String sql = ("SELECT name from list");
				try {
					rs10 = st.executeQuery(sql);
				} catch (SQLTimeoutException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				String[] list = new String[6];
				int i = 0;
				try {
					while(rs10.next()){
						String colum = rs10.getString("name");
						list[i] = colum;
						i++;
					  }
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				return list;
		}

		//check
		public int getcheck(int v1,int v2,int v3,int v4,int v5,int v6) {
			String sql = ("SELECT COUNT(*) as count from star " +
					"WHERE follow = " + v1 + " and follower = " + v2 +
					" and tweet = " + v3 + " and createdate = " + v4 +
					" and list = " + v5 + " and check" + v6 + " >= 1");
			try {
				rs10 = st.executeQuery(sql);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			int i = 0;
			try {
				while(rs10.next()) {
					i = rs10.getInt("count");
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			return i;

		}
}
