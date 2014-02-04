package examples;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class TweetSearch {
    
    public static void main(String[] args) {
    	// The factory instance is re-useable and thread safe.
        Twitter twitter = TwitterFactory.getSingleton();
        
        Query query = new Query("dieudonné");
        QueryResult result;
        
		try {
			
			result = twitter.search(query);
	        for (Status status : result.getTweets()) {
	            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	        }
	        
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
