package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;

import de.tum.in.far.threedui.bartender.BlueAppearance;
import de.tum.in.far.threedui.bartender.TransformableObject;

public class ArrowObject extends TransformableObject {

	private static final float HEIGHT = 0.046f;

	
	private Switch arrowSwitch;
	
	public ArrowObject() {
		Appearance blueAppearance = new BlueAppearance();
		Appearance redAppearance = new Appearance();
		
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f red = new Color3f(0.8f, 0.3f, 0.3f);
		Color3f specular = new Color3f(0.9f, 0.5f, 0.5f);
		Material redMat = new Material(red, black, red, specular, 25.0f);
		redMat.setLightingEnable(true);
		redAppearance.setMaterial(redMat);

		arrowSwitch = new Switch();
		arrowSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
		
		Cylinder arrowReleased = new Cylinder(0.005f, HEIGHT, blueAppearance);
		Cylinder arrowPressed = new Cylinder(0.005f, HEIGHT, redAppearance);
		Cone co = new Cone(0.01f, 0.02f, redAppearance);
		Cone co1 = new Cone(0.01f, 0.02f, redAppearance);
		TransformGroup coneGroup = new TransformGroup();
		TransformGroup coneGroup1 = new TransformGroup();
		Transform3D tf3d = new Transform3D();
		tf3d.set(new Vector3f(0.0f, (HEIGHT / 2 + 0.02f / 2), 0.0f));
		coneGroup.setTransform(tf3d);
		coneGroup.addChild(co);
		coneGroup1.setTransform(tf3d);
		coneGroup1.addChild(co1);
		
		TransformGroup tgReleased1 = new TransformGroup();
		TransformGroup tgReleased = new TransformGroup();
		Transform3D t3d = new Transform3D();
		t3d.setTranslation(new Vector3d(0.0, 0.0, 0.005/2));
		tgReleased1.setTransform(t3d);
		tgReleased.addChild(coneGroup);
		tgReleased.addChild(tgReleased1);
		
		TransformGroup tgPressed1 = new TransformGroup();
		TransformGroup tgPressed = new TransformGroup();
		t3d = new Transform3D();
		t3d.setTranslation(new Vector3d(0.0, 0.0, 0.005/2));
		tgPressed1.setTransform(t3d);
		tgPressed.addChild(tgPressed1);
		tgPressed.addChild(coneGroup1);
		
		arrowSwitch.addChild(tgReleased);
		arrowSwitch.addChild(tgPressed);
		arrowSwitch.setWhichChild(0);
		
		tgReleased.addChild(arrowReleased);
		tgPressed.addChild(arrowPressed);
		
		transGroup.addChild(arrowSwitch);
		
	}
	
	public void arrowPressed(boolean flag) {
		if (flag) {
			arrowSwitch.setWhichChild(1);
		} else {
			arrowSwitch.setWhichChild(0);
		}
	}
}
