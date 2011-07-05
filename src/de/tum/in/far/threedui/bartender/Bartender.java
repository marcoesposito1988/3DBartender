package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Point3d;

public class Bartender {
	
	UbitrackManager ubitrackManager;
	
	Menu menu = new Menu();
	Pointer pointer = new Pointer();
	Glass glass = new Glass();
	
	BranchGroup menuGroup = new BranchGroup();
	MenuBehavior menuBehavior;
	
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
		
		menuBehavior = new MenuBehavior(menu,pointer);
		menuGroup.addChild(menuBehavior);
		menuBehavior.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0), 1));
		ubitrackManager.addObjectToViewer(menuGroup);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
	}

}
