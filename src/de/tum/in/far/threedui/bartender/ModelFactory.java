package de.tum.in.far.threedui.bartender;

import java.io.File;
import java.io.FileNotFoundException;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

public class ModelFactory {

	public static ModelObject loadVRMLModel(String modelFileName) {
		VrmlLoader loader = new VrmlLoader();
		Scene myScene = null;
		try {
			myScene = loader.load( "models" + File.separator + modelFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IncorrectFormatException e) {
			e.printStackTrace();
		} catch (ParsingErrorException e) {
			e.printStackTrace();
		}

		BranchGroup bg = new BranchGroup();
		TransformGroup offset = new TransformGroup();
		bg.addChild(offset);
		offset.addChild(myScene.getSceneGroup());
		
		return new ModelObject(bg);
	}
	
	public static ModelObject loadObjModel(String modelFileName) {
		ObjectFile loader = new ObjectFile();
		Scene myScene = null;
		try {
			myScene = loader.load( "models" + File.separator + modelFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IncorrectFormatException e) {
			e.printStackTrace();
		} catch (ParsingErrorException e) {
			e.printStackTrace();
		}

		BranchGroup bg = new BranchGroup();
		TransformGroup offset = new TransformGroup();
		bg.addChild(offset);
		offset.addChild(myScene.getSceneGroup());
		
		return new ModelObject(bg);
	}

}
