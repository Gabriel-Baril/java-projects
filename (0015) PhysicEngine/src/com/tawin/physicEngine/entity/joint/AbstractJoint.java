package com.tawin.physicEngine.entity.joint;

import com.tawin.physicEngine.entity.Entity;

public abstract class AbstractJoint implements Entity{
	
	public abstract void link(AbstractJoint joint);
}
