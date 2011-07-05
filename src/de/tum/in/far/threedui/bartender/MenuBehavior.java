package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class MenuBehavior extends Behavior {
	
	Menu menu;
	Pointer pointer;
	
	public MenuBehavior(Menu menu, Pointer pointer) {
		this.menu = menu;
		this.pointer = pointer;
	}

	@Override
	public void initialize() {
		armOnMenuItems();
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
				System.out.println(ev.getTriggeringPath().getObject().getName());
				System.out.println(ev.getArmingPath().getObject().getName());
//				selectedItem(ev.getTriggeringPath().getObject());
			}
		}
		
		armOnMenuItems();
	}
	
	protected void armOnMenuItems() {
		Enumeration<MenuItem> items = menu.displayedMenuItems.getAllChildren();
		while (items.hasMoreElements()) {
			MenuItem mi = items.nextElement();
			System.out.println("arming for "+mi.toString());
			wakeupOn(new WakeupOnCollisionEntry(mi.getBounds()));
		}
	}
	
//	protected void selectedItem(Node node) {
//		pointer.attachModel(node);
//	}

}
