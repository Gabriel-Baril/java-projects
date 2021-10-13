package infWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import toolkit.PVector;

import geometry.Vertex;
import toolkit.Const;
import toolkit.GraphTab;
import main.Main;

public class InfPanneau extends JPanel{
	private static final long serialVersionUID = 1L;
	String[] pointTitle = {"Point","X","Y","r","θ","int(deg)","int(rad)","ext(deg)","ext(rad)"};
	String[] segmentTitle = {"Segment","Dist","Equation","Start","End"};
	String[] polygonTitle = {"Name","Area","Peri.","nbPo.","nbSeg.","tot.ang","max.ang"};
	String[] baryTitle = {"BaryCenter","X","Y","r","θ"};
	String[] barySegTitle = {"BaryCenter(seg)","Dist","Equation","Start","End"};
	String[] optionTitle = {"Option","State"};
	GraphTab pointTab;
	GraphTab segmentTab;
	GraphTab polygonTab;
	GraphTab baryTab;
	GraphTab barySegTab;
	GraphTab optionTab;

	double barHeight = 15;

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		int nbPoint = 0;
		int nbOptions = 21;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				nbPoint = Const.polygons.get(i).points.size();
			}
		}
		polygonTab = new GraphTab(polygonTitle,setContentPolygon(polygonTitle.length),new PVector(0,0),Const.INF_WIDTH,barHeight*2,Const.INF_WIDTH/polygonTitle.length,barHeight,new Color(0,0,255));
		pointTab = new GraphTab(pointTitle,setContentPoint(pointTitle.length),new PVector(0,barHeight*2),Const.INF_WIDTH,barHeight*(nbPoint + 1),Const.INF_WIDTH/pointTitle.length,barHeight,Color.BLUE);
		segmentTab = new GraphTab(segmentTitle,setContentSegment(segmentTitle.length),new PVector(0,barHeight*(nbPoint + 1) + 2*barHeight),Const.INF_WIDTH,barHeight*(nbPoint + 1),Const.INF_WIDTH/segmentTitle.length,barHeight,new Color(0,0,255));
		baryTab = new GraphTab(baryTitle,setContentBary(baryTitle.length),new PVector(0,2*barHeight*(nbPoint + 1) + 2*barHeight),Const.INF_WIDTH,barHeight*2,Const.INF_WIDTH/baryTitle.length,barHeight,new Color(255,0,0));
		barySegTab = new GraphTab(barySegTitle,setContentBarySegment(barySegTitle.length),new PVector(0,2 * barHeight*(nbPoint + 1) + 4*barHeight),Const.INF_WIDTH,barHeight*(nbPoint + 1),Const.INF_WIDTH/barySegTitle.length,barHeight,new Color(255,0,0));
		optionTab = new GraphTab(optionTitle,setContentOption(optionTitle.length),new PVector(0,3 * barHeight*(nbPoint + 1) + 4*barHeight),Const.INF_WIDTH,barHeight*(nbOptions + 1),Const.INF_WIDTH/optionTitle.length,barHeight,new Color(0,255,0));
		pointTab.construct(g2d,20,10);
		segmentTab.construct(g2d,40,40);
		polygonTab.construct(g2d,30,30);
		baryTab.construct(g2d,40,40);
		barySegTab.construct(g2d, 40, 40);
		optionTab.construct(g2d, 30, 160);
		Main.iFen.setSize(Const.INF_WIDTH,(int)(2 * barHeight*(nbPoint + 1) + 4*barHeight + barHeight*(nbPoint + 1) + nbOptions*barHeight) + 48);
	}

	public String[][] setContentPoint(int titleSize){
		String[][] content = null;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				content = new String[Const.polygons.get(i).points.size()][titleSize];
				for(int j = 0;j < Const.polygons.get(i).points.size();j++) {
					content[j][0] = Const.polygons.get(i).points.get(j).name;
					content[j][1] = String.valueOf((int)Const.polygons.get(i).points.get(j).pos.x);
					content[j][2] = String.valueOf((int)Const.polygons.get(i).points.get(j).pos.y);
					content[j][3] = String.valueOf((int)Const.dist(new Vertex(0,0),new Vertex(Const.polygons.get(i).points.get(j).pos.x,Const.polygons.get(i).points.get(j).pos.y)));
					content[j][4] = String.valueOf((int)Math.toDegrees(Math.atan2(Const.polygons.get(i).points.get(j).pos.y, Const.polygons.get(i).points.get(j).pos.x))) + "°";
					content[j][5] = String.valueOf(String.format("%.1f",Const.polygons.get(i).getAngleOf(Const.polygons.get(i).points.get(j)))) + "°";
					content[j][6] = String.valueOf(String.format("%.2f", Math.toRadians(Const.polygons.get(i).getAngleOf(Const.polygons.get(i).points.get(j)))));
					content[j][7] = String.valueOf(String.format("%.1f",(360 - Const.polygons.get(i).getAngleOf(Const.polygons.get(i).points.get(j))))) + "°";
					content[j][8] = String.valueOf(String.format("%.2f", Math.toRadians(360 - Const.polygons.get(i).getAngleOf(Const.polygons.get(i).points.get(j)))));
				}
			}
		}
		return content;
	}

	public String[][] setContentSegment(int titleSize){
		String[][] content = null;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				content = new String[Const.polygons.get(i).segments.size()][titleSize];
				for(int j = 0;j < Const.polygons.get(i).segments.size();j++) {
					content[j][0] = Const.polygons.get(i).segments.get(j).start.name + Const.polygons.get(i).segments.get(j).end.name;
					content[j][1] = String.valueOf(String.format("%.2f", Const.polygons.get(i).segments.get(j).dist()));
					content[j][2] = Const.polygons.get(i).segments.get(j).getEqu().show() + "0";
					content[j][3] = Const.polygons.get(i).segments.get(j).start.name + "(" + String.valueOf((int)Const.polygons.get(i).segments.get(j).start.pos.x) + "," + String.valueOf((int)Const.polygons.get(i).segments.get(j).start.pos.y) + ")";
					content[j][4] = Const.polygons.get(i).segments.get(j).end.name + "(" + String.valueOf((int)Const.polygons.get(i).segments.get(j).end.pos.x) + "," + String.valueOf((int)Const.polygons.get(i).segments.get(j).end.pos.y) + ")";
				}
			}
		}
		return content;
	}

	public String[][] setContentPolygon(int titleSize){
		String[][] content = null;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				content = new String[1][titleSize];
				Main.iFen.setTitle("GEOMETRY_INFORMATION " + "(" + Const.polygons.get(i).name + ")");
				content[0][0] = Const.polygons.get(i).name;
				content[0][1] = String.valueOf(String.format("%.2f", Const.polygons.get(i).getArea()));
				content[0][2] = String.valueOf(String.format("%.2f", Const.polygons.get(i).getPerimeter()));
				content[0][3] = String.valueOf(Const.polygons.get(i).points.size());
				content[0][4] = String.valueOf(Const.polygons.get(i).segments.size());
				content[0][5] = String.valueOf(((Const.polygons.get(i).points.size() - 2)*180));
				content[0][6] = String.valueOf(((Const.polygons.get(i).points.size() - 2)*180)/Const.polygons.get(i).points.size());
			}
		}
		return content;
	}

	public String[][] setContentBary(int titleSize){
		String[][] content = null;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				content = new String[1][titleSize];
				content[0][0] = Const.polygons.get(i).getBarycenter().name;
				content[0][1] = String.valueOf((int)Const.polygons.get(i).getBarycenter().pos.x);
				content[0][2] = String.valueOf((int)Const.polygons.get(i).getBarycenter().pos.y);
				content[0][3] = String.valueOf((int)Const.dist(new Vertex(0,0),new Vertex(Const.polygons.get(i).getBarycenter().pos.x,Const.polygons.get(i).getBarycenter().pos.y)));
				content[0][4] = String.valueOf((int)Math.toDegrees(Math.atan2(Const.polygons.get(i).getBarycenter().pos.y, Const.polygons.get(i).getBarycenter().pos.x))) + "°";
			}
		}
		return content;
	}

	public String[][] setContentBarySegment(int titleSize){
		String[][] content = null;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				content = new String[Const.polygons.get(i).segments_brc.size()][titleSize];
				for(int j = 0;j < Const.polygons.get(i).segments_brc.size();j++) {
					content[j][0] = Const.polygons.get(i).segments_brc.get(j).start.name + Const.polygons.get(i).segments_brc.get(j).end.name;
					content[j][1] = String.valueOf(String.format("%.2f", Const.polygons.get(i).segments_brc.get(j).dist()));
					content[j][2] = Const.polygons.get(i).segments_brc.get(j).getEqu().show() + "0";
					content[j][3] = Const.polygons.get(i).segments_brc.get(j).start.name + "(" + String.valueOf((int)Const.polygons.get(i).segments_brc.get(j).start.pos.x) + "," + String.valueOf((int)Const.polygons.get(i).segments_brc.get(j).start.pos.y) + ")";
					content[j][4] = Const.polygons.get(i).segments_brc.get(j).end.name + "(" + String.valueOf((int)Const.polygons.get(i).segments_brc.get(j).end.pos.x) + "," + String.valueOf((int)Const.polygons.get(i).segments_brc.get(j).end.pos.y) + ")";
				}
			}
		}
		return content;
	}

	public String[][] setContentOption(int titleSize){
		String[][] content = null;
		for(int i = 0;i < Const.polygons.size();i++) {
			if(Const.polygons.get(i).isFocused()) {
				content = new String[Const.polygons.get(i).options.length][titleSize];
				Const.polygons.get(i).updateOption();
				for(int j = 0;j < Const.polygons.get(i).options.length;j++) {
					content[j][0] = Const.optionNames[j] + " (" + Const.optionShortcuts[j] + ")";
					content[j][1] = String.valueOf(Const.polygons.get(i).options[j]);
				}
			}
		}
		return content;
	}
}
