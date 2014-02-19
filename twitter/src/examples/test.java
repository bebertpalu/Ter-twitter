package examples;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import com.sun.org.glassfish.gmbal.Description;


public class TestFileload implements Serializable {

	private final String liste = "/home/nordine/workspace/java/TER/twitter/liste.txt";
	private final String tweet = "/home/nordine/workspace/java/TER/twitter/";
	private static boolean areFilesLoaded = false; 	
	GeoLocation sudest = new GeoLocation(45.29962080944616, 4.790771305561066);
	GeoLocation sudouest = new GeoLocation(44.80288598267254, 1.0993650555610657);
	GeoLocation nordest = new GeoLocation(48.62709981647218, 4.395263493061066);
	GeoLocation nordouest = new GeoLocation(48.10156216940599, -0.4387208819389343);
	String sautLigne = System.getProperty("line.separator");
	Twitter twitter = TwitterFactory.getSingleton();
	String[] arr ;
	
	
	
	
	/*
	 * Load 
	 */
	
	public void load()
	{
		search(liste);	
	}
	
	/*
	 * Search fichier en parametre en entre 
	 */
	public void search(String file){
	
		try{
				
		InputStream ips=new FileInputStream(file);
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne;
		
			while ((ligne=br.readLine())!=null)
			{
			
			arr = ligne.split(";");
			parcourTb();
			//System.out.println(arr.length);
			}
			
			
		} 
		catch (Exception e){
			System.out.println(e.toString());
			}
	}
	
	/*
	 * parcourtab parcour le tableau de données
	 */
	public void parcourTb()
	{
		
		try{
			for(int j=0; j<4; j++)
			{
				if(j==0)
				{
					String loc = "45.29962080944616, 4.790771305561066";
					System.out.println(loc);
					getTweetLoc(loc, j);
				}else if(j==1)
					{
						String loc = "44.80288598267254, 1.0993650555610657";
						System.out.println(loc);
						getTweetLoc(loc, j);
					}
				else if(j==2)
				{
					String loc = "48.62709981647218, 4.395263493061066";
					System.out.println(loc);
					getTweetLoc(loc, j);
				}
				else
				{
					String loc = "48.10156216940599, -0.4387208819389343";
					System.out.println(loc);
					j = 3;
					getTweetLoc(loc, j);
					
				}
			
				
			}
		
			
			
		} 
		catch (Exception e){
			System.out.println(e.toString());
			}
	}
	
	void getTweetLoc(String local, int geo)
	{
		try {
		BufferedWriter fstream = new BufferedWriter(new FileWriter("tw.txt", true));
		fstream.write(local + sautLigne);
		for(int i=0; i<= arr.length; i++)
		{
		
			System.out.println(" le MOTS " + arr[i]);
			fstream.write(arr[i] + sautLigne);
			getTweet(arr[i], geo, fstream);
		} 
		
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * GetTweet Mot clé en paramatre en entre 
	 */
	public void getTweet (String name, int localisation, BufferedWriter fc) throws IOException
	{
		try {
		//BufferedWriter fstream = new BufferedWriter(new FileWriter("tw.txt", true));
		//fstream.write(tweet+"\n");
//		FileWriter fstream = new FileWriter("tweetListestream.txt");
//	    BufferedWriter out = new BufferedWriter(fstream);
		Query query = new Query(name);
		
		QueryResult result;
		
			if(localisation==0)
			{
				query.setGeoCode(sudest, 400, Query.KILOMETERS);
				
			}
			else if(localisation==1)
			{
				query.setGeoCode(sudouest, 400, Query.KILOMETERS);
				
			}
			else if(localisation==2)
			{
				query.setGeoCode(nordest, 400, Query.KILOMETERS);
				
			}
			else if(localisation==3)
				{
				query.setGeoCode(nordouest, 400, Query.KILOMETERS);
				
				}
			result = twitter.search(query);
			for (Status status : result.getTweets()) {
				
				System.out.println(status.getText() + sautLigne);
				fc.write(status.getText() + sautLigne);
				//fstream.write("Geo du tweet" +status.getGeoLocation() + sautLigne);
           
            
			}
		
		
			
        
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fc.close();
		
	}
	

	
	/*
	 * MAIN de Test
	 */
    public static void main(String[] args) throws IOException {
    	
    	TestFileload objet = new TestFileload();
    	objet.load();	
    	
    
    }
	
}
