package de.tum.in.far.threedui.bartender;

import java.util.ArrayList;
import java.util.List;




public class RecipeFactory {
	
	public RecipeFactory(String path){
		
		List<Recipe> recepies = new ArrayList<Recipe>(); //???
		
		public void loadRecepies(){
			
			//Vodka Bull
		
			VodkaBull=new Recepy();
			VodkaBull.addincredients("Vodka");
			VodkaBull.addincredients("RedBull");
			recepies.add(VodkaBull);
			
			//Gin Tonic
			
			GinTonic=new Recepy();
			GinTonic.addincredients("Gin");
			GinTonic.addincredients("Tonic");
			recepies.add(GinTonic);
		
		}
		
		public void RandomRecepies() {
		
			
			
		}
		
		public String getRecepyNames(String RecepyName){
			
		}
		
	}
}
