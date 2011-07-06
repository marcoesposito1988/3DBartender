package de.tum.in.far.threedui.bartender;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class MenuData {
	
	public enum MenuItemType { ITEM, CATEGORY };
	
	public class MenuItemData {
		String name;
		MenuItemType type;
		String modelFileName;
		ModelType modelType;
		public MenuItemData(String name, String modelFileName,ModelType modelType, MenuItemType type) {
			this.name = name;
			this.modelFileName = modelFileName;
			this.type = type;
			this.modelType = modelType;
		}
	}
	
	protected Tree<MenuItemData> menuData = new Tree<MenuItemData>();
	
	public MenuData() {
		loadStaticData();
	}
	
	private Node<MenuItemData> createCategory(String name,String modelFileName, ModelType modelType) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.CATEGORY));
	}
	
	private Node<MenuItemData> createItem(String name,String modelFileName, ModelType modelType) {
		return new Node<MenuItemData>(new MenuItemData(name,modelFileName,modelType,MenuItemType.ITEM));
	}
	
	private void loadStaticData() {
		menuData.setRootElement(new Node<MenuItemData>());
		Node<MenuItemData> alcoholics = menuData.getRootElement().addChild(createCategory("Alcoholics","Bottle.obj",ModelType.OBJ));
		alcoholics.addChild(createItem("Gin","Bottle.obj",ModelType.OBJ));
		alcoholics.addChild(createItem("Rum", "Bottle.obj",ModelType.OBJ));
		Node<MenuItemData> non_alcoholics = menuData.getRootElement().addChild(createCategory("Non-Alcoholics","Bottle.obj",ModelType.OBJ));
		non_alcoholics.addChild(createItem("Tonic", "Bottle.obj",ModelType.OBJ));
		non_alcoholics.addChild(createItem("Cola", "Bottle.obj",ModelType.OBJ));
	}
	
//	public MenuData(String dataFileName) {
//		loadFromFile(dataFileName);
//	}

}
