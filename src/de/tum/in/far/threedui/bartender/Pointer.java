package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Box;

public class Pointer extends TransformableObject {

	protected ModelObject model;
	protected ArrowObject arrow;
	
	protected TransformGroup offset = new TransformGroup();
	protected Transform3D pointerPosition = new Transform3D();
	
	public Pointer() {
		// TODO Anke: create a posereceiver and store it as a field
		
		//pointerPoseReceiver = new PoseReceiver();
		//here make zylinder and cone
		transGroup.addChild(offset);
	}
	public void setArrow(ArrowObject arrow) {
		// TODO Anke: store this model and use it
		
		//offset = new TransformGroup();
		this.arrow = arrow;
		//Transform3D t3d = new Transform3D();
		
		//offset.setTransform(t3d);
		offset.addChild(arrow);
		
	}
	
	public void setModel(ModelObject model) {
		// TODO Anke: store this model and use it
		
		//bg = new BranchGroup();
		//offset = new TransformGroup();
		this.model = model;
		//Transform3D t3d = new Transform3D();
		
		//offset.setTransform(t3d);
		offset.addChild(model);
		transGroup.addChild(offset);
		
		
	}
	public void setModelScaling(double factor) {
		pointerPosition.setScale(factor);
		offset.setTransform(pointerPosition);
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Anke: you can use this main to test the pointer
		// in case it doesn't work, it may be that I have something wrong:
		// when you look for errors, check my code as well!
		
		// FIRST: create stuff
		Pointer p = new Pointer();
		p.setArrow(new ArrowObject());
		ModelObject sheep = ModelFactory.loadVRMLModel("Sheep.wrl");
		Menu m = new Menu();
		
		
		// SECOND: create UbitrackManager, call prepareTracking()
		UbitrackManager um = new UbitrackManager();
		um.prepareTracking();
		// THIRD: get all the PoseReceivers you need
		PoseReceiver pr = um.getReceiverForMarker("posesink");
		PoseReceiver pr2 = um.getReceiverForMarker("posesink2");
		PoseReceiver pr3 = um.getReceiverForMarker("posesink3");
		// FOURTH: startTracking();
		um.startTracking();
		
		// FIFTH: link the PoseReceivers to the TransformGroups
		pr.setTransformGroup(p.transGroup);
		pr2.setTransformGroup(sheep.transGroup);
		pr3.setTransformGroup(m.transGroup);
		// SIXTH: add objects to the viewer
		um.addObjectToViewer(p);
		um.addObjectToViewer(sheep);
		um.addObjectToViewer(m);
		// SEVENTH: in order to test a main, edit TestConfig.launch
	}
	
}
