package de.tum.in.far.threedui.bartender;

import ubitrack.SimplePose;

public class ObstructablePoseReceiver extends PoseReceiver {

	CollidableObject object = null;
	
	public void receivePose(SimplePose pose) {
		object.updatedStatus();
		super.receivePose(pose);
	}

}
