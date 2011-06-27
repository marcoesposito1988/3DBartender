package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.List;

public class RecipeFactory {
	
	List<Recipe> recepies = new ArrayList<Recipe>();
	
//  TODO loading recipes from an XML file 	
//	public RecipeFactory(String path){
//		loadRecipes(path);
//	}
	
	public RecipeFactory() {
		loadRecipes();
	}
	
	public void loadRecipes(){
		
		//Vodka Bull
	
		Recipe VodkaBull = new Recipe("VodkaBull");
		VodkaBull.addIngredient("Vodka");
		VodkaBull.addIngredient("RedBull");
		recepies.add(VodkaBull);
		
		//Gin Tonic
		
		Recipe GinTonic = new Recipe("GinTonic");
		GinTonic.addIngredient("Gin");
		GinTonic.addIngredient("Tonic");
		recepies.add(GinTonic);
	
	}
	
	public Recipe getRandomRecipe() {
		// TODO Anke: returns a random recipe from the list
		
	}
	
	public String[] getRecepyNames() {
		// TODO Anke: returns a string array with the name of all recipes
	}
}
