package de.tum.in.far.threedui.bartender;

import java.io.File;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class Bartender {
	
	UbitrackManager ubitrackManager;
	
	Menu menu = new Menu();
	Pointer pointer = new Pointer();
//	Glass glass = new Glass();
	ModelObject glassObject;
	TransformableObject glassTransfObject;
	
	BranchGroup menuGroup = new BranchGroup();
	MenuBehavior menuBehavior;
	
	public Bartender() {
		ubitrackManager = new UbitrackManager();
	}
	
	public void start() {
		ubitrackManager = new UbitrackManager();

		pointer.setArrow(new ArrowObject());
		
		ubitrackManager.prepareTracking();

		PoseReceiver pointerReceiver = ubitrackManager.getReceiverForMarker("posesink");
		PoseReceiver menuReceiver = ubitrackManager.getReceiverForMarker("posesink3");
		PoseReceiver glassReceiver = ubitrackManager.getReceiverForMarker("posesink4");

		ubitrackManager.startTracking();
		
		ubitrackManager.addObjectToViewer(pointer);
		ubitrackManager.addObjectToViewer(menu);
//		ubitrackManager.addObjectToViewer(glass);
		
		pointer.setPoseReceiver(pointerReceiver);
		menu.setPoseReceiver(menuReceiver);
//		glass.setPoseReceiver(glassReceiver);
		
		glassTransfObject=new TransformableObject();
		try {
			glassObject = ModelFactory.loadModel("bar-set" + File.separator + "cocktail-glass.blend", ModelType.BLEND);
//			glassObject = ModelFactory.loadModel("Brown_Glass.wrl", ModelType.VRML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TransformGroup tg = new TransformGroup();
		Transform3D t3d = new Transform3D();
//		t3d.rotX(Math.PI/2);
		t3d.setScale(0.1);
		tg.setTransform(t3d);
		tg.addChild(glassObject);
		glassTransfObject.transGroup.addChild(tg);
		glassTransfObject.setPoseReceiver(glassReceiver);
		ubitrackManager.addObjectToViewer(glassTransfObject);
		menuBehavior = new MenuBehavior(menu,pointer);
		menuGroup.addChild(menuBehavior);
		menuBehavior.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0), 1));
		ubitrackManager.addObjectToViewer(menuGroup);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
	}

}
