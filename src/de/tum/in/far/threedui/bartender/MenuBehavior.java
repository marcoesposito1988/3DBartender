package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOnTransformChange;

public class MenuBehavior extends Behavior {
	
	Menu menu;
	Pointer pointer;
	
	public MenuBehavior(Menu menu, Pointer pointer) {
		this.menu = menu;
		this.pointer = pointer;
	}

	@Override
	public void initialize() {
		wakeupOn(new WakeupOnCollisionEntry(menu));
		// at each movement of the pointer, take the position, cast a ray and highlight corresp. item in menu
		wakeupOn(new WakeupOnTransformChange(menu.transGroup));

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
				
				selectedItem(ev.getTriggeringPath().getObject());
			}
			if (genericEvt instanceof WakeupOnTransformChange) {
				// take position, cast the ray
			}
		}
	}
	
	protected void selectedItem(Node node) {
		pointer.attachModel(node);
	}

}
