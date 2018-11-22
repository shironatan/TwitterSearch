package twitter;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class Twitter {
	public TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

	public void Tw(String C1,String C2,String C3){

		DBconnection con = new DBconnection();
		con.getDBconnection();
		con.getDelete();

        StatusListener listener = new StatusListener() {

            @Override


            public void onStatus(Status status) {

            	//starテーブルの内容削除


            	//フォロー・フォロワー
            	//System.out.println("フォロー" + status.getUser().getFriendsCount());
            	con.getfollow(status.getUser().getFriendsCount());

            	//System.out.println("フォロワー" + status.getUser().getFollowersCount());
            	con.getfollower(status.getUser().getFollowersCount());

            	//System.out.println("ツイート数" + status.getUser().getStatusesCount());
            	con.gettweetcount(status.getUser().getStatusesCount());

            	//System.out.println("アカウント作成日" + status.getUser().getCreatedAt());
            	con.getcreateat(status.getUser().getCreatedAt());

            	//System.out.println("リスト数" + status.getUser().getListedCount());
            	con.getlist(status.getUser().getListedCount());

                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                String id = status.getUser().getScreenName();
                String text = status.getText();

                //分岐点
                int select = con.getCount(id);
                int cc1 = 0;
            	int cc2 = 0;
            	int cc3 = 0;
                if(select == 1 & !(text.indexOf("RT") != -1)) {
                	if(text.indexOf(C1) != -1){
                		cc1 = 1;
                	};
                	if(text.indexOf(C2) != -1 & !(text.indexOf("RT") != -1)) {
                		cc2 = 1;
                	}
                	if(text.indexOf(C3) != -1 & !(text.indexOf("RT") != -1)) {
                		cc3 = 1;
                	}
                	con.getUpdate(id, cc1, cc2, cc3);

                }else if(!(text.indexOf("RT") != -1)){
                	if(text.indexOf(C1) != -1 & !(text.indexOf("RT") != -1)) {
                		cc1 = 1;
                	};
                	if(text.indexOf(C2) != -1 & !(text.indexOf("RT") != -1)) {
                		cc2 = 1;
                	}
                	if(text.indexOf(C3) != -1 & !(text.indexOf("RT") != -1)) {
                		cc3 = 1;
                	}
                	con.getInsert(id, cc1, cc2, cc3);
                }

            }



            @Override

            public void onException(Exception ex) {

                ex.printStackTrace();

            }



			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO 自動生成されたメソッド・スタブ

			}



			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO 自動生成されたメソッド・スタブ

			}



			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO 自動生成されたメソッド・スタブ

			}



			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO 自動生成されたメソッド・スタブ

			}


        };

        twitterStream.addListener(listener);

        //フィルター
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(new String[] {C1,C2,C3});
        twitterStream.filter(filterQuery);

        }

    }