package com.tawin.physicEngine.main;

import com.tawin.physicEngine.PhysicsEngine;
import com.tawin.physicEngine.debug.Debug;
import com.tawin.physicEngine.entity.joint.LinearJoint;
import com.tawin.physicEngine.toolKit.Vec2;

public class Main {
	public static PhysicsEngine engine = new PhysicsEngine();
	public static LinearJoint joint = new LinearJoint(new Vec2(350,350), 40, 45, 20);
	
	
	public static void main(String[] args) {
		new Thread(new Runnable(){
			public void run(){
				Debug.alert("PROGRAM OPENED");
				new Fenetre();
			}
		}).start();
	}
}
