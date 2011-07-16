package de.tum.in.far.threedui.bartender;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Recipe {
	
	public enum Status { COMPLETE, INCOMPLETE, WRONG };
	
	public String name;
	// TODO if we had muddler or shaker, this would be a tree
	protected Set<String> content;
	
	public Recipe() {
		this.name = "solution";
		content = new HashSet<String>();
	}
	
	public Recipe(String name) {
		this.name = name;
		content = new HashSet<String>();
	}

	public Status verify(Recipe solution) {
		Iterator<String> ingr = solution.content.iterator();
		while(ingr.hasNext()) {
			if (!content.contains(ingr.next()))
				return Status.WRONG;
		}
		if (solution.content.size() != content.size())
			return Status.INCOMPLETE;
		return Status.COMPLETE;
	}
	
	public void addIngredient(String id) {
		content.add(id);
	}
	
	// TODO in case of muddler, shaker ecc
//	public void addMuddler(String id) {
//	
//	}

}
