package de.tum.in.far.threedui.bartender;

import java.awt.Font;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;

public class Label extends TransformableObject {
	
	private static ColoringAttributes labelColor = new ColoringAttributes(1.0f, 0f, 0f,ColoringAttributes.NICEST);
	private static int TextSize = 2;
	
	private String text = "New label";
	
	private BranchGroup labelGroup = new BranchGroup();
	
	private TransformGroup textGroup;
	private Appearance textAppear;
	private Shape3D textShape;
	private Text3D textGeom;
	
	private TransformGroup backGroup;
	private Appearance backAppearance;
	private Box backBox;

	private Transform3D backPosition;
	
	public Label(String text) {
		this();
		setText(text);
	}
	
	public Label() {
		
	    textGroup = new TransformGroup();
	    labelGroup.addChild(textGroup);

	    textAppear = new Appearance();
	    textAppear.setColoringAttributes(labelColor);
	    textAppear.setMaterial(new Material());

	    // Create a simple shape leaf node, add it to the scene graph.
	    Font3D font3D = new Font3D(new Font("Helvetica", Font.PLAIN, TextSize),
	        new FontExtrusion());
	    textGeom = new Text3D(font3D, new String(this.text));
	    textGeom.setCapability(ALLOW_BOUNDS_READ);
	    textGeom.setAlignment(Text3D.ALIGN_CENTER);
	    textShape = new Shape3D();
	    textShape.setGeometry(textGeom);
	    textShape.setAppearance(textAppear);
	    textGroup.addChild(textShape);
	    
	    createBack();
	}
	
	public void setText(String text) {
		this.text = text;
		this.textGeom.setString(text);
		this.textShape.setGeometry(textGeom);
		createBack();
		addChild(labelGroup);
	}
	
	private void createBack() {
		if (backGroup != null) {
			removeChild(backGroup);
		}
		backGroup = new TransformGroup();
	    backPosition = new Transform3D();
	    backPosition.setTranslation(new Vector3d(0,0.35*TextSize,0));
	    backGroup.setTransform(backPosition);
	    BoundingBox myb = new BoundingBox(textShape.getBounds());
	    Point3d upper = new Point3d(), lower = new Point3d();
	    myb.getUpper(upper); myb.getLower(lower);
	    double textHeight = upper.y-lower.y;
	    double textWidth = upper.x-lower.x;
		backBox = new Box(0.60f*(float)textWidth,1f*(float)textHeight,0.1f,backAppearance);
		backGroup.addChild(backBox);
		labelGroup.addChild(backGroup);
	}
	
	public static void main(String[] args) {
		TestViewer tv = new TestViewer();
		tv.initializeJava3D();
		Label lbl = new Label();
		lbl.setText("Gin");
		tv.addObject(lbl);
		tv.addCameraDisplacement(new Vector3d(0,0,10));
	}

}
