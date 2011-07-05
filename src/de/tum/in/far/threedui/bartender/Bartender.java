package de.tum.in.far.threedui.bartender;

public class Bartender {
	
	UbitrackManager ubitrackManager;
	
	Menu menu = new Menu();
	Pointer pointer = new Pointer();
	Glass glass = new Glass();
	
	public Bartender() {
		ubitrackManager = new UbitrackManager();
	}
	
	public void start() {
		ubitrackManager = new UbitrackManager();

		pointer.setArrow(new ArrowObject());
		
		ubitrackManager.prepareTracking();

		PoseReceiver pointerReceiver = ubitrackManager.getReceiverForMarker("posesink");
		PoseReceiver menuReceiver = ubitrackManager.getReceiverForMarker("posesink3");
		PoseReceiver glassReceiver = ubitrackManager.getReceiverForMarker("posesink4");

		ubitrackManager.startTracking();
		
		ubitrackManager.addObjectToViewer(pointer);
		ubitrackManager.addObjectToViewer(menu);
		ubitrackManager.addObjectToViewer(glass);
		
		pointer.setPoseReceiver(pointerReceiver);
		menu.setPoseReceiver(menuReceiver);
		glass.setPoseReceiver(glassReceiver);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
	}

}
