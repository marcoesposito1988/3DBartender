package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.MenuData.MenuItemType;

public class Menu extends TransformableObject {
	
	public static final double DEFAULT_GAP = 0.15;
	
	protected Map<String,TransformGroup> menuItems = new HashMap<String,TransformGroup>();
	
	protected TransformGroup displayedMenuItems;

	protected TransformGroup menuItemsGroup = new TransformGroup();
	
	protected Transform3D menuItemsPosition = new Transform3D();
	
	protected MenuData menuData;
	
	PoseReceiver poseReceiver;
	
	public Menu() {
		prepareGeometry();
		menuData = new MenuData();
		createMenuItems(menuData.menuData.getRootElement(),null);
		showCategory("root");
	}
	
//	public Menu(String dataFileName) {
//		prepareGeometry();
//		menuData = new MenuData(dataFileName);
//	}
	
	private void prepareGeometry() {
		menuItemsPosition.rotX(Math.PI/2);
		menuItemsPosition.setTranslation(new Vector3d(0,0,0.025));
		menuItemsGroup.setTransform(menuItemsPosition);
		transGroup.addChild(menuItemsGroup);
		
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
	}
	
	private MenuItem createMenuItem(String name, String labelText, ModelObject model) {
		MenuItem newMenuItem = new MenuItem(name,labelText,model);
		return newMenuItem;
	}
	
	public void createMenuItems(Node<MenuData.MenuItemData> tree, String category) {
		if (tree.data == null) {
			// ROOT
			TransformGroup catTrGr = new TransformGroup();
			menuItems.put("root",catTrGr);
			for (Node<MenuData.MenuItemData> child : tree.children) {
				createMenuItems(child,"root");
			}
			placeMenuItems(catTrGr.getAllChildren(),DEFAULT_GAP);
			return;
		}
		if(tree.data.type == MenuItemType.CATEGORY) {
			// create category
			System.out.println("created category "+tree.data.name);
			TransformGroup catTrGr = new TransformGroup();
			menuItems.put(tree.data.name, catTrGr);
			ModelObject model = null;
			try {
				model = ModelFactory.loadModel(tree.data.modelFileName, tree.data.modelType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MenuItem ciao = createMenuItem(tree.data.name, tree.data.name,	model);
			menuItems.get(category).addChild(ciao);
			// continue walking
			if (tree.children == null)
				return;
			for (Node<MenuData.MenuItemData> child : tree.children) {
				createMenuItems(child,tree.data.name);
			}
			placeMenuItems(catTrGr.getAllChildren(),DEFAULT_GAP);
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
			menuItems.get(category).addChild(ciao);
		}
	}

	public void placeMenuItems(Enumeration<? extends TransformableObject> items, double gap) {
		Transform3D myt3d = new Transform3D();
		int i = 0;
		while (items.hasMoreElements()){
			myt3d.setTranslation(new Vector3d((i+0.5-menuItems.size()/2)*gap,0,0));
			items.nextElement().transGroup.setTransform(myt3d);
			i++;
		}
	}
	
	public void showCategory(String categoryName) {
		menuItemsGroup.removeAllChildren();
		menuItemsGroup.addChild(menuItems.get(categoryName));
	}

}
