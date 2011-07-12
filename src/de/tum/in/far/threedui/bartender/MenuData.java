package de.tum.in.far.threedui.bartender;

import java.io.File;

import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class MenuData {
	// TODO differentiate bottles
	private static final String BOTTLE_MODEL = "Sheep.wrl";
	private static final ModelType BOTTLE_TYPE = ModelType.VRML;
	
	public enum MenuItemType { ITEM, CATEGORY };
	
	public class MenuItemData {
		String name;
		MenuItemType type;
		String modelFileName;
		ModelType modelType;
		double scaling = 1;
		Vector3d offset = null;
		public MenuItemData(String name, String modelFileName,ModelType modelType, MenuItemType type) {
			this.name = name;
			this.modelFileName = modelFileName;
			this.type = type;
			this.modelType = modelType;
		}
		public MenuItemData(String name, String modelFileName,ModelType modelType, MenuItemType type, double scaling, Vector3d offset) {
			this(name,modelFileName,modelType,type);
			this.scaling = scaling;
			this.offset = offset;
		}
	}
	
	protected Tree<MenuItemData> menuData = new Tree<MenuItemData>();
	
	public MenuData() {
		loadStaticData();
	}
	
	private Node<MenuItemData> createCategory(String name,String modelFileName, ModelType modelType) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.CATEGORY));
	}
	
	private Node<MenuItemData> createCategory(String name,String modelFileName, ModelType modelType,double scaling, Vector3d offset) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.CATEGORY,scaling,offset));
	}
	
	private Node<MenuItemData> createItem(String name,String modelFileName, ModelType modelType) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.ITEM));
	}
	
	private void loadStaticData() {
		menuData.setRootElement(new Node<MenuItemData>());
//		Node<MenuItemData> alcoholics = menuData.getRootElement().addChild(createCategory("Alcoholics","Bottle.obj",ModelType.OBJ));
//		alcoholics.addChild(createItem("Gin","Bottle.obj",ModelType.OBJ));
//		alcoholics.addChild(createItem("Rum", "Bottle.obj",ModelType.OBJ));
//		Node<MenuItemData> non_alcoholics = menuData.getRootElement().addChild(createCategory("Non-Alcoholics","Bottle.obj",ModelType.OBJ));
//		non_alcoholics.addChild(createItem("Tonic", "Bottle.obj",ModelType.OBJ));
//		non_alcoholics.addChild(createItem("Cola", "Bottle.obj",ModelType.OBJ));
		
		// testing: sheep menu!
//		Node<MenuItemData> alcoholics = menuData.getRootElement().addChild(createCategory("Alcoholics","gin.obj",ModelType.OBJ, 0.01,new Vector3d(0,0,0)));
		Node<MenuItemData> alcoholics = menuData.getRootElement().addChild(createCategory("Alcoholics","Sheep.wrl",ModelType.VRML, 0.01,new Vector3d(0,0,0)));
		alcoholics.addChild(createItem("Gin",BOTTLE_MODEL,BOTTLE_TYPE));
		alcoholics.addChild(createItem("Rum", BOTTLE_MODEL,BOTTLE_TYPE));
		Node<MenuItemData> non_alcoholics = menuData.getRootElement().addChild(createCategory("Non-Alcoholics",BOTTLE_MODEL,BOTTLE_TYPE));
		non_alcoholics.addChild(createItem("Tonic",BOTTLE_MODEL,BOTTLE_TYPE));
		non_alcoholics.addChild(createItem("Cola",BOTTLE_MODEL,BOTTLE_TYPE));
	}
	
//	public MenuData(String dataFileName) {
//		loadFromFile(dataFileName);
//	}

}
