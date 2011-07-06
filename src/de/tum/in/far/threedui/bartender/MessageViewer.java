package de.tum.in.far.threedui.bartender;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;
import java.io.IOException;

public class MessageViewer extends Viewer {
	
	protected Panel messageBar = new Panel();
	protected Label messageLabel = new Label();
	
	protected static String DefaultText = "Welcome";
	
	public MessageViewer(String frameTitle, final UbitrackFacade ubitrack) {
		super(frameTitle,ubitrack);
		
		messageBar.add(messageLabel);
		frame.add(BorderLayout.NORTH,messageBar);
		messageLabel.setText(DefaultText);
		frame.pack();

	}
	
	public void setMessageText(String text) {
		messageLabel.setText(text);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UbitrackFacade ubitrackFacade = new UbitrackFacade();
		MessageViewer viewer = new MessageViewer("MessageViewer test", ubitrackFacade);
		ubitrackFacade.initUbitrack();
		ImageReceiver imageReceiver = new ImageReceiver();
		if (!ubitrackFacade.setImageCallback("imgsink", imageReceiver)) {
			return;
		}
		
		BackgroundObject backgroundObject = new BackgroundObject();
		viewer.addObject(backgroundObject);
		imageReceiver.setBackground(backgroundObject.getBackground());
		char a = 'a';
		String b = new String();
		while(true) {
			try {
				a = (char) System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b.concat(String.valueOf(a));
			viewer.setMessageText(b);
		}
	}

}
