package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Menu extends TransformableObject {
	
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();

	private TransformGroup menuItemsGroup = new TransformGroup();
	
	private PoseReceiver poseReceiver = new PoseReceiver();
	
	private Transform3D position = new Transform3D();
	
	public Menu() {
		position.rotX(Math.PI/2);
		position.setTranslation(new Vector3d(0,0,0.025));
		menuItemsGroup.setTransform(position);
		transGroup.addChild(menuItemsGroup);
		
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		poseReceiver.setTransformGroup(transGroup);
	}
	
	public void createMenuItem(String name, String labelText, ModelObject model) {
		MenuItem newMenuItem = new MenuItem(name,labelText,model);
		menuItems.add(newMenuItem);
		menuItemsGroup.addChild(newMenuItem);
	}
	
	public void placeMenuItems(double gap) {
		if (menuItems.size() == 1)
			return;
		Transform3D myt3d = new Transform3D();
		int i = 0;
		for (MenuItem mi : menuItems) {
			myt3d.setTranslation(new Vector3d((i+0.5-menuItems.size()/2)*gap,0,0));
			mi.transGroup.setTransform(myt3d);
			i++;
		}
	}
	
	public PoseReceiver getPoseReceiver() {
		return poseReceiver;
	}
	
	public static void main(String[] args) {
		UbitrackManager um = new UbitrackManager();
		Menu m = new Menu();
		m.createMenuItem("Alcoholics","Alcoholics",ModelFactory.loadVRMLModel("Sheep.wrl"));
		m.createMenuItem("NonAlcoholics","Non Alcoholics",ModelFactory.loadVRMLModel("Sheep.wrl"));
		m.createMenuItem("Tools","Tools",ModelFactory.loadVRMLModel("Sheep.wrl"));
		m.createMenuItem("Ice","Ice",ModelFactory.loadVRMLModel("Sheep.wrl"));
		m.placeMenuItems(0.15);
		if (!um.linkReceiverToMarker(m.getPoseReceiver(), "posesink"))
			System.out.println("Error: could not link receiver to marker");	
		
		um.addObjectToViewer(m);
		
		um.startTracking();
	}

}
