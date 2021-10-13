package toolkit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import toolkit.PVector;

public class GraphTab {
	PVector pos;
	private double barHeight;
	private double barWidth;
	private double totalWidth;
	private double totalHeight;
	private String[] title;
	private String[][] content;
	private Color color;
	
	public GraphTab(String[] title,String[][] content,PVector pos,double totalWidth,double totalHeight,double barWidth,double barHeight,Color color) {
		this.title = title;
		this.content = content;
		this.pos = pos;
		this.totalWidth = totalWidth;
		this.totalHeight = totalHeight;
		this.barWidth = barWidth;
		this.barHeight = barHeight;
		this.color = color;
	}
	
	public void construct(Graphics2D g2d,int dTitle,int dComponent) {
		for(int i = 0;i < title.length;i++) {
			g2d.setFont(new Font("default",Font.BOLD, 13));
			g2d.drawString(title[i],(int) (pos.x + barWidth*i + barWidth/2 - dTitle),(int) (pos.y + barHeight/2 + 5));
			g2d.setFont(new Font("default",Font.PLAIN, 12));
		}
		
		g2d.setColor(this.color);
		
		g2d.drawLine((int)pos.x,(int)pos.y,(int)pos.x,(int)(pos.y + totalHeight));
		g2d.drawLine((int)pos.x,(int)pos.y,(int)(pos.x + totalWidth),(int)pos.y);
		
		g2d.drawLine((int)(pos.x + totalWidth),(int)pos.y,(int)(pos.x + totalWidth),(int)(pos.y + totalHeight));
		g2d.drawLine((int)pos.x,(int)(pos.y + totalHeight),(int)(pos.x + totalWidth),(int)(pos.y + totalHeight));
		
		for(int i = 1;i < (totalWidth/barWidth);i++) {
			g2d.drawLine((int)(pos.x + barWidth*i),(int)pos.y, (int)(pos.x + barWidth*i), (int)(pos.y + totalHeight));
		}
		
		for(int i = 1;i < (totalHeight/barHeight);i++) {
			g2d.drawLine((int)(pos.x),(int)(pos.y + barHeight*i),(int)(pos.x + totalWidth),(int)(pos.y + barHeight*i));
		}

		g2d.setColor(Color.black);
		
		try {
			for(int i = 0;i < content.length;i++) {
				for(int j = 0;j < content[i].length;j++) {
					g2d.drawString(String.valueOf(content[i][j]), (int)(pos.x + barWidth*j + barWidth/2 - dComponent),(int) (pos.y + barHeight*(i + 1) + barHeight/2 + 4));
				}
			}
		}catch(NullPointerException e) {}
		
	}
}
