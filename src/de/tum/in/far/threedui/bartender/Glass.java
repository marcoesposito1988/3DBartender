package de.tum.in.far.threedui.bartender;

import java.io.File;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Glass extends WorkbenchItem {
	
	protected String modelFileName = "bar-set" + File.separator + "cocktail-glass.obj";
	
	protected ModelObject model;
	
	protected TransformGroup adjustGroup = new TransformGroup();
	
	public Glass() {
		model = ModelFactory.loadObjModel(modelFileName);
		Transform3D adjust = new Transform3D();
		adjust.rotX(Math.PI/2);
		adjust.setScale(0.1);
		adjustGroup.setTransform(adjust);
		adjustGroup.addChild(model);
		transGroup.addChild(adjustGroup);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestViewer tv = new TestViewer();
		tv.initializeJava3D();
		MenuItem mi = new MenuItem("Glass","Cocktail Glass",ModelFactory.loadColladaModel("glass study edited.dae"));
		mi.setModelScaling(0.18);
		//mi.setLabelBottom(1);
		BranchGroup mybrgr = new BranchGroup();
		TransformGroup mytrgr = new TransformGroup();
		Transform3D myt3d = new Transform3D();
		myt3d.rotY(Math.PI/2);
		mytrgr.setTransform(myt3d);
		mytrgr.addChild(mi);
		mybrgr.addChild(mytrgr);
		tv.addObject(mybrgr);
		Appearance app = new Appearance();
		Material latumamma = new Material();
		latumamma.setEmissiveColor((float)0.1,(float) 0.2 ,(float)0.2);
		latumamma.setDiffuseColor((float)0.3 ,(float)0.3 ,(float)0.3);
		latumamma.setShininess(0.2f);
		latumamma.setSpecularColor(.9f,.9f,.9f);
		app.setMaterial(latumamma);
		mi.transGroup.getChild(0)
		tv.addCameraDisplacement(new Vector3d(0,0,+0.5));
	}

}
