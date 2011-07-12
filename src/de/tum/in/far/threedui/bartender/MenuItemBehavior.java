package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class MenuItemBehavior extends Behavior {
	
	int times = 0;
	
	static boolean justVisualized = false;
	protected Timer visualizationTimer = new Timer();
	static final int VISUALIZATION_TIME = 1000;
	
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
		if (GlobalStatus.menu.viewable == true && justVisualized == false) {
			WakeupOnCollisionEntry ev;
			WakeupCriterion genericEvt;
			
			while (criteria.hasMoreElements()) {
				genericEvt = (WakeupCriterion) criteria.nextElement();
				if (genericEvt instanceof WakeupOnCollisionEntry){
					if (GlobalStatus.pointer.viewable == true) {
						// get selected item, put it in pointer
						if (menuItem.isCategory) {
							justVisualized = true;
							GlobalStatus.menu.showCategory(menuItem.getName());
							visualizationTimer.schedule(new TimerTask() {
								
								@Override
								public void run() {
									justVisualized = false;
									
								}
							}, VISUALIZATION_TIME);
						} else {
							GlobalStatus.pointer.attachModel(menuItem.detachModel());
						}
					}
				}
			}
			wakeupOn(new WakeupOnCollisionEntry(menuItem.modelGroup.getChild(0)));
		}
		
	}
	
//	protected void selectedItem(Node node) {
//		pointer.attachModel(node);
//	}

}
