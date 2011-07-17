package de.tum.in.far.threedui.bartender;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class GlassObject extends TransformableObject {

	private static final float GLASS_HEIGHT = 0.022f;
	private static final float BOTTOM_HEIGHT = GLASS_HEIGHT/8;
	private static final float AIR_HEIGHT = GLASS_HEIGHT/5;
	private static final float LIQUID_HEIGHT = GLASS_HEIGHT-(AIR_HEIGHT+BOTTOM_HEIGHT);
	private static final float EMPTY_HEIGHT = GLASS_HEIGHT-BOTTOM_HEIGHT;
	
	private static final float DIAMETER_OUTER = GLASS_HEIGHT/4;
	private static final float DIAMETER_INNER = DIAMETER_OUTER*0.95f;
	
	private static final Vector3f LIQUID_OFFSET = new Vector3f(0f,-((GLASS_HEIGHT-LIQUID_HEIGHT)/2-BOTTOM_HEIGHT),0f);
	private static final Vector3f AIR_OFFSET = new Vector3f(0f,(GLASS_HEIGHT-AIR_HEIGHT)/2,0f);
	private static final Vector3f EMPTY_OFFSET = new Vector3f(0f,BOTTOM_HEIGHT/2,0f);
	private static final Vector3f GLOBAL_OFFSET = new Vector3f(0,GLASS_HEIGHT/2,0);
	
	private static final float GLASS_TRANSPARENCY = 0.7f;
	private static final float AIR_TRANSPARENCY = 0.7f;
	private static final float LIQUID_TRANSPARENCY = 0.7f;

	protected Appearance glassAppearance = new Appearance();
	protected Appearance airAppearance = new Appearance();
	protected Appearance liquidAppearance = new Appearance();
	
	protected TransparencyAttributes glassTransparency;
	protected TransparencyAttributes airTransparency;
	protected TransparencyAttributes liquidTransparency;
	
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f red = new Color3f(0.8f, 0.3f, 0.3f);
	Color3f specular = new Color3f(0.9f, 0.5f, 0.5f);
	Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	
	protected Material transparentMat;
	protected Material whiteMat;
	protected Material liquidMat;
	
	protected TransformGroup glassTg = new TransformGroup();
	protected TransformGroup airTg = new TransformGroup();
	protected TransformGroup liquidTg = new TransformGroup();
	protected TransformGroup emptyTg = new TransformGroup();
	protected TransformableObject globalGlassTo = new TransformableObject();
	protected TransformableObject globalTo = new TransformableObject();
	
	protected Transform3D airPos = new Transform3D();
	protected Transform3D liquidPos = new Transform3D();
	protected Transform3D globalPos = new Transform3D();
	protected Transform3D emptyPos = new Transform3D();
	
	protected BranchGroup emptyGroup = new BranchGroup();
	protected BranchGroup filledGroup = new BranchGroup();
	
	protected Cylinder glass;
	protected Cylinder air;
	protected Cylinder liquid;
	protected Cylinder empty;
	
	protected Switch glassSwitch = new Switch();
	
	// objects for animations
	protected ModelObject umbrella;
	protected ModelObject straw;
	protected TransformableObject umbrellaGroup = new TransformableObject();
	protected TransformableObject strawGroup = new TransformableObject();
	protected TransformableObject animationTo = new TransformableObject();
	
	public GlassObject() {
		glassSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
		
		airPos.setTranslation(AIR_OFFSET);
		liquidPos.setTranslation(LIQUID_OFFSET);
		globalPos.setTranslation(GLOBAL_OFFSET);
		emptyPos.setTranslation(EMPTY_OFFSET);
		
		// Glass appearance
		transparentMat = new Material(white, black, red, specular, 25.0f);
		transparentMat.setLightingEnable(true);
		
	    glassTransparency = new TransparencyAttributes(TransparencyAttributes.BLENDED,GLASS_TRANSPARENCY);
		glassAppearance.setMaterial(transparentMat);
		glassAppearance.setTransparencyAttributes(glassTransparency);
		
		// Air appearance
		whiteMat = new Material(white, black, red, specular, 25.0f);
		whiteMat.setLightingEnable(true);
	    airTransparency = new TransparencyAttributes(TransparencyAttributes.BLENDED, AIR_TRANSPARENCY);
	    airAppearance.setMaterial(whiteMat);
		airAppearance.setTransparencyAttributes(airTransparency);
	
		// TODO work on liquid color
		liquidMat = new Material(white, black, red, specular, 25.0f);
		liquidMat.setLightingEnable(true);
		liquidMat.setCapability(Material.ALLOW_COMPONENT_WRITE);
		liquidTransparency = new TransparencyAttributes(TransparencyAttributes.BLENDED, LIQUID_TRANSPARENCY);
		liquidAppearance.setMaterial(liquidMat);
		liquidAppearance.setTransparencyAttributes(liquidTransparency);
		liquidAppearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		
		// geometry
		
		glass = new Cylinder(DIAMETER_OUTER, GLASS_HEIGHT, glassAppearance);
		air = new Cylinder(DIAMETER_INNER, AIR_HEIGHT, airAppearance);
		liquid = new Cylinder(DIAMETER_INNER, LIQUID_HEIGHT,liquidAppearance);
		empty = new Cylinder(DIAMETER_INNER, EMPTY_HEIGHT, airAppearance);
		
		airTg.addChild(air);
		liquidTg.addChild(liquid);
		glassTg.addChild(glass);

		airTg.setTransform(airPos);
		liquidTg.setTransform(liquidPos);
		globalTo.setTransform(globalPos);
		
		emptyTg.addChild(empty);
		emptyTg.setTransform(emptyPos);
		
		filledGroup.addChild(airTg);
		filledGroup.addChild(liquidTg);
		emptyGroup.addChild(emptyTg);
		
		glassSwitch.addChild(emptyGroup);
		glassSwitch.addChild(filledGroup);
		
		globalGlassTo.addChild(glassTg);
		globalGlassTo.addChild(glassSwitch);
		
		globalTo.addChild(globalGlassTo);
		
		addChild(globalTo);
		
		// stuff for animations
		
		try {
			umbrella = ModelFactory.loadModel("Umbrella.wrl", ModelType.VRML);
			straw = ModelFactory.loadModel("Straw.wrl", ModelType.VRML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		umbrellaGroup.addChild(umbrella);
		strawGroup.addChild(straw);
		animationTo.addChild(umbrellaGroup);
		animationTo.addChild(strawGroup);
		Transform3D t3d = new Transform3D();
		t3d.setScale(0.1);
		t3d.setTranslation(new Vector3d(0,0.005,0));
		animationTo.setTransform(t3d);
		
		glassSwitch.setWhichChild(0);
	}
	
	public void setTransparent() {
		glassSwitch.setWhichChild(0);
	}
	
	public void setColor(Color3f liquidColor) {
		liquidMat.setAmbientColor(liquidColor);
		liquidAppearance.setMaterial(liquidMat);
		glassSwitch.setWhichChild(1); 
	}
	
	public void doSuccessAnimation() {
		globalTo.addChild(animationTo);
	}
	
	public void reset() {
		globalTo.removeAllChildren();
		globalTo.addChild(globalGlassTo);
		setTransparent();
	}
}
