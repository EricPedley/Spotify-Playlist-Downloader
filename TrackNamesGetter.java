import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class TrackNamesGetter {
	
	
	
	public static ArrayList<String> getTrackNames(String spotifyURL) throws IOException {
		Scanner in = null;
		FileWriter writer = null;
		String out = "";
		try {
			URL url = new URL(spotifyURL);
			InputStream stream = url.openStream();
			writer = new FileWriter("src/out.txt");
			in = new Scanner(stream);
			while(in.hasNextLine()) {
				String s = in.nextLine();
//				if(s.contains("track-name")) {
//					int startIndex = s.indexOf("o\">");
//					int endIndex = s.indexOf("<",startIndex);
//					System.out.println(s.substring(startIndex+3,endIndex));
//				}
//				if(s.contains("Renegades"))
//					System.out.println("it is reading them");
				//if(s.contains("name"))
					//System.out.println(s);
					writer.write(s+"\n");
				out+=s+"\n";
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			writer.close();
		}
		//System.out.println(out.indexOf("Short Change Hero"));
		ArrayList<String> tracks = new ArrayList<String>();
		ArrayList<String> artists = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		String s = out;//getTrackNames("https://open.spotify.com/playlist/0KD36OLXMAghwkb97xl6cU");
		for(int index=s.indexOf("\",\"popularity\":");index>=0;index=s.indexOf("\",\"popularity\":",index+1)) {
			int index2 = s.lastIndexOf("\"name\":\"",index-10)+8;
			tracks.add(s.substring(index2,index));
		}
		for(int index=s.indexOf("\"disc_number\":");index>=0;index=s.indexOf("\"disc_number\":",index+1)) {
			int index2 = s.lastIndexOf("name\":",index-50);
			artists.add(s.substring(index2+7,s.indexOf("\"",index2+7)));
		}
		for(int c=0;c<tracks.size();c++) {
			result.add((tracks.get(c)+"-"+artists.get(c)));
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		//getTrackNames("https://open.spotify.com/playlist/0KD36OLXMAghwkb97xl6cU");
		try {
			ArrayList<String> tracks = new ArrayList<String>();
			ArrayList<String> artists = new ArrayList<String>();
			ArrayList<String> s = getTrackNames("https://open.spotify.com/playlist/0KD36OLXMAghwkb97xl6cU");
			for(String str: s) {
				System.out.println(str);
			}
//			int count=0;
//			for(int index=s.indexOf("\",\"popularity\":");index>=0;index=s.indexOf("\",\"popularity\":",index+1)) {
//				count++;
//				int index2 = s.lastIndexOf("\"name\":\"",index-10)+8;
//				tracks.add(s.substring(index2,index));
//			}
//			for(int index=s.indexOf("\"disc_number\":");index>=0;index=s.indexOf("\"disc_number\":",index+1)) {
//				//count++;
//				int index2 = s.lastIndexOf("name\":",index-50);
//				artists.add(s.substring(index2+7,s.indexOf("\"",index2+7)));
//			}
//			for(int c=0;c<tracks.size();c++) {
//				System.out.println(tracks.get(c)+"-"+artists.get(c));
//			}
//				System.out.println(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
