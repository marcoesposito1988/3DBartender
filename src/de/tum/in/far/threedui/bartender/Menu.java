package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.MenuData.MenuItemType;

public class Menu extends TransformableObject {
	
	public static final double DEFAULT_GAP = 0.15;
	
	
	// STORE
	protected MenuData menuData;
	protected Map<String,BranchGroup> menuBranches = new HashMap<String,BranchGroup>();
	protected Map<String,List<MenuItem>> menuItems = new HashMap<String,List<MenuItem>>();
	
	// VISUALIZATION
	protected BranchGroup displayedMenuItems = new BranchGroup();
	protected TransformGroup menuItemsGroup = new TransformGroup();
	protected Transform3D menuItemsPosition = new Transform3D();
	

	
	PoseReceiver poseReceiver;
	
	public Menu() {
		prepareGeometry();
		menuData = new MenuData();
		createMenuItems(menuData.menuData.getRootElement(),null);
	}
	
//	public Menu(String dataFileName) {
//		prepareGeometry();
//		menuData = new MenuData(dataFileName);
//	}
	
	private void prepareGeometry() {
		displayedMenuItems.addChild(menuItemsGroup);
		menuItemsGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		menuItemsGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		menuItemsGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		
		menuItemsPosition.rotX(Math.PI/2);
		menuItemsPosition.setTranslation(new Vector3d(0,0,0.025));
		menuItemsGroup.setTransform(menuItemsPosition);
		transGroup.addChild(displayedMenuItems);

	}
	
	private MenuItem createMenuItem(String name, String labelText, ModelObject model) {
		MenuItem newMenuItem = new MenuItem(name,labelText,model);
		return newMenuItem;
	}
	
	public void createMenuItems(Node<MenuData.MenuItemData> tree, String category) {
		if (tree.data == null) {
			// ROOT
			BranchGroup catBrGr = new BranchGroup();
			menuBranches.put("root",catBrGr);
			menuItems.put("root", new ArrayList<MenuItem>());
			for (Node<MenuData.MenuItemData> child : tree.children) {
				createMenuItems(child,"root");
			}
			placeMenuItems(catBrGr.getAllChildren(),DEFAULT_GAP);
			return;
		}
		if(tree.data.type == MenuItemType.CATEGORY) {
			// create category
			System.out.println("created category "+tree.data.name);
			BranchGroup catBrGr = new BranchGroup();
			menuBranches.put(tree.data.name, catBrGr);
			menuItems.put(tree.data.name, new ArrayList<MenuItem>());
			ModelObject model = null;
			try {
				model = ModelFactory.loadModel(tree.data.modelFileName, tree.data.modelType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MenuItem ciao = createMenuItem(tree.data.name, tree.data.name,	model);
			menuBranches.get(category).addChild(ciao);
			menuItems.get(category).add(ciao);
			// continue walking
			if (tree.children == null)
				return;
			for (Node<MenuData.MenuItemData> child : tree.children) {
				createMenuItems(child,tree.data.name);
			}
			placeMenuItems(catBrGr.getAllChildren(),DEFAULT_GAP);
		} else {	// tree.data.type == MenuItemType.ITEM
			// create item
			System.out.println("created item "+tree.data.name);
			ModelObject model = null;
			try {
				model = ModelFactory.loadModel(tree.data.modelFileName, tree.data.modelType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MenuItem ciao = createMenuItem(tree.data.name, tree.data.name,	model);
			menuBranches.get(category).addChild(ciao);
			menuItems.get(category).add(ciao);
		}
	}

	public void placeMenuItems(Enumeration<? extends TransformableObject> items, double gap) {
		Transform3D myt3d = new Transform3D();
		int i = 0;
		while (items.hasMoreElements()){
			myt3d.setTranslation(new Vector3d((i+0.5-menuBranches.size()/2)*gap,0,0));
			items.nextElement().transGroup.setTransform(myt3d);
			i++;
		}
	}
	
	public void showCategory(String categoryName) {
		menuItemsGroup.removeAllChildren();
		menuItemsGroup.addChild(menuBranches.get(categoryName));
		
		for (MenuItem mi : menuItems.get(categoryName)) {
			mi.armBehavior();
		}

	}

}
