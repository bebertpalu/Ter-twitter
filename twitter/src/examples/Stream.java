package examples;

import java.util.List;

import twitter4j.*;

public class Stream {

	// https://dev.twitter.com/docs/streaming-apis/parameters for more information on parameters
	static private void public_stream() {
	    
	    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	    
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
	    
	}
	
	static private void public_stream_with_query() {
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	    
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
            	
            	User user = status.getUser();
                System.out.println("@" + user.getScreenName() + " - " + status.getText());
                
                Twitter twitter = TwitterFactory.getSingleton();
                List<Status> statuses;
				try {
					statuses = twitter.getUserTimeline(user.getScreenName(), new Paging(1, 10));
					System.out.println("\t"+statuses.size()+" statuses for user "+user.getScreenName()+".");
					
					for (Status timelineStatus : statuses) {
						System.out.println("\t**"+timelineStatus.getText()+" ("+timelineStatus.getCreatedAt()+")");
					}
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        
        /////////////////////////////////////
        // Query writing
        FilterQuery q = new FilterQuery();
        
        //   language (does not work?)
//        String[] lang = { "fr" };
//        q.language(lang);
        
        //   geolocation
        //    montpellier
        double lat1 = 43.579645;
        double lon1 = 3.821497;
        double lat2 = 43.637442;
        double lon2 = 3.935308;
        //    france
        double lat1_fr = 43.659924;
        double lon1_fr = -5.408936;
        double lat2_fr = 50.805935;
        double lon2_fr = 6.478271;
        
        // be careful with order of coordinates: (longitude, latitude)
        double[][] geolocations = { {lon1_fr, lat1_fr}, {lon2_fr, lat2_fr} };
        q.locations(geolocations);
        
        // by keywords
        String[] keywords = { "dieudonné" };
        // the keyword query is disabled for now
//        q.track(keywords);
        
        // Listening on stream
        twitterStream.filter(q);
	}
	
	static private void user_stream() {

	}
	
	public static void main(String[] args) {
		
//		public_stream();
		public_stream_with_query();
		
	}
	
}
