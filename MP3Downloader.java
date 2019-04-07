import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MP3Downloader {
	//https://coc.oeaa.cc/66e42fef2df8cf0d55aed6e7c7aa0354/cPCLFtxpadE
	//https://www.flvto.biz/download/direct/mp3/yt_N-_mHedypEU
	
	public static void downloadFile(String url, String outputName, String outputFilePath) {

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("/usr/local/bin/youtube-dl", "-o", outputFilePath+"/"+outputName,url);
		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(output);
				System.exit(0);
			} else {
				System.out.println("abnormal");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
	
	
	public static void downloadFileWithSearch(String searchTerm, String outputName, String outputFilePath, JTextArea out) {

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("/usr/local/bin/youtube-dl", "-o",outputFilePath+"/"+outputName,"ytsearch:"+searchTerm);
		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				System.out.println(line);
				out.setText(out.getText()+line+"\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("sucess");
				out.setText(out.getText()+"Downloaded Successfully: " +outputName+"\n");
			} else {
				System.out.println("fail");
				out.setText(out.getText()+"Error with download of: " +outputName+"\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void downloadFiles(ArrayList<String> searchTerms, String outputDestination,JTextArea out) {
		for(String s: searchTerms) {
			downloadFileWithSearch(s,s+".mp3",outputDestination, out);
		}
	}
	
	public static void downloadFilesFromSpotify(String spotifyPlaylistURL,String outputDestination, JTextArea out) {
		try {
			downloadFiles(TrackNamesGetter.getTrackNames(spotifyPlaylistURL),outputDestination,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		JTextField f1 = new JTextField(35);
		JTextField f2 = new JTextField(35);
		JLabel l1 = new JLabel("Playlist URL:");
		JLabel l2 = new JLabel("Output Folder:");
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(l1);
		panel.add(f1);
		panel.add(l2);
		panel.add(f2);
		
		int exitVal = JOptionPane.showConfirmDialog(null,panel,"Spotify Playlist Downloader",JOptionPane.DEFAULT_OPTION);
		if(exitVal==0) {
			if(f1.getText().length()>0&&f2.getText().length()>0)
				downloadFilesFromSpotify(f1.getText(),f2.getText(),new JTextArea());
		}
//		ArrayList<String> tester = new ArrayList<String>();
//		tester.add("Crab Rave");
//		downloadFiles(tester,"Crab Rave");
		//downloadFile("https://www.youtube.com/watch?v=NxxjLD2pmlk","test.mp3","TestFolder");
		//downloadFileWithSearch("Megalovania","megalovania.mp3","Hehe xd");
	}
}
