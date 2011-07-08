package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class WorkbenchBehavior extends Behavior {
	
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
		WakeupOnCollisionEntry ev;
		WakeupCriterion genericEvt;
		
		while (criteria.hasMoreElements()) {
			genericEvt = (WakeupCriterion) criteria.nextElement();
			if (genericEvt instanceof WakeupOnCollisionEntry){
				ev = (WakeupOnCollisionEntry) genericEvt;
				System.out.println("COLLISION");
				System.out.println("with glass, "+times+++" times");
			}
		}
		
		wakeupOn(new WakeupOnCollisionEntry(wbItem.transGroup.getChild(0)));
	}

}
