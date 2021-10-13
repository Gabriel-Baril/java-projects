package main;

import java.util.ArrayList;

public class PolyArm {
	
	ArrayList<Arm> arms_;
	
	public PolyArm(ArrayList<Arm> arms_){
		this.arms_ = arms_;
	}
	
	public void update(){
		for(int i = 0;i < arms_.size();i++){
			if(i != 0){
				double dx = arms_.get(i-1).posEnd.x - arms_.get(i).posStart.x;
				double dy = arms_.get(i-1).posEnd.y - arms_.get(i).posStart.y;
				arms_.get(i).posStart = arms_.get(i-1).posEnd;
				arms_.get(i).posEnd = new Vec2(arms_.get(i).posEnd.x + dx,arms_.get(i).posEnd.y + dy);
			}
		}
	}
	
	public void target2arms(Vec2 point) {
		double dx = point.x - arms_.get(0).posStart.x;
		double dy = point.y - arms_.get(0).posStart.y;
		double len_1 = arms_.get(0).getLen();
		double len_2 = arms_.get(1).getLen();
		
		double theta_2 = Math.acos((dx*dx + dy*dy - len_1*len_1 - len_2*len_2)/(2*len_1*len_2));
		double theta_1 = Math.atan(dy/dx) - Math.atan((len_2*Math.sin(theta_2))/(len_1 + len_2*Math.cos(theta_2)));
		if(Math.sqrt(dx*dx + dy*dy) <= len_1 + len_2) {
			if(arms_.get(0).posStart.x - point.x <= 0) {
				arms_.get(0).setAngle(Math.toDegrees(theta_1));
				arms_.get(1).setAngle(Math.toDegrees(theta_2) + Math.toDegrees(theta_1));
			} else {
				arms_.get(0).setAngle(180 + Math.toDegrees(theta_1));
				arms_.get(1).setAngle(180 + Math.toDegrees(theta_2) + Math.toDegrees(theta_1));
			}
		}
	}
	
//	public void render(){
//		for(int i = 0;i < arms_.size();i++){
//			arms_.get(i).DRAW();
//		}
//	}
}
