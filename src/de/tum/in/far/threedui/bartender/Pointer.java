package de.tum.in.far.threedui.bartender;

public class Pointer extends TransformableObject {
	
	PoseReceiver pointerPoseReceiver;
	
	public Pointer() {
		// TODO Anke: create a posereceiver and store it as a field
	}
	
	public void setModel(ModelObject model) {
		// TODO Anke: store this model and use it
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Anke: you can use this main to test the pointer
		// in case it doesn't work, it may be that I have something wrong:
		// when you look for errors, check my code as well!
		
		UbitrackManager um = new UbitrackManager();
		Pointer p = new Pointer();
		p.setModel(um.loadModel("Sheep.wrl"));
		um.linkReceiverToMarker(p.getPoseReceiver(), "posesink");
		um.startTracking();
	}

}
