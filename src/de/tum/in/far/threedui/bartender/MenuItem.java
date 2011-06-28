package de.tum.in.far.threedui.bartender;

import java.io.File;

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
		transGroup.addChild(globalGroup);
	}
	
	public void setModel(ModelObject model) {
		this.model = model;
		modelGroup.addChild(model);
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
	
	public String getName() {
		// TODO localization
		return this.name;
	}
	
	public static void main(String[] args) {
		TestViewer tv = new TestViewer();
		tv.initializeJava3D();
		MenuItem mi = new MenuItem("Glass","Cocktail Glass",ModelFactory.loadObjModel("misc" + File.separator + "Bottles" + File.separator + "bottles.obj"));
		mi.setModelScaling(0.18);
		//mi.setLabelBottom(1);
		tv.addObject(mi);
		tv.addCameraDisplacement(new Vector3d(0,0,+0.5));
	}

}
