package de.tum.in.far.threedui.bartender;

import java.io.File;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class Glass extends WorkbenchItem {
	
	protected String modelFileName = "bar-set" + File.separator + "cocktail-glass.obj";
	
	protected ModelObject model;
	
	protected TransformGroup adjustGroup = new TransformGroup();
	
	protected WorkbenchBehavior behavior;
	
	public Glass() {
		model = ModelFactory.loadObjModel(modelFileName);
		Transform3D adjust = new Transform3D();
		adjust.rotX(Math.PI/2);
		adjust.setScale(0.1);
		adjustGroup.setTransform(adjust);
		adjustGroup.addChild(model);
		transGroup.addChild(adjustGroup);
		
		behavior = new WorkbenchBehavior(this);
		transGroup.addChild(behavior);
		behavior.setSchedulingBounds(new BoundingSphere());
	}

}
