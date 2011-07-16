package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.List;

public class RecipeFactory {
	
	protected static List<Recipe> recipes = new ArrayList<Recipe>();
	
//  TODO loading recipes from an XML file 	
//	public RecipeFactory(String path){
//		loadRecipes(path);
//	}
	
	public static void loadRecipes(){
		
		//Vodka Bull
	
		Recipe VodkaBull = new Recipe("VodkaBull");
		VodkaBull.addIngredient("Vodka");
		VodkaBull.addIngredient("RedBull");
		recipes.add(VodkaBull);
		
		//Gin Tonic
		
		Recipe GinTonic = new Recipe("GinTonic");
		GinTonic.addIngredient("Gin");
		GinTonic.addIngredient("Tonic");
		recipes.add(GinTonic);
		
		// Cuba Libre
		Recipe CubaLibre = new Recipe("CubaLibre");
		CubaLibre.addIngredient("Cola");
		CubaLibre.addIngredient("Rum");
		recipes.add(CubaLibre);
	}
	
	public static Recipe getRandomRecipe() {
		return recipes.get((int)(Math.random()*recipes.size()));
	}
	
	// for menu selection
	public static String[] getRecipesNames() {
		ArrayList<String> recipesNames = new ArrayList<String>();
		for (Recipe r : recipes)
			recipesNames.add(r.name);
		// java... -.-
		String[] typeInd = new String[1];
		return recipesNames.toArray(typeInd);
	}
}
