package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.MenuData.MenuItemType;

public class Menu extends CollidableObject {
	
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
		
		displayedMenuItems.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		displayedMenuItems.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		displayedMenuItems.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		displayedMenuItems.setCapability(BranchGroup.ALLOW_DETACH);
		
		menuItemsGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		menuItemsGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		menuItemsGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);

		menuItemsGroup.addChild(displayedMenuItems);
		menuItemsPosition.rotX(Math.PI/2);
		menuItemsPosition.setTranslation(new Vector3d(0,0,0.025));
		menuItemsGroup.setTransform(menuItemsPosition);
		addChild(menuItemsGroup);

	}
	
	private MenuItem createMenuItem(String name, String labelText, TransformableObject model, boolean isCategory) {
		MenuItem newMenuItem = new MenuItem(name,labelText,model,isCategory);
		return newMenuItem;
	}
	
	private MenuItem createMenuItem(String name, String labelText, TransformableObject model, boolean isCategory, double scaling, Vector3d offset) {
		MenuItem newMenuItem = new MenuItem(name,labelText,model,isCategory);
		Transform3D t3d = new Transform3D();
		t3d.setScale(scaling);
		t3d.setTranslation(offset);
		newMenuItem.transGroup.setTransform(t3d);
		return newMenuItem;
	}
	
	public void createMenuItems(Node<MenuData.MenuItemData> tree, String category) {
		if (tree.data == null) {
			// ROOT
			BranchGroup catBrGr = new BranchGroup();
			catBrGr.setCapability(BranchGroup.ALLOW_DETACH);
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
			catBrGr.setCapability(BranchGroup.ALLOW_DETACH);
			menuBranches.put(tree.data.name, catBrGr);
			menuItems.put(tree.data.name, new ArrayList<MenuItem>());
			ModelObject model = null;
			try {
				model = ModelFactory.loadModel(tree.data.modelFileName, tree.data.modelType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MenuItem ciao;
			if(tree.data.offset == null)
				ciao = createMenuItem(tree.data.name, tree.data.name,model,true);
			else
				ciao = createMenuItem(tree.data.name, tree.data.name, model,true,tree.data.scaling,tree.data.offset);
			menuBranches.get(category).addChild(ciao);
			menuItems.get(category).add(ciao);
			// continue walking
			if (tree.children == null)
				return;
			for (Node<MenuData.MenuItemData> child : tree.children) {
				createMenuItems(child,tree.data.name);
			}
			ArrowObject arrObj = new ArrowObject();
			Transform3D arrAdj = new Transform3D();
			arrAdj.rotX(Math.PI/2);
			arrAdj.rotY(Math.PI/2);
			arrAdj.rotZ(Math.PI/2);
			arrObj.transGroup.setTransform(arrAdj);
			MenuItem back = createMenuItem(category, "Back", arrObj,true);
			menuBranches.get(tree.data.name).addChild(back);
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
			MenuItem ciao;
			if(tree.data.offset == null)
				ciao = createMenuItem(tree.data.name, tree.data.name,model,false);
			else
				ciao = createMenuItem(tree.data.name, tree.data.name, model,false,tree.data.scaling,tree.data.offset);
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
		if (displayedMenuItems.numChildren() != 0)
			displayedMenuItems.removeAllChildren();
		
		BranchGroup category = menuBranches.get(categoryName);
		displayedMenuItems.addChild(category);
		Enumeration<MenuItem> children = category.getAllChildren();
		while (children.hasMoreElements()) {
			MenuItem child = (MenuItem) children.nextElement();
			child.armBehavior();
		}
	}
	
	@Override
	public void startBehavior() {
		Enumeration<MenuItem> children = ((Group) displayedMenuItems.getChild(0)).getAllChildren();
		while (children.hasMoreElements()) {
			MenuItem child = (MenuItem) children.nextElement();
			child.behavior.setEnable(true);
		}
	}
	
	@Override
	public void stopBehavior() {
		Enumeration<MenuItem> children = ((Group) displayedMenuItems.getChild(0)).getAllChildren();
		while (children.hasMoreElements()) {
			MenuItem child = (MenuItem) children.nextElement();
			child.behavior.setEnable(false);
		}
	}

}
