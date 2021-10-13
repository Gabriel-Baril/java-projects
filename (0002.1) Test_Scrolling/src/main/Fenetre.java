package main;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;

	public Fenetre(){
		this.build();
		this.setup();
		this.gameLoop();
	}

	public void build(){
		//this.pack();
		this.addKeyListener(Main.kListener);
		this.setTitle("Scrolling test");
		this.setSize(Main.WIDTH,Main.HEIGHT);
		this.setResizable(true);
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(Main.render);
	}

	public void setup(){
		System.out.println("loading...");
		//Main.images.add(new Sprite("data/background.jpg",0,0,2,2,0));
		//Main.images.add(new Sprite("data/player.png",90,90,10,10,0));
		int[][] map ={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0},
					  {0,0,1,1,1,2,2,2,1,1,1,1,0,0,0,0,0,0,0,0},
					  {1,1,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1},
					  {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
					  {3,2,3,3,2,3,2,2,3,3,2,3,3,3,2,3,2,3,3,3},
					  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
					  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}};
		
		for(int i = 0;i < map.length;i++){
			for(int j = 0;j < map[0].length;j++){
				if(map[j][i] == 0){
					
				}
				if(map[j][i] == 1){
					Main.platforms.add(new Platform(i*10,j*10,new Sprite("data/grass_block.png",i*10,j*10)));
				}
				if(map[j][i] == 2){
					Main.platforms.add(new Platform(i*10,j*10,new Sprite("data/dirt_block.png",i*10,j*10)));
				}
				if(map[j][i] == 3){
					Main.platforms.add(new Platform(i*10,j*10,new Sprite("data/stone_block.png",i*10,j*10)));
				}
			}
		}
		System.out.println("finish");
	}

	public void gameLoop(){
		while(Main.running){
			try{
				Thread.sleep(10);
				System.out.println(Main.player.x);
				for(int i = 0;i < Main.platforms.size();i++){
					Main.platforms.get(i).update();
				}
				Main.player.update();
				Main.player.collideEdge();
				Main.player.collidePlatform();
				Main.player.applyForce(0, 0.01f);
				Main.player.limitMove();
				Main.render.repaint();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
