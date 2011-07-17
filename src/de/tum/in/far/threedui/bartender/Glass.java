package de.tum.in.far.threedui.bartender;

import java.awt.Color;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

public class Glass extends WorkbenchItem {
	
//	protected String modelFileName = "bar-set" + File.separator + "cocktail-glass.obj";
//	protected ModelType modelFileType = ModelType.OBJ; 
	
	protected GlassObject model;
	
	protected TransformGroup adjustGroup = new TransformGroup();
	protected TransformGroup animationGroup = new TransformGroup();
	
	WorkbenchBehavior behavior;
	
	Color color = new Color(0,0,0);
	
	public void doSuccessAnimation() {
		model.doSuccessAnimation();
	}
	
	public void doFailAnimation() {
		// TODO Anke: make the glass fall (use animationGroup)
	}
	
	public Glass() {
//		try {
//			model = ModelFactory.loadModel(modelFileName, modelFileType);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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

	public void pourStuff(Color glassColor) {
		color = new Color((color.getRGB() + glassColor.getRGB()));
		model.setColor(new Color3f(color));
	}
	
	public void emptyGlass() {
		model.reset();
	}

}
