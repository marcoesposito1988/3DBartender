package de.tum.in.far.threedui.bartender;

public class Bartender {
	
	UbitrackManager ubitrackManager;
	
	public Bartender() {
		ubitrackManager = new UbitrackManager();
	}
	
	public void start() {
		ubitrackManager.startTracking();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
	}

}
