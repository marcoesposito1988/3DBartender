package de.tum.in.far.threedui.bartender;

public class Bartender {
	
	UbitrackManager ubitrackManager;
	
	Menu menu = new Menu();
	Pointer pointer = new Pointer();
	
	public Bartender() {
		ubitrackManager = new UbitrackManager();
	}
	
	public void start() {
		ubitrackManager = new UbitrackManager();

		pointer.setArrow(new ArrowObject());
		
		ubitrackManager.prepareTracking();

		PoseReceiver pr = ubitrackManager.getReceiverForMarker("posesink");
		PoseReceiver pr2 = ubitrackManager.getReceiverForMarker("posesink3");

		ubitrackManager.startTracking();
		ubitrackManager.addObjectToViewer(pointer);
		ubitrackManager.addObjectToViewer(menu);
		
		pointer.setPoseReceiver(pr);
		menu.setPoseReceiver(pr2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
	}

}
