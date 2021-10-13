package toolkit;

import java.util.ArrayList;

import main.Panneau;
import main.Window;
import pendulum.DoublePendulum;
import pendulum.Pendulum;

public class Const {
	public static final double GRAVITY = 0.01;
	public static ArrayList<DoublePendulum> doublePendulums = new ArrayList<DoublePendulum>();
	public static ArrayList<Pendulum> pendulums = new ArrayList<Pendulum>();
	public static Panneau panel = new Panneau();
	public static boolean running = true;
	public static int WIDTH = 720;
	public static int HEIGHT = 480;
	public static Window window;
}
