import java.util.ArrayList;

public class YoutubeLinkGetter {
	
	
	
	public static ArrayList<String> getYoutubeLinks(ArrayList <String> MP3) {
		for(String thing : MP3) {
			getMP3Link(thing);
		}
		
		return MP3;
	}
	public static String getMP3Link(String youtubeLink){
		ArrayList<String> stuff = new ArrayList<String>() ;
				for(String thing : stuff) {
					youtubeLink=youtubeLink.substring(32);
					
					
				}
				return youtubeLink;
		
	}

}
