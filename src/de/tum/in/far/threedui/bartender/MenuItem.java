package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class MenuItem extends TransformableObject {

	private String name;
	private ModelObject model;
	private Label label;
	
	private Transform3D modelPosition = new Transform3D();
	private Transform3D labelPosition = new Transform3D();
	
	private TransformGroup modelGroup = new TransformGroup();
	private TransformGroup labelGroup = new TransformGroup();
	
	private TransformGroup globalGroup = new TransformGroup();
	
	private String labelText;
	private int labelHeight;
	
	public MenuItem(String name, String labelText, ModelObject model) {
		this.name = name;
		setModel(model);
		setLabel(labelText);
		addChild(globalGroup);
	}
	
	public void setModel(ModelObject model) {
		this.model = model;
		this.globalGroup.addChild(model);
	}
	
	public void setModelHeight(double height) {
		this.modelPosition.setTranslation(new Vector3d(0,0,height/2));
		this.modelGroup.setTransform(this.modelPosition);
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
		addChild(labelGroup);
	}
	
	public void setLabelBottom (double height) {
		labelPosition.setTranslation(new Vector3d(0,height+this.labelHeight/2,0));
		labelGroup.setTransform(labelPosition);
	}
	
	public void setLabelScaling(double factor) {
		labelPosition.setScale(factor);
		labelGroup.setTransform(labelPosition);
	}
	
	public String getName() {
		// TODO localization
		return this.name;
	}
	
	public void setup() {

		modelGroup.addChild(model);
		globalGroup.addChild(modelGroup);
	}
	
	public static void main(String[] args) {
		TestViewer tv = new TestViewer();
		tv.initializeJava3D();
		MenuItem mi = new MenuItem("Sheep","Pecora",tv.loadModel("Sheep.wrl"));
		Transform3D myt3d = new Transform3D(); 
		mi.getTransformGroup().getTransform(myt3d);
		myt3d.setScale(3);
		mi.getTransformGroup().setTransform(myt3d);
		tv.addObject(mi);
		tv.addCameraDisplacement(new Vector3d(0,0,+0.5));
	}

}
