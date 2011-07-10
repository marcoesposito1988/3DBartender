package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class MenuItemBehavior extends Behavior {
	
	static Pointer pointer;
	static Menu menu;
	int times = 0;
	
	public static void setEnvironment(Menu m,Pointer p) {
		menu = m;
		pointer = p;
	}
	
	MenuItem menuItem;
	
	public MenuItemBehavior(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@Override
	public void initialize() {
		wakeupOn(new WakeupOnCollisionEntry(menuItem.modelGroup.getChild(0)));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void processStimulus(Enumeration criteria) {
		WakeupOnCollisionEntry ev;
		WakeupCriterion genericEvt;
		
		while (criteria.hasMoreElements()) {
			genericEvt = (WakeupCriterion) criteria.nextElement();
			if (genericEvt instanceof WakeupOnCollisionEntry){
				// get selected item, put it in pointer
				if (menuItem.isCategory) {
					menu.showCategory(menuItem.getName());
				} else {
					pointer.setModel(menuItem.model);
				}
			}
		}
		wakeupOn(new WakeupOnCollisionEntry(menuItem.modelGroup.getChild(0)));
	}
	
//	protected void selectedItem(Node node) {
//		pointer.attachModel(node);
//	}

}
