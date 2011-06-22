package de.tum.in.far.threedui.bartender;

public class Bartender {
	
	private String appName = "3D Bartender";
	
	private UbitrackFacade ubitrackFacade;
	private Viewer viewer;
	
	private ImageReceiver imageReceiver;
	
	public Bartender() {
		ubitrackFacade = new UbitrackFacade();
		viewer = new Viewer(appName, ubitrackFacade);
		
		ubitrackFacade.initUbitrack();
		imageReceiver = new ImageReceiver();
		if (!ubitrackFacade.setImageCallback("imgsink", imageReceiver)) {
			return;
		}
		ubitrackFacade.startDataflow();
		
		BackgroundObject backgroundObject = new BackgroundObject();
		viewer.addObject(backgroundObject);
		imageReceiver.setBackground(backgroundObject.getBackground());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
	}

}
