package de.tum.in.far.threedui.bartender;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class MenuItem extends TransformableObject {

	private String name;
	TransformableObject model;
	private Label label;
	boolean isCategory;
	
	private Transform3D modelPosition = new Transform3D();
	private Transform3D labelPosition = new Transform3D();
	
	TransformGroup modelGroup = new TransformGroup();
	private TransformGroup labelGroup = new TransformGroup();
	
	private Transform3D globalPosition = new Transform3D();
	private TransformGroup globalGroup = new TransformGroup();
	
	// Behavior
	private BranchGroup behaviorGroup = new BranchGroup();
	
	private String labelText;
	private int labelHeight;
	
	MenuItemBehavior behavior;
	
	public MenuItem(String name, String labelText, TransformableObject model, boolean isCategory) {
		modelGroup.setCapability(ALLOW_CHILDREN_EXTEND);
		modelGroup.setCapability(ALLOW_CHILDREN_WRITE);
		modelGroup.setCapability(ALLOW_CHILDREN_READ);
		
		this.name = name;
		this.isCategory = isCategory;
		setModel(model);
		setLabel(labelText);
		transGroup.addChild(globalGroup);
		
		behavior = new MenuItemBehavior(this);
		globalGroup.addChild(behavior);
	}
	
	public void setModel(TransformableObject model2) {
		this.model = model2;
		modelGroup.addChild(model2);
		globalGroup.addChild(modelGroup);
	}
	
	public void setModelHeight(double height) {
		modelPosition.setTranslation(new Vector3d(0,0,height/2));
		modelGroup.setTransform(this.modelPosition);
	}
	
	public void setModelScaling(double factor) {
		modelPosition.setScale(factor);
		modelGroup.setTransform(modelPosition);
	}
	
	public TransformableObject detachModel() {
		model.detach();
		return model;
	}
	
	public void reattachModel() {
		globalGroup.addChild(model);
	}
	
	public void setLabel(String labelText) {
		this.labelText = labelText;
		
		if (label != null) {
			labelGroup.removeChild(label);
		}
		label = new Label();
		label.setText(labelText);
		setLabelScaling(0.01);
		setLabelBottom(0.06);
		labelGroup.addChild(label);
		transGroup.addChild(labelGroup);
	}
	
	public void setLabelBottom (double height) {
		labelPosition.setTranslation(new Vector3d(0,height+this.labelHeight/2,0));
		labelGroup.setTransform(labelPosition);
	}
	
	public void setLabelScaling(double factor) {
		labelPosition.setScale(factor);
		labelGroup.setTransform(labelPosition);
	}
	
	public void setGlobalScaling(double factor) {
		globalPosition.setScale(factor);
		globalGroup.setTransform(labelPosition);
	}
	
	public String getName() {
		// TODO localization
		return this.name;
	}
	
	public void armBehavior() {
		behavior.setSchedulingBounds(new BoundingSphere());

	}

}
