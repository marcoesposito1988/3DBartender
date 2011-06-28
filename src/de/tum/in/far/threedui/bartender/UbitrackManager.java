package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public class UbitrackManager {
	private String appName = "3D Bartender";
	
	private UbitrackFacade ubitrackFacade;
	private Viewer viewer;
	
	private ImageReceiver imageReceiver;
	
	public UbitrackManager() {
		ubitrackFacade = new UbitrackFacade();
		viewer = new Viewer(appName, ubitrackFacade);
		
		ubitrackFacade.initUbitrack();
		imageReceiver = new ImageReceiver();
		if (!ubitrackFacade.setImageCallback("imgsink", imageReceiver)) {
			return;
		}
		
		BackgroundObject backgroundObject = new BackgroundObject();
		viewer.addObject(backgroundObject);
		imageReceiver.setBackground(backgroundObject.getBackground());
	}
	
	public void startTracking() {
		ubitrackFacade.startDataflow();
	}
	
	public void addObjectToViewer(BranchGroup obj) {
		viewer.addObject(obj);
	}
	
	public boolean linkReceiverToMarker(PoseReceiver rec, String markerId) {
		return ubitrackFacade.setPoseCallback("posesink", rec);
	}
	
	public static void main(String[] args) {
		UbitrackManager um = new UbitrackManager();
		
		PoseReceiver myposereceiver = new PoseReceiver();
		if (!um.linkReceiverToMarker(myposereceiver, "posesink"))
			System.out.println("Error: could not link receiver to marker");
		
		BranchGroup mygroup = new BranchGroup();
		TransformGroup mytransfgroup = new TransformGroup();
		mytransfgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mytransfgroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		mytransfgroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		mygroup.addChild(mytransfgroup);
		
		ModelObject sheep = ModelFactory.loadVRMLModel("Sheep.wrl");
		mytransfgroup.addChild(sheep);
		
		myposereceiver.setTransformGroup(mytransfgroup);
		
		um.addObjectToViewer(mygroup);
		
		um.startTracking();
	}
	
}
