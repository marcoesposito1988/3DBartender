package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import de.tum.in.far.threedui.bartender.UbitrackFacade;
import de.tum.in.far.threedui.ex2.solution.PoseReceiver;

public class Pointer extends TransformableObject {
	
	PoseReceiver pointerPoseReceiver;
	private UbitrackFacade ubitrackFacade;
	
	public Pointer() {
		// TODO Anke: create a posereceiver and store it as a field
		
		
		pointerPoseReceiver = new PoseReceiver();
		if (!ubitrackFacade.setPoseCallback("posesink", pointerPoseReceiver)) {
		return;
		}
		ubitrackFacade.startDataflow();
		
	}
	
	public void setModel(ModelObject model) {
		// TODO Anke: store this model and use it
		BranchGroup bg = new BranchGroup();
		TransformGroup offset = new TransformGroup();
		Transform3D t3d = new Transform3D();
		
		offset.setTransform(t3d);
		bg.addChild(offset);
		offset.addChild(model);
		
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
		p.setModel(um.loadModel("Sheep.wrl"));//p.getPoseReceiver()
		um.linkReceiverToMarker("posesink",p.getPoseReceiver());
		um.startTracking();
	}



}
