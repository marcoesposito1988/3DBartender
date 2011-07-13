package de.tum.in.far.threedui.bartender;

import java.io.File;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Glass extends WorkbenchItem {
	
	protected String modelFileName = "bar-set" + File.separator + "cocktail-glass.blend";
	
	//protected ModelObject model;
	protected GlassObject model;
	
	protected TransformGroup adjustGroup = new TransformGroup();
	protected TransformGroup animationGroup = new TransformGroup();
	
	WorkbenchBehavior behavior;
	
	public void doSuccessAnimation() {
		// TODO Anke: put umbrella and straw into the glass
	}
	
	public void doFailAnimation() {
		// TODO Anke: make the glass fall (use animationGroup)
	}
	
	public Glass() {
		//model = ModelFactory.loadBlendModel(modelFileName);
		
		model = new GlassObject();
		Transform3D adjust = new Transform3D();
		adjust.rotX(Math.PI/2);
		adjust.setTranslation(new Vector3d(0,0,0.02));
		adjust.setScale(5);
		adjustGroup.setTransform(adjust);
		animationGroup.addChild(model);
		adjustGroup.addChild(animationGroup);
		addChild(adjustGroup);
		
		behavior = new WorkbenchBehavior(this);
		addChild(behavior);
		behavior.setSchedulingBounds(new BoundingSphere());
	}

}
