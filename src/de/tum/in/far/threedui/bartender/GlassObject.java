package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.*;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;

import de.tum.in.far.threedui.bartender.BlueAppearance;
import de.tum.in.far.threedui.bartender.TransformableObject;

public class GlassObject extends TransformableObject {

	private static final float HEIGHT = 0.023f;
	private static final float HEIGHT2 = 0.015f;


	//private static final String TransparencyAttributes = null;

	
	private Switch GlassSwitch;
	private TransparencyAttributes transps;
	private TransparencyAttributes whiteTransparency;
	private TransparencyAttributes colorTransparency;
	
	public GlassObject() {
		Appearance transparentAppearance = new Appearance();
		Appearance whiteAppearance =new Appearance();
		Appearance insideAppearance_color = new Appearance();
		
	
		//Colors
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f red = new Color3f(0.8f, 0.3f, 0.3f);
		Color3f specular = new Color3f(0.9f, 0.5f, 0.5f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		
		//Material transparent
		Material transparentMat = new Material(white, black, red, specular, 25.0f);
		transparentMat.setLightingEnable(true);
		float transparencyValue = 0.7f;
	    transps = new TransparencyAttributes(TransparencyAttributes.BLENDED,transparencyValue);
		transparentAppearance.setMaterial(transparentMat);

		transparentAppearance.setTransparencyAttributes(transps);
		
		
		//Material white
		Material whiteMat = new Material(white, black, red, specular, 25.0f);
		whiteMat.setLightingEnable(true);
		float whiteValue = 0.5f;
	    whiteTransparency = new TransparencyAttributes(TransparencyAttributes.BLENDED, whiteValue);
	    whiteAppearance.setMaterial(whiteMat);
	
		whiteAppearance.setTransparencyAttributes(whiteTransparency);
	
		
		//Material red
		Material colorMat = new Material(red, black, red, specular, 25.0f);
		colorMat.setLightingEnable(true);
		float colorValue = 0.5f;
		colorTransparency = new TransparencyAttributes(TransparencyAttributes.BLENDED, colorValue);
		insideAppearance_color.setMaterial(colorMat);

		insideAppearance_color.setTransparencyAttributes(colorTransparency);
	
		

		GlassSwitch = new Switch();
		GlassSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
		
		Cylinder glassTransparent = new Cylinder(0.005f, HEIGHT, transparentAppearance);
		Cylinder glassWhite=new Cylinder(0.0049f, HEIGHT2, whiteAppearance);
		
		
		Cylinder glassTransparent2 = new Cylinder(0.005f, HEIGHT, transparentAppearance);
		Cylinder glassFilled = new Cylinder(0.0049f, HEIGHT2, insideAppearance_color);
		
		
		//Cone co = new Cone(0.01f, 0.02f, transparentAppearance);
		//Cone co1 = new Cone(0.01f, 0.02f, insideAppearance_color);
		TransformGroup whiteCylinderGroup = new TransformGroup();
		TransformGroup colorCylinderGroup = new TransformGroup();
		//Transform3D tf3d = new Transform3D();
		//tf3d.set(new Vector3f(0.0f, (HEIGHT / 2 + 0.02f / 2), 0.0f));
		//coneGroup.setTransform(tf3d);
		whiteCylinderGroup.addChild(glassWhite);
		//coneGroup1.setTransform(tf3d);
		colorCylinderGroup.addChild(glassFilled);
		
		TransformGroup tgReleased1 = new TransformGroup();
		TransformGroup tgReleased = new TransformGroup();
		//Transform3D t3d = new Transform3D();
		//t3d.setTranslation(new Vector3d(0.0, 0.0, 0.005/2));
		//tgReleased1.setTransform(t3d);
		tgReleased.addChild(whiteCylinderGroup);
		tgReleased.addChild(tgReleased1);
		
		TransformGroup tgPressed1 = new TransformGroup();
		TransformGroup tgPressed = new TransformGroup();
		//t3d = new Transform3D();
		//t3d.setTranslation(new Vector3d(0.0, 0.0, 0.005/2));
		//tgPressed1.setTransform(t3d);
		tgPressed.addChild(tgPressed1);
		tgPressed.addChild(colorCylinderGroup);
		
		GlassSwitch.addChild(tgReleased);
		GlassSwitch.addChild(tgPressed);
		GlassSwitch.setWhichChild(1);
		
		tgReleased.addChild(glassTransparent);
		tgPressed.addChild(glassTransparent2);
		
		transGroup.addChild(GlassSwitch);
		
	}
	
	public void glassPressed(boolean flag) {
		if (flag) {
			GlassSwitch.setWhichChild(0);
		} else {
			GlassSwitch.setWhichChild(1);
		}
	}
}
