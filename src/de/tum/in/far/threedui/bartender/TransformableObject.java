package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class TransformableObject extends BranchGroup {

	protected TransformGroup transGroup = new TransformGroup();
	
	public TransformableObject() {
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		setCapability(BranchGroup.ALLOW_DETACH);
		
		super.addChild(transGroup);
	}
	
	public TransformGroup getTransformGroup() {
		return transGroup;
	}
	
	public void setTransform(Transform3D t3d) {
		transGroup.setTransform(t3d);
	}
	
	@Override
	public void addChild(Node arg0) {
		transGroup.addChild(arg0);
	}
	
	@Override
	public void removeChild(Node arg0) {
		transGroup.removeChild(arg0);
	}
	
	@Override
	public void removeAllChildren() {
		transGroup.removeAllChildren();
	}
	
	@Override
	public Node getChild(int arg0) {
		return transGroup.getChild(arg0);
	}
	
	@Override
	public int numChildren() {
		return transGroup.numChildren();
	}
	
	protected void deepAddChild(Node child) {
		super.addChild(child);
	}
	
	protected void deepRemoveChild(int index) {
		super.removeChild(index);
	}
}
