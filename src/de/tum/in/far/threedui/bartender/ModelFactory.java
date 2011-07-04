package de.tum.in.far.threedui.bartender;

import java.io.File;
import java.io.FileNotFoundException;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

import lm.blender.loader.BlenderLoader;

import org.jdesktop.j3d.loaders.collada.Collada14Loader;
import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import com.microcrowd.loader.java3d.max3ds.Loader3DS;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

public class ModelFactory {
	
	public enum ModelType { VRML, OBJ, COLLADA, TDS, BLEND };
	
	public static ModelObject loadModel(String modelFileName, ModelType modelFileType) throws Exception {
		switch (modelFileType) {
		case VRML: return loadVRMLModel(modelFileName);
		case OBJ: return loadObjModel(modelFileName);
		case COLLADA: return loadColladaModel(modelFileName);
		case TDS: return load3DSModel(modelFileName);
		case BLEND: return loadBlendModel(modelFileName);
		default: throw new Exception();
		}
	}

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
	
	public static ModelObject load3DSModel(String modelFileName) {
		Loader3DS loader = new Loader3DS();
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
	
	public static ModelObject loadBlendModel(String modelFileName) {
		BlenderLoader loader = new BlenderLoader();
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
	
	public static ModelObject loadColladaModel(String modelFileName) {
		Collada14Loader loader = new Collada14Loader();
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
