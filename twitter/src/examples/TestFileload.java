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

	private final String liste = "/home/nordine/workspace/java/TER/twitter/noms-medicaments.txt";
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
			for(int i=0; i<=arr.length; i++)
				{
				
					System.out.println(" le MOTS " + arr[i]);
					getTweet(arr[i]);
				}
			
		
			
			
		} 
		catch (Exception e){
			System.out.println(e.toString());
			}
	}
	
	
	/*
	 * GetTweet Mot clé en paramatre en entre 
	 */
	public void getTweet (String name) throws IOException
	{
		try {
		BufferedWriter fstream = new BufferedWriter(new FileWriter("sudouest.txt", true));
		//fstream.write(tweet+"\n");
//		FileWriter fstream = new FileWriter("tweetListestream.txt");
//	    BufferedWriter out = new BufferedWriter(fstream);
		Query query = new Query(name);
		QueryResult result;
		query.setGeoCode(sudouest, 400, Query.KILOMETERS);
		result = twitter.search(query);
		//fstream.write(name + sautLigne);
		
		
		for (Status status : result.getTweets()) {
				
				System.out.println(status.getText() + sautLigne);
				fstream.write(status.getText() + sautLigne);
				
				//fstream.write("Geo du tweet" +status.getGeoLocation() + sautLigne);
           
            
			}
		
		
			fstream.close();
        
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	/*
	 * MAIN de Test
	 */
    public static void main(String[] args) throws IOException {
    	
    	TestFileload objet = new TestFileload();
    	objet.load();	
    
    }
	
}
