package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;

public class UbitrackManager {
	private String appName = "3D Bartender";
	
	private UbitrackFacade ubitrackFacade;
	private Viewer viewer;
	
	private ImageReceiver imageReceiver;
	
	static ModelFactory modelFactory = new ModelFactory(); 
	
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
	
	public ModelObject loadModel(String fileName) {
		return modelFactory.loadModel(fileName);
	}
}
