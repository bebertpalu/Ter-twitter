package examples;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

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


public class TweetSearch implements Serializable {
	
	static private void searchTweet() throws IOException
	{
	//////////////////
    // Query writing//
	/////////////////
		
    FilterQuery q = new FilterQuery();
		
    //france//
	double lat1_fr = 43.659924;
	double lon1_fr = -5.408936;
	double lat2_fr = 50.805935;
	double lon2_fr = 6.478271;
    
    // be careful with order of coordinates: (longitude, latitude)
    double[][] geolocations = { {lon1_fr, lat1_fr}, {lon2_fr, lat2_fr} };
    q.locations(geolocations);
    
 // Create file
    FileWriter fstream = new FileWriter("tweetListe.txt");
    BufferedWriter out = new BufferedWriter(fstream);
    String sautLigne = System.getProperty("line.separator");
    
	// The factory instance is re-useable and thread safe.
    Twitter twitter = TwitterFactory.getSingleton();
    
    // Requete sur le mot cle
    Query query = new Query("doliprane");
    //Query c = new Query(q);
    
    QueryResult result;
    
    
	try {
		
		
		result = twitter.search(query);
        for (Status status : result.getTweets()) {
            //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        	System.out.println(status.getText() + sautLigne);
            out.write(status.getText() + sautLigne);
            
        }
        out.close();
        
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
    
    //Main test
    public static void main(String[] args) throws IOException {
    	
    	TweetSearch objet = new TweetSearch();
    	objet.searchTweet();
    
    }
    
    
    
}
