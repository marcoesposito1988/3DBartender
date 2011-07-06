package de.tum.in.far.threedui.bartender;

import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PointLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import de.tum.in.far.threedui.bartender.ModelFactory.ModelType;

public class TestViewer {
	
	public class Viewer {

		private static final long serialVersionUID = 1L;

		protected final JFrame frame;
		protected final SimpleUniverse universe;

		private final BranchGroup rootNode;
		
		public Viewer(String frameTitle) {
			frame = new JFrame("3D UI - " + frameTitle);
			
			// Get display information like color depth, etc...
			GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
			
			// Create a 3D canvas using the settings of your display
			Canvas3D canvas3D = new Canvas3D(config);
			canvas3D.setSize(800,600);
			universe = new SimpleUniverse(canvas3D);
			
			// Attach lights, shapes, etc here
			rootNode = new BranchGroup();
			rootNode.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
			universe.addBranchGraph(rootNode);
			
			createLight();
			initKeyNavigation();
			
			frame.getContentPane().add(canvas3D);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public void addObject(BranchGroup object) {
			rootNode.addChild(object);
		}
		
		public void removeObject(BranchGroup object) {
			rootNode.removeChild(object);
		}
		
		public TransformGroup getCameraTransformGroup() {
			// TODO Die kann eigentlich raus
			return universe.getViewingPlatform().getViewPlatformTransform();
		}
		
		private void createLight() {
			// First, create Ambient Light
			//Red, Green, Blue values in 0-1 range
			Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
			
			// Set up the ambient light
			AmbientLight ambientLight = new AmbientLight(white);
			BoundingSphere boundingSphere = new BoundingSphere();
			boundingSphere.setRadius(40.0);
			ambientLight.setInfluencingBounds(boundingSphere);
			
			BranchGroup ambientLightBranchGroup = new BranchGroup();
			ambientLightBranchGroup.addChild(ambientLight);
			
			// Attach it to your scene
			addObject(ambientLightBranchGroup);
			
			// Second, create Directional Light
			// (x,y,z) left, down, backwards
			Vector3f lightDirection = new Vector3f(1.0f, -1.0f, -1.0f);
			DirectionalLight light = new DirectionalLight(
			white, lightDirection);
			light.setInfluencingBounds(boundingSphere);
			
			BranchGroup directionalLightBranchGroup = new BranchGroup();
			directionalLightBranchGroup.addChild(light);

			// Attach it to your scene
			addObject(directionalLightBranchGroup);
			
			// Third, create Point Light
			// Create point ligth with standard attenuation
			PointLight pointLight = new PointLight();
			Point3f position = new Point3f(0.0f,2.f,0.f);
			pointLight.setPosition(position);
			pointLight.setInfluencingBounds(boundingSphere);
			
			BranchGroup pointLightBranchGroup = new BranchGroup();
			pointLightBranchGroup.addChild(pointLight);
			
			//attach it to your scene
			addObject(pointLightBranchGroup);
		}
		
		private void initKeyNavigation() {
			// Get ViewingPlatform from your simple universe
			TransformGroup transformGroupCamera = getCameraTransformGroup();
			
			// Create Behavior and attach it
			KeyNavigatorBehavior keyNavBehav = new KeyNavigatorBehavior(transformGroupCamera);
			
			// Set Bounds
			keyNavBehav.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));

			BranchGroup bg = new BranchGroup();
			bg.addChild(keyNavBehav);
			rootNode.addChild(bg);
		}


	}

	public static final String EXERCISE = "Exercise 1 Solution";
	private Viewer viewer;
	
	public void initializeJava3D() {
		System.out.println("Creating Viewer - " + EXERCISE);
		viewer = new Viewer(EXERCISE);
		
		System.out.println("Done - Enjoy");
	}
	
	public void addObject(BranchGroup obj) {
		this.viewer.addObject(obj);
	}
	
	public void addCameraDisplacement(Vector3d v) {
		Transform3D myt3d = new Transform3D();
		myt3d.setTranslation(v);
		Transform3D cameraPosition = new Transform3D();
		viewer.getCameraTransformGroup().getTransform(cameraPosition);
		cameraPosition.mul(myt3d);
		viewer.getCameraTransformGroup().setTransform(cameraPosition);
	}
	
	public static void main(String[] args) {

		TestViewer exercise1 = new TestViewer();
		exercise1.initializeJava3D();
		BranchGroup bg = new BranchGroup();
		TransformGroup tg = new TransformGroup();
		Transform3D t3d = new Transform3D();
		t3d.setScale(10);
		tg.setTransform(t3d);
		ModelObject glass = null;
		try {
			//glass = ModelFactory.loadModel("bar-set" + File.separator + "cocktail-glass.blend", ModelType.BLEND);
			glass = ModelFactory.loadModel("Umbrella.wrl", ModelType.VRML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tg.addChild(glass);
		bg.addChild(tg);
		exercise1.addObject(bg);
	}
	
}
