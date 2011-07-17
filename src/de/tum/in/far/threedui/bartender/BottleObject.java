package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;

public class BottleObject extends TransformableObject {
	private static final float BODY_HEIGHT = 0.04f;
	private static final float BODY_RADIUS = 0.01f;
	private static final float ROUND_RADIUS = BODY_RADIUS;
	private static final float NECK_HEIGHT = BODY_HEIGHT/2;
	private static final float NECK_RADIUS = BODY_RADIUS/3;
	
	private static final Vector3d BODY_OFFSET = new Vector3d(0,0,0);
	private static final Vector3d ROUND_OFFSET = new Vector3d(0,BODY_HEIGHT/2,0);
	private static final Vector3d NECK_OFFSET = new Vector3d(0,BODY_HEIGHT/2+ROUND_RADIUS+NECK_HEIGHT/2-0.002,0);
	
	protected Cylinder neck = new Cylinder(NECK_RADIUS,NECK_HEIGHT);
	protected Sphere round = new Sphere(ROUND_RADIUS);
	protected Cylinder body = new Cylinder(BODY_RADIUS,BODY_HEIGHT);
	
	protected TransformGroup bodyTg = new TransformGroup();
	protected TransformGroup roundTg = new TransformGroup();
	protected TransformGroup neckTg = new TransformGroup();
	
	protected TransformGroup globalTg = new TransformGroup();
	
	protected Material transparentMat;
	protected Appearance glassAppearance = new Appearance();
	protected TransparencyAttributes glassTransparency;
	private static final float GLASS_TRANSPARENCY = 0f;
	private static final Color3f BLACK = new Color3f(0.0f, 0.0f, 0.0f);
	private static final Color3f RED = new Color3f(0.8f, 0.3f, 0.3f);
	private static final Color3f SPECULAR = new Color3f(0.9f, 0.5f, 0.5f);
	private static final Color3f WHITE = new Color3f(1.0f, 1.0f, 1.0f);
	
	public BottleObject() {
		transparentMat = new Material(WHITE, BLACK, RED, SPECULAR, 5.0f);
		transparentMat.setLightingEnable(true);
		
	    glassTransparency = new TransparencyAttributes(TransparencyAttributes.BLENDED,GLASS_TRANSPARENCY);
		glassAppearance.setMaterial(transparentMat);
		glassAppearance.setTransparencyAttributes(glassTransparency);
		
		body.setAppearance(glassAppearance);
		neck.setAppearance(glassAppearance);
		round.setAppearance(glassAppearance);
		
		bodyTg.addChild(body);
		roundTg.addChild(round);
		neckTg.addChild(neck);
		
		Transform3D bodyt3d = new Transform3D();
		bodyt3d.setTranslation(BODY_OFFSET);
		Transform3D neckt3d = new Transform3D();
		neckt3d.setTranslation(NECK_OFFSET);
		Transform3D roundt3d = new Transform3D();
		roundt3d.setTranslation(ROUND_OFFSET);
		
		bodyTg.setTransform(bodyt3d);
		roundTg.setTransform(roundt3d);
		neckTg.setTransform(neckt3d);
		
		globalTg.addChild(bodyTg);
		globalTg.addChild(roundTg);
		globalTg.addChild(neckTg);
		
		addChild(globalTg);
	}
	
	public void setBottleAppearance(Appearance app) {
		neck.setAppearance();
		round.setAppearance();
		body.setAppearance();
	}
	
}
