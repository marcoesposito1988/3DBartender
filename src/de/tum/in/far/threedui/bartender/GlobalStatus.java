package de.tum.in.far.threedui.bartender;

public class GlobalStatus {
	public static Menu menu;
	public static Pointer pointer;
	public static WorkbenchItem workbench;
	
	public static MenuItem selectedItem;
	public static Recipe recipe;
	public static Recipe status;

	public static void setEnvironment(Pointer p, Menu m, WorkbenchItem w) {
		menu = m;
		pointer = p;
		workbench = w;
	}
	
	public static void addIngredient(String id) {
		status.addIngredient(id);
		// check status
	}
}
