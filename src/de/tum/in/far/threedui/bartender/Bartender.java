package de.tum.in.far.threedui.bartender;

import java.util.Timer;
import java.util.TimerTask;

import javax.media.j3d.BranchGroup;

import de.tum.in.far.threedui.bartender.Recipe.Status;

public class Bartender {
	private static final long END_WAIT = 3000;
	static Bartender instance;
	MenuItem selectedItem = null;
	Recipe currentStatus = new Recipe();
	Recipe targetRecipe = null;
	
	UbitrackManager ubitrackManager;
	
	Menu menu = new Menu();
	Pointer pointer = new Pointer();
	Glass glass = new Glass();
	ModelObject glassObject;
	TransformableObject glassTransfObject;
	BranchGroup globalGroup = new BranchGroup();
	
	Timer endTimer = new Timer();
	
	public Bartender() {
		ubitrackManager = new UbitrackManager();
		RecipeFactory.loadRecipes();
		MenuItemBehavior.bartender = this;
		WorkbenchBehavior.bartender = this;
		
		globalGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		globalGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		globalGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
	}
	
	public void start() {
		targetRecipe = RecipeFactory.getRandomRecipe();
		
		ubitrackManager.prepareTracking();

		ObstructablePoseReceiver pointerReceiver = ubitrackManager.getObstructableReceiverForMarker("posesink4");
		ObstructablePoseReceiver menuReceiver = ubitrackManager.getObstructableReceiverForMarker("posesink3");
		ObstructablePoseReceiver glassReceiver = ubitrackManager.getObstructableReceiverForMarker("posesink");

		ubitrackManager.startTracking();
		
		pointer.setPoseReceiver(pointerReceiver);
		menu.setPoseReceiver(menuReceiver);
		glass.setPoseReceiver(glassReceiver);

		menu.showCategory("root");
		
		pointer.parent = globalGroup;
		glass.parent = globalGroup;
		menu.parent = globalGroup;
		
		ubitrackManager.addObjectToViewer(globalGroup);

		ubitrackManager.viewer.setMessageText(targetRecipe.name);
	}
	
	public void reset() {
		if (selectedItem != null) {
			pointer.detachModel();
			selectedItem.reattachModel();
			selectedItem = null;
		}
		glass.emptyGlass();
		targetRecipe = RecipeFactory.getRandomRecipe();
		currentStatus = new Recipe();
		ubitrackManager.viewer.setMessageText(targetRecipe.name);
		menu.showCategory("root");
	}
	
	void menuCategorySelected(String id) {
		menu.showCategory(id);
	}
	
	void menuItemSelected(MenuItem mi) {
		pointer.attachModel(mi.detachModel());
		selectedItem = mi;
	}
	
	void itemPoured() {
		String ingredient = selectedItem.getName();
		currentStatus.addIngredient(ingredient);
		glass.pourStuff(selectedItem.glassColor);
		pointer.detachModel();
		selectedItem.reattachModel();
		menu.showCategory("root");
		currentStatus.addIngredient(selectedItem.getName());
		System.out.println("Added "+selectedItem.getName());
		selectedItem = null;
		Recipe.Status status = targetRecipe.verify(currentStatus);
		if (status == Status.COMPLETE) {
			// do success animation
			System.out.println("SUCCESS!!");
			glass.doSuccessAnimation();
			ubitrackManager.viewer.setMessageText("SUCCESS!");
		}
		if (status == Status.WRONG) {
			// do fail animation
			System.out.println("FAIL!!!");
			ubitrackManager.viewer.setMessageText("FAIL");
		}
		if (status == Status.COMPLETE || status == Status.WRONG) {
			endTimer.schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					reset();
				}
				
			}, END_WAIT);
			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bartender bart = new Bartender();
		bart.start();
		
	}

}
