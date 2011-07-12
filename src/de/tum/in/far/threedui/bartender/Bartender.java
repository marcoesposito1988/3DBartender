package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;

public class Bartender {
	
	UbitrackManager ubitrackManager;
	
	Menu menu = new Menu();
	Pointer pointer = new Pointer();
	Glass glass = new Glass();
	ModelObject glassObject;
	TransformableObject glassTransfObject;
	
	BranchGroup globalGroup = new BranchGroup();
	
	public Bartender() {
		ubitrackManager = new UbitrackManager();
		
		globalGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		globalGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		globalGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
	}
	
	public void start() {
		GlobalStatus.setEnvironment(pointer, menu, glass);
		
		ubitrackManager.prepareTracking();

		ObstructablePoseReceiver pointerReceiver = ubitrackManager.getObstructableReceiverForMarker("posesink4");
		ObstructablePoseReceiver menuReceiver = ubitrackManager.getObstructableReceiverForMarker("posesink3");
		ObstructablePoseReceiver glassReceiver = ubitrackManager.getObstructableReceiverForMarker("posesink");

		ubitrackManager.startTracking();
		
		pointer.setPoseReceiver(pointerReceiver);
		menu.setPoseReceiver(menuReceiver);
		glass.setPoseReceiver(glassReceiver);

		menu.showCategory("root");
		
		pointer.parent = globalGroup;
		glass.parent = globalGroup;
		menu.parent = globalGroup;
		
		ubitrackManager.addObjectToViewer(globalGroup);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
	}

}
