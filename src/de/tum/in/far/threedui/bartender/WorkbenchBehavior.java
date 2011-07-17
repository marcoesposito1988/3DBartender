package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

import de.tum.in.far.threedui.bartender.Recipe.Status;

public class WorkbenchBehavior extends Behavior {
	
	static Bartender bartender;
	
	WorkbenchItem wbItem;
	
	int times = 0;
	
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
				if (bartender.selectedItem != null) {
					bartender.itemPoured();
					MenuItemBehavior.selectionMutex = false;
				}
			}
		}
		
		wakeupOn(new WakeupOnCollisionEntry(wbItem.transGroup.getChild(0)));
	}

}
