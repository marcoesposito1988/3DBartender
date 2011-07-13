package de.tum.in.far.threedui.bartender;

import java.util.Timer;
import java.util.TimerTask;

import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;

public class CollidableObject extends TransformableObject {
	
	// transGroup is used for adjustment, poseGroup for receiving poses
	TransformGroup poseGroup = new TransformGroup();
	
	public static final int REMOVE_DELAY = 100000;
	
	Behavior behavior = null;
	
	ObstructablePoseReceiver poseReceiver = null;
	
	Group parent = null;
	
	public Boolean viewable = false;
	
	private Timer obstructionTimer = null;
	
	public CollidableObject() {
		poseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		poseGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		poseGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		poseGroup.setCapability(BranchGroup.ALLOW_DETACH);
		
		deepRemoveChild(0);
		poseGroup.addChild(transGroup);
		deepAddChild(poseGroup);
	}
	
	public void setBehavior(Behavior b) {
		behavior = b;
	}
	
	public void startBehavior() {
		if (behavior != null)
			behavior.setEnable(true);
	}
	
	public void stopBehavior() {
		if (behavior != null)
			behavior.setEnable(false);
	}
	
	public void setPoseReceiver(ObstructablePoseReceiver pr) {
		poseReceiver = pr;
		pr.setTransformGroup(poseGroup);
		pr.object = this;
	}
	
	public void updatedStatus() {
//		System.out.println("updating status");
		if (viewable) {
//			System.out.println("object is already viewable, resetting timer");
			obstructionTimer.cancel();
		} else {
//			System.out.println("object was not viewable, adding it to the scene");
			if (parent.indexOfChild(this) == -1)
				parent.addChild(this);
			startBehavior();
		}
//		System.out.println("creating new timer");
		obstructionTimer = new Timer();
		obstructionTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (viewable) {
					System.out.println(getClass().getName()+", timer elapsed: removing object from screen");
					stopBehavior();
					try {
						Thread.sleep(REMOVE_DELAY);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (getParent() != null)
						parent = (Group) getParent();
					detach();
					viewable = false;
				}
				
			}
			
		}, REMOVE_DELAY);
		
		viewable = true;
	}

}
