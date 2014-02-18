package examples;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
public class VaccinSearch {
	

	static private void public_stream_with_query()
	{
		
		//creation de l'instance Twitter stream
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	    
		//creation de l'instance "ecouteur" 
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status)  {
            	
        		String sautLigne = System.getProperty("line.separator");

        		Twitter twitter = TwitterFactory.getSingleton();

               
				try {
					
					FileWriter fstream = new FileWriter("tweetListestream.txt");
				    BufferedWriter out = new BufferedWriter(fstream);
				    
				        
					Query query = new Query("vaccin");
					QueryResult result;
					result = twitter.search(query);
					System.out.println("resulte :d:d:d:d "+ result.getTweets());
					
					
			        for (Status statuse : result.getTweets()) {
			        	System.out.println(statuse.getText() + sautLigne);
			        	out.write(statuse.getText() + sautLigne);
			        	
			        	} 
			        	out.close();
			        
			        
			        
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
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
        
        
        ///////////////////////////////////////////////////////
        // Query Filter by geolocalisation in french///////////
        //////////////////////////////////////////////////////
        
        FilterQuery q = new FilterQuery();
 
        double lat1_fr = 43.659924;
        double lon1_fr = -5.408936;
        double lat2_fr = 50.805935;
        double lon2_fr = 6.478271;
       
        double[][] geolocations = { {lon1_fr, lat1_fr}, {lon2_fr, lat2_fr} };
        q.locations(geolocations);
       
      
        twitterStream.filter(q);
        
       
	}
	
	public static void main(String[] args){
		

		VaccinSearch requeter = new VaccinSearch();
		requeter.public_stream_with_query();
		
		
	}

}
