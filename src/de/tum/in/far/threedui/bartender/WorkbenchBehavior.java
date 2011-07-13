package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class WorkbenchBehavior extends Behavior {
	
	static Pointer pointer;
	static Menu menu;
	
	WorkbenchItem wbItem;
	
	int times = 0;
	
	public static void setEnvironment(Menu m, Pointer p) {
		menu = m;
		pointer = p;
	}
	
	public WorkbenchBehavior(WorkbenchItem wbItem) {
		this.wbItem = wbItem;
	}

	@Override
	public void initialize() {
		wakeupOn(new WakeupOnCollisionEntry(wbItem.transGroup.getChild(0)));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void processStimulus(Enumeration criteria) {
		
		if (wbItem.viewable == false) {
			wakeupOn(new WakeupOnCollisionEntry(wbItem.transGroup.getChild(0)));
			return;
		}
		
		WakeupOnCollisionEntry ev;
		WakeupCriterion genericEvt;
		
		while (criteria.hasMoreElements()) {
			genericEvt = (WakeupCriterion) criteria.nextElement();
			if (genericEvt instanceof WakeupOnCollisionEntry){
				ev = (WakeupOnCollisionEntry) genericEvt;
				if (GlobalStatus.selectedItem != null) {
					String ingredient = GlobalStatus.selectedItem.getName();
					GlobalStatus.status.addIngredient(ingredient);
					GlobalStatus.pointer.detachModel();
					GlobalStatus.selectedItem.reattachModel();
					GlobalStatus.menu.showCategory("root");
					System.out.println("Added "+GlobalStatus.selectedItem.getName());
				}
			}
		}
		
		wakeupOn(new WakeupOnCollisionEntry(wbItem.transGroup.getChild(0)));
	}

}
