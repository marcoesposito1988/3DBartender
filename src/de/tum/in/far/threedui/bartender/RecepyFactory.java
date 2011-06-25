package de.tum.in.far.threedui.bartender;

import java.awt.List;



public class RecepyFactory {
	
	public RecepyFactory(String path){
		
		List<Recepy> recepies= new List(); //???
		
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
