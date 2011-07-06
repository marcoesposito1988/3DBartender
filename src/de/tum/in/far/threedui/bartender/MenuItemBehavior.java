package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class MenuItemBehavior extends Behavior {
	
	static Pointer pointer;
	
	public static void setPointer(Pointer p) {
		pointer = p;
	}
	
	MenuItem menuItem;
	
	public MenuItemBehavior(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@Override
	public void initialize() {
		wakeupOn(new WakeupOnCollisionEntry(menuItem.getBounds()));
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
				ev = (WakeupOnCollisionEntry) genericEvt;
				System.out.println("COLLISION");
				//System.out.println(ev.getArmingPath().getObject().getName());
//				selectedItem(ev.getTriggeringPath().getObject());
			}
		}
		wakeupOn(new WakeupOnCollisionEntry(menuItem.model.getBounds()));
	}
	
//	protected void selectedItem(Node node) {
//		pointer.attachModel(node);
//	}

}
