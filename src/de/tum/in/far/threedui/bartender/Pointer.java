package de.tum.in.far.threedui.bartender;

import java.io.File;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.ArrowObject;





public class Pointer extends TransformableObject {
	
	
	
	private de.tum.in.far.threedui.bartender.PoseReceiver pointerPoseReceiver;
	
	private static de.tum.in.far.threedui.bartender.ArrowObject arrowObject;

	private ModelObject model;
	private ArrowObject arrow;
	private static TransformGroup offset=new TransformGroup();
	private static BranchGroup bg = new BranchGroup();
	
	private Transform3D pointerPosition = new Transform3D();
	
	
	public Pointer() {
		// TODO Anke: create a posereceiver and store it as a field
		
		//pointerPoseReceiver = new PoseReceiver();
		//here make zylinder and cone
		
	}
	public void setArrow(ArrowObject arrow) {
		// TODO Anke: store this model and use it
		
		bg = new BranchGroup();
		//offset = new TransformGroup();
		this.arrow = arrow;
		//Transform3D t3d = new Transform3D();
		
		//offset.setTransform(t3d);
		offset.addChild(arrow);
		bg.addChild(offset);
		
		
	}
	
	public void setModel(ModelObject model) {
		// TODO Anke: store this model and use it
		
		//bg = new BranchGroup();
		//offset = new TransformGroup();
		this.model = model;
		//Transform3D t3d = new Transform3D();
		
		//offset.setTransform(t3d);
		offset.addChild(model);
		transGroup.addChild(offset);
		
		
	}
	public void setModelScaling(double factor) {
		pointerPosition.setScale(factor);
		offset.setTransform(pointerPosition);
	}
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Anke: you can use this main to test the pointer
		// in case it doesn't work, it may be that I have something wrong:
		// when you look for errors, check my code as well!
		
		UbitrackManager um = new UbitrackManager();
		
		//TestViewer test=new TestViewer();
		//test.initializeJava3D();
		
		
		Pointer p = new Pointer(); //method backModel... externel model 
		arrowObject=new ArrowObject();
		p.setArrow(arrowObject);//p.getPoseReceiver()
		//p.setModel(ModelFactory.loadVRMLModel("Sheep.wrl"));
		p.setModelScaling(10);
		
		um.addObjectToViewer(p);
		um.linkReceiverToMarker(p.getPoseReceiver(),"posesink");
		um.startTracking();
		
		
		//test.addObject(p);
		//test.addCameraDisplacement(new Vector3d(0,0,+0.5));
		
		
	}

	private PoseReceiver getPoseReceiver() {
		return pointerPoseReceiver;
	}



}
