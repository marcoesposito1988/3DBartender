package de.tum.in.far.threedui.bartender;

public class Recipe {
	
	public enum Status { COMPLETE, INCOMPLETE, WRONG };
	
	//We could have each tree as a tree and separate between 
	// addIngredients: directly
	// addMuddler: take a new layer
	
	// M: I will think about how to handle this, I'll let you know
	
	public Recipe(String name) {
		// TODO Anke: sets name, initializes ingredients list as an empty list
		
		
		
	}
	
	public Recipe() {
		// TODO Auto-generated constructor stub
	}

	public Status verify(Recipe solution) {
		//TODO Anke: compares contents of "solution" with its own, returns a value from Status
		
	}
	
	public void addIngredient(String id) {
		// TODO tree
		
	
	}
	public void addMuddler(String id) {
	
	}

}
