package de.tum.in.far.threedui.bartender;

import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;

public class MenuItemBehavior extends Behavior {
	static Bartender bartender;
	
	int times = 0;
	
	static boolean justVisualized = false;
	protected static Timer visualizationTimer = new Timer();
	static final int VISUALIZATION_TIME = 1000;
	
	static boolean selectionMutex = false;
	
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
		
		// TODO debug stuff
		System.out.println("MenuItem "+menuItem.getName()+"  detected collision");
		if (justVisualized)
			System.out.println("justVisualized is true, waiting");
		if (bartender.menu.viewable == false)
			System.out.println("menu is not viewable");
		if (bartender.pointer.viewable == false)
			System.out.println("pointer is not viewable");
		
		if (bartender.menu.viewable == true && bartender.pointer.viewable == true && justVisualized == false) {
			WakeupOnCollisionEntry ev;
			WakeupCriterion genericEvt;
			System.out.println("MenuItem processing stimulus");
			
			while (criteria.hasMoreElements()) {
				genericEvt = (WakeupCriterion) criteria.nextElement();
				if (genericEvt instanceof WakeupOnCollisionEntry){
					if (selectionMutex == false) {
						// get selected item, put it in pointer
						if (menuItem.isCategory) {
							justVisualized = true;
							bartender.menuCategorySelected(menuItem.getName());
							visualizationTimer.schedule(new TimerTask() {
								
								@Override
								public void run() {
									justVisualized = false;
									
								}
							}, VISUALIZATION_TIME);
							return;
						} else {
							selectionMutex = true;
							bartender.menuItemSelected(menuItem);
							return;
						}
					}
				}
			}
		}
		System.out.println("rearming behavior for menuItem "+menuItem.getName());
		wakeupOn(new WakeupOnCollisionEntry(menuItem.modelGroup.getChild(0)));
		
	}

}
