package de.tum.in.far.threedui.bartender;

import java.awt.Color;

import javax.vecmath.Vector3d;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class MenuData {
	// TODO differentiate bottles
	private static final String BOTTLE_MODEL = "vodka.obj";
	private static final ModelType BOTTLE_TYPE = ModelType.OBJ;
	private static final float BOTTLE_SCALING = 0.01f;
	private static final Vector3d BOTTLE_OFFSET = new Vector3d(0,-0.08,0);
	
	public enum MenuItemType { ITEM, CATEGORY };
	
	public class MenuItemData {
		String name;
		MenuItemType type;
		String modelFileName;
		ModelType modelType;
		Color glassColor;
		double scaling = 1;
		Vector3d offset = null;
		public MenuItemData(String name, String modelFileName,ModelType modelType, MenuItemType type, Color color) {
			this.name = name;
			this.modelFileName = modelFileName;
			this.type = type;
			this.modelType = modelType;
			this.glassColor = color;
		}
		public MenuItemData(String name, String modelFileName,ModelType modelType, MenuItemType type, Color color, double scaling, Vector3d offset) {
			this(name,modelFileName,modelType,type,color);
			this.scaling = scaling;
			this.offset = offset;
		}
	}
	
	protected Tree<MenuItemData> menuData = new Tree<MenuItemData>();
	
	public MenuData() {
		loadStaticData();
	}
	
	private Node<MenuItemData> createCategory(String name,String modelFileName, ModelType modelType) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.CATEGORY,null));
	}
	
	private Node<MenuItemData> createCategory(String name,String modelFileName, ModelType modelType,double scaling, Vector3d offset) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.CATEGORY,null,scaling,offset));
	}
	
	private Node<MenuItemData> createItem(String name,String modelFileName, ModelType modelType, Color glassColor) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.ITEM, glassColor));
	}
	
	private Node<MenuItemData> createItem(String name,String modelFileName, ModelType modelType, Color glassColor,double scaling, Vector3d offset) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.ITEM, glassColor,scaling,offset));
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
		Node<MenuItemData> alcoholics = menuData.getRootElement().addChild(createCategory("Alcoholics",BOTTLE_MODEL,BOTTLE_TYPE));
		alcoholics.addChild(createItem("Gin",BOTTLE_MODEL,BOTTLE_TYPE,Color.white));
		alcoholics.addChild(createItem("Rum", BOTTLE_MODEL,BOTTLE_TYPE,Color.yellow));
		alcoholics.addChild(createItem("Vodka", BOTTLE_MODEL,BOTTLE_TYPE,Color.white));
		Node<MenuItemData> non_alcoholics = menuData.getRootElement().addChild(createCategory("Non-Alcoholics",BOTTLE_MODEL,BOTTLE_TYPE));
		non_alcoholics.addChild(createItem("Tonic",BOTTLE_MODEL,BOTTLE_TYPE,Color.white));
		non_alcoholics.addChild(createItem("Cola",BOTTLE_MODEL,BOTTLE_TYPE,Color.black));
		non_alcoholics.addChild(createItem("RedBull", BOTTLE_MODEL,BOTTLE_TYPE,Color.orange));
	}
	
//	public MenuData(String dataFileName) {
//		loadFromFile(dataFileName);
//	}

}
