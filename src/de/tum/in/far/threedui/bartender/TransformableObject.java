package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public class TransformableObject extends BranchGroup {

	protected final TransformGroup transGroup;
	
	protected PoseReceiver poseReceiver;
	
	public TransformableObject() {
		transGroup = new TransformGroup();
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		addChild(transGroup);
	}
	
	public TransformGroup getTransformGroup() {
		return transGroup;
	}
	
	public void setPoseReceiver(PoseReceiver pr) {
		poseReceiver = pr;
		pr.setTransformGroup(transGroup);
	}
}
