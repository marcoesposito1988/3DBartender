package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class Pointer extends CollidableObject {

	protected TransformableObject model;
	protected ArrowObject arrow;
	
	protected TransformGroup offset = new TransformGroup();
	protected Transform3D pointerPosition = new Transform3D();
	
	protected Group attachedModel;
	
	public Pointer() {
		offset.setCapability(ALLOW_CHILDREN_EXTEND);
		offset.setCapability(ALLOW_CHILDREN_WRITE);
		offset.setCapability(ALLOW_CHILDREN_READ);
		
		//pointerPoseReceiver = new PoseReceiver();
		//here make zylinder and cone
		transGroup.addChild(offset);
		this.arrow = new ArrowObject();
		offset.addChild(arrow);
	}

	public void setModel(TransformableObject model) {
		this.model = model;
		offset.addChild(model);
		transGroup.addChild(offset);
	}
	
	public TransformableObject removeModel() {
		offset.removeAllChildren();
		offset.addChild(arrow);
		return this.model;
	}
	
	public void setModelScaling(double factor) {
		pointerPosition.setScale(factor);
		offset.setTransform(pointerPosition);
	}
	
	public void attachModel(Group model) {
		attachedModel = model;
		offset.removeAllChildren();
		offset.addChild(model);
	}
	
	public TransformableObject detachModel() {
		offset.removeAllChildren();
		offset.addChild(arrow);
		Group ret = attachedModel;
		attachedModel = null;
		return (TransformableObject) ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// FIRST: create stuff
		Pointer p = new Pointer();
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
