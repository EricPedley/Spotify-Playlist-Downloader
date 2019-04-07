import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JPanel {
	private JScrollPane console;
	private JTextArea consoleText;
	private JTextField spotifyURL, outPath;
	private JLabel l1, l2, l3;
	private JButton submit;
	private boolean running;

	public GUI() {
		running = false;
		Box everything = Box.createVerticalBox();
		Box textFields = Box.createVerticalBox();
		spotifyURL = new JTextField();
		outPath = new JTextField();
		submit = new JButton("Start Download");
		submit.addActionListener(new Handler());
		l1 = new JLabel("Spotify Playlist URL:");
		textFields.add(l1);
		textFields.add(spotifyURL);
		l2 = new JLabel("Folder to write to");
		textFields.add(l2);
		textFields.add(outPath);
		textFields.add(submit);
		l3 = new JLabel("Console Output:");
		textFields.add(l3);
		consoleText = new JTextArea();
		consoleText.setEditable(false);
		consoleText.setText("Hello, world!");
		console = new JScrollPane(consoleText);
		everything.add(textFields);
		everything.add(console);
		add(everything);
	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit) {
				running = !running;
				System.out.println("starting dl");
				new Thread() {
					public void run() {
						consoleText.setText(consoleText.getText() + "\nStarting dl\n");
					}
				}.run();
				
				
				new Thread() {
					public void run() {
						MP3Downloader.downloadFilesFromSpotify(spotifyURL.getText(), outPath.getText(), consoleText);
					}
				}.run();
			}
		}
	}
}
