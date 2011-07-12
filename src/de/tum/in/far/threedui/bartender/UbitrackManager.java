package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

import de.tum.in.far.threedui.bartender.PoseReceiver;

public class UbitrackManager {
	private String appName = "3D Bartender";
	
	private UbitrackFacade ubitrackFacade;
	MessageViewer viewer;
	
	private ImageReceiver imageReceiver;
	
	public UbitrackManager() {
		ubitrackFacade = new UbitrackFacade();
		viewer = new MessageViewer(appName, ubitrackFacade);
		
	}
	
	public void prepareTracking() {
		ubitrackFacade.initUbitrack();
	}
	
	public void startTracking() {
		imageReceiver = new ImageReceiver();
		if (!ubitrackFacade.setImageCallback("imgsink", imageReceiver)) {
			return;
		}
		
		BackgroundObject backgroundObject = new BackgroundObject();
		viewer.addObject(backgroundObject);
		imageReceiver.setBackground(backgroundObject.getBackground());
		ubitrackFacade.startDataflow();
	}
	
	public void addObjectToViewer(BranchGroup obj) {
		viewer.addObject(obj);
	}
	
	public PoseReceiver getReceiverForMarker(String markerId) {
		PoseReceiver ret = new PoseReceiver();
		ubitrackFacade.setPoseCallback(markerId, ret);
		return ret;
	}
	
	public ObstructablePoseReceiver getObstructableReceiverForMarker(String markerId) {
		ObstructablePoseReceiver ret = new ObstructablePoseReceiver();
		ubitrackFacade.setPoseCallback(markerId, ret);
		return ret;
	}
	
	public static void main(String[] args) {
		// FIRST: create stuff
		Pointer p = new Pointer();
		ModelObject sheep = ModelFactory.loadVRMLModel("Sheep.wrl");
		
		// SECOND: create UbitrackManager, call prepareTracking()
		UbitrackManager um = new UbitrackManager();
		um.prepareTracking();
		// THIRD: get all the PoseReceivers you need
		PoseReceiver pr = um.getReceiverForMarker("posesink");
		PoseReceiver pr2 = um.getReceiverForMarker("posesink2");
		// FOURTH: startTracking();
		um.startTracking();
		
		// FIFTH: link the PoseReceivers to the TransformGroups
		pr.setTransformGroup(p.transGroup);
		pr2.setTransformGroup(sheep.transGroup);
		// SIXTH: add objects to the viewer
		um.addObjectToViewer(p);
		um.addObjectToViewer(sheep);
		// SEVENTH: in order to test a main, edit TestConfig.launch

	}
	
}
