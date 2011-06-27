package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public class Menu extends TransformableObject {
	
	//public static ModelFactory modelFactory;
	
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();

	private TransformGroup menuItemsGroup = new TransformGroup();
	
	public Menu() {
		this.addChild(menuItemsGroup);
	}
	
	public void loadMenuItem(String name, String labelText, String modelFileName) {
		MenuItem newMenuItem = new MenuItem(name,labelText,modelFactory.loadModel(modelFileName));
		menuItems.add(newMenuItem);
		menuItemsGroup.addChild(newMenuItem);
	}

}
