package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Cylinder;

public class CancelObject extends TransformableObject {
	private static final float CYL_RADIUS = 0.005f;
	private static final float CYL_HEIGHT = 0.1f;
	
	private static final Appearance app = new BlueAppearance();
	
	protected Cylinder c1 = new Cylinder(CYL_RADIUS,CYL_HEIGHT,app);
	protected Cylinder c2 = new Cylinder(CYL_RADIUS,CYL_HEIGHT,app);
	
	protected TransformGroup tg1 = new TransformGroup();
	protected TransformGroup tg2 = new TransformGroup();
	
	protected TransformGroup globalTg = new TransformGroup();
	
	public CancelObject() {
		Transform3D t3d = new Transform3D();
		t3d.rotZ(Math.PI/4);
		tg1.setTransform(t3d);
		t3d.rotZ(-Math.PI/4);
		tg2.setTransform(t3d);
		
		tg1.addChild(c1);
		tg2.addChild(c2);
		
		globalTg.addChild(tg1);
		globalTg.addChild(tg2);
		
		addChild(globalTg);
	}

}
